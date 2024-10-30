package net.artyrian.frontiers.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class EntityMixin
{
    @Shadow private BlockPos blockPos;
    @Shadow private World world;
    @Shadow private Vec3d pos;

    @Shadow public World getWorld()
    {
        return this.world;
    }

    @Shadow public BlockPos getBlockPos() {
        return this.blockPos;
    }

    @Shadow public Vec3d getPos() {
        return this.pos;
    }
}
