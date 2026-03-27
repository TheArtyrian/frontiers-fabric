package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.mixin_intf.GuiIntf;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixinNF
{
    @Inject(method = "renderHealthLevel", at = @At("TAIL"))
    private void renderSanity(GuiGraphics context, CallbackInfo ci)
    {
        ((GuiIntf)this).frontiersML$accessibleFromAllRenderSanity(context);
    }
}
