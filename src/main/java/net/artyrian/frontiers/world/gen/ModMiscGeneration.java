package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.function.Predicate;

public class ModMiscGeneration
{
    private static final Predicate<BiomeSelectionContext> QUICKSAND_GENKEY = BiomeSelectors.includeByKey(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP,
            BiomeKeys.BEACH,
            BiomeKeys.RIVER
    );

    public static void generateMisc()
    {
        BiomeModifications.addFeature(QUICKSAND_GENKEY,
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.QUICKSAND_PLACED_KEY);
    }
}
