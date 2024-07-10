package net.artyrian.frontiers.util;

import net.minecraft.block.Block;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.math.BlockPos;

public class LootTableHelper
{
    // Set a few predicates.
    public static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS));

    public static LootTable.Builder newRoseBushDrops(Block bush, Block flower)
    {
        LootPoolEntry.Builder<?> builder = ItemEntry.builder(flower)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                .conditionally(WITH_SHEARS)
                .alternatively(
                        (ItemEntry.builder(bush).conditionally(SurvivesExplosionLootCondition.builder()))
                );
        return LootTable.builder()
                .pool(
                        LootPool.builder()
                                .with(builder)
                                .conditionally(
                                        BlockStatePropertyLootCondition.builder(bush).properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))
                                )
                                .conditionally(
                                        LocationCheckLootCondition.builder(
                                                LocationPredicate.Builder.create()
                                                        .block(BlockPredicate.Builder.create().blocks(bush).state(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))),
                                                new BlockPos(0, 1, 0)
                                        )
                                )
                )
                .pool(
                        LootPool.builder()
                                .with(builder)
                                .conditionally(
                                        BlockStatePropertyLootCondition.builder(bush).properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.UPPER))
                                )
                                .conditionally(
                                        LocationCheckLootCondition.builder(
                                                LocationPredicate.Builder.create()
                                                        .block(BlockPredicate.Builder.create().blocks(bush).state(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER))),
                                                new BlockPos(0, -1, 0)
                                        )
                                )
                );
    }
}
