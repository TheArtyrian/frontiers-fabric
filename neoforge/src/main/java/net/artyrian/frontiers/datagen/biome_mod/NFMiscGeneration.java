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

public class NFMiscGeneration
{
    public static final ResourceKey<BiomeModifier> ADD_QUICKSAND = FRBiomeModsNF.registerKey("add_quicksand");
    public static final ResourceKey<BiomeModifier> ADD_SLIME_TRAIL = FRBiomeModsNF.registerKey("add_slime_trail");
    public static final ResourceKey<BiomeModifier> ADD_EBONCORK = FRBiomeModsNF.registerKey("add_eboncork");

    public static void register(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_QUICKSAND, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_QUICKSAND),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.QUICKSAND_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION
        ));

        context.register(ADD_SLIME_TRAIL, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SLIME_TRAIL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_STRUCTURES
        ));

        context.register(ADD_EBONCORK, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_EBONCORK),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EBONCORK_SPIKE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}
