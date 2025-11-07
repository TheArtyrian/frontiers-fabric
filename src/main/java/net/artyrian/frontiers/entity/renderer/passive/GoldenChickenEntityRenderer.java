package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.GoldenChickenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GoldenChickenEntityRenderer extends MobEntityRenderer<GoldenChickenEntity, ChickenEntityModel<GoldenChickenEntity>>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID,"textures/entity/chicken/golden_chicken.png");

    public GoldenChickenEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new ChickenEntityModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3F);
    }

    public Identifier getTexture(GoldenChickenEntity goldenChickenEntity) {
        return TEXTURE;
    }

    protected float getAnimationProgress(GoldenChickenEntity goldenChickenEntity, float f)
    {
        float g = MathHelper.lerp(f, goldenChickenEntity.prevFlapProgress, goldenChickenEntity.flapProgress);
        float h = MathHelper.lerp(f, goldenChickenEntity.prevMaxWingDeviation, goldenChickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}