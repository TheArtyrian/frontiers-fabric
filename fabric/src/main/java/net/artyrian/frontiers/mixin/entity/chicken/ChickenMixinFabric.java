package net.artyrian.frontiers.mixin.entity.chicken;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Chicken.class)
public class ChickenMixinFabric
{
    @ModifyReturnValue(method = "method_58366", at = @At("RETURN"))
    private static boolean frontiersCanAlsoFollowGoldenFood(boolean original, @Local(argsOnly = true) ItemStack stack)
    {
        return MixinShortcuts.chickenFood(original, stack);
    }
}
