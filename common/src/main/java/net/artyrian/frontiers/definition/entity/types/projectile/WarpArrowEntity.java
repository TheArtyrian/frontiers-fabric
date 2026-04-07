package net.artyrian.frontiers.definition.entity.types.projectile;

import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.property.FRDamageType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class WarpArrowEntity extends AbstractArrow
{
    public WarpArrowEntity(EntityType<? extends WarpArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public WarpArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(FREntity.WARP_ARROW.get(), owner, world, stack, shotFrom);
    }

    public WarpArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(FREntity.WARP_ARROW.get(), x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target)
    {
        super.doPostHurtEffects(target);
        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity entity)
        {
            for (int i = 0; i < 32; i++) {
                this.level()
                        .addParticle(
                                ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian()
                        );
            }

            if (!entity.level().isClientSide)
            {
                this.playTeleportSound(entity.level(), this.position());
                entity.changeDimension(new DimensionTransition((ServerLevel) entity.level(), this.position(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING));
                entity.resetFallDistance();
                entity.hurt(FRDamageType.of(this.level(), FRDamageType.ENDER_PEARL_WARP), 5.0F);
            }
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        super.onHitBlock(blockHitResult);
        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity entity)
        {
            for (int i = 0; i < 32; i++) {
                this.level()
                        .addParticle(
                                ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian()
                        );
            }

            if (!entity.level().isClientSide)
            {
                this.playTeleportSound(entity.level(), this.position());
                entity.changeDimension(new DimensionTransition((ServerLevel) entity.level(), this.position(), entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.DO_NOTHING));
                entity.resetFallDistance();
                entity.hurt(FRDamageType.of(this.level(), FRDamageType.ENDER_PEARL_WARP), 5.0F);
            }
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
        return new ItemStack(FRItems.WARP_ARROW.get());
    }

    private void playTeleportSound(Level world, Vec3 pos) {
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
    }
}

