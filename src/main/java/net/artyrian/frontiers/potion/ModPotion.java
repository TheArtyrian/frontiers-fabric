package net.artyrian.frontiers.potion;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotion
{
    // Levitation
    public static final RegistryEntry<Potion> LEVITATION = Registry.registerReference(Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 200, 0))
    );
    // Levitation (long)
    public static final RegistryEntry<Potion> LONG_LEVITATION = Registry.registerReference(Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.long_levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 600, 0))
    );
    // Levitation II
    public static final RegistryEntry<Potion> STRONG_LEVITATION = Registry.registerReference(Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.strong_levitation"),
            new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 120, 1))
    );

    // Advanced Potion - Life Boost
    public static final RegistryEntry<Potion> LIFE_BOOST = Registry.registerReference(Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.life_boost"),
            new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 0))
    );
    // Advanced Potion - Turbo Regeneration
    public static final RegistryEntry<Potion> TURBO_REGENERATION = Registry.registerReference(Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.turbo_regeneration"),
            new Potion(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 2))
    );

    // Debonair
    public static final RegistryEntry<Potion> DEBONAIR = Registry.registerReference(
            Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.debonair"), new Potion());

    // Interesting Potion - Health
    public static final RegistryEntry<Potion> INTERESTING_HEALTH = Registry.registerReference(
            Registries.POTION, Identifier.of(Frontiers.MOD_ID, "frontiers.interesting_health"), new Potion());

    // Registers mod potions. Just sends a log message.
    public static void registerPotions()
    {
        //Frontiers.LOGGER.info("Registering Mod Potions for " + Frontiers.MOD_ID);
    }
}