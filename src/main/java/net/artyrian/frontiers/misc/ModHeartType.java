package net.artyrian.frontiers.misc;

import net.minecraft.client.gui.hud.InGameHud;

// Loads heart types
public class ModHeartType
{
    static
    {
        InGameHud.HeartType.values();        // Ensure class is loaded.
    }

    // Vanilla values start:
    //                   CONTAINER
    //                   NORMAL
    //                   POISON
    //                   WITHERED
    //                   ABSORBING
    //                   FROZEN
    public static InGameHud.HeartType FRONTIERS_PINK;
    public static InGameHud.HeartType FRONTIERS_PURPLE;
    public static InGameHud.HeartType FRONTIERS_ONFIRE;
    public static InGameHud.HeartType FRONTIERS_STORM;
    public static InGameHud.HeartType FRONTIERS_CONTAINER_STORM;
    //public static InGameHud.HeartType FRONTIERS_TYPE3;
}
