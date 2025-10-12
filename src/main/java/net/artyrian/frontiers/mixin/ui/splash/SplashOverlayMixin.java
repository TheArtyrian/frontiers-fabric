package net.artyrian.frontiers.mixin.ui.splash;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.resource.ResourceReload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

@Mixin(SplashOverlay.class)
public class SplashOverlayMixin
{
    @Mutable
    @Shadow @Final private static IntSupplier BRAND_ARGB;

    @Mutable
    @Shadow @Final private static Identifier LOGO;

    @Unique private static final int SPECIFICATIONS_PURPLE = 0x373363;
    @Unique private static final int SPOOKY_ORANGE = ColorHelper.Argb.getArgb(255, 196, 67, 13);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void logoColorChangeIfPossible(MinecraftClient client, ResourceReload monitor, Consumer exceptionHandler, boolean reloading, CallbackInfo ci)
    {
        if (Frontiers.EVENTS.IS_HALLOWEEN)
        {
            boolean mono = MinecraftClient.getInstance().options.getMonochromeLogo().getValue();

            if (!mono) BRAND_ARGB = () -> SPOOKY_ORANGE;
        }
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            boolean mono = MinecraftClient.getInstance().options.getMonochromeLogo().getValue();

            if (!mono) BRAND_ARGB = () -> SPECIFICATIONS_PURPLE;
            LOGO = Identifier.of(Frontiers.MOD_ID, "textures/gui/joke/mojnay.png");
        }
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIFFIIII)V",
                    ordinal = 0)
    )
    private void renderpass1(DrawContext instance, Identifier texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, Operation<Void> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            int a = (int)((double)instance.getScaledWindowWidth() * 0.225);
            int b = (int)((double)instance.getScaledWindowHeight() * -0.05);
            double e = Math.min(instance.getScaledWindowWidth() * 0.75, instance.getScaledWindowHeight()) * 0.25;
            int nn = (int)((e * 4.0));
            instance.drawTexture(LOGO, a, b, nn, nn, 0.0F, 0.0F, 480, 480, 480, 480);
        }
        else
        {
            original.call(instance, texture, x, y, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        }
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIFFIIII)V",
                    ordinal = 1)
    )
    private void renderpass2(DrawContext instance, Identifier texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, Operation<Void> original)
    {
        if (!Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            original.call(instance, texture, x, y, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        }
    }
}
