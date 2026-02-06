package net.artyrian.frontiers.datagen.biome_mod;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FRBiomeModsNF
{
    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        NFOreGeneration.register(context);
        NFVegetationGeneration.register(context);
        NFStructureGeneration.register(context);
        NFMiscGeneration.register(context);

        NFEntityGeneration.register(context);
    }

    public static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Frontiers.id(name));
    }
}
