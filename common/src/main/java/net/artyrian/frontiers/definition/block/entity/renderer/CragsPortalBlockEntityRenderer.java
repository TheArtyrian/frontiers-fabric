package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.CragsPortalBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class CragsPortalBlockEntityRenderer implements BlockEntityRenderer<CragsPortalBlockEntity>
{
    public static final ResourceLocation FUZZ_TEXTURE = Frontiers.id("textures/entity/crags_overlay.png");
    public static final ResourceLocation PORTAL_TEXTURE = Frontiers.id("textures/entity/crags_portal.png");

    public CragsPortalBlockEntityRenderer(BlockEntityRendererProvider.Context ctx)
    {

    }

    public void render(
            CragsPortalBlockEntity cragsPortalBlockEntity, float f, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, int j
    )
    {
        Matrix4f matrix4f = matrixStack.last().pose();
        this.renderSides(cragsPortalBlockEntity, matrix4f, vertexConsumerProvider.getBuffer(this.getLayer()));
    }

    private void renderSides(CragsPortalBlockEntity entity, Matrix4f matrix, VertexConsumer vertexConsumer)
    {
        float f = this.getBottomYOffset();
        float g = this.getTopYOffset();
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(entity, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, f, f, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, g, g, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(
            CragsPortalBlockEntity entity, Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, Direction side
    )
    {
        if (entity.shouldDrawSide(side))
        {
            vertices.addVertex(model, x1, y1, z1);
            vertices.addVertex(model, x2, y1, z2);
            vertices.addVertex(model, x2, y2, z3);
            vertices.addVertex(model, x1, y2, z4);
        }
    }

    protected float getTopYOffset() {
        return 0.75F;
    }

    protected float getBottomYOffset() {
        return 0.375F;
    }

    protected RenderType getLayer() {
        return ModRenderLayers.getCragsPortal();
    }
}
