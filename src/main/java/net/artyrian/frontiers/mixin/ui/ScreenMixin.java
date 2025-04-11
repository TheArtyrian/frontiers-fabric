package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.FrontiersConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin
{
    @Shadow public int height;
    @Shadow public int width;
    @Shadow protected TextRenderer textRenderer;
    @Shadow @Nullable protected MinecraftClient client;

    @Inject(method = "renderInGameBackground", at = @At("HEAD"), cancellable = true)
    private void oldAlphaBG(DrawContext context, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doUniqueInventoryBlur())
        {
            context.fillGradient(0, 0, this.width, this.height, 0xc01b1b39, 0xc01b1b39);
            ci.cancel();
        }
    }
}
