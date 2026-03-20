package net.artyrian.frontiers.definition.entity.misc;

import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.networking.payload.CragsMonsterKillPayload;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.misc.ModDamageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;

public class CragsMonsterEntity extends Entity
{
    private Player playertarget;
    private double animCompletion;
    private Vec3 startPos;
    private static final EntityDataAccessor<Float> ANIM_COMPLETION = SynchedEntityData.defineId(CragsMonsterEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> START_POS = SynchedEntityData.defineId(CragsMonsterEntity.class, EntityDataSerializers.VECTOR3);
    private static final EntityDataAccessor<Optional<UUID>> PLAYER_UUID = SynchedEntityData.defineId(CragsMonsterEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    public CragsMonsterEntity(EntityType<? extends CragsMonsterEntity> entityType, Level world)
    {
        super(entityType, world);
        this.noPhysics = true;
        this.animCompletion = 0.0;
    }

    public CragsMonsterEntity(Level world, double x, double y, double z, Player player)
    {
        this(ModEntity.CRAGS_MONSTER.get(), world);
        this.setPos(x, y, z);
        this.playertarget = player;

        this.startPos = new Vec3(x, y, z);
        this.entityData.set(START_POS, new Vector3f((float)x, (float)y, (float)z));
        this.entityData.set(PLAYER_UUID, Optional.of(this.playertarget.getUUID()));
    }

    @Override
    public void tick()
    {
        // I'm not trying to make pathfinding here, im trying to make a stupid jumpscare
        if (this.playertarget != null)
        {
            if (this.playertarget.isSpectator() || this.playertarget.isDeadOrDying())
            {
                this.playertarget = null;
            }

            if (this.playertarget != null)
            {
                double newx = Mth.lerp(animCompletion, this.startPos.x, this.playertarget.getX());
                double newy = Mth.lerp(animCompletion, this.startPos.y, this.playertarget.getY());
                double newz = Mth.lerp(animCompletion, this.startPos.z, this.playertarget.getZ());

                Vec3 newPos = new Vec3(newx, newy, newz);
                this.moveTo(newPos, this.getYRot(), this.getXRot());

                if (this.animCompletion < 1.0)
                {
                    this.animCompletion += 0.1;
                    this.entityData.set(ANIM_COMPLETION, (float)this.animCompletion);
                }
            }
        }
        else
        {
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder)
    {
        builder.define(START_POS, new Vector3f(0.0F, 0.0F, 0.0F));
        builder.define(ANIM_COMPLETION, 0.0F);
        builder.define(PLAYER_UUID, Optional.empty());
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data)
    {
        if (START_POS.equals(data))
        {
            Vector3f importer = this.entityData.get(START_POS);
            double xx = importer.x;
            double yy = importer.y;
            double zz = importer.z;
            this.startPos = new Vec3(xx, yy, zz);
        }

        if (ANIM_COMPLETION.equals(data))
        {
            this.animCompletion = this.entityData.get(ANIM_COMPLETION);
        }

        if (PLAYER_UUID.equals(data))
        {
            Optional<UUID> optionalUUID = this.entityData.get(PLAYER_UUID);
            if (optionalUUID.isPresent())
            {
                UUID uuid = optionalUUID.get();
                if (this.level().getPlayerByUUID(uuid) != null)
                {
                    this.playertarget = this.level().getPlayerByUUID(uuid);
                }
            }
        }

        super.onSyncedDataUpdated(data);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        if (nbt.contains("TargetPlayer"))
        {
            UUID uuid = nbt.getUUID("TargetPlayer");
            this.entityData.set(PLAYER_UUID, Optional.of(uuid));
        }
        this.entityData.set(ANIM_COMPLETION, (float)nbt.getDouble("AnimCompletion"));

        double setX = nbt.getDouble("StartX");
        double setY = nbt.getDouble("StartY");
        double setZ = nbt.getDouble("StartZ");

        this.entityData.set(START_POS, new Vector3f((float)setX, (float)setY, (float)setZ));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        Vector3f floats = this.entityData.get(START_POS);
        float anim = this.entityData.get(ANIM_COMPLETION);
        Optional<UUID> uuid = this.entityData.get(PLAYER_UUID);

        uuid.ifPresent(value -> nbt.putUUID("TargetPlayer", value));

        nbt.putDouble("StartX", anim);

        nbt.putDouble("StartX", floats.x);
        nbt.putDouble("StartY", floats.y);
        nbt.putDouble("StartZ", floats.z);
    }

    @Override
    public boolean canUsePortal(boolean allowVehicles) { return false; }

    @Override
    public void playerTouch(Player player)
    {
        if (player instanceof ServerPlayer)
        {
            if (!player.isCreative() && !player.isSpectator())
            {
                PlayerPersistentNBT.Sanity.setCragsMonsterKill(player);

                MinecraftServer server = this.level().getServer();
                if (server != null)
                {
                    VectorLib.NETWORK.sendToPlayer((ServerPlayer) player, new CragsMonsterKillPayload(
                            ((PlayerIntf)player).frontiers_1_21x$killedByCragsMonster())
                    );
                }

                player.hurt(ModDamageType.of(player.level(), ModDamageType.INSANITY), Float.MAX_VALUE);
            }
            this.discard();
        }
    }

    protected MovementEmission getMovementEmission()
    {
        return MovementEmission.NONE;
    }
}
