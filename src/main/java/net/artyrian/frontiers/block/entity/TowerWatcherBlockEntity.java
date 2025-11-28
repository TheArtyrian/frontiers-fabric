package net.artyrian.frontiers.block.entity;

import net.artyrian.frontiers.block.entity.entity_model.BoggedModelBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TowerWatcherBlockEntity extends BlockEntity
{
    public TowerWatcherBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.TOWER_WATCHER, pos, state);
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

    public static void tickClient(World world, BlockPos pos, BlockState state, TowerWatcherBlockEntity blockEntity)
    {

    }

    public static void tickServer(World world, BlockPos pos, BlockState state, TowerWatcherBlockEntity blockEntity)
    {

    }
}
