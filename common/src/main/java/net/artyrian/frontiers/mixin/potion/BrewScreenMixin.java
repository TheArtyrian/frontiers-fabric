package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(BrewingStandMenu.PotionSlot.class)
public abstract class BrewScreenMixin
{
    @Inject(method = "mayPlaceItem", at = @At("RETURN"), cancellable = true)
    private static void gg(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(cir.getReturnValue() || stack.is(ModItem.LIGHTNING_IN_A_BOTTLE.get()));
    }
}
