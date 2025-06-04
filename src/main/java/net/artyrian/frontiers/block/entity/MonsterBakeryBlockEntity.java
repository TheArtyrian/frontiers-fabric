package net.artyrian.frontiers.block.entity;

import com.google.common.collect.Maps;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.MonsterBakeryBlock;
import net.artyrian.frontiers.client.screen.monster_bakery.MonsterBakeryScreenHandler;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

public class MonsterBakeryBlockEntity extends LockableContainerBlockEntity implements SidedInventory
{
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate()
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
        public int size() {
            return 5;
        }
    };

    @Nullable
    private static volatile Map<Item, Integer> fuelMap;
    private static volatile Map<Item, EntityType<? extends LivingEntity>> entityMap;
    public static final int MAX_INCUBATE_TIME = 600;
    /** Unused for now. */
    @Nullable
    private Entity renderedEntity;

    private String entity_id;
    private int burnTime;
    private int fuelTime;
    private int incTime;
    private int incTimeTotal = 1000;
    private int baseMaxIncTime = 1000;
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
        super(ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY, pos, state);
    }

    @Override
    protected Text getContainerName()
    {
        return Text.translatable("container.frontiers.monster_bakery");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks()
    {
        return this.inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        return new MonsterBakeryScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup)
    {
        return createNbt(registryLookup);
    }

    @Override
    public void setStack(int slot, ItemStack stack)
    {
        ItemStack itemStack = this.inventory.get(slot);
        boolean stackWillMesh = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(itemStack, stack);
        this.inventory.set(slot, stack);
        stack.capCount(this.getMaxCount(stack));
        if (slot == 0 && !stackWillMesh)
        {
            this.incTimeTotal = this.getCookTime();
            this.incTime = 0;
            this.spawnChance = this.baseSpawnChance;
            this.markDirty();
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        this.burnTime = nbt.getShort("BurnTime");
        this.fuelTime = nbt.getShort("FuelTime");
        this.incTime = nbt.getShort("IncTime");
        this.incTimeTotal = nbt.getShort("IncTimeTotal");
        this.spawnChance = nbt.getShort("SpawnChance");

        if (nbt.contains("EntityID", NbtElement.STRING_TYPE))
        {
            this.entity_id = nbt.getString("EntityID");
        }
        else
        {
            this.entity_id = null;
        }

        Inventories.readNbt(nbt, this.inventory, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("FuelTime", (short)this.fuelTime);
        nbt.putShort("IncTime", (short)this.incTime);
        nbt.putShort("IncTimeTotal", (short)this.incTimeTotal);
        nbt.putShort("SpawnChance", (short)this.spawnChance);

        if (this.entity_id != null)
        {
            nbt.putString("EntityID", this.entity_id);
        }

        Inventories.writeNbt(nbt, this.inventory, registryLookup);
    }

    @Override
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) { return false; }
    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) { return this.isValid(slot, stack); }
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir)  { return false; }

    @Override
    public boolean isValid(int slot, ItemStack stack)
    {
        if (slot == DISPLAYSLOT[0]) return false;
        else if (slot == ITEMSLOT[0]) return true;
        else return isFuel(stack);
    }

    @Override
    public int[] getAvailableSlots(Direction side)
    {
        if (side == Direction.UP) return ITEMSLOT;
        else return (side == Direction.DOWN) ? DISPLAYSLOT : FUELSLOT;
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
    {
        if (state.isOf(ModBlocks.MONSTER_BAKERY))
        {
            boolean lit = state.get(MonsterBakeryBlock.LIT);
            if (lit)
            {
                if (!blockEntity.isPlayerInRange(world, pos))
                {
                    blockEntity.lastRotation = blockEntity.rotation;
                }
                else
                {
                    Random random = world.getRandom();
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() + random.nextDouble();
                    double f = (double)pos.getZ() + random.nextDouble();

                    world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
                    world.addParticle(ParticleTypes.FLAME, d, e, f, 0.0, 0.0, 0.0);

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

    public static void serverTick(World world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
    {
        if (state.isOf(ModBlocks.MONSTER_BAKERY))
        {
            // Originally this tick ran in both the server and client...which was stupid. Too lazy to move it all here
            whoaBigTick(world, pos, state, blockEntity);
        }
    }

    private static void whoaBigTick(World world, BlockPos pos, BlockState state, MonsterBakeryBlockEntity blockEntity)
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
                EntityType<? extends LivingEntity> mob = createRecipeMap().get(inputSlot.getItem());
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
                        fuelSlot.decrement(1);
                        if (fuelSlot.isEmpty())
                        {
                            Item remainder = item.getRecipeRemainder();
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
                    SpawnResult resultingVal = trySummoningEntity((ServerWorld)world, pos, blockEntity, createRecipeMap().get(inputSlot.getItem()));
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

                            if (blockEntity.spawnChance < 100) blockEntity.spawnChance += 10;
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
                        inputSlot.decrement(1);
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
                blockEntity.incTime = MathHelper.clamp(blockEntity.incTime - 4, 0, blockEntity.incTimeTotal);
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
                blockEntity.entity_id = Registries.ENTITY_TYPE.getId(egg.getEntityType(putStack)).toString();
            }
            else
            {
                blockEntity.entity_id = null;
            }
        }

        if (bakery_active != blockEntity.isActive())
        {
            mark_dirty = true;
            state = state.with(MonsterBakeryBlock.LIT, blockEntity.isActive());
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }

        if (mark_dirty)
        {
            ((ServerWorld)world).getChunkManager().markForUpdate(blockEntity.getPos());
            markDirty(world, pos, state);
        }
    }

    private static SpawnResult trySummoningEntity(ServerWorld world, BlockPos pos, MonsterBakeryBlockEntity entity, EntityType<? extends LivingEntity> entityToSpawn)
    {
        boolean spawnSuccess = false;
        double spawnRange = 4.0;
        int maxNearbyEntites = 6;
        Random random = world.getRandom();
        String entityToSpawnID = Registries.ENTITY_TYPE.getId(entityToSpawn).toString();

        int attemptChance = random.nextBetween(0, 100);
        if (attemptChance <= entity.spawnChance)
        {
            for (int i = 0; i < entity.maxSpawnAmount; i++)
            {
                double attemptX = (double)pos.getX() + (random.nextDouble() - random.nextDouble()) * spawnRange + 0.5;
                double attemptY = (pos.getY() + random.nextInt(3) - 1);
                double attemptZ = (double)pos.getZ() + (random.nextDouble() - random.nextDouble()) * spawnRange + 0.5;

                if (world.isSpaceEmpty(entityToSpawn.getSpawnBox(attemptX, attemptY, attemptZ)))
                {
                    BlockPos spawnPos = BlockPos.ofFloored(attemptX, attemptY, attemptZ);
                    if (!SpawnRestriction.canSpawn(entityToSpawn, world, SpawnReason.SPAWNER, spawnPos, world.getRandom()))
                    {
                        //Frontiers.LOGGER.warn("Cannot spawn here!");
                        continue;
                    }

                    NbtCompound compound = new NbtCompound();
                    compound.putString("id", entityToSpawnID);
                    Entity spawnedEntity = EntityType.loadEntityWithPassengers(compound, world, candidate ->
                    {
                        candidate.refreshPositionAndAngles(attemptX, attemptY, attemptZ, candidate.getYaw(), candidate.getPitch());
                        return candidate;
                    });
                    if (spawnedEntity == null)
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery - malformed entity ID of {} at {}!", entityToSpawnID, pos);
                        return SpawnResult.FAILED;
                    }

                    int entitiesInArea = world.getEntitiesByType(
                            TypeFilter.equals(spawnedEntity.getClass()),
                            new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)
                                    .expand(spawnRange), EntityPredicates.EXCEPT_SPECTATOR
                    ).size();

                    if (entitiesInArea >= maxNearbyEntites)
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery at {}, too many entities ofsame!", pos);
                        return SpawnResult.FAILED;
                    }

                    spawnedEntity.refreshPositionAndAngles(spawnedEntity.getX(), spawnedEntity.getY(), spawnedEntity.getZ(), random.nextFloat() * 360.0F, 0.0F);
                    if (spawnedEntity instanceof MobEntity mob)
                    {
                        if (!mob.canSpawn(world, SpawnReason.SPAWNER))
                        {
                            //Frontiers.LOGGER.warn("Cannot spawn with spawner!");
                            continue;
                        }

                        ((MobEntity)spawnedEntity).initialize(world, world.getLocalDifficulty(spawnedEntity.getBlockPos()), SpawnReason.SPAWNER, null);
                    }

                    if (!world.spawnNewEntityAndPassengers(spawnedEntity))
                    {
                        //Frontiers.LOGGER.warn("Failed to spawn with Monster Bakery at {}! Entity already exists, or something of the sort!", pos);
                        return SpawnResult.FAILED;
                    }

                    world.syncWorldEvent(WorldEvents.SPAWNER_SPAWNS_MOB, pos, 0);
                    world.emitGameEvent(spawnedEntity, GameEvent.ENTITY_PLACE, spawnPos);
                    if (spawnedEntity instanceof MobEntity) ((MobEntity)spawnedEntity).playSpawnEffects();
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

    private static boolean canTryCraft(DefaultedList<ItemStack> slots)
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
        if (this.entity_id != null && Registries.ENTITY_TYPE.get(Identifier.of(this.entity_id)) != null && this.isActive())
        {
            NbtCompound comp = new NbtCompound();
            comp.putString("id", this.entity_id);
            return EntityType.loadEntityWithPassengers(comp, world, Function.identity());
            //Frontiers.LOGGER.info(this.entity_id);

            //return this.renderedEntity;
        }
        else
        {
            this.renderedEntity = null;
            //Frontiers.LOGGER.info((this.entity_id != null) ? this.entity_id : "null!");
            return null;
        }

        //ItemStack egg = this.inventory.get(2);
        //if (!egg.isEmpty() && egg.getItem() instanceof SpawnEggItem eggItem)
        //{
        //    EntityType<?> type = eggItem.getEntityType(egg);
        //    String ID = Registries.ENTITY_TYPE.getId(type).toString();
        //    NbtCompound comp = new NbtCompound();
        //    comp.putString("id", ID);
        //    this.renderedEntity = EntityType.loadEntityWithPassengers(comp, world, Function.identity());
        //    //Frontiers.LOGGER.info(egg.toString());
        //}
        //else
        //{
        //    this.renderedEntity = null;
        //    //Frontiers.LOGGER.info(egg.toString());
        //    return null;
        //}

        //return this.renderedEntity;
    }

    private boolean isPlayerInRange(World world, BlockPos pos)
    {
        return world.isPlayerInRange((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, this.requiredPlayerRange);
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

    public static Map<Item, EntityType<? extends LivingEntity>> createRecipeMap()
    {
        Map<Item, EntityType<? extends LivingEntity>> map = entityMap;

        if (map != null) return map;
        else
        {
            Map<Item, EntityType<? extends LivingEntity>> map2 = defaultRecipes();
            entityMap = map2;
            return map2;
        }
    }

    /** The default list of Monster Bakery fuels, kept seperate to make external editing easier. Feel free to mixin to this! */
    public static Map<Item, Integer> defaultFuels()
    {
        Map<Item, Integer> mapper = Maps.newLinkedHashMap();
        mapper.put(ModItem.INCENSE, 2400);
        mapper.put(ModItem.ECTOPLASM, 1600);
        mapper.put(Items.GHAST_TEAR, 600);
        mapper.put(Items.BLAZE_POWDER, 900);
        mapper.put(ModItem.INVOKE_SHARD, 4200);
        mapper.put(ModItem.END_CRYSTAL_SHARD, 12000);
        return mapper;
    }

    /** The default list of Monster Bakery recipes. Not data driven for now, to keep things secure. Feel free to mixin to this, though! :) */
    public static Map<Item, EntityType<? extends LivingEntity>> defaultRecipes()
    {
        Map<Item, EntityType<? extends LivingEntity>> mapper = Maps.newLinkedHashMap();
        mapper.put(Items.ROTTEN_FLESH, EntityType.ZOMBIE);
        mapper.put(Items.BONE, EntityType.SKELETON);
        mapper.put(Items.SPIDER_EYE, EntityType.SPIDER);
        mapper.put(Items.SLIME_BALL, EntityType.SLIME);
        mapper.put(Items.MAGMA_CREAM, EntityType.MAGMA_CUBE);
        mapper.put(ModItem.ONYX_BONE, EntityType.WITHER_SKELETON);
        mapper.put(ModItem.FROST_BONE, EntityType.STRAY);
        mapper.put(Items.BLAZE_ROD, EntityType.BLAZE);
        mapper.put(Items.PORKCHOP, EntityType.PIG);
        mapper.put(Items.CHICKEN, EntityType.CHICKEN);
        mapper.put(Items.BEEF, EntityType.COW);
        mapper.put(Items.MUTTON, EntityType.SHEEP);
        mapper.put(Items.BREEZE_ROD, EntityType.BREEZE);
        mapper.put(Items.COD, EntityType.COD);
        mapper.put(Items.SALMON, EntityType.SALMON);
        //mapper.put(Items.TROPICAL_FISH, EntityType.TROPICAL_FISH);        ...nah, i got a bad feeling
        mapper.put(Items.PUFFERFISH, EntityType.PUFFERFISH);
        mapper.put(Items.INK_SAC, EntityType.SQUID);
        mapper.put(Items.GLOW_INK_SAC, EntityType.GLOW_SQUID);
        return mapper;
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
        return SpawnEggItem.SPAWN_EGGS.getOrDefault(entity, (SpawnEggItem) Items.ZOMBIE_SPAWN_EGG);
    }

    @Override
    public int size() { return this.inventory.size(); }
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