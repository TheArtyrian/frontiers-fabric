package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
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
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        // Server
        if (!world.isClient())
        {
            NbtCompound writestack = new NbtCompound();
            user.writeCustomDataToNbt(writestack);
            writestack.putBoolean(nbt_to_set, value);
            user.readCustomDataFromNbt(writestack);
        }

        return super.finishUsing(stack, world, user);
    }
}
