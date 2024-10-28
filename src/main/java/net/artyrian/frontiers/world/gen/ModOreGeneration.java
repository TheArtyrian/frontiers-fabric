package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModOreGeneration
{
    public static void generateOres()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.JUNGLE,
                    BiomeKeys.BAMBOO_JUNGLE,
                    BiomeKeys.SPARSE_JUNGLE
                ),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.ICE_SPIKES),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY);
    }
}
