package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FRStats
{
    public static final Supplier<ResourceLocation> OPEN_PERSONALCHEST = register("open_personalchest");
    public static final Supplier<ResourceLocation> REMOVE_CURSE = register("remove_curse");
    public static final Supplier<ResourceLocation> HIT_BALL = register("hit_ball");
    public static final Supplier<ResourceLocation> INTERACT_WITH_FLETCHING_TABLE = register("interact_with_fletching_table");
    public static final Supplier<ResourceLocation> INTERACT_WITH_MONSTER_BAKERY = register("interact_with_monster_bakery");

    private static Supplier<ResourceLocation> register(String id)
    {
        return VectorLib.REGISTRY.registerStat(Frontiers.MOD_ID, id, () -> Frontiers.id(id));
    }

    public static Stat<ResourceLocation> getStat(ResourceLocation stat) { return getStat(stat, StatFormatter.DEFAULT); }
    public static Stat<ResourceLocation> getStat(ResourceLocation stat, StatFormatter form) { return Stats.CUSTOM.get(stat, form); }

    public static void registerStats()
    {

    }
}
