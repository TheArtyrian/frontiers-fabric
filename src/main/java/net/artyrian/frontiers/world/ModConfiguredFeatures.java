package net.artyrian.frontiers.world;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final RegistryKey<ConfiguredFeature<?, ?>> CRIMCONE_KEY = registerKey("crimcone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FUNGAL_DAFFODIL_KEY = registerKey("fungal_daffodil");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SNOW_DAHLIA_KEY = registerKey("snow_dahlia");

    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERDINITE_ORE_KEY = registerKey("verdinite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VIVULITE_ORE_KEY = registerKey("vivulite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FROSTITE_ORE_KEY = registerKey("frostite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BRIMTAN_ORE_KEY = registerKey("brimtan_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIELOSTONE_KEY = registerKey("hielostone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIELOSTONE_ICE_MOD_KEY = registerKey("hielostone_ice_mod");

    public static final RegistryKey<ConfiguredFeature<?, ?>> QUICKSAND_KEY = registerKey("quicksand");

    // All registries.
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        RuleTest baseStone = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceables = new BlockMatchRuleTest(Blocks.PACKED_ICE);
        RuleTest hielostoneRule = new BlockMatchRuleTest(ModBlocks.HIELOSTONE);
        RuleTest cragulstaneRule = new BlockMatchRuleTest(ModBlocks.CRAGULSTANE);

        // Cobalt ore
        List<OreFeatureConfig.Target> cobaltOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.COBALT_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState())
                );
        // Verdinite ore
        List<OreFeatureConfig.Target> verdiniteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.VERDINITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_VERDINITE_ORE.getDefaultState())
                );
        // Vivulite ore
        List<OreFeatureConfig.Target> vivuliteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.VIVULITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_VIVULITE_ORE.getDefaultState())
                );
        // Frostite ore
        List<OreFeatureConfig.Target> frostiteOres =
                List.of(OreFeatureConfig.createTarget(iceReplaceables, ModBlocks.FROSTITE_ORE.getDefaultState())
                );

        // Quicksand
        register(
                context,
                QUICKSAND_KEY,
                Feature.DISK,
                new DiskFeatureConfig(
                        PredicatedStateProvider.of(ModBlocks.QUICKSAND),
                        BlockPredicate.matchingBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.COARSE_DIRT)),
                        UniformIntProvider.create(2, 4),
                        1
                )
        );

        // Crimcone
        WeightedBlockStateProvider crimProvider = new WeightedBlockStateProvider(
                DataPool.<BlockState>builder()
                        .add(ModBlocks.CRIMCONE.getDefaultState(), 100)
        );
        register(context, CRIMCONE_KEY, Feature.NETHER_FOREST_VEGETATION,
                new NetherForestVegetationFeatureConfig(crimProvider, 8, 4));
        // Snow Dahlia
        register(context, SNOW_DAHLIA_KEY, Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        64, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SNOW_DAHLIA)))
                ));
        // Fungal Daffodil
        register(context, FUNGAL_DAFFODIL_KEY, Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        64, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FUNGAL_DAFFODIL)))
                ));

        // "Ores"
        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(cobaltOres, 5, 0.5F));
        register(context, VERDINITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(verdiniteOres, 5, 0.85F));
        register(context, VIVULITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(vivuliteOres, 4, 1.0F));
        register(context, FROSTITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(frostiteOres, 7));
        register(context, BRIMTAN_ORE_KEY, Feature.ORE, new OreFeatureConfig(cragulstaneRule, ModBlocks.BRIMTAN_ORE.getDefaultState(), 5, 0.45F));
        register(context, HIELOSTONE_KEY, Feature.ORE, new OreFeatureConfig(baseStone, ModBlocks.HIELOSTONE.getDefaultState(), 64));
        register(context, HIELOSTONE_ICE_MOD_KEY, Feature.ORE, new OreFeatureConfig(baseStone, Blocks.PACKED_ICE.getDefaultState(), 32));
    }

    // Registers a key.
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Frontiers.MOD_ID, name));
    }

    // Actually registers context.
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register (Registerable<ConfiguredFeature<?, ?>> context,
                                                                                    RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
