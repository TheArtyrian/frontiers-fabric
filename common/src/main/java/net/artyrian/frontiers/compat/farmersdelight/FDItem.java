package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.item.custom.tool.BrokenToolItem;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.artyrian.frontiers.reg.property.FRFoodComponents;
import net.artyrian.frontiers.reg.property.FRToolMaterial;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.DiscFragmentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.vertisoft.vectorlib.VectorLib;

import java.util.List;
import java.util.function.Supplier;

// A list of Farmer's Delight exclusive pack-in items.
public class FDItem
{
    // All items will start null.


    // Knives
    public static Supplier<Item> COBALT_KNIFE = null;
    public static Supplier<Item> OBSIDIAN_KNIFE = null;
    public static Supplier<Item> OBSIDIAN_KNIFE_BROKEN = null;
    public static Supplier<Item> VERDINITE_KNIFE = null;
    public static Supplier<Item> FROSTITE_KNIFE = null;
    public static Supplier<Item> VIVULITE_KNIFE = null;
    public static Supplier<Item> BRIMTAN_KNIFE = null;
    public static Supplier<Item> MOURNING_GOLD_KNIFE = null;
    public static Supplier<Item> BRIMTAN_SHELL_KNIFE = null;

    // Food
    public static Supplier<Item> TRUFFLE_PASTA = null;
    public static Supplier<Item> FRIED_GOLDEN_EGG = null;

    // Existing FD items; here for referencing!
    public static Supplier<Item> DIAMOND_KNIFE = null;
    public static Supplier<Item> NETHERITE_KNIFE = null;
    public static Supplier<Item> GOLDEN_KNIFE = null;

    public static Supplier<Item> RICE = null;
    public static Supplier<Item> ONION = null;
    public static Supplier<Item> CABBAGE_SEEDS = null;
    public static Supplier<Item> TOMATO_SEEDS = null;

    public static Supplier<Item> PASTA_WITH_MUTTON_CHOP = null;
    public static Supplier<Item> FRIED_EGG = null;
    public static Supplier<Item> STRAW = null;

    // References to the mod's potion effects.
    public static Supplier<MobEffect> NOURISHMENT;
    public static Supplier<Holder<MobEffect>> NOURISHMENT_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Supplier<Item> registerItem(String name, Supplier<Item> item)
    {
        return VectorLib.REGISTRY.registerItem(Frontiers.MOD_ID, name, item);
    }

    private static Supplier<Item> datagenTemp(String id, String name)
    {
        Supplier<Item> sup = () -> new Item(new Item.Properties());
        return VectorLib.REGISTRY.registerItem(id, name, sup);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN FD IS ENABLED!
    private static void registerItemsTrue()
    {
        NOURISHMENT = VectorLib.REGISTRY.getFromRegistry(BuiltInRegistries.MOB_EFFECT, Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "nourishment"), MobEffects.MOVEMENT_SPEED.value());
        NOURISHMENT_REG = () -> BuiltInRegistries.MOB_EFFECT.wrapAsHolder(NOURISHMENT.get());

        MOURNING_GOLD_KNIFE = registerItem("mourning_gold_knife", () ->
                new KnifeItem(FRToolMaterial.MOURNING_GOLD, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.MOURNING_GOLD, 0.5F, -2.0F))
                )
        );

        COBALT_KNIFE = registerItem("cobalt_knife", () ->
                new KnifeItem(FRToolMaterial.COBALT, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.COBALT, 0.5F, -2.0F))
                )
        );

        OBSIDIAN_KNIFE = registerItem("obsidian_knife", () ->
                new UnbreakableKnifeItem(
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "obsidian_knife_broken"),
                        FRToolMaterial.OBSIDIAN,
                        new Item.Properties().attributes(DiggerItem.createAttributes(FRToolMaterial.OBSIDIAN, 0.5F, -2.0F))
                )
        );
        OBSIDIAN_KNIFE_BROKEN = registerItem("obsidian_knife_broken", () ->
                new BrokenToolItem(
                        OBSIDIAN_KNIFE.get(),
                        FRToolMaterial.OBSIDIAN,
                        new Item.Properties().stacksTo(1))
        );

        VERDINITE_KNIFE = registerItem("verdinite_knife", () ->
                new KnifeItem(FRToolMaterial.VERDINITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.VERDINITE, 0.5F, -2.0F))
                )
        );

        FROSTITE_KNIFE = registerItem("frostite_knife", () ->
                new KnifeItem(FRToolMaterial.FROSTITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.FROSTITE, 0.5F, -2.0F))
                )
        );

        VIVULITE_KNIFE = registerItem("vivulite_knife", () ->
                new KnifeItem(FRToolMaterial.VIVULITE, new Item.Properties()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.VIVULITE, 0.5F, -2.0F))
                )
        );

        BRIMTAN_KNIFE = registerItem("brimtan_knife", () ->
                new KnifeItem(FRToolMaterial.BRIMTAN, new Item.Properties().fireResistant()
                        .attributes(DiggerItem.createAttributes(FRToolMaterial.BRIMTAN, 0.5F, -2.0F))
                )
        );

        TRUFFLE_PASTA = registerItem("truffle_pasta", () ->
                new ConsumableItem(true, List.of(
                        new MobEffectInstance(NOURISHMENT_REG.get(), 9600, 0, true, true)
                ),
                        new Item.Properties().craftRemainder(Items.BOWL).stacksTo(16).food((
                                        new FoodProperties.Builder())
                                        .nutrition(16)
                                        .saturationModifier(1.8F)
                                        .effect(
                                                new MobEffectInstance(NOURISHMENT_REG.get(), 9600, 0, true, true), 1)
                                        .build())
                                .craftRemainder(Items.BOWL)
                )
        );

        FRIED_GOLDEN_EGG = registerItem("fried_golden_egg", () ->
                new ConsumableItem(false, List.of(
                        new MobEffectInstance(FRStatusEffects.ALLUREMENT, 1800, 0, true, true)
                ),
                        new Item.Properties().food(
                                new FoodProperties.Builder()
                                        .nutrition(5)
                                        .saturationModifier(0.8F)
                                        .effect(
                                                new MobEffectInstance(FRStatusEffects.ALLUREMENT, 1800, 0, true, true), 1)
                                        .build()
                        )
                )
        );

        BRIMTAN_SHELL_KNIFE = registerItem("brimtan_shell_knife", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));

        // Existing items.
        GOLDEN_KNIFE = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "golden_knife"));
        DIAMOND_KNIFE = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "diamond_knife"));
        NETHERITE_KNIFE = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "netherite_knife"));

        RICE = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "rice"));
        ONION = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "onion"));
        CABBAGE_SEEDS = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"));
        TOMATO_SEEDS = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"));

        PASTA_WITH_MUTTON_CHOP = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "pasta_with_mutton_chop"));
        FRIED_EGG = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "fried_egg"));
        STRAW = () -> BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "straw"));
    }

    private static void registerItemsDatagen()
    {
        MOURNING_GOLD_KNIFE = datagenTemp(Frontiers.MOD_ID, "mourning_gold_knife");
        COBALT_KNIFE = datagenTemp(Frontiers.MOD_ID, "cobalt_knife");
        OBSIDIAN_KNIFE = datagenTemp(Frontiers.MOD_ID, "obsidian_knife");
        OBSIDIAN_KNIFE_BROKEN = datagenTemp(Frontiers.MOD_ID, "obsidian_knife_broken");
        VERDINITE_KNIFE = datagenTemp(Frontiers.MOD_ID, "verdinite_knife");
        FROSTITE_KNIFE = datagenTemp(Frontiers.MOD_ID, "frostite_knife");
        VIVULITE_KNIFE = datagenTemp(Frontiers.MOD_ID, "vivulite_knife");
        BRIMTAN_KNIFE = datagenTemp(Frontiers.MOD_ID, "brimtan_knife");
        TRUFFLE_PASTA = datagenTemp(Frontiers.MOD_ID, "truffle_pasta");
        FRIED_GOLDEN_EGG = registerItem("fried_golden_egg", () -> new Item(new Item.Properties().food(FRFoodComponents.COOKED_GUARDIAN_SLICE)));
        BRIMTAN_SHELL_KNIFE = datagenTemp(Frontiers.MOD_ID, "brimtan_shell_knife");

        // Existing items.
        GOLDEN_KNIFE = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "golden_knife");
        DIAMOND_KNIFE = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "diamond_knife");
        NETHERITE_KNIFE = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "netherite_knife");
        RICE = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "rice");
        ONION = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "onion");
        CABBAGE_SEEDS = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds");
        TOMATO_SEEDS = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds");
        PASTA_WITH_MUTTON_CHOP = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "pasta_with_mutton_chop");
        FRIED_EGG = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "fried_egg");
        STRAW = datagenTemp(Frontiers.FARMERS_DELIGHT_ID, "straw");
    }

    public static void registerModItems(boolean datagen)
    {
        if (datagen) registerItemsDatagen();
        else registerItemsTrue();
    }
}
