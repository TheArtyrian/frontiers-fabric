package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.BlazeModelBlock;
import net.artyrian.frontiers.block.custom.models.BoggedModelBlock;
import net.artyrian.frontiers.block.custom.models.SkeletonModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.BoggedModelBlockEntity;
import net.artyrian.frontiers.block.entity.entity_model.SkeletonModelBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BannerBlock;
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
public class BoggedModelBlockEntityRenderer implements BlockEntityRenderer<BoggedModelBlockEntity>
{
    private final ModelPart body;
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/bogged_model.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    public BoggedModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.body = getModel().createModel();
    }

    @Override
    public void render(BoggedModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof BoggedModelBlock) h = RotationPropertyHelper.toDegrees(blockState.get(BoggedModelBlock.ROTATION));

        matrices.translate(0.5F, 1.5F, 0.5F);
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
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -25.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-3.0F, -13.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 16).mirrored().cuboid(1.0F, -13.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(16, 48).cuboid(-4.0F, -25.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(0, 48).cuboid(-3.9F, -13.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(0, 48).mirrored().cuboid(-0.1F, -13.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData leftArmOuter_r1 = bb_main.addChild("leftArmOuter_r1", ModelPartBuilder.create().uv(40, 48).mirrored().cuboid(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)).mirrored(false)
                .uv(40, 16).mirrored().cuboid(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.0F, -24.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData rightArmOuter_r1 = bb_main.addChild("rightArmOuter_r1", ModelPartBuilder.create().uv(40, 48).cuboid(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(40, 16).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData headOuter_r1 = bb_main.addChild("headOuter_r1", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F))
                .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -25.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
