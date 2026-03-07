package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.model.EntityModelBlock;
import net.artyrian.frontiers.definition.block.entity.model.EndermanModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.PhantomModelBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
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
public class PhantomModelBlockEntityRenderer implements BlockEntityRenderer<PhantomModelBlockEntity>, VectorIBlockIntf<PhantomModelBlockEntity>
{
    private final ModelPart body;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/phantom_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public PhantomModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = getModel().bakeRoot();
    }

    @Override
    public void render(PhantomModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
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

        matrices.popPose();
    }

    public static LayerDefinition getModel()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -2.0F, -6.5F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 17.0F, 2.0F));
        PartDefinition wing0 = body.addOrReplaceChild("wing0", CubeListBuilder.create().texOffs(23, 12).addBox(0.0F, 0.0F, 1.5F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -8.0F, 0.0F, 0.0F, 0.4363F));
        PartDefinition wingtip0 = wing0.addOrReplaceChild("wingtip0", CubeListBuilder.create().texOffs(16, 24).addBox(0.0F, 0.0F, 1.5F, 13.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        PartDefinition wing1 = body.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(23, 12).mirror().addBox(-6.0F, 0.0F, 1.5F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -2.0F, -8.0F, 0.0F, 0.0F, -0.4363F));
        PartDefinition wingtip1 = wing1.addOrReplaceChild("wingtip1", CubeListBuilder.create().texOffs(16, 24).mirror().addBox(-13.0F, 0.0F, 1.5F, 13.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -1.487F, -3.5905F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -7.0F, 0.3491F, 0.0F, 0.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(3, 20).addBox(-2.0F, -0.6339F, 1.3595F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, -0.4363F, 0.0F, 0.0F));
        PartDefinition tailtip = tail.addOrReplaceChild("tailtip", CubeListBuilder.create().texOffs(4, 29).addBox(-1.0F, -1.1491F, 0.9642F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 6.0F, -0.4363F, 0.0F, 0.0F));
        PartDefinition string = partdefinition.addOrReplaceChild("string", CubeListBuilder.create().texOffs(27, 1).addBox(0.5F, -15.0F, 0.5F, 0.0F, 6.0F, -1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public AABB getVectorLibIntfRenderBox(PhantomModelBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 0.5, pos.getY() - 1.5F, pos.getZ() - 0.5, pos.getX() + 1.5, pos.getY() + 1.5F, pos.getZ() + 1.5);
    }
}
