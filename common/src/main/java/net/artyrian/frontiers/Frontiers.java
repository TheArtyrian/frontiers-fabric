package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.loot.FRLootMods;
import net.artyrian.frontiers.reg.content.*;
import net.artyrian.frontiers.reg.misc.*;
import net.artyrian.frontiers.reg.property.*;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.artyrian.frontiers.reg.world.FRStructures;
import net.artyrian.frontiers.systems.FrontiersEventSystem;
import net.artyrian.frontiers.systems.FrontiersRandomTextList;
import net.minecraft.resources.ResourceLocation;
import net.vertisoft.vectorlib.VectorLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frontiers
{
    public static final String MOD_ID = "frontiers";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static FrontiersConfig CONFIG = new FrontiersConfig();
    public static FrontiersEventSystem EVENTS;

    public static final FrontiersRandomTextList DEATH_MSG = new FrontiersRandomTextList("death messages");
    public static final FrontiersRandomTextList HARDCORE_MSG = new FrontiersRandomTextList("hardcore death messages");

    public static final boolean DOING_DATAGEN = VectorLib.PLATFORM.isDatagen();

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
        LOGGER.info(
                "\n   > Ready to explore new frontiers? No? Good! We're %$@#ing doing it anyway!!!! :3" +
                String.format("\n   > Frontiers (ver. %1s) - a mod by Artyrian", VectorLib.PLATFORM.getModVersion(MOD_ID)) +
                "\n   > With help from Yurjezich, EmeraldEiscue, Hecco, Yirmiri, Aridoitsu, and many more"
        );

        // Register config file.
        CONFIG = FrontiersConfig.load_config();

        // Poll events.
        EVENTS = new FrontiersEventSystem();

        // Check loaded integratable mods
        checkLoadedMods();

        // Register mod content.
        FRItems.registerModItems();						// Items
        FRBlocks.registerModBlocks();					// Blocks (+ respective items)
        FRBlockEntities.registerBlockEntities();		// Block Entities
        FREntity.registerModEntities();				    // Entities
        FRFeature.registerFeatures();					// Features
        FRStructures.registerStructures();				// Structure
        FRSounds.registerSounds();						// Sounds
        FRStatusEffects.registerEffects();				// Status FX
        FRPotions.registerPotions();					// Potions
        FRRecipes.registerRecipes();					// Custom recipe types
        FRAttributes.registerModAttributes();			// Entity Attributes
        FRBlockProperties.registerProperties();		    // Block Properties
        FRDamageType.registerDamages();				    // Dmg types
        FRParticles.registerParticles();				// Particles
        FRStats.registerStats();						// Stats
        FRMenus.registerScreens();			            // Screens
        FRRegistries.MapDecor.register();               // Map Decorations
        FRCriteria.registerCriterion();				    // Advancement Criteria
        FRLootTables.registerLootTables();				// Chest Loot Tables
        FRTrade.bootstrap();				            // Villager trades
        FRLootConditions.registerConds();				// Loot Conditions
        FRDataComponents.registerComps();				// Item Data Components
        FRLevelEvents.register();                       // VectorLib Level Events

        FRLootMods.Modify.bootstrap();                  // Loot Modifiers - Modify
        FRLootMods.Replace.bootstrap();                 // Loot Modifiers - Replace

        FRDatapack.bootstrap();                         // Datapacks (mod integration)

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

    public static ResourceLocation id(String string) { return Frontiers.id(MOD_ID, string); }
    public static ResourceLocation id(String id, String string) { return VectorLib.id(id, string); }
}