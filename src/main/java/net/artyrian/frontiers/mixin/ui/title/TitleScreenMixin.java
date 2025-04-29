package net.artyrian.frontiers.mixin.ui.title;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.Identifier;
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
    private final Identifier APRIL_FOOLS_TEX = Identifier.ofVanilla("textures/block/dirt.png");

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
