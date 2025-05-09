package net.artyrian.frontiers.mixin.compat.yigd;

import com.b1n_ry.yigd.DeathHandler;
import com.b1n_ry.yigd.mixin.LivingEntityMixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathHandler.class)
public class YigdGravestoneCheckMixin
{
    @Inject(method = "onPlayerDeath", at = @At("HEAD"), cancellable = true)
    private void preventIfTotem(ServerPlayerEntity player, ServerWorld world, Vec3d pos, DamageSource deathSource, CallbackInfo ci)
    {
        PlayerInventory inventory = player.getInventory();
        ItemStack stack;

        for (int i = 0; i < inventory.size(); i++)
        {
            stack = inventory.getStack(i);
            if (stack.isOf(ModItem.TOTEM_OF_AVARICE))
            {
                Frontiers.LOGGER.info("[FRONTIERS] Prevented YIGD from generating a grave - Avarice Totem detected");
                ci.cancel();
            }
        }
    }
}
