package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.PumpkinGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PumpkinGolemEyesRenderer<T extends PumpkinGolemEntity> extends FeatureRenderer<T, PumpkinGolemModel<T>>
{
    private static final List<RenderLayer> TEXTURE_EYES = List.of(
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_1.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_2.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_3.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_4.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_5.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_6.png")),
            RenderLayer.getEyes(Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_7.png"))
    );

    public PumpkinGolemEyesRenderer(FeatureRendererContext<T, PumpkinGolemModel<T>> featureRendererContext)
    {
        super(featureRendererContext);
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            T entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        if (entity instanceof PumpkinGolemEntity pump && !pump.isGolemAsleep())
        {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture(entity));
            this.getContextModel().render(matrices, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV);
        }
    }

    public RenderLayer getEyesTexture(T entity)
    {
        if (entity instanceof PumpkinGolemEntity pump)
        {
            int clamper = Math.clamp(pump.getGolemStyle(), PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);
            return TEXTURE_EYES.get(clamper);
        }
        return TEXTURE_EYES.getFirst();
    }
}
