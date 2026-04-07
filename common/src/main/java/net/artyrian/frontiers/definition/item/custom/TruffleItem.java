package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

public class TruffleItem extends Item
{
    public TruffleItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand)
    {
        if (entity instanceof Hoglin hoglin && !((HoglinIntf)hoglin).frontiers$isImmuneToZombification())
        {
            Level level = user.level();
            if (!level.isClientSide)
            {
                stack.consume(1, user);
                hoglin.makeSound(FRSounds.HOGLIN_TRUFFLED.get());

                ((HoglinIntf)hoglin).frontiers_1_21x$setTruffled(true);
                hoglin.setPersistenceRequired();

                CompoundTag tag = new CompoundTag();
                entity.addAdditionalSaveData(tag);
                tag.putBoolean("IsImmuneToZombification", true);
                tag.putInt("TimeInOverworld", 0);
                entity.readAdditionalSaveData(tag);

                VectorEventSync.Entity.fireEvent(level, entity, FRLevelEvents.Entity.HOGLIN_TAME, 0);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.interactLivingEntity(stack, user, entity, hand);
    }
}
