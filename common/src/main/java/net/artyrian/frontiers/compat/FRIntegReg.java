package net.artyrian.frontiers.compat;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.bountifulfares.BFItemTabs;
import net.artyrian.frontiers.compat.dyemods.DyeModDummyItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.vertisoft.vectorlib.VectorLib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FRIntegReg
{
    public static final List<Supplier<? extends ItemLike>> INTEG_ITEMS = new ArrayList<>();
    public static final String INTEG_ID = "frontiers_integ";

    public static void initIntegr()
    {
        // MOD-COMPAT ONLY LOADS!!! Will only be done if the proper mod is detected.
        if (Frontiers.FARMERS_DELIGHT_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Found Farmer's Delight - registering exclusive content.");
            FDItem.registerModItems(Frontiers.DOING_DATAGEN);		    // Farmer's Delight Items (Knives, etc.)
        }
        if (Frontiers.BOUNTIFUL_FARES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Found Bountiful Fares - registering exclusive content.");
            BFItem.registerModItems(Frontiers.DOING_DATAGEN);			// Bountiful Fares Items (mainly refs to existing BF items)
            BFBlock.registerModBlocks(Frontiers.DOING_DATAGEN);	        // Bountiful Fares Blocks
        }
        if (Frontiers.SUPPLEMENTARIES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Found Supplementaries.");
        }
        if (Frontiers.LEGACY4J_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Found Legacy4J.");
        }
        if (Frontiers.DELICATE_DYES_LOADED || Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Found Delicate Dyes.");
        }

        // Datagen-dummy
        if (Frontiers.DOING_DATAGEN)
        {
            Frontiers.LOGGER.info("[FRONTIERS] Datagen is go!");
            DyeModDummyItem.registerDDyeItems();	            // DDyes
        }

        if (!INTEG_ITEMS.isEmpty() && Frontiers.CONFIG.doCreativeModeTabs())
        {
            VectorLib.REGISTRY.register(Frontiers.MOD_ID, INTEG_ID, BuiltInRegistries.CREATIVE_MODE_TAB, () ->
                CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                        .title(Component.translatable("itemGroup.frontiers.integ"))
                        .icon(() -> FRItems.COBALT_SWORD.get().getDefaultInstance())
                        .displayItems((param, output) -> {
                            for (Supplier<? extends ItemLike> like : INTEG_ITEMS)
                            {
                                output.accept(like.get().asItem().getDefaultInstance());
                            }
                        })
                        .build()
            );
        }
    }

    public static void integPost()
    {
        // Farmer's Delight Item Tab addendums
        if (Frontiers.FARMERS_DELIGHT_LOADED || Frontiers.DOING_DATAGEN)
        {
            FDItemTabs.registerModItemTabs();
        }
        // Bountiful Fares Item Tab addendums
        if (Frontiers.BOUNTIFUL_FARES_LOADED || Frontiers.DOING_DATAGEN)
        {
            BFItemTabs.registerModItemTabs();
        }
    }
}
