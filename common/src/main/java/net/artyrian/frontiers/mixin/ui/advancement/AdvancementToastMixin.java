package net.artyrian.frontiers.mixin.ui.advancement;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.misc.ModAdvancementFrame;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(AdvancementToast.class)
public abstract class AdvancementToastMixin
{
    // Color : 1269478
    @ModifyVariable(method = "draw", at = @At(value = "STORE"), ordinal = 0)
    public int changeColor(int original, @Local(ordinal = 0) DisplayInfo advancementDisplay)
    {
        if (advancementDisplay.getType() == ModAdvancementFrame.FRONTIERS_ADV) return 1269478;
        return original;
    }

    @Inject(method = "draw", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTFIELD,
            target = "Lnet/minecraft/client/toast/AdvancementToast;soundPlayed:Z",
            shift = At.Shift.AFTER)
    )
    public void playSoundExtra(GuiGraphics context, ToastComponent manager, long startTime, CallbackInfoReturnable<Toast.Visibility> cir, @Local(ordinal = 0) DisplayInfo advancementDisplay)
    {
        if (advancementDisplay.getType() == ModAdvancementFrame.FRONTIERS_ADV)
        {
            manager.getMinecraft().getSoundManager().play(SimpleSoundInstance.forUI(ModSounds.UI_TOAST_FRONTIERS, 1.0F, 1.0F));
        }
    }
}
