package net.artyrian.frontiers.mixin.entity.creeper;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.entity.ai.creeper.CreeperNewRevengeGoal;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Debug(export = true)
@Mixin(Creeper.class)
public abstract class CreeperMixin extends LivingEntityMixin
{
    /** Makes creepers unable to retaliate against Ocelots - if the config allows. */
    @ModifyArgs(
            method = "initGoals",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/RevengeGoal;<init>(Lnet/minecraft/entity/mob/PathAwareEntity;[Ljava/lang/Class;)V")),
            at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V")
    )
    private void redirRevGoal(Args args)
    {
        if (Frontiers.CONFIG.doOcelotsAttackCreepers())
        {
            args.set(1, new CreeperNewRevengeGoal((Creeper)(Object)this));
        }
    }

    @Inject(method = "dropEquipment", at = @At("TAIL"))
    private void doTaxidermy(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        boolean do_loot = world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        Entity entity = source.getEntity();
        if (
                do_loot
                && causedByPlayer
                && this.hasStatusEffect(MobEffects.WEAKNESS)
                && this.hasStatusEffect(MobEffects.MOVEMENT_SLOWDOWN)
                && source.getWeaponItem() != null
                && source.getWeaponItem().is(Items.SHEARS)
        )
        {
            this.dropItem(ModBlocks.CREEPER_MODEL);

            Entity self = world.getEntity(this.getUuid());
            this.getWorld().broadcastEntityEvent(self, EntityEvent.POOF);
            this.getWorld().playSound(self, self.blockPosition(), ModSounds.ENTITY_SHEARED, SoundSource.PLAYERS, 2.0F, 1.2F);
            source.getWeaponItem().hurtAndBreak(
                    source.getWeaponItem().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getUsedItemHand()));
            this.remove(Entity.RemovalReason.KILLED);
        }
    }
}
