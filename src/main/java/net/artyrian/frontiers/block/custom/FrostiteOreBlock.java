package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class FrostiteOreBlock extends TranslucentBlock
{
    public static final MapCodec<FrostiteOreBlock> CODEC = createCodec(FrostiteOreBlock::new);

    public MapCodec<? extends FrostiteOreBlock> getCodec() {
        return CODEC;
    }

    public FrostiteOreBlock(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    public static BlockState getMeltedState() {
        return Blocks.WATER.getDefaultState();
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) {
            this.melt(state, world, pos);
            ItemEntity ore = new ItemEntity(world,
                    pos.getX() + .5d,
                    pos.getY() + .1d,
                    pos.getZ() + .5d,
                    new ItemStack(ModItem.RAW_FROSTITE, 1)
            );
            ore.setVelocity(
                    .05d * (world.getRandom().nextDouble() * .02d),
                    .05d,
                    .05d * (world.getRandom().nextDouble() * 0.02D));
            world.spawnEntity(ore);
        }

    }

    protected void melt(BlockState state, World world, BlockPos pos) {
        if (world.getDimension().ultrawarm()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, getMeltedState());
            world.updateNeighbor(pos, getMeltedState().getBlock(), pos);
        }
    }
}
