package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModGiftLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModGiftLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(output, registryLookup, LootContextTypes.GIFT);
        this.registryLookup = registryLookup;
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                ModLootTables.SHULKER_BULLET,
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ModItem.SHULKER_RESIDUE))
                        )
        );

        lootTableBiConsumer.accept(
                ModLootTables.VEX_RAGE,
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(ModItem.INCENSE))
                        )
        );
    }
}