package net.artyrian.frontiers.definition.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.projectile.DynamiteArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DynamiteArrowEntityRenderer extends ArrowRenderer<DynamiteArrowEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/dynamite_arrow.png");

    public DynamiteArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(DynamiteArrowEntity dynamiteArrowEntity) {
        return TEXTURE;
    }
}