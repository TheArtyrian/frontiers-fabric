package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import java.util.function.Predicate;

public class FabricMiscGeneration
{
    public static void generateMisc()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.GENERATES_QUICKSAND),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModPlacedFeatures.QUICKSAND_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.all(),
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES, ModPlacedFeatures.SLIME_TRAIL_PLACED_KEY);

        BiomeModifications.addFeature(context -> context.canGenerateIn(ModDimension.CRAGS_KEY),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.EBONCORK_SPIKE_PLACED_KEY);
    }
}
