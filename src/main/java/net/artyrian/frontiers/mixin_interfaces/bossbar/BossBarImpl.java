package net.artyrian.frontiers.mixin_interfaces.bossbar;

import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;

public interface BossBarImpl
{
    @Nullable
    MusicSound frontiers_1_21x$getBossBarMusic();
    void frontiers_1_21x$setBossBarMusic(@Nullable MusicSound music);
}
