package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.artyrian.frontiers.definition.loot.condition.HardmodeLootCondition;
import net.artyrian.frontiers.reg.content.ModItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

// in new york i milly rock
// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class FabricLootModify
{

    // Modifies the loot tables.
    public static void modify()
    {
        // Sniffer loot
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.SNIFFER_DIGS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.add(LootItem.lootTableItem(ModItem.ANCIENT_ROSE_SEED.get()));
                });
                tableBuilder.modifyPools((pools) -> {
                    pools.add(LootItem.lootTableItem(ModItem.TRUFFLE.get())
                                    .when(LocationCheck.checkLocation(
                                    LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(Blocks.MYCELIUM)),
                                                    BlockPos.ZERO.relative(Direction.DOWN, 1)
                                            )
                                    )
                            );
                });
            }
        });

        // Ruined Portal
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.RUINED_PORTAL.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.add(LootItem.lootTableItem(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(2));
                });
            }
        });

        // Dungeon/Monster Room
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.DUNGEON.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                                .add(LootItem.lootTableItem(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1))
                );
            }
        });

        // Buried Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.BURIED_TREASURE.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(3))
                                .add(LootItem.lootTableItem(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get()).setWeight(1))
                );
            }
        });

        // End City Treasure Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.END_CITY.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(4))
                                .add(LootItem.lootTableItem(ModItem.END_CRYSTAL_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                );
            }
        });

        // Ominous Trial Vault - Rare
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.OMINOUS_VAULT_RARE.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.add(
                            LootItem.lootTableItem(ModItem.COBALT_HORSE_ARMOR.get())
                                    .setWeight(2)
                                    .when(HardmodeLootCondition.builder(true))
                    );
                });
            }
        });

        // Desert Pyramid Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.DESERT_CHEST.equals(key))
            {
                tableBuilder.withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(EmptyLootItem.emptyItem().setWeight(1))
                            .add(LootItem.lootTableItem(ModItem.TABLET_FRAGMENT.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))
                );
            }
        });

        // Desert Pyramid Archaeology
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.DESERT_PYRAMID_SUS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    pools
                            .add(LootItem.lootTableItem(ModItem.TABLET_FRAGMENT.get()))
                            .add(LootItem.lootTableItem(ModItem.CURSED_TABLET.get()));
                });
            }
        });

        // Pillager Outpost
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.PILLAGER_OUTPOST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(3))
                                .add(LootItem.lootTableItem(ModItem.INVOKE_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                );
            }
        });

        // Woodland Mansion
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.WOODLAND_MANSION.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(1))
                                .add(LootItem.lootTableItem(ModItem.INVOKE_SHARD.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                );
            }
        });

        // Bastion - Treasure
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.BASTION_TREASURE_CHEST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                );
            }
        });

        // Bastion - Hoglin Stable
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.BASTION_HOGLIN_STABLE_CHEST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                                .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                );
            }
        });

        // Bastion - Bridge
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.BASTION_BRIDGE_CHEST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                                .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                );
            }
        });

        // Bastion - Other
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.BASTION_OTHER_CHEST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                                .add(LootItem.lootTableItem(ModItem.UNFINISHED_CORE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                );
            }
        });

        // Ravager
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.RAVAGER.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItem.RAVAGER_TOOTH.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                )
                );
            }
        });

        // Ghast
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.GHAST.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItem.ECTOPLASM.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                )
                );
            }
        });

        // Witch
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (FRLootMods.WITCH.equals(key))
            {
                tableBuilder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(ModItem.WITCH_HAT.get())
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
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
