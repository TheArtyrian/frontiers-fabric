package net.artyrian.frontiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.vertisoft.vectorlib.VectorLib;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;

public class FrontiersConfig
{
    private static final File CONFIG = new File(VectorLib.PLATFORM.getConfigDirectory().toFile(), "frontiers.json");

    private final Common common;
    private final Client client;

    public FrontiersConfig()
    {
        this.common = new Common();
        this.client = new Client();
    }

    public static FrontiersConfig load_config()
    {
        FrontiersConfig config = new FrontiersConfig();

        // Do save in case it doesnt exist
        if (!CONFIG.exists()) save_config(config);

        Reader reader;
        try
        {
            reader = Files.newBufferedReader(CONFIG.toPath());
            Gson xnl = new GsonBuilder().setPrettyPrinting().create();
            config = xnl.fromJson(reader, FrontiersConfig.class);
            reader.close();

            Frontiers.LOGGER.info("Frontiers config file loaded.");
        }
        catch (IOException error)
        {
            Frontiers.LOGGER.error("Could not load/read Frontiers config file! Resorting to default configs - please check for config file's existence and try again.", error);
        }

        return config;
    }

    public static void save_config(FrontiersConfig file)
    {
        try
        {
            Writer writer = Files.newBufferedWriter(CONFIG.toPath());
            Gson xni = new GsonBuilder().setPrettyPrinting().create();
            xni.toJson(file, writer);
            writer.close();

            Frontiers.LOGGER.info("Frontiers config file saved.");
        }
        catch (IOException error)
        {
            Frontiers.LOGGER.error("A critical error occured while saving the Frontiers config file. Please make sure the file isn't damaged and try again.", error);
        }
    }

    private static class Common
    {
        private boolean evokerRebalance = true;                 // Totem of Undying Rebalance (if you false this you're mean >:{)
        private boolean parrotDismountChange = true;            // Determines if the parrot dismounting height change is active.
        private boolean ocelotsAttackCreepers = true;           // When enabled, Ocelots hunt Creepers
        private boolean appledogCompatEnabled = true;           // Enables the joke compatibility with the Appledog mod. Configurable since it's destructive.
        private boolean netherFossilRework = true;              // Whether or not to replace Bone Blocks with Onyx Bones with nether fossils
        private boolean bastionRework = true;                   // Whether or not to replace Nether Wart with Warped Wart with bastions
        private boolean creativeTabs = true;                    // Determines if Frontiers-related creative mode tabs will generate
    }

    private static class Client
    {
        private boolean channeledLightningRecolor = true;       // Player-summoned lightning has a slightly more magical color
        private int creditsBackgroundType = 1;                  // Credits screen bg type: 0 = O.G (best :3), 1 = Frontiers exclusive, 2 = Vanilla
        private boolean deathScreenComment = true;              // Show a snarky comment on the death screen.
        private boolean uniqueInventoryBlur = false;            // Does a unique inventory blur based on Alpha's blue. False by default.
        private boolean recoloredFishingLine = true;            // Enables/disables recoloring fishing line, i.e cobalt rod
        private boolean use3DFishBobbers = true;                // Enables/disables 3D fishing bobbers
        private boolean manaBarAlwaysShows = false;             // Always shows the mana bar when true
        private boolean useBuffItemHearts = true;               // Whether or not to use special heart sprites for HP buffs

        private final SmallTouches smallTouches;

        private Client()
        {
            this.smallTouches = new SmallTouches();
        }

        private static class SmallTouches
        {
            private boolean masterEnable = true;                // If this is disabled, NONE of the FX here will play.
            private boolean snowMeltIndicationFX = true;        // Controls playing of Snow Melt indication FX on client
            private boolean brewingStandChargeFX = true;        // Controls playing of special Brewing Stand charge FX on client
            private boolean cursedTabletChargeFX = true;        // Controls playing of special Cursed Tablet charge FX on client
            private boolean furnaceLightingFX = true;           // Controls playing of special Furnace start FX on client
            private boolean monsterBakeryFX = true;             // Controls playing of special Monster Bakery start FX on client
        }
    }

    // Common
    public boolean doEvokerRebalance() { return this.common.evokerRebalance; }
    public boolean doParrotDismountChange() { return this.common.parrotDismountChange; }
    public boolean doOcelotsAttackCreepers() { return this.common.ocelotsAttackCreepers; }
    public boolean doAppledogCompat() { return this.common.appledogCompatEnabled; }
    public boolean doNetherFossilRework() { return this.common.netherFossilRework; }
    public boolean doBastionRework() { return this.common.bastionRework; }
    public boolean doCreativeModeTabs() { return this.common.creativeTabs; }
    // Client
    public int creditsType() { return this.client.creditsBackgroundType; }
    public boolean do3DFishBobbers() { return this.client.use3DFishBobbers; }
    public boolean doColoredFishLine() { return this.client.recoloredFishingLine; }
    public boolean doesManaBarAlwaysShow() { return this.client.manaBarAlwaysShows; }
    public boolean doBuffHearts() { return this.client.useBuffItemHearts; }
    public boolean doDeathScreenComment() { return this.client.deathScreenComment; }
    public boolean doUniqueInventoryBlur() { return this.client.uniqueInventoryBlur; }
    public boolean doChanneledLightningRecolor() { return this.client.channeledLightningRecolor; }
    // Small FX
    public boolean doBrewChargeFX() { return this.client.smallTouches.masterEnable && this.client.smallTouches.brewingStandChargeFX; }
    public boolean doCursedTabletFX() { return this.client.smallTouches.masterEnable && this.client.smallTouches.cursedTabletChargeFX; }
    public boolean doSnowMeltGlisten() { return this.client.smallTouches.masterEnable && this.client.smallTouches.snowMeltIndicationFX; }
    public boolean doFurnaceCrackle() { return this.client.smallTouches.masterEnable && this.client.smallTouches.furnaceLightingFX; }
    public boolean doMonsterBakeryFX() { return this.client.smallTouches.masterEnable && this.client.smallTouches.monsterBakeryFX; }
}
