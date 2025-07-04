package net.artyrian.frontiers.item.trim;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.item.trim.ArmorTrimPatterns;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModTrimPatterns
{
    public static final RegistryKey<ArmorTrimPattern> PULSE = reg("pulse");
    public static final RegistryKey<ArmorTrimPattern> SLUDGE = reg("sludge");
    public static final RegistryKey<ArmorTrimPattern> PHOTON = reg("photon");

    public static void bootstrap(Registerable<ArmorTrimPattern> registry)
    {
        ArmorTrimPatterns.register(registry, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE, PULSE);
        ArmorTrimPatterns.register(registry, ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE, SLUDGE);
        ArmorTrimPatterns.register(registry, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE, PHOTON);
    }

    private static RegistryKey<ArmorTrimPattern> reg(String id)
    {
        return RegistryKey.of(RegistryKeys.TRIM_PATTERN, Identifier.of(Frontiers.MOD_ID, id));
    }
}
