package net.artyrian.frontiers.mixin.block.fences;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.custom.StoneFenceGateBlock;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceMixin extends BlockMixin
{
    @ModifyReturnValue(method = "canConnect", at = @At("RETURN"))
    private boolean alsoCheckStoneGate(boolean original,
                                       @Local(argsOnly = true) BlockState state,
                                       @Local(argsOnly = true) Direction side,
                                       @Local(argsOnly = true) boolean faceFullSquare)
    {
        Block block = state.getBlock();
        boolean bl =
                (
                        block instanceof StoneFenceGateBlock &&
                        StoneFenceGateBlock.canWallConnect(state, side) &&
                        !this.getDefaultState().isIn(BlockTags.WOODEN_FENCES)
                );
        return original || bl;
    }
}
