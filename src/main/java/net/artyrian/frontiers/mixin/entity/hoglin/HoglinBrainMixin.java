package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.mixin_interfaces.HoglinMixInterface;
import net.minecraft.entity.mob.HoglinBrain;
import net.minecraft.entity.mob.HoglinEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(HoglinBrain.class)
public abstract class HoglinBrainMixin
{
    @Inject(method = "isNearPlayer", at = @At(value = "RETURN"), cancellable = true)
    private static void why(HoglinEntity hoglin, CallbackInfoReturnable<Boolean> cir)
    {
        boolean truffled = ((HoglinMixInterface)hoglin).frontiers_1_21x$isTruffled();

        if (truffled) cir.setReturnValue(true);
        else cir.setReturnValue(cir.getReturnValue());
    }
}
