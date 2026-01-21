package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<HolderLookup.Provider> registryLookup;

    public ModEntityLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup,  LootContextParamSets.ENTITY);
        this.registryLookup = registryLookup;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                ModEntity.CRAWLER.getDefaultLootTable(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(Items.GUNPOWDER)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.resultNow(), UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(ModItem.SOUL)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.resultNow(), UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
                /* TODO: This might be replaced with Frontiers unique discs someway, somehow */
                        // .pool(
                        //         LootPool.builder()
                        //                 .with(TagEntry.expandBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS))
                        //                 .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.create().type(EntityTypeTags.SKELETONS)))
                        // )
        );
        lootTableBiConsumer.accept(
                ModEntity.CROW.getDefaultLootTable(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(Items.FEATHER)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.resultNow(), UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
        );
        lootTableBiConsumer.accept(
                ModEntity.JUNGLE_SPIDER.getDefaultLootTable(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(
                                                LootItem.lootTableItem(Items.STRING)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.resultNow(), UniformGenerator.between(0.0F, 1.0F)))
                                        )
                        )
        );
        // SHARES THE SAME LOOT TABLE AS THE REGULAR CHICKEN
        //lootTableBiConsumer.accept(
        //        ModEntity.GOLDEN_CHICKEN.getLootTableId(),
        //        LootTable.builder()
        //                .pool(
        //                        LootPool.builder()
        //                                .rolls(ConstantLootNumberProvider.create(1.0F))
        //                                .with(
        //                                        ItemEntry.builder(Items.FEATHER)
        //                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
        //                                                .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
        //                                )
        //                )
        //                .pool(
        //                        LootPool.builder()
        //                                .rolls(ConstantLootNumberProvider.create(1.0F))
        //                                .with(
        //                                        ItemEntry.builder(Items.CHICKEN)
        //                                                .apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition()))
        //                                                .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
        //                                )
        //                )
        //);
    }

    protected final AnyOfCondition.Builder createSmeltLootCondition()
    {
        HolderLookup.RegistryLookup<Enchantment> impl = this.registryLookup.resultNow().lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
                ),
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.entity()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.equipment()
                                                .mainhand(
                                                        ItemPredicate.Builder.item()
                                                                .withSubPredicate(
                                                                        ItemSubPredicates.ENCHANTMENTS,
                                                                        ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY)))
                                                                )
                                                )
                                )
                )
        );
    }
}
