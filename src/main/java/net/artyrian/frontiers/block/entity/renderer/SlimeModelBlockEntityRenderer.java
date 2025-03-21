package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.CreeperModelBlock;
import net.artyrian.frontiers.block.custom.models.SlimeModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.SlimeModelBlockEntity;
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
public class SlimeModelBlockEntityRenderer implements BlockEntityRenderer<SlimeModelBlockEntity>
{
    private final ModelPart body;
    private final ModelPart outer;
    private final ModelPart inner;
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/slime_model.png");
    private static final RenderLayer LAYER_INNER = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    private static final RenderLayer LAYER_OUTER = RenderLayer.getEntityTranslucent(TEXTURE);

    public SlimeModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.body = getModel().createModel();
        this.inner = this.body.getChild("inner");
        this.outer = this.body.getChild("outer");
    }

    @Override
    public void render(SlimeModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof SlimeModelBlock) h = RotationPropertyHelper.toDegrees(blockState.get(SlimeModelBlock.ROTATION));

        matrices.translate(0.5F, 0.0F, 0.5F);
        matrices.scale(1.5F, 1.5F, 1.5F);
        matrices.translate(0.0F, 1.48F, 0.0F);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(h));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER_INNER);
        inner.render(matrices, vertexConsumer, light, overlay, Colors.WHITE);

        VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(LAYER_OUTER);
        outer.render(matrices, vertexConsumer2, light, overlay, Colors.WHITE);

        matrices.pop();
    }

    private static TexturedModelData getModel()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData outer = modelPartData.addChild("inner", ModelPartBuilder.create().uv(0, 20).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(24, 20).cuboid(-3.3F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 24).cuboid(1.3F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 28).cuboid(0.0F, -4.0F, -3.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData inner = modelPartData.addChild("outer", ModelPartBuilder.create().uv(24, 0).cuboid(1.0F, -9.0F, -4.0F, 3.0F, 8.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.0F, -9.0F, 0.0F, 8.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
