package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SubzeroArrowEntity extends PersistentProjectileEntity
{
    public SubzeroArrowEntity(EntityType<? extends SubzeroArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public SubzeroArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.SUBZERO_ARROW, owner, world, stack, shotFrom);
    }

    public SubzeroArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.SUBZERO_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void onHit(LivingEntity target)
    {
        super.onHit(target);
        target.setFrozenTicks(target.getMinFreezeDamageTicks() + 1000);
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
        return new ItemStack(ModItem.SUBZERO_ARROW);
    }
}

