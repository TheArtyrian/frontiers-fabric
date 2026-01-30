package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.BoggedModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.SkeletonModelBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SkeletonModelBlock extends EntityModelBlock
{
    public SkeletonModelBlock(Properties settings)
    {
        super(settings);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.SKELETON_MODEL_BLOCKENTITY.get(), SkeletonModelBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new SkeletonModelBlockEntity(pos, state); }
}
