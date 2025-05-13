package net.artyrian.frontiers.entity.renderer.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.misc.CragsStalkerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CragsStalkerEntityRenderer extends EntityRenderer<CragsStalkerEntity>
{
    public static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/evileyes.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutout(TEXTURE);

    public CragsStalkerEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context);
    }

    @Override
    public void render(CragsStalkerEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        matrices.push();
        matrices.translate(0, 2, 0);
        matrices.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrices.peek();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);

        vertex(vertexConsumer, entry, 15728880, 0.0F, 0, 0, 1);
        vertex(vertexConsumer, entry, 15728880, 1.0F, 0, 1, 1);
        vertex(vertexConsumer, entry, 15728880, 1.0F, 1, 1, 0);
        vertex(vertexConsumer, entry, 15728880, 0.0F, 1, 0, 0);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(CragsStalkerEntity entity) { return TEXTURE; }

    private static void vertex(VertexConsumer buffer, MatrixStack.Entry matrix, int light, float x, int y, int u, int v)
    {
        buffer.vertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F)
                .color(-1)
                .texture((float)u, (float)v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, 0.0F, 1.0F, 0.0F);
    }
}
