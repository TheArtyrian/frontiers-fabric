package net.artyrian.frontiers.definition.block.entity.data;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.TowerSpawnerBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class TowerSpawner
{
    public static final String TAG = "SpawnData";
    public static final String CHILD_TAG = "SpawnedChildren";
    public static final int EVENT_SPAWN = 1;
    public static final Vec3i AABB_OFFSET_MIN = new Vec3i(-16, -1, -16);
    public static final Vec3i AABB_OFFSET_MAX = new Vec3i(16, 6, 16);

    private SimpleWeightedRandomList<SpawnData> spawnPotentials = SimpleWeightedRandomList.empty();

    private boolean defeated = false;

    @Nullable private Entity displayable;
    private double rotation;
    private double lastRotation;

    @Nullable private SpawnData nextData;
    private int spawnDelay = 20;
    private int minSpawnDelayNormal = 400;
    private int maxSpawnDelayNormal = 800;
    private int minSpawnDelayEnraged = 200;
    private int maxSpawnDelayEnraged = 400;

    private List<UUID> children = new ArrayList<>();
    private int maxChildrenNormal = 6;
    private int maxChildrenEnraged = 10;
    private int requiredPlayerRange = 16;
    private int spawnRange = 4;

    public TowerSpawner()
    {

    }

    public void clientTick(Level level, BlockPos pos)
    {
        this.defeated = level.getBlockState(pos).is(ModBlocks.TOWER_SPAWNER.get()) && level.getBlockState(pos).getValue(TowerSpawnerBlock.DEFEATED);

        if (!this.playerInRange(level, pos))
        {
            this.lastRotation = this.rotation;
        }
        else if (this.displayable != null)
        {
            boolean enraged = false;
            BlockState stateat = level.getBlockState(pos);
            if (stateat.is(ModBlocks.TOWER_SPAWNER.get()) && stateat.getValue(TowerSpawnerBlock.ENRAGED)) enraged = true;

            RandomSource randomsource = level.getRandom();
            double d0 = (double)pos.getX() + randomsource.nextDouble();
            double d1 = (double)pos.getY() + randomsource.nextDouble();
            double d2 = (double)pos.getZ() + randomsource.nextDouble();
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle((enraged) ? ModParticle.VEX_FLAME_BIG.get() : ModParticle.TOWER_FLAME.get(), d0, d1, d2, 0.0, 0.0, 0.0);
            if (this.spawnDelay > 0) this.spawnDelay--;

            this.lastRotation = this.rotation;
            this.rotation = (this.rotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
        }

    }

    public void serverTick(ServerLevel serverLevel, BlockPos pos)
    {
        this.defeated = serverLevel.getBlockState(pos).is(ModBlocks.TOWER_SPAWNER.get()) && serverLevel.getBlockState(pos).getValue(TowerSpawnerBlock.DEFEATED);

        if (this.playerInRange(serverLevel, pos))
        {
            if (this.spawnDelay == -1) this.delay(serverLevel, pos);

            if (this.spawnDelay > 0) this.spawnDelay--;
            else
            {
                int childCount = 0;
                List<UUID> removalStack = new ArrayList<>();
                if (!this.children.isEmpty())
                {
                    for (UUID id : this.children)
                    {
                        if (id == null || serverLevel.getEntity(id) == null) removalStack.add(id);
                        else childCount++;
                    }
                }

                if (!removalStack.isEmpty()) this.children.removeAll(removalStack);

                boolean enraged = false;
                boolean spawned = false;

                RandomSource randomsource = serverLevel.getRandom();
                SpawnData spawndata = this.getOrCreateNextSpawnData(serverLevel, randomsource, pos);
                int i = 0;

                BlockState stateat = serverLevel.getBlockState(pos);
                if (stateat.is(ModBlocks.TOWER_SPAWNER.get()) && stateat.getValue(TowerSpawnerBlock.ENRAGED)) enraged = true;

                int prepSpawnCnt = (enraged) ? this.maxChildrenEnraged : this.maxChildrenNormal;
                int spawnCount = prepSpawnCnt - childCount;

                while (true)
                {
                    if (i > spawnCount)
                    {
                        if (spawned) this.delay(serverLevel, pos);
                        break;
                    }

                    CompoundTag compoundtag = spawndata.getEntityToSpawn();
                    Optional<EntityType<?>> optional = EntityType.by(compoundtag);
                    if (optional.isEmpty())
                    {
                        this.delay(serverLevel, pos);
                        return;
                    }

                    ListTag listtag = compoundtag.getList("Pos", 6);
                    int j = listtag.size();
                    double d0 = (j >= 1) ? listtag.getDouble(0) : (double)pos.getX() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + 0.5;
                    double d1 = (j >= 2) ? listtag.getDouble(1) : (double)(pos.getY() + randomsource.nextInt(3) - 1);
                    double d2 = (j >= 3) ? listtag.getDouble(2) : (double)pos.getZ() + (randomsource.nextDouble() - randomsource.nextDouble()) * (double)this.spawnRange + 0.5;

                    if (serverLevel.noCollision((optional.get()).getSpawnAABB(d0, d1, d2)))
                    {
                        labelgo:
                        {
                            BlockPos blockpos = BlockPos.containing(d0, d1, d2);
                            if (spawndata.getCustomSpawnRules().isPresent())
                            {
                                if (!(optional.get()).getCategory().isFriendly() && serverLevel.getDifficulty() == Difficulty.PEACEFUL) break labelgo;

                                SpawnData.CustomSpawnRules customRules = spawndata.getCustomSpawnRules().get();
                                if (!customRules.isValidPosition(blockpos, serverLevel)) break labelgo;
                            }
                            else if (!SpawnPlacements.checkSpawnRules((EntityType)optional.get(), serverLevel, MobSpawnType.SPAWNER, blockpos, serverLevel.getRandom()))
                            {
                                break labelgo;
                            }

                            Entity entity = EntityType.loadEntityRecursive(compoundtag, serverLevel, (entityTem) -> {
                                entityTem.moveTo(d0, d1, d2, entityTem.getYRot(), entityTem.getXRot());
                                return entityTem;
                            });
                            if (entity == null)
                            {
                                this.delay(serverLevel, pos);
                                return;
                            }

                            int maxAlive = children.size();

                            if (maxAlive >= prepSpawnCnt)
                            {
                                this.delay(serverLevel, pos);
                                return;
                            }

                            entity.moveTo(entity.getX(), entity.getY(), entity.getZ(), randomsource.nextFloat() * 360.0F, 0.0F);
                            if (entity instanceof Mob mob)
                            {
                                if (spawndata.getCustomSpawnRules().isEmpty() && !mob.checkSpawnRules(serverLevel, MobSpawnType.SPAWNER) || !mob.checkSpawnObstruction(serverLevel)) {
                                    break labelgo;
                                }

                                boolean flag1 = spawndata.getEntityToSpawn().size() == 1 && spawndata.getEntityToSpawn().contains("id", ByteTag.TAG_STRING);
                                if (flag1)
                                {
                                    mob.finalizeSpawn(
                                            serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.SPAWNER, null);
                                }

                                Optional<EquipmentTable> equipment = spawndata.getEquipment();
                                if (mob != null && equipment.isPresent())
                                {
                                    mob.equip(equipment.get());
                                }
                            }

                            if (!serverLevel.tryAddFreshEntityWithPassengers(entity))
                            {
                                this.delay(serverLevel, pos);
                                return;
                            }
                            else
                            {
                                children.add(entity.getUUID());
                            }

                            VectorEventSync.Local.fireEvent(serverLevel, pos, FRLevelEvents.Local.TOWER_SPAWNER_SPAWN, enraged ? 1 : 0);
                            serverLevel.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);

                            if (entity instanceof Mob mob)
                            {
                                Vec3 mobVec = new Vec3(mob.getX(), mob.getY(0.5), mob.getZ());
                                VectorEventSync.Dual.fireEvent(serverLevel, mobVec, pos.getCenter().add(0.0, 0.8, 0.0), FRLevelEvents.Dual.TOWER_SPAWNER_FLAMETRAIL, enraged ? 1 : 0);
                                VectorEventSync.Entity.fireEvent(serverLevel, mob, FRLevelEvents.Entity.TOWER_ENTITY_POOF, enraged ? 1 : 0);
                            }

                            spawned = true;
                        }
                    }

                    i++;
                }
            }
        }
    }

    private void delay(Level level, BlockPos pos)
    {
        boolean enraged = false;
        BlockState stateat = level.getBlockState(pos);
        if (stateat.is(ModBlocks.TOWER_SPAWNER.get()) && stateat.getValue(TowerSpawnerBlock.ENRAGED)) enraged = true;

        RandomSource randomsource = level.random;

        int maxDel = this.maxSpawnDelayNormal;
        int minDel = this.minSpawnDelayNormal;

        if (enraged)
        {
            maxDel = this.maxSpawnDelayEnraged;
            minDel = this.minSpawnDelayEnraged;
        }

        if (maxDel <= minDel)
        {
            this.spawnDelay = minDel;
        }
        else
        {
            this.spawnDelay = minDel + randomsource.nextInt(maxDel - minDel);
            if (this.displayable != null) VectorEventSync.Local.fireEvent(level, pos, FRLevelEvents.Local.TOWER_SPAWNER_TINY_POOF, enraged ? 1 : 0);
        }

        this.spawnPotentials.getRandom(randomsource).ifPresent((datacule) -> this.setNextSpawnData(level, pos, datacule.data()));
        this.broadcast(level, pos, EVENT_SPAWN);
    }

    public void load(@Nullable Level level, BlockPos pos, CompoundTag tag)
    {
        this.spawnDelay = tag.getShort("Delay");
        this.defeated = tag.getBoolean("Defeated");
        boolean flag = tag.contains(TAG, ByteTag.TAG_COMPOUND);
        if (flag)
        {
            SpawnData spawndata = SpawnData.CODEC.parse(NbtOps.INSTANCE, tag.getCompound("SpawnData")).resultOrPartial((p_186391_) -> {
                Frontiers.LOGGER.warn("Invalid SpawnData for Tower Spawner: {}", p_186391_);
            }).orElseGet(SpawnData::new);

            this.setNextSpawnData(level, pos, spawndata);
        }

        if (tag.contains("SpawnPotentials", ByteTag.TAG_LIST))
        {
            ListTag listtag = tag.getList("SpawnPotentials", ByteTag.TAG_COMPOUND);

            this.spawnPotentials = SpawnData.LIST_CODEC.parse(NbtOps.INSTANCE, listtag).resultOrPartial((str) ->
                Frontiers.LOGGER.warn("Invalid SpawnPotentials list for Tower Spawner: {}", str)
            ).orElseGet(SimpleWeightedRandomList::empty);
        }
        else
        {
            this.spawnPotentials = SimpleWeightedRandomList.single(this.nextData != null ? this.nextData : new SpawnData());
        }


        if (tag.contains("SpawnRange", ByteTag.TAG_ANY_NUMERIC))
        {
            this.spawnRange = tag.getShort("SpawnRange");
        }

        if (tag.contains(CHILD_TAG, ByteTag.TAG_LIST))
        {
            ListTag imported = tag.getList(CHILD_TAG, ByteTag.TAG_INT_ARRAY);
            for (Tag tagex : imported)
            {
                children.add(NbtUtils.loadUUID(tagex));
            }
        }

        this.displayable = null;
    }

    public CompoundTag save(CompoundTag tag)
    {
        tag.putShort("Delay", (short)this.spawnDelay);
        tag.putShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
        tag.putShort("SpawnRange", (short)this.spawnRange);
        tag.putBoolean("Defeated", this.defeated);
        if (this.nextData != null)
        {
            tag.put(TAG, SpawnData.CODEC.encodeStart(NbtOps.INSTANCE, this.nextData).getOrThrow((p_337966_) ->
                    new IllegalStateException("Invalid SpawnData: " + p_337966_)
            ));
        }

        tag.put("SpawnPotentials", SpawnData.LIST_CODEC.encodeStart(NbtOps.INSTANCE, this.spawnPotentials).getOrThrow());

        ListTag childs = new ListTag();
        for (UUID uuid : this.children)
        {
            childs.add(NbtUtils.createUUID(uuid));
        }

        if (!childs.isEmpty())
        {
            tag.put(CHILD_TAG, childs);
        }

        return tag;
    }

    private boolean playerInRange(Level level, BlockPos pos)
    {
        return TowerSpawner.playerNearby(level, pos, this.requiredPlayerRange) && !this.defeated;
    }

    public static boolean playerNearby(Level level, BlockPos pos, double range)
    {
        AABB ab = new AABB(Vec3.atCenterOf(pos.offset(AABB_OFFSET_MIN)), Vec3.atCenterOf(pos.offset(AABB_OFFSET_MAX)));
        for (Player player : level.players())
        {
            if (ab.contains(player.position()))
            {
                if (EntitySelector.NO_SPECTATORS.test(player) && EntitySelector.LIVING_ENTITY_STILL_ALIVE.test(player))
                {
                    BlockState on = player.getBlockStateOn();
                    BlockState belowOn = level.getBlockState(player.blockPosition().below(2));

                    if (on.is(ModTags.Blocks.TOWER_WATCHABLES) || belowOn.is(ModTags.Blocks.TOWER_WATCHABLES)) return true;
                }
            }
        }
        return false;
    }

    public void setEntityId(EntityType<?> type, @Nullable Level level, RandomSource random, BlockPos pos)
    {
        this.getOrCreateNextSpawnData(level, random, pos).getEntityToSpawn().putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(type).toString());
    }

    private SpawnData getOrCreateNextSpawnData(@Nullable Level level, RandomSource random, BlockPos pos)
    {
        if (this.nextData != null) return this.nextData;
        else
        {
            this.setNextSpawnData(level, pos, this.spawnPotentials.getRandom(random).map(WeightedEntry.Wrapper::data).orElseGet(SpawnData::new));
            return this.nextData;
        }
    }

    @Nullable
    public Entity getOrCreateDisplayable(Level level, BlockPos pos)
    {
        if (this.displayable == null)
        {
            CompoundTag compoundtag = this.getOrCreateNextSpawnData(level, level.getRandom(), pos).getEntityToSpawn();
            if (!compoundtag.contains("id", ByteTag.TAG_STRING)) return null;

            this.displayable = EntityType.loadEntityRecursive(compoundtag, level, Function.identity());
            //if (compoundtag.size() == 1 && this.displayable instanceof Mob))
        }

        return this.displayable;
    }

    protected void setNextSpawnData(@Nullable Level level, BlockPos pos, SpawnData nextSpawnData)
    {
        this.nextData = nextSpawnData;
    }

    public void broadcast(Level level, BlockPos pos, int eventId)
    {
        level.blockEvent(pos, ModBlocks.TOWER_SPAWNER.get(), eventId, 0);
    }

    public boolean onEvent(Level level, BlockPos pos, int id)
    {
        if (id == EVENT_SPAWN)
        {
            if (level.isClientSide)
            {
                BlockState state = level.getBlockState(pos);
                if (state.is(ModBlocks.TOWER_SPAWNER.get()) && state.getValue(TowerSpawnerBlock.ENRAGED))
                {
                    this.spawnDelay = this.minSpawnDelayEnraged;
                }
                else this.spawnDelay = this.minSpawnDelayNormal;
            }
            return true;
        }
        else return false;
    }

    public boolean isDefeated() { return defeated; }
    public double getRot() { return rotation; }
    public double getRotLast() { return lastRotation; }
    public double getRise()
    {
        if (this.spawnDelay < 200)
        {
            return 0.8 * ((200.0 - ((double)this.spawnDelay)) / 200.0);
        }
        return 0.0;
    }
}
