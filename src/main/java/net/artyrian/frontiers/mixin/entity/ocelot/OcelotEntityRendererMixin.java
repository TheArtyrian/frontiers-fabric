package net.artyrian.frontiers.mixin.entity.ocelot;

import net.artyrian.frontiers.mixin.entity.LivingEntityRenderMixin;
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
public abstract class OcelotEntityRendererMixin<T extends OcelotEntity, M extends OcelotEntityModel<T>> extends LivingEntityRenderMixin<T, M>
{
    @Inject(method = "<init>", at = @At("TAIL"))
    public void frontiersCollarAppender(EntityRendererFactory.Context context, CallbackInfo ci)
    {

    }
}
