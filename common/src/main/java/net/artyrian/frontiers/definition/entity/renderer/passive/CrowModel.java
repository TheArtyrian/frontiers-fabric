package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class CrowModel extends HierarchicalModel<CrowEntity>
{
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart wingL;
    private final ModelPart wingR;
    private final ModelPart legL;
    private final ModelPart legR;
    private final ModelPart tail;

    public CrowModel(ModelPart root)
    {
        this.root = root;
        this.head = root.getChild("Head");
        this.body = root.getChild("Body");
        this.wingL = this.body.getChild("wingL");
        this.wingR = this.body.getChild("wingR");
        this.legL = root.getChild("LegL");
        this.legR = root.getChild("LegR");
        this.tail = root.getChild("Tail");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition modelData = new MeshDefinition();

        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition Head = modelPartData.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 9).addBox(-1.0F, -2.8F, -1.8F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -4.0F));

        PartDefinition head_overlay = Head.addOrReplaceChild("head_overlay", CubeListBuilder.create().texOffs(20, 0).addBox(-1.5F, -3.8F, -2.7F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition beak1_r1 = Head.addOrReplaceChild("beak1_r1", CubeListBuilder.create().texOffs(8, 17).addBox(-0.5F, -0.8F, -1.7F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, 0.1745F, 0.0F, 0.0F));
        PartDefinition Body = modelPartData.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 20.5F, -1.0F));
        PartDefinition body_r1 = Body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -5.5F, -2.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 1.0472F, 0.0F, 0.0F));
        PartDefinition wingL = Body.addOrReplaceChild("wingL", CubeListBuilder.create(), PartPose.offset(1.0F, -2.0F, -1.5F));
        PartDefinition wing0_r1 = wingL.addOrReplaceChild("wing0_r1", CubeListBuilder.create().texOffs(10, 9).addBox(0.0F, -0.1F, -2.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.309F, 0.0F, 0.0F));
        PartDefinition wingR = Body.addOrReplaceChild("wingR", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -1.5F));
        PartDefinition wing1_r1 = wingR.addOrReplaceChild("wing1_r1", CubeListBuilder.create().texOffs(10, 9).addBox(-1.0F, -0.1F, -2.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.309F, 0.0F, 0.0F));
        PartDefinition LegL = modelPartData.addOrReplaceChild("LegL", CubeListBuilder.create().texOffs(12, 6).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 22.0F, -1.0F));
        PartDefinition LegR = modelPartData.addOrReplaceChild("LegR", CubeListBuilder.create().texOffs(12, 6).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 22.0F, -1.0F));
        PartDefinition Tail = modelPartData.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));
        PartDefinition tail_r1 = Tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-1.5F, 0.1F, 0.2F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, 1.7453F, 0.0F, 0.0F));

        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(CrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
        this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.zRot = 0.0F;
        this.head.x = 0.0F;
        this.body.x = 0.0F;
        this.tail.x = 0.0F;
        this.wingR.x = -1.0F;
        this.wingL.x = 1.0F;

        this.wingL.zRot = 0.0F;
        this.wingL.y = -2.0F;
        this.wingL.xRot = 0.0F;
        this.wingR.zRot = 0.0F;
        this.wingR.y = -2.0F;
        this.wingR.xRot = 0.0F;

        float h = ageInTicks * 0.3F;

        this.head.y = 19.0F + h;
        this.tail.y = 20.0F + h;
        this.body.y = 20.5F + h;
        this.tail.xRot = 0.0F + Mth.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;

        if (entity.isFlying())
        {
            this.wingL.xRot = -1.0472F;
            this.wingR.xRot = -1.0472F;

            this.wingL.zRot = -0.0873F - ageInTicks;
            this.wingL.y = -2.0F + h;
            this.wingR.zRot = -0.0873F + ageInTicks;
            this.wingR.y = -2.0F + h;
        }
    }

    public void prepareMobModel(CrowEntity entity, float limbAngle, float limbDistance, float tickDelta)
    {
       this.body.xRot = 0.0F;
       this.wingL.xRot = 0.0F;
       this.wingL.yRot = 0.0F;
       this.wingR.xRot = 0.0F;
       this.wingR.yRot = 0.0F;
       this.legL.xRot = 0.0F;
       this.legR.xRot = 0.0F;
       this.legL.y = 22.0F;
       this.legR.y = 22.0F;
       this.legL.zRot = 0.0F;
       this.legR.zRot = 0.0F;

       if (entity.isFlying())
       {
           this.legL.xRot = -0.0299F;
           this.legR.xRot = -0.0299F;

           this.legL.xRot += (float) (Math.PI * 2.0 / 9.0);
           this.legR.xRot += (float) (Math.PI * 2.0 / 9.0);
       }
    }
}
