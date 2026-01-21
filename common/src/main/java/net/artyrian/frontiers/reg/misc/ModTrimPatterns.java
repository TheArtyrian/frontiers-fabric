package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.armortrim.TrimPatterns;

public class ModTrimPatterns
{
    public static final ResourceKey<TrimPattern> PULSE = reg("pulse");
    public static final ResourceKey<TrimPattern> SLUDGE = reg("sludge");
    public static final ResourceKey<TrimPattern> PHOTON = reg("photon");

    public static void bootstrap(BootstrapContext<TrimPattern> registry)
    {
        TrimPatterns.register(registry, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), PULSE);
        TrimPatterns.register(registry, ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), SLUDGE);
        TrimPatterns.register(registry, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), PHOTON);
    }

    private static ResourceKey<TrimPattern> reg(String id)
    {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id));
    }
}
