package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

// An item that will prevent zombification
public class TruffleItem extends Item
{
    public TruffleItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand)
    {
        NbtCompound precheck = new NbtCompound();
        entity.writeCustomDataToNbt(precheck);
        boolean notImmune = !precheck.contains("IsImmuneToZombification");

        if (stack.getItem() == ModItem.TRUFFLE && entity.getType() == EntityType.HOGLIN && notImmune)
        {
            stack.decrementUnlessCreative(1, user);

            ParticleUtil.spawnParticlesAround(entity.getWorld(), entity.getBlockPos(), 14, 2.0, 2.0, true, ParticleTypes.HAPPY_VILLAGER);
            ParticleUtil.spawnParticlesAround(entity.getWorld(), entity.getBlockPos(), 14, 2.0, 2.0, true, ParticleTypes.PORTAL);
            ParticleUtil.spawnParticlesAround(entity.getWorld(), entity.getBlockPos(), 14, 2.0, 2.0, true, ParticleTypes.SMOKE);

            NbtCompound IHopeThisWorksGodPlease = new NbtCompound();
            entity.writeCustomDataToNbt(IHopeThisWorksGodPlease);
            IHopeThisWorksGodPlease.putBoolean("IsImmuneToZombification", true);
            IHopeThisWorksGodPlease.putBoolean("BredWithTruffle", true);
            IHopeThisWorksGodPlease.putInt("TimeInOverworld", 0);

            entity.playSound(SoundEvents.BLOCK_ROOTS_BREAK);
            entity.readCustomDataFromNbt(IHopeThisWorksGodPlease);

            return ActionResult.SUCCESS;
        }
        else return super.useOnEntity(stack, user, entity, hand);
    }
}
