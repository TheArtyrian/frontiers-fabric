package net.artyrian.frontiers.definition.entity.types.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PrismarineArrowEntity extends AbstractArrow
{
    public PrismarineArrowEntity(EntityType<? extends PrismarineArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public PrismarineArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.PRISMARINE_ARROW.get(), owner, world, stack, shotFrom);
    }

    public PrismarineArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.PRISMARINE_ARROW.get(), x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.BUBBLE_POP, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() { return new ItemStack(ModItem.PRISMARINE_ARROW.get()); }

    @Override
    protected float getWaterInertia()
    {
        return 0.9F;
    }
}
