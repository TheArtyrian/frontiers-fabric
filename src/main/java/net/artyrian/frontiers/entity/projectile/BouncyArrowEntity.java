package net.artyrian.frontiers.entity.projectile;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class BouncyArrowEntity extends PersistentProjectileEntity
{
    private int deflectCount = 2;

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
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        if (this.deflectCount > 0)
        {
            // SPECIAL THANKS TO YIRMIRI FOR HER HELP GO CHECK OUT DUNGEON'S DELIGHT!!!!
            Vec3d reflected = new Vec3d(getVelocity().toVector3f().reflect(blockHitResult.getSide().getUnitVector())).multiply(0.5F);
            setVelocity(reflected);
            this.setPos(this.getX() + reflected.x, this.getY() + reflected.y, this.getZ() + reflected.z);
            this.velocityDirty = true;
            //if (!getWorld().isClient)
            //{
            //    ((ServerWorld) this.getWorld()).getChunkManager().sendToNearbyPlayers(this, new EntityVelocityUpdateS2CPacket(this.getId(), getVelocity()));
            //}
            this.playSound(this.getSound(), 1.0F, 2.4F / (this.random.nextFloat() * 0.2F + 0.9F));

            double dmg = this.getDamage();
            this.setDamage(dmg + (dmg * 0.5));

            BlockState blockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
            blockState.onProjectileHit(this.getWorld(), blockState, blockHitResult, this);
            this.deflectCount--;
        }
        else super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult)
    {
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getVelocity().length();
        double d = this.getDamage();
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().arrow(this, entity2 != null ? entity2 : this);

        if (this.getWeaponStack() != null && this.getWorld() instanceof ServerWorld serverWorld)
        {
            d = EnchantmentHelper.getDamage(serverWorld, this.getWeaponStack(), entity, damageSource, (float)d);
        }

        int i = MathHelper.ceil(MathHelper.clamp((double)f * d, 0.0, 2.147483647E9));

        if (this.isCritical())
        {
            long l = this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(l + (long)i, 2147483647L);
        }

        if (entity2 instanceof LivingEntity livingEntity)
        {
            livingEntity.onAttacking(entity);
        }

        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTicks();
        if (this.isOnFire() && !bl)
        {
            entity.setOnFireFor(5.0F);
        }

        if (entity.damage(damageSource, (float)i))
        {
            if (bl)
            {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2)
            {
                this.knockback(livingEntity2, damageSource);
                if (this.getWorld() instanceof ServerWorld serverWorld2)
                {
                    EnchantmentHelper.onTargetDamaged(serverWorld2, livingEntity2, damageSource, this.getWeaponStack());
                }

                this.onHit(livingEntity2);
                if (livingEntity2 != entity2 && livingEntity2 instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent())
                {
                    ((ServerPlayerEntity)entity2)
                            .networkHandler
                            .sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, GameStateChangeS2CPacket.DEMO_OPEN_SCREEN));
                }

                if (!this.getWorld().isClient && entity2 instanceof ServerPlayerEntity serverPlayerEntity)
                {
                    if (!entity.isAlive() && this.isShotFromCrossbow())
                    {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, Arrays.asList(entity));
                    }
                }
            }

            this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));

            if (this.deflectCount > 0) this.deflectCount--;
            this.setVelocity(this.getVelocity().multiply(-0.1, -0.1, -0.1));
        }
        else
        {
            entity.setFireTicks(j);
            this.deflect(ProjectileDeflection.SIMPLE, entity, this.getOwner(), false);
            this.setVelocity(this.getVelocity().multiply(0.2));
            if (!this.getWorld().isClient && this.getVelocity().lengthSquared() < 1.0E-7)
            {
                if (this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) this.dropStack(this.asItemStack(), 0.1F);
                this.discard();
            }
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
    protected ItemStack getDefaultItemStack() { return new ItemStack(ModItem.BOUNCY_ARROW); }
}

