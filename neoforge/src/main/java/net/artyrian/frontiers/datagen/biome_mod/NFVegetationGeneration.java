package net.artyrian.frontiers.datagen.biome_mod;

import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModPlacedFeatures;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

public class NFVegetationGeneration
{
    public static final ResourceKey<BiomeModifier> ADD_FUNGAL_DAFFODIL = FRBiomeModsNF.registerKey("add_fungal_daffodil");
    public static final ResourceKey<BiomeModifier> ADD_SNOW_DAHLIA = FRBiomeModsNF.registerKey("add_snow_dahlia");
    public static final ResourceKey<BiomeModifier> ADD_CRIMCONE = FRBiomeModsNF.registerKey("add_crimcone");
    public static final ResourceKey<BiomeModifier> ADD_EXPERIWINKLE = FRBiomeModsNF.registerKey("add_experiwinkle");

    public static void register(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_FUNGAL_DAFFODIL, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_FUNGAL_DAFFODIL),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FUNGAL_DAFFODIL_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_SNOW_DAHLIA, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_SNOW_DAHLIA),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SNOW_DAHLIA_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_CRIMCONE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_CRIMCONE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CRIMCONE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_EXPERIWINKLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.GENERATES_EXPERIWINKLE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EXPERIWINKLE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}
