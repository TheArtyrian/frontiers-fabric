package net.artyrian.frontiers.reg.misc;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.condition.HardmodeLootCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FRLootConditions
{
    public static final Supplier<LootItemConditionType> HARDMODE_CHECK = register("hardmode_check", () -> HardmodeLootCondition.CODEC);

    private static <T extends LootItemCondition> Supplier<LootItemConditionType> register(String id, Supplier<MapCodec<T>> codec)
    {
        return VectorLib.REGISTRY.registerLootCondition(Frontiers.MOD_ID, id, codec);
    }

    public static void registerConds()
    {

    }
}
