package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AncientRoseCropBlock extends CropBlock
{
    // Hi GitHub! Let's pretend like I know what ANY of this means! :}
    // update 5-16-25 i now know what all this means
    public static final MapCodec<AncientRoseCropBlock> CODEC = createCodec(AncientRoseCropBlock::new);
    public static final IntProperty AGE = Properties.AGE_5;
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 8.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 9.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0)
    };

    // Inherit super (idol lol).
    public AncientRoseCropBlock(Settings settings)
    {
        super(settings);
    }

    // wtf is a codec
    @Override
    public MapCodec<AncientRoseCropBlock> getCodec() {
        return CODEC;
    }

    // Get the shape of the outline
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[this.getAge(state)];
    }

    // Return same way as Torchflower - turn into actual flower if at max.
    @Override
    public BlockState withAge(int age) {
        return age == 6 ? ModBlocks.ANCIENT_ROSE.getDefaultState() : super.withAge(age);
    }

    // Get block max age.
    @Override
    public int getMaxAge()
    {
        return 6;
    }

    // Get age property.
    @Override
    protected IntProperty getAgeProperty()
    {
        return AGE;
    }

    // Return ancient rose seed.
    @Override
    protected ItemConvertible getSeedsItem()
    {
        return ModItem.ANCIENT_ROSE_SEED;
    }

    // Append age properties.
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }

    // Add butterfinger to orlando :smiling_imp:
    @Override
    protected int getGrowthAmount(World world) {
        return 1;
    }
}
