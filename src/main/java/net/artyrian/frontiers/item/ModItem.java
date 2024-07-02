package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.SmithTemplate;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Registers all mod items to Minecraft registries.
public class ModItem
{
    // ITEM LIST.

    // Cobalt Ingot
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new Item.Settings()));
    // Brimtan Ingot
    public static final Item BRIMTAN_INGOT = registerItem("brimtan_ingot", new Item(new Item.Settings()));
    // Frostite Ingot
    public static final Item FROSTITE_INGOT = registerItem("frostite_ingot", new Item(new Item.Settings()));
    // Cursed Tablet
    public static final Item CURSED_TABLET = registerItem("cursed_tablet", new Item(new Item.Settings()));
    // Obsidian Casing
    public static final Item OBSIDIAN_CASING = registerItem("obsidian_casing", new Item(new Item.Settings().fireproof()));
    // Smithing Template: Obsidian Upgrade
    public static final Item OBSIDIAN_UPGRADE_SMITHING_TEMPLATE = registerItem("obsidian_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.OBSIDIAN_UPGRADE_APPLIES_TO_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_INGREDIENTS_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.armorUpgradeSlotTextures(),
                    SmithTemplate.casingAdditionsTexture()
            )
    );

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. Just sends a log message.
    public static void registerModItems()
    {
        Frontiers.LOGGER.info("Registering Mod Items for " + Frontiers.MOD_ID);
    }
}
