package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.reg.content.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome>
{
    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, Registries.BIOME, completableFuture);
    }

    private void modBiomeTag()
    {
        this.getOrCreateTagBuilder(ModTags.Biomes.WHITE_TOWER_HAS_STRUCTURE)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.WINDSWEPT_HILLS);
    }

    // Vanilla tags.
    private void vanillaBiomeTag()
    {

    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modBiomeTag();
        vanillaBiomeTag();
    }
}
