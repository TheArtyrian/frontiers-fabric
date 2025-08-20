package net.artyrian.frontiers.entity.renderer.mob.crawler;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CrawlerChargeFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<CrawlerEntity, CreeperEntityModel<CrawlerEntity>>
{
    private static final Identifier SKIN = Identifier.of(Frontiers.MOD_ID, "textures/entity/crawler/crawler_armor.png");
    private final CreeperEntityModel<CrawlerEntity> model;

    public CrawlerChargeFeatureRenderer(FeatureRendererContext<CrawlerEntity, CreeperEntityModel<CrawlerEntity>> context, EntityModelLoader loader)
    {
        super(context);
        this.model = new CreeperEntityModel<>(loader.getModelPart(EntityModelLayers.CREEPER_ARMOR));
    }

    @Override
    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    @Override
    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    @Override
    protected EntityModel<CrawlerEntity> getEnergySwirlModel() {
        return this.model;
    }
}