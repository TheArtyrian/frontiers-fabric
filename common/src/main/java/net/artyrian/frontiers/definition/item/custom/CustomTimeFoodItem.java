package net.artyrian.frontiers.definition.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomTimeFoodItem extends Item
{
    private final int consumeTicks;

    public CustomTimeFoodItem(float eat_seconds, Properties settings)
    {
        super(settings);
        this.consumeTicks = (int)(eat_seconds * 20.0F);
    }

    public int getUseDuration(ItemStack stack, LivingEntity user)
    {
        FoodProperties foodComponent = stack.get(DataComponents.FOOD);
        return foodComponent != null ? this.consumeTicks : 0;
    }
}
