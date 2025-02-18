package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SpecialPlaceableFlowerBlock extends FlowerBlock implements Fertilizable
{
    private final Block BLOCK_OF;

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(BLOCK_OF);
    }

    public SpecialPlaceableFlowerBlock(Block blocktype, RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
        this.BLOCK_OF = blocktype;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state)
    {
        BlockPos below = pos.down(1);
        boolean growable = world.getBlockState(below).isOf(BLOCK_OF);
        return Frontiers.LEGACY4J_LOADED && growable;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state)
    {
        BlockPos below = pos.down(1);
        boolean growable = world.getBlockState(below).isOf(BLOCK_OF);
        return Frontiers.LEGACY4J_LOADED && growable;
    }

    @Override
    // Based on Legacy4J's inject code - go check them out! Please don't sue me :')
    // https://github.com/Wilyicaro/Legacy-Minecraft/blob/1.21/common/src/main/java/wily/legacy/mixin/FlowerBlockMixin.java
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
    {
        for(int xd = -3; xd <= 3; ++xd) {
            for(int zd = -3; zd <= 3; ++zd) {
                if (zd == 0 && xd == 0) continue;
                for(int yd = -1; yd <= 1; ++yd) {
                    BlockPos blockPos2 = pos.add(xd, yd, zd);
                    if (world.getBlockState(blockPos2).isAir() && random.nextInt((int)Math.pow(2,Math.abs(xd) + Math.abs(zd) + Math.abs(yd))) == 0) {
                        BlockState blockState2 = world.getBlockState(blockPos2.down());
                        if (blockState2.isOf(BLOCK_OF)) {
                            world.setBlockState(blockPos2, this.getDefaultState(), 3);
                            break;
                        }
                    }
                }
            }
        }
    }
}
