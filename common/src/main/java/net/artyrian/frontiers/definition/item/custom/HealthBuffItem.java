package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class HealthBuffItem extends Item
{
    private String nbt_to_set;
    private boolean value;

    public HealthBuffItem(String nbt_to_set, boolean value, Properties settings)
    {
        super(settings);
        this.nbt_to_set = nbt_to_set;
        this.value = value;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        return ItemUtils.startUsingInstantly(world, user, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        super.finishUsingItem(stack, world, user);
        if (user instanceof ServerPlayer serverPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }

        // Server
        if (!world.isClientSide())
        {
            CompoundTag writestack = new CompoundTag();
            user.addAdditionalSaveData(writestack);
            writestack.putBoolean(nbt_to_set, value);
            user.readAdditionalSaveData(writestack);
        }

        return stack;
    }
}
