package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FruitcakeEntity extends ThrowableItemProjectile
{
    public FruitcakeEntity(EntityType<? extends FruitcakeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public FruitcakeEntity(Level world, LivingEntity owner) {
        super(ModEntity.FRUITCAKE.get(), owner, world);
    }

    public FruitcakeEntity(Level world, double x, double y, double z) { super(ModEntity.FRUITCAKE.get(), x, y, z, world); }

    @Override
    protected Item getDefaultItem() {
        return ModItem.FRUITCAKE_SLICE.get();
    }

    @Override
    public void handleEntityEvent(byte status)
    {
        if (status == EntityEvent.DEATH)
        {
            double d = 0.08;

            for (int i = 0; i < 8; i++)
            {
                this.level()
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * 0.08,
                                ((double)this.random.nextFloat() - 0.5) * 0.08,
                                ((double)this.random.nextFloat() - 0.5) * 0.08
                        );
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        int i = 5;
        FoodProperties fd = this.getItem().get(DataComponents.FOOD);
        if (fd != null)
        {
            i = fd.nutrition();
        }

        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }

    @Override
    protected void onHit(HitResult hitResult)
    {
        super.onHit(hitResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
            this.discard();
        }
    }
}
