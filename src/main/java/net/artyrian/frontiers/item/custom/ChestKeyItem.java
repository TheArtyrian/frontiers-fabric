package net.artyrian.frontiers.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChestKeyItem extends Item
{
    public ChestKeyItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return stack.contains(DataComponentTypes.PROFILE) || super.hasGlint(stack);
    }
}
