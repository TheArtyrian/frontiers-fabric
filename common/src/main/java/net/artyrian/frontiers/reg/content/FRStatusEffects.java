package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.effect.PublicStatusEffect;
import net.artyrian.frontiers.definition.effect.QuickFlightEffect;
import net.artyrian.frontiers.definition.effect.StormPoisonEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FRStatusEffects
{
    public static final Holder<MobEffect> QUICK_FLIGHT = register("quick_flight", () -> new QuickFlightEffect(MobEffectCategory.HARMFUL, 0xFF2482));
    public static final Holder<MobEffect> STORM_POISONING = register("storm_poisoning", () -> new StormPoisonEffect(MobEffectCategory.HARMFUL, 0x6A375C));
    public static final Holder<MobEffect> MAGMA_VISION = register("magma_vision", () -> new PublicStatusEffect(MobEffectCategory.HARMFUL, 0xEA5B15));
    public static final Holder<MobEffect> ALLUREMENT = register("allurement", () -> new PublicStatusEffect(MobEffectCategory.BENEFICIAL, 0xA5D22D));
    public static final Holder<MobEffect> WELL_RESTED = register("well_rested", () -> new PublicStatusEffect(MobEffectCategory.BENEFICIAL, 0x92BDC0));

    private static Holder<MobEffect> register(String id, Supplier<MobEffect> statusEffect)
    {
        return VectorLib.REGISTRY.registerHolder(Frontiers.MOD_ID, id, BuiltInRegistries.MOB_EFFECT, statusEffect);
    }

    public static void registerEffects()
    {

    }
}
