package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.artyrian.frontiers.mixin_intf.BobberType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class CustomFishingRod extends FishingRodItem
{
    private final int ENCHANTABILITY;
    private final BobberType BOBBER_TYPE;

    public CustomFishingRod(BobberType rod_type, int enchantability, Item.Properties settings)
    {
        super(settings);
        this.BOBBER_TYPE = rod_type;
        this.ENCHANTABILITY = enchantability;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        if (user.fishing != null) {
            if (!world.isClientSide) {
                int i = user.fishing.retrieve(itemStack);
                itemStack.hurtAndBreak(i, user, LivingEntity.getSlotForHand(hand));
            }

            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.FISHING_BOBBER_RETRIEVE,
                    SoundSource.NEUTRAL,
                    1.0F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            user.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            world.playSound(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (world instanceof ServerLevel serverWorld)
            {
                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, itemStack, user) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverWorld, itemStack, user);

                FishingHook bobby = new FishingHook(user, world, k, j);
                ((BobberIntf)bobby).frontiers_1_21x$setParentItemStack(itemStack);

                world.addFreshEntity(bobby);
            }

            user.awardStat(Stats.ITEM_USED.get(this));
            user.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    @Override
    public int getEnchantmentValue() {
        return ENCHANTABILITY;
    }
}
