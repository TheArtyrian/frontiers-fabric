package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.data.payloads.OreWitherPayload;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.event.PlayerBlockBreakEventReg;
import net.artyrian.frontiers.item.ModItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.*;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.potion.ModPotion;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.util.VanillaLootModify;
import net.artyrian.frontiers.util.VanillaLootReplace;
import net.artyrian.frontiers.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frontiers implements ModInitializer
{
	// Mod ID.
	public static final String MOD_ID = "frontiers";

	// Logger and FabricLoader
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final FabricLoader LOADER = FabricLoader.getInstance();

	// Config file
	public static FrontiersConfig CONFIG = new FrontiersConfig();

	// Mods with official compatibility with Frontiers.
	public static final String FARMERS_DELIGHT_ID = "farmersdelight";
	public static final String BOUNTIFUL_FARES_ID = "bountifulfares";
	public static final String SUPPLEMENTARIES_ID = "supplementaries";
	public static final String LEGACY4J_ID = "legacy";
	public static final String ETHERIA_ID = "etheria";
	public static final String ENHANCERMOD_ID = "enhancermod";

	public static final boolean FARMERS_DELIGHT_LOADED = LOADER.isModLoaded(FARMERS_DELIGHT_ID);
	public static final boolean BOUNTIFUL_FARES_LOADED = LOADER.isModLoaded(BOUNTIFUL_FARES_ID);
	public static final boolean SUPPLEMENTARIES_LOADED = LOADER.isModLoaded(SUPPLEMENTARIES_ID);
	public static final boolean LEGACY4J_LOADED = LOADER.isModLoaded(LEGACY4J_ID);
	public static final boolean ETHERIA_LOADED = LOADER.isModLoaded(ETHERIA_ID);
	public static final boolean ENHANCERMOD_LOADED = LOADER.isModLoaded(ENHANCERMOD_ID);

	// Check datagen mode
	// (Thanks Bount. Fares GitHub for actually giving me a coherent answer to this)
	public static boolean DOING_DATAGEN = checkDatagen();

	// Initializes mod content.
	@Override
	public void onInitialize()
	{
		LOGGER.info("Ready to explore new frontiers? No? Good! We're %$@#ing doing it anyway!!!! :3 (Frontiers mod init point)");
		LOGGER.info("Farmer's Delight Loaded: " + String.valueOf(FARMERS_DELIGHT_LOADED));
		LOGGER.info("Bountiful Fares Loaded: " + String.valueOf(BOUNTIFUL_FARES_LOADED));
		LOGGER.info("Supplementaries Loaded: " + String.valueOf(SUPPLEMENTARIES_LOADED));
		LOGGER.info("Legacy4J Loaded: " + String.valueOf(LEGACY4J_LOADED));
		LOGGER.info("Etheria Loaded: " + String.valueOf(ETHERIA_LOADED));
		LOGGER.info("GreyL1me's Enhancer Mod Loaded: " + String.valueOf(ENHANCERMOD_LOADED));

		// Register config file.
		CONFIG = FrontiersConfig.load_config();

		// Register payloads common-side.
		registerPayloads();

		// Register mod content.
		ModItem.registerModItems();						// Items.
		ModBlocks.registerModBlocks();					// Blocks (+ respective items).
		ModItemTabs.registerModItemTabs();				// Creative tabs for items.
		ModEntity.registerModEntities();				// Entities
		ModSounds.registerSounds();						// Sounds
		ModBlockSoundGroups.registerSounds();			// Block Group SFX
		ModStatusEffects.registerEffects();				// Status FX
		ModPotion.registerPotions();					// Potions
		ModWorldGeneration.generateModWorldGen();		// World Gen
		ModEvents.registerEvents();						// Custom events
		ModAttribute.registerModAttributes();			// Entity Attributes
		ModBlockProperties.registerProperties();		// Block Properties
		ModAttachmentTypes.registerModAttachments();	// Attribute Types (Custom data trackers)
		ModDamageType.registerDamages();				// Dmg types
		ModParticle.registerParticles();				// Particles

		// Modify a few things.
		VanillaLootModify.modify();					// Mods some loot tables.
		VanillaLootReplace.replace();				// Replaces some loot tables.
		ModFuelReg.execute();						// Mod fuels.
		ModCompostable.execute();					// Mod compostables.

		// Do event registries.
		PlayerBlockBreakEventReg.doReg();

		// MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
		if (FARMERS_DELIGHT_LOADED || DOING_DATAGEN)
		{
			FDItem.registerModItems();				// Farmer's Delight Items (Knives, etc.)
			FDItemTabs.registerModItemTabs();		// Farmer's Delight Item Tab addendums
		}
		if (BOUNTIFUL_FARES_LOADED || DOING_DATAGEN)
		{

		}
		if (SUPPLEMENTARIES_LOADED || DOING_DATAGEN)
		{

		}
		if (LEGACY4J_LOADED || DOING_DATAGEN)
		{

		}
		if (ETHERIA_LOADED || DOING_DATAGEN)
		{

		}
		if (ETHERIA_LOADED || DOING_DATAGEN)
		{

		}
	}

	// Better datagen check
	private static boolean checkDatagen()
	{
		try
		{
			Class.forName("net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint");
			return (System.getProperty("fabric-api.datagen") != null);
		}
		catch (ClassNotFoundException unused)
		{
			return false;
		}
	}

	public void registerPayloads()
	{
		PayloadTypeRegistry.playS2C().register(WitherHardmodePayload.ID, WitherHardmodePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(OreWitherPayload.ID, OreWitherPayload.CODEC);
	}
}