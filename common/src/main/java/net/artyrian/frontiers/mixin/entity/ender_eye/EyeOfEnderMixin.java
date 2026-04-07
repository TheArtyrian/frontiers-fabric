package net.artyrian.frontiers.mixin.entity.ender_eye;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EyeOfEnder.class)
public abstract class EyeOfEnderMixin extends EntityMixin
{
    @ModifyReturnValue(method = "getDefaultItem", at = @At("RETURN"))
    private ItemStack frontiers$changeToVoidPearl(ItemStack original)
    {
        return new ItemStack(FRItems.VOID_PEARL.get());
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"))
    private void frontiers$changeToVectorEvent(Level instance, int event, BlockPos blockPos, int data, Operation<Void> original)
    {
        VectorEventSync.Dual.fireEvent(instance, this.position(), this.position(), FRLevelEvents.Dual.VOID_OR_ENDER_EYE_SMASH, 0);
    }
}
