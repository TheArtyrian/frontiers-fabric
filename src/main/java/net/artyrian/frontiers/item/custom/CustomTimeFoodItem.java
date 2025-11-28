package net.artyrian.frontiers.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CustomTimeFoodItem extends Item
{
    private final int consumeTicks;

    public CustomTimeFoodItem(float eat_seconds, Settings settings)
    {
        super(settings);
        this.consumeTicks = (int)(eat_seconds * 20.0F);
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user)
    {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        return foodComponent != null ? this.consumeTicks : 0;
    }
}
