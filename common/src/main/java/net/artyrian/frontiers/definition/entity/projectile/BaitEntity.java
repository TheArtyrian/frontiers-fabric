package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class BaitEntity extends ThrowableItemProjectile
{
    public BaitEntity(EntityType<? extends BaitEntity> entityType, Level world) { super(entityType, world); }
    public BaitEntity(Level world, LivingEntity owner) {
        super(ModEntity.BAIT.get(), owner, world);
    }
    public BaitEntity(Level world, double x, double y, double z) { super(ModEntity.BAIT.get(), x, y, z, world); }

    @Override
    protected Item getDefaultItem()
    {
        return ModItem.BAIT.get();
    }

    @Override
    public void handleEntityEvent(byte status)
    {
        if (status == EntityEvent.DEATH)
        {
            double times = 0.08;

            for (int i = 0; i < 8; i++)
            {
                this.level()
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );
            }
        }
        else if (status == EntityEvent.POOF)
        {
            double times = 0.08;

            for (int i = 0; i < 8; i++)
            {
                double x = this.getX() + (this.random.nextDouble() * this.random.nextIntBetweenInclusive(-1, 1));
                double y = this.getY() + 0.5;
                double z = this.getZ() + (this.random.nextDouble() * this.random.nextIntBetweenInclusive(-1, 1));
                this.level()
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                                x,
                                y,
                                z,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );

                x = this.getX() + (this.random.nextDouble() * this.random.nextIntBetweenInclusive(-1, 1));
                z = this.getZ() + (this.random.nextDouble() * this.random.nextIntBetweenInclusive(-1, 1));
                this.level()
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
    protected void onHit(HitResult hitResult)
    {
        super.onHit(hitResult);
        if (!this.level().isClientSide)
        {
            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
            this.discard();
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.isInWater())
        {
            if (!this.level().isClientSide)
            {
                this.level().broadcastEntityEvent(this, EntityEvent.POOF);
                this.discard();
            }
        }
    }
}
