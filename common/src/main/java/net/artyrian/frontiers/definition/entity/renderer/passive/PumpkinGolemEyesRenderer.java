package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;

public class PumpkinGolemEyesRenderer<T extends PumpkinGolemEntity> extends RenderLayer<T, PumpkinGolemModel<T>>
{
    private static final List<RenderType> TEXTURE_EYES = List.of(
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_1.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_2.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_3.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_4.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_5.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_6.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_7.png")),
            RenderType.eyes(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/mask/mask_8.png"))
    );

    public PumpkinGolemEyesRenderer(RenderLayerParent<T, PumpkinGolemModel<T>> featureRendererContext)
    {
        super(featureRendererContext);
    }

    @Override
    public void render(
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
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
            this.getParentModel().renderToBuffer(matrices, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY);
        }
    }

    public RenderType getEyesTexture(T entity)
    {
        if (entity instanceof PumpkinGolemEntity pump)
        {
            int clamper = Math.clamp(pump.getGolemStyle(), PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE_TRUE);
            return TEXTURE_EYES.get(clamper);
        }
        return TEXTURE_EYES.getFirst();
    }
}
