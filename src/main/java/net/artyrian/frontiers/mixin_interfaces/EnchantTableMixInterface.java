package net.artyrian.frontiers.mixin_interfaces;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface EnchantTableMixInterface
{
    static void frontiers$frontiersServerTableTick(World world, BlockPos pos, BlockState state, EnchantingTableBlockEntity blockEntity)
    {
        ((EnchantTableMixInterface)blockEntity).frontiers$attemptPasseCheckForCrystals(world, pos, state);
    }

    void frontiers$attemptPasseCheckForCrystals(World world, BlockPos pos, BlockState state);
    int frontiers$getCrystalCount();
}
