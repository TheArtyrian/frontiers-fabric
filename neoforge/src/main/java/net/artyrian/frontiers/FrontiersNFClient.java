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
import net.artyrian.frontiers.definition.particle.ColorExplodeParticle;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRMenus;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
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
        event.registerSpriteSet(FRParticles.WITHER_FACE.get(), WitherFaceParticle.Factory::new);
        event.registerSpriteSet(FRParticles.CRAG_SMOG.get(), CragSmogParticle.Factory::new);
        event.registerSpriteSet(FRParticles.VEX_FLAME.get(), FlameParticle.SmallFlameProvider::new);
        event.registerSpriteSet(FRParticles.VEX_FLAME_BIG.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(FRParticles.TOWER_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(FRParticles.TOWER_FLAME_SMALL.get(), FlameParticle.SmallFlameProvider::new);
        event.registerSpriteSet(FRParticles.WITHER_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(FRParticles.SNOW_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
        event.registerSpriteSet(FRParticles.COLOR_POOF.get(), ColorExplodeParticle.Builder::new);
    }

    @SubscribeEvent
    public static void registerMenus(RegisterMenuScreensEvent event)
    {
        event.register(FRMenus.CURSE_ALTAR.get(), CurseAltarScreen::new);
        event.register(FRMenus.FLETCHING_TABLE.get(), FletchingTableScreen::new);
        event.register(FRMenus.MONSTER_BAKERY.get(), MonsterBakeryScreen::new);
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
        event.registerEntityRenderer(FREntity.BALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(FREntity.FRUITCAKE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(FREntity.MANA_BOTTLE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(FREntity.GOLDEN_EGG.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(FREntity.BAIT.get(), ThrownItemRenderer::new);

        event.registerEntityRenderer(FREntity.WARP_ARROW.get(), WarpArrowEntityRenderer::new);
        event.registerEntityRenderer(FREntity.SUBZERO_ARROW.get(), SubzeroArrowEntityRenderer::new);
        event.registerEntityRenderer(FREntity.BOUNCY_ARROW.get(), BouncyArrowEntityRenderer::new);
        event.registerEntityRenderer(FREntity.DYNAMITE_ARROW.get(), DynamiteArrowEntityRenderer::new);
        event.registerEntityRenderer(FREntity.PRISMARINE_ARROW.get(), PrismarineArrowEntityRenderer::new);

        event.registerEntityRenderer(FREntity.PALE_TRIDENT.get(), PaleTridentEntityRenderer::new);

        event.registerEntityRenderer(FREntity.CRAWLER.get(), CrawlerEntityRenderer::new);
        event.registerEntityRenderer(FREntity.JUNGLE_SPIDER.get(), JungleSpiderEntityRenderer::new);
        event.registerEntityRenderer(FREntity.PUMPKIN_GOLEM.get(), PumpkinGolemEntityRenderer::new);
        event.registerEntityRenderer(FREntity.CROW.get(), CrowEntityRenderer::new);
        event.registerEntityRenderer(FREntity.GOLDEN_CHICKEN.get(), GoldenChickenEntityRenderer::new);

        event.registerEntityRenderer(FREntity.MANA_ORB.get(), ManaOrbEntityRenderer::new);
        event.registerEntityRenderer(FREntity.CRAGS_STALKER.get(), CragsStalkerEntityRenderer::new);
        event.registerEntityRenderer(FREntity.CRAGS_MONSTER.get(), CragsMonsterEntityRenderer::new);
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
        event.registerBlockEntityRenderer(FRBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get(), ChestRenderer<PersonalChestBlockEntity>::new);

        event.registerBlockEntityRenderer(FRBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), CurseAltarBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.MONSTER_BAKERY_BLOCKENTITY.get(), MonsterBakeryBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.CRAGS_PORTAL_BLOCKENTITY.get(), CragsPortalBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.PHANTOM_BED_BLOCKENTITY.get(), PhantomBedBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY.get(), EnchantingMagnetBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.ITEM_VACUUM.get(), ItemVacuumBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.TOWER_SPAWNER.get(), TowerSpawnerBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(FRBlockEntities.CREEPER_MODEL_BLOCKENTITY.get(), CreeperModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.SKELETON_MODEL_BLOCKENTITY.get(), SkeletonModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.STRAY_MODEL_BLOCKENTITY.get(), StrayModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.BOGGED_MODEL_BLOCKENTITY.get(), BoggedModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.WITHER_SKELETON_MODEL_BLOCKENTITY.get(), WitherSkeletonModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.ENDERMAN_MODEL_BLOCKENTITY.get(), EndermanModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.SLIME_MODEL_BLOCKENTITY.get(), SlimeModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.MAGMA_CUBE_MODEL_BLOCKENTITY.get(), MagmaCubeModelBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(FRBlockEntities.PHANTOM_MODEL_BLOCKENTITY.get(), PhantomModelBlockEntityRenderer::new);
    }
}