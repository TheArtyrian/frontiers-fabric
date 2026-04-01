package net.artyrian.frontiers.mixin_intf.bossbar;

import net.artyrian.frontiers.definition.networking.packet.client.ClientboundBossBarMusicPacket;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;

public interface BossbarHudIntf
{
    @Nullable
    Music frontiers_1_21x$getFirstAvailableMusic();
    void frontiers_1_21x$handleFrontiersMusicPacket(ClientboundBossBarMusicPacket packet);
}
