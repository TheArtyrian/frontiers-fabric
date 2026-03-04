package net.artyrian.frontiers.definition.particle.options;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.particles.*;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class ColorExplodeOptions extends ScalableParticleOptionsBase
{
    private static final Vector3f TOWER_REG = Vec3.fromRGB24(0x4E3863).toVector3f();
    private static final Vector3f TOWER_ENRAGED = Vec3.fromRGB24(0x7D083A).toVector3f();
    private static final Vector3f WHITE = Vec3.fromRGB24(0xFFFFFF).toVector3f();

    public static final ColorExplodeOptions REG_BIG = bigSmoke(TOWER_REG);
    public static final ColorExplodeOptions REG_SMALL = lilSmoke(TOWER_REG);
    public static final ColorExplodeOptions ENRAGED_BIG = bigSmoke(TOWER_ENRAGED);
    public static final ColorExplodeOptions ENRAGED_SMALL = lilSmoke(TOWER_ENRAGED);

    public static final MapCodec<ColorExplodeOptions> CODEC = RecordCodecBuilder.mapCodec((inst) -> inst.group(
            ExtraCodecs.VECTOR3F.fieldOf("from_color").forGetter((opt) -> opt.fromColor),
            ExtraCodecs.VECTOR3F.fieldOf("to_color").forGetter((opt) -> opt.toColor),
            SCALE.fieldOf("scale").forGetter(ScalableParticleOptionsBase::getScale))
            .apply(inst, ColorExplodeOptions::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, ColorExplodeOptions> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VECTOR3F, (col) -> col.fromColor,
            ByteBufCodecs.VECTOR3F, (col) -> col.toColor,
            ByteBufCodecs.FLOAT, ScalableParticleOptionsBase::getScale,
            ColorExplodeOptions::new
    );

    private final Vector3f fromColor;
    private final Vector3f toColor;

    ColorExplodeOptions(Vector3f color, Vector3f toColor, float scale)
    {
        super(scale);
        this.fromColor = color;
        this.toColor = toColor;
    }

    public Vector3f getFromColor() { return this.fromColor; }
    public Vector3f getToColor() { return this.toColor; }
    public ParticleType<ColorExplodeOptions> getType() { return ModParticle.COLOR_POOF.get(); }

    private static ColorExplodeOptions bigSmoke(Vector3f col) { return new ColorExplodeOptions(col, WHITE, 1.0F); }
    private static ColorExplodeOptions lilSmoke(Vector3f col) { return new ColorExplodeOptions(col, WHITE, 0.4F); }
}
