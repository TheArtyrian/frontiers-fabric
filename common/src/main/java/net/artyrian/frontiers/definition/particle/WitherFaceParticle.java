package net.artyrian.frontiers.definition.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class WitherFaceParticle extends TextureSheetParticle
{
    private SpriteSet spriteProvider;

    public WitherFaceParticle(ClientLevel clientWorld, double x, double y, double z,
                                 SpriteSet provider, double xSpeed, double ySpeed, double zSpeed)
    {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.lifetime = 14;
        this.setSpriteFromAge(provider);
        this.quadSize = 0.8F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        this.spriteProvider = provider;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.setSpriteFromAge(this.spriteProvider);
    }

    public static class Factory implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider)
        {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ)
        {
            return new WitherFaceParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
