package net.artyrian.frontiers.reg.property;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FRFoodComponents
{
    public static final FoodProperties MARSHMALLOW = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5F).fast().build();
    public static final FoodProperties FRUITCAKE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
    public static final FoodProperties ROASTED_MARSHMALLOW = new FoodProperties.Builder().nutrition(2).saturationModifier(0.25F).fast().build();
    public static final FoodProperties LEVI_ROLL = new FoodProperties.Builder().nutrition(3).fast().alwaysEdible().saturationModifier(0.45F)
            .effect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0), 0.4F)
            .build();
    public static final FoodProperties POMEGRANATE = new FoodProperties.Builder().nutrition(1).saturationModifier(1.2F).build();
    public static final FoodProperties TRUFFLE = new FoodProperties.Builder().nutrition(3).saturationModifier(6.7F).build();
    public static final FoodProperties TRUFFLE_POTATO_PUFF = new FoodProperties.Builder().nutrition(4).saturationModifier(2.5F).fast().build();
    public static final FoodProperties TRUFFLE_OIL = new FoodProperties.Builder().nutrition(1).saturationModifier(15.0F)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 2400, 1), 0.2F)
            .build();

    public static final FoodProperties APPLE_OF_ENLIGHTENMENT = new FoodProperties.Builder().nutrition(8).saturationModifier(1.2F).alwaysEdible().build();

    public static final FoodProperties GUARDIAN_SLICE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).build();
    public static final FoodProperties ELDER_GUARDIAN_SLICE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
    public static final FoodProperties COOKED_GUARDIAN_SLICE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).build();
    public static final FoodProperties COOKED_ELDER_GUARDIAN_SLICE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).build();

    public static final FoodProperties GLISTERING_MELON_REWORK = new FoodProperties.Builder().nutrition(3).saturationModifier(1.5F)
            .effect(new MobEffectInstance(MobEffects.HEAL, 1, 0, true, false), 0.5F)
            .build();
}