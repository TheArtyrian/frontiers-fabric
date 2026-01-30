package net.artyrian.frontiers.exclusive.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class LootNF
{
    public static void reg(LootTableLoadEvent event)
    {
        modify(event);
        replace(event);
    }

    private static void modify(LootTableLoadEvent event)
    {
        LootTable table = event.getTable();
        ResourceLocation name = event.getName();
    }

    private static void replace(LootTableLoadEvent event)
    {
        //HolderLookup.Provider.

        //if (FRLootMods.EVOKER.location() == name && Frontiers.CONFIG.doEvokerRebalance())       event.setTable(FRLootMods.evokerRebalance(wrapperLookup));
        //else if (FRLootMods.GUARDIAN.location() == name)                                        event.setTable(FRLootMods.guardian(wrapperLookup));
        //else if (FRLootMods.ELDER_GUARDIAN.location() == name)                                  event.setTable(FRLootMods.elderGuardian(wrapperLookup));
        //else if (FRLootMods.STRAY.location() == name)                                           event.setTable(FRLootMods.stray(wrapperLookup));
    }
}
