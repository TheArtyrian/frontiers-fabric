package net.artyrian.frontiers.mixin.client;

import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.misc.ModWorldEntryReason;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin
{
    @Inject(method = "getWorldEntryReason", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void switchToCragsCheck(boolean dead, RegistryKey<World> from, RegistryKey<World> to, CallbackInfoReturnable<DownloadingTerrainScreen.WorldEntryReason> cir)
    {
        if (!dead)
        {
            if (from == ModDimension.CRAGS_LEVEL_KEY || to == ModDimension.CRAGS_LEVEL_KEY)
            {
                cir.setReturnValue(ModWorldEntryReason.CRAGS);
            }
        }
    }
}
