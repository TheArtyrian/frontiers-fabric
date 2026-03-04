package net.artyrian.frontiers.definition.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.definition.particle.options.ColorExplodeOptions;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.DustColorTransitionOptions;
import org.joml.Vector3f;

public class ColorExplodeParticle extends ExplodeParticle
{
    private final Vector3f fromColor;
    private final Vector3f toColor;

    ColorExplodeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, ColorExplodeOptions options, SpriteSet sprites)
    {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        float f = this.random.nextFloat() * 0.3F + 0.7F;
        this.fromColor = this.randomColorV3F(options.getFromColor(), f);
        this.toColor = this.randomColorV3F(options.getToColor(), f);
        this.scale(options.getScale());
    }

    private Vector3f randomColorV3F(Vector3f vector, float multiplier)
    {
        return new Vector3f(
                this.randomizeColor(vector.x(), multiplier),
                this.randomizeColor(vector.y(), multiplier),
                this.randomizeColor(vector.z(), multiplier)
        );
    }

    protected float randomizeColor(float coordMultiplier, float multiplier)
    {
        return (this.random.nextFloat() * 0.2F + 0.8F) * coordMultiplier * multiplier;
    }

    private void lerpColors(float partialTick)
    {
        float f = ((float)this.age + partialTick) / ((float)this.lifetime + 1.0F);
        Vector3f vector3f = (new Vector3f(this.fromColor)).lerp(this.toColor, f);
        this.rCol = vector3f.x();
        this.gCol = vector3f.y();
        this.bCol = vector3f.z();
    }

    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks)
    {
        this.lerpColors(partialTicks);
        super.render(buffer, renderInfo, partialTicks);
    }

    public static class Builder implements ParticleProvider<ColorExplodeOptions>
    {
        private final SpriteSet sprites;

        public Builder(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(ColorExplodeOptions type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            return new ColorExplodeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, type, this.sprites);
        }
    }
}
