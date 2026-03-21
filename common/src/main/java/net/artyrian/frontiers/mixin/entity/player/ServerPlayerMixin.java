package net.artyrian.frontiers.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.GameRules;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends PlayerMixin
{
    @Inject(method = "die", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;dropAllDeathLoot(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)V")
    )
    private void frontiers_dropSkull(DamageSource damageSource, CallbackInfo ci)
    {
        boolean do_loot = level().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
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
                    this.spawnAtLocation(itemStack);
                }
            }
        }

        if (do_loot && this.getStringUUID().equals(VectorLib.SYSTEM.CONTRIB_IDS.get("Artyrian")))
        {
            ItemStack itemStack = new ItemStack(Items.BEETROOT);
            this.spawnAtLocation(itemStack);
        }
    }

    @Override
    public void frontiers_1_21x$setManaPts(int pts)
    {
        PlayerPersistentNBT.Mana.setMana((ServerPlayer)(Object)this, pts);
    }

    // PLAYERDATA RESTORATION ////////////////////////////////////////////////////////////////////

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void frnt$deathRestoreAppend(ServerPlayer that, boolean keepEverything, CallbackInfo ci) { PlayerPersistentNBT.handleRespawn(that, (ServerPlayer)(Object)this); }

    @Inject(method = "triggerDimensionChangeTriggers", at = @At("TAIL"))
    private void frnt$resyncDataBackToClient(ServerLevel level, CallbackInfo ci) { PlayerPersistentNBT.handleClientReload((ServerPlayer)(Object)this); }

    @ModifyExpressionValue(method = "restoreFrom",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean checkAvariceTotem(boolean original, @Local(argsOnly = true, ordinal = 1) ServerPlayer oldPlayer)
    {
        boolean used_totem = ((PlayerIntf)oldPlayer).frontiers_1_21x$usedAvariceTotem();
        if (used_totem)
        {
            this.getInventory().replaceWith(oldPlayer.getInventory());
            this.setScore(oldPlayer.getScore());
        }
        return original;
    }
}
