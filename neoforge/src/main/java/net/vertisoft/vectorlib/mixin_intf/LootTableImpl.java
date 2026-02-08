package net.vertisoft.vectorlib.mixin_intf;

import net.minecraft.world.level.storage.loot.LootPool;

import java.util.List;

public interface LootTableImpl
{
    List<LootPool> vectorLib$getPoolsForDelegate();
}
