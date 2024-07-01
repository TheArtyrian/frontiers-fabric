package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Mod items class.
public class ModItems
{
    // ITEM LIST. Self-explanatory, read by Item class name.

    // Ingredients.
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new Item.Settings()));
    public static final Item BRIMTAN_INGOT = registerItem("brimtan_ingot", new Item(new Item.Settings()));
    public static final Item CURSED_TABLET = registerItem("cursed_tablet", new Item(new Item.Settings()));

    // CREATIVE TAB MIX-INS.
    public static void creativeTabIngredients(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.NETHERITE_INGOT, COBALT_INGOT);
        tab.addAfter(COBALT_INGOT, BRIMTAN_INGOT);
        tab.add(CURSED_TABLET);
    }

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items - called .
    public static void registerModItems()
    {
        // Send notice in log.
        Frontiers.LOGGER.info("Registering Mod Items for " + Frontiers.MOD_ID);

        // Items already init'd in start of this event.

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::creativeTabIngredients);
    }
}
