package net.artyrian.frontiers.reg.misc;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.loot.condition.HardmodeLootCondition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class ModLootConditions
{
    public static final LootItemConditionType HARDMODE_CHECK = register("hardmode_check", HardmodeLootCondition.CODEC);

    private static LootItemConditionType register(String id, MapCodec<? extends LootItemCondition> codec)
    {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id), new LootItemConditionType(codec));
    }

    public static void registerConds()
    {

    }
}
