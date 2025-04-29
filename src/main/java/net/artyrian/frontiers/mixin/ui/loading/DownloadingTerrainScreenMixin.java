package net.artyrian.frontiers.mixin.ui.loading;

import net.artyrian.frontiers.misc.ModWorldEntryReason;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadingTerrainScreen.class)
public abstract class DownloadingTerrainScreenMixin extends ScreenMixin
{
    @Shadow @Final private DownloadingTerrainScreen.WorldEntryReason worldEntryReason;

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void renderModDimensionBG(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (this.worldEntryReason.equals(ModWorldEntryReason.CRAGS))
        {
            context.fillWithLayer(ModRenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
