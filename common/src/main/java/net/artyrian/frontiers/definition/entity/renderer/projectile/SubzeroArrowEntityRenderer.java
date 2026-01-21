package net.artyrian.frontiers.definition.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.projectile.SubzeroArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SubzeroArrowEntityRenderer extends ArrowRenderer<SubzeroArrowEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/subzero_arrow.png");

    public SubzeroArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(SubzeroArrowEntity subzeroArrowEntity) {
        return TEXTURE;
    }
}