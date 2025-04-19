package net.artyrian.frontiers.world;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VERDINITE_ORE_KEY = registerKey("verdinite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VIVULITE_ORE_KEY = registerKey("vivulite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FROSTITE_ORE_KEY = registerKey("frostite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIELOSTONE_KEY = registerKey("hielostone");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HIELOSTONE_ICE_MOD_KEY = registerKey("hielostone_ice_mod");

    // All registries.
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        RuleTest baseStone = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceables = new BlockMatchRuleTest(Blocks.PACKED_ICE);
        RuleTest hielostoneRule = new BlockMatchRuleTest(ModBlocks.HIELOSTONE);

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
        // Verdinite ore
        List<OreFeatureConfig.Target> vivuliteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.VIVULITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_VIVULITE_ORE.getDefaultState())
                );
        // Frostite ore
        List<OreFeatureConfig.Target> frostiteOres =
                List.of(OreFeatureConfig.createTarget(iceReplaceables, ModBlocks.FROSTITE_ORE.getDefaultState())
                );

        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(cobaltOres, 6, 0.65F));
        register(context, VERDINITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(verdiniteOres, 5, 0.85F));
        register(context, VIVULITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(vivuliteOres, 4, 1.0F));
        register(context, FROSTITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(frostiteOres, 7));
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
