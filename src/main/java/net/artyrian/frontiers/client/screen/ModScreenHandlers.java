package net.artyrian.frontiers.client.screen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreen;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreenHandler;
import net.artyrian.frontiers.client.screen.fletching.FletchingTableScreen;
import net.artyrian.frontiers.client.screen.fletching.FletchingTableScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers
{
    public static final ScreenHandlerType<CurseAltarScreenHandler> CURSE_ALTAR =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Frontiers.MOD_ID, "curse_altar"),
                    new ScreenHandlerType<>(CurseAltarScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final ScreenHandlerType<FletchingTableScreenHandler> FLETCHING_TABLE =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Frontiers.MOD_ID, "fletching_table"),
                    new ScreenHandlerType<>(FletchingTableScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void registerClientScreens()
    {
        HandledScreens.register(CURSE_ALTAR, CurseAltarScreen::new);
        HandledScreens.register(FLETCHING_TABLE, FletchingTableScreen::new);
    }

    public static void registerScreens()
    {

    }
}
