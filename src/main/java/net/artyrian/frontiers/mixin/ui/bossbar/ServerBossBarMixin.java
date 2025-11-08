package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(ServerBossBar.class)
public abstract class ServerBossBarMixin extends BossBarMixin
{
    @Shadow private boolean visible;
    @Shadow @Final private Set<ServerPlayerEntity> players;

    @Override
    public void frontiers_1_21x$setBossBarMusic(@Nullable MusicSound music)
    {
        if (this.frontiers_1_21x$getBossBarMusic() != music)
        {
            super.frontiers_1_21x$setBossBarMusic(music);
            if (this.visible)
            {
                BossBarMusicS2CPacket packet = new BossBarMusicS2CPacket(this.getUuid(), music);
                for (ServerPlayerEntity serverPlayerEntity : this.players)
                {
                    serverPlayerEntity.networkHandler.sendPacket(packet);
                }
            }
        }
    }
}
