package net.artyrian.frontiers.definition.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.helpers.LootModHelper;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.loot_tables.VectorLootMod;

public class FRLootMods
{
    public static class Modify
    {
        public static void bootstrap()
        {

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
