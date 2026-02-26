package net.artyrian.frontiers.mixin.ui.splash;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.util.FastColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

@Mixin(LoadingOverlay.class)
public class SplashOverlayMixin
{
    @Mutable @Shadow @Final private static IntSupplier BRAND_BACKGROUND;
    @Mutable @Shadow @Final private static ResourceLocation MOJANG_STUDIOS_LOGO_LOCATION;

    @Unique private static final int SPECIFICATIONS_PURPLE = 0x373363;
    @Unique private static final int SPOOKY_ORANGE = FastColor.ARGB32.color(255, 196, 67, 13);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void logoColorChangeIfPossible(Minecraft client, ReloadInstance monitor, Consumer exceptionHandler, boolean reloading, CallbackInfo ci)
    {
        if (Frontiers.EVENTS.IS_HALLOWEEN)
        {
            boolean mono = Minecraft.getInstance().options.darkMojangStudiosBackground().get();

            if (!mono) BRAND_BACKGROUND = () -> SPOOKY_ORANGE;
        }
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            boolean mono = Minecraft.getInstance().options.darkMojangStudiosBackground().get();

            if (!mono) BRAND_BACKGROUND = () -> SPECIFICATIONS_PURPLE;
            MOJANG_STUDIOS_LOGO_LOCATION = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/mojnay.png");
        }
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIFFIIII)V",
                    ordinal = 0)
    )
    private void renderpass1(GuiGraphics instance, ResourceLocation texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, Operation<Void> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            int a = (int)((double)instance.guiWidth() * 0.225);
            int b = (int)((double)instance.guiHeight() * -0.05);
            double e = Math.min(instance.guiWidth() * 0.75, instance.guiHeight()) * 0.25;
            int nn = (int)((e * 4.0));
            instance.blit(MOJANG_STUDIOS_LOGO_LOCATION, a, b, nn, nn, 0.0F, 0.0F, 480, 480, 480, 480);
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
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIFFIIII)V",
                    ordinal = 1)
    )
    private void renderpass2(GuiGraphics instance, ResourceLocation texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight, Operation<Void> original)
    {
        if (!Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            original.call(instance, texture, x, y, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        }
    }
}
