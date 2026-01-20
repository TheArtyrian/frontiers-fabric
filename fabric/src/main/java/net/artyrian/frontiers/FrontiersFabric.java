package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.networking.payload.BottleMessageWritePayload;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.vertisoft.vectorlib.VectorLib;

public class FrontiersFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Frontiers.init();
        VectorLib.bootstrap();

        ModItemTabs.registerModItemTabs();

        registerC2SPacketSync();
    }

    private void registerC2SPacketSync()
    {
        ServerPlayNetworking.registerGlobalReceiver(BottleMessageWritePayload.ID, ((payload, context) ->
                ModNetworkConstants.Client.bottleMessageWrite(payload, context.player()))
        );
    }
}
