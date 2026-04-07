package net.artyrian.frontiers.mixin.block.anvil;

import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilBlock.class)
public class AnvilMixin
{
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private static void frontiers$returnSelf(BlockState fallingState, CallbackInfoReturnable<BlockState> cir)
    {
        if (fallingState.is(FRBlocks.VIVULITE_ANVIL.get()))
        {
            cir.setReturnValue(fallingState);
            cir.cancel();
        }
    }
}
