package net.artyrian.frontiers;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.data.payloads.OreWitherPayload;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.misc.ModPredicate;
import net.artyrian.frontiers.particle.WitherFaceParticle;
import net.artyrian.frontiers.sounds.ModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

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

        // Do client receivers
        registerAllReceivers();

        // Do mipmaps
        addToBlockRenderMaps();
    }

    // Receivers
    private void registerAllReceivers()
    {
        ClientPlayNetworking.registerGlobalReceiver(WitherHardmodePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (payload.bool()) Frontiers.LOGGER.info("[Frontiers] Hardmode has been successfully set.");
            });
        });

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

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOW_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SNOW_DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FUNGAL_DAFFODIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FUNGAL_DAFFODIL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMCONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CRIMCONE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WARPED_WART, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORRUPTED_AMETHYST_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD, RenderLayer.getCutout());
    }
}
