package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockProperties
{
    // Brewing Stand - Has Rod / Lightning Bottles 0-2
    public static final BooleanProperty HAS_ROD = BooleanProperty.create("frontiers_has_rod");
    public static final BooleanProperty LIGHTNING_0 = BooleanProperty.create("frontiers_lightning_0");
    public static final BooleanProperty LIGHTNING_1 = BooleanProperty.create("frontiers_lightning_1");
    public static final BooleanProperty LIGHTNING_2 = BooleanProperty.create("frontiers_lightning_2");

    // Lightning Rod - Connected
    public static final BooleanProperty ROD_CONNECTED = BooleanProperty.create("frontiers_connected");

    // Budding Amethyst - Is Corrupt
    public static final BooleanProperty IS_CORRUPTED = BooleanProperty.create("frontiers_is_corrupted");

    // Sculk Catalyst - Wardenized
    public static final BooleanProperty CATALYST_WARDENIZED = BooleanProperty.create("frontiers_wardenized");

    // Strange Core - Active Power
    public static final IntegerProperty ACTIVE_POWER = IntegerProperty.create("active", 0, 2);

    // Lumen - Lumen Power
    public static final IntegerProperty LUMEN_POWER = IntegerProperty.create("lumen_power", 0, 2);

    // Entity Model
    public static final BooleanProperty MODEL_POWERED = BooleanProperty.create("model_powered");
    public static final BooleanProperty MODEL_SHEARED = BooleanProperty.create("model_sheared");
    public static final IntegerProperty MODEL_SHEAR_COUNT = IntegerProperty.create("model_shear_count", 0, 2);

    // Tower
    public static final BooleanProperty DEFEATED = BooleanProperty.create("defeated");
    public static final BooleanProperty ENRAGED = BooleanProperty.create("enraged");

    public static void registerProperties()
    {

    }
}
