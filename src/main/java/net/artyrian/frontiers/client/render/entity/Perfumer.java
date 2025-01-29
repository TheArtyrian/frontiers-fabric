package net.artyrian.frontiers.client.render.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.util.Arm;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class Perfumer<T extends IllagerEntity> extends SinglePartEntityModel<T> implements ModelWithArms, ModelWithHead
{
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart nose;
	private final ModelPart hat;
	private final ModelPart hat2;
	private final ModelPart hat3;
	private final ModelPart hat4;
	private final ModelPart arms;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart rightArm;
	private final ModelPart rightItem;
	private final ModelPart leftArm;
	private final ModelPart leftItem;
	public Perfumer(ModelPart root) {
		this.body = root.getChild("body");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.nose = this.head.getChild("nose");
		this.hat = this.head.getChild("hat");
		this.hat2 = this.hat.getChild("hat2");
		this.hat3 = this.hat2.getChild("hat3");
		this.hat4 = this.hat3.getChild("hat4");
		this.arms = this.body.getChild("arms");
		this.leg0 = this.body.getChild("leg0");
		this.leg1 = this.body.getChild("leg1");
		this.rightArm = this.body.getChild("rightArm");
		this.rightItem = this.rightArm.getChild("rightItem");
		this.leftArm = this.body.getChild("leftArm");
		this.leftItem = this.leftArm.getChild("leftItem");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 20).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 38).cuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(24, 0).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

		ModelPartData hat = head.addChild("hat", ModelPartBuilder.create().uv(64, 0).mirrored().cuboid(-10.0F, -2.02F, 0.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, -8.03F, -5.0F));

		ModelPartData hat2 = hat.addChild("hat2", ModelPartBuilder.create().uv(64, 12).mirrored().cuboid(-2.0F, -5.5F, -5.0F, 7.0F, 4.0F, 7.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-6.75F, 0.03F, 7.0F, -0.0524F, 0.0F, 0.0262F));

		ModelPartData hat3 = hat2.addChild("hat3", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.0F, 0.0F, -0.1047F, 0.0F, 0.0524F));

		ModelPartData hat4 = hat3.addChild("hat4", ModelPartBuilder.create(), ModelTransform.of(0.0F, -3.0F, 0.0F, -0.2094F, 0.0F, 0.1047F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create().uv(44, 22).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(44, 22).cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 38).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

		ModelPartData leg0 = body.addChild("leg0", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

		ModelPartData leg1 = body.addChild("leg1", ModelPartBuilder.create().uv(0, 22).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(40, 46).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData rightItem = rightArm.addChild("rightItem", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, 6.0F, 0.5F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(40, 46).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData leftItem = leftArm.addChild("leftItem", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 7.0F, 1.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color)
	{
		body.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart()
	{
		return null;
	}

	@Override
	public void setArmAngle(Arm arm, MatrixStack matrices)
	{

	}

	@Override
	public ModelPart getHead()
	{
		return null;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch)
	{

	}
}