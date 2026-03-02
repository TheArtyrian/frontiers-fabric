package net.artyrian.frontiers.mixin.client;

import net.artyrian.frontiers.mixin_intf.bossbar.BossBarHudImpl;
import net.artyrian.frontiers.reg.sound.FRMusic;
import net.artyrian.frontiers.reg.sound.ModSounds;
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
    @Shadow @Nullable public Screen screen;
    @Shadow @Nullable public LocalPlayer player;
    @Shadow @Final public Gui gui;

    @Inject(method = "getSituationalMusic", at = @At("HEAD"), cancellable = true)
    private void frontiers_customMusicEngineInject(CallbackInfoReturnable<Music> cir)
    {
        Music musicSound = Optionull.map(this.screen, Screen::getBackgroundMusic);
        if (musicSound == null && this.player != null)
        {
            boolean play_boss_music = this.gui.getBossOverlay().shouldPlayMusic();
            if (play_boss_music)
            {
                Music type = ((BossBarHudImpl)this.gui.getBossOverlay()).frontiers_1_21x$getFirstAvailableMusic();
                if (type != null)
                {
                    cir.setReturnValue(type);
                }
                else
                {
                    cir.setReturnValue(FRMusic.WITHER);
                }
            }
        }
    }
}
