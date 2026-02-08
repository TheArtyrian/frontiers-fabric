package net.vertisoft.vectorlib.mixin_intf;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public interface LootPoolImpl
{
    NumberProvider vectorLib$getRollSet();
    NumberProvider vectorLib$getBonusRollSet();
    List<LootPoolEntryContainer> vectorLib$getEntryContainerSet();
    List<LootItemCondition> vectorLib$getCondContainerSet();
    List<LootItemFunction> vectorLib$getFunctContainerSet();
    String vectorLib$getNameData();
    Predicate<LootContext> vectorLib$getCompositeSet();
    BiFunction<ItemStack, LootContext, ItemStack> vectorLib$getCompositeFunctionSet();

    void vectorLib$absorbImportant(LootPoolImpl pooltarg);
    void vectorLib$mergeUltraMega(LootPoolImpl pooltarg);
}
