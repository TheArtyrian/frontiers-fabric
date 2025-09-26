package net.artyrian.frontiers.entity.mob;

import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

public class JungleSpiderEntity extends SpiderEntity
{
    private static final BlockStateParticleEffect COBWEB_PARTICLES = new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.COBWEB.getDefaultState());
    public int webTime = this.random.nextInt(600) + 600;

    public JungleSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world)
    {
        super(entityType, world);
        this.experiencePoints = 10;
    }

    public static DefaultAttributeContainer.Builder createAttr()
    {
        return SpiderEntity.createSpiderAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0F);
    }

    @Override
    protected void initGoals()
    {
        super.initGoals();
        this.targetSelector.add(3, new JungleSpiderEntity.TargetGoalUnrelent<>(this, ParrotEntity.class));
        this.targetSelector.add(3, new JungleSpiderEntity.TargetGoalUnrelent<>(this, ChickenEntity.class));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("WebTime"))
        {
            this.webTime = nbt.getInt("WebTime");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("WebTime", this.webTime);
    }

    @Override
    public boolean tryAttack(Entity target)
    {
        boolean doAtk = super.tryAttack(target);
        if (doAtk)
        {
            if (target instanceof LivingEntity)
            {
                int i = 0;
                if (this.getWorld().getDifficulty() == Difficulty.NORMAL) i = 7;
                else if (this.getWorld().getDifficulty() == Difficulty.HARD) i = 15;

                boolean hardmode = false;
                if (this.getWorld() instanceof ServerWorld world)
                {
                    MinecraftServer server = world.getServer();
                    StateSaveLoad loader = StateSaveLoad.getServerState(server);
                    hardmode = loader.isInHardmode;
                }

                if (i > 0 && hardmode)
                {
                    ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, i * 20, 0), this);
                }
            }
        }
        return doAtk;
    }

    @Override
    public void tickMovement()
    {
        super.tickMovement();
        if (!this.getWorld().isClient && this.isAlive() && --this.webTime <= 0)
        {
            if (this.getBlockStateAtPos().isAir())
            {
                this.playSound(SoundEvents.BLOCK_COBWEB_PLACE, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                ((ServerWorld)this.getWorld()).spawnParticles(
                        COBWEB_PARTICLES,
                        this.getBlockX() + 0.5,
                        this.getBlockY() + 0.5,
                        this.getBlockZ() + 0.5,
                        30,
                        0.4,
                        0.4,
                        0.4,
                        0.7
                );
                this.getWorld().setBlockState(this.getBlockPos(), Blocks.COBWEB.getDefaultState());
                this.emitGameEvent(GameEvent.ENTITY_PLACE);
                this.webTime = this.random.nextInt(600) + 600;
            }
            else
            {
                this.webTime = this.random.nextInt(300) + 300;
            }
        }
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
    {
        return entityData;
    }

    @Override
    public Vec3d getVehicleAttachmentPos(Entity vehicle)
    {
        return vehicle.getWidth() <= this.getWidth() ? new Vec3d(0.0, 0.21875 * (double)this.getScale(), 0.0) : super.getVehicleAttachmentPos(vehicle);
    }

    @Override
    public boolean damage(DamageSource source, float amount)
    {
        boolean bl = super.damage(source, amount);
        if (this.getWorld().isClient)
        {
            return false;
        }
        else
        {
            if (amount >= 1.0F)
            {
                int removal = (int)(60.0F * amount);
                if (this.webTime - removal < 0) this.webTime = 1;
                else this.webTime -= removal;
            }

            return bl;
        }
    }

    public static boolean canSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random)
    {
        boolean baseValue = HostileEntity.canSpawnInDark(type, world, spawnReason, pos, random);
        if (baseValue)
        {
            boolean isAboveSea = pos.getY() >= world.getSeaLevel();
            boolean hardmode = false;
            MinecraftServer server = world.getServer();
            if (server != null)
            {
                StateSaveLoad loader = StateSaveLoad.getServerState(server);
                hardmode = loader.isInHardmode;
            }

            return (isAboveSea && hardmode);
        }
        return false;
    }

    static class TargetGoalUnrelent<T extends LivingEntity> extends ActiveTargetGoal<T>
    {
        public TargetGoalUnrelent(SpiderEntity spider, Class<T> targetEntityClass) { super(spider, targetEntityClass, true); }

        @Override
        public boolean canStart()
        {
            boolean start = super.canStart();
            if (start)
            {
                if (this.targetEntity != null && this.targetEntity instanceof TameableEntity tamedTarget) return !tamedTarget.isTamed();
            }
            return start;
        }

        @Override
        public boolean shouldContinue()
        {
            return super.shouldContinue();
        }
    }
}
