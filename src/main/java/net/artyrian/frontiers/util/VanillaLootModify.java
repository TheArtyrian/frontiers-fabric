package net.artyrian.frontiers.util;

import net.artyrian.frontiers.datagen.loot.loot_conditions.HardmodeLootCondition;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

// in new york i milly rock
// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class VanillaLootModify
{

    // Get a few loot table locations.
    private static final RegistryKey<LootTable> SNIFFER_DIGS = LootTables.SNIFFER_DIGGING_GAMEPLAY;
    private static final RegistryKey<LootTable> DESERT_PYRAMID_SUS = LootTables.DESERT_PYRAMID_ARCHAEOLOGY;

    private static final RegistryKey<LootTable> RUINED_PORTAL = LootTables.RUINED_PORTAL_CHEST;
    private static final RegistryKey<LootTable> DESERT_CHEST = LootTables.DESERT_PYRAMID_CHEST;
    private static final RegistryKey<LootTable> PILLAGER_OUTPOST = LootTables.PILLAGER_OUTPOST_CHEST;
    private static final RegistryKey<LootTable> WOODLAND_MANSION = LootTables.WOODLAND_MANSION_CHEST;
    private static final RegistryKey<LootTable> BASTION_TREASURE_CHEST = LootTables.BASTION_TREASURE_CHEST;
    private static final RegistryKey<LootTable> BASTION_BRIDGE_CHEST = LootTables.BASTION_BRIDGE_CHEST;
    private static final RegistryKey<LootTable> BASTION_HOGLIN_STABLE_CHEST = LootTables.BASTION_HOGLIN_STABLE_CHEST;
    private static final RegistryKey<LootTable> BASTION_OTHER_CHEST = LootTables.BASTION_OTHER_CHEST;
    private static final RegistryKey<LootTable> END_CITY = LootTables.END_CITY_TREASURE_CHEST;
    private static final RegistryKey<LootTable> BURIED_TREASURE = LootTables.BURIED_TREASURE_CHEST;
    private static final RegistryKey<LootTable> DUNGEON = LootTables.SIMPLE_DUNGEON_CHEST;

    private static final RegistryKey<LootTable> OMINOUS_VAULT_RARE = LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST;

    private static final RegistryKey<LootTable> GHAST = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/ghast"));
    private static final RegistryKey<LootTable> RAVAGER = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/ravager"));
    private static final RegistryKey<LootTable> WITCH = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/witch"));

    // Modifies the loot tables.
    public static void modify()
    {
        // Sniffer loot
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (SNIFFER_DIGS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.ANCIENT_ROSE_SEED));
                });
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.TRUFFLE)
                                    .conditionally(LocationCheckLootCondition.builder(
                                    LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(Blocks.MYCELIUM)),
                                                    BlockPos.ORIGIN.offset(Direction.DOWN, 1)
                                            )
                                    )
                            );
                });
            }
        });

        // Ruined Portal
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (RUINED_PORTAL.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE).weight(2));
                });
            }
        });

        // Dungeon/Monster Room
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (DUNGEON.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9))
                                .with(ItemEntry.builder(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
                );
            }
        });

        // Buried Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (BURIED_TREASURE.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(3))
                                .with(ItemEntry.builder(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
                );
            }
        });

        // End City Treasure Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (END_CITY.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(4))
                                .with(ItemEntry.builder(ModItem.END_CRYSTAL_SHARD).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                );
            }
        });

        // Ominous Trial Vault - Rare
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (OMINOUS_VAULT_RARE.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(
                            ItemEntry.builder(ModItem.COBALT_HORSE_ARMOR)
                                    .weight(2)
                                    .conditionally(HardmodeLootCondition.builder(true))
                    );
                });
            }
        });

        // Desert Pyramid Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (DESERT_CHEST.equals(key))
            {
                tableBuilder.pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(EmptyEntry.builder().weight(1))
                            .with(ItemEntry.builder(ModItem.TABLET_FRAGMENT).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
                );
            }
        });

        // Desert Pyramid Archaeology
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (DESERT_PYRAMID_SUS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools
                            .with(ItemEntry.builder(ModItem.TABLET_FRAGMENT))
                            .with(ItemEntry.builder(ModItem.CURSED_TABLET));
                });
            }
        });

        // Pillager Outpost
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (PILLAGER_OUTPOST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                                .with(EmptyEntry.builder().weight(3))
                                .with(ItemEntry.builder(ModItem.INVOKE_SHARD).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                );
            }
        });

        // Woodland Mansion
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (WOODLAND_MANSION.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(ModItem.INVOKE_SHARD).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                );
            }
        });

        // Bastion - Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (BASTION_TREASURE_CHEST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItem.UNFINISHED_CORE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                );
            }
        });

        // Bastion - Hoglin Stable
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (BASTION_HOGLIN_STABLE_CHEST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9))
                                .with(ItemEntry.builder(ModItem.UNFINISHED_CORE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                );
            }
        });

        // Bastion - Bridge
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (BASTION_BRIDGE_CHEST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9))
                                .with(ItemEntry.builder(ModItem.UNFINISHED_CORE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                );
            }
        });

        // Bastion - Other
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (BASTION_OTHER_CHEST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(EmptyEntry.builder().weight(9))
                                .with(ItemEntry.builder(ModItem.UNFINISHED_CORE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                );
            }
        });

        // Ravager
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (RAVAGER.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItem.RAVAGER_TOOTH)
                                        .weight(1)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                )
                );
            }
        });

        // Ghast
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (GHAST.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItem.ECTOPLASM)
                                        .weight(1)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        .conditionally(KilledByPlayerLootCondition.builder())
                                )
                );
            }
        });

        // Witch
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (WITCH.equals(key))
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItem.WITCH_HAT)
                                        .conditionally(KilledByPlayerLootCondition.builder())
                                        .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(wrapperLookup, 0.025F, 0.01F))
                                )
                );
            }
        });

        // I just edited the table manually idfc
        //LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
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
