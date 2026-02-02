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
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModScreenHandlers;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import java.util.List;

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
        event.registerEntityRenderer(ModEntity.BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntity.FRUITCAKE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntity.MANA_BOTTLE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntity.GOLDEN_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntity.BAIT.get(), ThrownItemRenderer::new);

        event.registerEntityRenderer(ModEntity.WARP_ARROW.get(), WarpArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.SUBZERO_ARROW.get(), SubzeroArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.BOUNCY_ARROW.get(), BouncyArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.DYNAMITE_ARROW.get(), DynamiteArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.PRISMARINE_ARROW.get(), PrismarineArrowEntityRenderer::new);

        event.registerEntityRenderer(ModEntity.PALE_TRIDENT.get(), PaleTridentEntityRenderer::new);

        event.registerEntityRenderer(ModEntity.CRAWLER.get(), CrawlerEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.JUNGLE_SPIDER.get(), JungleSpiderEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.PUMPKIN_GOLEM.get(), PumpkinGolemEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.CROW.get(), CrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.GOLDEN_CHICKEN.get(), GoldenChickenEntityRenderer::new);

        event.registerEntityRenderer(ModEntity.MANA_ORB.get(), ManaOrbEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.CRAGS_STALKER.get(), CragsStalkerEntityRenderer::new);
        event.registerEntityRenderer(ModEntity.CRAGS_MONSTER.get(), CragsMonsterEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event)
    {
        FrontiersClient.doTintsBlock();

        for (Pair<BlockColor, List<Block>> pair : FrontiersClient.blockColors)
        {
            for (Block block : pair.getSecond())
            {
                event.register(pair.getFirst(), block);
            }
        }
    }

    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event)
    {
        FrontiersClient.doTintsItem();

        for (Pair<ItemColor, List<ItemLike>> pair : FrontiersClient.itemColors)
        {
            for (ItemLike item : pair.getSecond())
            {
                event.register(pair.getFirst(), item);
            }
        }
    }

    @SubscribeEvent
    public static void registerBlockEntityRender(EntityRenderersEvent.RegisterRenderers event)
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