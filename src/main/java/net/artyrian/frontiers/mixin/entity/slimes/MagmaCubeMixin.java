package net.artyrian.frontiers.mixin.entity.slimes;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MagmaCubeEntity.class)
public abstract class MagmaCubeMixin extends SlimeMixin
{
    @Override
    public void dropEquipmentHook(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        Entity entity = source.getAttacker();
        int size = this.getSize();
        if (
                causedByPlayer
                        && this.hasStatusEffect(StatusEffects.WEAKNESS)
                        && this.hasStatusEffect(StatusEffects.SLOWNESS)
                        && source.getWeaponStack() != null
                        && source.getWeaponStack().isOf(Items.SHEARS)
                        && (size == 2)
        )
        {
            this.dropItem(ModBlocks.MAGMA_CUBE_MODEL);

            Entity self = world.getEntity(this.getUuid());
            this.getWorld().sendEntityStatus(self, EntityStatuses.ADD_DEATH_PARTICLES);
            this.getWorld().playSound(self, self.getBlockPos(), ModSounds.ENTITY_SHEARED, SoundCategory.PLAYERS, 2.0F, 1.2F);
            source.getWeaponStack().damage(
                    source.getWeaponStack().getMaxDamage(),
                    (LivingEntity)entity,
                    LivingEntity.getSlotForHand(((LivingEntity) entity).getActiveHand()));

            this.setSize(1, false);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }
}
