package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin extends PlayerMixin
{
    @Inject(method = "onDeath", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayerEntity;drop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;)V")
    )
    public void frontiers_dropSkull(DamageSource damageSource, CallbackInfo ci)
    {
        Entity entity = damageSource.getAttacker();
        if (entity instanceof CreeperEntity creeperEntity)
        {
            if (creeperEntity.shouldDropHead())
            {
                ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
                itemStack.set(DataComponentTypes.PROFILE, new ProfileComponent(this.getGameProfile()));
                itemStack.set(DataComponentTypes.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound(this.getGameProfile().getName()));
                if (!itemStack.isEmpty())
                {
                    creeperEntity.onHeadDropped();
                    this.dropStack(itemStack);
                }
            }
        }
    }

    @ModifyExpressionValue(method = "copyFrom",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    public boolean checkAvariceTotem(boolean original, @Local(argsOnly = true, ordinal = 1) ServerPlayerEntity oldPlayer)
    {
        boolean used_totem = ((PlayerMixInteface)oldPlayer).frontiers_1_21x$usedAvariceTotem();
        if (used_totem) return true;
        return original;
    }
}
