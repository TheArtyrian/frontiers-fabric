package net.artyrian.frontiers.definition.entity.misc;

import net.artyrian.frontiers.definition.networking.payload.CragsStalkerDespawnPayload;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.vertisoft.vectorlib.VectorLib;

import java.util.List;

public class CragsStalkerEntity extends Entity
{
    public static final DustParticleOptions SMOG = new DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, 0.5F);

    private Player target;

    private int entityAge;
    private boolean livesForever;

    public CragsStalkerEntity(EntityType<? extends CragsStalkerEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public CragsStalkerEntity(Level world, double x, double y, double z)
    {
        this(ModEntity.CRAGS_STALKER.get(), world);
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt)
    {
        this.livesForever = nbt.getBoolean("LivesForever");
        this.entityAge = nbt.getInt("EntityAge");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt)
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

            Level world = this.level();
            RandomSource random = world.getRandom();
            boolean doSmog = (random.nextFloat() >= 0.5F);

            if (doSmog)
            {
                for (int i = 0; i < 3; i++)
                {
                    world.addParticle(SMOG,
                            position().x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            position().y() + (0.1 * (1 + (random.nextInt(17)))),
                            position().z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            0.0, 0.025, 0.0);
                }

                for (int i = 0; i < 4; i++)
                {
                    world.addParticle(ModParticle.CRAG_SMOG.get(),
                            position().x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            position().y() + (0.1 * (1 + (random.nextInt(5)))),
                            position().z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            (0.05 * random.nextIntBetweenInclusive(-2, 2)), (0.025 * random.nextIntBetweenInclusive(1, 3)), (0.05 * random.nextIntBetweenInclusive(-2, 2)));
                }
            }
        }
    }

    @Override
    public void remove(RemovalReason reason)
    {
        if (!this.level().isClientSide)
        {
            VectorLib.NETWORK.sendToAllInChunk(
                    (ServerLevel)this.level(),
                    this.blockPosition(),
                    new CragsStalkerDespawnPayload(this.getX(), this.getY(), this.getZ())
            );
        }

        super.remove(reason);
    }

    @Override
    public boolean canUsePortal(boolean allowVehicles) { return false; }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    private void playerCircleCheck()
    {
        if (this.target == null)
        {
            this.target = this.level().getNearestPlayer(this, 20.0);

            if (this.target == null || ((PlayerMixInterface)this.target).frontiers_1_21x$getSanity() > 0 || this.level().dimension() != ModDimension.CRAGS_LEVEL_KEY)
            {
                this.target = this.level().getNearestPlayer(this, 8.0);
                if (this.target != null)
                {
                    this.discard();
                }
            }
            else if (!this.target.isCreative() && !this.target.isSpectator())
            {
                if (this.level() instanceof ServerLevel serverWorld)
                {
                    serverWorld.addFreshEntity(new CragsMonsterEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.target));

                    AABB box = new AABB(this.blockPosition()).inflate(30);
                    List<ServerPlayer> list = this.level().getEntitiesOfClass(ServerPlayer.class, box);

                    for (ServerPlayer targeter : list)
                    {
                        targeter.playNotifySound(ModSounds.CRAGSMONSTER_BELLOW.get(), SoundSource.HOSTILE, 10.0F, 0.8F);
                    }
                }

                this.level().addParticle(ParticleTypes.EXPLOSION_EMITTER,
                        position().x(),
                        position().y() + 1.0,
                        position().z(),
                        0.0, 0.0, 0.0);
                for (int i = 0; i < 30; i++)
                {
                    this.level().addParticle(ModParticle.CRAG_SMOG.get(),
                            position().x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            position().y() + (0.1 * (1 + (random.nextInt(5)))),
                            position().z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                            (0.2 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.2 * random.nextIntBetweenInclusive(-2, 2)));
                }

                this.discard();
            }
        }
    }
}
