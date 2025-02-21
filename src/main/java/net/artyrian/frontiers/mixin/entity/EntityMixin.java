package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin
{
    @Shadow public abstract @Nullable ItemEntity dropStack(ItemStack stack);

    @Shadow public abstract boolean isPlayer();

    @Shadow @Nullable public abstract ItemEntity dropItem(ItemConvertible item);

    @Shadow public abstract double getZ();

    @Shadow public abstract double getY();

    @Shadow public abstract int getBlockZ();

    @Shadow public abstract int getBlockY();

    @Shadow public abstract int getBlockX();

    @Shadow public abstract void setRemoved(Entity.RemovalReason reason);

    @Shadow public abstract void remove(Entity.RemovalReason reason);

    @Shadow public abstract World getEntityWorld();

    @Shadow protected abstract @Nullable String getSavedEntityId();

    @Shadow public abstract boolean isRemoved();

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract double getX();
    @Shadow @Nullable public abstract MinecraftServer getServer();
    @Shadow public abstract BlockPos getWorldSpawnPos(ServerWorld world, BlockPos basePos);
    @Shadow public abstract DynamicRegistryManager getRegistryManager();
    @Shadow private BlockPos blockPos;
    @Shadow private World world;
    @Shadow private Vec3d pos;
    @Shadow @Final protected DataTracker dataTracker;

    @Shadow public abstract World getWorld();

    @Shadow public abstract BlockPos getBlockPos();

    @Shadow public abstract Vec3d getPos();

    @Shadow public abstract DataTracker getDataTracker();

    @Shadow public abstract Text getDisplayName();

    @ModifyReturnValue(method = "getPickBlockStack", at = @At("RETURN"))
    public ItemStack getPickBlockStackMix(ItemStack original)
    {
        return original;
    }

    @Inject(method = "onStoppedTrackingBy", at = @At("TAIL"))
    public void injectOnStopTrack(ServerPlayerEntity player, CallbackInfo ci)
    {

    }

    @Inject(method = "onStartedTrackingBy", at = @At("TAIL"))
    public void injectOnStartTrack(ServerPlayerEntity player, CallbackInfo ci)
    {

    }

    @Inject(method = "setCustomName", at = @At("TAIL"))
    public void injectCustomName(Text name, CallbackInfo ci)
    {

    }
}
