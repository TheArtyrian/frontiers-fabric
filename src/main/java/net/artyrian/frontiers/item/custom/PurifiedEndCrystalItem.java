package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.mixin_interfaces.EndCrystalMixInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.EndCrystalItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class PurifiedEndCrystalItem extends EndCrystalItem
{
    public PurifiedEndCrystalItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        BlockPos blockPos2 = blockPos.up();
        if (!world.isAir(blockPos2))
        {
            return ActionResult.FAIL;
        }
        else
        {
            boolean use_stand = (blockState.isOf(Blocks.STONE_BRICKS));
            double d = (double) blockPos2.getX();
            double e = (double) blockPos2.getY();
            double f = (double) blockPos2.getZ();
            List<Entity> list = world.getOtherEntities(null, new Box(d, e, f, d + 1.0, e + 2.0, f + 1.0));
            if (!list.isEmpty())
            {
                return ActionResult.FAIL;
            }
            else
            {
                if (world instanceof ServerWorld)
                {
                    EndCrystalEntity endCrystalEntity = new EndCrystalEntity(world, d + 0.5, e, f + 0.5);
                    if (!use_stand) endCrystalEntity.setShowBottom(false);
                    ((EndCrystalMixInterface)endCrystalEntity).frontiers_1_21x$setFriendly(true);

                    world.spawnEntity(endCrystalEntity);
                    world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos2);
                }

                context.getStack().decrement(1);
                return ActionResult.success(world.isClient);
            }
        }
    }
}
