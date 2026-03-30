package net.artyrian.frontiers;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.reg.client.FREventsClient;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModPredicate;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FrontiersClient
{
    public static final List<Pair<BlockColor, List<Block>>> blockColors = new ArrayList<>();
    public static final List<Pair<ItemColor, List<ItemLike>>> itemColors = new ArrayList<>();

    public static void init()
    {
        ModPredicate.registerModPredicates();       // Item predicates.
        FREventsClient.bootstrap();                 // EventSync (client-side)

        // Do render layers
        renderMaps();
    }

    private static void renderMaps()
    {
        VectorLib.client().setRenderLayer(Blocks.END_PORTAL_FRAME, RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.ANCIENT_ROSE_CROP.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ANCIENT_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.VIOLET_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_ANCIENT_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_VIOLET_ROSE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ANCIENT_ROSE_BUSH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.VIOLET_ROSE_BUSH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BLIGHTED_BIRCH_SAPLING.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.SNOW_DAHLIA.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_SNOW_DAHLIA.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.FUNGAL_DAFFODIL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_FUNGAL_DAFFODIL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.CRIMCONE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_CRIMCONE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.EXPERIWINKLE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.POTTED_EXPERIWINKLE.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.EXPERIWINKLE_CROP.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.WARPED_WART.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.OAK_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.DARK_OAK_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BIRCH_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.SPRUCE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.JUNGLE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ACACIA_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.MANGROVE_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.AZALEA_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.CHERRY_WREATH.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BLIGHTED_BIRCH_WREATH.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.EBONCORK_DOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.EBONCORK_TRAPDOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BLIGHTED_BIRCH_DOOR.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.MONSTER_BAKERY.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.TOWER_SPAWNER.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.TOWER_HEART.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.TOWER_TREASURE_VAULT.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ITEM_VACUUM.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.PHANTOM_STITCH_BED.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.SLIME_BULB.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.CREEPER_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.SKELETON_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.STRAY_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BOGGED_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.BLAZE_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.WITHER_SKELETON_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.ENDERMAN_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.SLIME_MODEL.get(), RenderType.cutout());
        VectorLib.client().setRenderLayer(ModBlocks.MAGMA_CUBE_MODEL.get(), RenderType.cutout());

        VectorLib.client().setRenderLayer(ModBlocks.SEA_GLASS.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(ModBlocks.SEA_GLASS_PANE.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(ModBlocks.PALE_SEA_GLASS.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(ModBlocks.PALE_SEA_GLASS_PANE.get(), RenderType.translucent());
        VectorLib.client().setRenderLayer(ModBlocks.SLIME_TRAIL.get(), RenderType.translucent());

        VectorLib.client().setRenderLayer(ModBlocks.ENCHANTING_MAGNET.get(), RenderType.cutoutMipped());
        VectorLib.client().setRenderLayer(ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), RenderType.cutoutMipped());
        VectorLib.client().setRenderLayer(ModBlocks.COBALT_GRILLES.get(), RenderType.cutoutMipped());

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
                        ModBlocks.OAK_WREATH.get(),
                        ModBlocks.DARK_OAK_WREATH.get(),
                        ModBlocks.JUNGLE_WREATH.get(),
                        ModBlocks.ACACIA_WREATH.get(),
                        ModBlocks.MANGROVE_WREATH.get()
                )
        ));

        // Birch
        blockColors.add(new Pair<>((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                List.of(
                        ModBlocks.BIRCH_WREATH.get()
                )
        ));

        // Spruce
        blockColors.add(new Pair<>((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                List.of(
                        ModBlocks.SPRUCE_WREATH.get()
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
                        ModBlocks.OAK_WREATH.get(),
                        ModBlocks.DARK_OAK_WREATH.get(),
                        ModBlocks.JUNGLE_WREATH.get(),
                        ModBlocks.ACACIA_WREATH.get()
                )
        ));

        // Birch
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getBirchColor()),
                List.of(
                        ModBlocks.BIRCH_WREATH.get()
                )
        ));

        // Spruce
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getEvergreenColor()),
                List.of(
                        ModBlocks.SPRUCE_WREATH.get()
                )
        ));

        // Mangrove (ITEM ONLY)
        itemColors.add(new Pair<>((stack, tintIndex) -> FastColor.ARGB32.opaque(FoliageColor.getMangroveColor()),
                List.of(
                        ModBlocks.MANGROVE_WREATH.get()
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
