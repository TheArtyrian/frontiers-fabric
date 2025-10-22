package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class FruitcakeBlock extends Block
{
    public static final MapCodec<FruitcakeBlock> CODEC = createCodec(FruitcakeBlock::new);
    public static final int MAX_BITES = 6;
    public static final IntProperty BITES = Properties.BITES;
    public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
    protected static final VoxelShape[] BITES_TO_SHAPE = new VoxelShape[]
    {
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(3.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(5.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(9.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 8.0, 15.0),
            Block.createCuboidShape(13.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    };

    @Override
    public MapCodec<FruitcakeBlock> getCodec() {
        return CODEC;
    }

    public FruitcakeBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        world.playSound(player, player.getX(), player.getY(), player.getZ(), ModSounds.ITEM_GENERIC_TAKE, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (stack.isEmpty())
        {
            player.setStackInHand(hand, new ItemStack(ModItem.FRUITCAKE_SLICE));
        }
        else if (!player.getInventory().insertStack(new ItemStack(ModItem.FRUITCAKE_SLICE)))
        {
            player.dropItem(new ItemStack(ModItem.FRUITCAKE_SLICE), false);
        }

        if (state.get(BITES) < 6)
        {
            world.setBlockState(pos, state.with(BITES,state.get(BITES) + 1), Block.NOTIFY_ALL);
        }
        else
        {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

        return ItemActionResult.success(world.isClient);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return BITES_TO_SHAPE[state.get(BITES)];
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    )
    {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos)
    {
        return getComparatorOutput(state.get(BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (7 - bites) * 2;
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
}
