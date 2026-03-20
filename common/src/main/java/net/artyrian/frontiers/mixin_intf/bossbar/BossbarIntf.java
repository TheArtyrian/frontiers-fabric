package net.artyrian.frontiers.mixin_intf.bossbar;

import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;

public interface BossbarIntf
{
    @Nullable
    Music frontiers_1_21x$getBossBarMusic();
    void frontiers_1_21x$setBossBarMusic(@Nullable Music music);
}
