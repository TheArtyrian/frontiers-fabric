package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.item.custom.tool.Unbreakable;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.item.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;

// (Why do I decide to port this instead of doing some modCompile thing instead)

// A quasi-reimplementation of FarmersDelight's knife item - used to avoid dependencies & implement custom logic. The original code can be found here:
// https://github.com/MehVahdJukaar/FarmersDelightRefabricated/blob/fabric/1.21/src/main/java/vectorwing/farmersdelight/common/item/KnifeItem.java
public class KnifeItem extends DiggerItem
{
    // Unrelated but like every time I see super I can only think of Super Idol :'}
    public KnifeItem(Tier material, Properties settings)
    {
        super(material, FDTag.Blocks.MINEABLE_KNIFE, settings);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player miner) {
        return !miner.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        ItemStack tool = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Direction facing = context.getClickedFace();

        if (state.getBlock() == Blocks.PUMPKIN && tool.is(FDTag.Items.KNIVES)) {
            Player player = context.getPlayer();
            if (player != null && !world.isClientSide()) {
                Direction direction = facing.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : facing;
                world.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.f, 1.f);
                world.setBlock(pos, Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction), 11);
                ItemEntity itemEntity = new ItemEntity(world,
                        pos.getX() + .5d + direction.getStepX() * .65d,
                        pos.getY() + .1d, pos.getZ() + .5d + direction.getStepZ() * .65d,
                        new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itemEntity.setDeltaMovement(
                        .05d * direction.getStepX() + world.getRandom().nextDouble() * .02d,
                        .05d,
                        .05d * direction.getStepZ() + world.getRandom().nextDouble() * 0.02D);
                world.addFreshEntity(itemEntity);

                if (tool.getItem() instanceof Unbreakable toolUnb && toolUnb.getBrokenItem() != null)
                {
                    ItemStack stack2 = tool.hurtAndConvertOnBreak(1, toolUnb.getBrokenItem(), player, LivingEntity.getSlotForHand(context.getHand()));
                    if (stack2 != tool) player.setItemInHand(context.getHand(), stack2);
                }
                else
                {
                    tool.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        } else {
            return InteractionResult.PASS;
        }
    }
}