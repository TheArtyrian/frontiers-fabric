package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.mixin_intf.HoglinMixInterface;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.hoglin.HoglinAi;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(HoglinAi.class)
public abstract class HoglinBrainMixin
{
    @Inject(method = "isNearPlayer", at = @At(value = "RETURN"), cancellable = true)
    private static void why(Hoglin hoglin, CallbackInfoReturnable<Boolean> cir)
    {
        boolean truffled = ((HoglinMixInterface)hoglin).frontiers_1_21x$isTruffled();

        if (truffled) cir.setReturnValue(true);
        else cir.setReturnValue(cir.getReturnValue());
    }
}
