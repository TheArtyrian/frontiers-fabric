package net.artyrian.frontiers.compat.dyemods;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Solely comprised of dummy items.
public class DyeModDummyItem
{
    // Delicate Dyes
    public static Item CORAL_DYE;
    public static Item CANARY_DYE;
    public static Item WASABI_DYE;
    public static Item SACRAMENTO_DYE;
    public static Item SKY_DYE;
    public static Item BLURPLE_DYE;
    public static Item SANGRIA_DYE;
    public static Item ROSE_DYE;

    public static Item CORAL_BALL;
    public static Item CANARY_BALL;
    public static Item WASABI_BALL;
    public static Item SACRAMENTO_BALL;
    public static Item SKY_BALL;
    public static Item BLURPLE_BALL;
    public static Item SANGRIA_BALL;
    public static Item ROSE_BALL;

    // Delicate Dyes
    public static void registerDDyeItems()
    {
        CORAL_DYE = registerItemDD("coral_dye", new Item(new Item.Settings()));
        CORAL_BALL = registerItem("coral_ball", new Item(new Item.Settings()));

        CANARY_DYE = registerItemDD("canary_dye", new Item(new Item.Settings()));
        CANARY_BALL = registerItem("canary_ball", new Item(new Item.Settings()));

        WASABI_DYE = registerItemDD("wasabi_dye", new Item(new Item.Settings()));
        WASABI_BALL = registerItem("wasabi_ball", new Item(new Item.Settings()));

        SACRAMENTO_DYE = registerItemDD("sacramento_dye", new Item(new Item.Settings()));
        SACRAMENTO_BALL = registerItem("sacramento_ball", new Item(new Item.Settings()));

        SKY_DYE = registerItemDD("sky_dye", new Item(new Item.Settings()));
        SKY_BALL = registerItem("sky_ball", new Item(new Item.Settings()));

        BLURPLE_DYE = registerItemDD("blurple_dye", new Item(new Item.Settings()));
        BLURPLE_BALL = registerItem("blurple_ball", new Item(new Item.Settings()));

        SANGRIA_DYE = registerItemDD("sangria_dye", new Item(new Item.Settings()));
        SANGRIA_BALL = registerItem("sangria_ball", new Item(new Item.Settings()));

        ROSE_DYE = registerItemDD("rose_dye", new Item(new Item.Settings()));
        ROSE_BALL = registerItem("rose_ball", new Item(new Item.Settings()));
    }

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    private static Item registerItemDD(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.DELICATE_DYES_ID, name), item);
    }
}
