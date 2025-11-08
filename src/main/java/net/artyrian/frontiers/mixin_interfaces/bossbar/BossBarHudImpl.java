package net.artyrian.frontiers.mixin_interfaces.bossbar;

import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;

public interface BossBarHudImpl
{
    @Nullable
    MusicSound frontiers_1_21x$getFirstAvailableMusic();
    void frontiers_1_21x$handleFrontiersMusicPacket(BossBarMusicS2CPacket packet);
}
