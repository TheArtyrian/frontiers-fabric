package net.artyrian.frontiers.mixin.compat.yigd;

import com.b1n_ry.yigd.events.ServerEventHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEventHandler.class)
public class YigdGravestoneCheckMixin
{
    @Inject(method = "onLivingDeath", at = @At(
            value = "INVOKE",
            target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        cancellable = true
    )
    private void frontiers$preventTotemDropperHandle(
            LivingDeathEvent event,
            CallbackInfo ci,
            @Local ServerPlayer player
    ) {
        Inventory inventory = player.getInventory();
        ItemStack stack;

        for (int i = 0; i < inventory.getContainerSize(); i++)
        {
            stack = inventory.getItem(i);
            if (stack.is(FRItems.TOTEM_OF_AVARICE.get()))
            {
                Frontiers.LOGGER.info("[FRONTIERS] Prevented YIGD from generating a grave - Avarice Totem detected");
                ci.cancel();
            }
        }
    }
}
