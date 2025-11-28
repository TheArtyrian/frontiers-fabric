package net.artyrian.frontiers.item.custom.armor;

import com.google.common.base.Suppliers;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class PlateArmorItem extends ArmorItem
{
    private final Supplier<AttributeModifiersComponent> attributeModifiers;

    public PlateArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings)
    {
        super(material, type, settings);
        // Does health instead of armor
        this.attributeModifiers = Suppliers.memoize(
                () -> {
                    // Basic builder for all
                    AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
                    AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot());
                    Identifier identifier = Identifier.ofVanilla("armor." + type.getName());

                    // Health
                    int armor = material.value().getProtection(type);
                    builder.add(
                            EntityAttributes.GENERIC_MAX_HEALTH,
                            new EntityAttributeModifier(identifier, armor, EntityAttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    builder.add(
                            EntityAttributes.PLAYER_MINING_EFFICIENCY,
                            new EntityAttributeModifier(identifier, 0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    builder.add(
                            EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                            new EntityAttributeModifier(identifier, 0.25, EntityAttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );

                    return builder.build();
                }
        );
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() { return this.attributeModifiers.get(); }
}
