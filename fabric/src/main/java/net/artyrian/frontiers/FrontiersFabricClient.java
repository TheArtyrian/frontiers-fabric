package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.definition.block.entity.renderer.*;
import net.artyrian.frontiers.definition.entity.renderer.passive.CrowModel;
import net.artyrian.frontiers.definition.entity.renderer.passive.PumpkinGolemModel;
import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreen;
import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreen;
import net.artyrian.frontiers.definition.menu.monster_bakery.MonsterBakeryScreen;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.definition.particle.CragSmogParticle;
import net.artyrian.frontiers.definition.particle.WitherFaceParticle;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModBlocks;
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
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.FoliageColor;

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

        addBlockTints();

        doS2CPackets();
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

    private void addBlockTints()
    {
        // Foliage Blocks
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor(),
                ModBlocks.OAK_WREATH.get(),
                ModBlocks.DARK_OAK_WREATH.get(),
                ModBlocks.JUNGLE_WREATH.get(),
                ModBlocks.ACACIA_WREATH.get(),
                ModBlocks.MANGROVE_WREATH.get()
        );

        // Foliage Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getDefaultColor()),
                ModBlocks.OAK_WREATH.get(),
                ModBlocks.DARK_OAK_WREATH.get(),
                ModBlocks.JUNGLE_WREATH.get(),
                ModBlocks.ACACIA_WREATH.get()
        );

        // Birch
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                ModBlocks.BIRCH_WREATH.get()
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                ModBlocks.BIRCH_WREATH.get()
        );

        // Spruce
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                ModBlocks.SPRUCE_WREATH.get()
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                ModBlocks.SPRUCE_WREATH.get()
        );

        // Mangrove (ITEM ONLY)
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getMangroveColor()),
                ModBlocks.MANGROVE_WREATH.get()
        );

        if (Frontiers.BOUNTIFUL_FARES_LOADED)
        {
            ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null
                            ? BiomeColors.getAverageFoliageColor(world, pos)
                            : FoliageColor.getDefaultColor(),
                    BFBlock.APPLE_WREATH.get(),
                    BFBlock.LEMON_WREATH.get(),
                    BFBlock.ORANGE_WREATH.get(),
                    BFBlock.PLUM_WREATH.get(),
                    BFBlock.WALNUT_WREATH.get()
            );

            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getDefaultColor()),
                    BFBlock.APPLE_WREATH.get(),
                    BFBlock.LEMON_WREATH.get(),
                    BFBlock.ORANGE_WREATH.get(),
                    BFBlock.PLUM_WREATH.get(),
                    BFBlock.WALNUT_WREATH.get()
            );
        }
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

    public static void doS2CPackets()
    {
        // Hardmode setter
        ClientPlayNetworking.registerGlobalReceiver(WitherHardmodePayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.witherHardmodeSet(payload, context.client())
        );

        // Ore Wither
        ClientPlayNetworking.registerGlobalReceiver(OreWitherPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.witherOre(payload, context.player().level())
        );

        // Avarice Totem
        ClientPlayNetworking.registerGlobalReceiver(PlayerAvariceTotemPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.avariceTotem(payload, context.player())
        );

        // Sanity
        ClientPlayNetworking.registerGlobalReceiver(SanitySyncPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.sanitySync(payload, context.player())
        );

        // Crags Monster Kill
        ClientPlayNetworking.registerGlobalReceiver(CragsMonsterKillPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.cragsMonsterKillPlayer(payload, context.player())
        );

        // Despawn stalker sync
        ClientPlayNetworking.registerGlobalReceiver(CragsStalkerDespawnPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.despawnCragsStalker(payload, context.player().level())
        );

        // Chance-vary food item player sync
        ClientPlayNetworking.registerGlobalReceiver(ChanceFoodItemPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.chanceFoodItem(payload, context.player())
        );

        // Item Vacuum Empty Stack
        ClientPlayNetworking.registerGlobalReceiver(ItemVacuumEmptyPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.emptyItemVacuum(payload, context.player().level())
        );

        // Item Vacuum Sync Stack
        ClientPlayNetworking.registerGlobalReceiver(ItemVacuumStackSyncPayload.ID, (payload, context) ->
                ModNetworkConstants.ToClient.syncItemVacuumStack(payload, context.player().level())
        );
    }
}
