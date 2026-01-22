package net.artyrian.frontiers.mixin.block.lightning_rod;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Duration;

@Debug(export = true)
@Mixin(LightningRodBlock.class)
public abstract class LightningRodMixin extends BlockMixin
{
    @Unique
    private static final BooleanProperty CONNECTED = ModBlockProperties.ROD_CONNECTED;

    @Unique
    private static boolean isConnectedRod(BlockState state, Direction matching_dir)
    {
        return state.is(Blocks.LIGHTNING_ROD) && (state.getValue(BlockStateProperties.FACING) == matching_dir);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(BlockBehaviour.Properties settings, CallbackInfo ci)
    {
        this.registerDefaultState(this.getStateDefinition().any().setValue(CONNECTED, false));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    public void appendStuffs(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(CONNECTED);
    }

    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    public BlockState getRodAttachmentState(BlockState original, @Local(argsOnly = true) BlockPlaceContext ctx)
    {
        Direction dir = ctx.getClickedFace();
        BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos().relative(dir));

        return original.setValue(CONNECTED, isConnectedRod(blockState, dir));
    }

    @ModifyReturnValue(method = "updateShape", at = @At("RETURN"))
    public BlockState checkRodNearby(BlockState original,
                                     @Local(ordinal = 0, argsOnly = true) BlockState state,
                                     @Local(ordinal = 1, argsOnly = true) BlockState neighborState,
                                     @Local(argsOnly = true) Direction direction)
    {
        boolean isRightDir = (direction == state.getValue(BlockStateProperties.FACING));
        if (isRightDir) return original.setValue(CONNECTED, isConnectedRod(neighborState, state.getValue(BlockStateProperties.FACING)));
        return original;
    }
}
