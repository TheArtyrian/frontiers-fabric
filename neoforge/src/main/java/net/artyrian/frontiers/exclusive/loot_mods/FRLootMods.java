package net.artyrian.frontiers.exclusive.loot_mods;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FRLootMods
{
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(
            NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Frontiers.MOD_ID);
}
