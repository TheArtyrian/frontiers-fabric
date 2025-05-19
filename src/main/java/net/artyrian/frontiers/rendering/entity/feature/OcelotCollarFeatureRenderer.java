package net.artyrian.frontiers.rendering.entity.feature;

import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

/** A simple reimplementation of {@link net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer the Vanilla cat collar renderer}, built for the Ocelot using the Frontiers mixin code.*/
@Environment(EnvType.CLIENT)
public class OcelotCollarFeatureRenderer extends FeatureRenderer<OcelotEntity, OcelotEntityModel<OcelotEntity>>
{
    private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/cat/cat_collar.png");
    private final OcelotEntityModel<OcelotEntity> model;

    public OcelotCollarFeatureRenderer(FeatureRendererContext<OcelotEntity, OcelotEntityModel<OcelotEntity>> context, EntityModelLoader loader)
    {
        super(context);
        this.model = new OcelotEntityModel<>(loader.getModelPart(EntityModelLayers.OCELOT));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, OcelotEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
    {
        if (((OcelotMixIntf)entity).frontiers$isTamed())
        {
            int m = ((OcelotMixIntf)entity).frontiers$getCollarColor().getEntityColor();
            render(this.getContextModel(), this.model, SKIN, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, tickDelta, animationProgress, headPitch, headPitch, m);
        }
    }
}
