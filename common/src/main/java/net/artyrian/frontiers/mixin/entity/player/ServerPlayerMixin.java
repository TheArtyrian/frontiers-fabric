package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends PlayerMixin
{
    @Inject(method = "onDeath", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayerEntity;drop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;)V")
    )
    public void frontiers_dropSkull(DamageSource damageSource, CallbackInfo ci)
    {
        boolean do_loot = getWorld().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        Entity entity = damageSource.getEntity();
        if (entity instanceof Creeper creeperEntity && do_loot)
        {
            if (creeperEntity.canDropMobsSkull())
            {
                ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
                itemStack.set(DataComponents.PROFILE, new ResolvableProfile(this.getGameProfile()));
                itemStack.set(DataComponents.NOTE_BLOCK_SOUND, MethodToolbox.getSpecialHeadSound(this.getGameProfile().getName()));
                if (!itemStack.isEmpty())
                {
                    creeperEntity.increaseDroppedSkulls();
                    this.dropStack(itemStack);
                }
            }
        }

        if (do_loot && this.getUuidAsString().equals(Frontiers.CONTRIB_IDS.get("Artyrian")))
        {
            ItemStack itemStack = new ItemStack(Items.BEETROOT);
            this.dropStack(itemStack);
        }
    }

    @ModifyExpressionValue(method = "copyFrom",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    public boolean checkAvariceTotem(boolean original, @Local(argsOnly = true, ordinal = 1) ServerPlayer oldPlayer)
    {
        boolean used_totem = ((PlayerMixInterface)oldPlayer).frontiers_1_21x$usedAvariceTotem();
        if (used_totem)
        {
            this.getInventory().replaceWith(oldPlayer.getInventory());
            this.setScore(oldPlayer.getScore());
        }
        return original;
    }

    @Override
    public void frontiers$openBottleScreen(ItemStack stack, InteractionHand hand)
    {
        // Unused due to basically being unnecessary in this scope
        //Frontiers.LOGGER.info("shut up {server}");
    }
}
