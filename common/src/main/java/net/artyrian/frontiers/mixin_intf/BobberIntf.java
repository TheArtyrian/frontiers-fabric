package net.artyrian.frontiers.mixin_intf;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface BobberIntf
{
    BobberType frontiers_1_21x$getBobberLevel();
    ItemStack frontiers_1_21x$getParentItemStack();
    int frontiers_1_21x$getLineColor();
    void frontiers_1_21x$setBobberLevel(BobberType bobber);
    void frontiers_1_21x$setParentItemStack(ItemStack stack);
}