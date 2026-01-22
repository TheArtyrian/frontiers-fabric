package net.artyrian.frontiers.mixin.entity.ender_dragon;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
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
    @Shadow private @Nullable EndDragonFight fight;
    @Shadow @Final private EnderDragonPart body;
    @Shadow @Final private EnderDragonPhaseManager phaseManager;
    @Mutable
    @Shadow @Final private static float TAKEOFF_THRESHOLD;

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void setupNewDamageThresh(CallbackInfo ci) { TAKEOFF_THRESHOLD = FRONTIERS_THRESH_MODIFIER; }

    @ModifyConstant(method = "damagePart", constant = @Constant(floatValue = 0.25F, ordinal = 0))
    private float argChangeDamageThresh(float constant) { return FRONTIERS_THRESH_MODIFIER; }

    /** Increases base Dragon HP */
    @ModifyReturnValue(method = "createEnderDragonAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder buffThisBeautifulWoman(AttributeSupplier.Builder original)
    {
        return original.add(Attributes.MAX_HEALTH, 1500.0);
    }

    /** Rewards all players within tracking range the ultimate advancement */
    @Inject(method = "updatePostDeath", at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;)V")
    )
    private void emitGameEvent(CallbackInfo ci)
    {
        if (!this.getWorld().isClientSide)
        {
            for (ServerPlayer targeter : PlayerLookup.tracking((ServerLevel) this.getWorld(), this.getBlockPos()))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.trigger(targeter, this.getType());
            }
        }
    }

    @Inject(method = "launchLivingEntities", at = @At("HEAD"), cancellable = true)
    private void frontiersRewriteLaunchMethod(ServerLevel world, List<Entity> entities, CallbackInfo ci)
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
                    DamageSource damageSource = this.getDamageSources().mobAttack((EnderDragon)(Object)this);
                    entity.hurt(damageSource, (entity instanceof Player) ? 30.0F : 5.0F);
                    EnchantmentHelper.doPostAttackEffects(world, entity, damageSource);
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
                DamageSource damageSource = this.getDamageSources().mobAttack((EnderDragon)(Object)this);

                entity.hurt(damageSource, (entity instanceof Player) ? 40.0F : 10.0F);

                if (this.getWorld() instanceof ServerLevel serverWorld)
                {
                    EnchantmentHelper.doPostAttackEffects(serverWorld, entity, damageSource);
                }
            }
        }
        ci.cancel();
    }
}
