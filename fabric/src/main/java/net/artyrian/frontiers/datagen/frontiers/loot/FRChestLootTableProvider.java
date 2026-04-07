package net.artyrian.frontiers.datagen.frontiers.loot;

import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.misc.FRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetOminousBottleAmplifierFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FRChestLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<HolderLookup.Provider> registryLookup;

    public FRChestLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup,  LootContextParamSets.CHEST);
        this.registryLookup = registryLookup;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                FRLootTables.CRAGS_ALTAR_CHEST,
                LootTable.lootTable()
                        .withPool(
                                // Treasure
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(EmptyLootItem.emptyItem().setWeight(10))
                                        .add(LootItem.lootTableItem(FRItems.MUSIC_DISC_DIAPHRAGM.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                        )
                        .withPool(
                                // Junk
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(2.0F, 4.0F))
                                        .add(LootItem.lootTableItem(FRItems.ONYX_BONE.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))))

                                        .add(LootItem.lootTableItem(Items.GUNPOWDER).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                                        .add(LootItem.lootTableItem(Items.STRING).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                                        .add(LootItem.lootTableItem(Items.TORCH).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))

                                        .add(LootItem.lootTableItem(FRBlocks.CRAGULSTANE_BRICKS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 7.0F))))
                                        .add(LootItem.lootTableItem(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get()).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                        )
                        .withPool(
                                // Goods
                                LootPool.lootPool()
                                        .setRolls(UniformGenerator.between(1.0F, 3.0F))

                                        .add(LootItem.lootTableItem(Items.OMINOUS_BOTTLE).setWeight(5)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                                .apply(SetOminousBottleAmplifierFunction.setAmplifier(UniformGenerator.between(2.0F, 4.0F)))
                                        )
                                        .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP).setWeight(5).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                        .add(LootItem.lootTableItem(Items.NAME_TAG).setWeight(5).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))

                                        .add(LootItem.lootTableItem(Items.DIAMOND).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F))))
                                        .add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))

                                        .add(LootItem.lootTableItem(FRItems.BRIMTAN_CLUSTER.get()).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F))))
                                        .add(LootItem.lootTableItem(FRItems.TABLET_FRAGMENT.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                        )
        );
    }
}
