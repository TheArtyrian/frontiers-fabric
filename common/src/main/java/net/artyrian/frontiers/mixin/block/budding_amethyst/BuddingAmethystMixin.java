package net.artyrian.frontiers.mixin.block.budding_amethyst;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.misc.ModPredicate;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Debug(export = true)
@Mixin(BuddingAmethystBlock.class)
public abstract class BuddingAmethystMixin extends BlockMixin
{
    @Shadow
    public static boolean canGrowIn(BlockState state)
    {
        return false;
    }

    @Unique
    private static final BooleanProperty CORRUPTED = ModBlockProperties.IS_CORRUPTED;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(BlockBehaviour.Properties settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().setValue(CORRUPTED, false));
    }

    @Override
    public void appendMix(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(CORRUPTED);
        super.appendMix(builder, ci);
    }

    /* TODO: BUG: Amethyst buds placed on corrupted budding amethyst can still grow.
        As of writing this it's literally 1 AM so idc, remind me to fix it later */
    @ModifyExpressionValue(method = "randomTick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/block/BuddingAmethystBlock;canGrowIn(Lnet/minecraft/block/BlockState;)Z"))
    public boolean xd(
            boolean original,
            @Local(argsOnly = true)
            BlockPos pos,
            @Local(argsOnly = true) BlockState state,
            @Local(argsOnly = true) ServerLevel world,
            @Local Direction direction)
    {
        Optional<Boolean> is_corrupted = world.getBlockState(pos).getOptionalValue(CORRUPTED);
        if (is_corrupted.isPresent())
        {
            boolean corrupt = is_corrupted.get();
            if (corrupt)
            {
                BlockPos lazyPosLol = pos.relative(direction);
                BlockState lazyStateLol = world.getBlockState(lazyPosLol);
                Block block = null;
                if (this.canGrowIn(lazyStateLol)) {
                    block = ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.is(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD) && lazyStateLol.getValue(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.is(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD) && lazyStateLol.getValue(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.is(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD) && lazyStateLol.getValue(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.CORRUPTED_AMETHYST_CLUSTER;
                }

                if (block != null) {
                    BlockState blockState2 = block.defaultBlockState()
                            .setValue(AmethystClusterBlock.FACING, direction)
                            .setValue(AmethystClusterBlock.WATERLOGGED, lazyStateLol.getFluidState().getType() == Fluids.WATER);
                    world.setBlockAndUpdate(lazyPosLol, blockState2);
                }
                return false;
            }
        }
        return original;
    }
}
