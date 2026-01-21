package net.artyrian.frontiers.definition.item.custom.tool;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/** An interface for unbreakable tools. */
public interface Unbreakable
{
    @Nullable
    public Item getBrokenItem();

    /** Determines if a provided ItemStack is useable based on its damage. */
    public static boolean isUsable(ItemStack stack)
    {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }
}
