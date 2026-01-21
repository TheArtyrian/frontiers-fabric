package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;

public class FrontiersFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        FrontiersClient.init();

        doMenus();
        doParticleReg();
        doClientEventReg();
    }

    public static void doMenus()
    {
        MenuScreens.register(ModScreenHandlers.CURSE_ALTAR.get(), CurseAltarScreen::new);
        MenuScreens.register(ModScreenHandlers.FLETCHING_TABLE.get(), FletchingTableScreen::new);
        MenuScreens.register(ModScreenHandlers.MONSTER_BAKERY.get(), MonsterBakeryScreen::new);
    }

    public static void doParticleReg()
    {
        ParticleFactoryRegistry.getInstance().register(ModParticle.WITHER_FACE.get(), WitherFaceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.CRAG_SMOG.get(), CragSmogParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.VEX_FLAME.get(), FlameParticle.SmallFlameProvider::new);
    }

    public static void doClientEventReg()
    {
        ClientLifecycleEvents.CLIENT_STARTED.register((phase) -> ClientEvents.registerDeathScreenMsg());
    }
}
