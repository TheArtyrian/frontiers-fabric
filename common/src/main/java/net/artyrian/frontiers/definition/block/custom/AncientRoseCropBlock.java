package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class AncientRoseCropBlock extends CropBlock
{
    public static final MapCodec<AncientRoseCropBlock> CODEC = simpleCodec(AncientRoseCropBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private static final VoxelShape[] SHAPES = new VoxelShape[]
    {
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 9.0, 11.0),
            Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0)
    };

    public AncientRoseCropBlock(Properties settings)
    {
        super(settings);
    }

    @Override @NotNull
    public MapCodec<AncientRoseCropBlock> codec() {
        return CODEC;
    }

    @Override @NotNull
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPES[this.getAge(state)];
    }

    @Override @NotNull
    public BlockState getStateForAge(int age)
    {
        return age == 6 ? FRBlocks.ANCIENT_ROSE.get().defaultBlockState() : super.getStateForAge(age);
    }

    @Override
    public int getMaxAge()
    {
        return 6;
    }

    @Override
    protected @NotNull IntegerProperty getAgeProperty()
    {
        return AGE;
    }

    @Override @NotNull
    protected ItemLike getBaseSeedId()
    {
        return FRItems.ANCIENT_ROSE_SEED.get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    @Override
    protected int getBonemealAgeIncrease(@NotNull Level world) {
        return 1;
    }
}
