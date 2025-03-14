package net.artyrian.frontiers.mixin.ui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public abstract class ScreenMixin
{
    @Shadow public int height;
    @Shadow public int width;
    @Shadow protected TextRenderer textRenderer;
    @Shadow @Nullable protected MinecraftClient client;
}
