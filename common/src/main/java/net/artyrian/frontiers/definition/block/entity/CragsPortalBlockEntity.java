package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CragsPortalBlockEntity extends BlockEntity
{
    protected CragsPortalBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState)
    {
        super(blockEntityType, blockPos, blockState);
    }

    public CragsPortalBlockEntity(BlockPos pos, BlockState state)
    {
        this(FRBlockEntities.CRAGS_PORTAL_BLOCKENTITY.get(), pos, state);
    }

    public boolean shouldDrawSide(Direction direction) {
        return direction.getAxis() == Direction.Axis.Y;
    }
}