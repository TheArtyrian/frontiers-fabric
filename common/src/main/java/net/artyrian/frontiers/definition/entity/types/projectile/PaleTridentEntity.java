package net.artyrian.frontiers.definition.entity.types.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class PaleTridentEntity extends AbstractArrow
{
    private static final EntityDataAccessor<Byte> LOYALTY = SynchedEntityData.defineId(PaleTridentEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ENCHANTED = SynchedEntityData.defineId(PaleTridentEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean dealtDamage;
    public int returnTimer;

    public PaleTridentEntity(EntityType<? extends PaleTridentEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public PaleTridentEntity(Level world, LivingEntity owner, ItemStack stack)
    {
        super(ModEntity.PALE_TRIDENT.get(), owner, world, stack, null);
        this.entityData.set(LOYALTY, this.getLoyalty(stack));
        this.entityData.set(ENCHANTED, stack.hasFoil());
    }

    public PaleTridentEntity(Level world, double x, double y, double z, ItemStack stack)
    {
        super(ModEntity.PALE_TRIDENT.get(), x, y, z, world, stack, stack);
        this.entityData.set(LOYALTY, this.getLoyalty(stack));
        this.entityData.set(ENCHANTED, stack.hasFoil());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(LOYALTY, (byte)0);
        builder.define(ENCHANTED, false);
    }

    @Override
    public void tick()
    {
        if (this.inGroundTime > 4)
        {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = this.entityData.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null)
        {
            if (!this.isOwnerAlive())
            {
                if (!this.level().isClientSide && this.pickup == Pickup.ALLOWED)
                {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
            else
            {
                this.setNoPhysics(true);
                Vec3 vec3d = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3d.y * 0.015 * (double)i, this.getZ());
                if (this.level().isClientSide)
                {
                    this.yOld = this.getY();
                }

                double d = 0.05 * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3d.normalize().scale(d)));
                if (this.returnTimer == 0)
                {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                this.returnTimer++;
            }
        }

        super.tick();
    }

    private boolean isOwnerAlive()
    {
        Entity entity = this.getOwner();
        return entity == null || !entity.isAlive() ? false : !(entity instanceof ServerPlayer) || !entity.isSpectator();
    }

    public boolean isEnchanted() {
        return this.entityData.get(ENCHANTED);
    }

    @Nullable
    @Override
    protected EntityHitResult findHitEntity(Vec3 currentPosition, Vec3 nextPosition)
    {
        return this.dealtDamage ? null : super.findHitEntity(currentPosition, nextPosition);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        Entity entity = entityHitResult.getEntity();
        float f = 10.0F;
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, (Entity)(entity2 == null ? this : entity2));
        if (this.level() instanceof ServerLevel serverWorld)
        {
            f = EnchantmentHelper.modifyDamage(serverWorld, this.getWeaponItem(), entity, damageSource, f);
        }

        this.dealtDamage = true;
        if (entity.hurt(damageSource, f))
        {
            if (entity.getType() == EntityType.ENDERMAN)
            {
                return;
            }

            if (this.level() instanceof ServerLevel serverWorld)
            {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverWorld, entity, damageSource, this.getWeaponItem());
            }

            if (entity instanceof LivingEntity livingEntity)
            {
                this.doKnockback(livingEntity, damageSource);
                this.doPostHurtEffects(livingEntity);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @Override
    protected void hitBlockEnchantmentEffects(ServerLevel world, BlockHitResult blockHitResult, ItemStack weaponStack)
    {
        Vec3 vec3d = blockHitResult.getBlockPos().clampLocationWithin(blockHitResult.getLocation());
        EnchantmentHelper.onHitBlock(
                world,
                weaponStack,
                this.getOwner() instanceof LivingEntity livingEntity ? livingEntity : null,
                this,
                null,
                vec3d,
                world.getBlockState(blockHitResult.getBlockPos()),
                item -> this.kill()
        );
    }

    @Override
    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    @Override
    protected boolean tryPickup(Player player)
    {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItem.PALE_TRIDENT.get());
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    public void playerTouch(Player player)
    {
        if (this.ownedBy(player) || this.getOwner() == null)
        {
            super.playerTouch(player);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.entityData.set(LOYALTY, this.getLoyalty(this.getPickupItemStackOrigin()));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    private byte getLoyalty(ItemStack stack)
    {
        return this.level() instanceof ServerLevel serverWorld
                ? (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverWorld, stack, this), 0, 127)
                : 0;
    }

    @Override
    public void tickDespawn()
    {
        int i = this.entityData.get(LOYALTY);
        if (this.pickup != Pickup.ALLOWED || i <= 0)
        {
            super.tickDespawn();
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}
