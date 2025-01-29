package net.artyrian.frontiers.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class EntityMixin
{
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
}
