package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface BobberMixInterface
{
    int frontiers_1_21x$getBobberLevel();
    ItemStack frontiers_1_21x$getParentItemStack();
    Item frontiers_1_21x$getParentItem();
    int frontiers_1_21x$getLineColor();
    void frontiers_1_21x$setBobberLevel(BobberType bobber);
    void frontiers_1_21x$setParentItemStack(ItemStack stack);
    void frontiers_1_21x$setParentItem(Item item);
    void frontiers_1_21x$setLineColor(BobberType bobber);
}