package net.artyrian.frontiers.entity.projectile;

import com.mojang.logging.LogUtils;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class CobaltBobber extends FishingBobberEntity
{
    private static final Logger LOGGER = LogUtils.getLogger();
    private final Random velocityRandom = Random.create();
    private boolean caughtFish;
    private int outOfOpenWaterTicks;
    private static final int field_30665 = 10;
    private static final TrackedData<Integer> HOOK_ENTITY_ID = DataTracker.registerData(CobaltBobber.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> CAUGHT_FISH = DataTracker.registerData(CobaltBobber.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int removalTimer;
    private int hookCountdown;
    private int waitCountdown;
    private int fishTravelCountdown;
    private float fishAngle;
    private boolean inOpenWater = true;
    @Nullable
    private Entity hookedEntity;
    private CobaltBobber.State state = CobaltBobber.State.FLYING;
    private final int luckBonus = 0;
    private final int waitTimeReductionTicks = 0;

    public CobaltBobber(EntityType<? extends FishingBobberEntity> type, World world, int luckBonus, int waitTimeReductionTicks) {
        super(type, world);
    }

    public CobaltBobber(EntityType<? extends CobaltBobber> entityType, World world)
    {
        super(entityType, world);
    }

    public CobaltBobber(PlayerEntity thrower, World world, int luckBonus, int waitTimeReductionTicks) {
        this(ModEntity.COBALT_BOBBER, world, luckBonus, waitTimeReductionTicks);
        this.setOwner(thrower);
        float f = thrower.getPitch();
        float g = thrower.getYaw();
        float h = MathHelper.cos(-g * (float) (Math.PI / 180.0) - (float) Math.PI);
        float i = MathHelper.sin(-g * (float) (Math.PI / 180.0) - (float) Math.PI);
        float j = -MathHelper.cos(-f * (float) (Math.PI / 180.0));
        float k = MathHelper.sin(-f * (float) (Math.PI / 180.0));
        double d = thrower.getX() - (double)i * 0.3;
        double e = thrower.getEyeY();
        double l = thrower.getZ() - (double)h * 0.3;
        this.refreshPositionAndAngles(d, e, l, g, f);
        Vec3d vec3d = new Vec3d((double)(-i), (double)MathHelper.clamp(-(k / j), -5.0F, 5.0F), (double)(-h));
        double m = vec3d.length();
        vec3d = vec3d.multiply(
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365),
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365),
                0.6 / m + this.random.nextTriangular(0.5, 0.0103365)
        );
        this.setVelocity(vec3d);
        this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 180.0F / (float)Math.PI));
        this.setPitch((float)(MathHelper.atan2(vec3d.y, vec3d.horizontalLength()) * 180.0F / (float)Math.PI));
        this.prevYaw = this.getYaw();
        this.prevPitch = this.getPitch();
    }

    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(ModItem.COBALT_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(ModItem.COBALT_FISHING_ROD);
        if (!player.isRemoved() && player.isAlive() && (bl || bl2) && !(this.squaredDistanceTo(player) > 1024.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    private void setPlayerFishHook(@Nullable CobaltBobber fishingBobber) {
        PlayerEntity playerEntity = this.getPlayerOwner();
        if (playerEntity != null) {
            playerEntity.fishHook = fishingBobber;
        }
    }

    static enum PositionType {
        ABOVE_WATER,
        INSIDE_WATER,
        INVALID;
    }

    static enum State {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }
}
