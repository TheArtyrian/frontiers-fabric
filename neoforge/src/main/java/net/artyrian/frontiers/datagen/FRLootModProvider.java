package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.artyrian.frontiers.exclusive.loot_mods.ReplaceTableEvoker;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.vertisoft.vectorlib.exclusive.loot_mods.AddExtraPools;
import net.vertisoft.vectorlib.exclusive.loot_mods.BlendPools;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.MixAndPick;
import net.vertisoft.vectorlib.exclusive.loot_mods.ReplaceTable;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.OneForAll;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FRLootModProvider extends GlobalLootModifierProvider
{
    public FRLootModProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, Frontiers.MOD_ID);
    }

    @Override
    protected void start()
    {
        modify();
        replace();
    }

    private void modify()
    {
        add("modify_sniffer", new BlendPools(FRLootMods.SNIFFER_DIGS, FRLootMods.Modify.SNIFFER, 0.5F), List.of());
        add("modify_ruined_portal", new BlendPools(FRLootMods.RUINED_PORTAL, FRLootMods.Modify.RUINED_PORTAL, 0.6F), List.of());
        add("modify_ominous_vault_rare", new BlendPools(FRLootMods.OMINOUS_VAULT_RARE, FRLootMods.Modify.OMINOUS_TRIAL, 0.5F), List.of());
        add("modify_desert_temple_sus_sand", new BlendPools(FRLootMods.DESERT_PYRAMID_SUS, FRLootMods.Modify.DESERT_TEMPLE_SUS_SAND, 0.4F), List.of());

        add("modify_dungeon", new AddExtraPools(FRLootMods.DUNGEON, FRLootMods.Modify.MONSTER_ROOM), List.of());
        add("modify_buried_treasure", new AddExtraPools(FRLootMods.BURIED_TREASURE, FRLootMods.Modify.BURIED_TREASURE), List.of());
        add("modify_end_city", new AddExtraPools(FRLootMods.END_CITY, FRLootMods.Modify.END_CITY), List.of());
        add("modify_desert_temple", new AddExtraPools(FRLootMods.DESERT_CHEST, FRLootMods.Modify.DESERT_TEMPLE), List.of());
        add("modify_pillager_outpost", new AddExtraPools(FRLootMods.PILLAGER_OUTPOST, FRLootMods.Modify.PILLAGER_OUTPOST), List.of());
        add("modify_woodland_mansion", new AddExtraPools(FRLootMods.WOODLAND_MANSION, FRLootMods.Modify.WOODLAND_MANSION), List.of());

        add("modify_bastion_treasure", new AddExtraPools(FRLootMods.BASTION_TREASURE_CHEST, FRLootMods.Modify.BASTION_TREASURE), List.of());
        add("modify_bastion_stable", new AddExtraPools(FRLootMods.BASTION_HOGLIN_STABLE_CHEST, FRLootMods.Modify.BASTION_STABLE), List.of());
        add("modify_bastion_bridge", new AddExtraPools(FRLootMods.BASTION_BRIDGE_CHEST, FRLootMods.Modify.BASTION_BRIDGE), List.of());
        add("modify_bastion_other", new AddExtraPools(FRLootMods.BASTION_OTHER_CHEST, FRLootMods.Modify.BASTION_OTHER), List.of());

        add("modify_ravager", new AddExtraPools(FRLootMods.RAVAGER, FRLootMods.Modify.RAVAGER), List.of());
        add("modify_ghast", new AddExtraPools(FRLootMods.GHAST, FRLootMods.Modify.GHAST), List.of());
        add("modify_witch", new AddExtraPools(FRLootMods.WITCH, FRLootMods.Modify.WITCH), List.of());

        add("modify_spawner", new AddExtraPools(FRLootMods.SPAWNER, FRLootMods.Modify.SPAWNER), List.of());
    }

    private void replace()
    {
        add("replace_evoker", new ReplaceTableEvoker(FRLootMods.EVOKER, FRLootMods.Replace.EVOKER), List.of());
        add("replace_guardian", new ReplaceTable(FRLootMods.GUARDIAN, FRLootMods.Replace.GUARDIAN), List.of());
        add("replace_elder_guardian", new ReplaceTable(FRLootMods.ELDER_GUARDIAN, FRLootMods.Replace.ELDER_GUARDIAN), List.of());
        add("replace_stray", new ReplaceTable(FRLootMods.STRAY, FRLootMods.Replace.STRAY), List.of());
    }
}
