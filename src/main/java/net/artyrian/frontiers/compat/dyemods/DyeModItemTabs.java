package net.artyrian.frontiers.compat.dyemods;

import net.artyrian.frontiers.compat.bountifulfares.BFItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.DyeColor;

public class DyeModItemTabs
{
    public static class DelicateDyes
    {
        public static void tabTools(FabricItemGroupEntries tab)
        {
            DyeColor CORAL = DyeColor.byName("DD_CORAL", DyeColor.WHITE);
            DyeColor UMBER = DyeColor.byName("DD_UMBER", DyeColor.WHITE);
            DyeColor CANARY = DyeColor.byName("DD_CANARY", DyeColor.WHITE);
            DyeColor WASABI = DyeColor.byName("DD_WASABI", DyeColor.WHITE);
            DyeColor SACRAMENTO = DyeColor.byName("DD_SACRAMENTO", DyeColor.WHITE);
            DyeColor SKY = DyeColor.byName("DD_SKY", DyeColor.WHITE);
            DyeColor BLURPLE = DyeColor.byName("DD_BLURPLE", DyeColor.WHITE);
            DyeColor LAVENDER = DyeColor.byName("DD_LAVENDER", DyeColor.WHITE);
            DyeColor SANGRIA = DyeColor.byName("DD_SANGRIA", DyeColor.WHITE);
            DyeColor ROSE = DyeColor.byName("DD_ROSE", DyeColor.WHITE);

            if (CORAL != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.RED), ModItem.COLOR_BALLS.get(CORAL));
            if (UMBER != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(CORAL), ModItem.COLOR_BALLS.get(UMBER));
            if (CANARY != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.YELLOW), ModItem.COLOR_BALLS.get(CANARY));
            if (WASABI != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(CANARY), ModItem.COLOR_BALLS.get(WASABI));
            if (SACRAMENTO != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.GREEN), ModItem.COLOR_BALLS.get(SACRAMENTO));
            if (SKY != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.CYAN), ModItem.COLOR_BALLS.get(SKY));
            if (BLURPLE != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.BLUE), ModItem.COLOR_BALLS.get(BLURPLE));
            if (LAVENDER != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.PURPLE), ModItem.COLOR_BALLS.get(LAVENDER));
            if (SANGRIA != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.MAGENTA), ModItem.COLOR_BALLS.get(SANGRIA));
            if (ROSE != DyeColor.WHITE) tab.addAfter(ModItem.COLOR_BALLS.get(DyeColor.PINK), ModItem.COLOR_BALLS.get(ROSE));
        }

        public static void registerModItemTabs()
        {
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(DyeModItemTabs.DelicateDyes::tabTools);
        }
    }
}
