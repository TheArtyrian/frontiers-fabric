package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
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
    }

    /** im not calling this class cartographer xd */
    private static void mapper()
    {
        add(VillagerProfession.CARTOGRAPHER, 3, ((entity, randomSource) -> VectorTrade
                .explorerMap(entity, randomSource, 14, ModTags.Structures.ON_WHITE_TOWER_MAPS, "filled_map.frontiers.tower", FRRegistries.MapDecor.TOWER, 12, 10))
        );
    }

    private static void fletcher()
    {
        // Spectral Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(Items.GLOWSTONE_DUST, 16)),

                new ItemStack(ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), 4), 3, 10, VectorTrade.HIGH_MULT)
        ));
        // Subzero Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(ModBlocks.SNOW_DAHLIA.get(), 4)),

                new ItemStack(ModItem.SUBZERO_ARROW_ARROWHEAD.get(), 3), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Bouncy Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(ModItem.HARDENED_SLIME.get(), 1)),

                new ItemStack(ModItem.BOUNCY_ARROW_ARROWHEAD.get(), 3), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Warp Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(Items.ENDER_PEARL, 4)),

                new ItemStack(ModItem.WARP_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Dynamite Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(Items.GUNPOWDER, 12)),

                new ItemStack(ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Prismarine Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, ARROWHEAD_EMERALDS),
                Optional.of(new ItemCost(Items.PRISMARINE_SHARD, 8)),

                new ItemStack(ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), 3), 3, 10, VectorTrade.HIGH_MULT)
        ));
    }

    private static void add(VillagerProfession prof, int level, VillagerTrades.ItemListing offer) { VectorLib.REGISTRY.registerVillagerTrade(() -> new VectorTrade.Profession(prof, level, offer)); }
    private static void add(boolean isRare, VillagerTrades.ItemListing offer) { VectorLib.REGISTRY.registerWanderingTrade(() -> new VectorTrade.Wandering(isRare, offer)); }
}
