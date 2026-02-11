package net.artyrian.frontiers.compat;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.bountifulfares.BFItemTabs;
import net.artyrian.frontiers.compat.dyemods.DyeModDummyItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;

public class FRIntegReg
{
    public static void initIntegr()
    {
        // MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
        if (Frontiers.FARMERS_DELIGHT_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Farmer's Delight detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
            FDItem.registerModItems(Frontiers.DOING_DATAGEN);		    // Farmer's Delight Items (Knives, etc.)
        }
        if (Frontiers.BOUNTIFUL_FARES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Bountiful Fares detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
            BFItem.registerModItems(Frontiers.DOING_DATAGEN);			// Bountiful Fares Items (mainly refs to existing BF items)
            BFBlock.registerModBlocks(Frontiers.DOING_DATAGEN);	        // Bountiful Fares Blocks
        }
        if (Frontiers.SUPPLEMENTARIES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Supplementaries detected.");
        }
        if (Frontiers.LEGACY4J_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Legacy4J detected.");
        }
        if (Frontiers.DELICATE_DYES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Delicate Dyes detected.");
        }

        // Datagen-dummy
        if (Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Doing mod datagen stuff!!!");
            DyeModDummyItem.registerDDyeItems();	// DDyes
        }
    }

    public static void integPost()
    {
        // Farmer's Delight Item Tab addendums
        if (Frontiers.FARMERS_DELIGHT_LOADED || Frontiers.DOING_DATAGEN)
        {
            FDItemTabs.registerModItemTabs();
        }
        // Bountiful Fares Item Tab addendums
        if (Frontiers.BOUNTIFUL_FARES_LOADED || Frontiers.DOING_DATAGEN)
        {
            BFItemTabs.registerModItemTabs();
        }
    }
}
