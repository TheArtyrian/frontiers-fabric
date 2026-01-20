package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NecroWeaveBlock extends Block
{
    public static final MapCodec<NecroWeaveBlock> CODEC = simpleCodec(NecroWeaveBlock::new);
    @Override public MapCodec<NecroWeaveBlock> codec() {
        return CODEC;
    }

    public NecroWeaveBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance)
    {
        entity.causeFallDamage(fallDistance, 0.05F, world.damageSources().fall());
    }
}
