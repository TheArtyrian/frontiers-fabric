package net.artyrian.frontiers.mixin.block.fences;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FenceBlock.class)
public abstract class FenceMixin extends BlockMixin
{
    @ModifyReturnValue(method = "connectsTo", at = @At("RETURN"))
    private boolean alsoCheckStoneGate(boolean original,
                                       @Local(argsOnly = true) BlockState state,
                                       @Local(argsOnly = true) Direction side,
                                       @Local(argsOnly = true) boolean faceFullSquare)
    {
        Block block = state.getBlock();
        boolean bl =
                (
                        block instanceof net.artyrian.frontiers.definition.block.custom.StoneFenceGateBlock &&
                        net.artyrian.frontiers.definition.block.custom.StoneFenceGateBlock.canWallConnect(state, side) &&
                        !this.defaultBlockState().is(BlockTags.WOODEN_FENCES)
                );
        return original || bl;
    }
}
