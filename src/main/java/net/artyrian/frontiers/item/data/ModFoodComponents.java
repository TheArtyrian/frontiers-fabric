package net.artyrian.frontiers.item.data;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents
{
    public static final FoodComponent MARSHMALLOW = new FoodComponent.Builder().nutrition(1).saturationModifier(0.5F).snack().build();
    public static final FoodComponent ROASTED_MARSHMALLOW = new FoodComponent.Builder().nutrition(2).saturationModifier(0.75F).snack().build();
    public static final FoodComponent LEVI_ROLL = new FoodComponent.Builder().nutrition(3).saturationModifier(0.45F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0), 0.4F)
            .build();
}
