package net.artyrian.frontiers.util;

import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class VanillaLootModify
{
    // Get a few loot table locations.
    private static final RegistryKey<LootTable> SNIFFER_DIGS = LootTables.SNIFFER_DIGGING_GAMEPLAY;
    private static final RegistryKey<LootTable> RUINED_PORTAL = LootTables.RUINED_PORTAL_CHEST;
    private static final RegistryKey<LootTable> GHAST = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/ghast"));

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

        // I just edited the table manually idfc
        //LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
        //    if (source.isBuiltin() && GHAST.equals(key))
        //    {
        //        LootPool.Builder poolBuilder = LootPool.builder()
        //                .rolls(ConstantLootNumberProvider.create(1.0F))
        //                .with(ItemEntry.builder(ModItem.ECTOPLASM)
        //                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
        //                    //.apply(EnchantedCountIncreaseLootFunction.builder(
        //                    //        RegistryWrapper.WrapperLookup.this.,
        //                    //        UniformLootNumberProvider.create(0.0F, 1.0F)
        //                    //)) fuck this
        //                    .conditionally(KilledByPlayerLootCondition.builder())
        //                );
//
        //        tableBuilder.pool(poolBuilder.build());
        //    }
        //});

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
