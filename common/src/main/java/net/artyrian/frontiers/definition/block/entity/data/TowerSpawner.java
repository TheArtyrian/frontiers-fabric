package net.artyrian.frontiers.definition.block.entity.data;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.TowerSpawnerBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
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

    private SimpleWeightedRandomList<SpawnData> spawnPotentials = SimpleWeightedRandomList.empty();

    @Nullable private Entity displayable;
    private double rotation;
    private double lastRotation;

    @Nullable private SpawnData nextData;
    private int spawnDelay = 20;
    private int minSpawnDelayNormal = 400;
    private int maxSpawnDelayNormal = 800;
    private int minSpawnDelayEnraged = 200;
    private int maxSpawnDelayEnraged = 400;

    private List<UUID> children = new ArrayList<>(0);
    private int maxChildrenNormal = 6;
    private int maxChildrenEnraged = 10;
    private int requiredPlayerRange = 16;
    private int spawnRange = 4;

    public TowerSpawner()
    {

    }

    public void clientTick(Level level, BlockPos pos)
    {
        if (!this.playerNearby(level, pos))
        {
            this.lastRotation = this.rotation;
        }
        else if (this.displayable != null)
        {
            RandomSource randomsource = level.getRandom();
            double d0 = (double)pos.getX() + randomsource.nextDouble();
            double d1 = (double)pos.getY() + randomsource.nextDouble();
            double d2 = (double)pos.getZ() + randomsource.nextDouble();
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
            if (this.spawnDelay > 0) {
                --this.spawnDelay;
            }

            this.lastRotation = this.rotation;
            this.rotation = (this.rotation + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0;
        }

    }

    public void serverTick(ServerLevel serverLevel, BlockPos pos)
    {
        if (this.playerNearby(serverLevel, pos))
        {
            if (this.spawnDelay == -1) this.delay(serverLevel, pos);

            if (this.spawnDelay > 0) this.spawnDelay--;
            else
            {
                boolean enraged = false;
                boolean spawned = false;

                RandomSource randomsource = serverLevel.getRandom();
                SpawnData spawndata = this.getOrCreateNextSpawnData(serverLevel, randomsource, pos);
                int i = 0;

                BlockState stateat = serverLevel.getBlockState(pos);
                if (stateat.is(ModBlocks.TOWER_SPAWNER.get()) && stateat.getValue(TowerSpawnerBlock.ENRAGED)) enraged = true;

                int prepSpawnCnt = (enraged) ? this.maxChildrenEnraged : this.maxChildrenNormal;
                int spawnCount = prepSpawnCnt - children.size();

                while (true)
                {
                    if (i >= spawnCount)
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

                            if (maxAlive >= spawnCount)
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

                            serverLevel.levelEvent(2004, pos, 0);
                            serverLevel.gameEvent(entity, GameEvent.ENTITY_PLACE, blockpos);
                            if (entity instanceof Mob mob) mob.spawnAnim();

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
        RandomSource randomsource = level.random;

        int maxDel = this.maxSpawnDelayNormal;
        int minDel = this.minSpawnDelayNormal;
        BlockState stateat = level.getBlockState(pos);
        if (stateat.is(ModBlocks.TOWER_SPAWNER.get()) && stateat.getValue(TowerSpawnerBlock.ENRAGED))
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
        }

        this.spawnPotentials.getRandom(randomsource).ifPresent((datacule) -> {
            this.setNextSpawnData(level, pos, datacule.data());
        });
        this.broadcast(level, pos, EVENT_SPAWN);
    }

    public void load(@Nullable Level level, BlockPos pos, CompoundTag tag)
    {
        this.spawnDelay = tag.getShort("Delay");
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

    private boolean playerNearby(Level level, BlockPos pos)
    {
        return level.hasNearbyAlivePlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, (double)this.requiredPlayerRange);
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

    public double getRot() { return rotation; }
    public double getRotLast() { return lastRotation; }
}
