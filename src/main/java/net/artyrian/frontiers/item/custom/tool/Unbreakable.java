package net.artyrian.frontiers.item.custom.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

/** An interface for unbreakable tools. */
public interface Unbreakable
{
    @Nullable
    public Item getBrokenItem();

    /** Determines if a provided ItemStack is useable based on its damage. */
    public static boolean isUsable(ItemStack stack)
    {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }
}
