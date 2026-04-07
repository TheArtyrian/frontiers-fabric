package net.artyrian.frontiers.datagen.biome_mod;

import net.artyrian.frontiers.reg.content.FREntity;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

import java.util.List;

public class NFEntityGeneration
{
    public static final ResourceKey<BiomeModifier> SPAWN_JUNGLE_SPIDER = FRBiomeModsNF.registerKey("spawn_jungle_spider");
    public static final ResourceKey<BiomeModifier> SPAWN_CROW = FRBiomeModsNF.registerKey("spawn_crow");

    public static void register(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_JUNGLE_SPIDER, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                List.of(new MobSpawnSettings.SpawnerData(FREntity.JUNGLE_SPIDER.get(), 100, 4, 4))));

        context.register(SPAWN_CROW, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST)),
                List.of(new MobSpawnSettings.SpawnerData(FREntity.CROW.get(), 40, 1, 3))));
    }
}
