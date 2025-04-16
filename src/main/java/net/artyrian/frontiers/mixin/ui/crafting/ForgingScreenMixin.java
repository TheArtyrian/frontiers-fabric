package net.artyrian.frontiers.mixin.ui.crafting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ForgingScreenHandler.class)
public abstract class ForgingScreenMixin
{
    @Shadow @Final protected PlayerEntity player;
    @Shadow @Final protected CraftingResultInventory output;
}