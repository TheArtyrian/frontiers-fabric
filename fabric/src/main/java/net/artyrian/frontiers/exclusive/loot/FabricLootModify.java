package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.definition.loot.helpers.LootModHelper;
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
            if (LootModHelper.SNIFFER_DIGS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.sniffer(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Ruined Portal
            else if (LootModHelper.RUINED_PORTAL.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.ruinedPortalTemplate(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Dungeon/Monster Room
            else if (LootModHelper.DUNGEON.equals(key)) tableBuilder.withPool(LootModHelper.Modify.monsterRoom(wrapperLookup));

            // Buried Treasure
            else if (LootModHelper.BURIED_TREASURE.equals(key)) tableBuilder.withPool(LootModHelper.Modify.buriedTreasure(wrapperLookup));

            // End City Treasure Chest
            else if (LootModHelper.END_CITY.equals(key)) tableBuilder.withPool(LootModHelper.Modify.endCity(wrapperLookup));

            // Ominous Trial Vault - Rare
            else if (LootModHelper.OMINOUS_VAULT_RARE.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.ominousTrialSpawner(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Desert Pyramid Chest
            else if (LootModHelper.DESERT_CHEST.equals(key))
            {
                tableBuilder.withPool(LootModHelper.Modify.desertTemple(wrapperLookup));
            }

            // Desert Pyramid Archaeology
            else if (LootModHelper.DESERT_PYRAMID_SUS.equals(key))
            {
                tableBuilder.modifyPools((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.desertSusSand(wrapperLookup))
                    {
                        pools.add(loot);
                    }
                });
            }

            // Pillager Outpost
            else if (LootModHelper.PILLAGER_OUTPOST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.pillagerOutpost(wrapperLookup));

            // Woodland Mansion
            else if (LootModHelper.WOODLAND_MANSION.equals(key)) tableBuilder.withPool(LootModHelper.Modify.woodlandMansion(wrapperLookup));

            // Bastion - Treasure
            else if (LootModHelper.BASTION_TREASURE_CHEST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.bastionTreasure(wrapperLookup));

            // Bastion - Hoglin Stable
            else if (LootModHelper.BASTION_HOGLIN_STABLE_CHEST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.bastionStable(wrapperLookup));

            // Bastion - Bridge
            else if (LootModHelper.BASTION_BRIDGE_CHEST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.bastionBridge(wrapperLookup));

            // Bastion - Other
            else if (LootModHelper.BASTION_OTHER_CHEST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.bastionOther(wrapperLookup));

            // Ravager
            else if (LootModHelper.RAVAGER.equals(key)) tableBuilder.withPool(LootModHelper.Modify.ravager(wrapperLookup));

            // Ghast
            else if (LootModHelper.GHAST.equals(key)) tableBuilder.withPool(LootModHelper.Modify.ghast(wrapperLookup));

            // Witch
            else if (LootModHelper.WITCH.equals(key)) tableBuilder.withPool(LootModHelper.Modify.witch(wrapperLookup));

            // Spawner
            else if (LootModHelper.SPAWNER.equals(key)) tableBuilder.withPool(LootModHelper.Modify.spawner(wrapperLookup));
        });
    }
}
