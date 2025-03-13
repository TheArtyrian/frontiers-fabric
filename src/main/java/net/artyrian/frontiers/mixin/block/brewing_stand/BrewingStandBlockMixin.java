package net.artyrian.frontiers.mixin.block.brewing_stand;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(BrewingStandBlock.class)
public abstract class BrewingStandBlockMixin extends BlockMixin
{
    @Shadow protected abstract void appendProperties(StateManager.Builder<Block, BlockState> builder);

    @Unique private static final BooleanProperty RODDED_PROPERTY = ModBlockProperties.HAS_ROD;
    @Unique private static final BooleanProperty LIGHTNING_0 = ModBlockProperties.LIGHTNING_0;
    @Unique private static final BooleanProperty LIGHTNING_1 = ModBlockProperties.LIGHTNING_1;
    @Unique private static final BooleanProperty LIGHTNING_2 = ModBlockProperties.LIGHTNING_2;

    @Unique private static final VoxelShape SHAPE_RODDED = VoxelShapes.union(
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 2.0, 15.0), Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)
    );
    @Unique private static boolean isUpwardsRod(BlockState state) {
        return state.isOf(Blocks.LIGHTNING_ROD) && (state.get(Properties.FACING) == Direction.UP);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(RODDED_PROPERTY, false));
        this.setDefaultState(this.getDefaultState().with(LIGHTNING_0, false));
        this.setDefaultState(this.getDefaultState().with(LIGHTNING_1, false));
        this.setDefaultState(this.getDefaultState().with(LIGHTNING_2, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void implant(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(RODDED_PROPERTY);
        builder.add(LIGHTNING_0);
        builder.add(LIGHTNING_1);
        builder.add(LIGHTNING_2);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos)
    {
        return direction == Direction.UP
                ? state.with(RODDED_PROPERTY, isUpwardsRod(neighborState))
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
        return this.getDefaultState().with(RODDED_PROPERTY, isUpwardsRod(blockState));
    }

    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    protected void getRoddedVoxel(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.get(RODDED_PROPERTY)) cir.setReturnValue(SHAPE_RODDED);
    }
}
