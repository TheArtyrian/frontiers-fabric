package net.artyrian.frontiers.exclusive.poi;

import com.google.common.collect.ImmutableSet;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.world.FRPointOfInterest;
import net.vertisoft.vectorlib.VectorLib;

public class PoiFabric
{
    public static void register()
    {
        FRPointOfInterest.CRAGS_PORTAL = VectorLib.REGISTRY.registerPoiType(
                Frontiers.MOD_ID,
                "crags_portal",
                ImmutableSet.of(FRBlocks.CRAGS_PORTAL.get().defaultBlockState()),
                0,
                1
        );
    }
}
