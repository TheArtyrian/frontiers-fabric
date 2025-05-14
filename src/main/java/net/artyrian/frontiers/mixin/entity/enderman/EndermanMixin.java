package net.artyrian.frontiers.mixin.entity.enderman;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public abstract class EndermanMixin extends LivingEntityMixin
{
    @ModifyExpressionValue(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean playerGazeProtectionREMOVE_IN_1_21_2(boolean original, @Local ItemStack stack)
    {
        return original ||
                stack.isOf(ModBlocks.CARVED_GLISTERING_MELON.asItem()) ||
                stack.isOf(ModBlocks.CARVED_MELON.asItem()) ||
                stack.isOf(ModBlocks.WHITE_PUMPKIN.asItem());
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
            this.dropItem(ModBlocks.ENDERMAN_MODEL);

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
