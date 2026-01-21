package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import java.util.Map;

public class ModTrimMaterials
{
    public static final ResourceKey<TrimMaterial> COBALT = of("cobalt");
    public static final ResourceKey<TrimMaterial> VERDINITE = of("verdinite");
    public static final ResourceKey<TrimMaterial> VIVULITE = of("vivulite");
    public static final ResourceKey<TrimMaterial> FROSTITE = of("frostite");
    public static final ResourceKey<TrimMaterial> MOURNING_GOLD = of("mourning_gold");
    public static final ResourceKey<TrimMaterial> BRIMTAN = of("brimtan");

    public static void bootstrap(BootstrapContext<TrimMaterial> registry)
    {
        register(registry, COBALT,
                ModItem.COBALT_INGOT.get(), Style.EMPTY.withColor(0x003AD8), 0.001F, Map.of(
                        ModArmorMaterials.COBALT_ARMOR_MATERIAL, "cobalt_darker"));

        register(registry, VERDINITE,
                ModItem.VERDINITE_INGOT.get(), Style.EMPTY.withColor(0x05A559), 0.002F, Map.of(
                        ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, "verdinite_darker"));

        register(registry, VIVULITE,
                ModItem.VIVULITE_INGOT.get(), Style.EMPTY.withColor(0xBB144B), 0.003F, Map.of(
                        ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, "vivulite_darker"));

        register(registry, FROSTITE,
                ModItem.FROSTITE_INGOT.get(), Style.EMPTY.withColor(0x409DC0), 0.004F, Map.of(
                        ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, "frostite_darker"));

        register(registry, MOURNING_GOLD,
                ModItem.MOURNING_GOLD_INGOT.get(), Style.EMPTY.withColor(0xA28C86), 0.005F, Map.of(
                        ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, "mourning_gold_darker"));

        register(registry, BRIMTAN,
                ModItem.BRIMTAN_INGOT.get(), Style.EMPTY.withColor(0xFF3A07), 0.006F, Map.of(
                        ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, "brimtan_darker"));
    }

    private static void register(
            BootstrapContext<TrimMaterial> registry,
            ResourceKey<TrimMaterial> armor,
            Item item,
            Style style,
            float itemModelIndex
    )
    {
        register(registry, armor, item, style, itemModelIndex, Map.of());
    }

    private static void register(
            BootstrapContext<TrimMaterial> registry,
            ResourceKey<TrimMaterial> armor,
            Item item,
            Style style,
            float itemModelIndex,
            Map<Holder<ArmorMaterial>, String> overrideArmorMaterials
    )
    {
        TrimMaterial armorTrimMaterial = TrimMaterial.create(
                armor.location().getPath(),
                item,
                itemModelIndex,
                Component.translatable(Util.makeDescriptionId("trim_material", armor.location())).withStyle(style),
                overrideArmorMaterials
        );
        registry.register(armor, armorTrimMaterial);
    }

    // Why are these gas prices so HIGH wawa, why
    private static ResourceKey<TrimMaterial> of(String id)
    {
        return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id));
    }
}
