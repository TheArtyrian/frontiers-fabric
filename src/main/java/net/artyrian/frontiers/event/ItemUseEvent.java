package net.artyrian.frontiers.event;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDTag;
import net.artyrian.frontiers.item.custom.tool.Unbreakable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;

public class ItemUseEvent
{
    public static void doReg()
    {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            BlockState block = world.getBlockState(hitResult.getBlockPos());

            if (block.isOf(Blocks.MELON))
            {
                if (!stack.isOf(Items.SHEARS) && !stack.isIn(FDTag.Items.KNIVES))
                {
                    return ActionResult.PASS;
                }
                else if (world.isClient)
                {
                    return ActionResult.success(world.isClient);
                }
                else
                {
                    Direction direction = hitResult.getSide();
                    Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                    world.playSound(null, hitResult.getBlockPos(), SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(hitResult.getBlockPos(), ModBlocks.CARVED_MELON.getDefaultState().with(CarvedPumpkinBlock.FACING, direction2), Block.NOTIFY_ALL_AND_REDRAW);
                    ItemEntity itemEntity = new ItemEntity(
                            world,
                            (double)hitResult.getBlockPos().getX() + 0.5 + (double)direction2.getOffsetX() * 0.65,
                            (double)hitResult.getBlockPos().getY() + 0.1,
                            (double)hitResult.getBlockPos().getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.65,
                            new ItemStack(Items.MELON_SEEDS, 4)
                    );
                    itemEntity.setVelocity(
                            0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02
                    );
                    world.spawnEntity(itemEntity);

                    if (stack.getItem() instanceof Unbreakable toolUnb && toolUnb.getBrokenItem() != null)
                    {
                        ItemStack stack2 = stack.damage(1, toolUnb.getBrokenItem(), player, LivingEntity.getSlotForHand(hand));
                        if (stack2 != stack)  player.setStackInHand(hand, stack2);
                    }
                    else
                    {
                        stack.damage(1, player, LivingEntity.getSlotForHand(hand));
                    }

                    world.emitGameEvent(player, GameEvent.SHEAR, hitResult.getBlockPos());
                    player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
                    return ActionResult.success(world.isClient);
                }
            }
            return ActionResult.PASS;
        });
    }
}
