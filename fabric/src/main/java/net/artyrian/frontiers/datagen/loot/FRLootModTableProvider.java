package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class FRLootModTableProvider
{
    private static final String lootmods_loc = "frontiers_lootmods/";

    public static class Modify extends SimpleFabricLootTableProvider
    {
        private CompletableFuture<HolderLookup.Provider> registryLookup;
        private static final String loc = lootmods_loc + "modify/";

        public static ResourceKey<LootTable> EVOKER = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "evoker"));
        public static ResourceKey<LootTable> GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "guardian"));
        public static ResourceKey<LootTable> ELDER_GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "elder_guardian"));
        public static ResourceKey<LootTable> STRAY = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "stray"));

        public Modify(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
        {
            super(output, registryLookup,  LootContextParamSets.ENTITY);
            this.registryLookup = registryLookup;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
        {

        }
    }

    public static class Replace extends SimpleFabricLootTableProvider
    {
        private CompletableFuture<HolderLookup.Provider> registryLookup;
        private static final String loc = lootmods_loc + "replace/";

        public static ResourceKey<LootTable> EVOKER = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "evoker"));
        public static ResourceKey<LootTable> GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "guardian"));
        public static ResourceKey<LootTable> ELDER_GUARDIAN = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "elder_guardian"));
        public static ResourceKey<LootTable> STRAY = ResourceKey.create(Registries.LOOT_TABLE, Frontiers.id(loc + "stray"));

        public Replace(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup)
        {
            super(output, registryLookup,  LootContextParamSets.ENTITY);
            this.registryLookup = registryLookup;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer)
        {
            HolderLookup.Provider wrap = registryLookup.resultNow();

            lootTableBiConsumer.accept(EVOKER, FRLootMods.Replace.evokerRebalance(wrap));
            lootTableBiConsumer.accept(GUARDIAN, FRLootMods.Replace.guardian(wrap));
            lootTableBiConsumer.accept(ELDER_GUARDIAN, FRLootMods.Replace.elderGuardian(wrap));
            lootTableBiConsumer.accept(STRAY, FRLootMods.Replace.stray(wrap));
        }
    }
}
