package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.VanillaItem;
import net.artyrian.frontiers.misc.ModPredicate;
import net.artyrian.frontiers.misc.ModRarity;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frontiers implements ModInitializer
{
	// Mod ID and logger pointer.
	public static final String MOD_ID = "frontiers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Initializes mod content.
	@Override
	public void onInitialize()
	{
		LOGGER.info("Frontiers Mod init! Hello there! :3");

		// Register mod content.
		ModItem.registerModItems();					// Items.
		ModBlocks.registerModBlocks();				// Blocks (+ respective items).
		ModItemTabs.registerModItemTabs();			// Creative tabs for items.
		ModEntity.registerModEntities();			// Entities
		ModPredicate.registerModPredicates();		// Item predicates.
	}
}