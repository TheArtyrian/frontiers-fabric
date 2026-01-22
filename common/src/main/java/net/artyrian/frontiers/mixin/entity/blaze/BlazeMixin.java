package net.artyrian.frontiers.mixin.entity.blaze;

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
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blaze.class)
public abstract class BlazeMixin extends LivingEntityMixin
{
    @Override
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
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
            this.dropItem(ModBlocks.BLAZE_MODEL.get());

            Entity self = world.getEntity(this.getUuid());
            this.getWorld().broadcastEntityEvent(self, EntityEvent.POOF);
            this.getWorld().playSound(self, self.blockPosition(), ModSounds.ENTITY_SHEARED.get(), SoundSource.PLAYERS, 2.0F, 1.2F);
            source.getWeaponItem().hurtAndBreak(
                    source.getWeaponItem().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getUsedItemHand()));
            this.remove(Entity.RemovalReason.KILLED);
        }
    }
}
