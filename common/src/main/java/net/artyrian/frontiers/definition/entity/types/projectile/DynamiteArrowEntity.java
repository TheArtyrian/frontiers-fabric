package net.artyrian.frontiers.definition.entity.types.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class DynamiteArrowEntity extends AbstractArrow
{
    public DynamiteArrowEntity(EntityType<? extends DynamiteArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public DynamiteArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.DYNAMITE_ARROW.get(), owner, world, stack, shotFrom);
    }

    public DynamiteArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.DYNAMITE_ARROW.get(), x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target)
    {
        super.doPostHurtEffects(target);
        if (!this.level().isClientSide)
        {
            this.level().explode(
                    null,
                    Explosion.getDefaultDamageSource(this.level(), this),
                    null,
                    this.blockPosition().getX(),
                    this.blockPosition().getY(),
                    this.blockPosition().getZ(),
                    1.0F,
                    this.isOnFire(),
                    Level.ExplosionInteraction.TNT
            );
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        super.onHitBlock(blockHitResult);
        if (!this.level().isClientSide)
        {
            this.level().explode(
                    null,
                    Explosion.getDefaultDamageSource(this.level(), this),
                    null,
                    this.blockPosition().getX(),
                    this.blockPosition().getY(),
                    this.blockPosition().getZ(),
                    1.0F,
                    this.isOnFire(),
                    Level.ExplosionInteraction.TNT
            );
        }
        this.discard();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItem.DYNAMITE_ARROW.get());
    }
}
