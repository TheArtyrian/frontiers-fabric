package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PrismarineArrowEntity extends PersistentProjectileEntity
{
    public PrismarineArrowEntity(EntityType<? extends PrismarineArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public PrismarineArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.PRISMARINE_ARROW, owner, world, stack, shotFrom);
    }

    public PrismarineArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.PRISMARINE_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.BUBBLE_POP, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() { return new ItemStack(ModItem.PRISMARINE_ARROW); }

    @Override
    protected float getDragInWater()
    {
        return 0.9F;
    }
}
