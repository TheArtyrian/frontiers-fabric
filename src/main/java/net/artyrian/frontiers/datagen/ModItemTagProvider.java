package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    // Mod tags.
    private void modItemTag()
    {

    }

    // Vanilla tags.
    private void vanillaItemTag()
    {

    }

    // Fabric tags.
    private void fabricItemTag()
    {

    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modItemTag();
        vanillaItemTag();
        fabricItemTag();
    }
}
