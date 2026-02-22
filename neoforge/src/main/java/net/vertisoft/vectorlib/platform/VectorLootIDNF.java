package net.vertisoft.vectorlib.platform;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.vertisoft.vectorlib.VectorLib;

public class VectorLootIDNF implements VectorLootIDIntf
{
    @Override
    public void setLootTableID(LootTable table, ResourceLocation id)
    {
        if (table.getLootTableId() == null)
        {
            table.setLootTableId(id);
        }
        else
        {
            VectorLib.LOGGER.error("Provided loot table already has an ID - {}", table.getLootTableId());
        }
    }
}
