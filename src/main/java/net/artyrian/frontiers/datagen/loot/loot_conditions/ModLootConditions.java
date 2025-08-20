package net.artyrian.frontiers.datagen.loot.loot_conditions;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModLootConditions
{
    public static final LootConditionType HARDMODE_CHECK = register("hardmode_check", HardmodeLootCondition.CODEC);

    private static LootConditionType register(String id, MapCodec<? extends LootCondition> codec)
    {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, Identifier.of(Frontiers.MOD_ID, id), new LootConditionType(codec));
    }

    public static void registerConds()
    {

    }
}
