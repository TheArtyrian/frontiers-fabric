package net.artyrian.frontiers.mixin.ui.crafting;

import net.artyrian.frontiers.mixin.ui.ScreenHandlerMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ForgingScreenHandler.class)
public abstract class ForgingScreenMixin extends ScreenHandlerMixin
{
    @Shadow @Final protected Inventory input;
    @Shadow @Final protected PlayerEntity player;
    @Shadow @Final protected CraftingResultInventory output;
}