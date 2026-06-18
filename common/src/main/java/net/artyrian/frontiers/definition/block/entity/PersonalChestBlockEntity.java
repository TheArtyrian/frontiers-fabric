package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonalChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity
{
    private int cooldown_time = 0;
    private UUID owner = null;
    private List<UUID> allowed_users = new ArrayList<>();
    private NonNullList<ItemStack> inventory = NonNullList.withSize(27, ItemStack.EMPTY);

    private final ContainerOpenersCounter stateManager = new ContainerOpenersCounter()
    {
        @Override
        protected void onOpen(Level world, BlockPos pos, BlockState state)
        {
            PersonalChestBlockEntity.playSound(world, pos, state, FRSounds.PERSONAL_CHEST_OPEN.get());
        }

        @Override
        protected void onClose(Level world, BlockPos pos, BlockState state)
        {
            PersonalChestBlockEntity.playSound(world, pos, state, FRSounds.PERSONAL_CHEST_CLOSE.get());
        }

        @Override
        protected void openerCountChanged(Level world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount)
        {
            PersonalChestBlockEntity.this.onViewerCountUpdate(world, pos, state, oldViewerCount, newViewerCount);
        }

        @Override
        protected boolean isOwnContainer(Player player)
        {
            if (!(player.containerMenu instanceof ChestMenu))
            {
                return false;
            }
            else
            {
                Container inventory = ((ChestMenu)player.containerMenu).getContainer();
                return inventory == PersonalChestBlockEntity.this;
            }
        }
    };
    private final ChestLidController lidAnimator = new ChestLidController();

    public PersonalChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) { super(type, pos, state); }
    public PersonalChestBlockEntity(BlockPos pos, BlockState state) { this(FRBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get(), pos, state); }

    @Override
    public int getContainerSize() {
        return 27;
    }
    @Override
    protected Component getDefaultName() { return Component.translatable("container.frontiers.personal_chest"); }
    @Override
    public boolean canTakeItem(Container hopperInventory, int slot, ItemStack stack) { return false; }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(nbt))
        {
            ContainerHelper.loadAllItems(nbt, this.inventory, registryLookup);
        }
        if (nbt.hasUUID("Owner"))
        {
            UUID testuuid = nbt.getUUID("Owner");
            if (testuuid != null) this.owner = testuuid;
        }

        if (nbt.contains("AllowedUsers", Tag.TAG_LIST))
        {
            ListTag nbtList = (ListTag)nbt.get("AllowedUsers");

            if (nbtList != null && !nbtList.isEmpty())
            {
                for (int i = 0; i < nbtList.size(); i++)
                {
                    this.allowed_users.add(NbtUtils.loadUUID(nbtList.get(i)));
                }
            }
        }

        if (nbt.contains("CooldownTime", Tag.TAG_INT)) this.cooldown_time = nbt.getInt("CooldownTime");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        if (!this.trySaveLootTable(nbt))
        {
            ContainerHelper.saveAllItems(nbt, this.inventory, registryLookup);
        }
        if (this.owner != null) {
            nbt.putUUID("Owner", this.owner);
        }

        if (!this.allowed_users.isEmpty())
        {
            ListTag allowedlist = new ListTag();

            for (UUID allowedUser : this.allowed_users)
            {
                allowedlist.add(NbtUtils.createUUID(allowedUser));
            }

            if (!allowedlist.isEmpty()) { nbt.put("AllowedUsers", allowedlist); }
        }

        nbt.putInt("CooldownTime", this.cooldown_time);
    }

    public static void clientTick(Level world, BlockPos pos, BlockState state, PersonalChestBlockEntity blockEntity)
    {
        blockEntity.lidAnimator.tickLid();
        if (blockEntity.cooldown_time > 0) blockEntity.cooldown_time--;
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, PersonalChestBlockEntity blockEntity)
    {
        if (blockEntity.cooldown_time > 0) blockEntity.cooldown_time--;
    }

    static void playSound(Level world, BlockPos pos, BlockState state, SoundEvent soundEvent)
    {
        double d = (double)pos.getX() + 0.5;
        double e = (double)pos.getY() + 0.5;
        double f = (double)pos.getZ() + 0.5;

        world.playSound(null, d, e, f, soundEvent, SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public boolean triggerEvent(int type, int data) {
        if (type == 1) {
            this.lidAnimator.shouldBeOpen(data > 0);
            return true;
        } else {
            return super.triggerEvent(type, data);
        }
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.stateManager.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.stateManager.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }
    @Override
    protected void setItems(NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }
    @Override
    public float getOpenNess(float tickDelta) { return this.lidAnimator.getOpenness(tickDelta); }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) { return saveWithoutMetadata(registryLookup); }

    public static int getPlayersLookingInChestCount(BlockGetter world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.hasBlockEntity()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PersonalChestBlockEntity) {
                return ((PersonalChestBlockEntity)blockEntity).stateManager.getOpenerCount();
            }
        }

        return 0;
    }

    public static void copyInFull(PersonalChestBlockEntity from, PersonalChestBlockEntity to)
    {
        NonNullList<ItemStack> cache = from.getItems();

        from.setItems(to.getItems());

        to.setItems(cache);
        to.setChestOwner(from.getChestOwner());
        to.allowed_users = from.allowed_users;
    }

    @Override @NotNull
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory)
    {
        return ChestMenu.threeRows(syncId, playerInventory, this);
    }

    public void onScheduledTick()
    {
        if (!this.remove)
        {
            this.stateManager.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void onViewerCountUpdate(Level world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount)
    {
        Block block = state.getBlock();
        world.blockEvent(pos, block, 1, newViewerCount);
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
            this.setChanged();
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
        this.setChanged();
    }

    // UNIQUE: Adds a provided user to the allowed list, if possible.
    public void addToAllowedList(UUID uuid)
    {
        if (!this.allowed_users.contains(uuid))
        {
            this.allowed_users.add(uuid);
            this.setChanged();
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

    // Cooldowns
    public void setCooldown(int time)
    {
        this.cooldown_time = time;
        this.setChanged();
    }
    public int getCooldown() { return this.cooldown_time; }
}
