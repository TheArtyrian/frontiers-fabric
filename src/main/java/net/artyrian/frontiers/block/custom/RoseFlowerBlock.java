package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class RoseFlowerBlock extends FlowerBlock implements Fertilizable
{
    private final Block BLOCK_TO_GROW;

    public RoseFlowerBlock(Block block_to_place, RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
        this.BLOCK_TO_GROW = block_to_place;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state)
    {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
    {
        boolean grow_rose = false;
        boolean made_a_rose = false;
        Block what_to_look_for = getDefaultState().isOf(ModBlocks.ROSE) ? ModBlocks.ANCIENT_ROSE : ModBlocks.ROSE;
        BlockPos memorypoint = pos;

        // Check for violet rose first.
        for (int _x1 = -3; _x1 <= 3; _x1++)
        {
            for (int _z1 = -3; _z1 <= 3; _z1++)
            {
                if (_x1 == 0 && _z1 == 0) continue;
                for (int _y1 = -1; _y1 <=1; ++_y1)
                {
                    BlockPos blockPos2 = pos.add(_x1, _y1, _z1);
                    boolean is_grass = world.getBlockState(blockPos2.down()).isOf(Blocks.GRASS_BLOCK);
                    if (world.getBlockState(blockPos2).isOf(what_to_look_for))
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
                                world.spawnParticles(
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
                                world.setBlockState(blockPos2, ModBlocks.VIOLET_ROSE.getDefaultState(), 3);
                                made_a_rose = true;
                                break;
                            }
                        }
                        else if (Frontiers.LEGACY4J_LOADED && is_grass)
                        {
                            world.setBlockState(blockPos2, this.getDefaultState(), 3);
                            break;
                        }
                    }
                }
            }
        }
    }
}
