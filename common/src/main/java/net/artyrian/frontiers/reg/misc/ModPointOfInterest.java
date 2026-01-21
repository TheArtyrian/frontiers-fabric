package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.Set;
import java.util.function.Supplier;

public class ModPointOfInterest
{
    public static final Supplier<PoiType> CRAGS_PORTAL = VectorLib.REGISTRY.registerPoiType(
            Frontiers.MOD_ID,
            "crags_portal",
            Set.of(ModBlocks.CRAGS_PORTAL.get().defaultBlockState()),
            0,
            1
    );

    public static void registerPOIs()
    {

    }
}
