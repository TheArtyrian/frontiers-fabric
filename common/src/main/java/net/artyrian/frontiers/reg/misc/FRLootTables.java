package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class FRLootTables
{
    // Chests
    public static ResourceKey<LootTable> CRAGS_ALTAR_CHEST = ResourceKey.create(
            Registries.LOOT_TABLE, Frontiers.id("chests/crags_altar_chest"));

    // Gameplay ("Gift")
    public static ResourceKey<LootTable> SHULKER_BULLET = ResourceKey.create(
            Registries.LOOT_TABLE, Frontiers.id("gameplay/shulker_bullet"));
    public static ResourceKey<LootTable> VEX_RAGE = ResourceKey.create(
            Registries.LOOT_TABLE, Frontiers.id("gameplay/vex_rage"));

    public static void registerLootTables()
    {

    }
}
