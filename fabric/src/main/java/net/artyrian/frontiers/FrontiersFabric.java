package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.networking.payload.BottleMessageWritePayload;
import net.artyrian.frontiers.exclusive.loot.FabricLootModify;
import net.artyrian.frontiers.exclusive.loot.FabricLootReplace;
import net.artyrian.frontiers.exclusive.world.FabricWorldGen;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDispenserActions;
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

        // Misc.
        ModItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        FRRegistries.FurnaceFuels.register();
        FRRegistries.Flammable.register();
        FRRegistries.Compostable.register();
        ModDispenserActions.execute();

        // Packets
        regC2SPackets();

        // Modifiers
        FabricLootModify.modify();						// Mods some loot tables
        FabricLootReplace.replace();					// Replaces some loot tables
        FabricWorldGen.generate();		                // World Gen
    }

    private void regC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(BottleMessageWritePayload.ID, ((payload, context) ->
                ModNetworkConstants.Client.bottleMessageWrite(payload, context.player()))
        );
    }
}
