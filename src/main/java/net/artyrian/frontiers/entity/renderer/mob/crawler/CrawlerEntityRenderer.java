package net.artyrian.frontiers.entity.renderer.mob.crawler;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CrawlerEntityRenderer extends MobEntityRenderer<CrawlerEntity, CreeperEntityModel<CrawlerEntity>>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/crawler/crawler.png");

    public CrawlerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CreeperEntityModel<>(context.getPart(EntityModelLayers.CREEPER)), 0.5F);
        this.addFeature(new CrawlerEyesFeatureRenderer<>(this));
        this.addFeature(new CrawlerChargeFeatureRenderer(this, context.getModelLoader()));
    }

    protected void scale(CrawlerEntity crawlerEntity, MatrixStack matrixStack, float f) {
        float g = crawlerEntity.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }

    protected float getAnimationCounter(CrawlerEntity crawlerEntity, float f) {
        float g = crawlerEntity.getClientFuseTime(f);
        return (int)(g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
    }

    public Identifier getTexture(CrawlerEntity crawlerEntity) {
        return TEXTURE;
    }
}