package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.HoglinMixInterface;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(HoglinEntityRenderer.class)
public abstract class HoglinRenderMixin
{
    @Unique private static final Identifier CHILL_TEX = Identifier.of(Frontiers.MOD_ID, "textures/entity/hoglin/hoglin_tame.png");
    @Final @Shadow private static Identifier TEXTURE;

    @Inject(method = "getTexture*", at = @At("RETURN"), cancellable = true)
    public void getTexture(HoglinEntity hoglinEntity, CallbackInfoReturnable<Identifier> cir)
    {
        boolean truffled = ((HoglinMixInterface)hoglinEntity).frontiers_1_21x$isTruffled();
        if (truffled) cir.setReturnValue(CHILL_TEX);
        else cir.setReturnValue(TEXTURE);
    }
}
