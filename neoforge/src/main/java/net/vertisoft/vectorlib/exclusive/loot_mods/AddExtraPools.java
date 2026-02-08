package net.vertisoft.vectorlib.exclusive.loot_mods;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.MergePools;
import net.vertisoft.vectorlib.mixin_intf.LootTableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddExtraPools implements IGlobalLootModifier
{
    private final ResourceKey<LootTable> target_table;
    private final ResourceKey<LootTable> merging_table;

    public static final MapCodec<AddExtraPools> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("target_table").forGetter(AddExtraPools::getTargetTable),
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("merging_table").forGetter(AddExtraPools::getMergedTable)
        ).apply(instance, AddExtraPools::new);
    });

    public AddExtraPools(ResourceKey<LootTable> target_table, ResourceKey<LootTable> merging_table)
    {
        this.target_table = target_table;
        this.merging_table = merging_table;
    }

    public MapCodec<? extends IGlobalLootModifier> codec() { return NFLootModSet.ADD_EXTRA_POOLS.get(); }

    public ResourceKey<LootTable> getTargetTable() { return this.target_table; }
    public ResourceKey<LootTable> getMergedTable() {
        return this.merging_table;
    }

    public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        if (context.getQueriedLootTableId().equals(this.getTargetTable().location()))  return this.doApply(generatedLoot, context);
        return generatedLoot;
    }

    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        ObjectArrayList<ItemStack> mergeTarget = new ObjectArrayList<>();
        mergeTarget.addAll(generatedLoot);

        Optional<Holder.Reference<LootTable>> mergerTable = context.getResolver().get(Registries.LOOT_TABLE, this.merging_table);
        if (mergerTable.isPresent())
        {
            LootTable setGo = mergerTable.get().value();
            ServerLevel level = context.getLevel();
            setGo.getRandomItemsRaw(context, LootTable.createStackSplitter(level, mergeTarget::add));
            return mergeTarget;
        }
        return generatedLoot;
    }
}
