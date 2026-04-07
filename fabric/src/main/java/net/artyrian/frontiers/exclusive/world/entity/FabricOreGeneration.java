package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.world.FRFeaturesPlaced;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricOreGeneration
{
    public static void generateOres()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_HIELOSTONE),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.HIELOSTONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_HIELOSTONE),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.HIELOSTONE_ICE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.VERDINITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.VIVULITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_BLACK_EMERALD),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.BLACK_EMERALD_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_BRIMTAN),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.BRIMTAN_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_FROSTITE),
                GenerationStep.Decoration.UNDERGROUND_ORES, FRFeaturesPlaced.FROSTITE_ORE_PLACED_KEY);
    }
}
