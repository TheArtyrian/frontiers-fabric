package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.world.FRFeaturesPlaced;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FabricMiscGeneration
{
    public static void generateMisc()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_QUICKSAND),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION, FRFeaturesPlaced.QUICKSAND_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES, FRFeaturesPlaced.SLIME_TRAIL_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.tag(FRTags.Biomes.GENERATES_EBONCORK),
                GenerationStep.Decoration.VEGETAL_DECORATION, FRFeaturesPlaced.EBONCORK_SPIKE_PLACED_KEY);
    }
}
