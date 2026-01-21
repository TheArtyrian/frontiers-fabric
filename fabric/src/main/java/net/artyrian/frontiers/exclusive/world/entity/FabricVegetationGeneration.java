package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricVegetationGeneration
{
    public static void generateVeg()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        Biomes.MUSHROOM_FIELDS
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FUNGAL_DAFFODIL_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        Biomes.FROZEN_RIVER
                        ),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SNOW_DAHLIA_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        Biomes.CRIMSON_FOREST
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRIMCONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                        Biomes.FLOWER_FOREST,
                        Biomes.MEADOW
                ),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.EXPERIWINKLE_PLACED_KEY);
    }
}
