package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModDamageType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class WarpArrowEntity extends PersistentProjectileEntity
{
    public WarpArrowEntity(EntityType<? extends WarpArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public WarpArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.WARP_ARROW, owner, world, stack, shotFrom);
    }

    public WarpArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.WARP_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHit(LivingEntity target)
    {
        super.onHit(target);
        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity entity)
        {
            for (int i = 0; i < 32; i++) {
                this.getWorld()
                        .addParticle(
                                ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian()
                        );
            }

            if (!entity.getWorld().isClient)
            {
                this.playTeleportSound(entity.getWorld(), this.getPos());
                entity.teleportTo(new TeleportTarget((ServerWorld) entity.getWorld(), this.getPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP));
                entity.onLanding();
                entity.damage(ModDamageType.of(this.getWorld(), ModDamageType.ENDER_PEARL_WARP), 5.0F);
            }
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        super.onBlockHit(blockHitResult);
        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity entity)
        {
            for (int i = 0; i < 32; i++) {
                this.getWorld()
                        .addParticle(
                                ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0, this.getZ(), this.random.nextGaussian(), 0.0, this.random.nextGaussian()
                        );
            }

            if (!entity.getWorld().isClient)
            {
                this.playTeleportSound(entity.getWorld(), this.getPos());
                entity.teleportTo(new TeleportTarget((ServerWorld) entity.getWorld(), this.getPos(), entity.getVelocity(), entity.getYaw(), entity.getPitch(), TeleportTarget.NO_OP));
                entity.onLanding();
                entity.damage(ModDamageType.of(this.getWorld(), ModDamageType.ENDER_PEARL_WARP), 5.0F);
            }
        }
        this.discard();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItem.WARP_ARROW);
    }

    private void playTeleportSound(World world, Vec3d pos) {
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS);
    }
}

