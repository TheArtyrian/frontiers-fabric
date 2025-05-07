package net.artyrian.frontiers.event;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class VillagerTradeEventReg
{
    private static final float LOW_MULT = 0.05F;
    private static final float HIGH_MULT = 0.2F;

    public static void doReg()
    {
        // Fletcher - Lv 5 arrowheads
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FLETCHER, 5, factories ->
        {
            // Spectral Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 14),
                            Optional.of(new TradedItem(Items.GLOWSTONE_DUST, 20)),

                            new ItemStack(ModItem.SPECTRAL_ARROW_ARROWHEAD, 2),
                                    3, 10, HIGH_MULT
                    )));

            // Subzero Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 20),
                            Optional.of(new TradedItem(ModBlocks.SNOW_DAHLIA, 4)),

                            new ItemStack(ModItem.SUBZERO_ARROW_ARROWHEAD, 2),
                            3, 10, HIGH_MULT
                    )));

            // Bouncy Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 22),
                            Optional.of(new TradedItem(ModBlocks.FUNGAL_DAFFODIL, 4)),

                            new ItemStack(ModItem.BOUNCY_ARROW_ARROWHEAD, 2),
                            3, 10, HIGH_MULT
                    )));

            // Warp Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 24),
                            Optional.of(new TradedItem(Items.ENDER_PEARL, 8)),

                            new ItemStack(ModItem.WARP_ARROW_ARROWHEAD, 2),
                            3, 10, HIGH_MULT
                    )));

            // Dynamite Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 20),
                            Optional.of(new TradedItem(Items.GUNPOWDER, 24)),

                            new ItemStack(ModItem.DYNAMITE_ARROW_ARROWHEAD, 2),
                            3, 10, HIGH_MULT
                    )));

            // Prismarine Arrowhead
            factories
                    .add(((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 17),
                            Optional.of(new TradedItem(Items.PRISMARINE_SHARD, 8)),

                            new ItemStack(ModItem.PRISMARINE_ARROW_ARROWHEAD, 2),
                            3, 10, HIGH_MULT
                    )));
        });
    }
}
