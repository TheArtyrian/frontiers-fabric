package net.artyrian.frontiers.definition.entity.renderer.mob.crawler;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.mob.CrawlerEntity;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class CrawlerChargeFeatureRenderer extends EnergySwirlLayer<CrawlerEntity, CreeperModel<CrawlerEntity>>
{
    private static final ResourceLocation SKIN = Frontiers.id("textures/entity/crawler/crawler_armor.png");
    private final CreeperModel<CrawlerEntity> model;

    public CrawlerChargeFeatureRenderer(RenderLayerParent<CrawlerEntity, CreeperModel<CrawlerEntity>> context, EntityModelSet loader)
    {
        super(context);
        this.model = new CreeperModel<>(loader.bakeLayer(ModelLayers.CREEPER_ARMOR));
    }

    @Override
    protected float xOffset(float partialAge) {
        return partialAge * 0.01F;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return SKIN;
    }

    @Override
    protected EntityModel<CrawlerEntity> model() {
        return this.model;
    }
}