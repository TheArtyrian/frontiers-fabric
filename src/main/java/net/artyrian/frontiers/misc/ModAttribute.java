package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModAttribute
{
    public static final RegistryEntry<EntityAttribute> PLAYER_EATEN_APPLE = register(
            "player.ate_hpapple", new ClampedEntityAttribute("attribute.name.player.ate_hpapple", 0.0, 0.0, 1.0).setTracked(true)
    );

    public static final RegistryEntry<EntityAttribute> PLAYER_DEFAULT_HP = register(
            "player.ate_hpapple", new ClampedEntityAttribute("attribute.name.player.default_hp", 20.0, 0.0, 20.0).setTracked(true)
    );

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(Frontiers.MOD_ID, id), attribute);
    }

    // Registers mod attributes. Just sends a log message.
    public static void registerModAttributes()
    {
        Frontiers.LOGGER.info("Registering Mod Attributes for " + Frontiers.MOD_ID);
    }
}
