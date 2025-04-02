package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BouncyArrowEntity extends PersistentProjectileEntity
{
    private int deflectCount = 1;

    private static final ProjectileDeflection RICOCHET = (projectile, hitEntity, random) -> {
        float f = 180.0F;
        projectile.setVelocity(projectile.getVelocity().multiply(-2.0));
        projectile.setYaw(projectile.getYaw() * f);
        projectile.prevYaw += f;
        projectile.velocityDirty = true;
    };

    public BouncyArrowEntity(EntityType<? extends BouncyArrowEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public BouncyArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.BOUNCY_ARROW, owner, world, stack, shotFrom);
    }

    public BouncyArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.BOUNCY_ARROW, x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
        //if (this.getWorld().isClient && !this.inGround) {
        //    this.getWorld().addParticle(ParticleTypes.PORTAL, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        //}
    }

    @Override
    protected void onHit(LivingEntity target)
    {
        super.onHit(target);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        super.onBlockHit(blockHitResult);
        if (this.deflectCount > 0)
        {
            this.deflectCount--;
            RICOCHET.deflect(this, this.getOwner(), this.random);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Deflects")) {
            this.deflectCount = nbt.getInt("Deflects");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Deflects", this.deflectCount);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItem.BOUNCY_ARROW);
    }

    @Override
    public void setVelocity(Vec3d velocity)
    {

        super.setVelocity(velocity);
    }
}

