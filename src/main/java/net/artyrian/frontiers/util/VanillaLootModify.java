package net.artyrian.frontiers.util;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKey;

// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class VanillaLootModify
{
    // Get a few loot table locations.
    private static final RegistryKey<LootTable> SNIFFER_DIGS = LootTables.SNIFFER_DIGGING_GAMEPLAY;
    private static final RegistryKey<LootTable> RUINED_PORTAL = LootTables.RUINED_PORTAL_CHEST;
    //private static final RegistryKey<LootTable> ROSE_BUSH = Blocks.ROSE_BUSH.getLootTableKey();

    // Modifies the loot tables.
    public static void modify()
    {
        // Sniffer loot
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && SNIFFER_DIGS == key)
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.ANCIENT_ROSE_SEED));
                });
            }
        });

        // Ruined Portal
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && RUINED_PORTAL == key)
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE).weight(2));
                });
            }
        });

        //// Rose Bush
        //LootTableEvents.REPLACE.register((key, tableBuilder, source) -> {
        //    if (source.isBuiltin() && ROSE_BUSH == key)
        //    {
        //            LootPoolEntry.Builder<?> builder = ItemEntry.builder(ModBlocks.ROSE)
        //                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
        //                .conditionally(LootTableHelper.WITH_SHEARS)
        //                .alternatively(
        //                        (ItemEntry.builder(Blocks.ROSE_BUSH).conditionally(SurvivesExplosionLootCondition.builder()))
        //                );
//
        //            tableBuilder.builder().build();
        //        }
        //});
    }
}
