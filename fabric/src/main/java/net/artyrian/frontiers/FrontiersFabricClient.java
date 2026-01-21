package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.definition.block.entity.renderer.*;
import net.artyrian.frontiers.definition.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.definition.entity.renderer.passive.CrowModel;
import net.artyrian.frontiers.definition.entity.renderer.passive.PumpkinGolemModel;
import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;

public class FrontiersFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        FrontiersClient.init();

        doMenus();
        doParticleReg();
        doClientEventReg();

        doBlockEntityRender();
        doEntityRenderLayers();
    }

    public static void doEntityRenderLayers()
    {
        EntityModelLayerRegistry.registerModelLayer(FRRegistries.ModelLayers.CROW, CrowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FRRegistries.ModelLayers.PUMPKIN_GOLEM, PumpkinGolemModel::createBodyLayer);
    }

    public static void doBlockEntityRender()
    {
        BlockEntityRenderers.register(ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get(), ChestRenderer<PersonalChestBlockEntity>::new);

        BlockEntityRenderers.register(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), CurseAltarBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY.get(), MonsterBakeryBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.CRAGS_PORTAL_BLOCKENTITY.get(), CragsPortalBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.PHANTOM_BED_BLOCKENTITY.get(), PhantomBedBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY.get(), EnchantingMagnetBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.ITEM_VACUUM.get(), ItemVacuumBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.CREEPER_MODEL_BLOCKENTITY.get(), CreeperModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SKELETON_MODEL_BLOCKENTITY.get(), SkeletonModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.STRAY_MODEL_BLOCKENTITY.get(), StrayModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BOGGED_MODEL_BLOCKENTITY.get(), BoggedModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.WITHER_SKELETON_MODEL_BLOCKENTITY.get(), WitherSkeletonModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY.get(), EndermanModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SLIME_MODEL_BLOCKENTITY.get(), SlimeModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.MAGMA_CUBE_MODEL_BLOCKENTITY.get(), MagmaCubeModelBlockEntityRenderer::new);
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
