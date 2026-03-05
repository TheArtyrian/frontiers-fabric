package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandMenu.PotionSlot.class)
public class PotionSlotMixin
{
    @Inject(method = "onTake", at = @At("HEAD"))
    private void frontiers$checkItemTaken(Player player, ItemStack stack, CallbackInfo ci)
    {
        if (player instanceof ServerPlayer server)
        {
            ModCriteria.BREWED_ITEM.get().trigger(server, stack);
        }
    }
}
