package net.artyrian.frontiers.reg.property;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FRPotions
{
    public static final Holder<Potion> LEVITATION = registerPotion("frontiers.levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 200, 0)));
    public static final Holder<Potion> LONG_LEVITATION = registerPotion("frontiers.long_levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 600, 0)));
    public static final Holder<Potion> STRONG_LEVITATION = registerPotion("frontiers.strong_levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 120, 1)));

    public static final Holder<Potion> MAGMA_VISION = registerPotion("frontiers.magma_vision", () -> new Potion(new MobEffectInstance(FRStatusEffects.MAGMA_VISION, 1800, 0)));
    public static final Holder<Potion> LONG_MAGMA_VISION = registerPotion("frontiers.long_magma_vision", () -> new Potion(new MobEffectInstance(FRStatusEffects.MAGMA_VISION, 4200, 0)));

    public static final Holder<Potion> DECAY = registerPotion("frontiers.decay", () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 400, 0)));
    public static final Holder<Potion> LIFE_BOOST = registerPotion("frontiers.life_boost", () -> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 0)));
    public static final Holder<Potion> TURBO_REGENERATION = registerPotion("frontiers.turbo_regeneration", () -> new Potion(new MobEffectInstance(MobEffects.REGENERATION, 300, 2)));

    public static final Holder<Potion> DEBONAIR = registerPotion("frontiers.debonair", () -> new Potion());
    public static final Holder<Potion> GLUTTONY = registerPotion("frontiers.gluttony", () -> new Potion());

    public static final Holder<Potion> INTERESTING_HEALTH = registerPotion("frontiers.interesting_health", () -> new Potion());
    public static final Holder<Potion> INTERESTING_HARM = registerPotion("frontiers.interesting_harm", () -> new Potion());

    private static Holder<Potion> registerPotion(String id, Supplier<Potion> type)
    {
        return VectorLib.REGISTRY.registerHolder(Frontiers.MOD_ID, id, BuiltInRegistries.POTION, type);
    }

    // Registers mod potions. Just sends a log message.
    public static void registerPotions()
    {
        //Frontiers.LOGGER.info("Registering Mod Potions for " + Frontiers.MOD_ID);
    }
}