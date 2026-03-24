package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import java.util.List;

public class PumpkinGolemEntityRenderer extends MobRenderer<PumpkinGolemEntity, PumpkinGolemModel<PumpkinGolemEntity>>
{
    private static final List<ResourceLocation> TEXTURES_OFF = List.of(
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_1.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_2.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_3.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_4.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_5.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_6.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_7.png")

    );

    private static final List<ResourceLocation> TEXTURES_GLOW = List.of(
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_1_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_2_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_3_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_4_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_5_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_6_on.png"),
            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_7_on.png")

    );

    public PumpkinGolemEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new PumpkinGolemModel<>(context.bakeLayer(FRRegistries.ModelLayers.PUMPKIN_GOLEM)), 0.5F);
        this.addLayer(new PumpkinGolemEyesRenderer<>(this));
        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(PumpkinGolemEntity livingEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i)
    {
        PumpkinGolemModel<PumpkinGolemEntity> model = this.getModel();
        model.is_asleep = livingEntity.isGolemAsleep();
        //model.tick_delta = g;
        //model.pick_ticks = livingEntity.getPickTicks();
        //if (model.pick_ticks > 0) Frontiers.LOGGER.info("got");
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(PumpkinGolemEntity golem)
    {
        int style = Math.clamp(golem.getGolemStyle(), PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);

        if (golem.isGolemAsleep()) return TEXTURES_OFF.get(style);
        return TEXTURES_GLOW.get(style);
    }

    protected void setupRotations(PumpkinGolemEntity golem, PoseStack matrixStack, float f, float g, float h, float i)
    {
        super.setupRotations(golem, matrixStack, f, g, h, i);
        if (!golem.isGolemAsleep())
        {
            if (!((double)golem.walkAnimation.speed() < 0.01))
            {
                float j = 9.0F;
                float jhalf = j / 2.0F;
                float jquart = jhalf / 2.0F;
                float jaddHalf = (j - 1.0F) / 2.0F;

                float k = golem.walkAnimation.position(h) + jaddHalf;
                float l = (Math.abs(k % j - jhalf) - jquart) / jquart;
                matrixStack.mulPose(Axis.ZP.rotationDegrees(jhalf * l));
            }
        }
    }
}
