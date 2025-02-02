package net.artyrian.frontiers.util;

import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaEntityLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.entry.RegistryEntryOwner;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeSource;

// in new york i milly rock
// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class VanillaLootModify
{

    // Get a few loot table locations.
    private static final RegistryKey<LootTable> SNIFFER_DIGS = LootTables.SNIFFER_DIGGING_GAMEPLAY;
    private static final RegistryKey<LootTable> RUINED_PORTAL = LootTables.RUINED_PORTAL_CHEST;
    private static final RegistryKey<LootTable> DESERT_CHEST = LootTables.DESERT_PYRAMID_CHEST;
    private static final RegistryKey<LootTable> PILLAGER_OUTPOST = LootTables.PILLAGER_OUTPOST_CHEST;
    private static final RegistryKey<LootTable> WOODLAND_MANSION = LootTables.WOODLAND_MANSION_CHEST;
    private static final RegistryKey<LootTable> DESERT_PYRAMID_SUS = LootTables.DESERT_PYRAMID_ARCHAEOLOGY;
    private static final RegistryKey<LootTable> GHAST = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/ghast"));
    private static final RegistryKey<LootTable> RAVAGER = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/ravager"));

    //.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
    //RegistryWrapper.Impl<Enchantment> impl = Registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

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
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && RUINED_PORTAL == key)
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE).weight(2));
                });
            }
        });

        // Desert Pyramid Chest
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && DESERT_CHEST == key)
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
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && DESERT_PYRAMID_SUS == key)
            {
                tableBuilder.modifyPools((pools) -> {
                    pools.with(ItemEntry.builder(ModItem.TABLET_FRAGMENT));
                });
            }
        });

        // Pillager Outpost
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && PILLAGER_OUTPOST == key)
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
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && WOODLAND_MANSION == key)
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(2.0F, 3.0F))
                                .with(EmptyEntry.builder().weight(1))
                                .with(ItemEntry.builder(ModItem.INVOKE_SHARD).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                );
            }
        });

        // Ravager
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (source.isBuiltin() && RAVAGER == key)
            {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .with(ItemEntry.builder(ModItem.RAVAGER_TOOTH)
                                        .weight(1)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        /*.apply(EnchantedCountIncreaseLootFunction.builder(, UniformLootNumberProvider.create(0.0F, 1.0F)))*/
                                )
                );
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
