package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.models.BlazeModelBlock;
import net.artyrian.frontiers.block.custom.models.CreeperModelBlock;
import net.artyrian.frontiers.block.entity.entity_model.BlazeModelBlockEntity;
import net.artyrian.frontiers.block.entity.entity_model.CreeperModelBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.BlazeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.RotationPropertyHelper;

import java.util.Arrays;

// Inferred from java code help me
@Environment(EnvType.CLIENT)
public class BlazeModelBlockEntityRenderer implements BlockEntityRenderer<BlazeModelBlockEntity>
{
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart[] rods;

    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/blaze_model_burnout.png");
    private static final Identifier TEXTURE_ALIVE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mob_model/blaze_model.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    private static final RenderLayer LAYER_ALIVE = RenderLayer.getEntityCutoutNoCull(TEXTURE_ALIVE);

    public BlazeModelBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.root = BlazeEntityModel.getTexturedModelData().createModel();
        this.head = root.getChild(EntityModelPartNames.HEAD);
        this.rods = new ModelPart[12];
        Arrays.setAll(this.rods, index -> root.getChild("part" + index));
    }

    @Override
    public void render(BlazeModelBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();

        BlockState blockState = entity.getCachedState();
        float h = 0.0F;
        RenderLayer layertouse = LAYER;
        int truelight = light;
        float deltaset = tickDelta;
        if (blockState.getBlock() instanceof BlazeModelBlock)
        {
            boolean powered = blockState.get(BlazeModelBlock.MODEL_POWERED);
            h = RotationPropertyHelper.toDegrees(blockState.get(BlazeModelBlock.ROTATION));
            layertouse = (powered) ? LAYER_ALIVE : LAYER;
            truelight = (powered) ? 255 : light;
            deltaset = (powered) ? tickDelta : 0.0F;
        }

        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(h));

        setAngles(entity.getAge() + deltaset);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(layertouse);

        head.render(matrices, vertexConsumer, truelight, overlay, Colors.WHITE);
        for (int i = 0; i < this.rods.length; i++)
        {
            this.rods[i].render(matrices, vertexConsumer, truelight, overlay, Colors.WHITE);
        }

        matrices.pop();
    }

    public void setAngles(float animationProgress)
    {
        float f = animationProgress * ((float) Math.PI * -0.1F);

        for (int i = 0; i < 4; i++) {
            this.rods[i].pivotY = -2.0F + MathHelper.cos(((float)(i * 2) + animationProgress) * 0.25F);
            this.rods[i].pivotX = MathHelper.cos(f) * 9.0F;
            this.rods[i].pivotZ = MathHelper.sin(f) * 9.0F;
            f += 1.5F;
        }

        f = (float) (Math.PI / 4) + animationProgress * ((float) Math.PI * 0.03F);

        for (int i = 4; i < 8; i++) {
            this.rods[i].pivotY = 2.0F + MathHelper.cos(((float)(i * 2) + animationProgress) * 0.25F);
            this.rods[i].pivotX = MathHelper.cos(f) * 7.0F;
            this.rods[i].pivotZ = MathHelper.sin(f) * 7.0F;
            f += 1.5F;
        }

        f = 0.47123894F + animationProgress * ((float) Math.PI * -0.05F);

        for (int i = 8; i < 12; i++) {
            this.rods[i].pivotY = 11.0F + MathHelper.cos(((float)i * 1.5F + animationProgress) * 0.5F);
            this.rods[i].pivotX = MathHelper.cos(f) * 5.0F;
            this.rods[i].pivotZ = MathHelper.sin(f) * 5.0F;
            f += 1.5F;
        }
    }
}
