package net.artyrian.frontiers.rendering;

import net.artyrian.frontiers.block.entity.renderer.CragsPortalBlockEntityRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

public class ModRenderLayers
{
    private static final RenderLayer CRAGS_PORTAL = RenderLayer.of(
            "frontiers_crags_portal",
            VertexFormats.POSITION,
            VertexFormat.DrawMode.QUADS,
            1536,
            false,
            false,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(RenderPhase.END_PORTAL_PROGRAM)
                    .texture(
                            RenderPhase.Textures.create()
                                    .add(CragsPortalBlockEntityRenderer.FUZZ_TEXTURE, false, false)
                                    .add(CragsPortalBlockEntityRenderer.PORTAL_TEXTURE, false, false)
                                    .build()
                    )
                    .build(false)
    );

    public static RenderLayer getCragsPortal()
    {
        return CRAGS_PORTAL;
    }

    public static void reg()
    {

    }
}
