package net.artyrian.frontiers.mixin.entity.ocelot;

import net.artyrian.frontiers.mixin.entity.LivingEntityRenderMixin;
import net.artyrian.frontiers.rendering.entity.feature.OcelotCollarFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.OcelotEntityRenderer;
import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.OcelotEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotEntityRenderer.class)
public abstract class OcelotEntityRendererMixin extends LivingEntityRenderMixin<OcelotEntity, OcelotEntityModel<OcelotEntity>>
{
    @Inject(method = "<init>", at = @At("TAIL"))
    public void frontiersCollarAppender(EntityRendererFactory.Context context, CallbackInfo ci)
    {
        OcelotEntityRenderer thiss = ((OcelotEntityRenderer)((Object)this));
        this.addFeature(new OcelotCollarFeatureRenderer(thiss, context.getModelLoader()));
    }
}
