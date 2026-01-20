package net.artyrian.frontiers;

import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FrontiersClient
{
    public static final Map<Supplier<Block>, RenderType> RENDER_LAYER_MAP = new HashMap<>();

    public static void init()
    {

    }

    private static void renderMaps()
    {
        RENDER_LAYER_MAP.put(ModBlocks.ANCIENT_ROSE_CROP, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ANCIENT_ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.VIOLET_ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_ANCIENT_ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_VIOLET_ROSE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ANCIENT_ROSE_BUSH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.VIOLET_ROSE_BUSH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BLIGHTED_BIRCH_SAPLING, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.SNOW_DAHLIA, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_SNOW_DAHLIA, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.FUNGAL_DAFFODIL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_FUNGAL_DAFFODIL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.CRIMCONE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_CRIMCONE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.EXPERIWINKLE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.POTTED_EXPERIWINKLE, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.EXPERIWINKLE_CROP, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.WARPED_WART, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.OAK_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.DARK_OAK_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BIRCH_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.SPRUCE_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.JUNGLE_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ACACIA_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.MANGROVE_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.AZALEA_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.CHERRY_WREATH, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BLIGHTED_BIRCH_WREATH, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.EBONCORK_DOOR, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.EBONCORK_TRAPDOOR, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BLIGHTED_BIRCH_DOOR, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.MONSTER_BAKERY, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ITEM_VACUUM, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.PHANTOM_STITCH_BED, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.SLIME_BULB, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.CORRUPTED_AMETHYST_CLUSTER, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.CREEPER_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.SKELETON_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.STRAY_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BOGGED_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.BLAZE_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.WITHER_SKELETON_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.ENDERMAN_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.SLIME_MODEL, RenderType.cutout());
        RENDER_LAYER_MAP.put(ModBlocks.MAGMA_CUBE_MODEL, RenderType.cutout());

        RENDER_LAYER_MAP.put(ModBlocks.SEA_GLASS, RenderType.translucent());
        RENDER_LAYER_MAP.put(ModBlocks.SEA_GLASS_PANE, RenderType.translucent());
        RENDER_LAYER_MAP.put(ModBlocks.PALE_SEA_GLASS, RenderType.translucent());
        RENDER_LAYER_MAP.put(ModBlocks.PALE_SEA_GLASS_PANE, RenderType.translucent());
        RENDER_LAYER_MAP.put(ModBlocks.SLIME_TRAIL, RenderType.translucent());

        RENDER_LAYER_MAP.put(ModBlocks.ENCHANTING_MAGNET, RenderType.cutoutMipped());
        RENDER_LAYER_MAP.put(ModBlocks.BLIGHTED_BIRCH_LEAVES, RenderType.cutoutMipped());
        RENDER_LAYER_MAP.put(ModBlocks.COBALT_GRILLES, RenderType.cutoutMipped());
    }
}
