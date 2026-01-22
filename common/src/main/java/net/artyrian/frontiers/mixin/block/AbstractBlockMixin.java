package net.artyrian.frontiers.mixin.block;

import net.artyrian.frontiers.block.custom.PersonalChestBlock;
import net.artyrian.frontiers.block.custom.UnbreakableInDimensionBlock;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(BlockBehaviour.class)
public abstract class AbstractBlockMixin
{
    @Shadow protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    )
    {
        return null;
    };
}
