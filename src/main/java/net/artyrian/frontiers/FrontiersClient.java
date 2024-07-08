package net.artyrian.frontiers;

import net.artyrian.frontiers.client.render.entity.CobaltFishingBobberEntityRenderer;
import net.artyrian.frontiers.entity.ModEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

// API init lol
public class FrontiersClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // Create renderers.
        EntityRendererRegistry.register(ModEntity.COBALT_BOBBER, CobaltFishingBobberEntityRenderer::new);
    }
}
