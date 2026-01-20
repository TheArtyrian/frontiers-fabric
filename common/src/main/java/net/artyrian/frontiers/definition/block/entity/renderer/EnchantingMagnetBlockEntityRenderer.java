package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.EnchantingMagnetBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Mth;

public class EnchantingMagnetBlockEntityRenderer implements BlockEntityRenderer<EnchantingMagnetBlockEntity>
{
    public static final Material MAG_TEXTURE = new Material(
            TextureAtlas.LOCATION_BLOCKS, Frontiers.id("entity/enchanting_magnet")
    );

    private final ModelPart cube;

    public EnchantingMagnetBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.cube = getTexturedModelData().bakeRoot();
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bone = modelPartData.addOrReplaceChild("bone",
                CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -13.0F, 4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(8.0F, 8.0F, -8.0F)
        );
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void render(EnchantingMagnetBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        if (entity.getExp() > 0)
        {
            matrices.pushPose();

            float scalesize = entity.getSizePercent();

            matrices.translate(0.5F, 0.6F, 0.5F);
            matrices.scale(scalesize, scalesize, scalesize);

            matrices.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp(tickDelta, entity.getLastRot(), entity.getRot()) * 10.0F));

            VertexConsumer vexCon = MAG_TEXTURE.buffer(vertexConsumers, RenderType::entityTranslucent);
            this.cube.render(matrices, vexCon, 255, OverlayTexture.NO_OVERLAY, CommonColors.WHITE);

            matrices.popPose();
        }
    }
}
