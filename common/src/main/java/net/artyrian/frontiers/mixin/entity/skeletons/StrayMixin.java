package net.artyrian.frontiers.mixin.entity.skeletons;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Stray.class)
public abstract class StrayMixin extends AbstractSkeletonMixin
{
    @Shadow protected abstract AbstractArrow getArrow(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom);

    @Override
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        boolean do_loot = world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
        Entity entity = source.getEntity();
        if (
                do_loot
                        && causedByPlayer
                        && this.hasEffect(MobEffects.WEAKNESS)
                        && this.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)
                        && source.getWeaponItem() != null
                        && source.getWeaponItem().is(Items.SHEARS)
        )
        {
            this.spawnAtLocation(ModBlocks.STRAY_MODEL.get());

            Entity self = world.getEntity(this.getUUID());
            this.level().broadcastEntityEvent(self, EntityEvent.POOF);
            this.level().playSound(self, self.blockPosition(), ModSounds.ENTITY_SHEARED.get(), SoundSource.PLAYERS, 2.0F, 1.2F);
            source.getWeaponItem().hurtAndBreak(
                    source.getWeaponItem().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getUsedItemHand()));
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @ModifyReturnValue(method = "getArrow", at = @At("RETURN"))
    private AbstractArrow returnSubzeroArrow(AbstractArrow original,
                                                          @Local(argsOnly = true, ordinal = 0) ItemStack arrow,
                                                          @Local(argsOnly = true) float damageModifier,
                                                          @Local(argsOnly = true, ordinal = 1) @Nullable ItemStack shotFrom)
    {
        if (!this.level().isClientSide)
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(this.level().getServer());
            boolean hardmode = loader.isInHardmode;

            if (hardmode)
            {
                return ProjectileUtil.getMobArrow(
                        (LivingEntity)original.getOwner(), new ItemStack(ModItem.SUBZERO_ARROW.get()), damageModifier, shotFrom);
            }
        }
        return original;
    }
}
