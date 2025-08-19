package net.artyrian.frontiers.mixin.entity.enderman;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.SpiritCandleBlock;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

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

    @ModifyVariable(method = "teleportTo(DDD)Z", at = @At("STORE"), ordinal = 0)
    private boolean blockForSpiritCandle(boolean original, @Local BlockPos.Mutable mutable)
    {
        if (original)
        {
            BlockBox box = new BlockBox(mutable).expand(SpiritCandleBlock.BOX_EFFECT_SIZE);
            BlockPos min = new BlockPos(box.getMinX(), box.getMinY(), box.getMinZ());
            BlockPos max = new BlockPos(box.getMaxX(), box.getMaxY(), box.getMaxZ());

            for (BlockPos pos : BlockPos.iterate(min, max))
            {
                BlockState state = this.getWorld().getBlockState(pos);
                if (state.isOf(ModBlocks.SPIRIT_CANDLE))
                {
                    Optional<Boolean> lit = state.getOrEmpty(SpiritCandleBlock.LIT);
                    boolean hasLit = lit.isPresent();
                    if (hasLit && lit.get())
                    {
                        SpiritCandleBlock.spawnBlockingParticles((ServerWorld)this.getWorld(), pos);
                        SpiritCandleBlock.spawnBlockingParticles((ServerWorld)this.getWorld(), this.getBlockPos());

                        return false;
                    }
                }
            }
        }
        return original;
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
