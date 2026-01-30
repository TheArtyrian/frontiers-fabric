package net.artyrian.frontiers.mixin.entity.iron_golem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IronGolem.class)
public class IronGolemMixinFabric
{
    /**
     * Prevents Iron Golems from attacking Crawlers / tamed Hoglins in their target goal.
     */
    @ModifyReturnValue(method = "method_6498", at = @At("RETURN"))
    private static boolean frontiersAlsoAttemptForTargeter(boolean original, @Local(argsOnly = true) LivingEntity entity)
    {
        return original
                && !entity.getType().is(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                && !(entity instanceof HoglinIntf hog && hog.frontiers_1_21x$isTruffled());
    }
}
