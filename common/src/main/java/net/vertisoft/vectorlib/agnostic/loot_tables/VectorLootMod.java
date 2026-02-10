package net.vertisoft.vectorlib.agnostic.loot_tables;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.vertisoft.vectorlib.mixin_intf.VectorLootBuilderImpl;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A cross-loader solution for direct loot table modifications that:
 * <p>
 * - Actually modifies a provided table itself, rather than using smoke and mirrors to drop an item, making drop rates consistent across loaders
 * <p>
 * - Doesn't rely on json-based events and unique loot tables
 * <p>
 * - Should not interfere with other modification systems, such as Fabric's
 * <p>
 * Any modifications should be done during the common mod startup (after item definitions) for best practice.
 * <p>
 * <a href="https://github.com/FabricMC/fabric-api/tree/1.21.1/fabric-loot-api-v3/src/main/java/net/fabricmc/fabric/api/loot/v3">
 *     Code handling is heavily inspired by the Fabric Loot Table modification system (v3).
 * </a>
 */
public class VectorLootMod
{
    public static class Replace
    {
        private static final Map<ResourceKey<LootTable>, List<Lambda>> HASH = new HashMap<>();

        @FunctionalInterface
        public interface Lambda
        {
            @Nullable LootTable replace(ResourceKey<LootTable> key, LootTable table, HolderLookup.Provider wrapper);
        }

        public static void add(ResourceKey<LootTable> key, Lambda lambda)
        {
            if (HASH.containsKey(key) && HASH.get(key) != null)
            {
                List<Lambda> lambList = HASH.get(key);
                lambList.add(lambda);
            }
            else
            {
                List<Lambda> lister = new ArrayList<>();
                lister.add(lambda);
                HASH.put(key, lister);
            }
        }

        /** Runs through the data map. Returns a pair - a LootTable, and a boolean determining if anything changed. */
        private static Pair<LootTable, Boolean> run(ResourceKey<LootTable> table_key, LootTable table, HolderLookup.Provider wrapper)
        {
            boolean changed = false;
            LootTable retTable = table;

            for (ResourceKey<LootTable> hashKey : HASH.keySet())
            {
                if (hashKey == table_key)
                {
                    List<Lambda> list = HASH.get(hashKey);
                    for (Lambda lamb : list)
                    {
                        retTable = lamb.replace(table_key, table, wrapper);
                    }
                    changed = true;
                    break;
                }
            }

            return new Pair<>(retTable, changed);
        }
    }

    public static class Modify
    {
        private static final Map<ResourceKey<LootTable>, List<Lambda>> HASH = new HashMap<>();

        @FunctionalInterface
        public interface Lambda
        {
            void modify(ResourceKey<LootTable> key, LootTable.Builder builder, VectorLootBuilderImpl casted, HolderLookup.Provider wrapper);
        }

        public static void add(ResourceKey<LootTable> key, Lambda lambda)
        {
            if (HASH.containsKey(key) && HASH.get(key) != null)
            {
                List<Lambda> lambList = HASH.get(key);
                lambList.add(lambda);
            }
            else
            {
                List<Lambda> lister = new ArrayList<>();
                lister.add(lambda);
                HASH.put(key, lister);
            }
        }

        /** Runs through the data map. Returns the builder provided with any modifications. */
        private static LootTable.Builder run(ResourceKey<LootTable> table_key, LootTable.Builder builder, HolderLookup.Provider wrapper)
        {
            for (ResourceKey<LootTable> hashKey : HASH.keySet())
            {
                if (hashKey == table_key)
                {
                    List<Lambda> list = HASH.get(hashKey);
                    for (Lambda lamb : list)
                    {
                        lamb.modify(table_key, builder, (VectorLootBuilderImpl)builder, wrapper);
                    }
                }
            }

            return builder;
        }
    }

    public static <T> T runModifier(T parse, ResourceLocation id, RegistryOps<JsonElement> ops, WeakHashMap<RegistryOps<JsonElement>, HolderLookup.Provider> wrappers)
    {
        if (parse instanceof LootTable table && table != LootTable.EMPTY)
        {
            ResourceKey<LootTable> table_key = ResourceKey.create(Registries.LOOT_TABLE, id);
            HolderLookup.Provider registries = wrappers.get(ops);

            LootTable.Builder builder = VectorLootBuilderImpl.deconstruct(table);

            return (T)builder.build();
        }
        else return parse;
    }
}
