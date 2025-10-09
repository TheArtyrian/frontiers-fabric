package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.PumpkinGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class PumpkinGolemModel<T extends PumpkinGolemEntity> extends SinglePartEntityModel<T>
{
    private static final float LEFT_ROLL = -0.7854F;
    private static final float RIGHT_ROLL = 0.7854F;
    private static final float LEFT_ROLL_PICK = 0.1745F;
    private static final float RIGHT_ROLL_PICK = -0.1745F;

    private static final float LEFT_PIVOTX = 7.0F;
    private static final float RIGHT_PIVOTX = -7.0F;
    private static final float LEFT_PIVOTX_PICK = 10.0F;
    private static final float RIGHT_PIVOTX_PICK = -10.0F;

    public boolean is_asleep;

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;

    public PumpkinGolemModel(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("Head");
        this.leftLeg = root.getChild("LeftLeg");
        this.rightLeg = root.getChild("RightLeg");
        this.leftArm = root.getChild("LeftArm");
        this.rightArm = root.getChild("RightArm");
    }

    public static TexturedModelData getTexturedModelData()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.0F, -22.0F, 0.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

        ModelPartData stem2_r1 = Head.addChild("stem2_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -16.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData LeftLeg = modelPartData.addChild("LeftLeg", ModelPartBuilder.create().uv(48, 6).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 18.0F, 0.0F));

        ModelPartData RightLeg = modelPartData.addChild("RightLeg", ModelPartBuilder.create().uv(48, 6).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 18.0F, 0.0F));

        ModelPartData LeftArm = modelPartData.addChild("LeftArm", ModelPartBuilder.create().uv(48, 6).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 14.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        ModelPartData RightArm = modelPartData.addChild("RightArm", ModelPartBuilder.create().uv(48, 6).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(PumpkinGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (this.is_asleep)
        {
            this.head.pitch = 0.4F;
            this.rightLeg.pitch = 0.0F;
            this.leftLeg.pitch = 0.0F;
        }
        else
        {
            this.head.pitch = 0.0F;
            this.rightLeg.pitch = -1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
            this.leftLeg.pitch = 1.5F * MathHelper.wrap(limbSwing, 13.0F) * limbSwingAmount;
        }
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
    }

    public void animateModel(PumpkinGolemEntity entity, float limbAngle, float limbDistance, float tickDelta)
    {
        if (!entity.isGolemAsleep())
        {
            if (entity.getPickTicks() > 0)
            {
                int tick = entity.getPickTicks();
                this.rightArm.pitch = -2.0F + 1.5F * MathHelper.wrap((float)tick - tickDelta, 10.0F);
                this.leftArm.pitch = -2.0F + 1.5F * MathHelper.wrap((float)tick - tickDelta, 10.0F);

                this.rightArm.pivotX = RIGHT_PIVOTX_PICK;
                this.leftArm.pivotX = LEFT_PIVOTX_PICK;
                this.rightArm.roll = RIGHT_ROLL_PICK;
                this.leftArm.roll = LEFT_ROLL_PICK;
            }
            else
            {
                this.rightArm.pitch = 0.0F;
                this.leftArm.pitch = 0.0F;

                this.rightArm.pivotX = RIGHT_PIVOTX;
                this.leftArm.pivotX = LEFT_PIVOTX;
                this.rightArm.roll = RIGHT_ROLL;
                this.leftArm.roll = LEFT_ROLL;
            }
        }
        else
        {
            this.rightArm.pitch = 0.0F;
            this.leftArm.pitch = 0.0F;

            this.rightArm.pivotX = RIGHT_PIVOTX;
            this.leftArm.pivotX = LEFT_PIVOTX;
            this.rightArm.roll = RIGHT_ROLL;
            this.leftArm.roll = LEFT_ROLL;
        }
    }
}