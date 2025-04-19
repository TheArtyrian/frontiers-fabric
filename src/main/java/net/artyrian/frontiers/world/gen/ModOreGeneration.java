package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.function.Predicate;

public class ModOreGeneration
{
    // Preset Predicates
    private static final Predicate<BiomeSelectionContext> HIELOSTONE_GENKEY = BiomeSelectors.includeByKey(
            BiomeKeys.SNOWY_PLAINS,
            BiomeKeys.ICE_SPIKES,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.FROZEN_RIVER,
            BiomeKeys.FROZEN_PEAKS
    );

    public static void generateOres()
    {

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                    BiomeKeys.JUNGLE,
                    BiomeKeys.BAMBOO_JUNGLE,
                    BiomeKeys.SPARSE_JUNGLE
                ),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_ICE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.VERDINITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.VIVULITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.ICE_SPIKES),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY);
    }
}
