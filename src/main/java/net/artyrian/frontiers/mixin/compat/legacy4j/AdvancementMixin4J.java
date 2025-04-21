package net.artyrian.frontiers.mixin.compat.legacy4j;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModAdvancementFrame;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.transformer.meta.MixinInner;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.mixin.transformer.meta.MixinProxy;
import org.spongepowered.asm.mixin.transformer.meta.MixinRenamed;

//@Pseudo(AdvancementToastMixin.class)
public class AdvancementMixin4J
{
    //@Inject(method = "render", at = @At("HEAD"))
    //private void x(DrawContext guiGraphics, ToastManager toastComponent, long l, CallbackInfoReturnable<Toast.Visibility> cir, CallbackInfo ci)
    //{
    //    Frontiers.LOGGER.info("hi");
    //}

    // Color : 1269478
    //@ModifyVariable(method = "render", at = @At(value = "STORE"))
    //public int changeColor(int original, @Local(ordinal = 0) AdvancementDisplay advancementDisplay)
    //{
    //    if (advancementDisplay.getFrame() == ModAdvancementFrame.FRONTIERS_ADV)
    //    {
    //        return 1269478;
    //    }
    //    return original;
    //}
}