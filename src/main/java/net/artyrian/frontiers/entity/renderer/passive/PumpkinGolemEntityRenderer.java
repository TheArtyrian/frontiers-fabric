package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.rendering.ModModelLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.EndermanEyesFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.List;

public class PumpkinGolemEntityRenderer extends MobEntityRenderer<PumpkinGolemEntity, PumpkinGolemModel<PumpkinGolemEntity>>
{
    private static final List<Identifier> TEXTURES_OFF = List.of(
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_1.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_2.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_3.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_4.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_5.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_6.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_7.png")

    );

    private static final List<Identifier> TEXTURES_GLOW = List.of(
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_1_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_2_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_3_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_4_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_5_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_6_on.png"),
            Identifier.of(Frontiers.MOD_ID, "textures/entity/pumpkin_golem/pumpkin_golem_7_on.png")

    );

    public PumpkinGolemEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new PumpkinGolemModel<>(context.getPart(ModModelLayers.PUMPKIN_GOLEM)), 0.5F);
        this.addFeature(new PumpkinGolemEyesRenderer<>(this));
        this.shadowRadius = 0.8F;
    }

    @Override
    public void render(PumpkinGolemEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
    {
        PumpkinGolemModel<PumpkinGolemEntity> model = this.getModel();
        model.is_asleep = livingEntity.isGolemAsleep();
        //model.tick_delta = g;
        //model.pick_ticks = livingEntity.getPickTicks();
        //if (model.pick_ticks > 0) Frontiers.LOGGER.info("got");
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(PumpkinGolemEntity golem)
    {
        int style = Math.clamp(golem.getGolemStyle(), PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);

        if (golem.isGolemAsleep()) return TEXTURES_OFF.get(style);
        return TEXTURES_GLOW.get(style);
    }

    protected void setupTransforms(PumpkinGolemEntity golem, MatrixStack matrixStack, float f, float g, float h, float i)
    {
        super.setupTransforms(golem, matrixStack, f, g, h, i);
        if (!golem.isGolemAsleep())
        {
            if (!((double)golem.limbAnimator.getSpeed() < 0.01))
            {
                float j = 9.0F;
                float jhalf = j / 2.0F;
                float jquart = jhalf / 2.0F;
                float jaddHalf = (j - 1.0F) / 2.0F;

                float k = golem.limbAnimator.getPos(h) + jaddHalf;
                float l = (Math.abs(k % j - jhalf) - jquart) / jquart;
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(jhalf * l));
            }
        }
    }
}
