package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.TimeSwitchLogBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

public class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRIMCONE_KEY = registerKey("crimcone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FUNGAL_DAFFODIL_KEY = registerKey("fungal_daffodil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_DAHLIA_KEY = registerKey("snow_dahlia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EXPERIWINKLE_KEY = registerKey("experiwinkle");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VERDINITE_ORE_KEY = registerKey("verdinite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VIVULITE_ORE_KEY = registerKey("vivulite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTITE_ORE_KEY = registerKey("frostite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMTAN_ORE_KEY = registerKey("brimtan_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_EMERALD_ORE_KEY = registerKey("black_emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIELOSTONE_KEY = registerKey("hielostone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIELOSTONE_ICE_MOD_KEY = registerKey("hielostone_ice_mod");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLIGHTED_BIRCH_KEY = registerKey("blighted_birch");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_FUNGAL_DAFFODIL_KEY = registerKey("huge_fungal_daffodil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> QUICKSAND_KEY = registerKey("quicksand");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SLIME_TRAIL_KEY = registerKey("slime_trail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONCORK_SPIKE_KEY = registerKey("eboncork_spike");

    // All registries.
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest baseStone = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);
        RuleTest hielostoneRule = new BlockMatchTest(ModBlocks.HIELOSTONE.get());
        RuleTest cragulstaneRule = new BlockMatchTest(ModBlocks.CRAGULSTANE.get());

        // Cobalt ore
        List<OreConfiguration.TargetBlockState> cobaltOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.COBALT_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState())
                );
        // Verdinite ore
        List<OreConfiguration.TargetBlockState> verdiniteOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.VERDINITE_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_VERDINITE_ORE.get().defaultBlockState())
                );
        // Vivulite ore
        List<OreConfiguration.TargetBlockState> vivuliteOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.VIVULITE_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_VIVULITE_ORE.get().defaultBlockState())
                );
        // Black Emerald ore
        List<OreConfiguration.TargetBlockState> blackEmeraldOres =
                List.of(OreConfiguration.target(stoneReplaceables, ModBlocks.BLACK_EMERALD_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get().defaultBlockState())
                );
        // Frostite ore
        List<OreConfiguration.TargetBlockState> frostiteOres =
                List.of(OreConfiguration.target(iceReplaceables, ModBlocks.FROSTITE_ORE.get().defaultBlockState())
                );

        // Quicksand
        register(
                context,
                QUICKSAND_KEY,
                Feature.DISK,
                new DiskConfiguration(
                        RuleBasedBlockStateProvider.simple(ModBlocks.QUICKSAND.get()),
                        BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.COARSE_DIRT)),
                        UniformInt.of(2, 4),
                        1
                )
        );

        // Blighted Birch Tree
        register(
                context,
                BLIGHTED_BIRCH_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get().defaultBlockState()
                                .setValue(TimeSwitchLogBlock.CAN_SWITCH, true)),
                        new StraightTrunkPlacer(5, 2, 0),
                        BlockStateProvider.simple(ModBlocks.BLIGHTED_BIRCH_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build()
        );

        // Crimcone
        WeightedStateProvider crimProvider = new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(ModBlocks.CRIMCONE.get().defaultBlockState(), 100)
        );
        register(context, CRIMCONE_KEY, Feature.NETHER_FOREST_VEGETATION,
                new NetherForestVegetationConfig(crimProvider, 8, 4));
        // Snow Dahlia
        register(context, SNOW_DAHLIA_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SNOW_DAHLIA.get())))
                ));
        // Fungal Daffodil
        register(context, FUNGAL_DAFFODIL_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.FUNGAL_DAFFODIL.get())))
                ));
        // Experiwinkle
        register(context, EXPERIWINKLE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        2, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.EXPERIWINKLE.get())))
                ));

        // Slime Trail
        register(context, SLIME_TRAIL_KEY, ModFeature.SLIME_TRAIL.get(), new NoneFeatureConfiguration());

        // Eboncork Spike
        register(context, EBONCORK_SPIKE_KEY, ModFeature.EBONCORK_SPIKE.get(), new NoneFeatureConfiguration());

        // Huge Fungal Daffodil
        register(
                context, HUGE_FUNGAL_DAFFODIL_KEY, ModFeature.HUGE_FUNGAL_DAFFODIL.get(),
                new HugeMushroomFeatureConfiguration(
                        BlockStateProvider.simple(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)),
                        BlockStateProvider.simple(
                                Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP,false).setValue(HugeMushroomBlock.DOWN,false)
                        ),
                        5
                )
        );

        // Ores + Stones
        register(context, COBALT_ORE_KEY, Feature.ORE, new OreConfiguration(cobaltOres, 5, 0.5F));
        register(context, VERDINITE_ORE_KEY, Feature.ORE, new OreConfiguration(verdiniteOres, 5, 0.85F));
        register(context, VIVULITE_ORE_KEY, Feature.ORE, new OreConfiguration(vivuliteOres, 4, 1.0F));
        register(context, FROSTITE_ORE_KEY, Feature.ORE, new OreConfiguration(frostiteOres, 7));
        register(context, BLACK_EMERALD_ORE_KEY, Feature.ORE, new OreConfiguration(blackEmeraldOres, 3));
        register(context, BRIMTAN_ORE_KEY, Feature.ORE, new OreConfiguration(cragulstaneRule, ModBlocks.BRIMTAN_ORE.get().defaultBlockState(), 5, 0.45F));
        register(context, HIELOSTONE_KEY, Feature.ORE, new OreConfiguration(baseStone, ModBlocks.HIELOSTONE.get().defaultBlockState(), 64));
        register(context, HIELOSTONE_ICE_MOD_KEY, Feature.ORE, new OreConfiguration(baseStone, Blocks.PACKED_ICE.defaultBlockState(), 32));
    }

    // Registers a key.
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
    }

    // Actually registers context.
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                    ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
