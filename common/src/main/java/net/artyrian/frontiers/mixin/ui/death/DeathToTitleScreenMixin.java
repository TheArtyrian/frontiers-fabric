package net.artyrian.frontiers.mixin.ui.death;

import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.TitleConfirmScreen.class)
public abstract class DeathToTitleScreenMixin extends ScreenMixin
{
    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void doDifferentCragsDeathBG(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (this.minecraft.player != null && ((PlayerMixInterface)this.minecraft.player).frontiers_1_21x$killedByCragsMonster())
        {
            context.fillRenderType(FRRegistries.RenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
