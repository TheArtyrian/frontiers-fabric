package net.artyrian.frontiers.item.trim;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.data.ModArmorMaterials;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ModTrimMaterials
{
    public static final RegistryKey<ArmorTrimMaterial> COBALT = of("cobalt");
    public static final RegistryKey<ArmorTrimMaterial> VERDINITE = of("verdinite");
    public static final RegistryKey<ArmorTrimMaterial> VIVULITE = of("vivulite");
    public static final RegistryKey<ArmorTrimMaterial> FROSTITE = of("frostite");
    public static final RegistryKey<ArmorTrimMaterial> MOURNING_GOLD = of("mourning_gold");

    public static void bootstrap(Registerable<ArmorTrimMaterial> registry)
    {
        register(registry, COBALT,
                ModItem.COBALT_INGOT, Style.EMPTY.withColor(0x003AD8), 10.0F, Map.of(
                        ModArmorMaterials.COBALT_ARMOR_MATERIAL, "cobalt_darker"));

        register(registry, VERDINITE,
                ModItem.VERDINITE_INGOT, Style.EMPTY.withColor(0x05A559), 10.1F, Map.of(
                        ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, "verdinite_darker"));

        register(registry, VIVULITE,
                ModItem.VIVULITE_INGOT, Style.EMPTY.withColor(0xBB144B), 10.2F, Map.of(
                        ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, "vivulite_darker"));

        register(registry, FROSTITE,
                ModItem.FROSTITE_INGOT, Style.EMPTY.withColor(0x409DC0), 10.3F, Map.of(
                        ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, "frostite_darker"));

        register(registry, MOURNING_GOLD,
                ModItem.MOURNING_GOLD_INGOT, Style.EMPTY.withColor(0xA28C86), 10.4F, Map.of(
                        ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, "mourning_gold_darker"));

    }

    private static void register(
            Registerable<ArmorTrimMaterial> registry,
            RegistryKey<ArmorTrimMaterial> armor,
            Item item,
            Style style,
            float itemModelIndex
    )
    {
        register(registry, armor, item, style, itemModelIndex, Map.of());
    }

    private static void register(
            Registerable<ArmorTrimMaterial> registry,
            RegistryKey<ArmorTrimMaterial> armor,
            Item item,
            Style style,
            float itemModelIndex,
            Map<RegistryEntry<ArmorMaterial>, String> overrideArmorMaterials
    )
    {
        ArmorTrimMaterial armorTrimMaterial = ArmorTrimMaterial.of(
                armor.getValue().getPath(),
                item,
                itemModelIndex,
                Text.translatable(Util.createTranslationKey("trim_material", armor.getValue())).fillStyle(style),
                overrideArmorMaterials
        );
        registry.register(armor, armorTrimMaterial);
    }

    // Why are these gas prices so HIGH wawa, why
    private static RegistryKey<ArmorTrimMaterial> of(String id)
    {
        return RegistryKey.of(RegistryKeys.TRIM_MATERIAL, Identifier.of(Frontiers.MOD_ID, id));
    }
}
