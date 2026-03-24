package net.artyrian.frontiers.definition.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.projectile.PrismarineArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PrismarineArrowEntityRenderer extends ArrowRenderer<PrismarineArrowEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/prismarine_arrow.png");

    public PrismarineArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public ResourceLocation getTextureLocation(PrismarineArrowEntity prismarineArrowEntity) {
        return TEXTURE;
    }
}