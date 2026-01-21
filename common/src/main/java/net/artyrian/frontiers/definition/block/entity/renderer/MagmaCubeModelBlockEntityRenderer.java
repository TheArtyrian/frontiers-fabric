package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.model.EntityModelBlock;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.MagmaCubeModelBlockEntity;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;

// Parts of this were exported from Blockbench then adapted to my own code
public class MagmaCubeModelBlockEntityRenderer implements BlockEntityRenderer<MagmaCubeModelBlockEntity>
{
    private final ModelPart body;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/magma_cube_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public MagmaCubeModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = getModel().bakeRoot();
    }

    @Override
    public void render(MagmaCubeModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();

        BlockState blockState = entity.getBlockState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof EntityModelBlock) h = RotationSegment.convertToDegrees(blockState.getValue(EntityModelBlock.ROTATION));

        matrices.translate(0.5F, 0.0F, 0.5F);
        matrices.scale(1.5F, 1.5F, 1.5F);
        matrices.translate(0.0F, 1.48F, 0.0F);

        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(h));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        body.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    private static LayerDefinition getModel()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 0).addBox(0.0F, -12.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(8, 1).addBox(0.0F, -10.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 14).addBox(0.0F, -8.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 23).addBox(0.0F, -7.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 6).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(4, 4).addBox(-4.0F, -12.0F, 0.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(4, 5).addBox(-4.0F, -10.0F, 0.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 10).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(4, 6).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }
}
