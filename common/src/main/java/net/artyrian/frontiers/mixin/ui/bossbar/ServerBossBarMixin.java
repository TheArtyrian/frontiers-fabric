package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(ServerBossEvent.class)
public abstract class ServerBossBarMixin extends BossBarMixin
{
    @Shadow private boolean visible;
    @Shadow @Final private Set<ServerPlayer> players;

    @Override
    public void frontiers_1_21x$setBossBarMusic(@Nullable Music music)
    {
        if (this.frontiers_1_21x$getBossBarMusic() != music)
        {
            super.frontiers_1_21x$setBossBarMusic(music);
            if (this.visible)
            {
                BossBarMusicS2CPacket packet = new BossBarMusicS2CPacket(this.getUuid(), music);
                for (ServerPlayer serverPlayerEntity : this.players)
                {
                    serverPlayerEntity.connection.send(packet);
                }
            }
        }
    }
}
