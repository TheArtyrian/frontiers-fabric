package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.StrayModelBlockEntity;
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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class StrayModelBlock extends EntityModelBlock
{
    public static final BooleanProperty MODEL_SHEARED = ModBlockProperties.MODEL_SHEARED;

    public StrayModelBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(MODEL_SHEARED, false));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.STRAY_MODEL_BLOCKENTITY.get(), StrayModelBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new StrayModelBlockEntity(pos, state); }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(MODEL_SHEARED);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(Items.SHEARS) && !state.getValue(StrayModelBlock.MODEL_SHEARED))
        {
            if (!level.isClientSide)
            {
                level.setBlock(pos, state.setValue(StrayModelBlock.MODEL_SHEARED, true), UPDATE_ALL_IMMEDIATE);
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
