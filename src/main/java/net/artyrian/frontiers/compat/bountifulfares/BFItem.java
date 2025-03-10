package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

// A list of Bountiful Fares exclusive pack-in items.
public class BFItem
{
    public static Item GUARDIAN_SOUP = null;

    // Existing BF items; here for referencing!
    public static Item FELDSPAR = null;
    public static Item SPONGEKIN_SLICE = null;
    public static Item PASSION_GLAZED_SALMON = null;
    public static Item COCONUT_CRUSTED_COD = null;

    // References to the mod's potion effects.
    public static StatusEffect ENRICHMENT;
    public static RegistryEntry<StatusEffect> ENRICHMENT_REG;

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    public static void registerModItems()
    {
        // Register status effects. Risky? Hahahahahahahaha
        ENRICHMENT = Registries.STATUS_EFFECT.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "enrichment"));
        ENRICHMENT_REG = Registries.STATUS_EFFECT.getEntry(ENRICHMENT);

        // Register new items.
        GUARDIAN_SOUP = registerItem("guardian_soup",
                new StackableBowlFoodItem(List.of(
                        new StatusEffectInstance(ENRICHMENT_REG, 1200, 0, true, true),
                        new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0, true, true)),
                        new Item.Settings().maxCount(16).food((
                                new FoodComponent.Builder())
                                .nutrition(10)
                                .saturationModifier(0.6F)
                                .statusEffect(
                                        new StatusEffectInstance(ENRICHMENT_REG, 2400, 0, true, true), 1)
                                .statusEffect(
                                        new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0, true, true), 1)
                                .build())
                                .recipeRemainder(Items.BOWL))
        );

        // Locate existing items.
        FELDSPAR = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "feldspar"));
        SPONGEKIN_SLICE = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "spongekin_slice"));
        PASSION_GLAZED_SALMON = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "passion_glazed_salmon"));
        COCONUT_CRUSTED_COD = Registries.ITEM.get(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "coconut_crusted_cod"));
    }
}
