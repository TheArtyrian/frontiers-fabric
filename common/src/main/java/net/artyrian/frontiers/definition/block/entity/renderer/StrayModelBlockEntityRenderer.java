package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.model.EntityModelBlock;
import net.artyrian.frontiers.definition.block.custom.model.StrayModelBlock;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.SkeletonModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.StrayModelBlockEntity;
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
public class StrayModelBlockEntityRenderer implements BlockEntityRenderer<StrayModelBlockEntity>, VectorIBlockIntf<StrayModelBlockEntity>
{
    private final ModelPart body;
    private final ModelPart inner;
    private final ModelPart clothes;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/stray_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public StrayModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = SkeletonModelBlockEntityRenderer.getModel().bakeRoot();
        this.inner = this.body.getChild(SkeletonModelBlockEntityRenderer.INNER);
        this.clothes = this.body.getChild(SkeletonModelBlockEntityRenderer.CLOTHES);
    }

    @Override
    public void render(StrayModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();

        BlockState blockState = entity.getBlockState();
        boolean properState = (blockState.getBlock() instanceof EntityModelBlock);
        float h = 0.0F;
        if (properState) h = RotationSegment.convertToDegrees(blockState.getValue(EntityModelBlock.ROTATION));

        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(h));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        inner.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        if (properState && !blockState.getValue(StrayModelBlock.MODEL_SHEARED)) clothes.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    @Override
    public AABB getVectorLibIntfRenderBox(StrayModelBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 2.5, pos.getZ() + 1.5);
    }
}
