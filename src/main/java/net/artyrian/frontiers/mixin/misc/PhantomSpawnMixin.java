package net.artyrian.frontiers.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PhantomSpawner.class)
@Debug(export = true)
public abstract class PhantomSpawnMixin
{
    @ModifyExpressionValue(method = "spawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/LocalDifficulty;isHarderThan(F)Z"))
    private boolean alsoHasNoAntiPhantomStuff(boolean original, @Local ServerPlayerEntity player)
    {
        return original && !player.hasStatusEffect(ModStatusEffects.WELL_RESTED);
    }
}
