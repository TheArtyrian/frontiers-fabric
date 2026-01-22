package net.artyrian.frontiers.mixin.compat.yigd;

import com.b1n_ry.yigd.DeathHandler;
import com.b1n_ry.yigd.mixin.LivingEntityMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathHandler.class)
public class YigdGravestoneCheckMixin
{
    @Inject(method = "onPlayerDeath", at = @At("HEAD"), cancellable = true)
    private void preventIfTotem(ServerPlayer player, ServerLevel world, Vec3 pos, DamageSource deathSource, CallbackInfo ci)
    {
        Inventory inventory = player.getInventory();
        ItemStack stack;

        for (int i = 0; i < inventory.getContainerSize(); i++)
        {
            stack = inventory.getItem(i);
            if (stack.is(ModItem.TOTEM_OF_AVARICE))
            {
                Frontiers.LOGGER.info("[FRONTIERS] Prevented YIGD from generating a grave - Avarice Totem detected");
                ci.cancel();
            }
        }
    }
}
