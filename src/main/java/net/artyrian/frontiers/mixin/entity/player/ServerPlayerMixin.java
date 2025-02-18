package net.artyrian.frontiers.mixin.entity.player;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
                if (!itemStack.isEmpty())
                {
                    creeperEntity.onHeadDropped();
                    this.dropStack(itemStack);
                }
            }
        }
    }
}
