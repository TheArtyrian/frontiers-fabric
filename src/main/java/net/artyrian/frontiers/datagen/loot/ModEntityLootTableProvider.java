package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEntityLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModEntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(output, registryLookup,  LootContextTypes.ENTITY);
        this.registryLookup = registryLookup;
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                ModEntity.CRAWLER.getLootTableId(),
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.GUNPOWDER)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(ModItem.SOUL)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
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
                ModEntity.CROW.getLootTableId(),
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.FEATHER)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
                                        )
                        )
        );
        lootTableBiConsumer.accept(
                ModEntity.JUNGLE_SPIDER.getLootTableId(),
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(
                                                ItemEntry.builder(Items.STRING)
                                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                                        .apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.resultNow(), UniformLootNumberProvider.create(0.0F, 1.0F)))
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

    protected final AnyOfLootCondition.Builder createSmeltLootCondition()
    {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.resultNow().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))
                ),
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.create()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.create()
                                                .mainhand(
                                                        ItemPredicate.Builder.create()
                                                                .subPredicate(
                                                                        ItemSubPredicateTypes.ENCHANTMENTS,
                                                                        EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))
                                                                )
                                                )
                                )
                )
        );
    }
}
