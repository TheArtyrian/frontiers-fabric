package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.FRIntegReg;
import net.artyrian.frontiers.definition.loot.FRLootMods;
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
    public static final String DUNGEONS_DELIGHT_ID = "dungeonsdelight";
    public static final String AETHER_ID = "aether";
    public static final String QUARK_ID = "quark";

    public static boolean FARMERS_DELIGHT_LOADED;
    public static boolean BOUNTIFUL_FARES_LOADED;
    public static boolean APPLEDOG_LOADED;
    public static boolean AEU_LOADED;
    public static boolean SUPPLEMENTARIES_LOADED;
    public static boolean LEGACY4J_LOADED;
    public static boolean ENHANCERMOD_LOADED;
    public static boolean YIGD_LOADED;
    public static boolean DELICATE_DYES_LOADED;
    public static boolean DUNGEONS_DELIGHT_LOADED;
    public static boolean AETHER_LOADED;
    public static boolean QUARK_LOADED;

    public static void init()
    {
        LOGGER.info("Ready to explore new frontiers? No? Good! We're %$@#ing doing it anyway!!!! :3 (Frontiers mod init point)");

        // Register config file.
        CONFIG = FrontiersConfig.load_config();

        // Check loaded integratable mods
        checkLoadedMods();

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
        FRLevelEvents.register();                       // VectorLib Level Events

        FRLootMods.Modify.bootstrap();                  // Loot Modifiers - Modify
        FRLootMods.Replace.bootstrap();                 // Loot Modifiers - Replace

        // Mod integration
        FRIntegReg.initIntegr();

        // VectorLib content
        String capeDir = "textures/entity/capes/";
        VectorLib.SYSTEM.addContribCape(
                VectorLib.SYSTEM.getContribID("Yurjezich"), Frontiers.id(capeDir + "yurjezich_cape.png"), false);
        VectorLib.SYSTEM.addContribCape(
                VectorLib.SYSTEM.getContribID("LucarioDeath"), Frontiers.id(capeDir + "ld_cape.png"), false);
        VectorLib.SYSTEM.addContribCape(
                VectorLib.SYSTEM.getContribID("EmeraldEiscue"), Frontiers.id(capeDir + "eiscue_cape.png"), false);
        VectorLib.SYSTEM.addContribCape(
                VectorLib.SYSTEM.getContribID("Courtjjester"), Frontiers.id(capeDir + "courtjjester_cape.png"), true);

        if (VectorLib.PLATFORM.isClient()) FRRegistries.Splash.register();
    }

    private static void checkLoadedMods()
    {
        FARMERS_DELIGHT_LOADED = VectorLib.PLATFORM.isModLoaded(FARMERS_DELIGHT_ID);
        BOUNTIFUL_FARES_LOADED = VectorLib.PLATFORM.isModLoaded(BOUNTIFUL_FARES_ID);
        APPLEDOG_LOADED = VectorLib.PLATFORM.isModLoaded(APPLEDOG_ID);
        AEU_LOADED = VectorLib.PLATFORM.isModLoaded(AEU_ID);
        SUPPLEMENTARIES_LOADED = VectorLib.PLATFORM.isModLoaded(SUPPLEMENTARIES_ID);
        LEGACY4J_LOADED = VectorLib.PLATFORM.isModLoaded(LEGACY4J_ID);
        ENHANCERMOD_LOADED = VectorLib.PLATFORM.isModLoaded(ENHANCERMOD_ID);
        YIGD_LOADED = VectorLib.PLATFORM.isModLoaded(YIGD_ID);
        DELICATE_DYES_LOADED = VectorLib.PLATFORM.isModLoaded(DELICATE_DYES_ID);
        DUNGEONS_DELIGHT_LOADED = VectorLib.PLATFORM.isModLoaded(DUNGEONS_DELIGHT_ID);
        AETHER_LOADED = VectorLib.PLATFORM.isModLoaded(AETHER_ID);
        QUARK_LOADED = VectorLib.PLATFORM.isModLoaded(QUARK_ID);
    }

    public static ResourceLocation id(String string) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, string); }
    public static ResourceLocation id(String id, String string) { return ResourceLocation.fromNamespaceAndPath(id, string); }
}