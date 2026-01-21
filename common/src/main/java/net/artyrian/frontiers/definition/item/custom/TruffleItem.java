package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

// An item that will prevent zombification
public class TruffleItem extends Item
{
    public TruffleItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand)
    {
        CompoundTag precheck = new CompoundTag();
        entity.addAdditionalSaveData(precheck);
        boolean notImmune = !precheck.contains("IsImmuneToZombification");

        if (stack.getItem() == ModItem.TRUFFLE.get() && entity.getType() == EntityType.HOGLIN && notImmune)
        {
            if (!user.level().isClientSide())
            {
                stack.consume(1, user);

                CompoundTag IHopeThisWorksGodPlease = new CompoundTag();
                entity.addAdditionalSaveData(IHopeThisWorksGodPlease);
                IHopeThisWorksGodPlease.putBoolean("IsImmuneToZombification", true);
                IHopeThisWorksGodPlease.putBoolean("BredWithTruffle", true);
                IHopeThisWorksGodPlease.putInt("TimeInOverworld", 0);
                IHopeThisWorksGodPlease.putBoolean("PersistenceRequired", true);

                entity.makeSound(SoundEvents.ROOTS_BREAK);
                entity.readAdditionalSaveData(IHopeThisWorksGodPlease);

                return InteractionResult.SUCCESS;
            }
            else
            {
                ParticleUtils.spawnParticles(entity.level(), entity.blockPosition(), 14, 2.0, 2.0, true, ParticleTypes.HAPPY_VILLAGER);
                ParticleUtils.spawnParticles(entity.level(), entity.blockPosition(), 14, 2.0, 2.0, true, ParticleTypes.PORTAL);
                ParticleUtils.spawnParticles(entity.level(), entity.blockPosition(), 14, 2.0, 2.0, true, ParticleTypes.SMOKE);

                return InteractionResult.SUCCESS;
            }
        }
        else return super.interactLivingEntity(stack, user, entity, hand);
    }
}
