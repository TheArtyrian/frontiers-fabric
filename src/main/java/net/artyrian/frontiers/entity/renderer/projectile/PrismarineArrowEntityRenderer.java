package net.artyrian.frontiers.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.projectile.PrismarineArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PrismarineArrowEntityRenderer extends ProjectileEntityRenderer<PrismarineArrowEntity>
{
    public static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/prismarine_arrow.png");

    public PrismarineArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(PrismarineArrowEntity prismarineArrowEntity) {
        return TEXTURE;
    }
}