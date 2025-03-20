package net.artyrian.frontiers.block.entity.entity_model;

import net.artyrian.frontiers.block.custom.models.BlazeModelBlock;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazeModelBlockEntity extends BlockEntity
{
    private float age = 0.0F;

    public BlazeModelBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY, pos, state);
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

    public static void tick(World world, BlockPos pos, BlockState state, BlazeModelBlockEntity blockEntity)
    {
        boolean power = state.get(BlazeModelBlock.MODEL_POWERED);
        if (power)
        {
            Random random = world.getRandom();

            blockEntity.age += 1.0F;
            for (int i = 0; i < 2; i++)
            {
                world.addParticle(ParticleTypes.LARGE_SMOKE,
                        pos.getX() + 0.5 + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        pos.getY() + 0.1,
                        pos.getZ() + 0.5 + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        0.0, 0.0, 0.0);
            }
        }
    }

    public float getAge() { return this.age; }
}
