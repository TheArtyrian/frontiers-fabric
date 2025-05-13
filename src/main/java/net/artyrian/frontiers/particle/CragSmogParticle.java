package net.artyrian.frontiers.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class CragSmogParticle extends SpriteBillboardParticle
{
    private SpriteProvider spriteProvider;

    private final double floorVelocityX;
    private final double floorVelocityZ;
    private final float maxScale;

    public CragSmogParticle(ClientWorld clientWorld, double x, double y, double z,
                              SpriteProvider provider, double xSpeed, double ySpeed, double zSpeed)
    {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.maxAge = 60 + this.random.nextInt(12);
        this.setSpriteForAge(provider);

        this.maxScale = 0.2F + (0.05F * this.random.nextBetween(-2, 3));
        this.scale = 0.0F;

        this.gravityStrength = (ySpeed > 0.0F) ? 0.25F : 0F;
        this.velocityX = xSpeed;
        this.velocityY = ySpeed;
        this.velocityZ = zSpeed;

        this.floorVelocityX = xSpeed / 2.0;
        this.floorVelocityZ = zSpeed / 2.0;

        this.spriteProvider = provider;

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    public int getBrightness(float tint)
    {
        int i = super.getBrightness(tint);
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    @Override
    public ParticleTextureSheet getType()
    {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);

        if (this.age > this.maxAge / 2 && this.scale > 0.0F)
        {
            this.scale -= 0.025F;
        }
        else
        {
            if (this.scale < this.maxScale )
            {
                this.scale += 0.025F;
            }
            else
            {
                this.scale = this.maxScale;
            }
        }

        if (this.onGround)
        {
            this.velocityX = this.floorVelocityX;
            this.velocityZ = this.floorVelocityZ;
        }
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
            return new CragSmogParticle(world, x, y, z, this.spriteProvider, velocityX, velocityY, velocityZ);
        }
    }
}
