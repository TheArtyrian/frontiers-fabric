package net.artyrian.frontiers.definition.loot;

import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class LootTableHelper
{
    // Set a few predicates.
    public static final LootItemCondition.Builder WITH_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    public static LootTable.Builder newRoseBushDrops(Block bush, Block flower)
    {
        LootPoolEntryContainer.Builder<?> builder = LootItem.lootTableItem(flower)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .when(WITH_SHEARS)
                .otherwise(
                        (LootItem.lootTableItem(bush).when(ExplosionCondition.survivesExplosion()))
                );
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(builder)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(bush).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))
                                )
                                .when(
                                        LocationCheck.checkLocation(
                                                LocationPredicate.Builder.location()
                                                        .setBlock(BlockPredicate.Builder.block().of(bush).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))),
                                                new BlockPos(0, 1, 0)
                                        )
                                )
                )
                .withPool(
                        LootPool.lootPool()
                                .add(builder)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(bush).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER))
                                )
                                .when(
                                        LocationCheck.checkLocation(
                                                LocationPredicate.Builder.location()
                                                        .setBlock(BlockPredicate.Builder.block().of(bush).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER))),
                                                new BlockPos(0, -1, 0)
                                        )
                                )
                );
    }
}
