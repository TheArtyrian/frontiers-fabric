package net.artyrian.frontiers.mixin.entity.creeper;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.entity.ai.creeper.CreeperNewRevengeGoal;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Debug(export = true)
@Mixin(CreeperEntity.class)
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
            args.set(1, new CreeperNewRevengeGoal((CreeperEntity)(Object)this));
        }
    }

    @Inject(method = "dropEquipment", at = @At("TAIL"))
    private void doTaxidermy(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        boolean do_loot = world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        Entity entity = source.getAttacker();
        if (
                do_loot
                && causedByPlayer
                && this.hasStatusEffect(StatusEffects.WEAKNESS)
                && this.hasStatusEffect(StatusEffects.SLOWNESS)
                && source.getWeaponStack() != null
                && source.getWeaponStack().isOf(Items.SHEARS)
        )
        {
            this.dropItem(ModBlocks.CREEPER_MODEL);

            Entity self = world.getEntity(this.getUuid());
            this.getWorld().sendEntityStatus(self, EntityStatuses.ADD_DEATH_PARTICLES);
            this.getWorld().playSound(self, self.getBlockPos(), ModSounds.ENTITY_SHEARED, SoundCategory.PLAYERS, 2.0F, 1.2F);
            source.getWeaponStack().damage(
                    source.getWeaponStack().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getActiveHand()));
            this.remove(Entity.RemovalReason.KILLED);
        }
    }
}
