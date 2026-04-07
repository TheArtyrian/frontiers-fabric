package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TowerWatcherBlockEntity extends BlockEntity
{
    public TowerWatcherBlockEntity(BlockPos pos, BlockState state)
    {
        super(FRBlockEntities.TOWER_WATCHER.get(), pos, state);
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

    public static void tickClient(Level world, BlockPos pos, BlockState state, TowerWatcherBlockEntity blockEntity)
    {

    }

    public static void tickServer(Level world, BlockPos pos, BlockState state, TowerWatcherBlockEntity blockEntity)
    {

    }
}
