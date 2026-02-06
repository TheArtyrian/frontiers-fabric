package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.bountifulfares.BFItemTabs;
import net.artyrian.frontiers.compat.dyemods.DyeModDummyItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.reg.content.*;
import net.artyrian.frontiers.reg.misc.*;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.systems.FrontiersEventSystem;
import net.artyrian.frontiers.systems.FrontiersRandomTextList;
import net.minecraft.resources.ResourceLocation;
import net.vertisoft.vectorlib.VectorLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frontiers
{
    // Mod ID.
    public static final String MOD_ID = "frontiers";

    // Logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Config file
    public static FrontiersConfig CONFIG = new FrontiersConfig();

    // Frontiers event system
    public static final FrontiersEventSystem EVENTS = new FrontiersEventSystem();

    // Death message providers
    public static final FrontiersRandomTextList DEATH_MSG = new FrontiersRandomTextList("death messages");
    public static final FrontiersRandomTextList HARDCORE_MSG = new FrontiersRandomTextList("hardcore death messages");

    public static final boolean DOING_DATAGEN = VectorLib.PLATFORM.isDatagen();

    // Mods that Frontiers works with.
    public static final String FARMERS_DELIGHT_ID = "farmersdelight";
    public static final String BOUNTIFUL_FARES_ID = "bountifulfares";
    public static final String APPLEDOG_ID = "appledog";
    public static final String AEU_ID = "aeu";
    public static final String SUPPLEMENTARIES_ID = "supplementaries";
    public static final String LEGACY4J_ID = "legacy";
    public static final String ENHANCERMOD_ID = "enhancermod";
    public static final String YIGD_ID = "yigd";
    public static final String DELICATE_DYES_ID = "delicate_dyes";

    public static final boolean FARMERS_DELIGHT_LOADED = VectorLib.PLATFORM.isModLoaded(FARMERS_DELIGHT_ID);
    public static final boolean BOUNTIFUL_FARES_LOADED = VectorLib.PLATFORM.isModLoaded(BOUNTIFUL_FARES_ID);
    public static final boolean APPLEDOG_LOADED = VectorLib.PLATFORM.isModLoaded(APPLEDOG_ID);
    public static final boolean AEU_LOADED = VectorLib.PLATFORM.isModLoaded(AEU_ID);
    public static final boolean SUPPLEMENTARIES_LOADED = VectorLib.PLATFORM.isModLoaded(SUPPLEMENTARIES_ID);
    public static final boolean LEGACY4J_LOADED = VectorLib.PLATFORM.isModLoaded(LEGACY4J_ID);
    public static final boolean ENHANCERMOD_LOADED = VectorLib.PLATFORM.isModLoaded(ENHANCERMOD_ID);
    public static final boolean YIGD_LOADED = VectorLib.PLATFORM.isModLoaded(YIGD_ID);
    public static final boolean DELICATE_DYES_LOADED = VectorLib.PLATFORM.isModLoaded(DELICATE_DYES_ID);

    public static void init()
    {
        LOGGER.info("Ready to explore new frontiers? No? Good! We're %$@#ing doing it anyway!!!! :3 (Frontiers mod init point)");

        // Register config file.
        CONFIG = FrontiersConfig.load_config();

        // Register mod content.
        ModItem.registerModItems();						// Items
        ModBlocks.registerModBlocks();					// Blocks (+ respective items)
        ModBlockEntities.registerBlockEntities();		// Block Entities
        ModEntity.registerModEntities();				// Entities
        ModFeature.registerFeatures();					// Features
        ModStructure.registerStructures();				// Structure
        ModSounds.registerSounds();						// Sounds
        ModStatusEffects.registerEffects();				// Status FX
        ModPotion.registerPotions();					// Potions
        ModRecipes.registerRecipes();					// Custom recipe types
        ModAttribute.registerModAttributes();			// Entity Attributes
        ModBlockProperties.registerProperties();		// Block Properties
        ModDamageType.registerDamages();				// Dmg types
        ModParticle.registerParticles();				// Particles
        ModStats.registerStats();						// Stats
        ModScreenHandlers.registerScreens();			// Screens
        ModCriteria.registerCriterion();				// Advancement Criteria
        ModLootTables.registerLootTables();				// Chest Loot Tables
        FRTrade.bootstrap();				            // Villager trades
        ModLootConditions.registerConds();				// Loot Conditions
        ModDataComponents.registerComps();				// Item Data Components


        // MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
        if (FARMERS_DELIGHT_LOADED || DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Farmer's Delight detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
            FDItem.registerModItems(DOING_DATAGEN);		    // Farmer's Delight Items (Knives, etc.)
            FDItemTabs.registerModItemTabs();		        // Farmer's Delight Item Tab addendums
        }
        if (BOUNTIFUL_FARES_LOADED || DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Bountiful Fares detected. Registering compat-exclusive content for " + Frontiers.MOD_ID);
            BFItem.registerModItems(DOING_DATAGEN);			// Bountiful Fares Items (mainly refs to existing BF items)
            BFBlock.registerModBlocks(DOING_DATAGEN);	    // Bountiful Fares Blocks
            BFItemTabs.registerModItemTabs();		        // Bountiful Fares Item Tab addendums
        }
        if (SUPPLEMENTARIES_LOADED || DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Supplementaries detected.");
        }
        if (LEGACY4J_LOADED || DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Legacy4J detected.");
        }
        if (DELICATE_DYES_LOADED || DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Delicate Dyes detected.");
        }

        // Datagen-dummy
        if (DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Doing mod datagen stuff!!!");
            DyeModDummyItem.registerDDyeItems();	// DDyes
        }

        // VectorLib content
        VectorLib.SYSTEM.CONTRIBUTOR_CAPES.put(
                VectorLib.SYSTEM.CONTRIB_IDS.get("Yurjezich"),
                Frontiers.id("textures/entity/capes/yurjezich_cape.png")
        );
        VectorLib.SYSTEM.CONTRIBUTOR_CAPES.put(
                VectorLib.SYSTEM.CONTRIB_IDS.get("LucarioDeath"),
                Frontiers.id("textures/entity/capes/ld_cape.png")
        );
        VectorLib.SYSTEM.CONTRIBUTOR_CAPES.put(
                VectorLib.SYSTEM.CONTRIB_IDS.get("EmeraldEiscue"),
                Frontiers.id("textures/entity/capes/eiscue_cape.png")
        );
        VectorLib.SYSTEM.CONTRIBUTOR_CAPES.put(
                VectorLib.SYSTEM.CONTRIB_IDS.get("Courtjjester"),
                Frontiers.id("textures/entity/capes/courtjjester_cape.png")
        );
        VectorLib.SYSTEM.TRANSPARENT_CAPES.add(VectorLib.SYSTEM.CONTRIB_IDS.get("Courtjjester"));
    }

    public static ResourceLocation id(String string) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, string); }
    public static ResourceLocation id(String id, String string) { return ResourceLocation.fromNamespaceAndPath(id, string); }
}