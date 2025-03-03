package net.artyrian.frontiers.particle;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class ModParticle
{
    // Common particle effect types for specific actions.
    public static final EntityEffectParticleEffect WITHER_PARTICLE = EntityEffectParticleEffect.create(
            ParticleTypes.ENTITY_EFFECT, ColorHelper.Argb.withAlpha(255, StatusEffects.WITHER.value().getColor()));
    public static final EntityEffectParticleEffect BLACK_PARTICLE = EntityEffectParticleEffect.create(
            ParticleTypes.ENTITY_EFFECT, ColorHelper.Argb.withAlpha(222, Colors.BLACK));

    // Custom particles
    public static final SimpleParticleType WITHER_FACE =
            registerParticle("wither_face", FabricParticleTypes.simple());

    // Register custom particles
    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType)
    {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Frontiers.MOD_ID, name), particleType);
    }

    public static void registerParticles()
    {

    }
}