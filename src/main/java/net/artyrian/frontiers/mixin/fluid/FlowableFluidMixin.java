package net.artyrian.frontiers.mixin.fluid;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(FlowableFluid.class)
public abstract class FlowableFluidMixin
{
    @ModifyReturnValue(method = "canFill", at = @At(value = "RETURN", ordinal = 2))
    private boolean checkFlowStopTag(boolean original, @Local(argsOnly = true) BlockState blockState)
    {
        Block block = blockState.getBlock();
        if (block.getDefaultState().isIn(ModTags.Blocks.PREVENTS_FLUID_FLOW))
        {
            return false;
        }
        return original;
    }
}
