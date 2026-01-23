package net.artyrian.frontiers.mixin.entity.slimes;

import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public abstract class SlimeMixin extends LivingEntityMixin
{
    @Shadow public abstract int getSize();
    @Shadow public abstract void setSize(int size, boolean heal);

    @Override
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        boolean do_loot = world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        Entity entity = source.getEntity();
        int size = this.getSize();
        if (
                do_loot
                        && causedByPlayer
                        && this.hasEffect(MobEffects.WEAKNESS)
                        && this.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
                        && source.getWeaponItem() != null
                        && source.getWeaponItem().is(Items.SHEARS)
                        && (size == 2)
        )
        {
            this.spawnAtLocation(ModBlocks.SLIME_MODEL.get());

            Entity self = world.getEntity(this.getUUID());
            this.level().broadcastEntityEvent(self, EntityEvent.POOF);
            this.level().playSound(self, self.blockPosition(), ModSounds.ENTITY_SHEARED.get(), SoundSource.PLAYERS, 2.0F, 1.2F);
            source.getWeaponItem().hurtAndBreak(
                    source.getWeaponItem().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getUsedItemHand()));

            this.setSize(1, false);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }
}
