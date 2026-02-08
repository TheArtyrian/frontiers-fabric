package net.vertisoft.vectorlib.exclusive.loot_mods.old;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import jdk.jfr.Experimental;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;
import net.vertisoft.vectorlib.mixin_intf.LootPoolImpl;
import net.vertisoft.vectorlib.mixin_intf.LootTableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/** An old attempt at loot pool combining. Best not to use as it favors your additions FAR more.  */
@Experimental
public class MixAndPick implements IGlobalLootModifier
{
    private final ResourceKey<LootTable> target_table;
    private final ResourceKey<LootTable> merging_table;

    public static final MapCodec<MixAndPick> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("target_table").forGetter(MixAndPick::getTargetTable),
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("merging_table").forGetter(MixAndPick::getMergedTable)
        ).apply(instance, MixAndPick::new);
    });

    public MixAndPick(ResourceKey<LootTable> target_table, ResourceKey<LootTable> merging_table)
    {
        this.target_table = target_table;
        this.merging_table = merging_table;
    }

    public MapCodec<? extends IGlobalLootModifier> codec() { return NFLootModSet.MIX_AND_PICK.get(); }

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
        Optional<Holder.Reference<LootTable>> mergerTable = context.getResolver().get(Registries.LOOT_TABLE, this.merging_table);
        if (mergerTable.isPresent())
        {
            Optional<Holder.Reference<LootTable>> targeter = context.getResolver().get(Registries.LOOT_TABLE, this.target_table);
            if (targeter.isPresent())
            {
                List<LootPool> poolsetOld = ((LootTableImpl)targeter.get().value()).vectorLib$getPoolsForDelegate();
                List<LootPool> poolsetNew = ((LootTableImpl)mergerTable.get().value()).vectorLib$getPoolsForDelegate();

                if (poolsetNew.size() > 1 || poolsetOld.size() > 1)
                {
                    VectorLib.LOGGER.error("LootMod -> MixAndPick: Provided Loot Tables must have one pool each. Falling back to default drops.");
                }
                else
                {
                    LootTable.Builder builder = LootTable.lootTable();
                    LootPool.Builder poolBuilder = LootPool.lootPool();

                    for (ItemStack stack : generatedLoot)
                    {
                        if (!stack.isEmpty())
                        {
                            LootPoolSingletonContainer.Builder<?> builderX = LootItem.lootTableItem(stack.getItem());
                            if (!stack.getComponents().isEmpty())
                            {
                                for (DataComponentType type : stack.getComponents().keySet())
                                {
                                    if (stack.getComponents().get(type) != null) builderX.apply(SetComponentsFunction.setComponent(type, stack.getComponents().get(type)));
                                }
                            }
                            poolBuilder.add(builderX);
                        }
                    }

                    LootPool pool = poolBuilder.build();

                    // Gets all the main data from the old set - the new ones SHOULD NOT MATTER.
                    ((LootPoolImpl)pool).vectorLib$absorbImportant((LootPoolImpl)poolsetOld.getFirst());

                    for (LootPool poolarg : poolsetNew)
                    {
                        ((LootPoolImpl)pool).vectorLib$mergeUltraMega((LootPoolImpl) poolarg);
                    }

                    builder.pools.add(pool);
                    LootTable finale = builder.build();

                    ObjectArrayList<ItemStack> mergeTarget = new ObjectArrayList<>();
                    ServerLevel level = context.getLevel();
                    finale.getRandomItemsRaw(context, LootTable.createStackSplitter(level, mergeTarget::add));
                    return mergeTarget;
                }
            }
        }
        return generatedLoot;
    }
}
