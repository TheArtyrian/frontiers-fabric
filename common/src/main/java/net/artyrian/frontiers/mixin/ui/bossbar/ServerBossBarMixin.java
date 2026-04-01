package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.definition.networking.packet.client.ClientboundBossBarMusicPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Set;

@Mixin(ServerBossEvent.class)
public abstract class ServerBossBarMixin extends BossBarMixin
{
    @Shadow private boolean visible;
    @Shadow @Final private Set<ServerPlayer> players;
    @Shadow public abstract Collection<ServerPlayer> getPlayers();

    @Override
    public void frontiers_1_21x$setBossBarMusic(@Nullable Music music)
    {
        if (this.frontiers_1_21x$getBossBarMusic() != music)
        {
            super.frontiers_1_21x$setBossBarMusic(music);
            if (this.visible)
            {
                for (ServerPlayer serverPlayerEntity : players)
                {
                    serverPlayerEntity.connection.send(new ClientboundBossBarMusicPacket(this.getId(), music));
                }
            }
        }
    }

    @Inject(method = "addPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.AFTER))
    private void frontiers$alsoUpdatePacketerSend(ServerPlayer player, CallbackInfo ci)
    {
        Music target = this.frontiers_1_21x$getBossBarMusic();
        if (target != null)
        {
            player.connection.send(new ClientboundBossBarMusicPacket(this.getId(), target));
        }
    }
}
