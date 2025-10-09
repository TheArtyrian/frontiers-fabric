package net.artyrian.frontiers.entity.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ai.pumpkin_golem.PumpkinGolemPickGoal;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PumpkinGolemEntity extends GolemEntity
{
    public static final int MIN_STYLE = 0;
    public static final int MAX_STYLE = 6;

    private static final TrackedData<Integer> FACE_STYLE = DataTracker.registerData(PumpkinGolemEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> IS_ASLEEP = DataTracker.registerData(PumpkinGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    private int pickTicksLeft;

    public PumpkinGolemEntity(EntityType<? extends GolemEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new PumpkinGolemAI.SwimUnlessHonkShoo(this));
        this.goalSelector.add(2, new PumpkinGolemPickGoal(this, 1.2F, 24));
        this.goalSelector.add(3, new PumpkinGolemAI.WanderGoal(this, 1.0, 1.0F));
        this.goalSelector.add(3, new PumpkinGolemAI.LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new PumpkinGolemAI.LookAroundButAwesome(this));
    }

    @Override
    public void tick()
    {
        if (this.pickTicksLeft > 0) {
            this.pickTicksLeft--;
        }

        if (this.isAlive())
        {
            World world = this.getWorld();

            boolean isnight = world.isNight();
            boolean isasleep = !this.isGolemAsleep();

            if (isasleep != isnight && !world.isClient)
            {
                boolean trySwitch = world.getRandom().nextBetween(0, 100) > 90;
                if (trySwitch)
                {
                    this.setGolemSleep(!isnight);
                }
            }
        }
        super.tick();
    }

    @Override
    protected boolean shouldFollowLeash() { return !this.isGolemAsleep(); }

    @Override
    public void handleStatus(byte status)
    {
        if (status == EntityStatuses.PLAY_ATTACK_SOUND)
        {
            this.pickTicksLeft = 10;
            this.playSound(ModSounds.PUMPKIN_GOLEM_PICK, 1.0F, 1.25F);
        }
        else
        {
            super.handleStatus(status);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {
        super.initDataTracker(builder);
        builder.add(FACE_STYLE, MIN_STYLE);
        builder.add(IS_ASLEEP, false);
    }

    @Nullable @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData)
    {
        int rand = world.getRandom().nextBetween(MIN_STYLE, MAX_STYLE);
        this.setGolemStyle(rand);

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("GolemStyle", this.getGolemStyle());
        nbt.putBoolean("IsAsleep", this.isGolemAsleep());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        this.setGolemStyle(nbt.getInt("GolemStyle"));
        this.setGolemSleep(nbt.getBoolean("IsAsleep"));
    }

    @Override
    // Yes I know the tag `CAN_BREATHE_UNDER_WATER` exists, but hear me out - hardcoding is hilarious (the iron golem does it)
    protected int getNextAirUnderwater(int air) { return air; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.PUMPKIN_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PUMPKIN_GOLEM_DEATH;
    }

    public boolean isGolemAsleep() { return this.dataTracker.get(IS_ASLEEP); }
    public void setGolemSleep(boolean sleeping) { this.dataTracker.set(IS_ASLEEP, sleeping); }
    public int getGolemStyle() { return this.dataTracker.get(FACE_STYLE); }
    public void setGolemStyle(int style) { this.dataTracker.set(FACE_STYLE, Math.clamp(style, MIN_STYLE, MAX_STYLE)); }
    public int getPickTicks() { return this.pickTicksLeft; }
    public void setPickTicks()
    {
        this.pickTicksLeft = 10;
        this.playSound(ModSounds.PUMPKIN_GOLEM_PICK, 1.0F, 1.25F);
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
    }

    public static DefaultAttributeContainer.Builder createAttr()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F);
    }

    // Unique AIs for Pumpkin Golem
    public static class PumpkinGolemAI
    {
        static class LookAtGoal extends LookAtEntityGoal
        {
            public LookAtGoal(MobEntity mob, Class<? extends LivingEntity> targetType, float range)
            {
                super(mob, targetType, range);
            }

            @Override
            public boolean canStart()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canStart();
            }

            @Override
            public boolean shouldContinue()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.shouldContinue();
            }
        }

        static class SwimUnlessHonkShoo extends SwimGoal
        {
            private final MobEntity mob;

            public SwimUnlessHonkShoo(MobEntity mob)
            {
                super(mob);
                this.mob = mob;
            }

            @Override
            public boolean canStart()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canStart();
            }

            @Override
            public boolean shouldContinue()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.shouldContinue();
            }
        }

        static class LookAroundButAwesome extends LookAroundGoal
        {
            private final MobEntity mob;

            public LookAroundButAwesome(MobEntity mob)
            {
                super(mob);
                this.mob = mob;
            }

            @Override
            public boolean canStart()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canStart();
            }

            @Override
            public boolean shouldContinue()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.shouldContinue();
            }
        }

        static class WanderGoal extends WanderAroundFarGoal
        {
            public WanderGoal(PumpkinGolemEntity entity, double speed, float prob)
            {
                super(entity, speed, prob);
            }

            @Override
            public boolean canStart()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.canStart();
            }

            @Override
            public boolean shouldContinue()
            {
                if (this.mob instanceof PumpkinGolemEntity pump && pump.isGolemAsleep()) return false;
                return super.shouldContinue();
            }
        }
    }
}
