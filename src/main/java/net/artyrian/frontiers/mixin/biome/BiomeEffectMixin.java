package net.artyrian.frontiers.mixin.biome;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.world.biome.BiomeEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(BiomeEffects.class)
public class BiomeEffectMixin
{
    @ModifyReturnValue(method = "getGrassColor", at = @At("RETURN"))
    private Optional<Integer> alphaGrass(Optional<Integer> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            return Optional.of(0x96ff37);
        }
        return original;
    }

    @ModifyReturnValue(method = "getFoliageColor", at = @At("RETURN"))
    private Optional<Integer> alphaFoliage(Optional<Integer> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            return Optional.of(0x44ef00);
        }
        return original;
    }

    @ModifyReturnValue(method = "getWaterColor", at = @At("RETURN"))
    private int alphaWater(int original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            return 0x003dfc;
        }
        return original;
    }

    @ModifyReturnValue(method = "getWaterFogColor", at = @At("RETURN"))
    private int alphaWaterFog(int original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            return 0x002188;
        }
        return original;
    }
}
