package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.entity.*;
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
    @Shadow public abstract @Nullable ItemEntity dropStack(ItemStack stack);
    @Shadow public abstract boolean isPlayer();
    @Shadow @Nullable public abstract ItemEntity dropItem(ItemLike item);
    @Shadow public abstract double getZ();
    @Shadow public abstract double getY();
    @Shadow public abstract int getBlockZ();
    @Shadow public abstract int getBlockY();
    @Shadow public abstract int getBlockX();
    @Shadow public abstract void setRemoved(Entity.RemovalReason reason);
    @Shadow public abstract void remove(Entity.RemovalReason reason);
    @Shadow public abstract Level getEntityWorld();
    @Shadow protected abstract @Nullable String getSavedEntityId();
    @Shadow public abstract boolean isRemoved();
    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);
    @Shadow public abstract double getX();
    @Shadow @Nullable public abstract MinecraftServer getServer();
    @Shadow public abstract BlockPos getWorldSpawnPos(ServerLevel world, BlockPos basePos);
    @Shadow public abstract RegistryAccess getRegistryManager();
    @Shadow private BlockPos blockPos;
    @Shadow private Level world;
    @Shadow private Vec3 pos;
    @Shadow @Final protected SynchedEntityData dataTracker;
    @Shadow public abstract Level getWorld();
    @Shadow public abstract BlockPos getBlockPos();
    @Shadow public abstract Vec3 getPos();
    @Shadow public abstract SynchedEntityData getDataTracker();
    @Shadow public abstract Component getDisplayName();
    @Shadow protected abstract BlockPos getPosWithYOffset(float offset);
    @Shadow public abstract String toString();
    @Shadow public abstract String getUuidAsString();
    @Shadow public abstract UUID getUuid();
    @Shadow public abstract Vec3 getVelocity();
    @Shadow public abstract DamageSources getDamageSources();
    @Shadow public abstract AABB getBoundingBox();
    @Shadow public abstract boolean isSneaking();
    @Shadow public abstract EntityType<?> getType();
    @Shadow public abstract boolean shouldSpawnSprintingParticles();

    @Shadow @Final protected RandomSource random;
    @Shadow public abstract Pose getPose();
    @Shadow public int age;
    @Shadow public abstract boolean hasVehicle();
    @Shadow public abstract double squaredDistanceTo(Entity entity);
    @Shadow public abstract void refreshPositionAndAngles(Vec3 pos, float yaw, float pitch);
    @Shadow public abstract float getYaw();
    @Shadow public abstract float getPitch();
    @Shadow public abstract void refreshPositionAndAngles(double x, double y, double z, float yaw, float pitch);
    @Shadow public abstract boolean isSubmergedInWater();
    @Shadow public abstract void discard();
    @Shadow public abstract void playSoundIfNotSilent(SoundEvent event);
    @Shadow public abstract RandomSource getRandom();
    @Shadow public abstract @Nullable ItemEntity dropStack(ItemStack stack, float yOffset);
    @Shadow public abstract void setVelocity(Vec3 velocity);
    @Shadow public abstract double squaredDistanceTo(Vec3 vector);
    @Shadow public abstract int getId();
    @Shadow public abstract boolean isSpectator();
    @Shadow public abstract boolean isTouchingWater();

    @ModifyReturnValue(method = "getPickBlockStack", at = @At("RETURN"))
    public ItemStack getPickBlockStackMix(ItemStack original)
    {
        return original;
    }

    @Inject(method = "onStoppedTrackingBy", at = @At("TAIL"))
    public void injectOnStopTrack(ServerPlayer player, CallbackInfo ci)
    {

    }

    @Inject(method = "onStartedTrackingBy", at = @At("TAIL"))
    public void injectOnStartTrack(ServerPlayer player, CallbackInfo ci)
    {

    }

    @Inject(method = "setCustomName", at = @At("TAIL"))
    public void injectCustomName(Component name, CallbackInfo ci)
    {

    }
}
