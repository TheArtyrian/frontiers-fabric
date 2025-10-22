package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.FoliageColors;

public class BFClientReg
{
    public static void run()
    {
        renderLayers();
        coloring();
    }

    private static void renderLayers()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.APPLE_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.ORANGE_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.LEMON_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.PLUM_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.GOLDEN_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.WALNUT_WREATH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.HOARY_WREATH, RenderLayer.getCutout());
    }

    private static void coloring()
    {
        // Foliage Blocks
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getFoliageColor(world, pos)
                        : FoliageColors.getDefaultColor(),
                BFBlock.APPLE_WREATH,
                BFBlock.LEMON_WREATH,
                BFBlock.ORANGE_WREATH,
                BFBlock.PLUM_WREATH,
                BFBlock.WALNUT_WREATH
        );

        // Foliage Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(FoliageColors.getDefaultColor()),
                BFBlock.APPLE_WREATH,
                BFBlock.LEMON_WREATH,
                BFBlock.ORANGE_WREATH,
                BFBlock.PLUM_WREATH,
                BFBlock.WALNUT_WREATH
        );
    }
}
