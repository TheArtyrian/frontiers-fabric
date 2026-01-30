package net.artyrian.frontiers.mixin.entity.enderman;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.block.custom.SpiritCandleBlock;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(EnderMan.class)
public abstract class EndermanMixin extends LivingEntityMixin
{
    @ModifyVariable(method = "teleport(DDD)Z", at = @At("STORE"), ordinal = 0)
    private boolean blockForSpiritCandle(boolean original, @Local BlockPos.MutableBlockPos mutable)
    {
        if (original)
        {
            BoundingBox box = new BoundingBox(mutable).inflatedBy(SpiritCandleBlock.BOX_EFFECT_SIZE);
            BlockPos min = new BlockPos(box.minX(), box.minY(), box.minZ());
            BlockPos max = new BlockPos(box.maxX(), box.maxY(), box.maxZ());

            for (BlockPos pos : BlockPos.betweenClosed(min, max))
            {
                BlockState state = this.level().getBlockState(pos);
                if (state.is(ModBlocks.SPIRIT_CANDLE.get()))
                {
                    Optional<Boolean> lit = state.getOptionalValue(SpiritCandleBlock.LIT);
                    boolean hasLit = lit.isPresent();
                    if (hasLit && lit.get())
                    {
                        SpiritCandleBlock.spawnBlockingParticles((ServerLevel)this.level(), pos);
                        SpiritCandleBlock.spawnBlockingParticles((ServerLevel)this.level(), this.blockPosition());

                        return false;
                    }
                }
            }
        }
        return original;
    }

    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void doTaxidermy(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
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
            this.spawnAtLocation(ModBlocks.ENDERMAN_MODEL.get());

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
}
