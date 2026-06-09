package net.artyrian.frontiers;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.reg.client.FREventsClient;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.VectorLib;

import java.util.ArrayList;
import java.util.List;

public class FrontiersClient
{
    public static final List<Pair<BlockColor, List<Block>>> blockColors = new ArrayList<>();
    public static final List<Pair<ItemColor, List<ItemLike>>> itemColors = new ArrayList<>();

    public static void init()
    {
        FREventsClient.bootstrap();                 // EventSync (client-side)

        // Do render layers
        renderMaps();
    }

    private static void renderMaps()
    {
        VectorLib.client().setRenderLayer(Blocks.END_PORTAL_FRAME, RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.ANCIENT_ROSE_CROP.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ANCIENT_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.VIOLET_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_ANCIENT_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_VIOLET_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ANCIENT_ROSE_BUSH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.VIOLET_ROSE_BUSH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BLIGHTED_BIRCH_SAPLING.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.SNOW_DAHLIA.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_SNOW_DAHLIA.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.FUNGAL_DAFFODIL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_FUNGAL_DAFFODIL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.CRIMCONE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_CRIMCONE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.EXPERIWINKLE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.POTTED_EXPERIWINKLE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.EXPERIWINKLE_CROP.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.WARPED_WART.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.OAK_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.DARK_OAK_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BIRCH_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.SPRUCE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.JUNGLE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ACACIA_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.MANGROVE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.AZALEA_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.CHERRY_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BLIGHTED_BIRCH_WREATH.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.EBONCORK_DOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.EBONCORK_TRAPDOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BLIGHTED_BIRCH_DOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.MONSTER_BAKERY.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.TOWER_SPAWNER.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.TOWER_HEART.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.TOWER_TREASURE_VAULT.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.TOWER_KEY_VAULT.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ITEM_VACUUM.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.PHANTOM_STITCH_BED.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.SLIME_BULB.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.CREEPER_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.SKELETON_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.STRAY_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BOGGED_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.BLAZE_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.WITHER_SKELETON_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.ENDERMAN_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.SLIME_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(FRBlocks.MAGMA_CUBE_MODEL.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(FRBlocks.SEA_GLASS.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(FRBlocks.SEA_GLASS_PANE.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(FRBlocks.PALE_SEA_GLASS.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(FRBlocks.PALE_SEA_GLASS_PANE.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(FRBlocks.SLIME_TRAIL.get(), RenderType.translucent());

        VectorLib.client().setRenderLayer(FRBlocks.ENCHANTING_MAGNET.get(), RenderType.cutoutMipped());
        VectorLib.client().setRenderLayer(FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), RenderType.cutoutMipped());
        VectorLib.client().setRenderLayer(FRBlocks.COBALT_GRILLES.get(), RenderType.cutoutMipped());

        if (Frontiers.BOUNTIFUL_FARES_LOADED)
        {
            VectorLib.client().setRenderLayer(BFBlock.APPLE_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.ORANGE_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.LEMON_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.PLUM_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.GOLDEN_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.WALNUT_WREATH.get(), RenderType.cutout());
            VectorLib.client().setRenderLayer(BFBlock.HOARY_WREATH.get(), RenderType.cutout());
        }
    }

    public static void doTintsBlock()
    {
        // Foliage Blocks
        blockColors.add(new Pair<>((state, world, pos, tintIndex) -> world != null && pos != null
                ? BiomeColors.getAverageFoliageColor(world, pos)
                : FoliageColor.getDefaultColor(),
                List.of(
                        FRBlocks.OAK_WREATH.get(),
                        FRBlocks.DARK_OAK_WREATH.get(),
                        FRBlocks.JUNGLE_WREATH.get(),
                        FRBlocks.ACACIA_WREATH.get(),
                        FRBlocks.MANGROVE_WREATH.get()
                )
        ));

        // Birch
        blockColors.add(new Pair<>((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                List.of(
                        FRBlocks.BIRCH_WREATH.get()
                )
        ));

        // Spruce
        blockColors.add(new Pair<>((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                List.of(
                        FRBlocks.SPRUCE_WREATH.get()
                )
        ));

        if (Frontiers.BOUNTIFUL_FARES_LOADED)
        {
            blockColors.add(new Pair<>((state, world, pos, tintIndex) -> world != null && pos != null
                    ? BiomeColors.getAverageFoliageColor(world, pos)
                    : FoliageColor.getDefaultColor(),
                    List.of(
                            BFBlock.APPLE_WREATH.get(),
                            BFBlock.LEMON_WREATH.get(),
                            BFBlock.ORANGE_WREATH.get(),
                            BFBlock.PLUM_WREATH.get(),
                            BFBlock.WALNUT_WREATH.get()
                    )
            ));
        }
    }

    public static void doTintsItem()
    {
        // Foliage Items
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getDefaultColor()),
                List.of(
                        FRBlocks.OAK_WREATH.get(),
                        FRBlocks.DARK_OAK_WREATH.get(),
                        FRBlocks.JUNGLE_WREATH.get(),
                        FRBlocks.ACACIA_WREATH.get()
                )
        ));

        // Birch
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                List.of(
                        FRBlocks.BIRCH_WREATH.get()
                )
        ));

        // Spruce
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                List.of(
                        FRBlocks.SPRUCE_WREATH.get()
                )
        ));

        // Mangrove (ITEM ONLY)
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getMangroveColor()),
                List.of(
                        FRBlocks.MANGROVE_WREATH.get()
                )
        ));

        if (Frontiers.BOUNTIFUL_FARES_LOADED)
        {
            itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getDefaultColor()),
                    List.of(
                            BFBlock.APPLE_WREATH.get(),
                            BFBlock.LEMON_WREATH.get(),
                            BFBlock.ORANGE_WREATH.get(),
                            BFBlock.PLUM_WREATH.get(),
                            BFBlock.WALNUT_WREATH.get()
                    )
            ));
        }
    }
}
