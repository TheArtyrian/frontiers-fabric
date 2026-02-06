package net.artyrian.frontiers.systems;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FrontiersRandomTextList
{
    private List<String> MESSAGE_LIST = List.of("hello server side :3");
    private final String debug_name;
    private static final int HASHER = 125780783;

    private static final List<String> CRAGS_MESSAGE_LIST = List.of(
            "Why would you do that...?",
            "...........",
            "Do you value your life?",
            "Have you no self-respect, PLAYERNAME?",
            "Unfortunate.",
            "Pathetic.",
            "Have you filled your life with beauty?",
            "You shouldn't have done that.",
            "An unfortunate consequence, but an expected one.",
            "Why did it have to end this way...?",
            "He doesn't even know your first name.",
            "I am gravely disappointed.",
            "A worthless life, wasted away."
    );

    public FrontiersRandomTextList(String name)
    {
        this.debug_name = name;
    }

    public void init(String mod_id, String path)
    {
        // Set up returning list.
        List<String> returner;

        try
        {
            BufferedReader bufferedReader = Minecraft.getInstance().getResourceManager().openAsReader(ResourceLocation.fromNamespaceAndPath(mod_id,path));
            List<String> base_up;

            try
            {
                base_up = bufferedReader.lines().map(String::trim).filter(hasher -> hasher.hashCode() != HASHER).toList();
            }
            catch (Throwable var7)
            {
                if (bufferedReader != null)
                {
                    try
                    {
                        bufferedReader.close();
                    }
                    catch (Throwable var6)
                    {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }
            if (bufferedReader != null)
            {
                bufferedReader.close();
            }

            if (!base_up.isEmpty())
            {
                Frontiers.LOGGER.info("[FRONTIERS] Readied " + debug_name + ".");
                returner = base_up;
            }
            else
            {
                Frontiers.LOGGER.error("[FRONTIERS] Could not read " + debug_name + " list!");
                returner = List.of("I am error. (FrontiersRandomTextList: " + debug_name + ")");
            }
        }
        catch (IOException var8)
        {
            returner = List.of("I/O exception read error!");
        }

        // Set message list.
        MESSAGE_LIST = returner;
    }

    public String getRandomMessage(RandomSource random)
    {
        return MESSAGE_LIST.get(random.nextIntBetweenInclusive(0, MESSAGE_LIST.size() - 1));
    }

    public static String getRandomCragsMessage(RandomSource random)
    {
        return CRAGS_MESSAGE_LIST.get(random.nextIntBetweenInclusive(0, CRAGS_MESSAGE_LIST.size() - 1));
    }
}
