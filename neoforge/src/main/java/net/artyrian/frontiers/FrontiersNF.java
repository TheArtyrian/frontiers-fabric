package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.FRIntegReg;
import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.exclusive.loot_mods.FRLootMods;
import net.artyrian.frontiers.exclusive.networking.NetworkingNF;
import net.artyrian.frontiers.exclusive.poi.PoiNF;
import net.artyrian.frontiers.exclusive.world.EntitySpawnsNF;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDispenserActions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.VectorLibNF;
import net.vertisoft.vectorlib.platform.VectorRegNF;

@Mod(Frontiers.MOD_ID)
public class FrontiersNF
{
    public FrontiersNF(IEventBus eventBus)
    {
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        ((VectorRegNF)VectorLib.REGISTRY).setEventBus(ModLoadingContext.get().getActiveContainer().getEventBus());

        Frontiers.init();
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLib.bootstrap();
        VectorLibNF.bootstrap(eventBus);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::payloadSetup);
        eventBus.addListener(this::modifySpawnPlacements);

        FRLootMods.REGISTRY.register(eventBus);
        PoiNF.doHookup(eventBus);
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
        ModItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        FRRegistries.FurnaceFuels.register();
        FRRegistries.Flammable.register();
        FRRegistries.Compostable.register();
        FRRegistries.MobAttributes.register();
        ModDispenserActions.execute();

        // Thanks, NeoForge! :D
        FRRegistries.NoteBlockInst.FRONTIERS_LOG_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_LOG_DRUM.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_ICE_BELL.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ICE_BELL.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_HARPSICHORD.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_STEEL_DRUM.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_ROBOLUNG.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ROBOLUNG.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_JESKOLA.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_JESKOLA.getDelegate();

        // DO LAST FOR NO REASON :D
        FRIntegReg.integPost();
    }

    @SubscribeEvent
    public void payloadSetup(final RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar reg = event.registrar("V1").executesOn(HandlerThread.NETWORK);

        if (true)
        {
            // SERVER
            NetworkingNF.ToServer.register(reg);

            // CLIENT
            NetworkingNF.ToClient.register(reg);

            // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
            VectorLibNF.payloadSetup(reg);
        }
    }

    @SubscribeEvent
    public void modifySpawnPlacements(RegisterSpawnPlacementsEvent event)
    {
        EntitySpawnsNF.reg(event);
    }
}