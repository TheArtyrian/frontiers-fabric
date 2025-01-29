package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModAttribute
{
    // Attributes
    public static final RegistryEntry<EntityAttribute> PLAYER_EATEN_APPLE = registerAttribute("player.eaten_apple",  new ClampedEntityAttribute(
            "frontiers.player.eaten_apple", 0.0, 0.0, 1.0)
    );
    // Modifiers
    public static final EntityAttributeModifier APPLE_HEALTH = registerModifier("apple_health", 4.0, EntityAttributeModifier.Operation.ADD_VALUE);

    private static EntityAttributeModifier registerModifier(String id, double value, EntityAttributeModifier.Operation operation)
    {
        return new EntityAttributeModifier(Identifier.of(Frontiers.MOD_ID, id), value, operation);
    }

    private static RegistryEntry<EntityAttribute> registerAttribute(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(Frontiers.MOD_ID, id), attribute);
    }

    // Registers mod attributes. Just sends a log message.
    public static void registerModAttributes()
    {
        //Frontiers.LOGGER.info("Registering Mod Attributes/Modifiers for " + Frontiers.MOD_ID);
    }
}
