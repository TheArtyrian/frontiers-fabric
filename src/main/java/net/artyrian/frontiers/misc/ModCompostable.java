package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

// Compostable item registry for compostable items.
public class ModCompostable
{
    public static void execute()
    {
        CompostingChanceRegistry.INSTANCE.add(ModItem.ANCIENT_ROSE_SEED,0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ANCIENT_ROSE,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ANCIENT_ROSE_BUSH,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ROSE,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.VIOLET_ROSE,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.VIOLET_ROSE_BUSH,0.65f);
    }
}
