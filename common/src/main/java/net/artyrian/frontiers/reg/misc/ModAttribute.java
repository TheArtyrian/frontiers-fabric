package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModAttribute
{
    // Modifiers
    public static final AttributeModifier APPLE_HEALTH = registerModifier("apple_health", 4.0, AttributeModifier.Operation.ADD_VALUE);

    private static AttributeModifier registerModifier(String id, double value, AttributeModifier.Operation operation)
    {
        return new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id), value, operation);
    }

    private static Holder<Attribute> registerAttribute(String id, Supplier<Attribute> attribute)
    {
        return VectorLib.REGISTRY.registerHolder(Frontiers.MOD_ID, id, BuiltInRegistries.ATTRIBUTE, attribute);
    }

    public static void registerModAttributes()
    {

    }
}
