package net.artyrian.frontiers.mixin_intf;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface EnchTableIntf
{
    static void frontiers$frontiersServerTableTick(Level world, BlockPos pos, BlockState state, EnchantingTableBlockEntity blockEntity)
    {
        ((EnchTableIntf)blockEntity).frontiers$attemptPasseCheckForCrystals(world, pos, state);
    }

    void frontiers$attemptPasseCheckForCrystals(Level world, BlockPos pos, BlockState state);
    int frontiers$getCrystalCount();
}
