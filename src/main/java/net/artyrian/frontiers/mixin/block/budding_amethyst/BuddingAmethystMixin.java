package net.artyrian.frontiers.mixin.block.budding_amethyst;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.misc.ModPredicate;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
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
    public void init_inject(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(CORRUPTED, false));
    }

    @Override
    public void appendMix(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
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
            @Local(argsOnly = true) ServerWorld world,
            @Local Direction direction)
    {
        Optional<Boolean> is_corrupted = world.getBlockState(pos).getOrEmpty(CORRUPTED);
        if (is_corrupted.isPresent())
        {
            boolean corrupt = is_corrupted.get();
            if (corrupt)
            {
                BlockPos lazyPosLol = pos.offset(direction);
                BlockState lazyStateLol = world.getBlockState(lazyPosLol);
                Block block = null;
                if (this.canGrowIn(lazyStateLol)) {
                    block = ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.isOf(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD) && lazyStateLol.get(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.isOf(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD) && lazyStateLol.get(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD;
                } else if (lazyStateLol.isOf(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD) && lazyStateLol.get(AmethystClusterBlock.FACING) == direction) {
                    block = ModBlocks.CORRUPTED_AMETHYST_CLUSTER;
                }

                if (block != null) {
                    BlockState blockState2 = block.getDefaultState()
                            .with(AmethystClusterBlock.FACING, direction)
                            .with(AmethystClusterBlock.WATERLOGGED, lazyStateLol.getFluidState().getFluid() == Fluids.WATER);
                    world.setBlockState(lazyPosLol, blockState2);
                }
                return false;
            }
        }
        return original;
    }
}
