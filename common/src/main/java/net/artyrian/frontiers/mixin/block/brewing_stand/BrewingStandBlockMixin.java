package net.artyrian.frontiers.mixin.block.brewing_stand;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
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
    @Shadow protected abstract void appendProperties(StateDefinition.Builder<Block, BlockState> builder);

    @Unique private static final BooleanProperty RODDED_PROPERTY = ModBlockProperties.HAS_ROD;
    @Unique private static final BooleanProperty LIGHTNING_0 = ModBlockProperties.LIGHTNING_0;
    @Unique private static final BooleanProperty LIGHTNING_1 = ModBlockProperties.LIGHTNING_1;
    @Unique private static final BooleanProperty LIGHTNING_2 = ModBlockProperties.LIGHTNING_2;

    @Unique private static final VoxelShape SHAPE_RODDED = Shapes.or(
            Block.box(1.0, 0.0, 1.0, 15.0, 2.0, 15.0), Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)
    );
    @Unique private static boolean isUpwardsRod(BlockState state) {
        return state.is(Blocks.LIGHTNING_ROD) && (state.getValue(BlockStateProperties.FACING) == Direction.UP);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(BlockBehaviour.Properties settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().setValue(RODDED_PROPERTY, false));
        this.setDefaultState(this.getDefaultState().setValue(LIGHTNING_0, false));
        this.setDefaultState(this.getDefaultState().setValue(LIGHTNING_1, false));
        this.setDefaultState(this.getDefaultState().setValue(LIGHTNING_2, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void implant(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(RODDED_PROPERTY);
        builder.add(LIGHTNING_0);
        builder.add(LIGHTNING_1);
        builder.add(LIGHTNING_2);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        return direction == Direction.UP
                ? state.setValue(RODDED_PROPERTY, isUpwardsRod(neighborState))
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public BlockState getPlacementState(BlockPlaceContext ctx) {
        BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().above());
        return this.getDefaultState().setValue(RODDED_PROPERTY, isUpwardsRod(blockState));
    }

    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    protected void getRoddedVoxel(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (state.getValue(RODDED_PROPERTY)) cir.setReturnValue(SHAPE_RODDED);
    }
}
