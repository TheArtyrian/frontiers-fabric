package net.artyrian.frontiers.definition.entity.mob;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.entity.passive.CrowEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

public class JungleSpiderEntity extends Spider
{
    private static final BlockParticleOption COBWEB_PARTICLES = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COBWEB.defaultBlockState());
    public int webTime = this.random.nextInt(600) + 600;

    public JungleSpiderEntity(EntityType<? extends Spider> entityType, Level world)
    {
        super(entityType, world);
        this.xpReward = 10;
    }

    public static AttributeSupplier.Builder createAttr()
    {
        return Spider.createAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.ATTACK_DAMAGE, 7.0F);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.targetSelector.addGoal(3, new TargetGoalUnrelent<>(this, Parrot.class));
        this.targetSelector.addGoal(3, new TargetGoalUnrelent<>(this, Chicken.class));
        this.targetSelector.addGoal(3, new TargetGoalUnrelent<>(this, CrowEntity.class));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("WebTime"))
        {
            this.webTime = nbt.getInt("WebTime");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("WebTime", this.webTime);
    }

    @Override
    public boolean doHurtTarget(Entity target)
    {
        boolean doAtk = super.doHurtTarget(target);
        if (doAtk)
        {
            if (target instanceof LivingEntity)
            {
                int i = 0;
                if (this.level().getDifficulty() == Difficulty.NORMAL) i = 7;
                else if (this.level().getDifficulty() == Difficulty.HARD) i = 15;

                boolean hardmode = false;
                if (this.level() instanceof ServerLevel world)
                {
                    MinecraftServer server = world.getServer();
                    StateSaveLoad loader = StateSaveLoad.getServerState(server);
                    hardmode = loader.isInHardmode;
                }

                if (i > 0 && hardmode)
                {
                    ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, i * 20, 0), this);
                }
            }
        }
        return doAtk;
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive() && --this.webTime <= 0)
        {
            if (this.getInBlockState().isAir())
            {
                this.playSound(SoundEvents.COBWEB_PLACE, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                ((ServerLevel)this.level()).sendParticles(
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
                this.level().setBlockAndUpdate(this.blockPosition(), Blocks.COBWEB.defaultBlockState());
                this.gameEvent(GameEvent.ENTITY_PLACE);
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData)
    {
        return entityData;
    }

    @Override
    public Vec3 getVehicleAttachmentPoint(Entity vehicle)
    {
        return vehicle.getBbWidth() <= this.getBbWidth() ? new Vec3(0.0, 0.21875 * (double)this.getScale(), 0.0) : super.getVehicleAttachmentPoint(vehicle);
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        boolean bl = super.hurt(source, amount);
        if (this.level().isClientSide)
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

    public static boolean canSpawn(EntityType<? extends Monster> type, ServerLevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random)
    {
        boolean baseValue = Monster.checkMonsterSpawnRules(type, world, spawnReason, pos, random);
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

    static class TargetGoalUnrelent<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
    {
        public TargetGoalUnrelent(Spider spider, Class<T> targetEntityClass) { super(spider, targetEntityClass, true); }

        @Override
        public boolean canUse()
        {
            boolean start = super.canUse();
            if (start)
            {
                if (this.target != null && this.target instanceof TamableAnimal tamedTarget) return !tamedTarget.isTame();
            }
            return start;
        }

        @Override
        public boolean canContinueToUse()
        {
            return super.canContinueToUse();
        }
    }
}
