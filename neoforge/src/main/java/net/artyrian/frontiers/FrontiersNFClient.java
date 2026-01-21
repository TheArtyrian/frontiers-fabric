package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = Frontiers.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class FrontiersNFClient
{
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticle.WITHER_FACE.get(), WitherFaceParticle.Factory::new);
        event.registerSpriteSet(ModParticle.CRAG_SMOG.get(), CragSmogParticle.Factory::new);
        event.registerSpriteSet(ModParticle.VEX_FLAME.get(), FlameParticle.SmallFlameProvider::new);
    }

    @SubscribeEvent
    public static void registerMenus(RegisterMenuScreensEvent event)
    {
        event.register(ModScreenHandlers.CURSE_ALTAR.get(), CurseAltarScreen::new);
        event.register(ModScreenHandlers.FLETCHING_TABLE.get(), FletchingTableScreen::new);
        event.register(ModScreenHandlers.MONSTER_BAKERY.get(), MonsterBakeryScreen::new);
    }
}