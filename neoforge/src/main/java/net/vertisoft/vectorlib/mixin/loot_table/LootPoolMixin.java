package net.vertisoft.vectorlib.mixin.loot_table;

import net.minecraft.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.mixin_intf.LootPoolImpl;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@Mixin(LootPool.class)
public class LootPoolMixin implements LootPoolImpl
{
    @Shadow @Final @Mutable private List<LootPoolEntryContainer> entries;
    @Shadow @Final @Mutable private List<LootItemCondition> conditions;
    @Shadow @Final @Mutable private List<LootItemFunction> functions;

    @Shadow @Mutable private NumberProvider rolls;
    @Shadow @Mutable private NumberProvider bonusRolls;
    @Shadow @Final @Mutable private Predicate<LootContext> compositeCondition;
    @Shadow @Final @Mutable private BiFunction<ItemStack, LootContext, ItemStack> compositeFunction;
    @Shadow @Nullable @Mutable private String name;

    @Override public NumberProvider vectorLib$getRollSet() { return this.rolls; }
    @Override public NumberProvider vectorLib$getBonusRollSet() { return this.bonusRolls; }
    @Override public String vectorLib$getNameData() { return this.name; }
    @Override public Predicate<LootContext> vectorLib$getCompositeSet() { return this.compositeCondition; }
    @Override public BiFunction<ItemStack, LootContext, ItemStack> vectorLib$getCompositeFunctionSet() { return this.compositeFunction; }

    @Override public List<LootPoolEntryContainer> vectorLib$getEntryContainerSet() { return this.entries; }
    @Override public List<LootItemCondition> vectorLib$getCondContainerSet() { return this.conditions; }
    @Override public List<LootItemFunction> vectorLib$getFunctContainerSet() { return this.functions; }

    @Override
    public void vectorLib$absorbImportant(LootPoolImpl pooltarg)
    {
        this.rolls = pooltarg.vectorLib$getRollSet();
        this.bonusRolls = pooltarg.vectorLib$getBonusRollSet();
        this.name = pooltarg.vectorLib$getNameData();
        this.compositeCondition = pooltarg.vectorLib$getCompositeSet();
        this.compositeFunction = pooltarg.vectorLib$getCompositeFunctionSet();
    }

    @Override
    public void vectorLib$mergeUltraMega(LootPoolImpl pooltarg)
    {
        List<LootPoolEntryContainer> entries_old = List.copyOf(this.entries);
        List<LootItemCondition> conditions_old = List.copyOf(this.conditions);
        List<LootItemFunction> functions_old = List.copyOf(this.functions);

        List<LootPoolEntryContainer> entries_inbound = pooltarg.vectorLib$getEntryContainerSet();
        List<LootItemCondition> conditions_inbound = pooltarg.vectorLib$getCondContainerSet();
        List<LootItemFunction> functions_inbound = pooltarg.vectorLib$getFunctContainerSet();

        this.entries = new ArrayList<>(0);
        this.conditions = new ArrayList<>(0);
        this.functions = new ArrayList<>(0);

        this.entries.addAll(entries_old);
        this.entries.addAll(entries_inbound);

        this.conditions.addAll(conditions_old);
        this.conditions.addAll(conditions_inbound);

        this.functions.addAll(functions_old);
        this.functions.addAll(functions_inbound);
    }
}
