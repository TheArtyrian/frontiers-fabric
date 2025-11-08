package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarImpl;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(BossBar.class)
public abstract class BossBarMixin implements BossBarImpl
{
    @Shadow public abstract UUID getUuid();

    @Unique @Nullable
    private MusicSound FRONTIERS$bossBarMusic = null;

    @Override @Nullable
    public MusicSound frontiers_1_21x$getBossBarMusic()
    {
        return FRONTIERS$bossBarMusic;
    }

    @Override
    public void frontiers_1_21x$setBossBarMusic(@Nullable MusicSound music)  { this.FRONTIERS$bossBarMusic = music; }
}
