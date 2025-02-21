package net.artyrian.frontiers.misc;

import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.util.Rarity;

public class ModAdvancementFrame
{
    static
    {
        AdvancementFrame.values();        // Ensure class is loaded.
    }

    // Vanilla values start:
    //                   TASK
    //                   CHALLENGE
    //                   GOAL
    public static AdvancementFrame FRONTIERS_ADV;
}
