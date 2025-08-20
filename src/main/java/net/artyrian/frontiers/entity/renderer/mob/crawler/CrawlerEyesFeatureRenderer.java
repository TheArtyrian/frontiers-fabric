package net.artyrian.frontiers.entity.renderer.mob.crawler;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CrawlerEyesFeatureRenderer<T extends LivingEntity> extends EyesFeatureRenderer<T, CreeperEntityModel<T>>
{
    private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/crawler/crawler_glow.png"));

    public CrawlerEyesFeatureRenderer(FeatureRendererContext<T, CreeperEntityModel<T>> featureRendererContext)
    {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}