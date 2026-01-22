package net.artyrian.frontiers.mixin.entity.ocelot;

import net.artyrian.frontiers.mixin.entity.LivingEntityRenderMixin;
import net.artyrian.frontiers.rendering.entity.feature.OcelotCollarFeatureRenderer;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.OcelotRenderer;
import net.minecraft.world.entity.animal.Ocelot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotRenderer.class)
public abstract class OcelotEntityRendererMixin extends LivingEntityRenderMixin<Ocelot, OcelotModel<Ocelot>>
{
    @Inject(method = "<init>", at = @At("TAIL"))
    public void frontiersCollarAppender(EntityRendererProvider.Context context, CallbackInfo ci)
    {
        OcelotRenderer thiss = ((OcelotRenderer)((Object)this));
        this.addFeature(new OcelotCollarFeatureRenderer(thiss, context.getModelSet()));
    }
}
