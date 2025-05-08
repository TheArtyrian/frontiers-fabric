package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetOminousBottleAmplifierLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(output, registryLookup,  LootContextTypes.CHEST);
        this.registryLookup = registryLookup;
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                ModLootTables.CRAGS_ALTAR_CHEST,
                LootTable.builder()
                        .pool(
                                // Treasure
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(EmptyEntry.builder().weight(10))
                                        .with(ItemEntry.builder(ModItem.MUSIC_DISC_DIAPHRAGM).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(Items.ENCHANTED_GOLDEN_APPLE).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                        )
                        .pool(
                                // Junk
                                LootPool.builder()
                                        .rolls(UniformLootNumberProvider.create(2.0F, 4.0F))
                                        .with(ItemEntry.builder(ModItem.ONYX_BONE).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))

                                        .with(ItemEntry.builder(Items.GUNPOWDER).weight(15).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                                        .with(ItemEntry.builder(Items.STRING).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
                                        .with(ItemEntry.builder(Items.TORCH).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))

                                        .with(ItemEntry.builder(ModBlocks.CRAGULSTANE_BRICKS).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 7.0F))))
                                        .with(ItemEntry.builder(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
                        )
                        .pool(
                                // Goods
                                LootPool.builder()
                                        .rolls(UniformLootNumberProvider.create(1.0F, 3.0F))

                                        .with(ItemEntry.builder(Items.OMINOUS_BOTTLE).weight(5)
                                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                                .apply(SetOminousBottleAmplifierLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F)))
                                        )
                                        .with(ItemEntry.builder(Items.NETHERITE_SCRAP).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
                                        .with(ItemEntry.builder(Items.NAME_TAG).weight(5).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))

                                        .with(ItemEntry.builder(Items.DIAMOND).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
                                        .with(ItemEntry.builder(Items.GLISTERING_MELON_SLICE).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))

                                        .with(ItemEntry.builder(ModItem.BRIMTAN_CLUSTER).weight(1).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))))
                                        .with(ItemEntry.builder(ModItem.TABLET_FRAGMENT).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                        )
        );
    }
}
