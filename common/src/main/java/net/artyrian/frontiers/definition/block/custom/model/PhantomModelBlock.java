package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.MagmaCubeModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.PhantomModelBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PhantomModelBlock extends EntityModelBlock
{
    public PhantomModelBlock(Properties settings)
    {
        super(settings);
    }

    private static final VoxelShape COLLIDER = Block.box(1.0, 15.0, 1.0, 15.0, 16.0, 15.0);
    private static final VoxelShape VOXEL_SHAPE = Shapes.join(
            COLLIDER, Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0), BooleanOp.OR);

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.PHANTOM_MODEL_BLOCKENTITY.get(), PhantomModelBlockEntity::tick) : null;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos.above()).isSolid();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new PhantomModelBlockEntity(pos, state); }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return VOXEL_SHAPE; }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return COLLIDER; }
}
