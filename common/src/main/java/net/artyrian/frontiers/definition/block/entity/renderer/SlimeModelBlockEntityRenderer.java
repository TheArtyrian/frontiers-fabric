package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.EntityModelBlock;
import net.artyrian.frontiers.definition.block.entity.EntityModelBlockEntity;
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
public class SlimeModelBlockEntityRenderer implements BlockEntityRenderer<EntityModelBlockEntity>
{
    private final ModelPart body;
    private final ModelPart outer;
    private final ModelPart inner;
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/slime_model.png");
    private static final RenderType LAYER_INNER = RenderType.entityCutoutNoCull(TEXTURE);
    private static final RenderType LAYER_OUTER = RenderType.entityTranslucent(TEXTURE);

    public SlimeModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.body = getModel().bakeRoot();
        this.inner = this.body.getChild("inner");
        this.outer = this.body.getChild("outer");
    }

    @Override
    public void render(EntityModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
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

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER_INNER);
        inner.render(matrices, vertexConsumer, light, overlay, CommonColors.WHITE);

        VertexConsumer vertexConsumer2 = vertexConsumers.getBuffer(LAYER_OUTER);
        outer.render(matrices, vertexConsumer2, light, overlay, CommonColors.WHITE);

        matrices.popPose();
    }

    private static LayerDefinition getModel()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition outer = modelPartData.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 20).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 20).addBox(-3.3F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 24).addBox(1.3F, -7.0F, -3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 28).addBox(0.0F, -4.0F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition inner = modelPartData.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(24, 0).addBox(1.0F, -9.0F, -4.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -9.0F, 0.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 32);
    }
}
