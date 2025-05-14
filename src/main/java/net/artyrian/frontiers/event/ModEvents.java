package net.artyrian.frontiers.event;

import net.artyrian.frontiers.effect.ModStatusEffects;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;

// A class allowing for custom entity events.
public class ModEvents
{
    static
    {
        EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> entity.hasStatusEffect(ModStatusEffects.QUICK_FLIGHT));
    }

    // Registers mod events. Just sends a log message.
    public static void registerEvents()
    {
        //Frontiers.LOGGER.info("Registering Mod Events for " + Frontiers.MOD_ID);

    }
}
