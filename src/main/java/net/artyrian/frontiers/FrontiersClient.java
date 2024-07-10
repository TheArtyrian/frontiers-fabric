package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.client.render.entity.CobaltFishingBobberEntityRenderer;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.misc.ModPredicate;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

// API init lol
public class FrontiersClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // Create renderers.
        EntityRendererRegistry.register(ModEntity.COBALT_BOBBER, CobaltFishingBobberEntityRenderer::new);

        // Client-side only registers:
        ModPredicate.registerModPredicates();		// Item predicates.

        // Add cutout mipmaps (help im going insane)
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VIOLET_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ANCIENT_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VIOLET_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VIOLET_ROSE_BUSH, RenderLayer.getCutout());
    }
}
