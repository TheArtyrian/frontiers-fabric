package net.artyrian.frontiers.definition.entity.renderer.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class SlimeSlabsHatModel extends Model
{
    public static ResourceLocation TEXTURE = Frontiers.id("textures/entity/slimeslabs_hat.png");
    private final ModelPart hat;
    private final ModelPart root;

    public SlimeSlabsHatModel()
    {
        super(RenderType::entityTranslucent);
        this.root = getTexturedModelData().bakeRoot();
        this.hat = root.getChild("hat");
    }

    public static LayerDefinition getTexturedModelData()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hat2 = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 8).addBox(-7.0F, -7.1009F, -8.491F, 14.0F, 12.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 34).addBox(-8.0F, -9.1009F, -9.491F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 1.5F));

        PartDefinition tip1 = hat2.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -2.1839F, -3.4233F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0509F, -1.491F, -0.2182F, 0.0F, 0.0F));
        PartDefinition tip2 = tip1.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(26, 52).addBox(-2.0F, -2.0553F, -1.2805F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.75F, 0.0F, -0.48F, 0.0F, 0.0F));
        PartDefinition tip3 = tip2.addOrReplaceChild("tip3", CubeListBuilder.create().texOffs(42, 52).addBox(-1.05F, -2.1262F, -0.0604F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.7F, 0.4F, -0.3491F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, int color)
    {
        this.hat.render(matrices, vertices, light, overlay, color);
    }
}
