package net.artyrian.frontiers.mixin.entity.vex;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.particle.ModParticle;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VexEntity.class)
public abstract class VexMixin extends MobEntityMixin
{
    @Shadow public abstract boolean isCharging();

    @Shadow public abstract void setCharging(boolean charging);

    @Override
    public void dropEquipmentHook(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        if (causedByPlayer && this.isCharging())
        {
            this.setCharging(false);

            world.spawnParticles(
                    ModParticle.VEX_CHARGE_PARTICLE_LR,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    6,
                    0.4,
                    0.4,
                    0.4,
                    0.7
            );
            world.spawnParticles(
                    ModParticle.VEX_CHARGE_PARTICLE_R,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    6,
                    0.4,
                    0.4,
                    0.4,
                    0.7
            );

            float drop_incense = world.getRandom().nextFloat();
            boolean do_loot = world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
            if (drop_incense >= 0.5F && do_loot)
            {
                ItemEntity incense = new ItemEntity(world,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        new ItemStack(ModItem.INCENSE, 1)
                );
                incense.setVelocity(
                        .05d * (world.getRandom().nextDouble() * 0.04D),
                        .1d,
                        .05d * (world.getRandom().nextDouble() * 0.04D));
                incense.setToDefaultPickupDelay();
                world.spawnEntity(incense);
            }
        }
    }

    @ModifyArg(method = "initEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;)V"))
    private ItemConvertible switchToMourningGold(ItemConvertible item)
    {
        return ModItem.MOURNING_GOLD_SWORD;
    }
}
