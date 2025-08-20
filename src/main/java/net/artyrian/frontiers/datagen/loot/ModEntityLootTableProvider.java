package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

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
    }
}
