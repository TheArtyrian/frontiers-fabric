package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.entity.ModEntity;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider
{
    public ModEntityTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    // Vanilla tags.
    private void vanillaEntityTag()
    {
        getOrCreateTagBuilder(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                .add(ModEntity.BALL)
        ;
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        vanillaEntityTag();
    }
}
