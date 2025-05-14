package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDTag;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
import org.jetbrains.annotations.Nullable;

public class MelonBlock extends Block
{
    public static final MapCodec<MelonBlock> CODEC = createCodec(MelonBlock::new);

    @Override
    public MapCodec<MelonBlock> getCodec() {
        return CODEC;
    }

    public MelonBlock(Settings settings)
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
            world.setBlockState(pos, ModBlocks.CARVED_MELON.getDefaultState().with(CarvedPumpkinBlock.FACING, direction2), Block.NOTIFY_ALL_AND_REDRAW);
            ItemEntity itemEntity = new ItemEntity(
                    world,
                    (double)pos.getX() + 0.5 + (double)direction2.getOffsetX() * 0.65,
                    (double)pos.getY() + 0.1,
                    (double)pos.getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.65,
                    new ItemStack(Items.MELON_SEEDS, 4)
            );
            itemEntity.setVelocity(
                    0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02
            );
            world.spawnEntity(itemEntity);
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            world.emitGameEvent(player, GameEvent.SHEAR, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
            return ItemActionResult.success(world.isClient);
        }
    }
}
