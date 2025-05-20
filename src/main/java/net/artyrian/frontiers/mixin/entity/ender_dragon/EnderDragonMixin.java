package net.artyrian.frontiers.mixin.entity.ender_dragon;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonMixin extends MobEntityMixin
{
    @Unique
    private static final float FRONTIERS_THRESH_MODIFIER = 0.05F;
    @Shadow private @Nullable EnderDragonFight fight;
    @Shadow @Final private EnderDragonPart body;
    @Shadow @Final private PhaseManager phaseManager;
    @Mutable
    @Shadow @Final private static float TAKEOFF_THRESHOLD;

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void setupNewDamageThresh(CallbackInfo ci) { TAKEOFF_THRESHOLD = FRONTIERS_THRESH_MODIFIER; }

    @ModifyConstant(method = "damagePart", constant = @Constant(floatValue = 0.25F, ordinal = 0))
    private float argChangeDamageThresh(float constant) { return FRONTIERS_THRESH_MODIFIER; }

    /** Increases base Dragon HP */
    @ModifyReturnValue(method = "createEnderDragonAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder buffThisBeautifulWoman(DefaultAttributeContainer.Builder original)
    {
        return original.add(EntityAttributes.GENERIC_MAX_HEALTH, 1500.0);
    }

    /** Rewards all players within tracking range the ultimate advancement */
    @Inject(method = "updatePostDeath", at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;)V")
    )
    private void emitGameEvent(CallbackInfo ci)
    {
        if (!this.getWorld().isClient)
        {
            for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) this.getWorld(), this.getBlockPos()))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.trigger(targeter, this.getType());
            }
        }
    }

    @Inject(method = "launchLivingEntities", at = @At("HEAD"), cancellable = true)
    private void frontiersRewriteLaunchMethod(ServerWorld world, List<Entity> entities, CallbackInfo ci)
    {
        double d = (this.body.getBoundingBox().minX + this.body.getBoundingBox().maxX) / 2.0;
        double e = (this.body.getBoundingBox().minZ + this.body.getBoundingBox().maxZ) / 2.0;

        for (Entity entity : entities)
        {
            if (entity instanceof LivingEntity livingEntity && !livingEntity.isFallFlying())
            {
                double f = entity.getX() - d;
                double g = entity.getZ() - e;
                double h = Math.max(f * f + g * g, 0.1);

                entity.addVelocity(f / h * 4.0, 0.2F, g / h * 4.0);

                if (!this.phaseManager.getCurrent().isSittingOrHovering() && livingEntity.getLastAttackedTime() < entity.age - 2)
                {
                    DamageSource damageSource = this.getDamageSources().mobAttack((EnderDragonEntity)(Object)this);
                    entity.damage(damageSource, (entity instanceof PlayerEntity) ? 30.0F : 5.0F);
                    EnchantmentHelper.onTargetDamaged(world, entity, damageSource);
                }
            }
        }
        ci.cancel();
    }

    @Inject(method = "damageLivingEntities", at = @At("HEAD"), cancellable = true)
    private void frontiersRewriteDamageMethod(List<Entity> entities, CallbackInfo ci)
    {
        for (Entity entity : entities)
        {
            if (entity instanceof LivingEntity living && !living.isFallFlying())
            {
                DamageSource damageSource = this.getDamageSources().mobAttack((EnderDragonEntity)(Object)this);

                entity.damage(damageSource, (entity instanceof PlayerEntity) ? 40.0F : 10.0F);

                if (this.getWorld() instanceof ServerWorld serverWorld)
                {
                    EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
                }
            }
        }
        ci.cancel();
    }
}
