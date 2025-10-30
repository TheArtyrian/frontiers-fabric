package net.artyrian.frontiers.mixin.block;

import net.artyrian.frontiers.block.custom.PersonalChestBlock;
import net.artyrian.frontiers.block.custom.UnbreakableInDimensionBlock;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin
{
    @Shadow protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    )
    {
        return null;
    };
}
