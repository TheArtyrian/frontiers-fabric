package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FastColor;
import net.minecraft.world.effect.MobEffects;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModParticle
{
    // Common particle effect types for specific actions.
    public static final ColorParticleOption WITHER_PARTICLE = ColorParticleOption.create(
            ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, MobEffects.WITHER.value().getColor()));
    public static final ColorParticleOption BLACK_PARTICLE = ColorParticleOption.create(
            ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(222, CommonColors.BLACK));
    public static final ColorParticleOption VEX_CHARGE_PARTICLE_LR = ColorParticleOption.create(
            ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(222, CommonColors.SOFT_RED));
    public static final ColorParticleOption VEX_CHARGE_PARTICLE_R = ColorParticleOption.create(
            ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(200, CommonColors.RED));

    // Custom particles
    public static final Supplier<SimpleParticleType> WITHER_FACE =
            registerParticleSimple("wither_face");
    public static final Supplier<SimpleParticleType> CRAG_SMOG =
            registerParticleSimple("crag_smog");
    public static final Supplier<SimpleParticleType> VEX_FLAME =
            registerParticleSimple("vex_flame");

    // Register custom particles
    private static Supplier<SimpleParticleType> registerParticleSimple(String name)
    {
        return VectorLib.REGISTRY.registerParticleType(Frontiers.MOD_ID, name);
    }

    public static void registerParticles()
    {

    }
}