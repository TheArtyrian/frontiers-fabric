package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Blame neoforge for this crap btw
@Mixin(FishingHook.class)
public class FishingBobberMixinFabric
{
    @ModifyExpressionValue(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean invalidHalterMainHand(boolean original, @Local(ordinal = 0) ItemStack itemStack)
    {
        return MixinShortcuts.fishingBobberReroute(original, itemStack, (FishingHook)(Object)this);
    }

    @ModifyExpressionValue(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 1))
    private boolean invalidHalterOffHand(boolean original, @Local(ordinal = 1) ItemStack itemStack2)
    {
        return MixinShortcuts.fishingBobberReroute(original, itemStack2, (FishingHook)(Object)this);
    }
}
