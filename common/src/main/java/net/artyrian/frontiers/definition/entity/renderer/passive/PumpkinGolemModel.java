package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class PumpkinGolemModel<T extends PumpkinGolemEntity> extends HierarchicalModel<T>
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

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition Head = modelPartData.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -22.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

        PartDefinition stem2_r1 = Head.addOrReplaceChild("stem2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -16.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition LeftLeg = modelPartData.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(48, 6).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 18.0F, 0.0F));

        PartDefinition RightLeg = modelPartData.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(48, 6).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 18.0F, 0.0F));

        PartDefinition LeftArm = modelPartData.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 6).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 14.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition RightArm = modelPartData.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 6).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 14.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(PumpkinGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (this.is_asleep)
        {
            this.head.xRot = 0.4F;
            this.rightLeg.xRot = 0.0F;
            this.leftLeg.xRot = 0.0F;
        }
        else
        {
            this.head.xRot = 0.0F;
            this.rightLeg.xRot = -1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
            this.leftLeg.xRot = 1.5F * Mth.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        }
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
    }

    public void prepareMobModel(PumpkinGolemEntity entity, float limbAngle, float limbDistance, float tickDelta)
    {
        if (!entity.isGolemAsleep())
        {
            if (entity.getPickTicks() > 0)
            {
                int tick = entity.getPickTicks();
                this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)tick - tickDelta, 10.0F);
                this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave((float)tick - tickDelta, 10.0F);

                this.rightArm.x = RIGHT_PIVOTX_PICK;
                this.leftArm.x = LEFT_PIVOTX_PICK;
                this.rightArm.zRot = RIGHT_ROLL_PICK;
                this.leftArm.zRot = LEFT_ROLL_PICK;
            }
            else
            {
                this.rightArm.xRot = 0.0F;
                this.leftArm.xRot = 0.0F;

                this.rightArm.x = RIGHT_PIVOTX;
                this.leftArm.x = LEFT_PIVOTX;
                this.rightArm.zRot = RIGHT_ROLL;
                this.leftArm.zRot = LEFT_ROLL;
            }
        }
        else
        {
            this.rightArm.xRot = 0.0F;
            this.leftArm.xRot = 0.0F;

            this.rightArm.x = RIGHT_PIVOTX;
            this.leftArm.x = LEFT_PIVOTX;
            this.rightArm.zRot = RIGHT_ROLL;
            this.leftArm.zRot = LEFT_ROLL;
        }
    }
}