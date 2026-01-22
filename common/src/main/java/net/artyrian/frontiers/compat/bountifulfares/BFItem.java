package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.List;
import java.util.Optional;

// A list of Bountiful Fares exclusive pack-in items.
public class BFItem
{
    // Compat items
    public static Item GUARDIAN_SOUP = null;
    public static Item ELDEN_BOWL = null;
    public static Item BREADED_GUARDIAN = null;
    public static Item MELON_SPRITZER_BOTTLE = null;
    public static Item GLISTERING_SPRITZER_BOTTLE = null;

    // public static Item PICKLE = null;
    // public static Item PICKLED_PEPPER = null;

    // Existing BF items; here for referencing!
    public static Item FELDSPAR = null;
    public static Item SPONGEKIN_SLICE = null;
    public static Item PASSION_GLAZED_SALMON = null;
    public static Item COCONUT_CRUSTED_COD = null;
    public static Item COCONUT_MILK_BOTTLE = null;
    public static Item LEEK = null;

    // References to the mod's potion effects.
    public static MobEffect ENRICHMENT;
    public static Holder<MobEffect> ENRICHMENT_REG;
    public static MobEffect RESTORATION;
    public static Holder<MobEffect> RESTORATION_REG;
    public static MobEffect ACIDIC;
    public static Holder<MobEffect> ACIDIC_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    public static void registerModItems()
    {
        // Register status effects. Risky? Hahahahahahahaha
        ENRICHMENT = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"));
        RESTORATION = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "restoration"));
        ACIDIC = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "acidic"));
        ENRICHMENT_REG = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ENRICHMENT);
        RESTORATION_REG = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(RESTORATION);
        ACIDIC_REG = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ACIDIC);

        // Register new items.
        // Guardian Soup
        GUARDIAN_SOUP = registerItem("guardian_soup",
                new StackableBowlFoodItem(List.of(
                        new MobEffectInstance(ENRICHMENT_REG, 1200, 0, true, true),
                        new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0, true, true)
                ),
                        new Item.Properties().stacksTo(16).food((
                                new FoodProperties.Builder())
                                .nutrition(14)
                                .saturationModifier(0.5F)
                                .effect(
                                        new MobEffectInstance(ENRICHMENT_REG, 1200, 0, true, true), 1)
                                .effect(
                                        new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0, true, true), 1)
                                .build())
                                .craftRemainder(Items.BOWL))
        );

        // Elden Bowl (guys no way Elden Ring referenced?!)
        ELDEN_BOWL = registerItem("elden_bowl",
                new StackableBowlFoodItem(List.of(
                        new MobEffectInstance(RESTORATION_REG, 1200, 0, true, true),
                        new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0, true, true)
                ),
                        new Item.Properties().stacksTo(16).food((
                                        new FoodProperties.Builder())
                                        .nutrition(18)
                                        .saturationModifier(0.4F)
                                        .effect(
                                                new MobEffectInstance(RESTORATION_REG, 1200, 0, true, true), 1)
                                        .effect(
                                                new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0, true, true), 1)
                                        .build())
                                .craftRemainder(Items.BOWL))
                );

        // Breaded Guardian
        BREADED_GUARDIAN = registerItem("breaded_guardian",
                new EffectFoodItem(List.of(
                        new MobEffectInstance(ACIDIC_REG, 600, 0),
                        new MobEffectInstance(RESTORATION_REG, 200, 0, true, true)
                ),
                        new Item.Properties().food(
                                new FoodProperties.Builder()
                                        .nutrition(10)
                                        .saturationModifier(0.7f)
                                        .effect(
                                                new MobEffectInstance(ACIDIC_REG, 600, 0),1)
                                        .effect(
                                                new MobEffectInstance(RESTORATION_REG, 200, 0, true, true),1)
                                        .build()
                        )
                )
        );

        // Melon Spritzer
        MELON_SPRITZER_BOTTLE = registerItem("melon_spritzer_bottle",
                new LiquidBottleItem(
                        List.of(new MobEffectInstance(RESTORATION_REG, 600, 1)),
                        new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)
                        .food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
                                .effect(new MobEffectInstance(RESTORATION_REG, 600, 1), 1.0F)
                                .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.3F).alwaysEdible().build())
                        .stacksTo(16))
        );

        // Glistering Spritzer
        GLISTERING_SPRITZER_BOTTLE = registerItem("glistering_spritzer_bottle",
                new LiquidBottleItem(
                        List.of(new MobEffectInstance(RESTORATION_REG, 800, 1)),
                        new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)
                                .food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
                                        .effect(new MobEffectInstance(RESTORATION_REG, 800, 1), 1.0F)
                                        .effect(new MobEffectInstance(MobEffects.HEAL, 1, 1, true, false), 0.8F)
                                        .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.3F).alwaysEdible().build())
                                .stacksTo(16))
        );

        // Locate existing items.
        FELDSPAR = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "feldspar"));
        SPONGEKIN_SLICE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "spongekin_slice"));
        PASSION_GLAZED_SALMON = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "passion_glazed_salmon"));
        COCONUT_CRUSTED_COD = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "coconut_crusted_cod"));
        COCONUT_MILK_BOTTLE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "coconut_milk_bottle"));
        if (Frontiers.DOING_DATAGEN)
        {
            LEEK = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "leek"), new Item(new Item.Properties()));
        }
        else
        {
            LEEK = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "leek"));
        }
    }
}
