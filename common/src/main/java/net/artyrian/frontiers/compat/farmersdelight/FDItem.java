package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.item.custom.tool.BrokenToolItem;
import net.artyrian.frontiers.item.custom.tool.UnbreakableAxeItem;
import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.DiscFragmentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
    public static MobEffect NOURISHMENT;
    public static Holder<MobEffect> NOURISHMENT_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN FD IS ENABLED!
    public static void registerModItems()
    {
        NOURISHMENT = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "nourishment"));
        NOURISHMENT_REG = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(NOURISHMENT);

        MOURNING_GOLD_KNIFE = registerItem("mourning_gold_knife",
                new KnifeItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.MOURNING_GOLD, 0.5F, -2.0F))
                )
        );

        COBALT_KNIFE = registerItem("cobalt_knife",
                new KnifeItem(ModToolMaterial.COBALT, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.COBALT, 0.5F, -2.0F))
                )
        );

        OBSIDIAN_KNIFE = registerItem("obsidian_knife",
                new UnbreakableKnifeItem(
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "obsidian_knife_broken"),
                        ModToolMaterial.OBSIDIAN,
                        new Item.Properties().attributes(DiggerItem.createAttributes(ModToolMaterial.OBSIDIAN, 0.5F, -2.0F))
                )
        );
        OBSIDIAN_KNIFE_BROKEN = registerItem("obsidian_knife_broken",
                new BrokenToolItem(
                        OBSIDIAN_KNIFE,
                        ModToolMaterial.OBSIDIAN,
                        new Item.Properties().stacksTo(1))
        );

        VERDINITE_KNIFE = registerItem("verdinite_knife",
                new KnifeItem(ModToolMaterial.VERDINITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.VERDINITE, 0.5F, -2.0F))
                )
        );

        FROSTITE_KNIFE = registerItem("frostite_knife",
                new KnifeItem(ModToolMaterial.FROSTITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.FROSTITE, 0.5F, -2.0F))
                )
        );

        VIVULITE_KNIFE = registerItem("vivulite_knife",
                new KnifeItem(ModToolMaterial.VIVULITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.VIVULITE, 0.5F, -2.0F))
                )
        );

        BRIMTAN_KNIFE = registerItem("brimtan_knife",
                new KnifeItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant()
                        .attributes(DiggerItem.createAttributes(ModToolMaterial.BRIMTAN, 0.5F, -2.0F))
                )
        );

        TRUFFLE_PASTA = registerItem("truffle_pasta",
                new ConsumableItem(List.of(
                        new MobEffectInstance(NOURISHMENT_REG, 9600, 0, true, true)
                ),
                        new Item.Properties().stacksTo(16).food((
                                        new FoodProperties.Builder())
                                        .nutrition(16)
                                        .saturationModifier(1.8F)
                                        .effect(
                                                new MobEffectInstance(NOURISHMENT_REG, 9600, 0, true, true), 1)
                                        .build())
                                .craftRemainder(Items.BOWL)
            )
        );

        FRIED_GOLDEN_EGG = registerItem("fried_golden_egg",
                new ConsumableItem(List.of(
                        new MobEffectInstance(ModStatusEffects.ALLUREMENT, 1800, 0, true, true)
                ),
                        new Item.Properties().food(
                                new FoodProperties.Builder()
                                        .nutrition(5)
                                        .saturationModifier(0.8F)
                                        .effect(
                                                new MobEffectInstance(ModStatusEffects.ALLUREMENT, 1800, 0, true, true), 1)
                                        .build()
                        )
                )
        );

        BRIMTAN_SHELL_KNIFE = registerItem("brimtan_shell_knife", new DiscFragmentItem(new Item.Properties().fireResistant()));

        // Existing items.
        GOLDEN_KNIFE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "golden_knife"));
        DIAMOND_KNIFE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "diamond_knife"));
        NETHERITE_KNIFE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "netherite_knife"));

        RICE = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "rice"));
        ONION = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "onion"));
        CABBAGE_SEEDS = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"));
        TOMATO_SEEDS = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"));

        PASTA_WITH_MUTTON_CHOP = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "pasta_with_mutton_chop"));
        FRIED_EGG = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "fried_egg"));
    }
}
