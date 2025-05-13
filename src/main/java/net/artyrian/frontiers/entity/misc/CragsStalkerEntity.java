package net.artyrian.frontiers.entity.misc;

import net.artyrian.frontiers.data.payloads.CragsStalkerDespawnPayload;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.artyrian.frontiers.particle.ModParticle;
import net.artyrian.frontiers.sounds.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class CragsStalkerEntity extends Entity
{
    public static final DustParticleEffect SMOG = new DustParticleEffect(DustParticleEffect.RED, 0.5F);

    private PlayerEntity target;

    private int entityAge;
    private boolean livesForever;

    public CragsStalkerEntity(EntityType<? extends CragsStalkerEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public CragsStalkerEntity(World world, double x, double y, double z)
    {
        this(ModEntity.CRAGS_STALKER, world);
        this.setPosition(x, y, z);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt)
    {
        this.livesForever = nbt.getBoolean("LivesForever");
        this.entityAge = nbt.getInt("EntityAge");
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt)
    {
        nbt.putBoolean("LivesForever", this.livesForever);
        nbt.putInt("EntityAge", this.entityAge);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.isRemoved())
        {
            if (this.entityAge >= 1200 && !this.livesForever) this.discard();
            else this.entityAge++;

            if (this.entityAge % 5 == 1)
            {
                this.playerCircleCheck();
            }

            World world = this.getWorld();
            Random random = world.getRandom();
            boolean doSmog = (random.nextFloat() >= 0.5F);

            if (doSmog)
            {
                for (int i = 0; i < 3; i++)
                {
                    world.addParticle(SMOG,
                            getPos().getX() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            getPos().getY() + (0.1 * (1 + (random.nextInt(17)))),
                            getPos().getZ() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            0.0, 0.025, 0.0);
                }

                for (int i = 0; i < 4; i++)
                {
                    world.addParticle(ModParticle.CRAG_SMOG,
                            getPos().getX() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            getPos().getY() + (0.1 * (1 + (random.nextInt(5)))),
                            getPos().getZ() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            (0.05 * random.nextBetween(-2, 2)), (0.025 * random.nextBetween(1, 3)), (0.05 * random.nextBetween(-2, 2)));
                }
            }
        }
    }

    @Override
    public void remove(RemovalReason reason)
    {
        if (!this.getWorld().isClient)
        {
            for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) this.getWorld(), this.getBlockPos()))
            {
                ServerPlayNetworking.send(targeter, new CragsStalkerDespawnPayload(this.getX(), this.getY(), this.getZ()));
            }
        }

        super.remove(reason);
    }

    @Override
    public boolean canUsePortals(boolean allowVehicles) { return false; }

    @Override
    protected Entity.MoveEffect getMoveEffect() {
        return Entity.MoveEffect.NONE;
    }

    private void playerCircleCheck()
    {
        if (this.target == null)
        {
            this.target = this.getWorld().getClosestPlayer(this, 20.0);

            if (this.target == null || ((PlayerMixInterface)this.target).frontiers_1_21x$getSanity() > 0 || this.getWorld().getRegistryKey() != ModDimension.CRAGS_LEVEL_KEY)
            {
                this.target = this.getWorld().getClosestPlayer(this, 8.0);
                if (this.target != null)
                {
                    this.discard();
                }
            }
            else if (!this.target.isCreative() && !this.target.isSpectator())
            {
                if (this.getWorld() instanceof ServerWorld serverWorld)
                {
                    serverWorld.spawnEntity(new CragsMonsterEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.target));
                }
                this.playSound(ModSounds.CRAGSMONSTER_BELLOW, 10.0F, 0.8F);
                this.getWorld().addParticle(ParticleTypes.EXPLOSION_EMITTER,
                        getPos().getX(),
                        getPos().getY() + 1.0,
                        getPos().getZ(),
                        0.0, 0.0, 0.0);
                for (int i = 0; i < 30; i++)
                {
                    this.getWorld().addParticle(ModParticle.CRAG_SMOG,
                            getPos().getX() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            getPos().getY() + (0.1 * (1 + (random.nextInt(5)))),
                            getPos().getZ() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            (0.2 * random.nextBetween(-2, 2)), (0.1 * random.nextBetween(1, 3)), (0.2 * random.nextBetween(-2, 2)));
                }

                this.discard();
            }
        }
    }
}
