package net.artyrian.frontiers.definition.entity.renderer.mob.jungle_spider;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.mob.JungleSpiderEntity;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JungleSpiderEntityRenderer<T extends JungleSpiderEntity> extends MobRenderer<T, SpiderModel<T>>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/spider/jungle_spider.png");

    public JungleSpiderEntityRenderer(EntityRendererProvider.Context context)
    {
        this(context, ModelLayers.SPIDER);
    }

    public JungleSpiderEntityRenderer(EntityRendererProvider.Context ctx, ModelLayerLocation layer)
    {
        super(ctx, new SpiderModel<>(ctx.bakeLayer(layer)), 0.8F);
        this.shadowRadius *= 0.5F;
    }

    protected void scale(JungleSpiderEntity jungleSpiderEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }

    protected float getFlipDegrees(T jungleSpiderEntity) {
        return 180.0F;
    }

    public ResourceLocation getTextureLocation(T jungleSpiderEntity) {
        return TEXTURE;
    }
}