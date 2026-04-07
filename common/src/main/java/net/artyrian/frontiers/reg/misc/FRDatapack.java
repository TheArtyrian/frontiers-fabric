package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.network.chat.Component;
import net.vertisoft.vectorlib.VectorLib;

public class FRDatapack
{
    public static void bootstrap()
    {
        VectorLib.REGISTRY.registerResourcePack(Frontiers.MOD_ID, "farmersdelight_frnt", Component.literal("Frontiers x Farmer's Delight"), true, true);
        VectorLib.REGISTRY.registerResourcePack(Frontiers.MOD_ID, "bountifulfares_frnt", Component.literal("Frontiers x Bountiful Fares"), true, true);
    }
}
