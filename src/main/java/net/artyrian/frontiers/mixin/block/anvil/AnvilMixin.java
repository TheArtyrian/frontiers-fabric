package net.artyrian.frontiers.mixin.block.anvil;

import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilBlock.class)
public class AnvilMixin
{
    @Inject(method = "getLandingState", at = @At("HEAD"), cancellable = true)
    private static void returnSelf(BlockState fallingState, CallbackInfoReturnable<BlockState> cir)
    {
        if (fallingState.isOf(ModBlocks.VIVULITE_ANVIL))
        {
            cir.setReturnValue(fallingState);
            cir.cancel();
        }
    }
}
