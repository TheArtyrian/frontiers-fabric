package net.artyrian.frontiers.reg.misc;

import com.google.common.collect.ImmutableMap;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;

import java.util.Optional;

public class FRTrade
{
    private static final int ARROWHEAD_EMERALDS = 12;

    public static void bootstrap()
    {
        mapper();
        fletcher();
        cleric();
    }

    /** im not calling this class cartographer xd */
    private static void mapper()
    {
        add(VillagerProfession.CARTOGRAPHER, 3, ((entity, randomSource) ->
                VectorTrade.explorerMap(entity, randomSource, 14, ModTags.Structures.ON_WHITE_TOWER_MAPS, "filled_map.frontiers.tower", FRRegistries.MapDecor.TOWER, 12, 10))
        );
    }

    private static void cleric()
    {
        // Cursed Tablet
        add(VillagerProfession.CLERIC, 5, ((entity, randomSource) ->
                VectorTrade.itemPurchase(
                        ModItem.CURSED_TABLET.get(),
                        1,
                        ModItem.INCENSE.get(),
                        randomSource.nextIntBetweenInclusive(3, 8),
                        randomSource.nextIntBetweenInclusive(16, 30),
                        4,
                        30,
                        VectorTrade.HIGH_MULT)
        ));
    }

    private static void fletcher()
    {
        int arrowheadLvl = 5;
        int arrowheadUses = 3;
        int arrowheadXP = 30;

        // Spectral Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), 4, Items.GLOWSTONE_DUST, 16, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
        // Subzero Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.SUBZERO_ARROW_ARROWHEAD.get(), 3, ModBlocks.SNOW_DAHLIA.get(), 4, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
        // Bouncy Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.BOUNCY_ARROW_ARROWHEAD.get(), 3, ModItem.HARDENED_SLIME.get(), 1, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
        // Warp Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.WARP_ARROW_ARROWHEAD.get(), 2, Items.ENDER_PEARL, 4, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
        // Dynamite Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), 2, Items.GUNPOWDER, 12, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
        // Prismarine Arrowhead
        add(VillagerProfession.FLETCHER, arrowheadLvl, ((entity, randomSource) ->
                VectorTrade.itemPurchase(ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), 3, Items.PRISMARINE_SHARD, 8, ARROWHEAD_EMERALDS, arrowheadUses, arrowheadXP, VectorTrade.HIGH_MULT)
        ));
    }

    private static void add(VillagerProfession prof, int level, VillagerTrades.ItemListing offer) { VectorLib.REGISTRY.registerVillagerTrade(() -> new VectorTrade.Profession(prof, level, offer)); }
    private static void add(boolean isRare, VillagerTrades.ItemListing offer) { VectorLib.REGISTRY.registerWanderingTrade(() -> new VectorTrade.Wandering(isRare, offer)); }
}
