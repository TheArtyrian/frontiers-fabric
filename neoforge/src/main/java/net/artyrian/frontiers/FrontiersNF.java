package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.platform.VectorClientNF;
import net.vertisoft.vectorlib.platform.VectorRegNF;

@Mod(Frontiers.MOD_ID)
public class FrontiersNF
{
    public FrontiersNF(IEventBus eventBus)
    {
        ((VectorRegNF)VectorLib.REGISTRY).setEventBus(eventBus);

        Frontiers.init();
        VectorLib.bootstrap();

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event)
    {
        FrontiersClient.init();

        ClientEvents.registerDeathScreenMsg();
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event)
    {
        // Thanks, NeoForge! :D
        FRRegistries.NoteBlockInst.FRONTIERS_LOG_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_LOG_DRUM;
        FRRegistries.NoteBlockInst.FRONTIERS_ICE_BELL.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ICE_BELL;
        FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_HARPSICHORD;
        FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_STEEL_DRUM;
        FRRegistries.NoteBlockInst.FRONTIERS_ROBOLUNG.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ROBOLUNG;
        FRRegistries.NoteBlockInst.FRONTIERS_JESKOLA.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_JESKOLA;

        ModItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        Frontiers.BAKER.bake();
    }

    @SubscribeEvent
    public void payloadSetup(final RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar reg = event.registrar("V1");
    }
}