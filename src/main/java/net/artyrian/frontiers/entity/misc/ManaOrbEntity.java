package net.artyrian.frontiers.entity.misc;

import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.entity.ModEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ManaOrbEntity extends Entity
{
    private static final int DESPAWN_AGE = 6000;
    private static final int EXPENSIVE_UPDATE_INTERVAL = 20;
    private int orbAge;
    private int health = 5;
    private int amount;
    private int pickingCount = 1;
    private PlayerEntity target;

    public ManaOrbEntity(World world, double x, double y, double z, int amount)
    {
        this(ModEntity.MANA_ORB, world);
        this.setPosition(x, y, z);
        this.setYaw((float)(this.random.nextDouble() * 360.0));
        this.setVelocity((this.random.nextDouble() * 0.2F - 0.1F) * 2.0, this.random.nextDouble() * 0.2 * 2.0, (this.random.nextDouble() * 0.2F - 0.1F) * 2.0);
        this.amount = amount;
    }

    public ManaOrbEntity(EntityType<? extends ManaOrbEntity> entityType, World world)
    {
        super(entityType, world);
    }

    @Override protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }
    @Override protected double getGravity() {
        return 0.03;
    }
    @Override public BlockPos getVelocityAffectingPos() {
        return this.getPosWithYOffset(0.999999F);
    }
    @Override public boolean isAttackable() {
        return false;
    }
    @Override public SoundCategory getSoundCategory() {
        return SoundCategory.AMBIENT;
    }
    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
        return new ManaOrbSpawnS2CPacket(this, entityTrackerEntry);
    }

    @Override protected void initDataTracker(DataTracker.Builder builder) {}
    @Override protected void onSwimmingStart() {}

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        nbt.putShort("Health", (short)this.health);
        nbt.putShort("Age", (short)this.orbAge);
        nbt.putShort("Value", (short)this.amount);
        nbt.putInt("Count", this.pickingCount);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        this.health = nbt.getShort("Health");
        this.orbAge = nbt.getShort("Age");
        this.amount = nbt.getShort("Value");
        this.pickingCount = Math.max(nbt.getInt("Count"), 1);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player)
    {
        if (player instanceof ServerPlayerEntity serverPlayerEntity)
        {
            if (player.experiencePickUpDelay == 0)
            {
                player.experiencePickUpDelay = 2;
                player.sendPickup(this, 1);

                this.pickingCount--;
                if (this.pickingCount == 0) {
                    this.discard();
                }
            }
        }
    }

    @Override
    public void tick()
    {
        super.tick();
        this.prevX = this.getX();
        this.prevY = this.getY();
        this.prevZ = this.getZ();

        if (this.isSubmergedIn(FluidTags.WATER)) this.applyWaterMovement();
        else this.applyGravity();


        if (this.getWorld().getFluidState(this.getBlockPos()).isIn(FluidTags.LAVA)) {
            this.setVelocity(
                    ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F)
            );
        }

        if (!this.getWorld().isSpaceEmpty(this.getBoundingBox())) {
            this.pushOutOfBlocks(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0, this.getZ());
        }

        if (this.age % EXPENSIVE_UPDATE_INTERVAL == 1)
        {
            this.expensiveUpdate();
        }

        if (this.target != null && (this.target.isSpectator() || this.target.isDead()))
        {
            this.target = null;
        }

        if (this.target != null)
        {
            Vec3d vec3d = new Vec3d(
                    this.target.getX() - this.getX(), this.target.getY() + (double)this.target.getStandingEyeHeight() / 2.0 - this.getY(), this.target.getZ() - this.getZ()
            );
            double d = vec3d.lengthSquared();
            if (d < 64.0)
            {
                double e = 1.0 - Math.sqrt(d) / 8.0;
                this.setVelocity(this.getVelocity().add(vec3d.normalize().multiply(e * e * 0.1)));
            }
        }

        this.move(MovementType.SELF, this.getVelocity());
        float f = 0.98F;
        if (this.isOnGround())
        {
            f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.98F;
        }

        this.setVelocity(this.getVelocity().multiply(f, 0.98, f));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(1.0, -0.9, 1.0));
        }

        this.orbAge++;
        if (this.orbAge >= DESPAWN_AGE) this.discard();
    }

    private void expensiveUpdate()
    {
        if (this.target == null || this.target.squaredDistanceTo(this) > 64.0)
        {
            this.target = this.getWorld().getClosestPlayer(this, 8.0);
        }

        if (this.getWorld() instanceof ServerWorld)
        {
            for (ManaOrbEntity orb : this.getWorld()
                    .getEntitiesByType(TypeFilter.instanceOf(ManaOrbEntity.class), this.getBoundingBox().expand(0.5), this::isMergeable))
            {
                this.merge(orb);
            }
        }
    }

    public static void spawn(ServerWorld world, Vec3d pos, int amount)
    {
        while (amount > 0)
        {
            int i = roundToOrbSize(amount);
            amount -= i;
            if (!wasMergedIntoExistingOrb(world, pos, i))
            {
                world.spawnEntity(new ManaOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), i));
            }
        }
    }

    private static boolean wasMergedIntoExistingOrb(ServerWorld world, Vec3d pos, int amount)
    {
        Box box = Box.of(pos, 1.0, 1.0, 1.0);
        int i = world.getRandom().nextInt(40);
        List<ManaOrbEntity> list = world.getEntitiesByType(TypeFilter.instanceOf(ManaOrbEntity.class), box, orb -> isMergeable(orb, i, amount));
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
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * 0.99F, Math.min(vec3d.y + 5.0E-4F, 0.06F), vec3d.z * 0.99F);
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
    public boolean damage(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else if (this.getWorld().isClient)
        {
            return true;
        }
        else
        {
            this.scheduleVelocityUpdate();
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
