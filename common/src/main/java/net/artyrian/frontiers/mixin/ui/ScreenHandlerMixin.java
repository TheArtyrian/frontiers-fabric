package net.artyrian.frontiers.mixin.ui;

import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractContainerMenu.class)
public abstract class ScreenHandlerMixin
{

    @Shadow public abstract void sendContentUpdates();
}
