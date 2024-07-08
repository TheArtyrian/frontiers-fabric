package net.artyrian.frontiers.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingRodItem.class)
public abstract class FishingRodMixin
{
    private int rod_tier = 0;

    @Inject(method = "<init>", at = @At("HEAD"))
    private void FishingRodItem(Item.Settings settings, CallbackInfo info)
    {
        this.rod_tier = 1;
        System.out.println(rod_tier);
    }
}
