package net.artyrian.frontiers.misc;

import net.minecraft.util.Rarity;

// Loads rarities
public class ModRarity
{
    static
    {
        Rarity.values();        // Ensure class is loaded.
    }

    // Vanilla values start:
    //                   COMMON
    //                   UNCOMMON
    //                   RARE
    //                   EPIC
    public static Rarity FRONTIERS_MYTHICAL;
    public static Rarity FRONTIERS_LEGENDARY;
    public static Rarity FRONTIERS_UNREAL;
}
