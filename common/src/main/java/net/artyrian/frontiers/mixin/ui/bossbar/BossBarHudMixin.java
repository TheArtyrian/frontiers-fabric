package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.mixin_intf.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_intf.bossbar.BossBarImpl;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.sounds.Music;
import net.minecraft.world.BossEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.UUID;

@Mixin(BossHealthOverlay.class)
public abstract class BossBarHudMixin implements BossBarHudImpl
{
    @Shadow @Final Map<UUID, LerpingBossEvent> events;

    @Override @Nullable
    public Music frontiers_1_21x$getFirstAvailableMusic()
    {
        if (!this.events.isEmpty())
        {
            BossBarImpl implier;
            for (BossEvent bossBar : this.events.values())
            {
                implier = (BossBarImpl)bossBar;
                if (implier.frontiers_1_21x$getBossBarMusic() != null)
                {
                    return implier.frontiers_1_21x$getBossBarMusic();
                }
            }
        }

        return null;
    }

    @Override
    public void frontiers_1_21x$handleFrontiersMusicPacket(BossBarMusicS2CPacket packet)
    {
        UUID uuid = packet.getBossBarUUID();
        Music sound = packet.getMusic();
        LerpingBossEvent bar = (this.events.get(uuid));

        if (bar != null)
        {
            ((BossBarImpl)bar).frontiers_1_21x$setBossBarMusic(sound);
        }
    }
}
