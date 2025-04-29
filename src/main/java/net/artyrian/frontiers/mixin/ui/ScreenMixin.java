package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.FrontiersConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
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
    @Shadow protected TextRenderer textRenderer;
    @Shadow @Nullable protected MinecraftClient client;

    @Unique
    @Final
    private final Identifier APRIL_FOOLS_TEX = Identifier.ofVanilla("textures/block/dirt.png");

    @Inject(method = "renderInGameBackground", at = @At("HEAD"), cancellable = true)
    private void oldAlphaBG(DrawContext context, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doUniqueInventoryBlur())
        {
            context.fillGradient(0, 0, this.width, this.height, 0xc01b1b39, 0xc01b1b39);
            ci.cancel();
        }
    }

    @Inject(method = "renderPanoramaBackground", at = @At("HEAD"), cancellable = true)
    private void renderAprilFoolsPano(DrawContext context, float delta, CallbackInfo ci)
    {
        if (Frontiers.IS_APRIL_FOOLS)
        {
            context.setShaderColor(0.3F, 0.3F, 0.3F, 1.0F);
            context.drawTexture(APRIL_FOOLS_TEX, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 64, 64);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
