package net.artyrian.frontiers.block.entity.portal;

import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class CragsPortalBlockEntity extends BlockEntity
{
    protected CragsPortalBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState)
    {
        super(blockEntityType, blockPos, blockState);
    }

    public CragsPortalBlockEntity(BlockPos pos, BlockState state)
    {
        this(ModBlockEntities.CRAGS_PORTAL_BLOCKENTITY, pos, state);
    }

    public boolean shouldDrawSide(Direction direction) {
        return direction.getAxis() == Direction.Axis.Y;
    }
}