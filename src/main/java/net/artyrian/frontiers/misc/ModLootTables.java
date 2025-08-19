package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables
{
    // Chests
    public static RegistryKey<LootTable> CRAGS_ALTAR_CHEST = RegistryKey.of(
            RegistryKeys.LOOT_TABLE, Identifier.of(Frontiers.MOD_ID, "chests/crags_altar_chest"));

    // Gameplay ("Gift")
    public static RegistryKey<LootTable> SHULKER_BULLET = RegistryKey.of(
            RegistryKeys.LOOT_TABLE, Identifier.of(Frontiers.MOD_ID, "gameplay/shulker_bullet"));
    public static RegistryKey<LootTable> VEX_RAGE = RegistryKey.of(
            RegistryKeys.LOOT_TABLE, Identifier.of(Frontiers.MOD_ID, "gameplay/vex_rage"));

    public static void registerLootTables()
    {

    }
}
