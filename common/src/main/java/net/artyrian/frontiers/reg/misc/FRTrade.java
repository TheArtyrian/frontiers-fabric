package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FRTrade
{
    public static final List<VectorTrade> TRADES = new ArrayList<>();

    public static void bootstrap()
    {
        // Spectral Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 14),
                Optional.of(new ItemCost(Items.GLOWSTONE_DUST, 20)),

                new ItemStack(ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));
        // Subzero Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 20),
                        Optional.of(new ItemCost(ModBlocks.SNOW_DAHLIA.get(), 4)),

                        new ItemStack(ModItem.SUBZERO_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Bouncy Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 22),
                        Optional.of(new ItemCost(ModBlocks.FUNGAL_DAFFODIL.get(), 4)),

                        new ItemStack(ModItem.BOUNCY_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Warp Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 24),
                        Optional.of(new ItemCost(Items.ENDER_PEARL, 8)),

                        new ItemStack(ModItem.WARP_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Dynamite Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 20),
                        Optional.of(new ItemCost(Items.GUNPOWDER, 24)),

                        new ItemStack(ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));

        // Prismarine Arrowhead
        add(VillagerProfession.FLETCHER, 5, ((entity, randomSource) -> new MerchantOffer(
                        new ItemCost(Items.EMERALD, 17),
                        Optional.of(new ItemCost(Items.PRISMARINE_SHARD, 8)),

                        new ItemStack(ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), 2), 3, 10, VectorTrade.HIGH_MULT)
        ));
    }

    private static void add(VillagerProfession prof, int level, VillagerTrades.ItemListing offer)
    {
        TRADES.add(new VectorTrade(prof, level, offer));
    }
}
