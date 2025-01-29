package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.state.property.BooleanProperty;

public class ModBlockProperties
{
    public static final BooleanProperty HAS_ROD = BooleanProperty.of("has_rod");

    // Registers mod properties. Just sends a log message.
    public static void registerProperties()
    {
        //Frontiers.LOGGER.info("Registering Mod Properties for " + Frontiers.MOD_ID);
    }
}
