package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricOreGeneration
{
    public static void generateOres()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_HIELOSTONE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_HIELOSTONE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_ICE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.VERDINITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.VIVULITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_BLACK_EMERALD),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_EMERALD_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_BRIMTAN),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRIMTAN_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_FROSTITE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY);
    }
}
