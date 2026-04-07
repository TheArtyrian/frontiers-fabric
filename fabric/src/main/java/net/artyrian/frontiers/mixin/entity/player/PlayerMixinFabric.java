package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixinFabric extends LivingEntityMixin
{
    @ModifyExpressionValue(method = "hurtCurrentlyUsedShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean doUniqueShieldChecks(boolean original)
    {
        return MixinShortcuts.playerCobaltShieldCheck(original, this.useItem);
    }
}
