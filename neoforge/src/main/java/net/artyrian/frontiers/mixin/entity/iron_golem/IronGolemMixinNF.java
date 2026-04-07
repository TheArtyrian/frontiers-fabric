package net.artyrian.frontiers.mixin.entity.iron_golem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IronGolem.class)
public class IronGolemMixinNF
{
    /**
     * Prevents Iron Golems from attacking Crawlers / tamed Hoglins in their target goal.
     */
    @ModifyReturnValue(method = "lambda$registerGoals$0", at = @At("RETURN"))
    private static boolean frontiersAlsoAttemptForTargeter(boolean original, @Local(argsOnly = true) LivingEntity entity)
    {
        return MixinShortcuts.ironGolemDefer(original, entity);
    }
}
