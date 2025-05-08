package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables
{
    public static RegistryKey<LootTable> CRAGS_ALTAR_CHEST = RegistryKey.of(
            RegistryKeys.LOOT_TABLE, Identifier.of(Frontiers.MOD_ID, "chests/crags_altar_chest"));

    public static void registerLootTables()
    {

    }
}
