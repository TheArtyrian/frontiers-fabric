package net.artyrian.frontiers.definition.entity.renderer.mob.crawler;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.world.entity.LivingEntity;

public class CrawlerEyesFeatureRenderer<T extends LivingEntity> extends EyesLayer<T, CreeperModel<T>>
{
    private static final RenderType SKIN = RenderType.eyes(Frontiers.id("textures/entity/crawler/crawler_glow.png"));

    public CrawlerEyesFeatureRenderer(RenderLayerParent<T, CreeperModel<T>> featureRendererContext)
    {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
}