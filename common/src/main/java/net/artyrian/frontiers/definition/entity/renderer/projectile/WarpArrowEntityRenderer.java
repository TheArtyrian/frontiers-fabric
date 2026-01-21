package net.artyrian.frontiers.definition.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.projectile.WarpArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class WarpArrowEntityRenderer extends ArrowRenderer<WarpArrowEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/warp_arrow.png");

    public WarpArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(WarpArrowEntity warpArrowEntity) {
        return TEXTURE;
    }
}