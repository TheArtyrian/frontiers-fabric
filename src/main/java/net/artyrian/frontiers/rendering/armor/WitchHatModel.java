package net.artyrian.frontiers.rendering.armor;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class WitchHatModel extends Model
{
    public static Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID,"textures/entity/witch_hat.png");
    private final ModelPart hat;
    private final ModelPart root;

    public WitchHatModel()
    {
        super(RenderLayer::getEntityTranslucent);
        this.root = getTexturedModelData().createModel();
        this.hat = root.getChild("hat");
    }

    public static TexturedModelData getTexturedModelData()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData hat = modelPartData.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -2.0188F, -5.0F, 10.0F, 2.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.9688F, 0.0F));
        ModelPartData hat2 = hat.addChild("hat2", ModelPartBuilder.create().uv(0, 12).cuboid(-5.0F, -5.5F, -5.0F, 7.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(1.75F, 0.0313F, 2.0F, -0.0524F, 0.0F, 0.0262F));
        ModelPartData hat3 = hat2.addChild("hat3", ModelPartBuilder.create().uv(0, 23).cuboid(-3.75F, -5.5F, -3.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -1.0F, -0.1047F, 0.0F, 0.0524F));
        ModelPartData hat4 = hat3.addChild("hat4", ModelPartBuilder.create().uv(16, 28).cuboid(-1.5F, -4.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.25F)), ModelTransform.of(-1.0F, -3.0F, -1.0F, -0.2094F, 0.0F, 0.1047F));

        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color)
    {
        this.hat.render(matrices, vertices, light, overlay, color);
    }
}
