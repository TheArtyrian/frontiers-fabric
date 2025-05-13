package net.artyrian.frontiers.world;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures
{
    public static final RegistryKey<PlacedFeature> CRIMCONE_PLACED_KEY = registerKey("crimcone_placed");
    public static final RegistryKey<PlacedFeature> SNOW_DAHLIA_PLACED_KEY = registerKey("snow_dahlia_placed");
    public static final RegistryKey<PlacedFeature> FUNGAL_DAFFODIL_PLACED_KEY = registerKey("fungal_daffodil_placed");

    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore_placed");
    public static final RegistryKey<PlacedFeature> VERDINITE_ORE_PLACED_KEY = registerKey("verdinite_ore_placed");
    public static final RegistryKey<PlacedFeature> VIVULITE_ORE_PLACED_KEY = registerKey("vivulite_ore_placed");
    public static final RegistryKey<PlacedFeature> FROSTITE_ORE_PLACED_KEY = registerKey("frostite_ore_placed");
    public static final RegistryKey<PlacedFeature> BRIMTAN_ORE_PLACED_KEY = registerKey("brimtan_ore_placed");
    public static final RegistryKey<PlacedFeature> HIELOSTONE_PLACED_KEY = registerKey("hielostone_placed");
    public static final RegistryKey<PlacedFeature> HIELOSTONE_ICE_PLACED_KEY = registerKey("hielostone_ice_placed");

    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Vegetation
        PlacedFeatures.register(
                context,
                CRIMCONE_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CRIMCONE_KEY),
                RarityFilterPlacementModifier.of(32),
                CountMultilayerPlacementModifier.of(6),
                BiomePlacementModifier.of()
        );

        PlacedFeatures.register(
                context,
                SNOW_DAHLIA_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_DAHLIA_KEY),
                RarityFilterPlacementModifier.of(6),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        PlacedFeatures.register(
                context,
                FUNGAL_DAFFODIL_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FUNGAL_DAFFODIL_KEY),
                RarityFilterPlacementModifier.of(32),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        // Ores
        register(context, COBALT_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        6,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-60), YOffset.fixed(20))
                )
        );

        register(context, VERDINITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VERDINITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        3,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(-30))
                )
        );

        register(context, VIVULITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.VIVULITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        4,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(-50))
                )
        );

        register(context, FROSTITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FROSTITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        16,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(6), YOffset.fixed(132))
                )
        );

        register(context, BRIMTAN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BRIMTAN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(
                        10,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(120))
                )
        );

        register(context, HIELOSTONE_ICE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HIELOSTONE_ICE_MOD_KEY),
                ModOrePlacement.modifiersWithCount(
                        16,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-20), YOffset.fixed(128))
                )
        );

        register(context, HIELOSTONE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HIELOSTONE_KEY),
                ModOrePlacement.modifiersWithCount(
                        12,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-20), YOffset.fixed(128))
                )
        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Frontiers.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
