package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NecroCarpetBlock extends CarpetBlock
{
    public static final MapCodec<NecroCarpetBlock> CODEC = simpleCodec(NecroCarpetBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    @Override
    public MapCodec<? extends NecroCarpetBlock> codec() {
        return CODEC;
    }

    public NecroCarpetBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return (!context.isDescending()) ? SHAPE : Shapes.empty();
    }
}
