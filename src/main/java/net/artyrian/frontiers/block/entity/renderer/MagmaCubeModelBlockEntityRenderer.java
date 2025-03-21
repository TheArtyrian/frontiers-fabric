package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.CreeperModelBlock;
import net.artyrian.frontiers.block.custom.models.MagmaCubeModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.MagmaCubeModelBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.RotationPropertyHelper;

// Parts of this were exported from Blockbench then adapted to my own code
@Environment(EnvType.CLIENT)
public class MagmaCubeModelBlockEntityRenderer implements BlockEntityRenderer<MagmaCubeModelBlockEntity>
{
    private final ModelPart body;
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/magma_cube_model.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    public MagmaCubeModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.body = getModel().createModel();
    }

    @Override
    public void render(MagmaCubeModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof MagmaCubeModelBlock) h = RotationPropertyHelper.toDegrees(blockState.get(MagmaCubeModelBlock.ROTATION));

        matrices.translate(0.5F, 0.0F, 0.5F);
        matrices.scale(1.5F, 1.5F, 1.5F);
        matrices.translate(0.0F, 1.48F, 0.0F);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(h));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        body.render(matrices, vertexConsumer, light, overlay, Colors.WHITE);

        matrices.pop();
    }

    private static TexturedModelData getModel()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, -9.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 0).cuboid(0.0F, -12.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 1).cuboid(0.0F, -10.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(36, 14).cuboid(0.0F, -8.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(36, 23).cuboid(0.0F, -7.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 4).cuboid(-4.0F, -5.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 5).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(-4.0F, -3.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 7).cuboid(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(4, 4).cuboid(-4.0F, -12.0F, 0.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(4, 5).cuboid(-4.0F, -10.0F, 0.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 10).cuboid(-4.0F, -8.0F, 0.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(4, 6).cuboid(-4.0F, -7.0F, 0.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
