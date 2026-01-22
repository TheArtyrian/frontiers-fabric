package net.artyrian.frontiers.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerList.class)
public abstract class PlayerManagerMixin
{
    @WrapOperation(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    private void makeFunOfArtyrianBecauseIHateHim(
            PlayerList instance,
            Component message,
            boolean overlay,
            Operation<Void> original,
            @Local(argsOnly = true) ServerPlayer player,
            @Local(ordinal = 0) String string
            )
    {
        if (player.getStringUUID().equals(Frontiers.CONTRIB_IDS.get("Artyrian")))
        {
            MutableComponent mutableText;
            if (player.getGameProfile().getName().equalsIgnoreCase(string))
            {
                mutableText = Component.translatable("multiplayer.player.joined_bad", player.getDisplayName());
            }
            else
            {
                mutableText = Component.translatable("multiplayer.player.joined_bad.renamed", player.getDisplayName(), string);
            }

            original.call(instance, mutableText.withStyle(ChatFormatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}
