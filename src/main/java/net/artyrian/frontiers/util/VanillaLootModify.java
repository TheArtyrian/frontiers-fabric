package net.artyrian.frontiers.util;

import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class VanillaLootModify
{
    // Get a few loot table locations.
    private static final RegistryKey<LootTable> SNIFFER_DIGS = LootTables.SNIFFER_DIGGING_GAMEPLAY;

    // Modifies the loot tables.
    public static void modify()
    {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && SNIFFER_DIGS == key)
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.ANCIENT_ROSE_SEED));
                });
            }
    });
    }
}
