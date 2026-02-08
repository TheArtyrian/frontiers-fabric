package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;

// in new york i milly rock
// Modifies existing Vanilla loot tables - doesn't overwrite them.
public class FabricLootModify
{
    // Modifies the loot tables.
    public static void modify()
    {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            // Sniffer loot
            if (FRLootMods.SNIFFER_DIGS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : FRLootMods.Modify.sniffer(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Ruined Portal
            else if (FRLootMods.RUINED_PORTAL.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : FRLootMods.Modify.ruinedPortalTemplate(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Dungeon/Monster Room
            else if (FRLootMods.DUNGEON.equals(key)) tableBuilder.withPool(FRLootMods.Modify.monsterRoom(wrapperLookup));

            // Buried Treasure
            else if (FRLootMods.BURIED_TREASURE.equals(key)) tableBuilder.withPool(FRLootMods.Modify.buriedTreasure(wrapperLookup));

            // End City Treasure Chest
            else if (FRLootMods.END_CITY.equals(key)) tableBuilder.withPool(FRLootMods.Modify.endCity(wrapperLookup));

            // Ominous Trial Vault - Rare
            else if (FRLootMods.OMINOUS_VAULT_RARE.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : FRLootMods.Modify.ominousTrialSpawner(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Desert Pyramid Chest
            else if (FRLootMods.DESERT_CHEST.equals(key))
            {
                tableBuilder.withPool(FRLootMods.Modify.desertTemple(wrapperLookup));
            }

            // Desert Pyramid Archaeology
            else if (FRLootMods.DESERT_PYRAMID_SUS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : FRLootMods.Modify.desertSusSand(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Pillager Outpost
            else if (FRLootMods.PILLAGER_OUTPOST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.pillagerOutpost(wrapperLookup));

            // Woodland Mansion
            else if (FRLootMods.WOODLAND_MANSION.equals(key)) tableBuilder.withPool(FRLootMods.Modify.woodlandMansion(wrapperLookup));

            // Bastion - Treasure
            else if (FRLootMods.BASTION_TREASURE_CHEST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.bastionTreasure(wrapperLookup));

            // Bastion - Hoglin Stable
            else if (FRLootMods.BASTION_HOGLIN_STABLE_CHEST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.bastionStable(wrapperLookup));

            // Bastion - Bridge
            else if (FRLootMods.BASTION_BRIDGE_CHEST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.bastionBridge(wrapperLookup));

            // Bastion - Other
            else if (FRLootMods.BASTION_OTHER_CHEST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.bastionOther(wrapperLookup));

            // Ravager
            else if (FRLootMods.RAVAGER.equals(key)) tableBuilder.withPool(FRLootMods.Modify.ravager(wrapperLookup));

            // Ghast
            else if (FRLootMods.GHAST.equals(key)) tableBuilder.withPool(FRLootMods.Modify.ghast(wrapperLookup));

            // Witch
            else if (FRLootMods.WITCH.equals(key)) tableBuilder.withPool(FRLootMods.Modify.witch(wrapperLookup));

            // Spawner
            else if (FRLootMods.SPAWNER.equals(key)) tableBuilder.withPool(FRLootMods.Modify.spawner(wrapperLookup));
        });
    }
}
