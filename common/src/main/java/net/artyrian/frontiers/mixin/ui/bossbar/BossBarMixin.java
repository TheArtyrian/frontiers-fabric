package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.mixin_intf.bossbar.BossbarIntf;
import net.minecraft.sounds.Music;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.UUID;

@Mixin(BossEvent.class)
public abstract class BossBarMixin implements BossbarIntf
{
    @Shadow public abstract UUID getId();

    @Unique @Nullable
    private Music FRONTIERS$bossBarMusic = null;

    @Override @Nullable
    public Music frontiers_1_21x$getBossBarMusic()
    {
        return FRONTIERS$bossBarMusic;
    }

    @Override
    public void frontiers_1_21x$setBossBarMusic(@Nullable Music music)  { this.FRONTIERS$bossBarMusic = music; }
}
