package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixinNF extends LivingEntityMixin
{
    @ModifyExpressionValue(method = "hurtCurrentlyUsedShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canPerformAction(Lnet/neoforged/neoforge/common/ItemAbility;)Z"))
    private boolean doUniqueShieldChecks(boolean original)
    {
        return original || this.useItem.is(ModItem.COBALT_SHIELD.get());
    }

    @ModifyExpressionValue(method = "tryToStartFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canElytraFly(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private boolean frontiersNF$canFlyWithQuickFlight(boolean original)
    {
        if (!original)
        {
            return ((Player)(Object)this).hasEffect(ModStatusEffects.QUICK_FLIGHT);
        }
        return original;
    }
}
