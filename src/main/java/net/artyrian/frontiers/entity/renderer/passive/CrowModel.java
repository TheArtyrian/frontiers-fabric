package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.entity.passive.CrowEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.math.MathHelper;

public class CrowModel extends SinglePartEntityModel<CrowEntity>
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

    public static TexturedModelData getTexturedModelData()
    {
        ModelData modelData = new ModelData();

        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -2.8F, -1.8F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, -4.0F));
        ModelPartData beak1_r1 = Head.addChild("beak1_r1", ModelPartBuilder.create().uv(8, 17).cuboid(-0.5F, -0.8F, -1.7F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, 0.1745F, 0.0F, 0.0F));
        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.5F, -1.0F));
        ModelPartData body_r1 = Body.addChild("body_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -5.5F, -2.5F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.0472F, 0.0F, 0.0F));
        ModelPartData wingL = Body.addChild("wingL", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -2.0F, -1.5F));
        ModelPartData wing0_r1 = wingL.addChild("wing0_r1", ModelPartBuilder.create().uv(10, 9).cuboid(0.0F, -0.1F, -2.3F, 1.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.309F, 0.0F, 0.0F));
        ModelPartData wingR = Body.addChild("wingR", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -2.0F, -1.5F));
        ModelPartData wing1_r1 = wingR.addChild("wing1_r1", ModelPartBuilder.create().uv(10, 9).cuboid(-1.0F, -0.1F, -2.3F, 1.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.309F, 0.0F, 0.0F));
        ModelPartData LegL = modelPartData.addChild("LegL", ModelPartBuilder.create().uv(12, 6).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, 22.0F, -1.0F));
        ModelPartData LegR = modelPartData.addChild("LegR", ModelPartBuilder.create().uv(12, 6).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 22.0F, -1.0F));
        ModelPartData Tail = modelPartData.addChild("Tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, 1.0F));
        ModelPartData tail_r1 = Tail.addChild("tail_r1", ModelPartBuilder.create().uv(0, 15).cuboid(-1.5F, 0.1F, 0.2F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.5F, 0.0F, 1.7453F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(CrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
        this.head.yaw = netHeadYaw * (float) (Math.PI / 180.0);
        this.head.roll = 0.0F;
        this.head.pivotX = 0.0F;
        this.body.pivotX = 0.0F;
        this.tail.pivotX = 0.0F;
        this.wingR.pivotX = -1.0F;
        this.wingL.pivotX = 1.0F;

        this.wingL.roll = 0.0F;
        this.wingL.pivotY = -2.0F;
        this.wingL.pitch = 0.0F;
        this.wingR.roll = 0.0F;
        this.wingR.pivotY = -2.0F;
        this.wingR.pitch = 0.0F;

        float h = ageInTicks * 0.3F;

        this.head.pivotY = 19.0F + h;
        this.tail.pivotY = 20.0F + h;
        this.body.pivotY = 20.5F + h;
        this.tail.pitch = 0.0F + MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;

        if (entity.isInAir())
        {
            this.wingL.pitch = -1.0472F;
            this.wingR.pitch = -1.0472F;

            this.wingL.roll = -0.0873F - ageInTicks;
            this.wingL.pivotY = -2.0F + h;
            this.wingR.roll = -0.0873F + ageInTicks;
            this.wingR.pivotY = -2.0F + h;
        }
    }

    public void animateModel(CrowEntity entity, float limbAngle, float limbDistance, float tickDelta)
    {
       this.body.pitch = 0.0F;
       this.wingL.pitch = 0.0F;
       this.wingL.yaw = 0.0F;
       this.wingR.pitch = 0.0F;
       this.wingR.yaw = 0.0F;
       this.legL.pitch = 0.0F;
       this.legR.pitch = 0.0F;
       this.legL.pivotY = 22.0F;
       this.legR.pivotY = 22.0F;
       this.legL.roll = 0.0F;
       this.legR.roll = 0.0F;

       if (entity.isInAir())
       {
           this.legL.pitch = -0.0299F;
           this.legR.pitch = -0.0299F;

           this.legL.pitch += (float) (Math.PI * 2.0 / 9.0);
           this.legR.pitch += (float) (Math.PI * 2.0 / 9.0);
       }
    }
}
