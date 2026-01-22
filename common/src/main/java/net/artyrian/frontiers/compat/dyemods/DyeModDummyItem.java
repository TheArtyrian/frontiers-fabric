package net.artyrian.frontiers.compat.dyemods;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

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
    public static Item UMBER_DYE;
    public static Item LAVENDER_DYE;

    public static Item CORAL_BALL;
    public static Item CANARY_BALL;
    public static Item WASABI_BALL;
    public static Item SACRAMENTO_BALL;
    public static Item SKY_BALL;
    public static Item BLURPLE_BALL;
    public static Item SANGRIA_BALL;
    public static Item ROSE_BALL;
    public static Item UMBER_BALL;
    public static Item LAVENDER_BALL;

    // Delicate Dyes
    public static void registerDDyeItems()
    {
        CORAL_DYE = registerItemDD("coral_dye", new Item(new Item.Properties()));
        CORAL_BALL = registerItem("coral_ball", new Item(new Item.Properties()));

        CANARY_DYE = registerItemDD("canary_dye", new Item(new Item.Properties()));
        CANARY_BALL = registerItem("canary_ball", new Item(new Item.Properties()));

        WASABI_DYE = registerItemDD("wasabi_dye", new Item(new Item.Properties()));
        WASABI_BALL = registerItem("wasabi_ball", new Item(new Item.Properties()));

        SACRAMENTO_DYE = registerItemDD("sacramento_dye", new Item(new Item.Properties()));
        SACRAMENTO_BALL = registerItem("sacramento_ball", new Item(new Item.Properties()));

        SKY_DYE = registerItemDD("sky_dye", new Item(new Item.Properties()));
        SKY_BALL = registerItem("sky_ball", new Item(new Item.Properties()));

        BLURPLE_DYE = registerItemDD("blurple_dye", new Item(new Item.Properties()));
        BLURPLE_BALL = registerItem("blurple_ball", new Item(new Item.Properties()));

        SANGRIA_DYE = registerItemDD("sangria_dye", new Item(new Item.Properties()));
        SANGRIA_BALL = registerItem("sangria_ball", new Item(new Item.Properties()));

        ROSE_DYE = registerItemDD("rose_dye", new Item(new Item.Properties()));
        ROSE_BALL = registerItem("rose_ball", new Item(new Item.Properties()));

        UMBER_DYE = registerItemDD("umber_dye", new Item(new Item.Properties()));
        UMBER_BALL = registerItem("umber_ball", new Item(new Item.Properties()));

        LAVENDER_DYE = registerItemDD("lavender_dye", new Item(new Item.Properties()));
        LAVENDER_BALL = registerItem("lavender_ball", new Item(new Item.Properties()));
    }

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name), item);
    }

    private static Item registerItemDD(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, name), item);
    }
}
