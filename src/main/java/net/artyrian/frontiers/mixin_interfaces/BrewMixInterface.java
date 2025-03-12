package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BrewMixInterface
{
    void frontiers_1_21x$craftLightning(World world, BlockPos pos, DefaultedList<ItemStack> slots);
}