package net.artyrian.frontiers.definition.entity.renderer.armor;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.function.Function;

public class WitchHatModel extends Model
{
    public static ResourceLocation TEXTURE = Frontiers.id("textures/entity/witch_hat.png");
    private final ModelPart hat;
    private final ModelPart root;

    public WitchHatModel()
    {
        super(RenderType::entityTranslucent);
        this.root = getTexturedModelData().bakeRoot();
        this.hat = root.getChild("hat");
    }

    public static LayerDefinition getTexturedModelData()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition hat = modelPartData.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0188F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.9688F, 0.0F));
        PartDefinition hat2 = hat.addOrReplaceChild("hat2", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -5.5F, -5.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, 0.0313F, 2.0F, -0.0524F, 0.0F, 0.0262F));
        PartDefinition hat3 = hat2.addOrReplaceChild("hat3", CubeListBuilder.create().texOffs(0, 23).addBox(-3.75F, -5.5F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.1047F, 0.0F, 0.0524F));
        PartDefinition hat4 = hat3.addOrReplaceChild("hat4", CubeListBuilder.create().texOffs(16, 28).addBox(-1.5F, -4.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.0F, -3.0F, -1.0F, -0.2094F, 0.0F, 0.1047F));

        return LayerDefinition.create(modelData, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color)
    {
        this.hat.render(matrices, vertices, light, overlay, color);
    }
}
