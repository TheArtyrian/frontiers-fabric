package net.artyrian.frontiers.event;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class VillagerTradeEventReg
{
    public static void doReg()
    {
        //TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 1, factories -> {
        //    factories.add(((entity, random) -> new TradeOffer(
        //            new TradedItem(Items.EMERALD, 2),
        //            new ItemStack(Items.DIRT, 5),
        //            1, 5, 0.05F
        //    )));
        //});
    }
}
