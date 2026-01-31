package net.artyrian.frontiers.mixin.entity.ender_dragon;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.definition.advancement.criterion.EntityKilledNearbyCriterion;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhaseManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Debug(export = true)
@Mixin(EnderDragon.class)
public abstract class EnderDragonMixin extends MobEntityMixin
{
    @Unique
    private static final float FRONTIERS_THRESH_MODIFIER = 0.05F;
    @Shadow private @Nullable EndDragonFight dragonFight;
    @Shadow @Final private EnderDragonPart body;
    @Shadow @Final private EnderDragonPhaseManager phaseManager;
    @Mutable
    @Shadow @Final private static float SITTING_ALLOWED_DAMAGE_PERCENTAGE;

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void frontiers$setupNewDamageThresh(CallbackInfo ci) { SITTING_ALLOWED_DAMAGE_PERCENTAGE = FRONTIERS_THRESH_MODIFIER; }

    @ModifyConstant(
            method = "hurt(Lnet/minecraft/world/entity/boss/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            constant = @Constant(floatValue = 0.25F, ordinal = 0)
    )
    private float frontiers$argChangeDamageThresh(float constant) { return FRONTIERS_THRESH_MODIFIER; }

    /** Increases base Dragon HP */
    @ModifyReturnValue(method = "createAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder frontiers$buffThisBeautifulWoman(AttributeSupplier.Builder original)
    {
        return original.add(Attributes.MAX_HEALTH, 1500.0);
    }

    /** Rewards all players within tracking range the ultimate advancement */
    @Inject(method = "tickDeath", at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;gameEvent(Lnet/minecraft/core/Holder;)V")
    )
    private void frontiers$emitGameEvent(CallbackInfo ci)
    {
        if (!this.level().isClientSide)
        {
            for (ServerPlayer targeter : VectorLib.NETWORK.getAllTrackingChunk((ServerLevel) this.level(), this.blockPosition(), false))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.get().trigger(targeter, this.getType());
            }
        }
    }

    @Inject(method = "knockBack", at = @At("HEAD"), cancellable = true)
    private void frontiers$frontiersRewriteLaunchMethod(ServerLevel world, List<Entity> entities, CallbackInfo ci)
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

                entity.push(f / h * 4.0, 0.2F, g / h * 4.0);

                if (!this.phaseManager.getCurrentPhase().isSitting() && livingEntity.getLastHurtByMobTimestamp() < entity.tickCount - 2)
                {
                    DamageSource damageSource = this.damageSources().mobAttack((EnderDragon)(Object)this);
                    entity.hurt(damageSource, (entity instanceof Player) ? 30.0F : 5.0F);
                    EnchantmentHelper.doPostAttackEffects(world, entity, damageSource);
                }
            }
        }
        ci.cancel();
    }

    @Inject(method = "hurt(Ljava/util/List;)V", at = @At("HEAD"), cancellable = true)
    private void frontiers$frontiersRewriteDamageMethod(List<Entity> entities, CallbackInfo ci)
    {
        for (Entity entity : entities)
        {
            if (entity instanceof LivingEntity living && !living.isFallFlying())
            {
                DamageSource damageSource = this.damageSources().mobAttack((EnderDragon)(Object)this);

                entity.hurt(damageSource, (entity instanceof Player) ? 40.0F : 10.0F);

                if (this.level() instanceof ServerLevel serverWorld)
                {
                    EnchantmentHelper.doPostAttackEffects(serverWorld, entity, damageSource);
                }
            }
        }
        ci.cancel();
    }
}
