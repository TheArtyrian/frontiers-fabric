package net.artyrian.frontiers.definition.block.entity;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.MonsterBakeryBlock;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryMenu;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MonsterBakeryBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer
{
    protected NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    protected final ContainerData propertyDelegate = new ContainerData()
    {
        @Override
        public int get(int index)
        {
            switch (index)
            {
                case 0:
                    return MonsterBakeryBlockEntity.this.burnTime;
                case 1:
                    return MonsterBakeryBlockEntity.this.fuelTime;
                case 2:
                    return MonsterBakeryBlockEntity.this.incTime;
                case 3:
                    return MonsterBakeryBlockEntity.this.incTimeTotal;
                case 4:
                    return MonsterBakeryBlockEntity.this.spawnChance;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value)
        {
            switch (index)
            {
                case 0:
                    MonsterBakeryBlockEntity.this.burnTime = value;
                    break;
                case 1:
                    MonsterBakeryBlockEntity.this.fuelTime = value;
                    break;
                case 2:
                    MonsterBakeryBlockEntity.this.incTime = value;
                    break;
                case 3:
                    MonsterBakeryBlockEntity.this.incTimeTotal = value;
                    break;
                case 4:
                    MonsterBakeryBlockEntity.this.spawnChance = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    @Nullable
    private static volatile Map<Item, Integer> fuelMap;
    @Nullable
    private static volatile Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> entityMap;
    public static final int MAX_INCUBATE_TIME = 1000;

    private static final int DEFAULT_PERCENT_INCREASE = 10;
    private static final int SMALL_PERCENT_INCREASE = 5;
    private static final int LARGE_PERCENT_INCREASE = 20;

    private String entity_id;
    private int burnTime;
    private int fuelTime;
    private int incTime;
    private int incTimeTotal = MAX_INCUBATE_TIME;
    private int baseMaxIncTime = MAX_INCUBATE_TIME;
    private int spawnChance = 10;
    private double rotation;
    private double lastRotation;

    private final double requiredPlayerRange = 16.0;
    private final int baseSpawnChance = 10;
    private final int maxSpawnAmount = 4;

    private final int[] ITEMSLOT = new int[]{0};
    private final int[] FUELSLOT = new int[]{1};
    private final int[] DISPLAYSLOT = new int[]{2};

    public MonsterBakeryBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY.get(), pos, state);
    }

    @Override
    protected Component getDefaultName()
    {
        return Component.translatable("container.frontiers.monster_bakery");
    }

    @Override
    protected NonNullList<ItemStack> getItems()
    {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory)
    {
        return new MonsterBakeryMenu(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup)
    {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        ItemStack itemStack = this.inventory.get(slot);
        boolean stackWillMesh = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, stack);
        this.inventory.set(slot, stack);
        stack.limitSize(this.getMaxStackSize(stack));
        if (slot == 0 && !stackWillMesh)
        {
            this.incTimeTotal = this.getCookTime();
            this.incTime = 0;
            this.spawnChance = this.baseSpawnChance;
            this.setChanged();
        }
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        this.burnTime = nbt.getShort("BurnTime");
        this.fuelTime = nbt.getShort("FuelTime");
        this.incTime = nbt.getShort("IncTime");
        this.incTimeTotal = nbt.getShort("IncTimeTotal");
        this.spawnChance = nbt.getShort("SpawnChance");

        if (nbt.contains("EntityID", Tag.TAG_STRING))
        {
            this.entity_id = nbt.getString("EntityID");
        }
        else
        {
            this.entity_id = null;
        }

        ContainerHelper.loadAllItems(nbt, this.inventory, registryLookup);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("FuelTime", (short)this.fuelTime);
        nbt.putShort("IncTime", (short)this.incTime);
        nbt.putShort("IncTimeTotal", (short)this.incTimeTotal);
        nbt.putShort("SpawnChance", (short)this.spawnChance);

        if (this.entity_id != null)
        {
            nbt.putString("EntityID", this.entity_id);
        }

        ContainerHelper.saveAllItems(nbt, this.inventory, registryLookup);
    }

    @Override
    public boolean canTakeItem(Container hopperInventory, int slot, ItemStack stack) { return false; }
    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) { return this.canPlaceItem(slot, stack); }
    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir)  { return false; }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack)
    {
        if (slot == DISPLAYSLOT[0]) return false;
        else if (slot == ITEMSLOT[0]) return true;
        else return isFuel(stack);
    }

    @Override
    public int[] getSlotsForFace(Direction side)
    {
        if (side == Direction.UP) return ITEMSLOT;
        else return (side == Direction.DOWN) ? DISPLAYSLOT : FUELSLOT;
    }

    public static void clientTick(Level world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
    {
        if (state.is(ModBlocks.MONSTER_BAKERY.get()))
        {
            boolean lit = state.getValue(MonsterBakeryBlock.LIT);
            if (lit)
            {
                if (!blockEntity.isPlayerInRange(world, pos))
                {
                    blockEntity.lastRotation = blockEntity.rotation;
                }
                else
                {
                    RandomSource random = world.getRandom();
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() + random.nextDouble();
                    double f = (double)pos.getZ() + random.nextDouble();

                    ParticleOptions flame = ParticleTypes.FLAME;

                    if (Frontiers.DUNGEONS_DELIGHT_LOADED)
                    {
                        Optional<ParticleType<?>> typer = BuiltInRegistries.PARTICLE_TYPE.getOptional(
                                Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "living_flame")
                        );

                        if (typer.isPresent())
                        {
                            flame = (SimpleParticleType)typer.get();
                        }
                    }

                    world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                    world.addParticle(flame, d, e, f, 0.0, 0.0, 0.0);

                    blockEntity.lastRotation = blockEntity.rotation;

                    blockEntity.incTime++;
                    if (blockEntity.incTime >= blockEntity.incTimeTotal) blockEntity.incTime = 0;

                    double caser = blockEntity.incTimeTotal - blockEntity.incTime;
                    double roundOut = (double)(blockEntity.incTimeTotal) / (double)(caser + 200.0);
                    blockEntity.rotation = (blockEntity.rotation + roundOut) % 360.0;
                }
            }
            else
            {
                blockEntity.lastRotation = blockEntity.rotation;
            }
        }
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
    {
        if (state.is(ModBlocks.MONSTER_BAKERY.get()))
        {
            // Originally this tick ran in both the server and client...which was stupid. Too lazy to move it all here
            whoaBigTick(world, pos, state, blockEntity);
        }
    }

    private static void whoaBigTick(Level world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
    {
        Item itemInSlot = blockEntity.inventory.get(2).getItem();
        Item targetSlotItem;

        boolean bakery_active = blockEntity.isActive();
        boolean mark_dirty = false;
        boolean doOutputPasse = false;

        if (blockEntity.isActive() && blockEntity.isPlayerInRange(world, pos)) blockEntity.burnTime--;

        ItemStack inputSlot = blockEntity.inventory.get(0);
        ItemStack fuelSlot = blockEntity.inventory.get(1);
        boolean inputFull = !inputSlot.isEmpty();
        boolean fuelFull = !fuelSlot.isEmpty();

        if (blockEntity.isActive() || inputFull && fuelFull)
        {
            if (MonsterBakeryBlockEntity.isRecipeItem(inputSlot))
            {
                EntityType<? extends LivingEntity> mob = createRecipeMap().get(inputSlot.getItem()).getFirst();
                targetSlotItem = MonsterBakeryBlockEntity.getSpawnEggItem(mob);

                if (targetSlotItem != itemInSlot)
                {
                    doOutputPasse = true;
                    mark_dirty = true;
                }
            }
            else
            {
                targetSlotItem = Items.AIR;
                if (!blockEntity.inventory.get(2).isEmpty())
                {
                    doOutputPasse = true;
                    mark_dirty = true;
                }
            }

            if (!blockEntity.isActive() && canTryCraft(blockEntity.inventory) && blockEntity.isPlayerInRange(world, pos))
            {
                blockEntity.burnTime = blockEntity.getFuelTime(fuelSlot);
                blockEntity.fuelTime = blockEntity.burnTime;
                if (blockEntity.isActive())
                {
                    mark_dirty = true;
                    if (fuelFull)
                    {
                        Item item = fuelSlot.getItem();
                        fuelSlot.shrink(1);
                        if (fuelSlot.isEmpty())
                        {
                            Item remainder = item.getCraftingRemainingItem();
                            blockEntity.inventory.set(1, remainder == null ? ItemStack.EMPTY : new ItemStack(remainder));
                        }
                    }
                }
            }

            if (blockEntity.isActive() && canTryCraft(blockEntity.inventory) && blockEntity.isPlayerInRange(world, pos))
            {
                blockEntity.incTime++;
                if (blockEntity.incTime == blockEntity.incTimeTotal)
                {
                    SpawnResult resultingVal = trySummoningEntity((ServerLevel)world, pos, blockEntity, createRecipeMap().get(inputSlot.getItem()).getFirst());
                    boolean decrementStack = false;

                    switch (resultingVal)
                    {
                        case SUCCESS:
                        {
                            blockEntity.incTime = 0;
                            blockEntity.incTimeTotal = blockEntity.getCookTime();

                            blockEntity.spawnChance = 10;
                            decrementStack = true;
                        }
                        break;

                        case RANDOM_CHANCE_FAILED:
                        {
                            blockEntity.incTime = 0;
                            blockEntity.incTimeTotal = blockEntity.getCookTime();

                            if (blockEntity.spawnChance < 100) blockEntity.spawnChance += createRecipeMap().get(inputSlot.getItem()).getSecond();
                            decrementStack = true;
                        }
                        break;

                        case FAILED:
                        {
                            blockEntity.incTime -= 20;
                            //Frontiers.LOGGER.warn("Failed to spawn entities using Monster Bakery at {}!", pos.toString());
                        }
                        break;
                    }

                    if (decrementStack)
                    {
                        inputSlot.shrink(1);
                        if (inputSlot.isEmpty())
                        {
                            blockEntity.inventory.set(0, ItemStack.EMPTY);
                        }
                    }

                    mark_dirty = true;
                }
            }
            else if (blockEntity.isPlayerInRange(world, pos))
            {
                blockEntity.incTime = 0;
            }
        }
        else
        {
            targetSlotItem = Items.AIR;
            if (!blockEntity.inventory.get(2).isEmpty())
            {
                doOutputPasse = true;
            }

            if (!blockEntity.isActive() && blockEntity.incTime > 0)
            {
                blockEntity.incTime = Mth.clamp(blockEntity.incTime - 4, 0, blockEntity.incTimeTotal);
                if (blockEntity.incTime == 0) blockEntity.spawnChance = blockEntity.baseSpawnChance;
            }
        }

        if (doOutputPasse)
        {
            ItemStack putStack;
            putStack = (targetSlotItem == Items.AIR) ? ItemStack.EMPTY : new ItemStack(targetSlotItem, 1);
            blockEntity.inventory.set(2, putStack);
            mark_dirty = true;

            if (!putStack.isEmpty() && putStack.getItem() instanceof SpawnEggItem egg)
            {
                blockEntity.entity_id = BuiltInRegistries.ENTITY_TYPE.getKey(egg.getType(putStack)).toString();
            }
            else
            {
                blockEntity.entity_id = null;
            }
        }

        if (bakery_active != blockEntity.isActive())
        {
            mark_dirty = true;
            state = state.setValue(MonsterBakeryBlock.LIT, blockEntity.isActive());
            world.setBlock(pos, state, Block.UPDATE_ALL);
        }

        if (mark_dirty)
        {
            ((ServerLevel)world).getChunkSource().blockChanged(blockEntity.getBlockPos());
            setChanged(world, pos, state);
        }
    }

    private static SpawnResult trySummoningEntity(ServerLevel world, BlockPos pos, MonsterBakeryBlockEntity entity, EntityType<? extends LivingEntity> entityToSpawn)
    {
        boolean spawnSuccess = false;
        double spawnRange = 4.0;
        int maxNearbyEntites = 6;
        RandomSource random = world.getRandom();
        String entityToSpawnID = BuiltInRegistries.ENTITY_TYPE.getKey(entityToSpawn).toString();

        int attemptChance = random.nextIntBetweenInclusive(0, 100);
        if (attemptChance <= entity.spawnChance)
        {
            for (int i = 0; i < entity.maxSpawnAmount; i++)
            {
                double attemptX = (double)pos.getX() + (random.nextDouble() - random.nextDouble()) * spawnRange + 0.5;
                double attemptY = pos.getY();
                double attemptZ = (double)pos.getZ() + (random.nextDouble() - random.nextDouble()) * spawnRange + 0.5;

                if (world.noCollision(entityToSpawn.getSpawnAABB(attemptX, attemptY, attemptZ)))
                {
                    BlockPos spawnPos = BlockPos.containing(attemptX, attemptY, attemptZ);
                    if (!SpawnPlacements.checkSpawnRules(entityToSpawn, world, MobSpawnType.SPAWNER, spawnPos, world.getRandom()))
                    {
                        //Frontiers.LOGGER.warn("Cannot spawn here!");
                        continue;
                    }

                    CompoundTag compound = new CompoundTag();
                    compound.putString("id", entityToSpawnID);
                    Entity spawnedEntity = EntityType.loadEntityRecursive(compound, world, candidate ->
                    {
                        candidate.moveTo(attemptX, attemptY, attemptZ, candidate.getYRot(), candidate.getXRot());
                        return candidate;
                    });
                    if (spawnedEntity == null)
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery - malformed entity ID of {} at {}!", entityToSpawnID, pos);
                        return SpawnResult.FAILED;
                    }

                    int entitiesInArea = world.getEntities(
                            EntityTypeTest.forExactClass(spawnedEntity.getClass()),
                            new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)
                                    .inflate(spawnRange), EntitySelector.NO_SPECTATORS
                    ).size();

                    if (entitiesInArea >= maxNearbyEntites)
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery at {}, too many entities ofsame!", pos);
                        return SpawnResult.FAILED;
                    }

                    spawnedEntity.moveTo(spawnedEntity.getX(), spawnedEntity.getY(), spawnedEntity.getZ(), random.nextFloat() * 360.0F, 0.0F);
                    if (spawnedEntity instanceof Mob mob)
                    {
                        if (!mob.checkSpawnRules(world, MobSpawnType.SPAWNER))
                        {
                            //Frontiers.LOGGER.warn("Cannot spawn with spawner!");
                            continue;
                        }

                        ((Mob)spawnedEntity).finalizeSpawn(world, world.getCurrentDifficultyAt(spawnedEntity.blockPosition()), MobSpawnType.SPAWNER, null);
                    }

                    if (!world.tryAddFreshEntityWithPassengers(spawnedEntity))
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery at {}! Entity already exists, or something of the sort!", pos);
                        return SpawnResult.FAILED;
                    }

                    world.levelEvent(LevelEvent.PARTICLES_MOBBLOCK_SPAWN, pos, 0);
                    world.gameEvent(spawnedEntity, GameEvent.ENTITY_PLACE, spawnPos);
                    if (spawnedEntity instanceof Mob) ((Mob)spawnedEntity).spawnAnim();
                    spawnSuccess = true;
                }
            }

            return (spawnSuccess) ? SpawnResult.SUCCESS : SpawnResult.FAILED;
        }
        else
        {
            return SpawnResult.RANDOM_CHANCE_FAILED;
        }
    }

    private static boolean canTryCraft(NonNullList<ItemStack> slots)
    {
        if (!slots.get(0).isEmpty() && isRecipeItem(slots.get(0)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Nullable
    public Entity getRenderedEntity()
    {
        if (this.entity_id != null && BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(this.entity_id)) != null && this.isActive())
        {
            CompoundTag comp = new CompoundTag();
            comp.putString("id", this.entity_id);
            return EntityType.loadEntityRecursive(comp, level, Function.identity());
        }
        else
        {
            return null;
        }
    }

    private boolean isPlayerInRange(Level world, BlockPos pos)
    {
        return world.hasNearbyAlivePlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, this.requiredPlayerRange);
    }

    public boolean isActive() { return this.burnTime > 0; }
    public static boolean isFuel(ItemStack stack) { return createFuelMap().containsKey(stack.getItem()); }
    public static boolean isRecipeItem(ItemStack stack) { return createRecipeMap().containsKey(stack.getItem()); }
    public static void clearFuel() { fuelMap = null; }
    public static void clearRecipes() { entityMap = null; }

    public static Map<Item, Integer> createFuelMap()
    {
        Map<Item, Integer> map = fuelMap;

        if (map != null) return map;
        else
        {
            Map<Item, Integer> map2 = defaultFuels();
            fuelMap = map2;
            return map2;
        }
    }

    public static Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> createRecipeMap()
    {
        Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> map = entityMap;

        if (map != null) return map;
        else
        {
            Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> map2 = defaultRecipes();
            entityMap = map2;
            return map2;
        }
    }

    /** The default list of Monster Bakery fuels, kept seperate to make external editing easier. Feel free to mixin to this! */
    public static Map<Item, Integer> defaultFuels()
    {
        Map<Item, Integer> mapper = Maps.newLinkedHashMap();
        mapper.put(ModItem.INCENSE.get(), 3200);
        mapper.put(ModItem.ECTOPLASM.get(), 2400);
        mapper.put(ModItem.SOUL.get(), 2000);
        mapper.put(Items.GHAST_TEAR, 600);
        mapper.put(Items.BLAZE_POWDER, 900);
        mapper.put(ModItem.INVOKE_SHARD.get(), 4200);
        mapper.put(ModItem.END_CRYSTAL_SHARD.get(), 12000);
        modFuels(mapper);
        return mapper;
    }

    private static void modFuels(Map<Item, Integer> mapper)
    {
        // Appledog - AEU version
        if (Frontiers.AEU_LOADED)
        {
            Optional<Item> red_40 = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AEU_ID, "red_40"));

            red_40.ifPresent(item -> mapper.put(item, 10));
        }

        // Dungeons Delight
        if (Frontiers.DUNGEONS_DELIGHT_LOADED)
        {
            Optional<Item> stained_scrap = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap"));
            Optional<Item> stained_frag = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_fragment"));
            Optional<Item> gunk = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "gunk"));

            stained_scrap.ifPresent(item -> mapper.put(item, 2000));
            stained_frag.ifPresent(item -> mapper.put(item, 200));
            gunk.ifPresent(item -> mapper.put(item, 120));
        }
    }

    /** The default list of Monster Bakery recipes. Not data driven for now, to keep things secure. Feel free to mixin to this, though! :) */
    public static Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> defaultRecipes()
    {
        Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> mapper = Maps.newLinkedHashMap();
        mapper.put(Items.ROTTEN_FLESH, Pair.of(EntityType.ZOMBIE, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.BONE, Pair.of(EntityType.SKELETON, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.SPIDER_EYE, Pair.of(EntityType.SPIDER, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.SLIME_BALL, Pair.of(EntityType.SLIME, 5));
        mapper.put(Items.MAGMA_CREAM, Pair.of(EntityType.MAGMA_CUBE, DEFAULT_PERCENT_INCREASE));
        mapper.put(ModItem.ONYX_BONE.get(), Pair.of(EntityType.WITHER_SKELETON, DEFAULT_PERCENT_INCREASE));
        mapper.put(ModItem.FROST_BONE.get(), Pair.of(EntityType.STRAY, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.BLAZE_ROD, Pair.of(EntityType.BLAZE, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.PORKCHOP, Pair.of(EntityType.PIG, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.CHICKEN, Pair.of(EntityType.CHICKEN, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.BEEF, Pair.of(EntityType.COW, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.MUTTON, Pair.of(EntityType.SHEEP, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.BREEZE_ROD, Pair.of(EntityType.BREEZE, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.COD, Pair.of(EntityType.COD, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.SALMON, Pair.of(EntityType.SALMON, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.PUFFERFISH, Pair.of(EntityType.PUFFERFISH, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.INK_SAC, Pair.of(EntityType.SQUID, DEFAULT_PERCENT_INCREASE));
        mapper.put(Items.GLOW_INK_SAC, Pair.of(EntityType.GLOW_SQUID, DEFAULT_PERCENT_INCREASE));
        mapper.put(ModItem.GUARDIAN_SLICE.get(), Pair.of(EntityType.GUARDIAN, DEFAULT_PERCENT_INCREASE));
        modRecipes(mapper);
        return mapper;
    }

    /** I like playing with other mods hiii other mods :3 */
    private static void modRecipes(Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> mapper)
    {
        // Appledog - AEU version
        if (Frontiers.AEU_LOADED)
        {
            int lame_percent_chance = 1;

            Optional<EntityType<?>> appledog_aeu = BuiltInRegistries.ENTITY_TYPE.getOptional(Frontiers.id(Frontiers.AEU_ID, "appledog"));

            Optional<Item> dogapple = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AEU_ID, "dogapple"));
            Optional<Item> applerock = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AEU_ID, "applerock"));
            Optional<Item> dollar = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AEU_ID, "appledogllar"));

            appledog_aeu.ifPresent(entity ->
            {
                EntityType<? extends LivingEntity> boilerplateMoment = (EntityType<? extends LivingEntity>) entity;

                dogapple.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, DEFAULT_PERCENT_INCREASE)));
                applerock.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, lame_percent_chance)));
                dollar.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, lame_percent_chance)));
            });
        }

        // Dungeons Delight
        if (Frontiers.DUNGEONS_DELIGHT_LOADED)
        {
            Optional<Item> tripe = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "rotten_tripe"));
            Optional<Item> gritty_flesh = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "gritty_flesh"));
            Optional<Item> brined_flesh = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "brined_flesh"));
            Optional<Item> sixsevenskibidi = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "bogged_brain"));
            Optional<Item> spider_meat = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "spider_meat"));
            Optional<Item> silverfish_meat = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "silverfish_abdomen"));
            Optional<Item> gunk = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "gunk"));

            Optional<EntityType<?>> dryad_dd = BuiltInRegistries.ENTITY_TYPE.getOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "zombified_dryad"));

            tripe.ifPresent(item -> mapper.put(item, Pair.of(EntityType.ZOMBIE, DEFAULT_PERCENT_INCREASE)));
            gritty_flesh.ifPresent(item -> mapper.put(item, Pair.of(EntityType.HUSK, DEFAULT_PERCENT_INCREASE)));
            brined_flesh.ifPresent(item -> mapper.put(item, Pair.of(EntityType.DROWNED, DEFAULT_PERCENT_INCREASE)));
            sixsevenskibidi.ifPresent(item -> mapper.put(item, Pair.of(EntityType.BOGGED, DEFAULT_PERCENT_INCREASE)));
            spider_meat.ifPresent(item -> mapper.put(item, Pair.of(EntityType.SPIDER, DEFAULT_PERCENT_INCREASE)));
            silverfish_meat.ifPresent(item -> mapper.put(item, Pair.of(EntityType.SILVERFISH, DEFAULT_PERCENT_INCREASE)));

            dryad_dd.ifPresent(entity ->
            {
                EntityType<? extends LivingEntity> boilerplateMoment = (EntityType<? extends LivingEntity>) entity;

                gunk.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, SMALL_PERCENT_INCREASE)));
            });
        }

        // Aether
        if (Frontiers.AETHER_LOADED)
        {
            Optional<Item> swet_ball = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AETHER_ID, "swet_ball"));
            Optional<Item> aechor_petal = BuiltInRegistries.ITEM.getOptional(Frontiers.id(Frontiers.AETHER_ID, "aechor_petal"));

            Optional<EntityType<?>> aechor_plant = BuiltInRegistries.ENTITY_TYPE.getOptional(Frontiers.id(Frontiers.AETHER_ID, "aechor_plant"));
            Optional<EntityType<?>> blue_swet = BuiltInRegistries.ENTITY_TYPE.getOptional(Frontiers.id(Frontiers.AETHER_ID, "blue_swet"));

            aechor_plant.ifPresent(entity ->
            {
                EntityType<? extends LivingEntity> boilerplateMoment = (EntityType<? extends LivingEntity>) entity;

                aechor_petal.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, DEFAULT_PERCENT_INCREASE)));
            });

            blue_swet.ifPresent(entity ->
            {
                EntityType<? extends LivingEntity> boilerplateMoment = (EntityType<? extends LivingEntity>) entity;

                swet_ball.ifPresent(item -> mapper.put(item, Pair.of(boilerplateMoment, SMALL_PERCENT_INCREASE)));
            });
        }
    }

    protected int getFuelTime(ItemStack fuel)
    {
        if (fuel.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = fuel.getItem();
            return createFuelMap().getOrDefault(item, 0);
        }
    }

    /** Here as a wrap in case more funct is added later. */
    protected int getCookTime()
    {
        return baseMaxIncTime;
    }

    public static Item getSpawnEggItem(EntityType<? extends LivingEntity> entity)
    {
        Optional<Item> defaultTo = Optional.ofNullable(SpawnEggItem.BY_ID.get(entity));
        if (defaultTo.isPresent()) return defaultTo.get();

        ResourceLocation loc = BuiltInRegistries.ENTITY_TYPE.getKey(entity);

        // Modded egg check - attempt I
        Optional<Item> attempt1 = BuiltInRegistries.ITEM.getOptional(Frontiers.id(loc.getNamespace(), loc.getPath() + "_spawn_egg"));
        if (attempt1.isPresent()) return attempt1.get();

        // Modded egg check - attempt II (or default to Zombie)
        Optional<Item> attempt2 = BuiltInRegistries.ITEM.getOptional(Frontiers.id(loc.getNamespace(), "spawn_egg_" + loc.getPath()));
        if (attempt2.isPresent()) return attempt2.get();

        // Failure
        else return Items.ZOMBIE_SPAWN_EGG;
    }

    @Override
    public int getContainerSize() { return this.inventory.size(); }
    public double getRot() { return this.rotation; }
    public double getLastRot() { return this.lastRotation; }

    /** A private enum used in the server tick to determine the result of a spawn attempt. */
    private enum SpawnResult
    {
        SUCCESS,                    // Mobs were successfully spawned.
        RANDOM_CHANCE_FAILED,       // The random chance failed.
        FAILED                      // The random chance passed, but the spawn attempt failed.
    }
}