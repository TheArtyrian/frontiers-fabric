package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.BoggedModelBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BoggedModelBlock extends EntityModelBlock
{
    public static final IntegerProperty MODEL_SHEAR_COUNT = ModBlockProperties.MODEL_SHEAR_COUNT;

    public BoggedModelBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(MODEL_SHEAR_COUNT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(MODEL_SHEAR_COUNT);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.BOGGED_MODEL_BLOCKENTITY.get(), BoggedModelBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new BoggedModelBlockEntity(pos, state); }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(Items.SHEARS) && (state.getValue(BoggedModelBlock.MODEL_SHEAR_COUNT) < 2))
        {
            if (!level.isClientSide)
            {
                level.setBlock(pos, state.cycle(BoggedModelBlock.MODEL_SHEAR_COUNT), UPDATE_ALL_IMMEDIATE);
                level.playSound(null, pos, SoundEvents.BOGGED_SHEAR, SoundSource.BLOCKS, 1.0F, 1.0F);
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
