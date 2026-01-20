package net.artyrian.frontiers.definition.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.BlazeModelBlock;
import net.artyrian.frontiers.definition.block.entity.BlazeModelBlockEntity;
import net.minecraft.client.model.BlazeModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Arrays;

// Inferred from java code help me
public class BlazeModelBlockEntityRenderer implements BlockEntityRenderer<BlazeModelBlockEntity>
{
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart[] rods;

    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/mob_model/blaze_model_burnout.png");
    private static final ResourceLocation TEXTURE_ALIVE = Frontiers.id("textures/entity/mob_model/blaze_model.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);
    private static final RenderType LAYER_ALIVE = RenderType.entityCutoutNoCull(TEXTURE_ALIVE);

    public BlazeModelBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.root = BlazeModel.createBodyLayer().bakeRoot();
        this.head = root.getChild(PartNames.HEAD);
        this.rods = new ModelPart[12];
        Arrays.setAll(this.rods, index -> root.getChild("part" + index));
    }

    @Override
    public void render(BlazeModelBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();

        BlockState blockState = entity.getBlockState();
        float h = 0.0F;
        RenderType layertouse = LAYER;
        int truelight = light;
        float deltaset = tickDelta;
        if (blockState.getBlock() instanceof BlazeModelBlock)
        {
            boolean powered = blockState.getValue(BlazeModelBlock.MODEL_POWERED);
            h = RotationSegment.convertToDegrees(blockState.getValue(BlazeModelBlock.ROTATION));
            layertouse = (powered) ? LAYER_ALIVE : LAYER;
            truelight = (powered) ? 255 : light;
            deltaset = (powered) ? tickDelta : 0.0F;
        }

        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
        matrices.mulPose(Axis.YP.rotationDegrees(h));

        setAngles(entity.getAge() + deltaset);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layertouse);

        head.render(matrices, vertexConsumer, truelight, overlay, CommonColors.WHITE);
        for (int i = 0; i < this.rods.length; i++)
        {
            this.rods[i].render(matrices, vertexConsumer, truelight, overlay, CommonColors.WHITE);
        }

        matrices.popPose();
    }

    public void setAngles(float animationProgress)
    {
        float f = animationProgress * ((float) Math.PI * -0.1F);

        for (int i = 0; i < 4; i++) {
            this.rods[i].y = -2.0F + Mth.cos(((float)(i * 2) + animationProgress) * 0.25F);
            this.rods[i].x = Mth.cos(f) * 9.0F;
            this.rods[i].z = Mth.sin(f) * 9.0F;
            f += 1.5F;
        }

        f = (float) (Math.PI / 4) + animationProgress * ((float) Math.PI * 0.03F);

        for (int i = 4; i < 8; i++) {
            this.rods[i].y = 2.0F + Mth.cos(((float)(i * 2) + animationProgress) * 0.25F);
            this.rods[i].x = Mth.cos(f) * 7.0F;
            this.rods[i].z = Mth.sin(f) * 7.0F;
            f += 1.5F;
        }

        f = 0.47123894F + animationProgress * ((float) Math.PI * -0.05F);

        for (int i = 8; i < 12; i++) {
            this.rods[i].y = 11.0F + Mth.cos(((float)i * 1.5F + animationProgress) * 0.5F);
            this.rods[i].x = Mth.cos(f) * 5.0F;
            this.rods[i].z = Mth.sin(f) * 5.0F;
            f += 1.5F;
        }
    }
}
