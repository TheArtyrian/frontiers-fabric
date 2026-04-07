package net.artyrian.frontiers.reg.property;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.armortrim.TrimPatterns;

public class FRTrimPatterns
{
    public static final ResourceKey<TrimPattern> PULSE = reg("pulse");
    public static final ResourceKey<TrimPattern> SLUDGE = reg("sludge");
    public static final ResourceKey<TrimPattern> PHOTON = reg("photon");

    public static void bootstrap(BootstrapContext<TrimPattern> registry)
    {
        TrimPatterns.register(registry, FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), PULSE);
        TrimPatterns.register(registry, FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), SLUDGE);
        TrimPatterns.register(registry, FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), PHOTON);
    }

    private static ResourceKey<TrimPattern> reg(String id)
    {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id));
    }
}
