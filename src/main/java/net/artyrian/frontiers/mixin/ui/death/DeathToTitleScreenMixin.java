package net.artyrian.frontiers.mixin.ui.death;

import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.TitleScreenConfirmScreen.class)
public abstract class DeathToTitleScreenMixin extends ScreenMixin
{
    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void doDifferentCragsDeathBG(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (this.client.player != null && ((PlayerMixInterface)this.client.player).frontiers_1_21x$killedByCragsMonster())
        {
            context.fillWithLayer(ModRenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
