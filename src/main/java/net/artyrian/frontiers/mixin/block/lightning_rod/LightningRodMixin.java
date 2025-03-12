package net.artyrian.frontiers.mixin.block.lightning_rod;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
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
        return state.isOf(Blocks.LIGHTNING_ROD) && (state.get(Properties.FACING) == matching_dir);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(CONNECTED, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendStuffs(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(CONNECTED);
    }

    @ModifyReturnValue(method = "getPlacementState", at = @At("RETURN"))
    public BlockState getRodAttachmentState(BlockState original, @Local(argsOnly = true) ItemPlacementContext ctx)
    {
        Direction dir = ctx.getSide();
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(dir));

        return original.with(CONNECTED, isConnectedRod(blockState, dir));
    }

    @ModifyReturnValue(method = "getStateForNeighborUpdate", at = @At("RETURN"))
    public BlockState checkRodNearby(BlockState original,
                                     @Local(ordinal = 0, argsOnly = true) BlockState state,
                                     @Local(ordinal = 1, argsOnly = true) BlockState neighborState,
                                     @Local(argsOnly = true) Direction direction)
    {
        boolean isRightDir = (direction == state.get(Properties.FACING));
        if (isRightDir) return original.with(CONNECTED, isConnectedRod(neighborState, state.get(Properties.FACING)));
        return original;
    }
}
