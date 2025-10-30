package net.artyrian.frontiers.block.entity;

import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.*;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonalChestBlockEntity extends LootableContainerBlockEntity implements LidOpenable
{
    private static final int VIEWER_COUNT_UPDATE_EVENT_TYPE = 1;

    private int cooldown_time = 0;
    private UUID owner = null;
    private List<UUID> allowed_users = new ArrayList<>();
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);

    private final ViewerCountManager stateManager = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            PersonalChestBlockEntity.playSound(world, pos, state, ModSounds.PERSONAL_CHEST_OPEN);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            PersonalChestBlockEntity.playSound(world, pos, state, ModSounds.PERSONAL_CHEST_CLOSE);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            PersonalChestBlockEntity.this.onViewerCountUpdate(world, pos, state, oldViewerCount, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (!(player.currentScreenHandler instanceof GenericContainerScreenHandler)) {
                return false;
            } else {
                Inventory inventory = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                return inventory == PersonalChestBlockEntity.this;
            }
        }
    };
    private final ChestLidAnimator lidAnimator = new ChestLidAnimator();

    public PersonalChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) { super(type, pos, state); }
    public PersonalChestBlockEntity(BlockPos pos, BlockState state) { this(ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY, pos, state); }

    @Override
    public int size() {
        return 27;
    }
    @Override
    protected Text getContainerName() { return Text.translatable("container.frontiers.personal_chest"); }
    @Override
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) { return false; }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);

        if (!this.readLootTable(nbt))
        {
            Inventories.readNbt(nbt, this.inventory, registryLookup);
        }
        if (nbt.containsUuid("Owner"))
        {
            UUID testuuid = nbt.getUuid("Owner");
            if (testuuid != null) this.owner = testuuid;
        }

        if (nbt.contains("AllowedUsers", NbtElement.LIST_TYPE))
        {
            NbtList nbtList = (NbtList)nbt.get("AllowedUsers");

            if (nbtList != null && !nbtList.isEmpty())
            {
                for (int i = 0; i < nbtList.size(); i++)
                {
                    this.allowed_users.add(NbtHelper.toUuid(nbtList.get(i)));
                }
            }
        }

        if (nbt.contains("CooldownTime", NbtElement.INT_TYPE)) this.cooldown_time = nbt.getInt("CooldownTime");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        if (!this.writeLootTable(nbt))
        {
            Inventories.writeNbt(nbt, this.inventory, registryLookup);
        }
        if (this.owner != null) {
            nbt.putUuid("Owner", this.owner);
        }

        if (!this.allowed_users.isEmpty())
        {
            NbtList allowedlist = new NbtList();

            for (UUID allowedUser : this.allowed_users)
            {
                allowedlist.add(NbtHelper.fromUuid(allowedUser));
            }

            if (!allowedlist.isEmpty()) { nbt.put("AllowedUsers", allowedlist); }
        }

        nbt.putInt("CooldownTime", this.cooldown_time);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, PersonalChestBlockEntity blockEntity)
    {
        blockEntity.lidAnimator.step();
        if (blockEntity.cooldown_time > 0) blockEntity.cooldown_time--;
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, PersonalChestBlockEntity blockEntity)
    {
        if (blockEntity.cooldown_time > 0) blockEntity.cooldown_time--;
    }

    static void playSound(World world, BlockPos pos, BlockState state, SoundEvent soundEvent)
    {
        double d = (double)pos.getX() + 0.5;
        double e = (double)pos.getY() + 0.5;
        double f = (double)pos.getZ() + 0.5;

        world.playSound(null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.lidAnimator.setOpen(data > 0);
            return true;
        } else {
            return super.onSyncedBlockEvent(type, data);
        }
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }
    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }
    @Override
    public float getAnimationProgress(float tickDelta) { return this.lidAnimator.getProgress(tickDelta); }

    @Nullable @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) { return createNbt(registryLookup); }

    public static int getPlayersLookingInChestCount(BlockView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.hasBlockEntity()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PersonalChestBlockEntity) {
                return ((PersonalChestBlockEntity)blockEntity).stateManager.getViewerCount();
            }
        }

        return 0;
    }

    public static void copyInventory(PersonalChestBlockEntity from, PersonalChestBlockEntity to)
    {
        DefaultedList<ItemStack> defaultedList = from.getHeldStacks();
        from.setHeldStacks(to.getHeldStacks());
        to.setHeldStacks(defaultedList);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    public void onScheduledTick()
    {
        if (!this.removed)
        {
            this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount)
    {
        Block block = state.getBlock();
        world.addSyncedBlockEvent(pos, block, 1, newViewerCount);
    }

    // UNIQUE: Checks if the player is the owner.
    public boolean playerOwnerMatches(UUID uuid)
    {
        if (this.owner != null)
        {
            return uuid.equals(this.owner);
        }
        else
        {
            this.owner = uuid;
            this.markDirty();
            return true;
        }
    }

    // UNIQUE: Gets the owner.
    public UUID getChestOwner()
    {
        return this.owner;
    }

    // UNIQUE: Sets the owner.
    public void setChestOwner(UUID uuid)
    {
        this.owner = uuid;
        this.markDirty();
    }

    // UNIQUE: Adds a provided user to the allowed list, if possible.
    public void addToAllowedList(UUID uuid)
    {
        if (!this.allowed_users.contains(uuid))
        {
            this.allowed_users.add(uuid);
            this.markDirty();
        }
    }

    // UNIQUE: Checks if a provided UUID is on the allowed list.
    public boolean isUUIDOnAllowedList(UUID uuid)
    {
        return (!this.allowed_users.isEmpty() && this.allowed_users.contains(uuid));
    }

    // UNIQUE: Checks if a provided UUID can be added to the allowed list.
    // Will return true if the user isn't on the list and isn't the owner.
    public boolean canAddToAllowedList(UUID uuid)
    {
        if (!this.allowed_users.isEmpty() && !this.owner.equals(uuid))
        {
            return (!this.allowed_users.contains(uuid));
        }
        return (!this.owner.equals(uuid));
    }

    // Cooldown related
    public void setCooldown(int time) { this.cooldown_time = time; }
    public int getCooldown() { return this.cooldown_time; }
}
