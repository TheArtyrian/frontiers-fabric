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
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;

public class ReplaceTable implements IGlobalLootModifier
{
    private final ResourceKey<LootTable> target_table;
    private final ResourceKey<LootTable> replacement_table;

    public static final MapCodec<ReplaceTable> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("target_table").forGetter(ReplaceTable::getTargetTable),
                ResourceKey.codec(Registries.LOOT_TABLE).fieldOf("replacement_table").forGetter(ReplaceTable::getReplacementTable)
        ).apply(instance, ReplaceTable::new);
    });

    public ReplaceTable(ResourceKey<LootTable> target_table, ResourceKey<LootTable> replacement_table)
    {
        this.target_table = target_table;
        this.replacement_table = replacement_table;
    }

    public MapCodec<? extends IGlobalLootModifier> codec() { return NFLootModSet.REPLACE_TABLE.get(); }

    public ResourceKey<LootTable> getTargetTable() { return this.target_table; }
    public ResourceKey<LootTable> getReplacementTable() {
        return this.replacement_table;
    }

    public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        if (context.getQueriedLootTableId().equals(this.getTargetTable().location()))  return this.doApply(generatedLoot, context);
        return generatedLoot;
    }

    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        Optional<Holder.Reference<LootTable>> newTable = context.getResolver().get(Registries.LOOT_TABLE, this.replacement_table);
        if (newTable.isPresent())
        {
            ObjectArrayList<ItemStack> senderPack = new ObjectArrayList<>();
            LootTable setGo = newTable.get().value();
            ServerLevel level = context.getLevel();
            setGo.getRandomItemsRaw(context, LootTable.createStackSplitter(level, senderPack::add));
            return senderPack;
        }
        return generatedLoot;
    }
}
