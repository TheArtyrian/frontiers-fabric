package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class BouncyArrowEntity extends AbstractArrow
{
    private int deflectCount = 2;

    public BouncyArrowEntity(EntityType<? extends BouncyArrowEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public BouncyArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.BOUNCY_ARROW.get(), owner, world, stack, shotFrom);
    }

    public BouncyArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom)
    {
        super(ModEntity.BOUNCY_ARROW.get(), x, y, z, world, stack, shotFrom);
    }

    @Override
    public void tick()
    {
        super.tick();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult)
    {
        if (this.deflectCount > 0)
        {
            // SPECIAL THANKS TO YIRMIRI FOR HER HELP GO CHECK OUT DUNGEON'S DELIGHT!!!!
            Vec3 reflected = new Vec3(getDeltaMovement().toVector3f().reflect(blockHitResult.getDirection().step())).scale(0.5F);
            setDeltaMovement(reflected);
            this.setPosRaw(this.getX() + reflected.x, this.getY() + reflected.y, this.getZ() + reflected.z);
            this.hasImpulse = true;
            //if (!getWorld().isClient)
            //{
            //    ((ServerWorld) this.getWorld()).getChunkManager().sendToNearbyPlayers(this, new EntityVelocityUpdateS2CPacket(this.getId(), getVelocity()));
            //}
            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 2.4F / (this.random.nextFloat() * 0.2F + 0.9F));

            double dmg = this.getBaseDamage();
            this.setBaseDamage(dmg + (dmg * 0.5));

            if (this.level().isClientSide && !this.inGround)
            {
                for (int i = 0; i < 5; i++)
                {
                    this.level().addParticle(ParticleTypes.ITEM_SLIME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
                }
            }

            BlockState blockState = this.level().getBlockState(blockHitResult.getBlockPos());
            blockState.onProjectileHit(this.level(), blockState, blockHitResult, this);
            this.deflectCount--;
        }
        else super.onHitBlock(blockHitResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getDeltaMovement().length();
        double d = this.getBaseDamage();
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.damageSources().arrow(this, entity2 != null ? entity2 : this);

        if (this.getWeaponItem() != null && this.level() instanceof ServerLevel serverWorld)
        {
            d = EnchantmentHelper.modifyDamage(serverWorld, this.getWeaponItem(), entity, damageSource, (float)d);
        }

        int i = Mth.ceil(Mth.clamp((double)f * d, 0.0, 2.147483647E9));

        if (this.isCritArrow())
        {
            long l = this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(l + (long)i, 2147483647L);
        }

        if (entity2 instanceof LivingEntity livingEntity)
        {
            livingEntity.setLastHurtMob(entity);
        }

        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getRemainingFireTicks();
        if (this.isOnFire() && !bl)
        {
            entity.igniteForSeconds(5.0F);
        }

        if (entity.hurt(damageSource, (float)i))
        {
            if (bl)
            {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2)
            {
                this.doKnockback(livingEntity2, damageSource);
                if (this.level() instanceof ServerLevel serverWorld2)
                {
                    EnchantmentHelper.doPostAttackEffectsWithItemSource(serverWorld2, livingEntity2, damageSource, this.getWeaponItem());
                }

                this.doPostHurtEffects(livingEntity2);
                if (livingEntity2 != entity2 && livingEntity2 instanceof Player && entity2 instanceof ServerPlayer && !this.isSilent())
                {
                    ((ServerPlayer)entity2)
                            .connection
                            .send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, ClientboundGameEventPacket.DEMO_PARAM_INTRO));
                }

                if (!this.level().isClientSide && entity2 instanceof ServerPlayer serverPlayerEntity)
                {
                    if (!entity.isAlive() && this.shotFromCrossbow())
                    {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, Arrays.asList(entity));
                    }
                }
            }

            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));

            if (this.deflectCount > 0) this.deflectCount--;
            this.setDeltaMovement(this.getDeltaMovement().multiply(-0.1, -0.1, -0.1));
        }
        else
        {
            entity.setRemainingFireTicks(j);
            this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
            this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7)
            {
                if (this.pickup == Pickup.ALLOWED) this.spawnAtLocation(this.getPickupItem(), 0.1F);
                this.discard();
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Deflects")) {
            this.deflectCount = nbt.getInt("Deflects");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Deflects", this.deflectCount);
    }

    @Override
    protected ItemStack getDefaultPickupItem() { return new ItemStack(ModItem.BOUNCY_ARROW.get()); }
}

