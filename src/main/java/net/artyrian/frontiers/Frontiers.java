package net.artyrian.frontiers;

import net.artyrian.frontiers.item.ModItems;
import net.fabricmc.api.ModInitializer;

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
		ModItems.registerModItems();
	}
}