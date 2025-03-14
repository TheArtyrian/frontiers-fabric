package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FrontiersRandomTextList
{
    private List<String> MESSAGE_LIST = List.of("hello client side :3");

    public void init(String mod_id, String path)
    {
        // Set up returning list.
        List<String> returner;

        try
        {
            BufferedReader bufferedReader = MinecraftClient.getInstance().getResourceManager().openAsReader(Identifier.of(mod_id,path));

            List<String> base_up;
            try
            {
                base_up = (List<String>)bufferedReader.lines().map(String::trim).filter(hasher -> hasher.hashCode() != 125780783).toList();
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
                Frontiers.LOGGER.info("[FRONTIERS] Readied death messages.");
                returner = base_up;
            }
            else
            {
                Frontiers.LOGGER.error("[FRONTIERS] Could not read death message list!");
                returner = List.of("I am error.");
            }
        }
        catch (IOException var8)
        {
            returner = List.of("I/O exception read error!");
        }

        // Set message list.
        MESSAGE_LIST = returner;
    }

    public String getRandomMessage(Random random)
    {
        return MESSAGE_LIST.get(random.nextBetween(0, MESSAGE_LIST.size() - 1));
    }
}
