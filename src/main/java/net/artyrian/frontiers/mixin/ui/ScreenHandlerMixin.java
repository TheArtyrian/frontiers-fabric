package net.artyrian.frontiers.mixin.ui;

import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin
{

    @Shadow public abstract void sendContentUpdates();
}
