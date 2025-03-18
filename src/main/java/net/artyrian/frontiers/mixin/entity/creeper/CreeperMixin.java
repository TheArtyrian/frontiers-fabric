package net.artyrian.frontiers.mixin.entity.creeper;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperMixin extends LivingEntityMixin
{
    @Inject(method = "dropEquipment", at = @At("TAIL"))
    private void doTaxidermy(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        Entity entity = source.getAttacker();
        if (
                causedByPlayer
                && this.hasStatusEffect(StatusEffects.WEAKNESS)
                && this.hasStatusEffect(StatusEffects.SLOWNESS)
                && source.getWeaponStack() != null
                && source.getWeaponStack().isOf(Items.SHEARS)
        )
        {
            this.dropItem(ModBlocks.CREEPER_MODEL);
        }
    }
}
