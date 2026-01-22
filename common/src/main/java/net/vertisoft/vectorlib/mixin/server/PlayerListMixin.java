package net.vertisoft.vectorlib.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin
{
    @WrapOperation(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private void vectorLib$makeFunOfArtyrianBecauseIHateHim(
            PlayerList instance,
            Component message,
            boolean overlay,
            Operation<Void> original,
            @Local(argsOnly = true) ServerPlayer player,
            @Local(ordinal = 0) String string
            )
    {
        if (player.getStringUUID().equals(VectorLib.SYSTEM.CONTRIB_IDS.get("Artyrian")))
        {
            MutableComponent mutableText;
            if (player.getGameProfile().getName().equalsIgnoreCase(string))
            {
                mutableText = Component.translatable("multiplayer.vectorlib.player.joined_bad", player.getDisplayName());
            }
            else
            {
                mutableText = Component.translatable("multiplayer.vectorlib.player.joined_bad.renamed", player.getDisplayName(), string);
            }

            original.call(instance, mutableText.withStyle(ChatFormatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}
