package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.client.screen.ModScreenHandlers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.bountifulfares.BFItemTabs;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.artyrian.frontiers.data.payloads.*;
import net.artyrian.frontiers.datagen.loot.loot_conditions.ModLootConditions;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.ModEntityDefaultAttr;
import net.artyrian.frontiers.event.ItemUseEvent;
import net.artyrian.frontiers.event.ModEvents;
import net.artyrian.frontiers.event.PlayerBlockBreakEventReg;
import net.artyrian.frontiers.event.VillagerTradeEventReg;
import net.artyrian.frontiers.item.ModItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.*;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.potion.ModPotion;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.recipe.ModRecipes;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.misc.FrontiersRandomTextList;
import net.artyrian.frontiers.util.VanillaLootModify;
import net.artyrian.frontiers.util.VanillaLootReplace;
import net.artyrian.frontiers.world.gen.ModWorldGeneration;
import net.artyrian.frontiers.world.poi.ModPointOfInterest;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

// Ready to reach new Frontiers?
// Created by Artyrian
public class Frontiers implements ModInitializer
{
	// Mod ID.
	public static final String MOD_ID = "frontiers";

	// Logger and FabricLoader
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final FabricLoader LOADER = FabricLoader.getInstance();

	// Config file
	public static FrontiersConfig CONFIG = new FrontiersConfig();

	// Death message providers
	public static final FrontiersRandomTextList DEATH_MSG = new FrontiersRandomTextList("death messages");
	public static final FrontiersRandomTextList HARDCORE_MSG = new FrontiersRandomTextList("hardcore death messages");

	// Mods that Frontiers works with.
	public static final String FARMERS_DELIGHT_ID = "farmersdelight";
	public static final String BOUNTIFUL_FARES_ID = "bountifulfares";
	public static final String APPLEDOG_ID = "appledog";
	public static final String SUPPLEMENTARIES_ID = "supplementaries";
	public static final String LEGACY4J_ID = "legacy";
	public static final String ETHYRIA_ID = "ethyria";
	public static final String ENHANCERMOD_ID = "enhancermod";
	public static final String YIGD_ID = "yigd";

	public static final boolean FARMERS_DELIGHT_LOADED = LOADER.isModLoaded(FARMERS_DELIGHT_ID);
	public static final boolean BOUNTIFUL_FARES_LOADED = LOADER.isModLoaded(BOUNTIFUL_FARES_ID);
	public static final boolean APPLEDOG_LOADED = LOADER.isModLoaded(APPLEDOG_ID);
	public static final boolean SUPPLEMENTARIES_LOADED = LOADER.isModLoaded(SUPPLEMENTARIES_ID);
	public static final boolean LEGACY4J_LOADED = LOADER.isModLoaded(LEGACY4J_ID);
	public static final boolean ETHYRIA_LOADED = LOADER.isModLoaded(ETHYRIA_ID);
	public static final boolean ENHANCERMOD_LOADED = LOADER.isModLoaded(ENHANCERMOD_ID);
	public static final boolean YIGD_LOADED = LOADER.isModLoaded(YIGD_ID);

	// Check datagen mode
	// (Thanks Bount. Fares GitHub for actually giving me a coherent answer to this)
	public static boolean DOING_DATAGEN = checkDatagen();

	// Special things involving dates
	private static int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	private static int month = Calendar.getInstance().get(Calendar.MONTH);
	public static final boolean IS_CHRISTMAS = (day == 25 && month == Calendar.DECEMBER);
	public static final boolean IS_APRIL_FOOLS = (day == 1 && month == Calendar.APRIL);
	public static final boolean IS_HALLOWEEN = (day == 31 && month == Calendar.OCTOBER);
	public static final boolean IS_THE_WORST_DAY_EVER = (day == 30 && month == Calendar.AUGUST);	// Artyrian's bday (EW)

	// List of important contributor IDs
	public static final Map<String, String> CONTRIB_IDS = Map.ofEntries(
            Map.entry("Artyrian", "774e37fc-1ca4-4156-827e-661afa24cb56"),
            Map.entry("Yurjezich", "2a9c377e-26cc-4d48-a62a-05ce3ac2f405"),
			Map.entry("KirbyTG", "651fefc2-fae9-46ea-b383-8e45798fc1b2"),
			Map.entry("Xenona", "708f1c4f-a652-4252-a090-855bafadd403"),
            Map.entry("LucarioDeath", "2f213cea-2443-4313-8aa4-0f4c72687ddd"),
            Map.entry("EmeraldEiscue", "3ab1a668-b818-4d44-b81c-ac1b105c7692"),
            Map.entry("Hecco", "bc56b2c8-9ef8-4532-b045-00f44804bca4"),
            Map.entry("Diemant", "32290fa8-77ed-4794-9cba-25c09e7f4e1d"),
            Map.entry("Yirmiri", "1cedf927-5c8f-4650-95e9-808fc8f94d00")
    );

	// Special cape list
	public static final Map<String, Identifier> CONTRIBUTOR_CAPES = Map.of(
			CONTRIB_IDS.get("Yurjezich"), Identifier.of(Frontiers.MOD_ID, "textures/entity/capes/yurjezich_cape.png"),
			CONTRIB_IDS.get("LucarioDeath"), Identifier.of(Frontiers.MOD_ID, "textures/entity/capes/ld_cape.png"),
			CONTRIB_IDS.get("EmeraldEiscue"), Identifier.of(Frontiers.MOD_ID, "textures/entity/capes/eiscue_cape.png")
	);

	// Initializes mod content.
	@Override
	public void onInitialize()
	{
		LOGGER.info("Ready to explore new frontiers? No? Good! We're %$@#ing doing it anyway!!!! :3 (Frontiers mod init point)");
		// LOGGER.info("[FRONTIERS] Farmer's Delight Loaded: " + String.valueOf(FARMERS_DELIGHT_LOADED));
		// LOGGER.info("[FRONTIERS] Bountiful Fares Loaded: " + String.valueOf(BOUNTIFUL_FARES_LOADED));
		// LOGGER.info("[FRONTIERS] Supplementaries Loaded: " + String.valueOf(SUPPLEMENTARIES_LOADED));
		// LOGGER.info("[FRONTIERS] Legacy4J Loaded: " + String.valueOf(LEGACY4J_LOADED));
		// LOGGER.info("[FRONTIERS] Etheria Loaded: " + String.valueOf(ETHERIA_LOADED));
		// LOGGER.info("[FRONTIERS] YIGD Loaded: " + String.valueOf(YIGD_LOADED));
		// LOGGER.info("[FRONTIERS] GreyL1me's Enhancer Mod Loaded: " + String.valueOf(ENHANCERMOD_LOADED));

		// Register config file.
		CONFIG = FrontiersConfig.load_config();

		// Register payloads common-side.
		registerPayloads();

		// Register mod content.
		ModItem.registerModItems();						// Items
		ModBlocks.registerModBlocks();					// Blocks (+ respective items)
		ModBlockEntities.registerBlockEntities();		// Block Entities
		ModItemTabs.registerModItemTabs();				// Creative tabs for items
		ModEntity.registerModEntities();				// Entities
		ModEntityDefaultAttr.register();				// Default Entity Attr.
		ModSounds.registerSounds();						// Sounds
		ModBlockSoundGroups.registerSounds();			// Block Group SFX
		ModStatusEffects.registerEffects();				// Status FX
		ModPotion.registerPotions();					// Potions
		ModWorldGeneration.generateModWorldGen();		// World Gen
		ModEvents.registerEvents();						// Custom events
		ModRecipes.registerRecipes();					// Custom recipe types
		ModAttribute.registerModAttributes();			// Entity Attributes
		ModBlockProperties.registerProperties();		// Block Properties
		ModAttachmentTypes.registerModAttachments();	// Attribute Types (Custom data trackers)
		ModDamageType.registerDamages();				// Dmg types
		ModParticle.registerParticles();				// Particles
		ModPointOfInterest.registerPOIs();				// POIs
		ModStats.registerStats();						// Stats
		ModScreenHandlers.registerScreens();			// Screens
		ModCriteria.registerCriterion();				// Advancement Criteria
		ModLootTables.registerLootTables();				// Chest Loot Tables
		ModLootConditions.registerConds();				// Loot Conditions
		ModDataComponents.registerComps();				// Item Data Components
		ModNetworkConstants.registerC2SPayloads();		// Client-to-Server Payloads

		// Modify a few things.
		VanillaLootModify.modify();						// Mods some loot tables
		VanillaLootReplace.replace();					// Replaces some loot tables
		ModFuelReg.execute();							// Mod fuels
		ModCompostable.execute();						// Mod compostables
		ModDispenserActions.execute();					// Mod dispensables

		// Do event registries.
		PlayerBlockBreakEventReg.doReg();
		VillagerTradeEventReg.doReg();
		ItemUseEvent.doReg();

		// MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
		if (FARMERS_DELIGHT_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] Farmer's Delight detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
			FDItem.registerModItems();				// Farmer's Delight Items (Knives, etc.)
			FDItemTabs.registerModItemTabs();		// Farmer's Delight Item Tab addendums
		}
		if (BOUNTIFUL_FARES_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] Bountiful Fares detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
			BFItem.registerModItems();				// Bountiful Fares Items (mainly refs to existing BF items)
			BFBlock.registerModBlocks();			// Bountiful Fares Blocks
			BFItemTabs.registerModItemTabs();		// Bountiful Fares Item Tab addendums
		}
		if (SUPPLEMENTARIES_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] Supplementaries detected.");
		}
		if (LEGACY4J_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] Legacy4J detected.");
		}
		if (ETHYRIA_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] Ethyria detected (no way is that the real xenrelle). Registering compat-exclusive content for " + Frontiers.MOD_ID);
		}
		if (ENHANCERMOD_LOADED || DOING_DATAGEN)
		{
			Frontiers.LOGGER.info("[FRONTIERS] GreyL1me's Enhancer Mod detected (oh my god it's greyl1me hiii). Registering compat-exclusive content for " + Frontiers.MOD_ID);
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

	// Payload register
	public void registerPayloads()
	{
		// Server --> Client
		PayloadTypeRegistry.playS2C().register(WitherHardmodePayload.ID, WitherHardmodePayload.CODEC);
		PayloadTypeRegistry.playS2C().register(OreWitherPayload.ID, OreWitherPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(PlayerAvariceTotemPayload.ID, PlayerAvariceTotemPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(SanitySyncPayload.ID, SanitySyncPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(CragsStalkerDespawnPayload.ID, CragsStalkerDespawnPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(CragsMonsterKillPayload.ID, CragsMonsterKillPayload.CODEC);

		// Client --> Server
		PayloadTypeRegistry.playC2S().register(BottleMessageWritePayload.ID, BottleMessageWritePayload.CODEC);
	}
}