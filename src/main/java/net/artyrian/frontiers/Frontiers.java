package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModCompostable;
import net.artyrian.frontiers.misc.ModFuelReg;
import net.artyrian.frontiers.util.VanillaLootModify;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import net.fabricmc.loader.api.FabricLoader;
import java.lang.Integer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frontiers implements ModInitializer
{
	// Mod ID.
	public static final String MOD_ID = "frontiers";

	// Logger and FabricLoader
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final FabricLoader LOADER = FabricLoader.getInstance();

	// Mods with official compatibility with Frontiers.
	public static final String FARMERS_DELIGHT_ID = "farmersdelight";
	public static final String BOUNTIFUL_FARES_ID = "bountifulfares";
	public static final String SUPPLEMENTARIES_ID = "supplementaries";

	public static final boolean FARMERS_DELIGHT_LOADED = LOADER.isModLoaded(FARMERS_DELIGHT_ID);
	public static final boolean BOUNTIFUL_FARES_LOADED = LOADER.isModLoaded(BOUNTIFUL_FARES_ID);
	public static final boolean SUPPLEMENTARIES_LOADED = LOADER.isModLoaded(SUPPLEMENTARIES_ID);

	// Initializes mod content.
	@Override
	public void onInitialize()
	{
		LOGGER.info("Frontiers Mod init! Hello there! :3");
		LOGGER.info("Farmer's Delight Loaded: " + String.valueOf(FARMERS_DELIGHT_LOADED));
		LOGGER.info("Bountiful Fares Loaded: " + String.valueOf(BOUNTIFUL_FARES_LOADED));
		LOGGER.info("Supplementaries Loaded: " + String.valueOf(SUPPLEMENTARIES_LOADED));

		// Register mod content.
		ModItem.registerModItems();					// Items.
		ModBlocks.registerModBlocks();				// Blocks (+ respective items).
		ModItemTabs.registerModItemTabs();			// Creative tabs for items.
		ModEntity.registerModEntities();			// Entities

		// Modify a few things.
		VanillaLootModify.modify();					// Mods some loot tables.
		ModFuelReg.execute();						// Mod fuels.
		ModCompostable.execute();					// Mod compostables.

		// MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
		if (FARMERS_DELIGHT_LOADED)
		{
			FDItem.registerModItems();				// Farmer's Delight Items (Knives, etc.)
			FDItemTabs.registerModItemTabs();		// Farmer's Delight Item Tab addendums
		}
		if (BOUNTIFUL_FARES_LOADED)
		{

		}
		if (SUPPLEMENTARIES_LOADED)
		{

		}
	}
}