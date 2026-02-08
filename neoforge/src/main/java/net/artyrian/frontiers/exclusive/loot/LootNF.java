package net.artyrian.frontiers.exclusive.loot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class LootNF
{
    public static void reg(LootTableLoadEvent event)
    {
        modify(event);
        replace(event);
    }

    private static void modify(LootTableLoadEvent event)
    {
        LootTable table = event.getTable();
        ResourceLocation name = event.getName();
    }

    private static void replace(LootTableLoadEvent event)
    {
        LootTable table = event.getTable();
        ResourceLocation name = event.getName();
    }
}
