package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.item.EndCrystalItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class PurifiedEndCrystalItem extends EndCrystalItem
{
    public PurifiedEndCrystalItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = world.getBlockState(blockPos);

        BlockPos blockPos2 = blockPos.above();
        if (!world.isEmptyBlock(blockPos2))
        {
            return InteractionResult.FAIL;
        }
        else
        {
            boolean use_stand = (blockState.is(Blocks.STONE_BRICKS));
            double d = (double) blockPos2.getX();
            double e = (double) blockPos2.getY();
            double f = (double) blockPos2.getZ();
            List<Entity> list = world.getEntities(null, new AABB(d, e, f, d + 1.0, e + 2.0, f + 1.0));
            if (!list.isEmpty())
            {
                return InteractionResult.FAIL;
            }
            else
            {
                if (world instanceof ServerLevel)
                {
                    EndCrystal endCrystalEntity = new EndCrystal(world, d + 0.5, e, f + 0.5);
                    if (!use_stand) endCrystalEntity.setShowBottom(false);
                    ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$setFriendly(true);

                    world.addFreshEntity(endCrystalEntity);
                    world.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos2);
                }

                context.getItemInHand().shrink(1);
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
    }
}
