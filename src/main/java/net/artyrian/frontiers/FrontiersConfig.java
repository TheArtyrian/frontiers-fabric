package net.artyrian.frontiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;

public class FrontiersConfig
{
    private static final File CONFIG = new File(Frontiers.LOADER.getConfigDir().toFile(), "frontiers.json");

    // All configs in the game
    private boolean evokerRebalance = true;         // Totem of Undying Rebalance (if you false this you're mean >:{)
    private boolean altEnderChestTex = true;        // Alternate Ender Chest texture to match Void Pearl

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
            Frontiers.LOGGER.error("Could not load/read Frontiers config file! Resorting to default configs - please check for config file's existence and try again.");
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
            Frontiers.LOGGER.error("A critical error occured while saving the Frontiers config file. Please make sure the file isn't damaged and try again.");
        }
    }

    public boolean doEvokerRebalance()
    {
        return evokerRebalance;
    }
}
