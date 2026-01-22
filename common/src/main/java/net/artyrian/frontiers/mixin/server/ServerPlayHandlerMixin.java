package net.artyrian.frontiers.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerPlayHandlerMixin
{
    @Shadow public ServerPlayer player;

    @WrapOperation(method = "cleanUp", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    private void hashtagRipBozo(PlayerList instance, Component message, boolean overlay, Operation<Void> original)
    {
        if (this.player.getStringUUID().equals(Frontiers.CONTRIB_IDS.get("Artyrian")))
        {
            original.call(instance, Component.translatable("multiplayer.player.left_bad", this.player.getDisplayName()).withStyle(ChatFormatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}
