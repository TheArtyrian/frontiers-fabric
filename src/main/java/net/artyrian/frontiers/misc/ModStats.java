package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.inventory.Inventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class ModStats
{
    public static final Identifier OPEN_PERSONALCHEST = register("open_personalchest", StatFormatter.DEFAULT);

    private static Identifier register(String id, StatFormatter formatter) {
        Identifier identifier = Identifier.of(Frontiers.MOD_ID, id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }

    public static void registerStats()
    {

    }
}
