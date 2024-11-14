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
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FROSTITE_ORE_KEY = registerKey("frostite_ore");

    // All registries.
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest iceReplaceables = new BlockMatchRuleTest(Blocks.PACKED_ICE);

        // Cobalt ore
        List<OreFeatureConfig.Target> cobaltOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.COBALT_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState())
                );
        // Frostite ore
        List<OreFeatureConfig.Target> frostiteOres =
                List.of(OreFeatureConfig.createTarget(iceReplaceables, ModBlocks.FROSTITE_ORE.getDefaultState())
                );

        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(cobaltOres, 6));
        register(context, FROSTITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(frostiteOres, 12));
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
