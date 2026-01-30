package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

public class FabricLootReplace
{
    public static void replace()
    {
        LootTableEvents.REPLACE.register((key, tableBuilder, source, wrapperLookup) ->
        {
            if (FRLootMods.EVOKER == key && Frontiers.CONFIG.doEvokerRebalance())       return FRLootMods.evokerRebalance(wrapperLookup);
            else if (FRLootMods.GUARDIAN == key)                                        return FRLootMods.guardian(wrapperLookup);
            else if (FRLootMods.ELDER_GUARDIAN == key)                                  return FRLootMods.elderGuardian(wrapperLookup);
            else if (FRLootMods.STRAY == key)                                           return FRLootMods.stray(wrapperLookup);

            return tableBuilder;
        });
    }
}
