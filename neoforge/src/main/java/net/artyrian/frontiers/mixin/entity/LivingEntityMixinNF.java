package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinNF
{
    @ModifyExpressionValue(method = "updateFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canElytraFly(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private boolean frontiersNF$canUpdateQuickFlightLivEnt(boolean original)
    {
        if (!original)
        {
            return ((Player)(Object)this).hasEffect(FRStatusEffects.QUICK_FLIGHT);
        }
        return original;
    }

    @WrapOperation(method = "updateFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;elytraFlightTick(Lnet/minecraft/world/entity/LivingEntity;I)Z"))
    private boolean frontiersNF$elytraFlyTickOverrideWithFX(ItemStack instance, LivingEntity livingEntity, int i, Operation<Boolean> original)
    {
        if (((LivingEntity)(Object)this).hasEffect(FRStatusEffects.QUICK_FLIGHT)) return true;
        else return original.call(instance, livingEntity, i);
    }
}
