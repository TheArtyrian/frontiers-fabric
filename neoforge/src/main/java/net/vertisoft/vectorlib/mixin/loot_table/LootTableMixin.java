package net.vertisoft.vectorlib.mixin.loot_table;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.vertisoft.vectorlib.mixin_intf.LootTableImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(LootTable.class)
public class LootTableMixin implements LootTableImpl
{
    @Shadow @Final private List<LootPool> pools;

    @Override public List<LootPool> vectorLib$getPoolsForDelegate() { return this.pools; }
}
