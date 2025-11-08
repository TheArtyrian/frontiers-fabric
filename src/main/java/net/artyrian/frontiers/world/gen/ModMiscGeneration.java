package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;

import java.util.function.Predicate;

public class ModMiscGeneration
{
    private static final Predicate<BiomeSelectionContext> QUICKSAND_GENKEY = BiomeSelectors.includeByKey(
            BiomeKeys.JUNGLE,
            BiomeKeys.SPARSE_JUNGLE,
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP,
            //BiomeKeys.BEACH,          Artyrian note: after further testing this was just straight up evil
            BiomeKeys.RIVER
    );

    public static void generateMisc()
    {
        BiomeModifications.addFeature(QUICKSAND_GENKEY,
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModPlacedFeatures.QUICKSAND_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.all(),
                GenerationStep.Feature.UNDERGROUND_STRUCTURES, ModPlacedFeatures.SLIME_TRAIL_PLACED_KEY);

        BiomeModifications.addFeature(context -> context.canGenerateIn(ModDimension.CRAGS_KEY),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.EBONCORK_SPIKE_PLACED_KEY);
    }
}
