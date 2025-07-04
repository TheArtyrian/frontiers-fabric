package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.item.custom.tool.BrokenToolItem;
import net.artyrian.frontiers.item.custom.tool.UnbreakableAxeItem;
import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.DiscFragmentItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;

// A list of Farmer's Delight exclusive pack-in items.
public class FDItem
{
    // All items will start null.


    // Knives
    public static Item COBALT_KNIFE = null;
    public static Item OBSIDIAN_KNIFE = null;
    public static Item OBSIDIAN_KNIFE_BROKEN = null;
    public static Item VERDINITE_KNIFE = null;
    public static Item FROSTITE_KNIFE = null;
    public static Item VIVULITE_KNIFE = null;
    public static Item BRIMTAN_KNIFE = null;
    public static Item MOURNING_GOLD_KNIFE = null;
    public static Item BRIMTAN_SHELL_KNIFE = null;

    // Food
    public static Item TRUFFLE_PASTA = null;
    public static Item FRIED_GOLDEN_EGG = null;

    // Existing FD items; here for referencing!
    public static Item DIAMOND_KNIFE = null;
    public static Item NETHERITE_KNIFE = null;
    public static Item GOLDEN_KNIFE = null;

    public static Item RICE = null;
    public static Item ONION = null;
    public static Item CABBAGE_SEEDS = null;
    public static Item TOMATO_SEEDS = null;

    public static Item PASTA_WITH_MUTTON_CHOP = null;
    public static Item FRIED_EGG = null;

    // References to the mod's potion effects.
    public static StatusEffect NOURISHMENT;
    public static RegistryEntry<StatusEffect> NOURISHMENT_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN FD IS ENABLED!
    public static void registerModItems()
    {
        NOURISHMENT = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "nourishment"));
        NOURISHMENT_REG = Registries.STATUS_EFFECT.getEntry(NOURISHMENT);

        MOURNING_GOLD_KNIFE = registerItem("mourning_gold_knife",
                new KnifeItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, 0.5F, -2.0F))
                )
        );

        COBALT_KNIFE = registerItem("cobalt_knife",
                new KnifeItem(ModToolMaterial.COBALT, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.COBALT, 0.5F, -2.0F))
                )
        );

        OBSIDIAN_KNIFE = registerItem("obsidian_knife",
                new UnbreakableKnifeItem(
                        Identifier.of(Frontiers.MOD_ID, "obsidian_knife_broken"),
                        ModToolMaterial.OBSIDIAN,
                        new Item.Settings().attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 0.5F, -2.0F))
                )
        );
        OBSIDIAN_KNIFE_BROKEN = registerItem("obsidian_knife_broken",
                new BrokenToolItem(
                        OBSIDIAN_KNIFE,
                        ModToolMaterial.OBSIDIAN,
                        new Item.Settings().maxCount(1))
        );

        VERDINITE_KNIFE = registerItem("verdinite_knife",
                new KnifeItem(ModToolMaterial.VERDINITE, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.VERDINITE, 0.5F, -2.0F))
                )
        );

        FROSTITE_KNIFE = registerItem("frostite_knife",
                new KnifeItem(ModToolMaterial.FROSTITE, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.FROSTITE, 0.5F, -2.0F))
                )
        );

        VIVULITE_KNIFE = registerItem("vivulite_knife",
                new KnifeItem(ModToolMaterial.VIVULITE, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.VIVULITE, 0.5F, -2.0F))
                )
        );

        BRIMTAN_KNIFE = registerItem("brimtan_knife",
                new KnifeItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, 0.5F, -2.0F))
                )
        );

        TRUFFLE_PASTA = registerItem("truffle_pasta",
                new ConsumableItem(List.of(
                        new StatusEffectInstance(NOURISHMENT_REG, 9600, 0, true, true)
                ),
                        new Item.Settings().maxCount(16).food((
                                        new FoodComponent.Builder())
                                        .nutrition(16)
                                        .saturationModifier(1.8F)
                                        .statusEffect(
                                                new StatusEffectInstance(NOURISHMENT_REG, 9600, 0, true, true), 1)
                                        .build())
                                .recipeRemainder(Items.BOWL)
            )
        );

        FRIED_GOLDEN_EGG = registerItem("fried_golden_egg",
                new ConsumableItem(List.of(
                        new StatusEffectInstance(ModStatusEffects.ALLUREMENT, 1800, 0, true, true)
                ),
                        new Item.Settings().food(
                                new FoodComponent.Builder()
                                        .nutrition(5)
                                        .saturationModifier(0.8F)
                                        .statusEffect(
                                                new StatusEffectInstance(ModStatusEffects.ALLUREMENT, 1800, 0, true, true), 1)
                                        .build()
                        )
                )
        );

        BRIMTAN_SHELL_KNIFE = registerItem("brimtan_shell_knife", new DiscFragmentItem(new Item.Settings().fireproof()));

        // Existing items.
        GOLDEN_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "golden_knife"));
        DIAMOND_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "diamond_knife"));
        NETHERITE_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "netherite_knife"));

        RICE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "rice"));
        ONION = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "onion"));
        CABBAGE_SEEDS = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"));
        TOMATO_SEEDS = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"));

        PASTA_WITH_MUTTON_CHOP = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "pasta_with_mutton_chop"));
        FRIED_EGG = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "fried_egg"));
    }
}
