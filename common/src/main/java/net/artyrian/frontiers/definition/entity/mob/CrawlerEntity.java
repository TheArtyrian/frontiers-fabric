package net.artyrian.frontiers.definition.entity.mob;

import net.artyrian.frontiers.definition.entity.ai.crawler.CrawlerIgniteGoal;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import java.util.Collection;

public class CrawlerEntity extends Monster implements PowerableMob
{
    private static final EntityDataAccessor<Integer> FUSE_SPEED = SynchedEntityData.defineId(CrawlerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CHARGED = SynchedEntityData.defineId(CrawlerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IGNITED = SynchedEntityData.defineId(CrawlerEntity.class, EntityDataSerializers.BOOLEAN);
    private int lastFuseTime;
    private int currentFuseTime;
    private int fuseTime = 40;
    private int explosionRadius = 5;
    private int headsDropped;

    public CrawlerEntity(EntityType<? extends CrawlerEntity> entityType, Level world)
    {
        super(entityType, world);
        this.xpReward = 15;
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new CrawlerIgniteGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttr()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    public int getMaxFallDistance()
    {
        return this.getTarget() == null ? this.getComfortableFallDistance(0.0F) : this.getComfortableFallDistance(this.getHealth() - 1.0F);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource)
    {
        boolean bl = super.causeFallDamage(fallDistance, damageMultiplier, damageSource);
        this.currentFuseTime += (int)(fallDistance * 1.5F);
        if (this.currentFuseTime > this.fuseTime - 5)
        {
            if (this.getTarget() == null) this.currentFuseTime = this.fuseTime - 5;
            else this.explode();
        }

        return bl;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        super.defineSynchedData(builder);
        builder.define(FUSE_SPEED, -1);
        builder.define(CHARGED, false);
        builder.define(IGNITED, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        if (this.entityData.get(CHARGED))
        {
            nbt.putBoolean("powered", true);
        }

        nbt.putShort("Fuse", (short)this.fuseTime);
        nbt.putByte("ExplosionRadius", (byte)this.explosionRadius);
        nbt.putBoolean("ignited", this.isIgnited());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(CHARGED, nbt.getBoolean("powered"));
        if (nbt.contains("Fuse", Tag.TAG_ANY_NUMERIC))
        {
            this.fuseTime = nbt.getShort("Fuse");
        }

        if (nbt.contains("ExplosionRadius", Tag.TAG_ANY_NUMERIC))
        {
            this.explosionRadius = nbt.getByte("ExplosionRadius");
        }

        if (nbt.getBoolean("ignited"))
        {
            this.ignite();
        }
    }

    @Override
    public void tick()
    {
        if (this.isAlive())
        {
            this.lastFuseTime = this.currentFuseTime;
            if (this.isIgnited())
            {
                this.setFuseSpeed(1);
            }

            int i = this.getFuseSpeed();
            if (i > 0 && this.currentFuseTime == 0)
            {
                this.playSound(ModSounds.CRAWLER_PRIMED.get(), 1.0F, 1.0F);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.currentFuseTime += i;
            if (this.currentFuseTime < 0)
            {
                this.currentFuseTime = 0;
            }

            if (this.currentFuseTime >= this.fuseTime)
            {
                this.currentFuseTime = this.fuseTime;
                this.explode();
            }
        }

        super.tick();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.CREEPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CREEPER_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel world, DamageSource source, boolean causedByPlayer)
    {
        super.dropCustomDeathLoot(world, source, causedByPlayer);
        Entity entity = source.getEntity();
        if (entity != this && entity instanceof Creeper creeperEntity && creeperEntity.canDropMobsSkull())
        {
            creeperEntity.increaseDroppedSkulls();
            this.spawnAtLocation(Items.CREEPER_HEAD);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return true;
    }

    @Override
    public boolean isPowered()
    {
        return this.entityData.get(CHARGED);
    }

    public float getClientFuseTime(float timeDelta)
    {
        return Mth.lerp(timeDelta, (float)this.lastFuseTime, (float)this.currentFuseTime) / (float)(this.fuseTime - 2);
    }

    public int getFuseSpeed() {
        return this.entityData.get(FUSE_SPEED);
    }

    public void setFuseSpeed(int fuseSpeed)
    {
        this.entityData.set(FUSE_SPEED, fuseSpeed);
    }

    @Override
    public void thunderHit(ServerLevel world, LightningBolt lightning)
    {
        super.thunderHit(world, lightning);
        this.entityData.set(CHARGED, true);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(ItemTags.CREEPER_IGNITERS))
        {
            SoundEvent soundEvent = itemStack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(player, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide)
            {
                this.ignite();
                if (!itemStack.isDamageableItem())
                {
                    itemStack.shrink(1);
                }
                else
                {
                    itemStack.hurtAndBreak(1, player, getSlotForHand(hand));
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        else
        {
            return super.mobInteract(player, hand);
        }
    }

    private void explode() {
        if (!this.level().isClientSide)
        {
            float f = this.isPowered() ? 1.5F : 1.0F;
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * f, this.isOnFire(), Level.ExplosionInteraction.MOB);
            this.spawnEffectsCloud();
            this.triggerOnDeathMobEffects(RemovalReason.KILLED);
            this.discard();
        }
    }

    private void spawnEffectsCloud()
    {
        Collection<MobEffectInstance> collection = this.getActiveEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloud areaEffectCloudEntity = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setRadius(4.0F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(20);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusPerTick(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());

            for (MobEffectInstance statusEffectInstance : collection) {
                areaEffectCloudEntity.addEffect(new MobEffectInstance(statusEffectInstance));
            }

            this.level().addFreshEntity(areaEffectCloudEntity);
        }
    }

    public boolean isIgnited() {
        return this.entityData.get(IGNITED);
    }

    public void ignite() {
        this.entityData.set(IGNITED, true);
    }

    public boolean shouldDropHead() {
        return this.isPowered() && this.headsDropped < 1;
    }

    public void onHeadDropped() {
        this.headsDropped++;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }
}
