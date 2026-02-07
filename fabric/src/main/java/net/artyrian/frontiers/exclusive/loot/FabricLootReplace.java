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
            if (FRLootMods.EVOKER == key && Frontiers.CONFIG.doEvokerRebalance())       return FRLootMods.Replace.evokerRebalance(wrapperLookup).build();
            else if (FRLootMods.GUARDIAN == key)                                        return FRLootMods.Replace.guardian(wrapperLookup).build();
            else if (FRLootMods.ELDER_GUARDIAN == key)                                  return FRLootMods.Replace.elderGuardian(wrapperLookup).build();
            else if (FRLootMods.STRAY == key)                                           return FRLootMods.Replace.stray(wrapperLookup).build();

            return tableBuilder;
        });
    }
}
