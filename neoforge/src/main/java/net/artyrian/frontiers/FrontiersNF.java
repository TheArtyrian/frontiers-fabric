package net.artyrian.frontiers;

import net.artyrian.frontiers.reg.content.ModItemTabs;
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
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event)
    {
        ModItemTabs.registerModItemTabs();
    }

    @SubscribeEvent
    public void payloadSetup(final RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar reg = event.registrar("V1");
    }
}