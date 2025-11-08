package net.artyrian.frontiers.mixin.ui.bossbar;

import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarImpl;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.sound.MusicSound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.UUID;

@Mixin(BossBarHud.class)
public abstract class BossBarHudMixin implements BossBarHudImpl
{
    @Shadow @Final Map<UUID, ClientBossBar> bossBars;

    @Override @Nullable
    public MusicSound frontiers_1_21x$getFirstAvailableMusic()
    {
        if (!this.bossBars.isEmpty())
        {
            BossBarImpl implier;
            for (BossBar bossBar : this.bossBars.values())
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
        MusicSound sound = packet.getMusic();
        ClientBossBar bar = (this.bossBars.get(uuid));

        if (bar != null)
        {
            ((BossBarImpl)bar).frontiers_1_21x$setBossBarMusic(sound);
        }
    }
}
