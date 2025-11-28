package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome>
{
    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, RegistryKeys.BIOME, completableFuture);
    }

    private void modBiomeTag()
    {
        this.getOrCreateTagBuilder(ModTags.Biomes.WHITE_TOWER_HAS_STRUCTURE)
                .add(BiomeKeys.SNOWY_SLOPES)
                .add(BiomeKeys.JAGGED_PEAKS)
                .add(BiomeKeys.FROZEN_PEAKS)
                .add(BiomeKeys.WINDSWEPT_HILLS);
    }

    // Vanilla tags.
    private void vanillaBiomeTag()
    {

    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modBiomeTag();
        vanillaBiomeTag();
    }
}
