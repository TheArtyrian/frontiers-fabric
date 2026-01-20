package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ExtendedFlowerBlock extends FlowerBlock implements BonemealableBlock
{
    private final Block BLOCK_OF;

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(BLOCK_OF);
    }

    public ExtendedFlowerBlock(Block blocktype, Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
        this.BLOCK_OF = blocktype;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state)
    {
        BlockPos below = pos.below(1);
        boolean growable = world.getBlockState(below).is(BLOCK_OF);
        return Frontiers.LEGACY4J_LOADED && growable;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state)
    {
        BlockPos below = pos.below(1);
        boolean growable = world.getBlockState(below).is(BLOCK_OF);
        return Frontiers.LEGACY4J_LOADED && growable;
    }

    @Override
    // Based on Legacy4J's inject code - go check them out! Please don't sue me :')
    // https://github.com/Wilyicaro/Legacy-Minecraft/blob/1.21/common/src/main/java/wily/legacy/mixin/FlowerBlockMixin.java
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state)
    {
        for(int xd = -3; xd <= 3; ++xd)
        {
            for(int zd = -3; zd <= 3; ++zd)
            {
                if (zd == 0 && xd == 0) continue;
                for(int yd = -1; yd <= 1; ++yd)
                {
                    BlockPos blockPos2 = pos.offset(xd, yd, zd);
                    if (world.getBlockState(blockPos2).isAir() && random.nextInt((int)Math.pow(2,Math.abs(xd) + Math.abs(zd) + Math.abs(yd))) == 0)
                    {
                        BlockState blockState2 = world.getBlockState(blockPos2.below());
                        if (blockState2.is(BLOCK_OF))
                        {
                            world.setBlock(blockPos2, this.defaultBlockState(), 3);
                            break;
                        }
                    }
                }
            }
        }
    }
}
