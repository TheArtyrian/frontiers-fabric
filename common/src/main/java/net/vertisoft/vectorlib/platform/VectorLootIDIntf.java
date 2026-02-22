package net.vertisoft.vectorlib.platform;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

public interface VectorLootIDIntf
{
    void setLootTableID(LootTable table, ResourceLocation id);
}
