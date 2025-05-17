package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModVegetationGeneration
{
    public static void generateVeg()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        BiomeKeys.MUSHROOM_FIELDS
                ),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.FUNGAL_DAFFODIL_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        BiomeKeys.FROZEN_RIVER
                        ),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SNOW_DAHLIA_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        BiomeKeys.CRIMSON_FOREST
                ),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.CRIMCONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        BiomeKeys.FLOWER_FOREST,
                        BiomeKeys.MEADOW
                ),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.EXPERIWINKLE_PLACED_KEY);
    }
}
