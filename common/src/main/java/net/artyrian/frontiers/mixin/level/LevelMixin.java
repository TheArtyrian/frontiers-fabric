package net.artyrian.frontiers.mixin.level;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin
{
    @Inject(
            method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;II)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/chunk/LevelChunk;setBlockState(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;",
                    shift = At.Shift.AFTER)
    )
    private void frontiers$adjustSnowMeltChecks(BlockPos pos, BlockState state, int flags, int recursionLeft, CallbackInfoReturnable<Boolean> cir)
    {
        Level self = (Level)(Object)this;
        if (
                state.isCollisionShapeFullBlock(self, pos)
                && self.getBiome(pos.below()).value().coldEnoughToSnow(pos.below())
                && self instanceof ServerLevel server
        )
        {
            MinecraftServer mcSer = self.getServer();
            if (mcSer != null)
            {
                StateSaveLoad serverState = StateSaveLoad.getServerState(mcSer);
                if (serverState.snowMeltPos.contains(pos.below())) serverState.snowMeltPos.remove(pos.below());
            }
        }
    }
}
