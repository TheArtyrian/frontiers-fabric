package net.artyrian.frontiers.event;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public class ClientInitEventReg
{
    public static void doReg()
    {
        // Death message registry.
        ClientLifecycleEvents.CLIENT_STARTED.register((phase) ->
        {
            Frontiers.DEATH_MSG.init(Frontiers.MOD_ID, "texts/death_messages.txt");
            Frontiers.HARDCORE_MSG.init(Frontiers.MOD_ID, "texts/death_messages_hardcore.txt");
        });
    }
}
