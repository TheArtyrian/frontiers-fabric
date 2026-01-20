package net.artyrian.frontiers;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Frontiers.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class FrontiersNFDatagen
{
    /** NOTE: Most of the generated asset files are managed inside of Fabric's datagen - this is for NF files ONLY! */
    @SubscribeEvent
    public static void packUpData(GatherDataEvent event)
    {

    }
}