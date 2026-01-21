package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.passive.GoldenChickenEntity;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GoldenChickenEntityRenderer extends MobRenderer<GoldenChickenEntity, ChickenModel<GoldenChickenEntity>>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/chicken/golden_chicken.png");

    public GoldenChickenEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    public ResourceLocation getTextureLocation(GoldenChickenEntity goldenChickenEntity) {
        return TEXTURE;
    }

    protected float getBob(GoldenChickenEntity goldenChickenEntity, float f)
    {
        float g = Mth.lerp(f, goldenChickenEntity.prevFlapProgress, goldenChickenEntity.flapProgress);
        float h = Mth.lerp(f, goldenChickenEntity.prevMaxWingDeviation, goldenChickenEntity.maxWingDeviation);
        return (Mth.sin(g) + 1.0F) * h;
    }
}