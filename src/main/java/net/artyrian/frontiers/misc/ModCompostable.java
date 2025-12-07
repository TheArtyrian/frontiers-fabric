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

        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ROSE,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.VIOLET_ROSE,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.VIOLET_ROSE_BUSH,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.SNOW_DAHLIA,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.FUNGAL_DAFFODIL,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.CRIMCONE,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.CARVED_MELON,0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModItem.WARPED_WART,0.65f);

        CompostingChanceRegistry.INSTANCE.add(ModBlocks.SUGAR_CANE_BLOCK,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.COCOA_BEAN_BLOCK,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModItem.LEVI_ROLL,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ANCIENT_ROSE,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.ANCIENT_ROSE_BUSH,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.WHITE_PUMPKIN,0.85f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.FUNGAL_DAFFODIL_BLOCK,0.85f);

        CompostingChanceRegistry.INSTANCE.add(ModItem.TRUFFLE,1.0f);
        CompostingChanceRegistry.INSTANCE.add(ModItem.EXPERIWINKLE_BULB,1.0f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.EXPERIWINKLE,1.0f);
    }
}
