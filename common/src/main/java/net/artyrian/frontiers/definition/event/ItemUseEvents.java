package net.artyrian.frontiers.definition.event;

import net.artyrian.frontiers.compat.farmersdelight.FDTag;
import net.artyrian.frontiers.definition.item.intf.Unbreakable;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ItemUseEvents
{
    public static InteractionResult tryForMelon(Player player, Level world, InteractionHand hand, BlockHitResult hitResult)
    {
        ItemStack stack = player.getItemInHand(hand);
        BlockState block = world.getBlockState(hitResult.getBlockPos());

        if (block.is(Blocks.MELON))
        {
            if (!stack.is(Items.SHEARS) && !stack.is(FDTag.Items.KNIVES))
            {
                return InteractionResult.PASS;
            }
            else if (world.isClientSide)
            {
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
            else
            {
                Direction direction = hitResult.getDirection();
                Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
                world.playSound(null, hitResult.getBlockPos(), SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                world.setBlock(hitResult.getBlockPos(), FRBlocks.CARVED_MELON.get().defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction2), Block.UPDATE_ALL_IMMEDIATE);
                ItemEntity itemEntity = new ItemEntity(
                        world,
                        (double)hitResult.getBlockPos().getX() + 0.5 + (double)direction2.getStepX() * 0.65,
                        (double)hitResult.getBlockPos().getY() + 0.1,
                        (double)hitResult.getBlockPos().getZ() + 0.5 + (double)direction2.getStepZ() * 0.65,
                        new ItemStack(Items.MELON_SEEDS, 4)
                );
                itemEntity.setDeltaMovement(
                        0.05 * (double)direction2.getStepX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getStepZ() + world.random.nextDouble() * 0.02
                );
                world.addFreshEntity(itemEntity);

                if (stack.getItem() instanceof Unbreakable toolUnb && toolUnb.getBrokenItem() != null)
                {
                    ItemStack stack2 = stack.hurtAndConvertOnBreak(1, toolUnb.getBrokenItem(), player, LivingEntity.getSlotForHand(hand));
                    if (stack2 != stack)  player.setItemInHand(hand, stack2);
                }
                else
                {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                }

                world.gameEvent(player, GameEvent.SHEAR, hitResult.getBlockPos());
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }
}
