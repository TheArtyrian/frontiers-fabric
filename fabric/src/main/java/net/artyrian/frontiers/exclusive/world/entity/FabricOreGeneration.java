package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import java.util.function.Predicate;

public class FabricOreGeneration
{
    // Preset Predicates
    private static final Predicate<BiomeSelectionContext> HIELOSTONE_GENKEY = BiomeSelectors.includeByKey(
            Biomes.SNOWY_PLAINS,
            Biomes.ICE_SPIKES,
            Biomes.FROZEN_OCEAN,
            Biomes.DEEP_FROZEN_OCEAN,
            Biomes.FROZEN_RIVER,
            Biomes.FROZEN_PEAKS
    );
    private static final Predicate<BiomeSelectionContext> BLACK_EMERALD_KEY = BiomeSelectors.includeByKey(
            Biomes.MEADOW,
            Biomes.CHERRY_GROVE,
            Biomes.GROVE,
            Biomes.SNOWY_SLOPES,
            Biomes.JAGGED_PEAKS,
            Biomes.FROZEN_PEAKS,
            Biomes.STONY_PEAKS,
            Biomes.WINDSWEPT_HILLS,
            Biomes.WINDSWEPT_GRAVELLY_HILLS,
            Biomes.WINDSWEPT_FOREST
    );
    private static final Predicate<BiomeSelectionContext> BRIMTAN_GENKEY = BiomeSelectors.includeByKey(
            ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crags_plains"))
    );

    public static void generateOres()
    {
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.COBALT_ORE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_PLACED_KEY);

        BiomeModifications.addFeature(HIELOSTONE_GENKEY,
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.HIELOSTONE_ICE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.VERDINITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.VIVULITE_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BLACK_EMERALD_KEY,
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLACK_EMERALD_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BRIMTAN_GENKEY,
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRIMTAN_ORE_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.ICE_SPIKES),
                GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY);
    }
}
