package net.artyrian.frontiers.definition.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class CragSmogParticle extends TextureSheetParticle
{
    private SpriteSet spriteProvider;

    private final double floorVelocityX;
    private final double floorVelocityZ;
    private final float maxScale;

    public CragSmogParticle(ClientLevel clientWorld, double x, double y, double z,
                              SpriteSet provider, double xSpeed, double ySpeed, double zSpeed)
    {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(provider);

        this.maxScale = 0.2F + (0.05F * this.random.nextIntBetweenInclusive(-2, 3));
        this.quadSize = 0.0F;

        this.gravity = (ySpeed > 0.0F) ? 0.25F : 0F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        this.floorVelocityX = xSpeed / 2.0;
        this.floorVelocityZ = zSpeed / 2.0;

        this.spriteProvider = provider;

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    public int getLightColor(float tint)
    {
        int i = super.getLightColor(tint);
        int k = i >> 16 & 255;
        return 240 | k << 16;
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

        if (this.age > this.lifetime / 2 && this.quadSize > 0.0F)
        {
            this.quadSize -= 0.025F;
        }
        else
        {
            if (this.quadSize < this.maxScale )
            {
                this.quadSize += 0.025F;
            }
            else
            {
                this.quadSize = this.maxScale;
            }
        }

        if (this.onGround)
        {
            this.xd = this.floorVelocityX;
            this.zd = this.floorVelocityZ;
        }
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
            return new CragSmogParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
