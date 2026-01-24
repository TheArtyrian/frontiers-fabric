package net.artyrian.frontiers.definition.entity.renderer.passive;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Ocelot;

/** A simple reimplementation of {@link net.minecraft.client.renderer.entity.layers.CatCollarLayer the Vanilla cat collar renderer}, built for the Ocelot using the Frontiers mixin code.*/
public class OcelotCollarFeatureRenderer extends RenderLayer<Ocelot, OcelotModel<Ocelot>>
{
    private static final ResourceLocation SKIN = ResourceLocation.withDefaultNamespace("textures/entity/cat/cat_collar.png");
    private final OcelotModel<Ocelot> model;

    public OcelotCollarFeatureRenderer(RenderLayerParent<Ocelot, OcelotModel<Ocelot>> context, EntityModelSet loader)
    {
        super(context);
        this.model = new OcelotModel<>(loader.bakeLayer(ModelLayers.OCELOT));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, Ocelot entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
    {
        if (((OcelotMixIntf)entity).frontiers$isTamed())
        {
            int m = ((OcelotMixIntf)entity).frontiers$getCollarColor().getTextureDiffuseColor();
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, SKIN, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headPitch, headPitch, m);
        }
    }
}
