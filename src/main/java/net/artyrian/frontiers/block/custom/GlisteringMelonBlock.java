package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDTag;
import net.artyrian.frontiers.item.custom.tool.Unbreakable;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class GlisteringMelonBlock extends Block
{
    public static final MapCodec<GlisteringMelonBlock> CODEC = createCodec(GlisteringMelonBlock::new);

    @Override
    public MapCodec<GlisteringMelonBlock> getCodec() {
        return CODEC;
    }

    public GlisteringMelonBlock(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (!stack.isOf(Items.SHEARS) && !stack.isIn(FDTag.Items.KNIVES))
        {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }
        else if (world.isClient)
        {
            return ItemActionResult.success(world.isClient);
        }
        else
        {
            Direction direction = hit.getSide();
            Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
            world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, ModBlocks.CARVED_GLISTERING_MELON.getDefaultState().with(CarvedPumpkinBlock.FACING, direction2), Block.NOTIFY_ALL_AND_REDRAW);
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    (double)pos.getX() + 0.5 + (double)direction2.getOffsetX() * 0.65,
                    (double)pos.getY() + 0.1,
                    (double)pos.getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.65,
                    new ItemStack(Items.GOLD_NUGGET, 4)
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

            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            return ItemActionResult.success(world.isClient);
        }
    }
}
