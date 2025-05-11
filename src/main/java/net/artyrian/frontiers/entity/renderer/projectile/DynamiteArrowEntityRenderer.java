package net.artyrian.frontiers.entity.renderer.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.projectile.DynamiteArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DynamiteArrowEntityRenderer extends ProjectileEntityRenderer<DynamiteArrowEntity>
{
    public static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/dynamite_arrow.png");

    public DynamiteArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(DynamiteArrowEntity dynamiteArrowEntity) {
        return TEXTURE;
    }
}