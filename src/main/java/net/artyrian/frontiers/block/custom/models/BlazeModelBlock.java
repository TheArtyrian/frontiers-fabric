package net.artyrian.frontiers.block.custom.models;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.block.entity.entity_model.BlazeModelBlockEntity;
import net.artyrian.frontiers.block.entity.entity_model.BoggedModelBlockEntity;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlazeModelBlock extends BlockWithEntity implements BlockEntityProvider
{
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final BooleanProperty MODEL_POWERED = ModBlockProperties.MODEL_POWERED;
    public static final MapCodec<BlazeModelBlock> CODEC = BlazeModelBlock.createCodec(BlazeModelBlock::new);

    private static final VoxelShape COLLIDER = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    private static final VoxelShape VOXEL_SHAPE = VoxelShapes.combineAndSimplify(
            COLLIDER, Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 16.0, 12.0), BooleanBiFunction.OR);

    public BlazeModelBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ROTATION, 0));
        this.setDefaultState(this.stateManager.getDefaultState().with(MODEL_POWERED, false));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? validateTicker(type, ModBlockEntities.BLAZE_MODEL_BLOCKENTITY, BlazeModelBlockEntity::tick) : null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return VOXEL_SHAPE; }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return COLLIDER; }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec()
    {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new BlazeModelBlockEntity(pos, state); }

    @Override
    protected BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        boolean is_receiving = ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos());
        return this.getDefaultState()
                .with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0F))
                .with(MODEL_POWERED, is_receiving);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATION);
        builder.add(MODEL_POWERED);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));}
    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) { return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));}

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClient)
        {
            boolean bl = state.get(MODEL_POWERED);
            if (bl != world.isReceivingRedstonePower(pos))
            {
                if (bl) world.scheduleBlockTick(pos, this, 4);
                else world.setBlockState(pos, state.cycle(MODEL_POWERED), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (state.get(MODEL_POWERED) && !world.isReceivingRedstonePower(pos)) world.setBlockState(pos, state.cycle(MODEL_POWERED), Block.NOTIFY_LISTENERS);
    }
}
