package net.artyrian.frontiers.definition.entity.renderer.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.misc.CragsMonsterEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class CragsMonsterEntityRenderer extends EntityRenderer<CragsMonsterEntity>
{
    public static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/theonetrueevil.png");
    private static final RenderType LAYER = RenderType.entityCutout(TEXTURE);

    public CragsMonsterEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context);
    }

    @Override
    public void render(CragsMonsterEntity entity, float yaw, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light)
    {
        matrices.pushPose();
        matrices.translate(0, 4, 0);
        matrices.scale(16, 16, 16);
        matrices.mulPose(this.entityRenderDispatcher.cameraOrientation());
        PoseStack.Pose entry = matrices.last();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);

        vertex(vertexConsumer, entry, 15728880, 0.0F, 0, 0, 1);
        vertex(vertexConsumer, entry, 15728880, 1.0F, 0, 1, 1);
        vertex(vertexConsumer, entry, 15728880, 1.0F, 1, 1, 0);
        vertex(vertexConsumer, entry, 15728880, 0.0F, 1, 0, 0);

        matrices.popPose();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public ResourceLocation getTextureLocation(CragsMonsterEntity entity) { return TEXTURE; }

    private static void vertex(VertexConsumer buffer, PoseStack.Pose matrix, int light, float x, int y, int u, int v)
    {
        buffer.addVertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F)
                .setColor(-1)
                .setUv((float)u, (float)v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(matrix, 0.0F, 1.0F, 0.0F);
    }
}
