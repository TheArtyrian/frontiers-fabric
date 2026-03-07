package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.model.EntityModelBlock;
import net.artyrian.frontiers.definition.block.entity.model.BoggedModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.SkeletonModelBlockEntity;
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
public class SkeletonModelBlockEntityRenderer implements BlockEntityRenderer<SkeletonModelBlockEntity>, VectorIBlockIntf<SkeletonModelBlockEntity>
{
    public static String CLOTHES = "clothes";
    public static String INNER = "inner";

    private final ModelPart body;
    private final ModelPart inner;
    private final ModelPart clothes;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/skeleton_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public SkeletonModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = SkeletonModelBlockEntityRenderer.getModel().bakeRoot();
        this.inner = this.body.getChild(SkeletonModelBlockEntityRenderer.INNER);
        this.clothes = this.body.getChild(SkeletonModelBlockEntityRenderer.CLOTHES);
    }

    @Override
    public void render(SkeletonModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();

        BlockState blockState = entity.getBlockState();
        float h = 0.0F;
        if (blockState.getBlock() instanceof EntityModelBlock) h = RotationSegment.convertToDegrees(blockState.getValue(EntityModelBlock.ROTATION));

        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(h));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        inner.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);
        clothes.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    public static LayerDefinition getModel()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition clothes = partdefinition.addOrReplaceChild(CLOTHES, CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(0, 48).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(0, 48).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 23.0F, 0.0F));
        PartDefinition rightArmOuter_r1 = clothes.addOrReplaceChild("rightArmClothes", CubeListBuilder.create().texOffs(40, 48).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition leftArmOuter_r1 = clothes.addOrReplaceChild("leftArmClothes", CubeListBuilder.create().texOffs(40, 48).mirror().addBox(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(4.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
        PartDefinition headOuter_r1 = clothes.addOrReplaceChild("headClothes", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition inner = partdefinition.addOrReplaceChild(INNER, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).mirror().addBox(1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 16).addBox(-3.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));
        PartDefinition rightArm_r1 = inner.addOrReplaceChild("rightArmInner", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition leftArm_r1 = inner.addOrReplaceChild("leftArmInner", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
        PartDefinition head_r1 = inner.addOrReplaceChild("headInner", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public AABB getVectorLibIntfRenderBox(SkeletonModelBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 2.5, pos.getZ() + 1.5);
    }
}
