package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.EnchantingMagnetBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;

public class EnchantingMagnetBlockEntityRenderer implements BlockEntityRenderer<EnchantingMagnetBlockEntity>
{
    public static final SpriteIdentifier MAG_TEXTURE = new SpriteIdentifier(
            SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/enchanting_magnet")
    );

    private final ModelPart cube;

    public EnchantingMagnetBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.cube = getTexturedModelData().createModel();
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone",
                ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, -13.0F, 4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.pivot(8.0F, 8.0F, -8.0F)
        );
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(EnchantingMagnetBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        if (entity.getExp() > 0)
        {
            matrices.push();

            float scalesize = entity.getSizePercent();

            matrices.translate(0.5F, 0.6F, 0.5F);
            matrices.scale(scalesize, scalesize, scalesize);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) MathHelper.lerp(tickDelta, entity.getLastRot(), entity.getRot()) * 10.0F));

            VertexConsumer vexCon = MAG_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntityTranslucent);
            this.cube.render(matrices, vexCon, 255, OverlayTexture.DEFAULT_UV, Colors.WHITE);

            matrices.pop();
        }
    }
}
