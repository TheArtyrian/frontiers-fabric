package net.artyrian.frontiers.effect;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.types.ModStatusEffect;
import net.artyrian.frontiers.effect.types.QuickFlightEffect;
import net.artyrian.frontiers.effect.types.StormPoisonEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects
{
    public static final RegistryEntry<StatusEffect> QUICK_FLIGHT = register("quick_flight", new QuickFlightEffect(StatusEffectCategory.HARMFUL, 0xFF2482));
    public static final RegistryEntry<StatusEffect> STORM_POISONING = register("storm_poisoning", new StormPoisonEffect(StatusEffectCategory.HARMFUL, 0x6A375C));
    public static final RegistryEntry<StatusEffect> MAGMA_VISION = register("magma_vision", new ModStatusEffect(StatusEffectCategory.HARMFUL, 0xEA5B15));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Frontiers.MOD_ID, id), statusEffect);
    }

    // Registers mod status fx. Just sends a log message.
    public static void registerEffects()
    {
        //Frontiers.LOGGER.info("Registering Mod Status Effects for " + Frontiers.MOD_ID);
    }
}
