package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class RoseFlowerBlock extends FlowerBlock implements BonemealableBlock
{
    private final Supplier<Block> BLOCK_TO_GROW;

    public RoseFlowerBlock(Supplier<Block> block_to_place, Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
        this.BLOCK_TO_GROW = block_to_place;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state)
    {
        boolean grow_rose = false;
        boolean made_a_rose = false;
        Block what_to_look_for = defaultBlockState().is(FRBlocks.ROSE.get()) ? FRBlocks.ANCIENT_ROSE.get() : FRBlocks.ROSE.get();
        BlockPos memorypoint = pos;

        // Check for violet rose first.
        for (int _x1 = -3; _x1 <= 3; _x1++)
        {
            for (int _z1 = -3; _z1 <= 3; _z1++)
            {
                if (_x1 == 0 && _z1 == 0) continue;
                for (int _y1 = -1; _y1 <=1; ++_y1)
                {
                    BlockPos blockPos2 = pos.offset(_x1, _y1, _z1);
                    boolean is_grass = world.getBlockState(blockPos2.below()).is(Blocks.GRASS_BLOCK);
                    if (world.getBlockState(blockPos2).is(what_to_look_for))
                    {
                        grow_rose = true;
                        memorypoint = blockPos2;
                    }

                    if (world.getBlockState(blockPos2).isAir() && random.nextInt((int)Math.pow(2,Math.abs(_x1) + Math.abs(_z1) + Math.abs(_y1))) == 0 && !made_a_rose)
                    {
                        if (grow_rose)
                        {
                            if (is_grass)
                            {
                                world.sendParticles(
                                        ParticleTypes.HAPPY_VILLAGER,
                                        memorypoint.getX() + 0.5,
                                        memorypoint.getY() + 0.5,
                                        memorypoint.getZ() + 0.5,
                                        12,
                                        0.25,
                                        0.25,
                                        0.25,
                                        0.2
                                        );
                                world.setBlock(blockPos2, this.BLOCK_TO_GROW.get().defaultBlockState(), 3);
                                made_a_rose = true;
                                break;
                            }
                        }
                        else if (Frontiers.LEGACY4J_LOADED && is_grass)
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
