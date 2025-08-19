package net.artyrian.frontiers.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayHandlerMixin
{
    @Shadow public ServerPlayerEntity player;

    @WrapOperation(method = "cleanUp", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    private void hashtagRipBozo(PlayerManager instance, Text message, boolean overlay, Operation<Void> original)
    {
        if (this.player.getUuidAsString().equals(Frontiers.CONTRIB_IDS.get("Artyrian")))
        {
            original.call(instance, Text.translatable("multiplayer.player.left_bad", this.player.getDisplayName()).formatted(Formatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}
