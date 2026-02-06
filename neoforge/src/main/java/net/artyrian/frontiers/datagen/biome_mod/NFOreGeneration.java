package net.artyrian.frontiers.datagen.biome_mod;

import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

public class NFOreGeneration
{
    public static final ResourceKey<BiomeModifier> ADD_COBALT_ORE = FRBiomeModsNF.registerKey("add_cobalt_ore");
    public static final ResourceKey<BiomeModifier> ADD_HIELOSTONE = FRBiomeModsNF.registerKey("add_hielostone");
    public static final ResourceKey<BiomeModifier> ADD_HIELOSTONE_ICE = FRBiomeModsNF.registerKey("add_hielostone_ice");
    public static final ResourceKey<BiomeModifier> ADD_VERDINITE_ORE = FRBiomeModsNF.registerKey("add_verdinite_ore");
    public static final ResourceKey<BiomeModifier> ADD_VIVULITE_ORE = FRBiomeModsNF.registerKey("add_vivulite_ore");
    public static final ResourceKey<BiomeModifier> ADD_BLACK_EMERALD = FRBiomeModsNF.registerKey("add_black_emerald");
    public static final ResourceKey<BiomeModifier> ADD_BRIMTAN_ORE = FRBiomeModsNF.registerKey("add_brimtan_ore");
    public static final ResourceKey<BiomeModifier> ADD_FROSTITE_ORE = FRBiomeModsNF.registerKey("add_bismuth_ore");

    public static void register(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_COBALT_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.COBALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_HIELOSTONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_HIELOSTONE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HIELOSTONE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_HIELOSTONE_ICE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_HIELOSTONE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HIELOSTONE_ICE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_VERDINITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VERDINITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_VIVULITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VIVULITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_BLACK_EMERALD, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_BLACK_EMERALD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLACK_EMERALD_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_BRIMTAN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_BRIMTAN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BRIMTAN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_FROSTITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_FROSTITE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FROSTITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }
}
