package net.artyrian.frontiers.world.gen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
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
    private static final Predicate<BiomeSelectionContext> BLACK_EMERALD_KEY = BiomeSelectors.includeByKey(
            BiomeKeys.MEADOW,
            BiomeKeys.CHERRY_GROVE,
            BiomeKeys.GROVE,
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.JAGGED_PEAKS,
            BiomeKeys.FROZEN_PEAKS,
            BiomeKeys.STONY_PEAKS,
            BiomeKeys.WINDSWEPT_HILLS,
            BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
            BiomeKeys.WINDSWEPT_FOREST
    );
    private static final Predicate<BiomeSelectionContext> BRIMTAN_GENKEY = BiomeSelectors.includeByKey(
            RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Frontiers.MOD_ID, "crags_plains"))
    );

    public static void generateOres()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_ICE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.VERDINITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.VIVULITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BLACK_EMERALD_KEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_EMERALD_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BRIMTAN_GENKEY,
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BRIMTAN_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.ICE_SPIKES),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY);
    }
}
