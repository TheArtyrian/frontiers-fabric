package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

// (Why do I decide to port this instead of doing some modCompile thing instead)

// A quasi-reimplementation of FarmersDelight's knife item - used to avoid dependencies. The original code can be found here:
// https://github.com/MehVahdJukaar/FarmersDelightRefabricated/blob/fabric/1.21/src/main/java/vectorwing/farmersdelight/common/item/KnifeItem.java
public class KnifeItem extends MiningToolItem
{
    // Unrelated but like every time I see super I can only think of Super Idol :'}
    public KnifeItem(ToolMaterial material, Settings settings)
    {
        super(material, FDTag.Blocks.MINEABLE_KNIFE, settings);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        ItemStack tool = context.getStack();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Direction facing = context.getSide();

        if (state.getBlock() == Blocks.PUMPKIN && tool.isIn(FDTag.Items.KNIVES)) {
            PlayerEntity player = context.getPlayer();
            if (player != null && !world.isClient()) {
                Direction direction = facing.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : facing;
                world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.f, 1.f);
                world.setBlockState(pos, Blocks.CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, direction), 11);
                ItemEntity itemEntity = new ItemEntity(world,
                        pos.getX() + .5d + direction.getOffsetX() * .65d,
                        pos.getY() + .1d, pos.getZ() + .5d + direction.getOffsetZ() * .65d,
                        new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itemEntity.setVelocity(
                        .05d * direction.getOffsetX() + world.getRandom().nextDouble() * .02d,
                        .05d,
                        .05d * direction.getOffsetZ() + world.getRandom().nextDouble() * 0.02D);
                world.spawnEntity(itemEntity);
                tool.damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            return ActionResult.success(world.isClient());
        } else {
            return ActionResult.PASS;
        }
    }
}