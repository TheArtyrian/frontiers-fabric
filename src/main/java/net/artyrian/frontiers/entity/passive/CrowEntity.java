package net.artyrian.frontiers.entity.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CrowEntity extends PathAwareEntity implements Flutterer
{
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;

    private float flapSpeed = 1.0F;
    private float nextFlapSpeed = 1.0F;

    public CrowEntity(EntityType<? extends PathAwareEntity> entityType, World world)
    {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, false);
    }

    public static DefaultAttributeContainer.Builder createAttr()
    {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(2, new FlyGoal(this, 1.0));
    }

    @Override
    protected EntityNavigation createNavigation(World world)
    {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public void tickMovement()
    {
        super.tickMovement();
        this.flap();
    }

    private void flap()
    {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = this.maxWingDeviation + (!this.isOnGround() && !this.hasVehicle() ? 4.0F : -1.0F) * 0.3F;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);

        if (!this.isOnGround() && this.flapSpeed < 1.0F)
        {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed *= 0.9F;
        Vec3d vec3d = this.getVelocity();

        if (!this.isOnGround() && vec3d.y < 0.0)
        {
            this.setVelocity(vec3d.multiply(1.0, 0.6, 1.0));
        }

        this.flapProgress = this.flapProgress + this.flapSpeed * 2.0F;
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    @Override
    protected boolean isFlappingWings() {
        return this.speed > this.nextFlapSpeed;
    }

    @Override
    protected void addFlapEffects()
    {
        this.playSound(ModSounds.CROW_FLY, 0.15F, 1.0F);
        this.nextFlapSpeed = this.speed + this.maxWingDeviation / 2.0F;
    }

    @Nullable @Override
    public SoundEvent getAmbientSound() { return ModSounds.CROW_IDLE; }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.CROW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CROW_DEATH;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) { /* lol lmao */ }

    public static boolean canSpawn(EntityType<CrowEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random)
    {
        long time = world.getLevelProperties().getTimeOfDay();
        if (CrowEntity.isNightCase(time))
        {
            if (pos.getY() > world.getSeaLevel() && world.getBlockState(pos.down()).isIn(ModTags.Blocks.CROW_CAN_SPAWN_ON))
            {
                if (random.nextInt(10) > 4) return false;

                return canMobSpawn(type, world, spawnReason, pos, random);
            }
        }

        return false;
    }

    private static boolean isNightCase(long time)
    {
        // Moon visibility at all
        if (Frontiers.EVENTS.IS_HALLOWEEN)
        {
            return (time >= 11834 || time <= 167);
        }
        // Sunset - Sunrise
        else
        {
            return (time >= 13000 && time <= 23000);
        }
    }
}