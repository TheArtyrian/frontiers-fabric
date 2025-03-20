package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.EndermanModelBlock;
import net.artyrian.frontiers.block.custom.models.WitherSkeletonModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.EndermanModelBlockEntity;
import net.artyrian.frontiers.block.entity.entity_model.SkeletonModelBlockEntity;
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
public class EndermanModelBlockEntityRenderer implements BlockEntityRenderer<EndermanModelBlockEntity>
{
    private final ModelPart body;
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/enderman_model.png");
    private static final Identifier TEXTURE_EYES = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/enderman_eyes_model.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    private static final RenderLayer LAYER_EYES = RenderLayer.getEyes(TEXTURE_EYES);

    public EndermanModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.body = getModel().createModel();
    }

    @Override
    public void render(EndermanModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof EndermanModelBlock) h = RotationPropertyHelper.toDegrees(blockState.get(EndermanModelBlock.ROTATION));

        matrices.translate(0.5F, 1.5F, 0.5F);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(h));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        body.render(matrices, vertexConsumer, light, overlay, Colors.WHITE);

        VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(LAYER_EYES);
        body.render(matrices, vertexConsumer2, 255, overlay, Colors.WHITE);

        matrices.pop();
    }

    private static TexturedModelData getModel()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(56, 0).mirrored().cuboid(1.0F, -31.0F, 1.0F, 2.0F, 30.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(56, 0).cuboid(-3.0F, -31.0F, 1.0F, 2.0F, 30.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body_r1 = bb_main.addChild("body_r1", ModelPartBuilder.create().uv(32, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -28.0F, 3.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData headpart_r1 = bb_main.addChild("headpart_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.3398F, -4.8039F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-4.0F, -4.8398F, -4.8039F, 8.0F, 8.0F, 8.0F, new Dilation(-0.5F)), ModelTransform.of(0.0F, -36.0F, -5.0F, 0.48F, 0.0F, 0.0F));

        ModelPartData rightArm_r1 = bb_main.addChild("rightArm_r1", ModelPartBuilder.create().uv(56, 0).cuboid(-6.0F, -1.2058F, -0.3038F, 2.0F, 30.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 0).mirrored().cuboid(4.0F, -1.2058F, -0.3038F, 2.0F, 30.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -37.0F, -3.0F, -0.1309F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
