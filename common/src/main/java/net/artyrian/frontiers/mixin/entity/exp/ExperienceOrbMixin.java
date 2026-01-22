package net.artyrian.frontiers.mixin.entity.exp;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.ExpMixImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.util.math.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbMixin extends EntityMixin implements ExpMixImpl
{
    @Shadow private Player target;
    @Shadow private int pickingCount;
    @Unique
    private BlockPos frontiers$magnetPosIfFound;

    @Inject(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/ExperienceOrbEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V",
            shift = At.Shift.BEFORE))
    private void frontiersMixMagnetMove(CallbackInfo ci)
    {
        if (this.frontiers$magnetPosIfFound != null &&
                (
                        !this.getWorld().getBlockState(this.frontiers$magnetPosIfFound).is(ModBlocks.ENCHANTING_MAGNET) ||
                        !frontiers$magnetPosIfFound.closerToCenterThan(this.getPos(), 32)
                )
        )
        {
            this.frontiers$magnetPosIfFound = null;
        }

        if (this.frontiers$magnetPosIfFound != null)
        {
            Vec3 centerPos = frontiers$magnetPosIfFound.getCenter();
            Vec3 vec3d = new Vec3(
                    centerPos.x() - this.getX(), centerPos.y() - this.getY(), centerPos.z() - this.getZ()
            );

            double d = vec3d.lengthSqr();
            double e_unclamp = 1.0 - Math.sqrt(d) / 8.0;
            double e = Math.clamp(e_unclamp, 0.4, 1.0);

            this.setVelocity(this.getVelocity().add(vec3d.normalize().multiply(e * e * 0.2, e_unclamp * e_unclamp * 0.2, e * e * 0.2)));
        }
    }

    @Inject(method = "expensiveUpdate", at = @At("TAIL"))
    private void frontiersExpUpdateInj(CallbackInfo ci)
    {
        BoundingBox surround = BoundingBox.fromCorners(this.getBlockPos().offset(-12, -12, -12), this.getBlockPos().offset(12, 12, 12));

        BlockPos minn = new BlockPos(surround.minX(), surround.minY(), surround.minZ());
        BlockPos maxx = new BlockPos(surround.maxX(), surround.maxY(), surround.maxZ());

        boolean found = false;
        for (BlockPos pos : BlockPos.betweenClosed(minn, maxx))
        {
            if (this.getWorld().getBlockState(pos).is(ModBlocks.ENCHANTING_MAGNET))
            {
                this.frontiers$magnetPosIfFound = pos;
                found = true;
                break;
            }
        }

        if (found) { if (this.target != null) this.target = null; }
        else if (this.frontiers$magnetPosIfFound != null) this.frontiers$magnetPosIfFound = null;
    }

    @ModifyExpressionValue(method = "expensiveUpdate", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;squaredDistanceTo(Lnet/minecraft/entity/Entity;)D"))
    private double frontiersWrapForMagnet(double original)
    {
        if (this.frontiers$magnetPosIfFound != null)
        {
            if (this.target != null) this.target = null;
            return -64.0F;
        }
        return original;
    }

    @Override @Nullable
    public BlockPos frontiers$getXPBlockPos() { return frontiers$magnetPosIfFound; }

    @Override
    public void frontiers$subtractCount()
    {
        this.pickingCount--;
        if (this.pickingCount == 0) this.discard();
    }
}
