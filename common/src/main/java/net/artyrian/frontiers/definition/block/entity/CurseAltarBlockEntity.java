package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarMenu;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Nameable;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CurseAltarBlockEntity extends BaseContainerBlockEntity implements Nameable, WorldlyContainer
{
    private static final int FUEL_SLOT = 0;
    private static final int ITEM_SLOT = 1;
    private static final int[] SLOTS_FOR_UP = new int[]{FUEL_SLOT};

    private NonNullList<ItemStack> items;
    protected final ContainerData container;

    public int ticks;
    public float sigilRotation;
    public float lastSigilRotation;
    public float tabletRotation;
    public float lastTabletRotation;
    public float targetTabletRotation;
    public float tabletGlow = 0.0F;

    private int charges = 0;
    @Nullable private Component customName;

    public CurseAltarBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), pos, state);
        this.items = NonNullList.withSize(1, ItemStack.EMPTY);
        this.container = new ContainerData()
        {
            @Override public int get(int i) { return (i == 0) ? CurseAltarBlockEntity.this.charges : 0; }
            @Override public void set(int i, int i1) { if (i == 0) CurseAltarBlockEntity.this.charges = i1; }
            @Override public int getCount() { return 1; }
        };
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup)
    {
        return this.saveCustomOnly(registryLookup);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        ContainerHelper.saveAllItems(nbt, this.items, registryLookup);
        if (this.hasCustomName()) nbt.putString("CustomName", Component.Serializer.toJson(this.customName, registryLookup));
        nbt.putByte("Charges", (byte)this.charges);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        ContainerHelper.loadAllItems(nbt, this.items, registryLookup);
        if (nbt.contains("CustomName", Tag.TAG_STRING)) this.customName = parseCustomNameSafe(nbt.getString("CustomName"), registryLookup);
        if (nbt.contains("Charges", Tag.TAG_BYTE)) this.charges = nbt.getByte("Charges");
    }

    public static void clientTick(Level world, BlockPos pos, BlockState state, CurseAltarBlockEntity blockEntity)
    {
        blockEntity.lastTabletRotation = blockEntity.tabletRotation;
        blockEntity.lastSigilRotation = (blockEntity.sigilRotation % 360.0F);
        Player playerEntity = world.getNearestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 3.0, false);
        if (playerEntity != null)
        {
            double d = playerEntity.getX() - ((double)pos.getX() + 0.5);
            double e = playerEntity.getZ() - ((double)pos.getZ() + 0.5);
            blockEntity.targetTabletRotation = (float) Mth.atan2(e, d);

            if (blockEntity.tabletGlow + 0.2F < 1.0F) { blockEntity.tabletGlow += 0.2F; }
            else { blockEntity.tabletGlow = 1.0F; }
        }
        else
        {
            blockEntity.targetTabletRotation += 0.02F;

            if (blockEntity.tabletGlow - 0.2F > 0.0F) { blockEntity.tabletGlow -= 0.2F; }
            else { blockEntity.tabletGlow = 0.0F; }
        }

        while (blockEntity.tabletRotation >= (float) Math.PI) { blockEntity.tabletRotation -= (float) (Math.PI * 2); }
        while (blockEntity.tabletRotation < (float) -Math.PI) { blockEntity.tabletRotation += (float) (Math.PI * 2); }

        while (blockEntity.targetTabletRotation >= (float) Math.PI) { blockEntity.targetTabletRotation -= (float) (Math.PI * 2); }
        while (blockEntity.targetTabletRotation < (float) -Math.PI) { blockEntity.targetTabletRotation += (float) (Math.PI * 2); }

        float g = blockEntity.targetTabletRotation - blockEntity.tabletRotation;

        while (g >= (float) Math.PI) { g -= (float) (Math.PI * 2); }
        while (g < (float) -Math.PI) { g += (float) (Math.PI * 2); }

        blockEntity.tabletRotation += g * 0.4F;
        blockEntity.sigilRotation = (blockEntity.sigilRotation % 360.0F) + 4.0F;
        blockEntity.ticks++;
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, CurseAltarBlockEntity blockEntity)
    {
        ItemStack itemstack = blockEntity.items.get(0);
        if (blockEntity.charges <= 0 && itemstack.is(ModItem.CURSED_TABLET.get()))
        {
            itemstack.shrink(1);
            blockEntity.updateTablet(world, pos, state);
        }
    }

    public void updateTablet(Level level, BlockPos pos, BlockState state)
    {
        this.charges = 20;
        this.setChanged();
        level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL_IMMEDIATE);
        VectorEventSync.Local.fireEvent(level, pos, FRLevelEvents.Local.CURSED_TABLET, 0);
    }

    //////////////////////////////////

    @Override public Component getName() { return (this.customName != null ? this.customName : this.getDefaultName()); }
    public int getCharges() { return this.charges; }

    public void setCustomName(@Nullable Component customName) { this.customName = customName; }
    @Nullable @Override public Component getCustomName() { return this.customName; }
    @Override @NotNull protected Component getDefaultName() { return Component.translatable("container.frontiers.curse_altar"); }

    @Override
    protected void applyImplicitComponents(DataComponentInput components)
    {
        super.applyImplicitComponents(components);
        this.customName = components.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder)
    {
        super.collectImplicitComponents(componentMapBuilder);
        componentMapBuilder.set(DataComponents.CUSTOM_NAME, this.customName);
    }

    @Override protected AbstractContainerMenu createMenu(int id, Inventory player) { return new CurseAltarMenu(id, player, this, this.container, ContainerLevelAccess.create(this.level, this.getBlockPos())); }

    //////////////////////////////////

    @Override
    public int[] getSlotsForFace(Direction direction)
    {
        if (direction == Direction.DOWN || direction == Direction.UP) return new int[0];
        return SLOTS_FOR_UP;
    }

    public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack itemStack, @Nullable Direction direction)
    {
        if (direction == Direction.DOWN || direction == Direction.UP) return false;
        return this.canPlaceItem(index, itemStack);
    }

    @Override public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) { return false; }
    @Override protected NonNullList<ItemStack> getItems() { return this.items; }
    @Override protected void setItems(NonNullList<ItemStack> list) { this.items = list; }
    @Override public int getContainerSize() { return this.items.size(); }
}
