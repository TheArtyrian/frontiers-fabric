package net.artyrian.frontiers;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.definition.block.entity.renderer.*;
import net.artyrian.frontiers.definition.entity.renderer.misc.CragsMonsterEntityRenderer;
import net.artyrian.frontiers.definition.entity.renderer.misc.CragsStalkerEntityRenderer;
import net.artyrian.frontiers.definition.entity.renderer.misc.ManaOrbEntityRenderer;
import net.artyrian.frontiers.definition.entity.renderer.mob.crawler.CrawlerEntityRenderer;
import net.artyrian.frontiers.definition.entity.renderer.mob.jungle_spider.JungleSpiderEntityRenderer;
import net.artyrian.frontiers.definition.entity.renderer.passive.*;
import net.artyrian.frontiers.definition.entity.renderer.projectile.*;
import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.definition.particle.ColorExplodeParticle;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.vertisoft.vectorlib.VectorLibFabricClient;

import java.util.List;

public class FrontiersFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        FrontiersClient.init();
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLibFabricClient.bootstrap();

        // Do color maps
        FrontiersClient.doTintsItem();
        FrontiersClient.doTintsBlock();

        doMenus();
        doParticleReg();
        doClientEventReg();

        doBlockEntityRender();
        doEntityRenderers();
        doEntityRenderLayers();

        addBlockTints();

        doS2CPackets();
    }

    public static void doEntityRenderLayers()
    {
        EntityModelLayerRegistry.registerModelLayer(FRRegistries.ModelLayers.CROW, CrowModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(FRRegistries.ModelLayers.PUMPKIN_GOLEM, PumpkinGolemModel::createBodyLayer);
    }

    public static void doEntityRenderers()
    {
        EntityRendererRegistry.register(ModEntity.BALL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntity.FRUITCAKE.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntity.MANA_BOTTLE.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntity.GOLDEN_EGG.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntity.BAIT.get(), ThrownItemRenderer::new);

        EntityRendererRegistry.register(ModEntity.WARP_ARROW.get(), WarpArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.SUBZERO_ARROW.get(), SubzeroArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.BOUNCY_ARROW.get(), BouncyArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.DYNAMITE_ARROW.get(), DynamiteArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.PRISMARINE_ARROW.get(), PrismarineArrowEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.PALE_TRIDENT.get(), PaleTridentEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.CRAWLER.get(), CrawlerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.JUNGLE_SPIDER.get(), JungleSpiderEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.PUMPKIN_GOLEM.get(), PumpkinGolemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CROW.get(), CrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.GOLDEN_CHICKEN.get(), GoldenChickenEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.MANA_ORB.get(), ManaOrbEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CRAGS_STALKER.get(), CragsStalkerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CRAGS_MONSTER.get(), CragsMonsterEntityRenderer::new);
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

        BlockEntityRenderers.register(ModBlockEntities.TOWER_SPAWNER.get(), TowerSpawnerBlockEntityRenderer::new);

        BlockEntityRenderers.register(ModBlockEntities.CREEPER_MODEL_BLOCKENTITY.get(), CreeperModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SKELETON_MODEL_BLOCKENTITY.get(), SkeletonModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.STRAY_MODEL_BLOCKENTITY.get(), StrayModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BOGGED_MODEL_BLOCKENTITY.get(), BoggedModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.WITHER_SKELETON_MODEL_BLOCKENTITY.get(), WitherSkeletonModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY.get(), EndermanModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.SLIME_MODEL_BLOCKENTITY.get(), SlimeModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.MAGMA_CUBE_MODEL_BLOCKENTITY.get(), MagmaCubeModelBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.PHANTOM_MODEL_BLOCKENTITY.get(), PhantomModelBlockEntityRenderer::new);
    }

    public static void doMenus()
    {
        MenuScreens.register(ModScreenHandlers.CURSE_ALTAR.get(), CurseAltarScreen::new);
        MenuScreens.register(ModScreenHandlers.FLETCHING_TABLE.get(), FletchingTableScreen::new);
        MenuScreens.register(ModScreenHandlers.MONSTER_BAKERY.get(), MonsterBakeryScreen::new);
    }

    private void addBlockTints()
    {
        for (Pair<BlockColor, List<Block>> pair : FrontiersClient.blockColors)
        {
            for (Block block : pair.getSecond())
            {
                ColorProviderRegistry.BLOCK.register(pair.getFirst(), block);
            }
        }

        for (Pair<ItemColor, List<ItemLike>> pair : FrontiersClient.itemColors)
        {
            for (ItemLike item : pair.getSecond())
            {
                ColorProviderRegistry.ITEM.register(pair.getFirst(), item);
            }
        }
    }

    public static void doParticleReg()
    {
        ParticleFactoryRegistry.getInstance().register(ModParticle.WITHER_FACE.get(), WitherFaceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.CRAG_SMOG.get(), CragSmogParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.VEX_FLAME.get(), FlameParticle.SmallFlameProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.VEX_FLAME_BIG.get(), FlameParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.TOWER_FLAME.get(), FlameParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.TOWER_FLAME_SMALL.get(), FlameParticle.SmallFlameProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.WITHER_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.SNOW_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.COLOR_POOF.get(), ColorExplodeParticle.Builder::new);
    }

    public static void doClientEventReg()
    {
        ClientLifecycleEvents.CLIENT_STARTED.register((phase) -> ClientEvents.registerDeathScreenMsg());
    }

    public static void doS2CPackets()
    {
        // Hardmode setter
        ClientPlayNetworking.registerGlobalReceiver(WitherHardmodePayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.witherHardmodeSet(payload, context.client())
        );

        // Avarice Totem
        ClientPlayNetworking.registerGlobalReceiver(PlayerAvariceTotemPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.avariceTotem(payload, context.player())
        );

        // Crags Monster Kill
        ClientPlayNetworking.registerGlobalReceiver(CragsMonsterKillPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.cragsMonsterKillPlayer(payload, context.player())
        );

        // Player Buffs
        ClientPlayNetworking.registerGlobalReceiver(BuffSyncPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.syncPlayerBuffs(payload, context.player())
        );

        // Sanity
        ClientPlayNetworking.registerGlobalReceiver(SanitySyncPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.sanitySync(payload, context.player())
        );

        // Mana
        ClientPlayNetworking.registerGlobalReceiver(ManaSyncPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.manaSync(payload, context.player())
        );

        // Chance-vary food item player sync
        ClientPlayNetworking.registerGlobalReceiver(ChanceFoodItemPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.chanceFoodItem(payload, context.player())
        );
    }
}
