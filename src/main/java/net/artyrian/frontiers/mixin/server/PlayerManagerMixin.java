package net.artyrian.frontiers.mixin.server;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin
{
    @WrapOperation(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Z)V"))
    private void makeFunOfArtyrianBecauseIHateHim(
            PlayerManager instance,
            Text message,
            boolean overlay,
            Operation<Void> original,
            @Local(argsOnly = true) ServerPlayerEntity player,
            @Local(ordinal = 0) String string
            )
    {
        if (player.getUuidAsString().equals(Frontiers.CONTRIB_IDS.get("Artyrian")))
        {
            MutableText mutableText;
            if (player.getGameProfile().getName().equalsIgnoreCase(string))
            {
                mutableText = Text.translatable("multiplayer.player.joined_bad", player.getDisplayName());
            }
            else
            {
                mutableText = Text.translatable("multiplayer.player.joined_bad.renamed", player.getDisplayName(), string);
            }

            original.call(instance, mutableText.formatted(Formatting.GOLD), overlay);
        }
        else original.call(instance, message, overlay);
    }
}
