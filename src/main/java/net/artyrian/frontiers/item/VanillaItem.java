package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

// Re-registers existing Minecraft items using new systems, if necessary.
public class VanillaItem
{
    // Vanilla Re-Implementations.
    public static final Item FISHING_ROD = registerItemVanilla("fishing_rod", new FishingRodItem(new Item.Settings().maxDamage(64)));

    // Re-registers a vanilla item.
    private static Item registerItemVanilla(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.ofVanilla(name), item);
    }

    // Re-registers vanilla items. Just sends a log message.
    public static void registerVanillaItems()
    {
        Frontiers.LOGGER.info("Re-registering necessary items for minecraft");
    }
}
