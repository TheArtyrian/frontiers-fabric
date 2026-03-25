package net.artyrian.frontiers.definition.item.custom.block;

import net.artyrian.frontiers.definition.block.custom.UnbreakableInDimensionBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class UnbreakableInDimBlockItem extends BlockItem
{
    public UnbreakableInDimBlockItem(Block block, Properties properties)
    {
        super(block, properties);

        // NO FUN ALLOWED :D
        if (!(block instanceof UnbreakableInDimensionBlock)) throw new IllegalArgumentException(
                "Any block provided for the UnbreakableInDimBlockItem MUST be of UnbreakableInDimensionBlock");
    }

    @Nullable @Override
    protected BlockState getPlacementState(BlockPlaceContext context)
    {
        Player player = context.getPlayer();
        BlockState blockstate = this.getBlock().getStateForPlacement(context);
        if (blockstate != null) return ((this.canPlace(context, blockstate) || player.isCreative())) ? blockstate : null;
        return null;
    }
}
