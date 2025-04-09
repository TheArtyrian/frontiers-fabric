package net.artyrian.frontiers.mixin.exp_suppress;

import com.mojang.serialization.Lifecycle;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionOptionsRegistryHolder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export=true)
@Mixin(DimensionOptionsRegistryHolder.class)
public class DimensionOptionsMixin
{
    // Silence, experimental reader, who cares
    //@Inject(method = "getLifecycle", at = @At("HEAD"), cancellable = true)
    //private static void shushChild(RegistryKey<DimensionOptions> key, DimensionOptions dimensionOptions, CallbackInfoReturnable<Lifecycle> cir)
    //{
    //    if (cir.getReturnValue() == Lifecycle.experimental())
    //    {
    //        Frontiers.LOGGER.warn("Found experimental warning on dimension lifecycle - suppressing!");
    //        Frontiers.LOGGER.warn("Dimension ID: {}", key.toString());
    //        cir.setReturnValue(Lifecycle.stable());
    //    }
    //}

    //@ModifyVariable(method = "toConfig", at = @At(value = "STORE"))
    //private Lifecycle replaceLifecycler(Lifecycle value)
    //{
    //    return Lifecycle.stable();
    //}
}
