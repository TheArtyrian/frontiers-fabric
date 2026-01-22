package net.artyrian.frontiers.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PhantomSpawner.class)
@Debug(export = true)
public abstract class PhantomSpawnMixin
{
    @ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/DifficultyInstance;isHarderThan(F)Z"))
    private boolean alsoHasNoAntiPhantomStuff(boolean original, @Local ServerPlayer player)
    {
        return original && !player.hasEffect(ModStatusEffects.WELL_RESTED);
    }
}
