package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.network.chat.Component;
import net.vertisoft.vectorlib.VectorLib;

public class FRDatapack
{
    public static final String FD_PACK = "farmersdelight_frnt";
    public static final String BF_PACK = "bountifulfares_frnt";
    public static final String DD_PACK = "delicatedyes_frnt";

    public static void bootstrap()
    {
        VectorLib.REGISTRY.registerResourcePackConditionally(
                Frontiers.FARMERS_DELIGHT_LOADED,
                Frontiers.MOD_ID,
                FD_PACK,
                Component.literal("Frontiers: Farmer's Delight"),
                true,
                true
        );
        VectorLib.REGISTRY.registerResourcePackConditionally(
                Frontiers.BOUNTIFUL_FARES_LOADED,
                Frontiers.MOD_ID,
                BF_PACK,
                Component.literal("Frontiers: Bountiful Fares"),
                true,
                true
        );
        VectorLib.REGISTRY.registerResourcePackConditionally(
                Frontiers.DELICATE_DYES_LOADED,
                Frontiers.MOD_ID,
                DD_PACK,
                Component.literal("Frontiers: Delicate Dyes"),
                true,
                true
        );
    }
}
