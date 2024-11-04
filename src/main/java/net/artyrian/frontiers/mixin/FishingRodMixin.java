package net.artyrian.frontiers.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingRodItem.class)
public class FishingRodMixin
{
    @Unique private int rod_tier = 0;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void FishRodMixin(Item.Settings settings, CallbackInfo info)
    {
        System.out.println(rod_tier);
    }
}
