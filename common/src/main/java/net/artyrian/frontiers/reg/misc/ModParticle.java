package net.artyrian.frontiers.reg.misc;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.particle.ColorExplodeParticle;
import net.artyrian.frontiers.definition.particle.options.ColorExplodeOptions;
import net.minecraft.core.particles.*;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FastColor;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModParticle
{
    // Common particle effect types for specific actions.
    public static final DustColorTransitionOptions BREWING_BLAZE =
            new DustColorTransitionOptions(Vec3.fromRGB24(0xFFA300).toVector3f(), Vec3.fromRGB24(0x2A1409).toVector3f(), 1.0F);
    public static final DustColorTransitionOptions GLOWING_OBSIDIAN =
            new DustColorTransitionOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, Vec3.fromRGB24(0x660E0E).toVector3f(), 1.0F);
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
    public static final Supplier<SimpleParticleType> VEX_FLAME_BIG =
            registerParticleSimple("vex_flame_big");
    public static final Supplier<SimpleParticleType> TOWER_FLAME =
            registerParticleSimple("tower_flame");
    public static final Supplier<SimpleParticleType> TOWER_FLAME_SMALL =
            registerParticleSimple("tower_flame_small");
    public static final Supplier<SimpleParticleType> WITHER_GLINT =
            registerParticleSimple("wither_glint");
    public static final Supplier<SimpleParticleType> SNOW_GLINT =
            registerParticleSimple("snow_glint");
    public static final Supplier<ParticleType<ColorExplodeOptions>> COLOR_POOF =
            registerAdvParticle("color_poof", (unimp) -> ColorExplodeOptions.CODEC, (unimp) -> ColorExplodeOptions.STREAM_CODEC);

    // Register custom particles
    private static <T extends ParticleOptions> Supplier<ParticleType<T>> registerAdvParticle(
            String name, Function<ParticleType<T>, MapCodec<T>> codec, Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamcodec
    )
    {
        return VectorLib.REGISTRY.registerParticleType(Frontiers.MOD_ID, name, codec, streamcodec);
    }

    private static Supplier<SimpleParticleType> registerParticleSimple(String name)
    {
        return VectorLib.REGISTRY.registerParticleType(Frontiers.MOD_ID, name);
    }

    public static void registerParticles()
    {

    }
}