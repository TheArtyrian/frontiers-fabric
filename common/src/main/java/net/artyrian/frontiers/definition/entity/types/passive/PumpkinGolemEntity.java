package net.artyrian.frontiers.definition.entity.types.passive;

import net.artyrian.frontiers.definition.entity.ai.pumpkin_golem.PumpkinGolemPickGoal;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class PumpkinGolemEntity extends AbstractGolem
{
    public static final int MIN_STYLE = 0;
    public static final int MAX_STYLE = 6;

    private static final EntityDataAccessor<Integer> FACE_STYLE = SynchedEntityData.defineId(PumpkinGolemEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_ASLEEP = SynchedEntityData.defineId(PumpkinGolemEntity.class, EntityDataSerializers.BOOLEAN);

    private int pickTicksLeft;

    public PumpkinGolemEntity(EntityType<? extends AbstractGolem> entityType, Level world)
    {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PumpkinGolemAI.SwimUnlessHonkShoo(this));
        this.goalSelector.addGoal(2, new PumpkinGolemPickGoal(this, 1.2F, 24));
        this.goalSelector.addGoal(3, new PumpkinGolemAI.WanderGoal(this, 1.0, 1.0F));
        this.goalSelector.addGoal(3, new PumpkinGolemAI.LookAtGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new PumpkinGolemAI.LookAroundButAwesome(this));
    }

    @Override
    public void tick()
    {
        if (this.pickTicksLeft > 0) {
            this.pickTicksLeft--;
        }

        if (this.isAlive())
        {
            Level world = this.level();

            boolean isnight = world.isNight();
            boolean isasleep = !this.isGolemAsleep();

            if (isasleep != isnight && !world.isClientSide)
            {
                boolean trySwitch = world.getRandom().nextIntBetweenInclusive(0, 100) > 90;
                if (trySwitch)
                {
                    this.setGolemSleep(!isnight);
                }
            }
        }
        super.tick();
    }

    @Override
    protected boolean shouldStayCloseToLeashHolder() { return !this.isGolemAsleep(); }

    @Override
    public void handleEntityEvent(byte status)
    {
        if (status == EntityEvent.START_ATTACKING)
        {
            this.pickTicksLeft = 10;
            this.playSound(ModSounds.PUMPKIN_GOLEM_PICK.get(), 1.0F, 1.25F);
        }
        else
        {
            super.handleEntityEvent(status);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(FACE_STYLE, MIN_STYLE);
        builder.define(IS_ASLEEP, false);
    }

    @Nullable @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData)
    {
        int rand = world.getRandom().nextIntBetweenInclusive(MIN_STYLE, MAX_STYLE);
        this.setGolemStyle(rand);

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("GolemStyle", this.getGolemStyle());
        nbt.putBoolean("IsAsleep", this.isGolemAsleep());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        this.setGolemStyle(nbt.getInt("GolemStyle"));
        this.setGolemSleep(nbt.getBoolean("IsAsleep"));
    }

    @Override
    // Yes I know the tag `CAN_BREATHE_UNDER_WATER` exists, but hear me out - hardcoding is hilarious (the iron golem does it)
    protected int decreaseAirSupply(int air) { return air; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.PUMPKIN_GOLEM_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PUMPKIN_GOLEM_DEATH.get();
    }

    public boolean isGolemAsleep() { return this.entityData.get(IS_ASLEEP); }
    public void setGolemSleep(boolean sleeping) { this.entityData.set(IS_ASLEEP, sleeping); }
    public int getGolemStyle() { return this.entityData.get(FACE_STYLE); }
    public void setGolemStyle(int style) { this.entityData.set(FACE_STYLE, Math.clamp(style, MIN_STYLE, MAX_STYLE)); }
    public int getPickTicks() { return this.pickTicksLeft; }
    public void setPickTicks()
    {
        this.pickTicksLeft = 10;
        this.playSound(ModSounds.PUMPKIN_GOLEM_PICK.get(), 1.0F, 1.25F);
        this.level().broadcastEntityEvent(this, EntityEvent.START_ATTACKING);
    }

    public static AttributeSupplier.Builder createAttr()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    // Unique AIs for Pumpkin Golem
    public static class PumpkinGolemAI
    {
        static class LookAtGoal extends LookAtPlayerGoal
        {
            public LookAtGoal(Mob mob, Class<? extends LivingEntity> targetType, float range)
            {
                super(mob, targetType, range);
            }

            @Override
            public boolean canUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canContinueToUse();
            }
        }

        static class SwimUnlessHonkShoo extends FloatGoal
        {
            private final Mob mob;

            public SwimUnlessHonkShoo(Mob mob)
            {
                super(mob);
                this.mob = mob;
            }

            @Override
            public boolean canUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canContinueToUse();
            }
        }

        static class LookAroundButAwesome extends RandomLookAroundGoal
        {
            private final Mob mob;

            public LookAroundButAwesome(Mob mob)
            {
                super(mob);
                this.mob = mob;
            }

            @Override
            public boolean canUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canContinueToUse();
            }
        }

        static class WanderGoal extends WaterAvoidingRandomStrollGoal
        {
            public WanderGoal(PumpkinGolemEntity entity, double speed, float prob)
            {
                super(entity, speed, prob);
            }

            @Override
            public boolean canUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canContinueToUse();
            }
        }
    }
}
