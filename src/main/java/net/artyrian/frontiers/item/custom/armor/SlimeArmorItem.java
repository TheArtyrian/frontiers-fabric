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

public class SlimeArmorItem extends ArmorItem
{
    private final Supplier<AttributeModifiersComponent> attributeModifiers;

    public SlimeArmorItem(RegistryEntry<ArmorMaterial> material, Type type, int extra_safe_dist, Settings settings)
    {
        super(material, type, settings);
        // Copies what Vanilla does but adds some things
        this.attributeModifiers = Suppliers.memoize(
                () -> {
                    // Basic builder for all
                    AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
                    AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot());
                    Identifier identifier = Identifier.ofVanilla("armor." + type.getName());

                    // Armor
                    int armor = material.value().getProtection(type);
                    builder.add(
                            EntityAttributes.GENERIC_ARMOR,
                            new EntityAttributeModifier(identifier, armor, EntityAttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    // Toughness
                    float toughness = material.value().toughness();
                    builder.add(
                            EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                            new EntityAttributeModifier(identifier, toughness, EntityAttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    // KB Resist
                    float kb_resist = material.value().knockbackResistance();
                    if (kb_resist > 0.0F) {
                        builder.add(
                                EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                                new EntityAttributeModifier(identifier, kb_resist, EntityAttributeModifier.Operation.ADD_VALUE),
                                attributeModifierSlot
                        );
                    }
                    // UNIQUE: Safe fall dist
                    if (extra_safe_dist > 0)
                    {
                        builder.add(
                                EntityAttributes.GENERIC_SAFE_FALL_DISTANCE,
                                new EntityAttributeModifier(identifier, extra_safe_dist, EntityAttributeModifier.Operation.ADD_VALUE),
                                attributeModifierSlot
                        );
                    }

                    return builder.build();
                }
        );
    }

    @Override
    public AttributeModifiersComponent getAttributeModifiers() { return this.attributeModifiers.get(); }
}
