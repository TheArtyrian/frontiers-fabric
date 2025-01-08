package net.artyrian.frontiers.mixin.entity.hoglin;

import net.minecraft.entity.mob.HoglinBrain;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoglinBrain.class)
public abstract class HoglinBrainMixin
{
    @Inject(method = "isNearPlayer", at = @At(value = "RETURN"), cancellable = true)
    private static void why(HoglinEntity hoglin, CallbackInfoReturnable<Boolean> cir)
    {
        NbtCompound checker = new NbtCompound();
        hoglin.writeNbt(checker);
        boolean hasTruff = checker.contains("BredWithTruffle", NbtElement.BYTE_TYPE) && checker.getBoolean("BredWithTruffle");

        if (hasTruff) cir.setReturnValue(true);
        else cir.setReturnValue(cir.getReturnValue());
    }
}
