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
    public static String CLOTHES_RARM = "rightArmClothes";
    public static String CLOTHES_LARM = "leftArmClothes";
    public static String CLOTHES_HEAD = "headClothes";
    public static String INNER = "inner";

    private final ModelPart body;
    private final ModelPart inner;
    private final ModelPart clothes;
    private final ModelPart clothesHead;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/skeleton_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public SkeletonModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = SkeletonModelBlockEntityRenderer.getModel(false).bakeRoot();
        this.inner = this.body.getChild(SkeletonModelBlockEntityRenderer.INNER);
        this.clothes = this.body.getChild(SkeletonModelBlockEntityRenderer.CLOTHES);
        this.clothesHead = this.body.getChild(SkeletonModelBlockEntityRenderer.CLOTHES_HEAD);
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
        clothesHead.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    public static LayerDefinition getModel(boolean do_mushrooms)
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition inner = partdefinition.addOrReplaceChild(INNER, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).mirror().addBox(1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 16).addBox(-3.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));
        PartDefinition head_r1 = inner.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
        PartDefinition rightArm_r1 = inner.addOrReplaceChild("rightArm_r1", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition leftArm_r1 = inner.addOrReplaceChild("leftArm_r1", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition clothes = partdefinition.addOrReplaceChild(CLOTHES, CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(0, 48).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(0, 48).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 23.0F, 0.0F));
        PartDefinition rightArmOuter_r1 = clothes.addOrReplaceChild(CLOTHES_RARM, CubeListBuilder.create().texOffs(40, 48).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-4.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition leftArmOuter_r1 = clothes.addOrReplaceChild(CLOTHES_LARM, CubeListBuilder.create().texOffs(40, 48).mirror().addBox(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(4.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition headCloth = partdefinition.addOrReplaceChild(CLOTHES_HEAD, CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition headOuter_r1 = headCloth.addOrReplaceChild("headOuter_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
        PartDefinition head = headCloth.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        if (do_mushrooms)
        {
            PartDefinition mushrooms_r1 = head.addOrReplaceChild("mushrooms_r1", CubeListBuilder.create().texOffs(50, 27).addBox(-3.0F, -5.0F, 0.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 3.0F, -1.5708F, 0.0F, 2.3562F));
            PartDefinition mushrooms_r2 = head.addOrReplaceChild("mushrooms_r2", CubeListBuilder.create().texOffs(50, 27).addBox(-3.0F, -5.0F, 0.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 3.0F, -1.5708F, 0.0F, 0.7854F));
            PartDefinition mushrooms_r3 = head.addOrReplaceChild("mushrooms_r3", CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.5F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.5F, 3.0F, 0.0F, -0.7854F, 0.0F));
            PartDefinition mushrooms_r4 = head.addOrReplaceChild("mushrooms_r4", CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.5F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.5F, 3.0F, 0.0F, 0.7854F, 0.0F));
            PartDefinition mushrooms_r5 = head.addOrReplaceChild("mushrooms_r5", CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -2.5F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.5F, -3.0F, 0.0F, 0.7854F, 0.0F));
            PartDefinition mushrooms_r6 = head.addOrReplaceChild("mushrooms_r6", CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -2.5F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.5F, -3.0F, 0.0F, -0.7854F, 0.0F));
        }

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public AABB getVectorLibIntfRenderBox(SkeletonModelBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 2.5, pos.getZ() + 1.5);
    }
}
