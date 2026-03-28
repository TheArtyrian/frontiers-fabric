package net.vertisoft.vectorlib.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.lolololol.VectorJoinMsg;
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
        if (VectorLib.SYSTEM.JOIN_MSGS.containsKey(player.getStringUUID()))
        {
            VectorJoinMsg msg = VectorLib.SYSTEM.JOIN_MSGS.get(player.getStringUUID());
            boolean formerName = player.getGameProfile().getName().equalsIgnoreCase(string);

            Style style = message.getStyle();
            if (formerName)
            {
                if (msg.renamed() != null) message = Component.translatable(msg.renamed(), player.getDisplayName()).withStyle(style);
            }
            else
            {
                if (msg.join() != null) message = Component.translatable(msg.join(), player.getDisplayName()).withStyle(style);
            }

            if (msg.color() != null) message = message.copy().withColor(msg.color());
        }
        original.call(instance, message, overlay);
    }
}
