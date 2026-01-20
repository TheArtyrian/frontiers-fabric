package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.definition.block.custom.BlazeModelBlock;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlazeModelBlockEntity extends BlockEntity
{
    private float age = 0.0F;

    public BlazeModelBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), pos, state);
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

    public static void tick(Level world, BlockPos pos, BlockState state, BlazeModelBlockEntity blockEntity)
    {
        boolean power = state.getValue(BlazeModelBlock.MODEL_POWERED);
        if (power)
        {
            RandomSource random = world.getRandom();

            blockEntity.age += 1.0F;
            for (int i = 0; i < 2; i++)
            {
                world.addParticle(ParticleTypes.LARGE_SMOKE,
                        pos.getX() + 0.5 + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        pos.getY() + (0.1 * (1 + (random.nextInt(17)))),
                        pos.getZ() + 0.5 + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        0.0, 0.0, 0.0);
            }
        }
    }

    public float getAge() { return this.age; }
}
