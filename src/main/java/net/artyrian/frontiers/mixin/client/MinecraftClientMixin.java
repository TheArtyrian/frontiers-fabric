package net.artyrian.frontiers.mixin.client;

import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.mixin_interfaces.bossbar.BossBarImpl;
import net.artyrian.frontiers.sounds.ModMusic;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.Nullables;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{
    @Shadow @Nullable public Screen currentScreen;
    @Shadow @Nullable public ClientPlayerEntity player;
    @Shadow @Final public InGameHud inGameHud;

    @Inject(method = "getMusicType", at = @At("HEAD"), cancellable = true)
    private void frontiers_customMusicEngineInject(CallbackInfoReturnable<MusicSound> cir)
    {
        MusicSound musicSound = Nullables.map(this.currentScreen, Screen::getMusic);
        if (musicSound == null && this.player != null)
        {
            boolean play_boss_music = this.inGameHud.getBossBarHud().shouldPlayDragonMusic();
            if (play_boss_music)
            {
                MusicSound type = ((BossBarHudImpl)this.inGameHud.getBossBarHud()).frontiers_1_21x$getFirstAvailableMusic();
                if (type == null)
                {
                    cir.setReturnValue(ModMusic.WITHER);
                }
            }
        }
    }
}
