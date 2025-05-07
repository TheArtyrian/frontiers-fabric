package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.armor.ModArmorBonus;
import net.artyrian.frontiers.misc.ModDamageType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class DynamiteArrowEntity extends PersistentProjectileEntity
{
    public DynamiteArrowEntity(EntityType<? extends DynamiteArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public DynamiteArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.DYNAMITE_ARROW, owner, world, stack, shotFrom);
    }

    public DynamiteArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.DYNAMITE_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHit(LivingEntity target)
    {
        super.onHit(target);
        if (!this.getWorld().isClient)
        {
            this.getWorld().createExplosion(
                    null,
                    Explosion.createDamageSource(this.getWorld(), this),
                    null,
                    this.getBlockPos().getX(),
                    this.getBlockPos().getY(),
                    this.getBlockPos().getZ(),
                    1.0F,
                    false,
                    World.ExplosionSourceType.TNT
            );
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient)
        {
            this.getWorld().createExplosion(
                    null,
                    Explosion.createDamageSource(this.getWorld(), this),
                    null,
                    this.getBlockPos().getX(),
                    this.getBlockPos().getY(),
                    this.getBlockPos().getZ(),
                    1.0F,
                    false,
                    World.ExplosionSourceType.TNT
            );
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
        return new ItemStack(ModItem.DYNAMITE_ARROW);
    }
}
