package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// A list of Farmer's Delight exclusive pack-in items.
public class FDItem
{
    // All items will start null.

    // Knives
    public static Item COBALT_KNIFE = null;
    public static Item OBSIDIAN_KNIFE = null;
    public static Item MOURNING_GOLD_KNIFE = null;
    public static Item DIAMOND_KNIFE = null;
    public static Item NETHERITE_KNIFE = null;
    public static Item GOLDEN_KNIFE = null;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN FD IS ENABLED!
    public static void registerModItems()
    {
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
                new KnifeItem(ModToolMaterial.OBSIDIAN, new Item.Settings()
                        .attributeModifiers(MiningToolItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 0.5F, -2.0F))
                )
        );

        // Existing items.
        GOLDEN_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "golden_knife"));
        DIAMOND_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "diamond_knife"));
        NETHERITE_KNIFE = Registries.ITEM.get(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "netherite_knife"));

        Frontiers.LOGGER.info("Farmer's Delight detected (sick mod taste btw)! Registering compat-exclusive items for " + Frontiers.MOD_ID);
    }
}
