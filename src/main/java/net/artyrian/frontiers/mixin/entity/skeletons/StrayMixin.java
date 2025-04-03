package net.artyrian.frontiers.mixin.entity.skeletons;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.entity.projectile.SubzeroArrowEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StrayEntity.class)
public abstract class StrayMixin extends LivingEntityMixin
{
    @Shadow protected abstract PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom);

    @Override
    public void dropEquipmentHook(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
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
            this.dropItem(ModBlocks.STRAY_MODEL);

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

    @ModifyReturnValue(method = "createArrowProjectile", at = @At("RETURN"))
    private PersistentProjectileEntity returnSubzeroArrow(PersistentProjectileEntity original,
                                                          @Local(argsOnly = true, ordinal = 0) ItemStack arrow,
                                                          @Local(argsOnly = true) float damageModifier,
                                                          @Local(argsOnly = true, ordinal = 1) @Nullable ItemStack shotFrom)
    {
        if (!this.getWorld().isClient)
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(this.getWorld().getServer());
            boolean hardmode = loader.isInHardmode;

            if (hardmode)
            {
                return ProjectileUtil.createArrowProjectile(
                        (LivingEntity)original.getOwner(), new ItemStack(ModItem.SUBZERO_ARROW), damageModifier, shotFrom);
            }
        }
        return original;
    }
}
