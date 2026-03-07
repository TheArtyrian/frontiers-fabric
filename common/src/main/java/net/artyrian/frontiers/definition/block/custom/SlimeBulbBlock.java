package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SlimeBulbBlock extends Block
{
    public static final MapCodec<SlimeBulbBlock> CODEC = simpleCodec(SlimeBulbBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);
    protected static final VoxelShape SHAPE_COLL = Block.box(2.0, 0.0, 2.0, 14.0, 5.0, 14.0);

    public SlimeBulbBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    public MapCodec<SlimeBulbBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPE; }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPE_COLL; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(AGE, 1);
    }

    protected IntegerProperty getAge() {
        return AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        int i = state.getValue(AGE);
        if (i != 0 && i < 3 && random.nextIntBetweenInclusive(0, 19) == 0)
        {
            BlockState blockState = state.setValue(AGE, i + 1);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        int i = state.getValue(AGE);
        if (i == 3)
        {
            world.playSound(null, pos, ModSounds.SLIME_BULB_PICK.get(), SoundSource.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            popResource(world, pos, new ItemStack(ModItem.HARDENED_SLIME.get(), 1));

            BlockState blockState = state.setValue(AGE, 0);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        else return super.useWithoutItem(state, world, pos, player, hit);
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    ) {
        return !state.canSurvive(world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return !world.isEmptyBlock(pos.below());
    }
}
