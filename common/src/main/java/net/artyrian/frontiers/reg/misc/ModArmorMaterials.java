package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials
{
    // Necro Weave
    public static final Holder<ArmorMaterial> NECRO_WEAVE_ARMOR_MATERIAL = registerArmorMaterial("necro_weave",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 10, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItem.NECRO_WEAVE.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("necro_weave"))), 2.5F, 0.0F));
    // Mourning Gold
    public static final Holder<ArmorMaterial> MOURNING_GOLD_ARMOR_MATERIAL = registerArmorMaterial("mourning",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 11);
            }), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItem.MOURNING_GOLD_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("mourning"))), 0.0F, 0.0F));
    // Cobalt
    public static final Holder<ArmorMaterial> COBALT_ARMOR_MATERIAL = registerArmorMaterial("cobalt",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 4);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.BODY, 14);
            }), 17, ModSounds.ARMOR_EQUIP_COBALT, () -> Ingredient.of(ModItem.COBALT_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("cobalt"))), 3.0F, 0.0F));
    // Verdinite
    public static final Holder<ArmorMaterial> VERDINITE_ARMOR_MATERIAL = registerArmorMaterial("verdinite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 17);
            }), 13, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItem.VERDINITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("verdinite"))), 4.0F, 0.0F));
    // Frostite
    public static final Holder<ArmorMaterial> FROSTITE_ARMOR_MATERIAL = registerArmorMaterial("frostite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 5);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.BODY, 17);
            }), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItem.FROSTITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("frostite"))), 4.0F, 0.0F));
    // Vivulite
    public static final Holder<ArmorMaterial> VIVULITE_ARMOR_MATERIAL = registerArmorMaterial("vivulite",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 6);
                map.put(ArmorItem.Type.LEGGINGS, 9);
                map.put(ArmorItem.Type.CHESTPLATE, 11);
                map.put(ArmorItem.Type.HELMET, 6);
                map.put(ArmorItem.Type.BODY, 20);
            }), 18, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItem.VIVULITE_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("vivulite"))), 5.0F, 0.1F));
    // Brimtan
    public static final Holder<ArmorMaterial> BRIMTAN_ARMOR_MATERIAL = registerArmorMaterial("brimtan",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 7);
                map.put(ArmorItem.Type.LEGGINGS, 10);
                map.put(ArmorItem.Type.CHESTPLATE, 12);
                map.put(ArmorItem.Type.HELMET, 7);
                map.put(ArmorItem.Type.BODY, 24);
            }), 6, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItem.BRIMTAN_INGOT.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("brimtan"))), 6.0F, 0.1F));
    // Plate
    public static final Holder<ArmorMaterial> PLATE_ARMOR_MATERIAL = registerArmorMaterial("plate",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 5, SoundEvents.ARMOR_EQUIP_TURTLE, Ingredient::of,
                    List.of(new ArmorMaterial.Layer(Frontiers.id("plate"))), 0.0F, 2.0F));
    // Slime
    public static final Holder<ArmorMaterial> SLIME_ARMOR_MATERIAL = registerArmorMaterial("slime",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->
            {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 1);
                map.put(ArmorItem.Type.CHESTPLATE, 1);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 1);
            }), 8, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItem.HARDENED_SLIME.get()),
                    List.of(new ArmorMaterial.Layer(Frontiers.id("slime"))), 0.0F, 0.0F));

    public static Holder<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material)
    {
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, Frontiers.id(name), material.get());
    }
}
