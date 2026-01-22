package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(Entity.class)
public abstract class EntityMixin
{
    @Shadow public abstract @Nullable ItemEntity spawnAtLocation(ItemStack stack);
    @Shadow public abstract boolean isAlwaysTicking();
    @Shadow @Nullable public abstract ItemEntity spawnAtLocation(ItemLike item);
    @Shadow public abstract double getZ();
    @Shadow public abstract double getY();
    @Shadow public abstract int getBlockZ();
    @Shadow public abstract int getBlockY();
    @Shadow public abstract int getBlockX();
    @Shadow public abstract void setRemoved(Entity.RemovalReason reason);
    @Shadow public abstract void remove(Entity.RemovalReason reason);
    @Shadow public abstract Level getCommandSenderWorld();
    @Shadow protected abstract @Nullable String getEncodeId();
    @Shadow public abstract boolean isRemoved();
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);
    @Shadow public abstract double getX();
    @Shadow @Nullable public abstract MinecraftServer getServer();
    @Shadow public abstract BlockPos adjustSpawnLocation(ServerLevel world, BlockPos basePos);
    @Shadow public abstract RegistryAccess registryAccess();
    @Shadow private BlockPos blockPosition;
    @Shadow private Level level;
    @Shadow private Vec3 position;
    @Shadow @Final protected SynchedEntityData entityData;
    @Shadow public abstract Level level();
    @Shadow public abstract BlockPos blockPosition();
    @Shadow public abstract Vec3 position();
    @Shadow public abstract SynchedEntityData getEntityData();
    @Shadow public abstract Component getDisplayName();
    @Shadow protected abstract BlockPos getOnPos(float offset);
    @Shadow public abstract String toString();
    @Shadow public abstract String getStringUUID();
    @Shadow public abstract UUID getUUID();
    @Shadow public abstract Vec3 getDeltaMovement();
    @Shadow public abstract DamageSources damageSources();
    @Shadow public abstract AABB getBoundingBox();
    @Shadow public abstract boolean isShiftKeyDown();
    @Shadow public abstract EntityType<?> getType();
    @Shadow public abstract boolean canSpawnSprintParticle();

    @Shadow @Final protected RandomSource random;
    @Shadow public abstract Pose getPose();
    @Shadow public int tickCount;
    @Shadow public abstract boolean isPassenger();
    @Shadow public abstract double distanceToSqr(Entity entity);
    @Shadow public abstract void moveTo(Vec3 pos, float yaw, float pitch);
    @Shadow public abstract float getYRot();
    @Shadow public abstract float getXRot();
    @Shadow public abstract void moveTo(double x, double y, double z, float yaw, float pitch);
    @Shadow public abstract boolean isUnderWater();
    @Shadow public abstract void discard();
    @Shadow public abstract void playSound(SoundEvent event);
    @Shadow public abstract RandomSource getRandom();
    @Shadow public abstract @Nullable ItemEntity spawnAtLocation(ItemStack stack, float yOffset);
    @Shadow public abstract void setDeltaMovement(Vec3 velocity);
    @Shadow public abstract double distanceToSqr(Vec3 vector);
    @Shadow public abstract int getId();
    @Shadow public abstract boolean isSpectator();
    @Shadow public abstract boolean isInWater();

    @ModifyReturnValue(method = "getPickResult", at = @At("RETURN"))
    public ItemStack getPickBlockStackMix(ItemStack original)
    {
        return original;
    }

    @Inject(method = "stopSeenByPlayer", at = @At("TAIL"))
    public void injectOnStopTrack(ServerPlayer player, CallbackInfo ci)
    {

    }

    @Inject(method = "startSeenByPlayer", at = @At("TAIL"))
    public void injectOnStartTrack(ServerPlayer player, CallbackInfo ci)
    {

    }

    @Inject(method = "setCustomName", at = @At("TAIL"))
    public void injectCustomName(Component name, CallbackInfo ci)
    {

    }
}
