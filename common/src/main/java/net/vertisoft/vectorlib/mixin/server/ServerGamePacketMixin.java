package net.vertisoft.vectorlib.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketMixin
{
    @Shadow public ServerPlayer player;

    @WrapOperation(method = "removePlayerFromWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private void vectorLib$hashtagRipBozo(PlayerList instance, Component message, boolean overlay, Operation<Void> original)
    {
        if (this.player.getStringUUID().equals(VectorLib.SYSTEM.CONTRIB_IDS.get("Artyrian")))
        {
            original.call(instance, Component.translatable("multiplayer.vectorlib.player.left_bad", this.player.getDisplayName()).withStyle(ChatFormatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}