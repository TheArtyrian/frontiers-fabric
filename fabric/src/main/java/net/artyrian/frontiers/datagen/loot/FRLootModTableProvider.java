package net.artyrian.frontiers.datagen.loot;

import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;

import java.util.function.BiConsumer;

public class FRLootModTableProvider
{
    public static class Modify
    {
        public static void generate(HolderLookup.Provider lookup, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder)
        {
            // Sniffer
            LootPool.Builder sniffer_pool = LootPool.lootPool();
            for (LootPoolSingletonContainer.Builder<?> entry : FRLootMods.Modify.sniffer(lookup))
            {
                sniffer_pool.add(entry);
            }
            builder.accept(FRLootMods.Modify.SNIFFER, LootTable.lootTable().withPool(sniffer_pool));

            // Ruined Portal
            LootPool.Builder portal_pool = LootPool.lootPool();
            for (LootPoolSingletonContainer.Builder<?> entry : FRLootMods.Modify.ruinedPortalTemplate(lookup))
            {
                portal_pool.add(entry);
            }
            builder.accept(FRLootMods.Modify.RUINED_PORTAL, LootTable.lootTable().withPool(portal_pool));

            // Trial Spawner
            LootPool.Builder trial_sp_pool = LootPool.lootPool();
            for (LootPoolSingletonContainer.Builder<?> entry : FRLootMods.Modify.ominousTrialSpawner(lookup))
            {
                trial_sp_pool.add(entry);
            }
            builder.accept(FRLootMods.Modify.OMINOUS_TRIAL, LootTable.lootTable().withPool(trial_sp_pool));

            // Desert Temple sus sand
            LootPool.Builder sus_pool = LootPool.lootPool();
            for (LootPoolSingletonContainer.Builder<?> entry : FRLootMods.Modify.desertSusSand(lookup))
            {
                sus_pool.add(entry);
            }
            builder.accept(FRLootMods.Modify.DESERT_TEMPLE_SUS_SAND, LootTable.lootTable().withPool(sus_pool));

            builder.accept(FRLootMods.Modify.MONSTER_ROOM, LootTable.lootTable().withPool(FRLootMods.Modify.monsterRoom(lookup)));
            builder.accept(FRLootMods.Modify.BURIED_TREASURE, LootTable.lootTable().withPool(FRLootMods.Modify.buriedTreasure(lookup)));
            builder.accept(FRLootMods.Modify.END_CITY, LootTable.lootTable().withPool(FRLootMods.Modify.endCity(lookup)));
            builder.accept(FRLootMods.Modify.DESERT_TEMPLE, LootTable.lootTable().withPool(FRLootMods.Modify.desertTemple(lookup)));
            builder.accept(FRLootMods.Modify.PILLAGER_OUTPOST, LootTable.lootTable().withPool(FRLootMods.Modify.pillagerOutpost(lookup)));
            builder.accept(FRLootMods.Modify.WOODLAND_MANSION, LootTable.lootTable().withPool(FRLootMods.Modify.woodlandMansion(lookup)));

            builder.accept(FRLootMods.Modify.BASTION_TREASURE, LootTable.lootTable().withPool(FRLootMods.Modify.bastionTreasure(lookup)));
            builder.accept(FRLootMods.Modify.BASTION_STABLE, LootTable.lootTable().withPool(FRLootMods.Modify.bastionStable(lookup)));
            builder.accept(FRLootMods.Modify.BASTION_BRIDGE, LootTable.lootTable().withPool(FRLootMods.Modify.bastionBridge(lookup)));
            builder.accept(FRLootMods.Modify.BASTION_OTHER, LootTable.lootTable().withPool(FRLootMods.Modify.bastionOther(lookup)));

            builder.accept(FRLootMods.Modify.RAVAGER, LootTable.lootTable().withPool(FRLootMods.Modify.ravager(lookup)));
            builder.accept(FRLootMods.Modify.GHAST, LootTable.lootTable().withPool(FRLootMods.Modify.ghast(lookup)));
            builder.accept(FRLootMods.Modify.WITCH, LootTable.lootTable().withPool(FRLootMods.Modify.witch(lookup)));

            builder.accept(FRLootMods.Modify.SPAWNER, LootTable.lootTable().withPool(FRLootMods.Modify.spawner(lookup)));
        }
    }

    public static class Replace
    {
        public static void generate(HolderLookup.Provider lookup, BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder)
        {
            builder.accept(FRLootMods.Replace.EVOKER, FRLootMods.Replace.evokerRebalance(lookup));
            builder.accept(FRLootMods.Replace.GUARDIAN, FRLootMods.Replace.guardian(lookup));
            builder.accept(FRLootMods.Replace.ELDER_GUARDIAN, FRLootMods.Replace.elderGuardian(lookup));
            builder.accept(FRLootMods.Replace.STRAY, FRLootMods.Replace.stray(lookup));
        }
    }
}
