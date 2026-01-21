package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.condition.HardmodeLootCondition;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.reg.content.ModItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class FabricLootReplace
{
    private static final ResourceKey<LootTable> EVOKER = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/evoker"));
    private static final ResourceKey<LootTable> GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/guardian"));
    private static final ResourceKey<LootTable> ELDER_GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/elder_guardian"));
    private static final ResourceKey<LootTable> STRAY = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/stray"));

    public static void replace()
    {
        // Evoker (ONLY IF CONFIG = TRUE)
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) -> {
            if (EVOKER == key && Frontiers.CONFIG.doEvokerRebalance())
            {
                return LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItem.INVOKE_SHARD.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(Items.EMERALD)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .build();
            }
            return null;
        });

        // Guardian
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) ->
        {
            if (GUARDIAN == key)
            {
                return LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(Items.PRISMARINE_SHARD)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItem.GUARDIAN_SLICE.get())
                                                        .setWeight(2)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                                                        .setWeight(2)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                        .add(EmptyLootItem.emptyItem())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING_FISH).apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup))))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
                        )
                        .build();
            }
            return null;
        });

        // Elder Guardian
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) ->
        {
            if (ELDER_GUARDIAN == key)
            {
                return LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItem.PALE_PRISMARINE_SHARD.get())
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 2.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItem.ELDER_GUARDIAN_SLICE.get())
                                                        .setWeight(3)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(1.0F, 2.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.PRISMARINE_CRYSTALS)
                                                        .setWeight(2)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                        )
                                        .add(EmptyLootItem.emptyItem())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.WET_SPONGE))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(ModItem.ELDER_GUARDIAN_SPINE.get()))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(HardmodeLootCondition.builder(true))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(NestedLootTable.lootTableReference(BuiltInLootTables.FISHING_FISH).apply(SmeltItemFunction.smelted().when(MethodToolbox.onfireCheck(wrapperLookup))))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(wrapperLookup, 0.025F, 0.01F))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(EmptyLootItem.emptyItem().setWeight(4))
                                        .add(LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1))
                        )
                        .build();
            }
            return null;
        });

        // Stray
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) ->
                {
                    if (STRAY == key)
                    {
                        return LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(Items.ARROW)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                                )
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ModItem.FROST_BONE.get())
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)))
                                                )
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(Items.TIPPED_ARROW)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)).setLimit(1))
                                                                .apply(SetPotionFunction.setPotion(Potions.SLOWNESS))
                                                                .when(HardmodeLootCondition.builder(false))
                                                )
                                                .add(
                                                        LootItem.lootTableItem(ModItem.SUBZERO_ARROW.get())
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(wrapperLookup, UniformGenerator.between(0.0F, 1.0F)).setLimit(1))
                                                                .when(HardmodeLootCondition.builder(true))
                                                )
                                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                )
                                .build();
                    }
                    return null;
                });
    }


}
