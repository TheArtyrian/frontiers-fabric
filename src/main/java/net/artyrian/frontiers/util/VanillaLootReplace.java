package net.artyrian.frontiers.util;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaEntityLootTableGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.Identifier;

import java.util.List;

public class VanillaLootReplace
{
    private static final RegistryKey<LootTable> EVOKER = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/evoker"));
    private static final RegistryKey<LootTable> GUARDIAN = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/guardian"));
    private static final RegistryKey<LootTable> ELDER_GUARDIAN = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/elder_guardian"));
    private static final RegistryKey<LootTable> STRAY = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/stray"));

    public static void replace()
    {
        // Evoker (ONLY IF CONFIG = TRUE)
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) -> {
            if (EVOKER == key && Frontiers.CONFIG.doEvokerRebalance())
            {
                return LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(ModItem.INVOKE_SHARD)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.EMERALD)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .conditionally(KilledByPlayerLootCondition.builder())
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
                return LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.PRISMARINE_SHARD)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(ModItem.GUARDIAN_SLICE)
                                                        .weight(2)
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                        .apply(FurnaceSmeltLootFunction.builder().conditionally(MethodToolbox.onfireCheck(wrapperLookup)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.PRISMARINE_CRYSTALS)
                                                        .weight(2)
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(EmptyEntry.builder())
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(LootTableEntry.builder(LootTables.FISHING_FISH_GAMEPLAY).apply(FurnaceSmeltLootFunction.builder().conditionally(MethodToolbox.onfireCheck(wrapperLookup))))
                                        .conditionally(KilledByPlayerLootCondition.builder())
                                        .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(wrapperLookup, 0.025F, 0.01F))
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
                return LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(ModItem.PALE_PRISMARINE_SHARD)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 2.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(ModItem.ELDER_GUARDIAN_SLICE)
                                                        .weight(3)
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                        .apply(FurnaceSmeltLootFunction.builder().conditionally(MethodToolbox.onfireCheck(wrapperLookup)))
                                        )
                                        .with(
                                                ItemEntry.builder(Items.PRISMARINE_CRYSTALS)
                                                        .weight(2)
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                                        .with(EmptyEntry.builder())
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.WET_SPONGE))
                                        .conditionally(KilledByPlayerLootCondition.builder())
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ModItem.ELDER_GUARDIAN_SPINE))
                                        .conditionally(KilledByPlayerLootCondition.builder())
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(LootTableEntry.builder(LootTables.FISHING_FISH_GAMEPLAY).apply(FurnaceSmeltLootFunction.builder().conditionally(MethodToolbox.onfireCheck(wrapperLookup))))
                                        .conditionally(KilledByPlayerLootCondition.builder())
                                        .conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(wrapperLookup, 0.025F, 0.01F))
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(EmptyEntry.builder().weight(4))
                                        .with(ItemEntry.builder(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).weight(1))
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
                        return LootTable.builder()
                                .pool(
                                        LootPool.builder()
                                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                                .with(
                                                        ItemEntry.builder(Items.ARROW)
                                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                                .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                )
                                )
                                .pool(
                                        LootPool.builder()
                                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                                .with(
                                                        ItemEntry.builder(Items.BONE)
                                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                                .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                )
                                )
                                .pool(
                                        LootPool.builder()
                                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                                .with(
                                                        ItemEntry.builder(Items.TIPPED_ARROW)
                                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                                .apply(EnchantedCountIncreaseLootFunction.builder(wrapperLookup, UniformLootNumberProvider.create(0.0F, 1.0F)).withLimit(1))
                                                                .apply(SetPotionLootFunction.builder(Potions.SLOWNESS))
                                                )
                                                .conditionally(KilledByPlayerLootCondition.builder())
                                )
                                .build();
                    }
                    return null;
                });
    }


}
