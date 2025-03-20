package net.artyrian.frontiers.block.entity.entity_model;

import net.artyrian.frontiers.block.entity.ModBlockEntities;
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

public class CreeperModelBlockEntity extends BlockEntity
{
    public CreeperModelBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CREEPER_MODEL_BLOCKENTITY, pos, state);
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

    public static void tick(World world, BlockPos pos, BlockState state, CreeperModelBlockEntity blockEntity)
    {

    }
}
