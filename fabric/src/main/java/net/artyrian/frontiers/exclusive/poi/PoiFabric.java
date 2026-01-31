package net.artyrian.frontiers.exclusive.poi;

import com.google.common.collect.ImmutableSet;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModPointOfInterest;
import net.vertisoft.vectorlib.VectorLib;

public class PoiFabric
{
    public static void register()
    {
        ModPointOfInterest.CRAGS_PORTAL = VectorLib.REGISTRY.registerPoiType(
                Frontiers.MOD_ID,
                "crags_portal",
                ImmutableSet.of(ModBlocks.CRAGS_PORTAL.get().defaultBlockState()),
                0,
                1
        );
    }
}
