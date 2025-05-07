package net.artyrian.frontiers.item.armor;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.sounds.ModSounds;
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
    // Necro Weave
    public static final RegistryEntry<ArmorMaterial> NECRO_WEAVE_ARMOR_MATERIAL = registerArmorMaterial("necro_weave",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(ModItem.NECRO_WEAVE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "necro_weave"))), 2.5F, 0.0F));
    // Mourning Gold
    public static final RegistryEntry<ArmorMaterial> MOURNING_GOLD_ARMOR_MATERIAL = registerArmorMaterial("mourning",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItem.MOURNING_GOLD_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "mourning"))), 0.0F, 0.0F));
    // Cobalt
    public static final RegistryEntry<ArmorMaterial> COBALT_ARMOR_MATERIAL = registerArmorMaterial("cobalt",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.BODY, 14);
            }), 17, ModSounds.ARMOR_EQUIP_COBALT, () -> Ingredient.ofItems(ModItem.COBALT_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "cobalt"))), 3.0F, 0.0F));
    // Verdinite
    public static final RegistryEntry<ArmorMaterial> VERDINITE_ARMOR_MATERIAL = registerArmorMaterial("verdinite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 17);
            }), 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItem.VERDINITE_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "verdinite"))), 4.0F, 0.0F));
    // Frostite
    public static final RegistryEntry<ArmorMaterial> FROSTITE_ARMOR_MATERIAL = registerArmorMaterial("frostite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 17);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItem.FROSTITE_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "frostite"))), 4.0F, 0.0F));
    // Vivulite
    public static final RegistryEntry<ArmorMaterial> VIVULITE_ARMOR_MATERIAL = registerArmorMaterial("vivulite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 6);
                map.put(ArmorItem.Type.LEGGINGS, 9);
                map.put(ArmorItem.Type.CHESTPLATE, 11);
                map.put(ArmorItem.Type.HELMET, 6);
                map.put(ArmorItem.Type.BODY, 20);
            }), 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItem.VIVULITE_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "vivulite"))), 5.0F, 0.1F));
    // Brimtan
    public static final RegistryEntry<ArmorMaterial> BRIMTAN_ARMOR_MATERIAL = registerArmorMaterial("brimtan",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 7);
                map.put(ArmorItem.Type.LEGGINGS, 10);
                map.put(ArmorItem.Type.CHESTPLATE, 12);
                map.put(ArmorItem.Type.HELMET, 7);
                map.put(ArmorItem.Type.BODY, 24);
            }), 6, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(ModItem.BRIMTAN_INGOT),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "brimtan"))), 6.0F, 0.1F));
    // Plate
    public static final RegistryEntry<ArmorMaterial> PLATE_ARMOR_MATERIAL = registerArmorMaterial("plate",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 8);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 11);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModBlocks.TOWER_BRICKS),
                    List.of(new ArmorMaterial.Layer(Identifier.of(Frontiers.MOD_ID, "plate"))), 0.0F, 2.0F));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material)
    {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(Frontiers.MOD_ID, name), material.get());
    }
}
