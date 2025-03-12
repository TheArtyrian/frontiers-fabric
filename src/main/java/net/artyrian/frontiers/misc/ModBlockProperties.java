package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;

public class ModBlockProperties
{
    // Brewing Stand - Has Rod
    public static final BooleanProperty HAS_ROD = BooleanProperty.of("frontiers_has_rod");
    // Lightning Rod - Connected
    public static final BooleanProperty ROD_CONNECTED = BooleanProperty.of("frontiers_connected");
    // Budding Amethyst - Is Corrupt
    public static final BooleanProperty IS_CORRUPTED = BooleanProperty.of("frontiers_is_corrupted");
    // Sculk Catalyst - Wardenized
    public static final BooleanProperty CATALYST_WARDENIZED = BooleanProperty.of("frontiers_wardenized");
    // Strange Core - Active Power
    public static final IntProperty ACTIVE_POWER = IntProperty.of("active", 0, 2);
    // Lumen - Lumen Power
    public static final IntProperty LUMEN_POWER = IntProperty.of("lumen_power", 0, 2);

    // Registers mod properties. Just sends a log message.
    public static void registerProperties()
    {
        //Frontiers.LOGGER.info("Registering Mod Properties for " + Frontiers.MOD_ID);
    }
}
