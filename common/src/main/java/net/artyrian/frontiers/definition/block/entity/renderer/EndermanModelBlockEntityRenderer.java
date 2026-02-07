package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.model.EntityModelBlock;
import net.artyrian.frontiers.definition.block.entity.model.BoggedModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.EndermanModelBlockEntity;
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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.AABB;
import net.vertisoft.vectorlib.agnostic.neoforge_stitching.VectorIBlockIntf;

// Parts of this were exported from Blockbench then adapted to my own code
public class EndermanModelBlockEntityRenderer implements BlockEntityRenderer<EndermanModelBlockEntity>, VectorIBlockIntf<EndermanModelBlockEntity>
{
    private final ModelPart body;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/enderman_model.png");
    private static final ResourceLocation TEXTURE_EYES = Frontiers.id("textures/entity/mob_model/enderman_eyes_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);
    private static final RenderType LAYER_EYES = RenderType.eyes(TEXTURE_EYES);

    public EndermanModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = getModel().bakeRoot();
    }

    @Override
    public void render(EndermanModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();

        BlockState blockState = entity.getBlockState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof EntityModelBlock) h = RotationSegment.convertToDegrees(blockState.getValue(EntityModelBlock.ROTATION));

        matrices.translate(0.5F, 1.5F, 0.5F);

        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(h));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        body.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(LAYER_EYES);
        body.render(matrices, vertexConsumer2, 255, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    private static LayerDefinition getModel()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition bb_main = modelPartData.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(1.0F, -31.0F, 1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(56, 0).addBox(-3.0F, -31.0F, 1.0F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body_r1 = bb_main.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 3.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition headpart_r1 = bb_main.addOrReplaceChild("headpart_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.3398F, -4.8039F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-4.0F, -4.8398F, -4.8039F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -36.0F, -5.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition rightArm_r1 = bb_main.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(56, 0).addBox(-6.0F, -1.2058F, -0.3038F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).mirror().addBox(4.0F, -1.2058F, -0.3038F, 2.0F, 30.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -37.0F, -3.0F, -0.1309F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public AABB getVectorLibIntfRenderBox(EndermanModelBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 3.5, pos.getZ() + 1.5);
    }
}
