package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.EnchantingMagnetBlockEntity;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class EnchantingMagnetBlock extends BlockWithEntity
{
    public static final MapCodec<EnchantingMagnetBlock> CODEC = EnchantingMagnetBlock.createCodec(EnchantingMagnetBlock::new);

    public EnchantingMagnetBlock(Settings settings)  { super(settings); }

    @Nullable @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new EnchantingMagnetBlockEntity(pos, state); }
    @Override
    protected BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() { return CODEC; }
    
    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return validateTicker(type, ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY,
                world.isClient ? EnchantingMagnetBlockEntity::clientTick : EnchantingMagnetBlockEntity::serverTick);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (!world.isClient && blockEntity instanceof EnchantingMagnetBlockEntity magnet)
        {
            if (!EnchantmentHelper.hasAnyEnchantmentsIn(tool, ModTags.Enchants.PREVENTS_MAGNET_EXP_DROP))
            {
                int dropper = Math.round(((float) magnet.getExp() / 2));
                this.dropExperience((ServerWorld) world, pos, dropper);
                world.updateComparators(pos, this);
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        BlockEntity ent = world.getBlockEntity(pos);
        if (ent instanceof EnchantingMagnetBlockEntity magnet)
        {
            int expAmnt = magnet.getExp();
            if (expAmnt >= 12)
            {
                if (stack.isOf(Items.GLASS_BOTTLE))
                {
                    stack.decrementUnlessCreative(1, player);

                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.2F);
                    if (stack.isEmpty())
                    {
                        player.setStackInHand(hand, new ItemStack(Items.EXPERIENCE_BOTTLE));
                    }
                    else if (!player.getInventory().insertStack(new ItemStack(Items.EXPERIENCE_BOTTLE)))
                    {
                        player.dropItem(new ItemStack(Items.EXPERIENCE_BOTTLE), false);
                    }

                    magnet.subtractExp(12);
                    world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 2);

                    world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

                    return ItemActionResult.success(world.isClient);
                }
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
