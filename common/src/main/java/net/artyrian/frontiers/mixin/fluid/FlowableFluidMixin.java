package net.artyrian.frontiers.mixin.fluid;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(FlowingFluid.class)
public abstract class FlowableFluidMixin
{
    @ModifyReturnValue(method = "canHoldFluid", at = @At(value = "RETURN", ordinal = 2))
    private boolean checkFlowStopTag(boolean original, @Local(argsOnly = true) BlockState blockState)
    {
        Block block = blockState.getBlock();
        if (block.defaultBlockState().is(ModTags.Blocks.PREVENTS_FLUID_FLOW))
        {
            return false;
        }
        return original;
    }
}
