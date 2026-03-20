package net.artyrian.frontiers.definition.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;

public class PostUseItem extends Item
{
    private final Action action;

    public PostUseItem(Action action, Properties settings)
    {
        super(settings);
        this.action = action;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        return ItemUtils.startUsingInstantly(world, user, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        ItemStack stack2 = super.finishUsingItem(stack, world, user);
        this.action.run(this, stack2, world, user);
        return stack2;
    }

    @FunctionalInterface
    public interface Action
    {
        void run(Item type, ItemStack stack, Level level, LivingEntity user);
    }
}
