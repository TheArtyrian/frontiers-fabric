package net.vertisoft.vectorlib.exclusive;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.exclusive.loot_mods.AddExtraPools;
import net.vertisoft.vectorlib.exclusive.loot_mods.BlendPools;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.MixAndPick;
import net.vertisoft.vectorlib.exclusive.loot_mods.ReplaceTable;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.MergePools;
import net.vertisoft.vectorlib.exclusive.loot_mods.old.OneForAll;

import java.util.function.Supplier;

public class NFLootModSet
{
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, VectorLib.ID);

    public static final Supplier<MapCodec<ReplaceTable>> REPLACE_TABLE = REGISTRY.register("replace_table", () -> ReplaceTable.CODEC);
    public static final Supplier<MapCodec<AddExtraPools>> ADD_EXTRA_POOLS = REGISTRY.register("add_extra_pools", () -> AddExtraPools.CODEC);
    public static final Supplier<MapCodec<BlendPools>> BLEND_POOLS = REGISTRY.register("blend_pools", () -> BlendPools.CODEC);

    // OLD
    public static final Supplier<MapCodec<MergePools>> MERGE_POOLS = REGISTRY.register("merge_pools", () -> MergePools.CODEC);
    public static final Supplier<MapCodec<OneForAll>> ONE_FOR_ALL = REGISTRY.register("one_for_all", () -> OneForAll.CODEC);
    public static final Supplier<MapCodec<MixAndPick>> MIX_AND_PICK = REGISTRY.register("mix_and_pick", () -> MixAndPick.CODEC);
}
