package net.artyrian.frontiers.exclusive.loot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

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
