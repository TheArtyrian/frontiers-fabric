package net.artyrian.frontiers.exclusive.loot_mods;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FRLootMods
{
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Frontiers.MOD_ID);

    public static final Supplier<MapCodec<ReplaceTableEvoker>> REPLACE_TABLE_EVOKER = REGISTRY.register("replace_table_evoker", () -> ReplaceTableEvoker.CODEC);
}
