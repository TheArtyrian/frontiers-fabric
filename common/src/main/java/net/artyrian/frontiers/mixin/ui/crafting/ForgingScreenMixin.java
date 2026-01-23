package net.artyrian.frontiers.mixin.ui.crafting;

import net.artyrian.frontiers.mixin.ui.ScreenHandlerMixin;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemCombinerMenu.class)
public abstract class ForgingScreenMixin extends ScreenHandlerMixin
{
    @Shadow @Final protected Container inputSlots;
    @Shadow @Final protected Player player;
    @Shadow @Final protected ResultContainer resultSlots;
}