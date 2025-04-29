package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.entity_model.CreeperModelBlockEntity;
import net.artyrian.frontiers.block.entity.portal.CragsPortalBlockEntity;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class CragsPortalBlockEntityRenderer implements BlockEntityRenderer<CragsPortalBlockEntity>
{
    public static final Identifier FUZZ_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/crags_overlay.png");
    public static final Identifier PORTAL_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/crags_portal.png");

    public CragsPortalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx)
    {

    }

    public void render(
            CragsPortalBlockEntity cragsPortalBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j
    )
    {
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
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
            vertices.vertex(model, x1, y1, z1);
            vertices.vertex(model, x2, y1, z2);
            vertices.vertex(model, x2, y2, z3);
            vertices.vertex(model, x1, y2, z4);
        }
    }

    protected float getTopYOffset() {
        return 0.75F;
    }

    protected float getBottomYOffset() {
        return 0.375F;
    }

    protected RenderLayer getLayer() {
        return ModRenderLayers.getCragsPortal();
    }
}
