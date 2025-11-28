package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class NecroCarpetBlock extends CarpetBlock
{
    public static final MapCodec<NecroCarpetBlock> CODEC = createCodec(NecroCarpetBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    @Override
    public MapCodec<? extends NecroCarpetBlock> getCodec() {
        return CODEC;
    }

    public NecroCarpetBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return (!context.isDescending()) ? SHAPE : VoxelShapes.empty();
    }
}
