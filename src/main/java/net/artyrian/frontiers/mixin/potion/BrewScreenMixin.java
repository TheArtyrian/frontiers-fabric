package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.item.ModItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.BrewingStandScreenHandler;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(BrewingStandScreenHandler.PotionSlot.class)
public abstract class BrewScreenMixin
{
    @Inject(method = "matches", at = @At("RETURN"), cancellable = true)
    private static void gg(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(cir.getReturnValue() || stack.isOf(ModItem.LIGHTNING_IN_A_BOTTLE));
    }
}
