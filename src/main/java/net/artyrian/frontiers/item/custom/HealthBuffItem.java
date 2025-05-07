package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HealthBuffItem extends Item
{
    private String nbt_to_set;
    private boolean value;

    public HealthBuffItem(String nbt_to_set, boolean value, Settings settings)
    {
        super(settings);
        this.nbt_to_set = nbt_to_set;
        this.value = value;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        // Server
        if (!world.isClient())
        {
            NbtCompound writestack = new NbtCompound();
            user.writeCustomDataToNbt(writestack);
            writestack.putBoolean(nbt_to_set, value);
            user.readCustomDataFromNbt(writestack);
        }

        return stack;
    }
}
