package net.artyrian.frontiers.mixin_intf;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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