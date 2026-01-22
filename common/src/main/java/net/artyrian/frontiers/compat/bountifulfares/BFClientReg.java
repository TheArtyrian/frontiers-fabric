package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.FoliageColor;

public class BFClientReg
{
    public static void run()
    {
        renderLayers();
        coloring();
    }

    private static void renderLayers()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.APPLE_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.ORANGE_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.LEMON_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.PLUM_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.GOLDEN_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.WALNUT_WREATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BFBlock.HOARY_WREATH, RenderType.cutout());
    }

    private static void coloring()
    {
        // Foliage Blocks
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : FoliageColor.getDefaultColor(),
                BFBlock.APPLE_WREATH,
                BFBlock.LEMON_WREATH,
                BFBlock.ORANGE_WREATH,
                BFBlock.PLUM_WREATH,
                BFBlock.WALNUT_WREATH
        );

        // Foliage Items
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getDefaultColor()),
                BFBlock.APPLE_WREATH,
                BFBlock.LEMON_WREATH,
                BFBlock.ORANGE_WREATH,
                BFBlock.PLUM_WREATH,
                BFBlock.WALNUT_WREATH
        );
    }
}
