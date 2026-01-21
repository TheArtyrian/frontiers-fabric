package net.artyrian.frontiers.definition.entity.renderer.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.projectile.PaleTridentEntity;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PaleTridentEntityRenderer extends EntityRenderer<PaleTridentEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/projectiles/pale_trident.png");
    private final TridentModel model;

    public PaleTridentEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.model = new TridentModel(context.bakeLayer(ModelLayers.TRIDENT));
    }

    public void render(PaleTridentEntity tridentEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        matrixStack.pushPose();
        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, tridentEntity.yRotO, tridentEntity.getYRot()) - 90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, tridentEntity.xRotO, tridentEntity.getXRot()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(
                vertexConsumerProvider, this.model.renderType(this.getTextureLocation(tridentEntity)), false, tridentEntity.isEnchanted()
        );
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(PaleTridentEntity tridentEntity) {
        return TEXTURE;
    }
}