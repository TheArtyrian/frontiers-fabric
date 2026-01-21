package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModGiftLootTableProvider extends SimpleFabricLootTableProvider
{
    private CompletableFuture<HolderLookup.Provider> registryLookup;

    public ModGiftLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(output, registryLookup, LootContextParamSets.GIFT);
        this.registryLookup = registryLookup;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
    {
        lootTableBiConsumer.accept(
                ModLootTables.SHULKER_BULLET,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(ModItem.SHULKER_RESIDUE.get()))
                        )
        );

        lootTableBiConsumer.accept(
                ModLootTables.VEX_RAGE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(ModItem.INCENSE.get()))
                        )
        );
    }
}