package net.artyrian.frontiers.definition.entity.renderer.mob.crawler;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.mob.CrawlerEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CrawlerEntityRenderer extends MobRenderer<CrawlerEntity, CreeperModel<CrawlerEntity>>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/crawler/crawler.png");

    public CrawlerEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new CreeperModel<>(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
        this.addLayer(new CrawlerEyesFeatureRenderer<>(this));
        this.addLayer(new CrawlerChargeFeatureRenderer(this, context.getModelSet()));
    }

    protected void scale(CrawlerEntity crawlerEntity, PoseStack matrixStack, float f)
    {
        float g = crawlerEntity.getClientFuseTime(f);
        float h = 1.0F + Mth.sin(g * 100.0F) * g * 0.01F;
        g = Mth.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }

    protected float getWhiteOverlayProgress(CrawlerEntity crawlerEntity, float f)
    {
        float g = crawlerEntity.getClientFuseTime(f);
        return (int)(g * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(g, 0.5F, 1.0F);
    }

    public ResourceLocation getTextureLocation(CrawlerEntity crawlerEntity) {
        return TEXTURE;
    }
}