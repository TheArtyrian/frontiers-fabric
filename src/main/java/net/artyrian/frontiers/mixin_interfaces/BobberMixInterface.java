package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface BobberMixInterface
{
    int getBobberLevel();
    ItemStack getParentItemStack();
    Item getParentItem();
    int getLineColor();
    void setBobberLevel(BobberType bobber);
    void setParentItemStack(ItemStack stack);
    void setParentItem(Item item);
    void setLineColor(BobberType bobber);
}
