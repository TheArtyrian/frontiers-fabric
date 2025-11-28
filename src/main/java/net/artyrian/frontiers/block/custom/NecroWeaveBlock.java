package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NecroWeaveBlock extends Block
{
    public static final MapCodec<NecroWeaveBlock> CODEC = createCodec(NecroWeaveBlock::new);
    @Override public MapCodec<NecroWeaveBlock> getCodec() {
        return CODEC;
    }

    public NecroWeaveBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance)
    {
        entity.handleFallDamage(fallDistance, 0.05F, world.getDamageSources().fall());
    }
}
