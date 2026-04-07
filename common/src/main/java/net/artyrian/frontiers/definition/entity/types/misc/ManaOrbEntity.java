package net.artyrian.frontiers.definition.entity.types.misc;

import net.artyrian.frontiers.definition.networking.packet.client.ClientboundManaOrbPacket;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.artyrian.frontiers.reg.content.FREntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.function.Predicate;

public class ManaOrbEntity extends Entity
{
    private static final int DESPAWN_AGE = 6000;
    private static final int EXPENSIVE_UPDATE_INTERVAL = 20;
    private static final Predicate<Entity> NOT_MAX_MANA = (entity) -> (entity instanceof PlayerIntf pl && !pl.frontiers_1_21x$atMaxMana());

    private int orbAge;
    private int health = 5;
    private int amount;
    private int pickingCount = 1;
    private Player target;

    public ManaOrbEntity(Level world, double x, double y, double z, int amount)
    {
        this(FREntity.MANA_ORB.get(), world);
        this.setPos(x, y, z);
        this.setYRot((float)(this.random.nextDouble() * 360.0));
        this.setDeltaMovement((this.random.nextDouble() * 0.2F - 0.1F) * 2.0, this.random.nextDouble() * 0.2 * 2.0, (this.random.nextDouble() * 0.2F - 0.1F) * 2.0);
        this.amount = amount;
    }

    public ManaOrbEntity(EntityType<? extends ManaOrbEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    @Override protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }
    @Override protected double getDefaultGravity() {
        return 0.03;
    }
    @Override public BlockPos getBlockPosBelowThatAffectsMyMovement() {
        return this.getOnPos(0.999999F);
    }
    @Override public boolean isAttackable() {
        return false;
    }
    @Override public SoundSource getSoundSource() {
        return SoundSource.AMBIENT;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityTrackerEntry)
    {
        return new ClientboundManaOrbPacket(this, entityTrackerEntry);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) {}
    @Override protected void doWaterSplashEffect() {}

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        nbt.putShort("Health", (short)this.health);
        nbt.putShort("Age", (short)this.orbAge);
        nbt.putShort("Value", (short)this.amount);
        nbt.putInt("Count", this.pickingCount);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        this.health = nbt.getShort("Health");
        this.orbAge = nbt.getShort("Age");
        this.amount = nbt.getShort("Value");
        this.pickingCount = Math.max(nbt.getInt("Count"), 1);
    }

    @Override
    public void playerTouch(Player player)
    {
        if (player instanceof ServerPlayer && NOT_MAX_MANA.test(player))
        {
            if (player.takeXpDelay == 0)
            {
                player.takeXpDelay = 2;
                player.take(this, 1);

                if (this.amount > 0) ((PlayerIntf)player).frontiers_1_21x$addMana(this.amount);

                this.pickingCount--;
                if (this.pickingCount == 0) this.discard();
            }
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();

        if (this.isEyeInFluid(FluidTags.WATER)) this.applyWaterMovement();
        else this.applyGravity();


        if (this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            this.setDeltaMovement(
                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F)
            );
        }

        if (!this.level().noCollision(this.getBoundingBox())) {
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0, this.getZ());
        }

        if (this.tickCount % EXPENSIVE_UPDATE_INTERVAL == 1)
        {
            this.expensiveUpdate();
        }

        if (this.target != null && (this.target.isSpectator() || this.target.isDeadOrDying() || !NOT_MAX_MANA.test(this.target)))
        {
            this.target = null;
        }

        if (this.target != null)
        {
            Vec3 vec3d = new Vec3(
                    this.target.getX() - this.getX(), this.target.getY() + (double)this.target.getEyeHeight() / 2.0 - this.getY(), this.target.getZ() - this.getZ()
            );
            double d = vec3d.lengthSqr();
            if (d < 64.0)
            {
                double e = 1.0 - Math.sqrt(d) / 8.0;
                this.setDeltaMovement(this.getDeltaMovement().add(vec3d.normalize().scale(e * e * 0.1)));
            }
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        float f = 0.98F;
        if (this.onGround())
        {
            f = this.level().getBlockState(this.getBlockPosBelowThatAffectsMyMovement()).getBlock().getFriction() * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(f, 0.98, f));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, -0.9, 1.0));
        }

        this.orbAge++;
        if (this.orbAge >= DESPAWN_AGE) this.discard();
    }

    private void expensiveUpdate()
    {
        if (this.target == null || this.target.distanceToSqr(this) > 64.0)
        {
            this.target = this.level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), 8.0, NOT_MAX_MANA);
        }

        if (this.level() instanceof ServerLevel)
        {
            for (ManaOrbEntity orb : this.level()
                    .getEntities(EntityTypeTest.forClass(ManaOrbEntity.class), this.getBoundingBox().inflate(0.5), this::isMergeable))
            {
                this.merge(orb);
            }
        }
    }

    public static void award(ServerLevel world, Vec3 pos, int amount)
    {
        while (amount > 0)
        {
            int i = roundToOrbSize(amount);
            amount -= i;
            if (!wasMergedIntoExistingOrb(world, pos, i))
            {
                world.addFreshEntity(new ManaOrbEntity(world, pos.x(), pos.y(), pos.z(), i));
            }
        }
    }

    private static boolean wasMergedIntoExistingOrb(ServerLevel world, Vec3 pos, int amount)
    {
        AABB box = AABB.ofSize(pos, 1.0, 1.0, 1.0);
        int i = world.getRandom().nextInt(40);
        List<ManaOrbEntity> list = world.getEntities(EntityTypeTest.forClass(ManaOrbEntity.class), box, orb -> isMergeable(orb, i, amount));
        if (!list.isEmpty())
        {
            ManaOrbEntity orb = list.getFirst();
            orb.pickingCount++;
            orb.orbAge = 0;
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isMergeable(ManaOrbEntity other)
    {
        return other != this && isMergeable(other, this.getId(), this.amount);
    }

    private static boolean isMergeable(ManaOrbEntity orb, int seed, int amount)
    {
        return !orb.isRemoved() && (orb.getId() - seed) % 40 == 0 && orb.amount == amount;
    }

    private void merge(ManaOrbEntity other)
    {
        this.pickingCount = this.pickingCount + other.pickingCount;
        this.orbAge = Math.min(this.orbAge, other.orbAge);
        other.discard();
    }

    private void applyWaterMovement()
    {
        Vec3 vec3d = this.getDeltaMovement();
        this.setDeltaMovement(vec3d.x * 0.99F, Math.min(vec3d.y + 5.0E-4F, 0.06F), vec3d.z * 0.99F);
    }

    public int getOrbSize()
    {
        if (this.amount >= 2477) return 10;
        else if (this.amount >= 1237) return 9;
        else if (this.amount >= 617) return 8;
        else if (this.amount >= 307) return 7;
        else if (this.amount >= 149) return 6;
        else if (this.amount >= 73) return 5;
        else if (this.amount >= 37) return 4;
        else if (this.amount >= 17) return 3;
        else if (this.amount >= 7) return 2;
        else return this.amount >= 3 ? 1 : 0;
    }

    public static int roundToOrbSize(int value)
    {
        if (value >= 2477) return 2477;
        else if (value >= 1237) return 1237;
        else if (value >= 617) return 617;
        else if (value >= 307) return 307;
        else if (value >= 149) return 149;
        else if (value >= 73) return 73;
        else if (value >= 37) return 37;
        else if (value >= 17) return 17;
        else if (value >= 7) return 7;
        else return value >= 3 ? 3 : 1;
    }

    @Override
    public boolean hurt(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else if (this.level().isClientSide)
        {
            return true;
        }
        else
        {
            this.markHurt();
            this.health = (int)((float)this.health - amount);
            if (this.health <= 0)
            {
                this.discard();
            }

            return true;
        }
    }

    public int getManaAmount() {
        return this.amount;
    }
}
