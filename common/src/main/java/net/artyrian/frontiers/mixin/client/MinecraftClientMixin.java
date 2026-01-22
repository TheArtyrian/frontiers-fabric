package net.artyrian.frontiers.mixin.client;

import net.artyrian.frontiers.mixin_intf.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.sounds.ModMusic;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.Optionull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.Music;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin
{
    @Shadow @Nullable public Screen currentScreen;
    @Shadow @Nullable public LocalPlayer player;
    @Shadow @Final public Gui inGameHud;

    @Inject(method = "getMusicType", at = @At("HEAD"), cancellable = true)
    private void frontiers_customMusicEngineInject(CallbackInfoReturnable<Music> cir)
    {
        Music musicSound = Optionull.map(this.currentScreen, Screen::getBackgroundMusic);
        if (musicSound == null && this.player != null)
        {
            boolean play_boss_music = this.inGameHud.getBossOverlay().shouldPlayMusic();
            if (play_boss_music)
            {
                Music type = ((BossBarHudImpl)this.inGameHud.getBossOverlay()).frontiers_1_21x$getFirstAvailableMusic();
                if (type == null)
                {
                    cir.setReturnValue(ModMusic.WITHER);
                }
            }
        }
    }
}
