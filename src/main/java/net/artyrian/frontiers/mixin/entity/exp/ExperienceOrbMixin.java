package net.artyrian.frontiers.mixin.entity.exp;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_interfaces.ExpMixImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbMixin extends EntityMixin implements ExpMixImpl
{
    @Shadow private PlayerEntity target;
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
                        !this.getWorld().getBlockState(this.frontiers$magnetPosIfFound).isOf(ModBlocks.ENCHANTING_MAGNET) ||
                        !frontiers$magnetPosIfFound.isWithinDistance(this.getPos(), 32)
                )
        )
        {
            this.frontiers$magnetPosIfFound = null;
        }

        if (this.frontiers$magnetPosIfFound != null)
        {
            Vec3d centerPos = frontiers$magnetPosIfFound.toCenterPos();
            Vec3d vec3d = new Vec3d(
                    centerPos.getX() - this.getX(), centerPos.getY() - this.getY(), centerPos.getZ() - this.getZ()
            );

            double d = vec3d.lengthSquared();
            double e_unclamp = 1.0 - Math.sqrt(d) / 8.0;
            double e = Math.clamp(e_unclamp, 0.4, 1.0);

            this.setVelocity(this.getVelocity().add(vec3d.normalize().multiply(e * e * 0.2, e_unclamp * e_unclamp * 0.2, e * e * 0.2)));
        }
    }

    @Inject(method = "expensiveUpdate", at = @At("TAIL"))
    private void frontiersExpUpdateInj(CallbackInfo ci)
    {
        BlockBox surround = BlockBox.create(this.getBlockPos().add(-12, -12, -12), this.getBlockPos().add(12, 12, 12));

        BlockPos minn = new BlockPos(surround.getMinX(), surround.getMinY(), surround.getMinZ());
        BlockPos maxx = new BlockPos(surround.getMaxX(), surround.getMaxY(), surround.getMaxZ());

        boolean found = false;
        for (BlockPos pos : BlockPos.iterate(minn, maxx))
        {
            if (this.getWorld().getBlockState(pos).isOf(ModBlocks.ENCHANTING_MAGNET))
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
