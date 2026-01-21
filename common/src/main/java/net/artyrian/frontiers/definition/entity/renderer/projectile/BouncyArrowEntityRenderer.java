package net.artyrian.frontiers.definition.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.projectile.BouncyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BouncyArrowEntityRenderer extends ArrowRenderer<BouncyArrowEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/bouncy_arrow.png");

    public BouncyArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(BouncyArrowEntity bouncyArrowEntity) {
        return TEXTURE;
    }
}