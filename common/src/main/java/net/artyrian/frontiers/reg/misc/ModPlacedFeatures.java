package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;

public class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> CRIMCONE_PLACED_KEY = registerKey("crimcone_placed");
    public static final ResourceKey<PlacedFeature> SNOW_DAHLIA_PLACED_KEY = registerKey("snow_dahlia_placed");
    public static final ResourceKey<PlacedFeature> FUNGAL_DAFFODIL_PLACED_KEY = registerKey("fungal_daffodil_placed");
    public static final ResourceKey<PlacedFeature> EXPERIWINKLE_PLACED_KEY = registerKey("experiwinkle_placed");

    public static final ResourceKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore_placed");
    public static final ResourceKey<PlacedFeature> VERDINITE_ORE_PLACED_KEY = registerKey("verdinite_ore_placed");
    public static final ResourceKey<PlacedFeature> VIVULITE_ORE_PLACED_KEY = registerKey("vivulite_ore_placed");
    public static final ResourceKey<PlacedFeature> FROSTITE_ORE_PLACED_KEY = registerKey("frostite_ore_placed");
    public static final ResourceKey<PlacedFeature> BRIMTAN_ORE_PLACED_KEY = registerKey("brimtan_ore_placed");
    public static final ResourceKey<PlacedFeature> BLACK_EMERALD_ORE_PLACED_KEY = registerKey("black_emerald_ore_placed");
    public static final ResourceKey<PlacedFeature> HIELOSTONE_PLACED_KEY = registerKey("hielostone_placed");
    public static final ResourceKey<PlacedFeature> HIELOSTONE_ICE_PLACED_KEY = registerKey("hielostone_ice_placed");

    public static final ResourceKey<PlacedFeature> QUICKSAND_PLACED_KEY = registerKey("quicksand_placed");

    public static final ResourceKey<PlacedFeature> SLIME_TRAIL_PLACED_KEY = registerKey("slime_trail_placed");
    public static final ResourceKey<PlacedFeature> EBONCORK_SPIKE_PLACED_KEY = registerKey("eboncork_spike_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        // Vegetation
        PlacementUtils.register(
                context,
                CRIMCONE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CRIMCONE_KEY),
                RarityFilter.onAverageOnceEvery(20),
                CountOnEveryLayerPlacement.of(6),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                SNOW_DAHLIA_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_DAHLIA_KEY),
                RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                EXPERIWINKLE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.EXPERIWINKLE_KEY),
                RarityFilter.onAverageOnceEvery(40),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FUNGAL_DAFFODIL_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGAL_DAFFODIL_KEY),
                RarityFilter.onAverageOnceEvery(20),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );

        // Quicksand
        PlacementUtils.register(
                context,
                QUICKSAND_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.QUICKSAND_KEY),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.SAND, Blocks.GRASS_BLOCK, Blocks.DIRT)),
                BiomeFilter.biome()
        );

        // Slime Trail
        PlacementUtils.register(
                context,
                SLIME_TRAIL_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SLIME_TRAIL_KEY),
                CountPlacement.of(32),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(60)),
                BiomeFilter.biome()
        );

        // Eboncork Spike
        PlacementUtils.register(
                context,
                EBONCORK_SPIKE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.EBONCORK_SPIKE_KEY),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(155)),
                BiomeFilter.biome()
        );

        // Ores
        register(context, COBALT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        5,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(12))
                )
        );

        register(context, VERDINITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VERDINITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        3,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-30))
                )
        );

        register(context, VIVULITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VIVULITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        4,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-50))
                )
        );

        register(context, FROSTITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FROSTITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        16,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(6), VerticalAnchor.absolute(132))
                )
        );

        register(
                context, BLACK_EMERALD_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACK_EMERALD_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(20))
                )
        );

        register(context, BRIMTAN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BRIMTAN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(120))
                )
        );

        register(context, HIELOSTONE_ICE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HIELOSTONE_ICE_MOD_KEY),
                ModOrePlacement.modifiersWithCount(
                        16,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(128))
                )
        );

        register(context, HIELOSTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HIELOSTONE_KEY),
                ModOrePlacement.modifiersWithCount(
                        12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(128))
                )
        );
    }

    public static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
