package net.vertisoft.vectorlib.mixin.impl;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.vertisoft.vectorlib.mixin_intf.VectorLootBuilderImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(LootTable.Builder.class)
public class LootBuilderMixin implements VectorLootBuilderImpl
{
    @Shadow @Final private ImmutableList.Builder<LootItemFunction> functions;
    @Shadow @Final public ImmutableList.Builder<LootPool> pools;

    @Override
    public LootTable.Builder vectorLib$addPools(List<LootPool> list)
    {
        this.pools.addAll(list);
        return (LootTable.Builder)(Object)this;
    }

    @Override
    public LootTable.Builder vectorLib$addFuncts(List<? extends LootItemFunction> list)
    {
        this.functions.addAll(list);
        return (LootTable.Builder)(Object)this;
    }
}
