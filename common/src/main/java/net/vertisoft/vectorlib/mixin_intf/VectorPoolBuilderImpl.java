package net.vertisoft.vectorlib.mixin_intf;

import net.minecraft.world.level.storage.loot.LootPool;

public interface VectorPoolBuilderImpl
{
    /** "Deconstructs" a Loot Pool into a builder, allowing for modifications. */
    static LootPool.Builder deconstruct(LootPool pool)
    {
        LootPool.Builder builder = LootPool.lootPool();

        VectorLootPoolImpl impl = ((VectorLootPoolImpl)pool);
        VectorPoolBuilderImpl buildimpl = ((VectorPoolBuilderImpl)builder);

        // TODO: FINISH UP DECONSTRUCTOR!

        builder.setRolls(impl.vectorLib$rolls());
        builder.setBonusRolls(impl.vectorLib$bonusRolls());

        return builder;
    }
}
