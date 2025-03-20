package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.CreeperModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.CreeperModelBlockEntity;
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
public class CreeperModelBlockEntityRenderer implements BlockEntityRenderer<CreeperModelBlockEntity>
{
    private final ModelPart body;
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/creeper_model.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);

    public CreeperModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.body = getModel().createModel();
    }

    // Yeah I know this is bad, kill me why dontcha :3
    @Override
    public void render(CreeperModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof CreeperModelBlock) h = RotationPropertyHelper.toDegrees(blockState.get(CreeperModelBlock.ROTATION));

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
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(16, 16).cuboid(-12.0F, -19.0F, 6.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-12.0F, -27.0F, 4.0F, 4.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-12.0F, -7.0F, 10.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 23).cuboid(-7.0F, -7.0F, 10.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-12.0F, -7.0F, 2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F))
                .uv(32, 23).cuboid(-7.0F, -7.0F, 3.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(48, 0).cuboid(-8.0F, -20.0F, 6.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F))
                .uv(26, 4).cuboid(-8.0F, -26.0F, 5.0F, 4.0F, 6.0F, 6.0F, new Dilation(0.0F))
                .uv(42, 26).cuboid(-8.0F, -11.0F, 7.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 24.0F, -8.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
