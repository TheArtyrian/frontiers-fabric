package net.artyrian.frontiers.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class WitherFaceParticle extends SpriteBillboardParticle
{
    private SpriteProvider spriteProvider;

    public WitherFaceParticle(ClientWorld clientWorld, double x, double y, double z,
                                 SpriteProvider provider, double xSpeed, double ySpeed, double zSpeed)
    {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.maxAge = 14;
        this.setSpriteForAge(provider);
        this.scale = 0.8F;
        this.velocityX = xSpeed;
        this.velocityY = ySpeed;
        this.velocityZ = zSpeed;

        this.spriteProvider = provider;

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public ParticleTextureSheet getType()
    {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    public static class Factory implements ParticleFactory<SimpleParticleType>
    {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider)
        {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
        {
            return new WitherFaceParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
