package net.artyrian.frontiers.definition.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.helpers.LootModHelper;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.vertisoft.vectorlib.agnostic.loot_tables.VectorLootMod;

public class FRLootMods
{
    public static class Modify
    {
        public static void bootstrap()
        {
            VectorLootMod.Modify.add(LootModHelper.SNIFFER_DIGS, (key, builder, casted, changed, wrapper) -> {
                casted.vLib$withAll((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.sniffer(wrapper))
                    {
                        pools.add(loot);
                    }
                });
            });

            VectorLootMod.Modify.add(LootModHelper.RUINED_PORTAL, (key, builder, casted, changed, wrapper) -> {
                casted.vLib$withAll((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.ruinedPortalTemplate(wrapper))
                    {
                        pools.add(loot);
                    }
                });
            });

            VectorLootMod.Modify.add(LootModHelper.OMINOUS_VAULT_RARE, (key, builder, casted, changed, wrapper) -> {
                casted.vLib$withAll((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.ominousTrialSpawner(wrapper))
                    {
                        pools.add(loot);
                    }
                });
            });

            VectorLootMod.Modify.add(LootModHelper.DESERT_PYRAMID_SUS, (key, builder, casted, changed, wrapper) -> {
                casted.vLib$withAll((pools) -> {
                    for (LootPoolSingletonContainer.Builder<?> loot : LootModHelper.Modify.desertSusSand(wrapper))
                    {
                        pools.add(loot);
                    }
                });
            });

            VectorLootMod.Modify.add(LootModHelper.DUNGEON, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.monsterRoom(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.BURIED_TREASURE, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.buriedTreasure(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.END_CITY, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.endCity(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.DESERT_CHEST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.desertTemple(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.PILLAGER_OUTPOST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.pillagerOutpost(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.WOODLAND_MANSION, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.woodlandMansion(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.BASTION_TREASURE_CHEST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.bastionTreasure(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.BASTION_HOGLIN_STABLE_CHEST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.bastionStable(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.BASTION_BRIDGE_CHEST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.bastionBridge(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.BASTION_OTHER_CHEST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.bastionOther(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.RAVAGER, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.ravager(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.GHAST, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.ghast(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.WITCH, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.witch(wrapper)));
            VectorLootMod.Modify.add(LootModHelper.SPAWNER, (key, builder, casted, changed, wrapper) -> builder.withPool(LootModHelper.Modify.spawner(wrapper)));
        }
    }

    public static class Replace
    {
        public static void bootstrap()
        {
            VectorLootMod.Replace.add(LootModHelper.EVOKER, (key, table, wrapper) ->
                    (Frontiers.CONFIG.doEvokerRebalance()) ? LootModHelper.Replace.evokerRebalance(wrapper).build() : table
            );
            VectorLootMod.Replace.add(LootModHelper.GUARDIAN, (key, table, wrapper) -> LootModHelper.Replace.guardian(wrapper).build());
            VectorLootMod.Replace.add(LootModHelper.ELDER_GUARDIAN, (key, table, wrapper) -> LootModHelper.Replace.elderGuardian(wrapper).build());
            VectorLootMod.Replace.add(LootModHelper.STRAY, (key, table, wrapper) -> LootModHelper.Replace.stray(wrapper).build());
        }
    }
}
