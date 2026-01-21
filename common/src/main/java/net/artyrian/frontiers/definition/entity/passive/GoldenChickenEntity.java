package net.artyrian.frontiers.definition.entity.passive;

import net.artyrian.frontiers.definition.entity.ai.chicken.ChickenMateGoal;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class GoldenChickenEntity extends Animal
{
    private static final EntityDimensions BABY_BASE_DIMENSIONS = ModEntity.GOLDEN_CHICKEN.get().getDimensions().scale(0.5F).withEyeHeight(0.2975F);
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    public float flapSpeed = 1.0F;
    private float nextFlapSpeed = 1.0F;
    public int eggLayTime;

    public GoldenChickenEntity(EntityType<? extends GoldenChickenEntity> entityType, Level world)
    {
        super(entityType, world);
        this.eggLayTime = this.random.nextInt(12000) + 12000;
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(2, new ChickenMateGoal(this, Chicken.class, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(
                this, 1.0, stack -> (stack.is(ItemTags.CHICKEN_FOOD) || stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD)), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose)
    {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getDefaultDimensions(pose);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = this.maxWingDeviation + (this.onGround() ? -1.0F : 4.0F) * 0.3F;
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
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.eggLayTime <= 0)
        {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(ModItem.GOLDEN_EGG.get());
            this.gameEvent(GameEvent.ENTITY_PLACE);
            this.eggLayTime = this.random.nextInt(12000) + 12000;
        }
    }

    @Override
    protected boolean isFlapping()
    {
        return this.flyDist > this.nextFlapSpeed;
    }
    @Override
    protected void onFlap()
    {
        this.nextFlapSpeed = this.flyDist + this.maxWingDeviation / 2.0F;
    }

    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.CHICKEN_AMBIENT; }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.CHICKEN_HURT; }
    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.CHICKEN_DEATH; }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state)
    {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel world, Animal other)
    {
        ItemStack itemStack = new ItemStack(ModItem.GOLDEN_EGG.get());
        ItemEntity itemEntity = new ItemEntity(world, this.position().x(), this.position().y(), this.position().z(), itemStack);
        itemEntity.setDefaultPickUpDelay();
        this.finalizeSpawnChildFromBreeding(world, other, null);
        this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        world.addFreshEntity(itemEntity);
    }

    @Nullable
    public GoldenChickenEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity)
    {
        return ModEntity.GOLDEN_CHICKEN.get().create(serverWorld);
    }

    @Override
    public boolean isFood(ItemStack stack)
    {
        return stack.is(ItemTags.CHICKEN_FOOD) || stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("EggLayTime"))
        {
            this.eggLayTime = nbt.getInt("EggLayTime");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("EggLayTime", this.eggLayTime);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable()
    {
        return EntityType.CHICKEN.getDefaultLootTable();
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction positionUpdater)
    {
        super.positionRider(passenger, positionUpdater);
        if (passenger instanceof LivingEntity passenger2)
        {
            passenger2.yBodyRot = this.yBodyRot;
        }
    }

    @Override
    public boolean canMate(Animal other)
    {
        if (other == this)
        {
            return false;
        }
        else if (!(other instanceof GoldenChickenEntity) && !(other instanceof Chicken))
        {
            return false;
        }
        else
        {
            return this.isInLove() && other.isInLove();
        }
    }
}