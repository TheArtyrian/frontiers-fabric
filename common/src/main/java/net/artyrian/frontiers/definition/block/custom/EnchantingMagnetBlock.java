package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.EnchantingMagnetBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class EnchantingMagnetBlock extends BaseEntityBlock
{
    public static final MapCodec<EnchantingMagnetBlock> CODEC = EnchantingMagnetBlock.simpleCodec(EnchantingMagnetBlock::new);

    public EnchantingMagnetBlock(Properties settings)  { super(settings); }

    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new EnchantingMagnetBlockEntity(pos, state); }
    @Override
    protected RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() { return CODEC; }
    
    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.ENCHANTING_MAGNET_BLOCKENTITY.get(),
                world.isClientSide ? EnchantingMagnetBlockEntity::clientTick : EnchantingMagnetBlockEntity::serverTick);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool)
    {
        super.playerDestroy(world, player, pos, state, blockEntity, tool);
        if (!world.isClientSide && blockEntity instanceof EnchantingMagnetBlockEntity magnet)
        {
            if (!EnchantmentHelper.hasTag(tool, ModTags.Enchants.PREVENTS_MAGNET_EXP_DROP))
            {
                int dropper = Math.round(((float) magnet.getExp() / 2));
                this.popExperience((ServerLevel) world, pos, dropper);
                world.updateNeighbourForOutputSignal(pos, this);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        BlockEntity ent = world.getBlockEntity(pos);
        if (ent instanceof EnchantingMagnetBlockEntity magnet)
        {
            int expAmnt = magnet.getExp();
            if (expAmnt >= 12)
            {
                if (stack.is(Items.GLASS_BOTTLE))
                {
                    stack.consume(1, player);

                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.2F);
                    if (stack.isEmpty())
                    {
                        player.setItemInHand(hand, new ItemStack(Items.EXPERIENCE_BOTTLE));
                    }
                    else if (!player.getInventory().add(new ItemStack(Items.EXPERIENCE_BOTTLE)))
                    {
                        player.drop(new ItemStack(Items.EXPERIENCE_BOTTLE), false);
                    }

                    magnet.subtractExp(12);
                    world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 2);

                    world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

                    return ItemInteractionResult.sidedSuccess(world.isClientSide);
                }
            }
        }

        return super.useItemOn(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos)
    {
        if (world.getBlockEntity(pos) instanceof EnchantingMagnetBlockEntity magent) return magent.get0To15Percent();
        else return 0;
    }
}
