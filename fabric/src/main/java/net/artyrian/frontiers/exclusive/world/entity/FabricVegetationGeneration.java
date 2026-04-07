package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.world.FRFeaturesPlaced;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricVegetationGeneration
{
    public static void generateVeg()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_FUNGAL_DAFFODIL),
                GenerationStep.Decoration.VEGETAL_DECORATION, FRFeaturesPlaced.FUNGAL_DAFFODIL_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_SNOW_DAHLIA),
                GenerationStep.Decoration.VEGETAL_DECORATION, FRFeaturesPlaced.SNOW_DAHLIA_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_CRIMCONE),
                GenerationStep.Decoration.VEGETAL_DECORATION, FRFeaturesPlaced.CRIMCONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_EXPERIWINKLE),
                GenerationStep.Decoration.VEGETAL_DECORATION, FRFeaturesPlaced.EXPERIWINKLE_PLACED_KEY);
    }
}
