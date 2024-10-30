package net.artyrian.frontiers.item.data;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials
{
    public static final RegistryEntry<ArmorMaterial> NECRO_WEAVE_ARMOR_MATERIAL = registerArmorMaterial("necro_weave",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(ModItem.NECRO_WEAVE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "necro_weave"))), 2.5F, 0.05F));
    public static final RegistryEntry<ArmorMaterial> FROSTITE_ARMOR_MATERIAL = registerArmorMaterial("frostite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItem.FROSTITE_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "frostite"))), 4.0F, 0.25F));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material)
    {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Frontiers.MOD_ID, name), material.get());
    }
}
