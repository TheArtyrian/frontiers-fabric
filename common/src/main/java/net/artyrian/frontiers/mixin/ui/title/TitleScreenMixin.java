package net.artyrian.frontiers.mixin.ui.title;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends ScreenMixin
{
    @Unique
    @Final
    private final ResourceLocation APRIL_FOOLS_TEX = ResourceLocation.withDefaultNamespace("textures/block/dirt.png");

    @Inject(method = "renderPanorama", at = @At("HEAD"), cancellable = true)
    private void renderAprilFoolsPano(GuiGraphics context, float delta, CallbackInfo ci)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            context.setColor(0.3F, 0.3F, 0.3F, 1.0F);
            context.blit(APRIL_FOOLS_TEX, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 64, 64);
            context.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
