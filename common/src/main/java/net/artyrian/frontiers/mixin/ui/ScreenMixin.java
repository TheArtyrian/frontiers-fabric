package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.FrontiersConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin
{
    @Shadow public int height;
    @Shadow public int width;
    @Shadow protected Font textRenderer;
    @Shadow @Nullable protected Minecraft client;

    @Unique
    @Final
    private final ResourceLocation APRIL_FOOLS_TEX = ResourceLocation.withDefaultNamespace("textures/block/dirt.png");

    @Inject(method = "renderInGameBackground", at = @At("HEAD"), cancellable = true)
    private void oldAlphaBG(GuiGraphics context, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doUniqueInventoryBlur())
        {
            context.fillGradient(0, 0, this.width, this.height, 0xc01b1b39, 0xc01b1b39);
            ci.cancel();
        }
    }

    @Inject(method = "renderPanoramaBackground", at = @At("HEAD"), cancellable = true)
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
