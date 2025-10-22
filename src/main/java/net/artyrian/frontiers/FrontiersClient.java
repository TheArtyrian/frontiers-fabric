package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.entity.ItemVacuumBlockEntity;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.block.entity.renderer.*;
import net.artyrian.frontiers.client.screen.ModScreenHandlers;
import net.artyrian.frontiers.compat.bountifulfares.BFClientReg;
import net.artyrian.frontiers.data.payloads.*;
import net.artyrian.frontiers.data.player.PlayerPersistentNBT;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.misc.CragsStalkerEntity;
import net.artyrian.frontiers.entity.renderer.misc.CragsMonsterEntityRenderer;
import net.artyrian.frontiers.entity.renderer.misc.CragsStalkerEntityRenderer;
import net.artyrian.frontiers.entity.renderer.misc.ManaOrbEntityRenderer;
import net.artyrian.frontiers.entity.renderer.mob.crawler.CrawlerEntityRenderer;
import net.artyrian.frontiers.entity.renderer.mob.jungle_spider.JungleSpiderEntityRenderer;
import net.artyrian.frontiers.entity.renderer.passive.CrowEntityRenderer;
import net.artyrian.frontiers.entity.renderer.passive.CrowModel;
import net.artyrian.frontiers.entity.renderer.passive.PumpkinGolemEntityRenderer;
import net.artyrian.frontiers.entity.renderer.passive.PumpkinGolemModel;
import net.artyrian.frontiers.entity.renderer.projectile.*;
import net.artyrian.frontiers.event.ClientInitEventReg;
import net.artyrian.frontiers.misc.ModPredicate;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.artyrian.frontiers.particle.CragSmogParticle;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.particle.WitherFaceParticle;
import net.artyrian.frontiers.rendering.ModModelLayers;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.artyrian.frontiers.sounds.ModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.FoliageColors;

import java.util.UUID;

// API init lol
public class FrontiersClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        // Client-side only registers:
        ModPredicate.registerModPredicates();		// Item predicates.

        // Register particles.
        ParticleFactoryRegistry.getInstance().register(ModParticle.WITHER_FACE, WitherFaceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.CRAG_SMOG, CragSmogParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticle.VEX_FLAME, FlameParticle.SmallFactory::new);

        // Do client receivers
        registerAllReceivers();

        // Do mipmaps
        addToBlockRenderMaps();

        // Do biome tints
        addBlockTints();

        // Do entities + model layers
        addEntityModelLayers();
        addEntities();

        // Do block entities
        addBlockEntities();

        // Do screen handler
        ModScreenHandlers.registerClientScreens();

        // Do render layers.
        ModRenderLayers.reg();

        // Register client side events.
        ClientInitEventReg.doReg();

        // Register compatibility
        if (Frontiers.BOUNTIFUL_FARES_LOADED) BFClientReg.run();
    }

    // Receivers
    private void registerAllReceivers()
    {
        // Hardmode setter
        ClientPlayNetworking.registerGlobalReceiver(WitherHardmodePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (payload.bool()) Frontiers.LOGGER.info("[Frontiers] Hardmode has been successfully set.");
            });
        });

        // Ore Wither Payload
        ClientPlayNetworking.registerGlobalReceiver(OreWitherPayload.ID, (payload, context) -> {
            World world = context.player().getWorld();
            for (int i = 0; i < 12; i++)
            {
                world.addParticle(
                        ParticleTypes.SMOKE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
            }
            for (int i = 0; i < 8; i++)
            {
                world.addParticle(
                        ModParticle.BLACK_PARTICLE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
                world.addParticle(
                        ModParticle.WITHER_PARTICLE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
            }

            world.addParticle(
                    ModParticle.WITHER_FACE,
                    payload.pos().getX() + 0.5,
                    payload.pos().getY() + 0.5,
                    payload.pos().getZ() + 0.5,
                    0.0,
                    0.02,
                    0.0
            );

            world.playSoundAtBlockCenter(payload.pos(), ModSounds.ORE_WITHER, SoundCategory.BLOCKS, 0.8F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F),
                    false);
        });

        // Avarice Totem
        ClientPlayNetworking.registerGlobalReceiver(PlayerAvariceTotemPayload.ID, (payload, context) ->
        {
            ClientPlayerEntity player = context.player();
            boolean boolpayload = payload.bool();

            PlayerPersistentNBT.AvariceTotem.setTotemStatus(((PlayerMixInterface)player), boolpayload);

            //NbtCompound persistentData = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
            //if (persistentData != null && persistentData.contains("totem"))
            //{
            //    Frontiers.LOGGER.info("Player avarice check (S2C) -> " + String.valueOf(persistentData.getBoolean("totem")));
            //}
        });

        // Sanity sync
        ClientPlayNetworking.registerGlobalReceiver(SanitySyncPayload.ID, (payload, context) ->
        {
            UUID uuid = payload.player_id();
            int sanity = payload.sanity();
            int sanitytick = payload.sanitytick();

            PlayerEntity player = context.player().getWorld().getPlayerByUuid(uuid);

            if (player != null)
            {
                NbtCompound compound = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
                compound.putInt("sanity", sanity);
                compound.putInt("sanity_tick", sanitytick);
            }
            else
            {
                Frontiers.LOGGER.warn("[FRONTIERS]: Received sanity sync packet with an unknown player UUID of " + uuid.toString() + ", ignoring");
            }
        });

        // Crags monster death sync
        ClientPlayNetworking.registerGlobalReceiver(CragsMonsterKillPayload.ID, (payload, context) ->
        {
            ClientPlayerEntity player = context.player();

            NbtCompound compound = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
            compound.putBoolean("cragsmonster_kill", payload.bool());
        });

        // Despawn stalker sync
        ClientPlayNetworking.registerGlobalReceiver(CragsStalkerDespawnPayload.ID, (payload, context) -> {

            World world = context.player().getWorld();
            Random random = world.getRandom();

            Vec3d remappedpos = new Vec3d(payload.x(), payload.y(), payload.z());

            for (int i = 0; i < 20; i++)
            {
                world.addParticle(CragsStalkerEntity.SMOG,
                        remappedpos.getX() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        remappedpos.getY() + (0.1 * (1 + (random.nextInt(17)))),
                        remappedpos.getZ() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        (0.1 * random.nextBetween(-2, 2)), (0.1 * random.nextBetween(1, 3)), (0.1 * random.nextBetween(-2, 2)));

                world.addParticle(ModParticle.CRAG_SMOG,
                        remappedpos.getX() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        remappedpos.getY() + 1.0,
                        remappedpos.getZ() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        (0.1 * random.nextBetween(-2, 2)), (0.1 * random.nextBetween(1, 3)), (0.1 * random.nextBetween(-2, 2)));
            }
        });

        // Chance-vary food item player sync
        ClientPlayNetworking.registerGlobalReceiver(ChanceFoodItemPayload.ID, (payload, context) ->
        {
            ClientPlayerEntity player = context.player();
            ItemStack stack = payload.stack();

            stack.decrementUnlessCreative(1, player);
        });

        // Item Vacuum Empty
        ClientPlayNetworking.registerGlobalReceiver(ItemVacuumEmptyPayload.ID, (payload, context) -> {
            World world = context.player().getWorld();
            BlockPos pos = payload.pos();
            BlockEntity entityAt = world.getBlockEntity(pos);

            if (entityAt instanceof ItemVacuumBlockEntity vac)
            {
                vac.setStack(ItemStack.EMPTY);
                world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                world.updateComparators(pos, world.getBlockState(pos).getBlock());
            }
        });

        // Item Vacuum Stack Sync
        ClientPlayNetworking.registerGlobalReceiver(ItemVacuumStackSyncPayload.ID, (payload, context) -> {
            World world = context.player().getWorld();
            BlockPos pos = payload.pos();
            ItemStack stack = payload.stack();
            BlockEntity entityAt = world.getBlockEntity(pos);

            if (entityAt instanceof ItemVacuumBlockEntity vac)
            {
                vac.setStack(stack);
                world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                world.updateComparators(pos, world.getBlockState(pos).getBlock());
            }
        });
    }

    private void addBlockTints()
    {
        // Foliage Blocks
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefaultColor(),
                ModBlocks.OAK_WREATH,
                ModBlocks.DARK_OAK_WREATH,
                ModBlocks.JUNGLE_WREATH,
                ModBlocks.ACACIA_WREATH,
                ModBlocks.MANGROVE_WREATH
        );

        // Foliage Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getDefaultColor()),
                ModBlocks.OAK_WREATH,
                ModBlocks.DARK_OAK_WREATH,
                ModBlocks.JUNGLE_WREATH,
                ModBlocks.ACACIA_WREATH
        );

        // Birch
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getBirchColor()),
                ModBlocks.BIRCH_WREATH
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getBirchColor()),
                ModBlocks.BIRCH_WREATH
        );

        // Spruce
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getSpruceColor()),
                ModBlocks.SPRUCE_WREATH
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getSpruceColor()),
                ModBlocks.SPRUCE_WREATH
        );

        // Mangrove (ITEM ONLY)
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getMangroveColor()),
                ModBlocks.MANGROVE_WREATH
        );
    }

    // Add cutout mipmaps (help im going insane)
    private void addToBlockRenderMaps()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VIOLET_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ANCIENT_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_VIOLET_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_ROSE_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VIOLET_ROSE_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLIGHTED_BIRCH_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOW_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SNOW_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FUNGAL_DAFFODIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FUNGAL_DAFFODIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMCONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CRIMCONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPERIWINKLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_EXPERIWINKLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPERIWINKLE_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WARPED_WART, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OAK_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARK_OAK_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BIRCH_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPRUCE_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JUNGLE_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACACIA_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANGROVE_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHERRY_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLIGHTED_BIRCH_WREATH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBONCORK_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EBONCORK_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLIGHTED_BIRCH_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MONSTER_BAKERY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ITEM_VACUUM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PHANTOM_STITCH_BED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORRUPTED_AMETHYST_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SKELETON_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAY_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BOGGED_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLAZE_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WITHER_SKELETON_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENDERMAN_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLIME_MODEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAGMA_CUBE_MODEL, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEA_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_SEA_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PALE_SEA_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENCHANTING_MAGNET, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLIGHTED_BIRCH_LEAVES, RenderLayer.getCutoutMipped());
    }

    private void addBlockEntities()
    {
        BlockEntityRendererFactories.register(ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY, ChestBlockEntityRenderer<PersonalChestBlockEntity>::new);

        BlockEntityRendererFactories.register(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY, CurseAltarBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY, MonsterBakeryBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.CRAGS_PORTAL_BLOCKENTITY, CragsPortalBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.PHANTOM_BED_BLOCKENTITY, PhantomBedBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY, EnchantingMagnetBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ITEM_VACUUM, ItemVacuumBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.CREEPER_MODEL_BLOCKENTITY, CreeperModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SKELETON_MODEL_BLOCKENTITY, SkeletonModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.STRAY_MODEL_BLOCKENTITY, StrayModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.BOGGED_MODEL_BLOCKENTITY, BoggedModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.BLAZE_MODEL_BLOCKENTITY, BlazeModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WITHER_SKELETON_MODEL_BLOCKENTITY, WitherSkeletonModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY, EndermanModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SLIME_MODEL_BLOCKENTITY, SlimeModelBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MAGMA_CUBE_MODEL_BLOCKENTITY, MagmaCubeModelBlockEntityRenderer::new);
    }

    private void addItemRenderers()
    {

    }

    private void addEntityModelLayers()
    {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PUMPKIN_GOLEM, PumpkinGolemModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CROW, CrowModel::getTexturedModelData);
    }

    private void addEntities()
    {
        EntityRendererRegistry.register(ModEntity.BALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.FRUITCAKE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.MANA_BOTTLE, FlyingItemEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.WARP_ARROW, WarpArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.SUBZERO_ARROW, SubzeroArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.BOUNCY_ARROW, BouncyArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.DYNAMITE_ARROW, DynamiteArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.PRISMARINE_ARROW, PrismarineArrowEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.PALE_TRIDENT, PaleTridentEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.CRAWLER, CrawlerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.JUNGLE_SPIDER, JungleSpiderEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.PUMPKIN_GOLEM, PumpkinGolemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CROW, CrowEntityRenderer::new);

        EntityRendererRegistry.register(ModEntity.MANA_ORB, ManaOrbEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CRAGS_STALKER, CragsStalkerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntity.CRAGS_MONSTER, CragsMonsterEntityRenderer::new);
    }
}
