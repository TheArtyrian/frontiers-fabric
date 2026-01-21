package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModArmorBonus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class SubzeroArrowEntity extends AbstractArrow
{
    public SubzeroArrowEntity(EntityType<? extends SubzeroArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public SubzeroArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.SUBZERO_ARROW.get(), owner, world, stack, shotFrom);
    }

    public SubzeroArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.SUBZERO_ARROW.get(), x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target)
    {
        super.doPostHurtEffects(target);
        int time = 1000;
        boolean wearing_frostite = ModArmorBonus.wearingSetOf(target, ModArmorBonus.FROSTITE);

        if (this.getOwner() instanceof Stray) time = 200;
        if (!wearing_frostite) target.setTicksFrozen(target.getTicksRequiredToFreeze() + time);
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
        return new ItemStack(ModItem.SUBZERO_ARROW.get());
    }
}

