package net.artyrian.frontiers.entity.renderer.mob.jungle_spider;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.mob.JungleSpiderEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.util.Identifier;

public class JungleSpiderEntityRenderer<T extends JungleSpiderEntity> extends MobEntityRenderer<T, SpiderEntityModel<T>>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID,"textures/entity/spider/jungle_spider.png");

    public JungleSpiderEntityRenderer(EntityRendererFactory.Context context)
    {
        this(context, EntityModelLayers.SPIDER);
    }

    public JungleSpiderEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer)
    {
        super(ctx, new SpiderEntityModel<>(ctx.getPart(layer)), 0.8F);
        this.shadowRadius *= 0.5F;
    }

    protected void scale(JungleSpiderEntity jungleSpiderEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.5F, 0.5F, 0.5F);
    }

    protected float getLyingAngle(T JungleSpiderEntity) {
        return 180.0F;
    }

    public Identifier getTexture(T JungleSpiderEntity) {
        return TEXTURE;
    }
}