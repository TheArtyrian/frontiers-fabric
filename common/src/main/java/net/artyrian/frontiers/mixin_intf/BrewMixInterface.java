package net.artyrian.frontiers.mixin_intf;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface BrewMixInterface
{
    void frontiers_1_21x$craftLightning(Level world, BlockPos pos, NonNullList<ItemStack> slots);
}