package net.artyrian.frontiers.entity.passive;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.ai.chicken.ChickenMateGoal;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GoldenChickenEntity extends AnimalEntity
{
    private static final EntityDimensions BABY_BASE_DIMENSIONS = ModEntity.GOLDEN_CHICKEN.getDimensions().scaled(0.5F).withEyeHeight(0.2975F);
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    public float flapSpeed = 1.0F;
    private float nextFlapSpeed = 1.0F;
    public int eggLayTime;

    public GoldenChickenEntity(EntityType<? extends GoldenChickenEntity> entityType, World world)
    {
        super(entityType, world);
        this.eggLayTime = this.random.nextInt(12000) + 12000;
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void initGoals()
    {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4));
        this.goalSelector.add(2, new ChickenMateGoal(this, ChickenEntity.class, 1.0));
        this.goalSelector.add(3, new TemptGoal(
                this, 1.0, stack -> (stack.isIn(ItemTags.CHICKEN_FOOD) || stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD)), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public EntityDimensions getBaseDimensions(EntityPose pose)
    {
        return this.isBaby() ? BABY_BASE_DIMENSIONS : super.getBaseDimensions(pose);
    }

    @Override
    public void tickMovement()
    {
        super.tickMovement();
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation = this.maxWingDeviation + (this.isOnGround() ? -1.0F : 4.0F) * 0.3F;
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
        if (!this.getWorld().isClient && this.isAlive() && !this.isBaby() && --this.eggLayTime <= 0)
        {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.dropItem(ModItem.GOLDEN_EGG);
            this.emitGameEvent(GameEvent.ENTITY_PLACE);
            this.eggLayTime = this.random.nextInt(12000) + 12000;
        }
    }

    @Override
    protected boolean isFlappingWings()
    {
        return this.speed > this.nextFlapSpeed;
    }
    @Override
    protected void addFlapEffects()
    {
        this.nextFlapSpeed = this.speed + this.maxWingDeviation / 2.0F;
    }

    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_CHICKEN_AMBIENT; }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.ENTITY_CHICKEN_HURT; }
    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_CHICKEN_DEATH; }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state)
    {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public void breed(ServerWorld world, AnimalEntity other)
    {
        ItemStack itemStack = new ItemStack(ModItem.GOLDEN_EGG);
        ItemEntity itemEntity = new ItemEntity(world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
        itemEntity.setToDefaultPickupDelay();
        this.breed(world, other, null);
        this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        world.spawnEntity(itemEntity);
    }

    @Nullable
    public GoldenChickenEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity)
    {
        return ModEntity.GOLDEN_CHICKEN.create(serverWorld);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.isIn(ItemTags.CHICKEN_FOOD) || stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("EggLayTime"))
        {
            this.eggLayTime = nbt.getInt("EggLayTime");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("EggLayTime", this.eggLayTime);
    }

    @Override
    protected RegistryKey<LootTable> getLootTableId()
    {
        return EntityType.CHICKEN.getLootTableId();
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater)
    {
        super.updatePassengerPosition(passenger, positionUpdater);
        if (passenger instanceof LivingEntity passenger2)
        {
            passenger2.bodyYaw = this.bodyYaw;
        }
    }

    @Override
    public boolean canBreedWith(AnimalEntity other)
    {
        if (other == this)
        {
            return false;
        }
        else if (!(other instanceof GoldenChickenEntity) && !(other instanceof ChickenEntity))
        {
            return false;
        }
        else
        {
            return this.isInLove() && other.isInLove();
        }
    }
}