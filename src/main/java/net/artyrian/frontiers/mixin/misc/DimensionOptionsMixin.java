package net.artyrian.frontiers.mixin.misc;

import com.google.common.collect.ImmutableSet;
import net.artyrian.frontiers.dimension.ModDimension;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionOptionsRegistryHolder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Debug(export=true)
@Mixin(DimensionOptionsRegistryHolder.class)
public class DimensionOptionsMixin
{
    @Mutable
    @Shadow @Final private static Set<RegistryKey<DimensionOptions>> VANILLA_KEYS;

    @Mutable
    @Shadow @Final private static int VANILLA_KEY_COUNT;

    // How about you shut up, experimental features screen
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void what(CallbackInfo ci)
    {
        VANILLA_KEYS.add(ModDimension.CRAGS_KEY);

        VANILLA_KEY_COUNT = VANILLA_KEYS.size();
    }

    // SHUT UP SHUT UP SHUT UP
    @Inject(method = "isVanilla", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
    private static void isAlsoFrontiers(RegistryKey<DimensionOptions> key, DimensionOptions dimensionOptions, CallbackInfoReturnable<Boolean> cir)
    {
        if (!cir.getReturnValue())
        {
            if (key == ModDimension.CRAGS_KEY)
            {
                cir.setReturnValue(true);
                //RegistryEntry<DimensionType> registryEntry = dimensionOptions.dimensionTypeEntry();
                //if (registryEntry.matchesKey(ModDimension.CRAGS_TYPE)) return true;
            }
        }
    }
}
