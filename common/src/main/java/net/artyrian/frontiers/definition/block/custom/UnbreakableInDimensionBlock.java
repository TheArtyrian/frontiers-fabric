package net.artyrian.frontiers.definition.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class UnbreakableInDimensionBlock extends Block
{
    private final ResourceKey<Level> DIMENSION;

    public UnbreakableInDimensionBlock(ResourceKey<Level> type, Properties settings)
    {
        super(settings);
        this.DIMENSION = type;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        ResourceKey<Level> c = ((Level)world).dimension();
        if (c.equals(DIMENSION))
        {
            return false;
        }
        return super.canSurvive(state, world, pos);
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
    {
        Block bloque = state.getBlock();
        if (bloque instanceof UnbreakableInDimensionBlock block && block.getUnbreakableDimension().equals(player.level().dimension()))
        {
            return 0.0F;
        }
        return super.getDestroyProgress(state, player, world, pos);
    }

    public ResourceKey<Level> getUnbreakableDimension()
    {
        return DIMENSION;
    }
}
