package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TowerSpawnerBlockEntity extends BlockEntity
{
    private boolean enraged;
    private double rotation;
    private double lastRotation;
    private int minSpawnDelayNormal = 400;
    private int maxSpawnDelayNormal = 800;
    private int minSpawnDelayEnraged = 200;
    private int maxSpawnDelayEnraged = 400;

    public TowerSpawnerBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TOWER_SPAWNER.get(), pos, state);
    }

    @Nullable @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) { return saveWithoutMetadata(registryLookup); }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
    }

    public static void tickClient(Level world, BlockPos pos, BlockState state, TowerSpawnerBlockEntity blockEntity)
    {

    }

    public static void tickServer(Level world, BlockPos pos, BlockState state, TowerSpawnerBlockEntity blockEntity)
    {

    }
}
