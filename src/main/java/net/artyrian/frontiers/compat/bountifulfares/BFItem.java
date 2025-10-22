package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;

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
    public static StatusEffect ENRICHMENT;
    public static RegistryEntry<StatusEffect> ENRICHMENT_REG;
    public static StatusEffect RESTORATION;
    public static RegistryEntry<StatusEffect> RESTORATION_REG;
    public static StatusEffect ACIDIC;
    public static RegistryEntry<StatusEffect> ACIDIC_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    public static void registerModItems()
    {
        // Register status effects. Risky? Hahahahahahahaha
        ENRICHMENT = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"));
        RESTORATION = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "restoration"));
        ACIDIC = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "acidic"));
        ENRICHMENT_REG = Registries.STATUS_EFFECT.getEntry(ENRICHMENT);
        RESTORATION_REG = Registries.STATUS_EFFECT.getEntry(RESTORATION);
        ACIDIC_REG = Registries.STATUS_EFFECT.getEntry(ACIDIC);

        // Register new items.
        // Guardian Soup
        GUARDIAN_SOUP = registerItem("guardian_soup",
                new StackableBowlFoodItem(List.of(
                        new StatusEffectInstance(ENRICHMENT_REG, 1200, 0, true, true),
                        new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 0, true, true)
                ),
                        new Item.Settings().maxCount(16).food((
                                new FoodComponent.Builder())
                                .nutrition(14)
                                .saturationModifier(0.5F)
                                .statusEffect(
                                        new StatusEffectInstance(ENRICHMENT_REG, 1200, 0, true, true), 1)
                                .statusEffect(
                                        new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 0, true, true), 1)
                                .build())
                                .recipeRemainder(Items.BOWL))
        );

        // Elden Bowl (guys no way Elden Ring referenced?!)
        ELDEN_BOWL = registerItem("elden_bowl",
                new StackableBowlFoodItem(List.of(
                        new StatusEffectInstance(RESTORATION_REG, 1200, 0, true, true),
                        new StatusEffectInstance(StatusEffects.HASTE, 600, 0, true, true)
                ),
                        new Item.Settings().maxCount(16).food((
                                        new FoodComponent.Builder())
                                        .nutrition(18)
                                        .saturationModifier(0.4F)
                                        .statusEffect(
                                                new StatusEffectInstance(RESTORATION_REG, 1200, 0, true, true), 1)
                                        .statusEffect(
                                                new StatusEffectInstance(StatusEffects.HASTE, 600, 0, true, true), 1)
                                        .build())
                                .recipeRemainder(Items.BOWL))
                );

        // Breaded Guardian
        BREADED_GUARDIAN = registerItem("breaded_guardian",
                new EffectFoodItem(List.of(
                        new StatusEffectInstance(ACIDIC_REG, 600, 0),
                        new StatusEffectInstance(RESTORATION_REG, 200, 0, true, true)
                ),
                        new Item.Settings().food(
                                new FoodComponent.Builder()
                                        .nutrition(10)
                                        .saturationModifier(0.7f)
                                        .statusEffect(
                                                new StatusEffectInstance(ACIDIC_REG, 600, 0),1)
                                        .statusEffect(
                                                new StatusEffectInstance(RESTORATION_REG, 200, 0, true, true),1)
                                        .build()
                        )
                )
        );

        // Melon Spritzer
        MELON_SPRITZER_BOTTLE = registerItem("melon_spritzer_bottle",
                new LiquidBottleItem(
                        List.of(new StatusEffectInstance(RESTORATION_REG, 600, 1)),
                        new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE)
                        .food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f)
                                .statusEffect(new StatusEffectInstance(RESTORATION_REG, 600, 1), 1.0F)
                                .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.3F).alwaysEdible().build())
                        .maxCount(16))
        );

        // Glistering Spritzer
        GLISTERING_SPRITZER_BOTTLE = registerItem("glistering_spritzer_bottle",
                new LiquidBottleItem(
                        List.of(new StatusEffectInstance(RESTORATION_REG, 800, 1)),
                        new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE)
                                .food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f)
                                        .statusEffect(new StatusEffectInstance(RESTORATION_REG, 800, 1), 1.0F)
                                        .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1, true, false), 0.8F)
                                        .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.3F).alwaysEdible().build())
                                .maxCount(16))
        );

        // Locate existing items.
        FELDSPAR = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "feldspar"));
        SPONGEKIN_SLICE = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "spongekin_slice"));
        PASSION_GLAZED_SALMON = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "passion_glazed_salmon"));
        COCONUT_CRUSTED_COD = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "coconut_crusted_cod"));
        COCONUT_MILK_BOTTLE = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "coconut_milk_bottle"));
        if (Frontiers.DOING_DATAGEN)
        {
            LEEK = Registry.register(Registries.ITEM, Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "leek"), new Item(new Item.Settings()));
        }
        else
        {
            LEEK = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "leek"));
        }
    }
}
