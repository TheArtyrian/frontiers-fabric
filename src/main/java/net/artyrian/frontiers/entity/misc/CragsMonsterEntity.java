package net.artyrian.frontiers.entity.misc;

import net.artyrian.frontiers.data.payloads.CragsMonsterKillPayload;
import net.artyrian.frontiers.data.player.PlayerPersistentNBT;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.misc.ModDamageType;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.UUID;

public class CragsMonsterEntity extends Entity
{
    private PlayerEntity playertarget;
    private double animCompletion;
    private Vec3d startPos;
    private static final TrackedData<Float> ANIM_COMPLETION = DataTracker.registerData(CragsMonsterEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Vector3f> START_POS = DataTracker.registerData(CragsMonsterEntity.class, TrackedDataHandlerRegistry.VECTOR3F);
    private static final TrackedData<Optional<UUID>> PLAYER_UUID = DataTracker.registerData(CragsMonsterEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);

    public CragsMonsterEntity(EntityType<? extends CragsMonsterEntity> entityType, World world)
    {
        super(entityType, world);
        this.noClip = true;
        this.animCompletion = 0.0;
    }

    public CragsMonsterEntity(World world, double x, double y, double z, PlayerEntity player)
    {
        this(ModEntity.CRAGS_MONSTER, world);
        this.setPosition(x, y, z);
        this.playertarget = player;

        this.startPos = new Vec3d(x, y, z);
        this.dataTracker.set(START_POS, new Vector3f((float)x, (float)y, (float)z));
        this.dataTracker.set(PLAYER_UUID, Optional.of(this.playertarget.getUuid()));
    }

    @Override
    public void tick()
    {
        // I'm not trying to make pathfinding here, im trying to make a stupid jumpscare
        if (this.playertarget != null)
        {
            if (this.playertarget.isSpectator() || this.playertarget.isDead())
            {
                this.playertarget = null;
            }

            if (this.playertarget != null)
            {
                double newx = MathHelper.lerp(animCompletion, this.startPos.x, this.playertarget.getX());
                double newy = MathHelper.lerp(animCompletion, this.startPos.y, this.playertarget.getY());
                double newz = MathHelper.lerp(animCompletion, this.startPos.z, this.playertarget.getZ());

                Vec3d newPos = new Vec3d(newx, newy, newz);
                this.refreshPositionAndAngles(newPos, this.getYaw(), this.getPitch());

                if (this.animCompletion < 1.0)
                {
                    this.animCompletion += 0.1;
                    this.dataTracker.set(ANIM_COMPLETION, (float)this.animCompletion);
                }
            }
        }
        else
        {
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder)
    {
        builder.add(START_POS, new Vector3f(0.0F, 0.0F, 0.0F));
        builder.add(ANIM_COMPLETION, 0.0F);
        builder.add(PLAYER_UUID, Optional.empty());
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data)
    {
        if (START_POS.equals(data))
        {
            Vector3f importer = this.dataTracker.get(START_POS);
            double xx = importer.x;
            double yy = importer.y;
            double zz = importer.z;
            this.startPos = new Vec3d(xx, yy, zz);
        }

        if (ANIM_COMPLETION.equals(data))
        {
            this.animCompletion = this.dataTracker.get(ANIM_COMPLETION);
        }

        if (PLAYER_UUID.equals(data))
        {
            Optional<UUID> optionalUUID = this.dataTracker.get(PLAYER_UUID);
            if (optionalUUID.isPresent())
            {
                UUID uuid = optionalUUID.get();
                if (this.getWorld().getPlayerByUuid(uuid) != null)
                {
                    this.playertarget = this.getWorld().getPlayerByUuid(uuid);
                }
            }
        }

        super.onTrackedDataSet(data);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt)
    {
        if (nbt.contains("TargetPlayer"))
        {
            UUID uuid = nbt.getUuid("TargetPlayer");
            this.dataTracker.set(PLAYER_UUID, Optional.of(uuid));
        }
        this.dataTracker.set(ANIM_COMPLETION, (float)nbt.getDouble("AnimCompletion"));

        double setX = nbt.getDouble("StartX");
        double setY = nbt.getDouble("StartY");
        double setZ = nbt.getDouble("StartZ");

        this.dataTracker.set(START_POS, new Vector3f((float)setX, (float)setY, (float)setZ));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt)
    {
        Vector3f floats = this.dataTracker.get(START_POS);
        float anim = this.dataTracker.get(ANIM_COMPLETION);
        Optional<UUID> uuid = this.dataTracker.get(PLAYER_UUID);

        uuid.ifPresent(value -> nbt.putUuid("TargetPlayer", value));

        nbt.putDouble("StartX", anim);

        nbt.putDouble("StartX", floats.x);
        nbt.putDouble("StartY", floats.y);
        nbt.putDouble("StartZ", floats.z);
    }

    @Override
    public boolean canUsePortals(boolean allowVehicles) { return false; }

    @Override
    public void onPlayerCollision(PlayerEntity player)
    {
        if (player instanceof ServerPlayerEntity)
        {
            if (!player.isCreative() && !player.isSpectator())
            {
                PlayerPersistentNBT.Sanity.setCragsMonsterKill((PlayerMixInterface) player);

                MinecraftServer server = this.getWorld().getServer();
                if (server != null)
                {
                    server.execute(() ->
                    {
                        ServerPlayNetworking.send(
                                (ServerPlayerEntity) player,
                                new CragsMonsterKillPayload(
                                        ((PlayerMixInterface)player).frontiers_1_21x$killedByCragsMonster()
                                ));
                    });
                }

                player.damage(ModDamageType.of(player.getWorld(), ModDamageType.INSANITY), 20000);
            }
            this.discard();
        }
    }

    protected Entity.MoveEffect getMoveEffect()
    {
        return MoveEffect.NONE;
    }
}
