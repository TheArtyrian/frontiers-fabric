package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class ExperiwinkleCropBlock extends CropBlock implements Fertilizable
{
    public static final MapCodec<ExperiwinkleCropBlock> CODEC = createCodec(ExperiwinkleCropBlock::new);
    public static final IntProperty AGE = Properties.AGE_1;
    private static final VoxelShape[] SHAPES = new VoxelShape[]
    {
            Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 6.0, 10.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0)
    };

    @Override
    public MapCodec<ExperiwinkleCropBlock> getCodec() {
        return CODEC;
    }

    public ExperiwinkleCropBlock(Settings settings) { super(settings); }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return SHAPES[this.getAge(state)];
    }

    @Override
    protected IntProperty getAgeProperty()
    {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItem.EXPERIWINKLE_BULB;
    }

    @Override
    public BlockState withAge(int age)
    {
        return age == 2 ? ModBlocks.EXPERIWINKLE.getDefaultState() : super.withAge(age);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (random.nextInt(3) != 0)
        {
            super.randomTick(state, world, pos, random);
        }
    }

    @Override
    protected int getGrowthAmount(World world) {
        return 1;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return false;
    }
}
