package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.definition.block.entity.renderer.*;
import net.artyrian.frontiers.definition.entity.renderer.passive.CrowModel;
import net.artyrian.frontiers.definition.entity.renderer.passive.PumpkinGolemModel;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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

    @SubscribeEvent
    public static void registerRenderLayerDef(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(FRRegistries.ModelLayers.CROW, CrowModel::createBodyLayer);
        event.registerLayerDefinition(FRRegistries.ModelLayers.PUMPKIN_GOLEM, PumpkinGolemModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get(), ChestRenderer<PersonalChestBlockEntity>::new);

        event.registerBlockEntityRenderer(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), CurseAltarBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY.get(), MonsterBakeryBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.CRAGS_PORTAL_BLOCKENTITY.get(), CragsPortalBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.PHANTOM_BED_BLOCKENTITY.get(), PhantomBedBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY.get(), EnchantingMagnetBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ITEM_VACUUM.get(), ItemVacuumBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(ModBlockEntities.CREEPER_MODEL_BLOCKENTITY.get(), CreeperModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SKELETON_MODEL_BLOCKENTITY.get(), SkeletonModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.STRAY_MODEL_BLOCKENTITY.get(), StrayModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BOGGED_MODEL_BLOCKENTITY.get(), BoggedModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.WITHER_SKELETON_MODEL_BLOCKENTITY.get(), WitherSkeletonModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY.get(), EndermanModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.SLIME_MODEL_BLOCKENTITY.get(), SlimeModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MAGMA_CUBE_MODEL_BLOCKENTITY.get(), MagmaCubeModelBlockEntityRenderer::new);
    }
}