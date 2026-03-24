package net.artyrian.frontiers.definition.entity.types.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class CrowEntity extends PathfinderMob implements FlyingAnimal
{
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;

    private float flapSpeed = 1.0F;
    private float nextFlapSpeed = 1.0F;

    public CrowEntity(EntityType<? extends PathfinderMob> entityType, Level world)
    {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 10, false);
    }

    public static AttributeSupplier.Builder createAttr()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.FLYING_SPEED, 0.4F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0));
    }

    @Override
    protected PathNavigation createNavigation(Level world)
    {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(true);
        birdNavigation.setCanPassDoors(true);
        return birdNavigation;
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        this.flap();
    }

    private void flap()
    {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = this.maxWingDeviation + (!this.onGround() && !this.isPassenger() ? 4.0F : -1.0F) * 0.3F;
        this.maxWingDeviation = Mth.clamp(this.maxWingDeviation, 0.0F, 1.0F);

        if (!this.onGround() && this.flapSpeed < 1.0F)
        {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed *= 0.9F;
        Vec3 vec3d = this.getDeltaMovement();

        if (!this.onGround() && vec3d.y < 0.0)
        {
            this.setDeltaMovement(vec3d.multiply(1.0, 0.6, 1.0));
        }

        this.flapProgress = this.flapProgress + this.flapSpeed * 2.0F;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    protected boolean isFlapping() {
        return this.flyDist > this.nextFlapSpeed;
    }

    @Override
    protected void onFlap()
    {
        this.playSound(ModSounds.CROW_FLY.get(), 0.15F, 1.0F);
        this.nextFlapSpeed = this.flyDist + this.maxWingDeviation / 2.0F;
    }

    @Nullable @Override
    public SoundEvent getAmbientSound() { return ModSounds.CROW_IDLE.get(); }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.CROW_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CROW_DEATH.get();
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) { /* lol lmao */ }

    public static boolean canSpawn(EntityType<CrowEntity> type, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random)
    {
        long time = world.getLevelData().getDayTime();
        if (CrowEntity.isNightCase(time))
        {
            if (pos.getY() > world.getSeaLevel() && world.getBlockState(pos.below()).is(ModTags.Blocks.CROW_CAN_SPAWN_ON))
            {
                if (random.nextInt(10) > 4) return false;

                return checkMobSpawnRules(type, world, spawnReason, pos, random);
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