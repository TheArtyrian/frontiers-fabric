package net.artyrian.frontiers.item.data;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents
{
    public static final FoodComponent MARSHMALLOW = new FoodComponent.Builder().nutrition(1).saturationModifier(0.5F).snack().build();
    public static final FoodComponent FRUITCAKE = new FoodComponent.Builder().nutrition(6).saturationModifier(0.8F).build();
    public static final FoodComponent ROASTED_MARSHMALLOW = new FoodComponent.Builder().nutrition(2).saturationModifier(0.75F).snack().build();
    public static final FoodComponent LEVI_ROLL = new FoodComponent.Builder().nutrition(3).snack().alwaysEdible().saturationModifier(0.45F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 0), 0.4F)
            .build();
    public static final FoodComponent POMEGRANATE = new FoodComponent.Builder().nutrition(1).saturationModifier(1.2F).build();
    public static final FoodComponent TRUFFLE = new FoodComponent.Builder().nutrition(3).saturationModifier(6.7F).build();
    public static final FoodComponent TRUFFLE_POTATO_PUFF = new FoodComponent.Builder().nutrition(4).saturationModifier(2.5F).snack().build();
    public static final FoodComponent TRUFFLE_OIL = new FoodComponent.Builder().nutrition(1).saturationModifier(15.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 2400, 1), 0.2F)
            .build();

    public static final FoodComponent APPLE_OF_ENLIGHTENMENT = new FoodComponent.Builder().nutrition(8).saturationModifier(1.2F).alwaysEdible().build();

    public static final FoodComponent GUARDIAN_SLICE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.2F).build();
    public static final FoodComponent ELDER_GUARDIAN_SLICE = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4F).build();
    public static final FoodComponent COOKED_GUARDIAN_SLICE = new FoodComponent.Builder().nutrition(6).saturationModifier(0.5F).build();
    public static final FoodComponent COOKED_ELDER_GUARDIAN_SLICE = new FoodComponent.Builder().nutrition(8).saturationModifier(0.6F).build();

    public static final FoodComponent GLISTERING_MELON_REWORK = new FoodComponent.Builder().nutrition(3).saturationModifier(1.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 0, true, false), 0.5F)
            .build();
}