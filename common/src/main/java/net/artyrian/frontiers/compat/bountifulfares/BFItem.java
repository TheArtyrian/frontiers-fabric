package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.FRIntegReg;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.vertisoft.vectorlib.VectorLib;

import java.util.List;
import java.util.function.Supplier;

// A list of Bountiful Fares exclusive pack-in items.
public class BFItem
{
    // Compat items
    public static Supplier<Item> GUARDIAN_SOUP = null;
    public static Supplier<Item> ELDEN_BOWL = null;
    public static Supplier<Item> BREADED_GUARDIAN = null;
    public static Supplier<Item> MELON_SPRITZER_BOTTLE = null;
    public static Supplier<Item> GLISTERING_SPRITZER_BOTTLE = null;

    // public static Supplier<Item> PICKLE = null;
    // public static Supplier<Item> PICKLED_PEPPER = null;

    // Existing BF items; here for referencing!
    public static Supplier<Item> WALNUT = null;
    public static Supplier<Item> LAPISBERRIES = null;
    public static Supplier<Item> FELDSPAR = null;
    public static Supplier<Item> SPONGEKIN_SLICE = null;
    public static Supplier<Item> PASSION_GLAZED_SALMON = null;
    public static Supplier<Item> COCONUT_CRUSTED_COD = null;
    public static Supplier<Item> COCONUT_MILK_BOTTLE = null;
    public static Supplier<Item> LEEK = null;
    public static Supplier<Item> COCONUT_COIR = null;
    public static Supplier<Item> FLOUR = null;
    public static Supplier<Item> CITRUS_ESSENCE = null;
    public static Supplier<Item> ELDERBERRIES = null;
    public static Supplier<Item> PICKLED_SPONGEKIN = null;
    public static Supplier<Item> LEMON = null;

    // References to the mod's potion effects.
    public static Supplier<MobEffect> ENRICHMENT;
    public static Supplier<Holder<MobEffect>> ENRICHMENT_REG;
    public static Supplier<MobEffect> RESTORATION;
    public static Supplier<Holder<MobEffect>> RESTORATION_REG;
    public static Supplier<MobEffect> ACIDIC;
    public static Supplier<Holder<MobEffect>> ACIDIC_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Supplier<Item> registerItem(String name, Supplier<Item> item)
    {
        Supplier<Item> returnable = VectorLib.REGISTRY.registerItem(Frontiers.MOD_ID, name, item);
        FRIntegReg.INTEG_ITEMS.add(returnable);
        return returnable;
    }

    private static Supplier<Item> datagenTemp(String id, String name)
    {
        Supplier<Item> sup = () -> new Item(new Item.Properties());
        return VectorLib.REGISTRY.registerItem(id, name, sup);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    private static void registerItemsTrue()
    {
        // Register status effects. Risky? Hahahahahahahaha
        ENRICHMENT = VectorLib.REGISTRY.getFromRegistry(BuiltInRegistries.MOB_EFFECT, Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"), MobEffects.MOVEMENT_SPEED.value());
        RESTORATION = VectorLib.REGISTRY.getFromRegistry(BuiltInRegistries.MOB_EFFECT, Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "restoration"), MobEffects.REGENERATION.value());
        ACIDIC = VectorLib.REGISTRY.getFromRegistry(BuiltInRegistries.MOB_EFFECT, Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "acidic"), MobEffects.DAMAGE_BOOST.value());
        ENRICHMENT_REG = () -> BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ENRICHMENT.get());
        RESTORATION_REG = () -> BuiltInRegistries.MOB_EFFECT.wrapAsHolder(RESTORATION.get());
        ACIDIC_REG = () -> BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ACIDIC.get());

        // Guardian Soup
        GUARDIAN_SOUP = registerItem("guardian_soup", () ->
                new StackableBowlFoodItem(List.of(
                        //new MobEffectInstance(ENRICHMENT_REG.get(), 1200, 0, true, true),
                        new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0, true, true)
                ),
                        new Item.Properties().stacksTo(16).food((
                                        new FoodProperties.Builder())
                                        .nutrition(14)
                                        .saturationModifier(0.5F)
                                        //.effect(
                                        //        new MobEffectInstance(ENRICHMENT_REG.get(), 1200, 0, true, true), 1)
                                        .effect(
                                                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0, true, true), 1)
                                        .build())
                                .craftRemainder(Items.BOWL))
        );

        // Elden Bowl (guys no way Elden Ring referenced?!)
        ELDEN_BOWL = registerItem("elden_bowl", () ->
                new StackableBowlFoodItem(List.of(
                        //new MobEffectInstance(RESTORATION_REG.get(), 1200, 0, true, true),
                        new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0, true, true)
                ),
                        new Item.Properties().stacksTo(16).food((
                                        new FoodProperties.Builder())
                                        .nutrition(18)
                                        .saturationModifier(0.4F)
                                        //.effect(
                                        //        new MobEffectInstance(RESTORATION_REG.get(), 1200, 0, true, true), 1)
                                        .effect(
                                                new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0, true, true), 1)
                                        .build())
                                .craftRemainder(Items.BOWL))
        );

        // Breaded Guardian
        BREADED_GUARDIAN = registerItem("breaded_guardian", () ->
                new EffectFoodItem(List.of(
                        //new MobEffectInstance(ACIDIC_REG.get(), 600, 0),
                        new MobEffectInstance(RESTORATION_REG.get(), 200, 0, true, true)
                ),
                        new Item.Properties().food(
                                new FoodProperties.Builder()
                                        .nutrition(10)
                                        .saturationModifier(0.7f)
                                        //.effect(
                                        //        new MobEffectInstance(ACIDIC_REG.get(), 600, 0),1)
                                        //.effect(
                                        //        new MobEffectInstance(RESTORATION_REG.get(), 200, 0, true, true),1)
                                        .build()
                        )
                )
        );

        // Melon Spritzer
        MELON_SPRITZER_BOTTLE = registerItem("melon_spritzer_bottle", () ->
                new LiquidBottleItem(
                        //List.of(new MobEffectInstance(RESTORATION_REG.get(), 600, 1)),
                        new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)
                                .food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
                                        //.effect(new MobEffectInstance(RESTORATION_REG.get(), 600, 1), 1.0F)
                                        .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.3F).alwaysEdible().build())
                                .stacksTo(16))
        );

        // Glistering Spritzer
        GLISTERING_SPRITZER_BOTTLE = registerItem("glistering_spritzer_bottle", () ->
                new LiquidBottleItem(
                        //List.of(new MobEffectInstance(RESTORATION_REG.get(), 800, 1)),
                        new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)
                                .food(new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
                                        //.effect(new MobEffectInstance(RESTORATION_REG.get(), 800, 1), 1.0F)
                                        .effect(new MobEffectInstance(MobEffects.HEAL, 1, 1, true, false), 0.8F)
                                        .effect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0), 0.3F).alwaysEdible().build())
                                .stacksTo(16))
        );

        // Locate existing items.
        FELDSPAR = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "feldspar"));
        SPONGEKIN_SLICE = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "spongekin_slice"));
        PASSION_GLAZED_SALMON = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "passion_glazed_salmon"));
        COCONUT_CRUSTED_COD = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "coconut_crusted_cod"));
        COCONUT_MILK_BOTTLE = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "coconut_milk_bottle"));
        LEEK = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "leek"));
        COCONUT_COIR = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "coconut_coir"));
        WALNUT = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "walnut"));
        LAPISBERRIES = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "lapisberries"));
        FLOUR = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "flour"));
        CITRUS_ESSENCE = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "citrus_essence"));
        ELDERBERRIES = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "elderberries"));
        PICKLED_SPONGEKIN = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "pickled_spongekin"));
        LEMON = () -> BuiltInRegistries.ITEM.get(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "lemon"));
    }

    private static void registerItemsDatagen()
    {
        GUARDIAN_SOUP = datagenTemp(Frontiers.MOD_ID,"guardian_soup");
        ELDEN_BOWL = datagenTemp(Frontiers.MOD_ID,"elden_bowl");
        BREADED_GUARDIAN = datagenTemp(Frontiers.MOD_ID,"breaded_guardian");
        MELON_SPRITZER_BOTTLE = datagenTemp(Frontiers.MOD_ID,"melon_spritzer_bottle");
        GLISTERING_SPRITZER_BOTTLE = datagenTemp(Frontiers.MOD_ID,"glistering_spritzer_bottle");

        FELDSPAR = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "feldspar");
        SPONGEKIN_SLICE = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "spongekin_slice");
        PASSION_GLAZED_SALMON = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "passion_glazed_salmon");
        COCONUT_CRUSTED_COD = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "coconut_crusted_cod");
        COCONUT_MILK_BOTTLE = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "coconut_milk_bottle");
        LEEK = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "leek");
        COCONUT_COIR = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "coconut_coir");
        WALNUT = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "walnut");
        LAPISBERRIES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "lapisberries");
        FLOUR = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "flour");
        CITRUS_ESSENCE = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "citrus_essence");
        ELDERBERRIES = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "elderberries");
        PICKLED_SPONGEKIN = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "pickled_spongekin");
        LEMON = datagenTemp(Frontiers.BOUNTIFUL_FARES_ID, "lemon");
    }

    public static void registerModItems(boolean datagen)
    {
        if (datagen) registerItemsDatagen();
        else registerItemsTrue();
    }
}
