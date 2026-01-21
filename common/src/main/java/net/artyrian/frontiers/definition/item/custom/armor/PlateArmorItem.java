package net.artyrian.frontiers.definition.item.custom.armor;

import com.google.common.base.Suppliers;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class PlateArmorItem extends ArmorItem
{
    private final Supplier<ItemAttributeModifiers> attributeModifiers;

    public PlateArmorItem(Holder<ArmorMaterial> material, Type type, Properties settings)
    {
        super(material, type, settings);
        // Does health instead of armor
        this.attributeModifiers = Suppliers.memoize(
                () -> {
                    // Basic builder for all
                    ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
                    EquipmentSlotGroup attributeModifierSlot = EquipmentSlotGroup.bySlot(type.getSlot());
                    ResourceLocation identifier = ResourceLocation.withDefaultNamespace("armor." + type.getName());

                    // Health
                    int armor = material.value().getDefense(type);
                    builder.add(
                            Attributes.MAX_HEALTH,
                            new AttributeModifier(identifier, armor, AttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    builder.add(
                            Attributes.MINING_EFFICIENCY,
                            new AttributeModifier(identifier, 0.25, AttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );
                    builder.add(
                            Attributes.BLOCK_INTERACTION_RANGE,
                            new AttributeModifier(identifier, 0.25, AttributeModifier.Operation.ADD_VALUE),
                            attributeModifierSlot
                    );

                    return builder.build();
                }
        );
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() { return this.attributeModifiers.get(); }
}
