package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BaitEntity extends ThrownItemEntity
{
    public BaitEntity(EntityType<? extends BaitEntity> entityType, World world) { super(entityType, world); }
    public BaitEntity(World world, LivingEntity owner) {
        super(ModEntity.BAIT, owner, world);
    }
    public BaitEntity(World world, double x, double y, double z) { super(ModEntity.BAIT, x, y, z, world); }

    @Override
    protected Item getDefaultItem()
    {
        return ModItem.BAIT;
    }

    @Override
    public void handleStatus(byte status)
    {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES)
        {
            double times = 0.08;

            for (int i = 0; i < 8; i++)
            {
                this.getWorld()
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );
            }
        }
        else if (status == EntityStatuses.ADD_DEATH_PARTICLES)
        {
            double times = 0.08;

            for (int i = 0; i < 8; i++)
            {
                double x = this.getX() + (this.random.nextDouble() * this.random.nextBetween(-1, 1));
                double y = this.getY() + 0.5;
                double z = this.getZ() + (this.random.nextDouble() * this.random.nextBetween(-1, 1));
                this.getWorld()
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
                                x,
                                y,
                                z,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );

                x = this.getX() + (this.random.nextDouble() * this.random.nextBetween(-1, 1));
                z = this.getZ() + (this.random.nextDouble() * this.random.nextBetween(-1, 1));
                this.getWorld()
                        .addParticle(
                                ParticleTypes.SPLASH,
                                x,
                                y,
                                z,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );
            }
        }
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient)
        {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.isTouchingWater())
        {
            if (!this.getWorld().isClient)
            {
                this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_DEATH_PARTICLES);
                this.discard();
            }
        }
    }
}
