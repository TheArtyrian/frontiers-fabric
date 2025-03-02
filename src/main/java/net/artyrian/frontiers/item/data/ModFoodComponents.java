package net.artyrian.frontiers.item.data;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents
{
    public static final FoodComponent MARSHMALLOW = new FoodComponent.Builder().nutrition(1).saturationModifier(0.5F).snack().build();
    public static final FoodComponent ROASTED_MARSHMALLOW = new FoodComponent.Builder().nutrition(2).saturationModifier(0.75F).snack().build();
    public static final FoodComponent LEVI_ROLL = new FoodComponent.Builder().nutrition(3).snack().alwaysEdible().saturationModifier(0.45F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0), 0.4F)
            .build();
    public static final FoodComponent TRUFFLE = new FoodComponent.Builder().nutrition(3).saturationModifier(6.7F).build();
    public static final FoodComponent APPLE_OF_ENLIGHTENMENT = new FoodComponent.Builder().nutrition(8).saturationModifier(1.2F).alwaysEdible().build();
}