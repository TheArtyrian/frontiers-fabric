package net.artyrian.frontiers.client.screen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreen;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers
{
    public static final ScreenHandlerType<CurseAltarScreenHandler> CURSE_ALTAR =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Frontiers.MOD_ID, "curse_altar"),
                    new ScreenHandlerType<>(CurseAltarScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void registerClientScreens()
    {
        HandledScreens.register(CURSE_ALTAR, CurseAltarScreen::new);
    }

    public static void registerScreens()
    {

    }
}
