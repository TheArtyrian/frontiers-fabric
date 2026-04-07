package net.artyrian.frontiers.mixin.ui.advancement;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.misc.FRAdvancementFrames;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.client.gui.screens.advancements.AdvancementWidgetType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AdvancementWidgetType.class)
public abstract class AdvancementAppearMixin
{
    @Shadow @Final private ResourceLocation boxSprite;

    @Final
    @Shadow
    public static AdvancementWidgetType UNOBTAINED;

    // Blah blah inefficient who care
    @Inject(method = "frameSprite", at = @At("HEAD"), cancellable = true)
    public void kwhat(AdvancementType frame, CallbackInfoReturnable<ResourceLocation> cir)
    {
        if (frame == FRAdvancementFrames.FRONTIERS_ADV)
        {
            if (this.boxSprite == UNOBTAINED.boxSprite())
            {
                cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "advancements/frontier_adv_unobtained"));
            }
            else
            {
                cir.setReturnValue(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "advancements/frontier_adv_obtained"));
            }
        }
    }
}
