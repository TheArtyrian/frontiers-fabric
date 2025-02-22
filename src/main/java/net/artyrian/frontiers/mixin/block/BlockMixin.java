package net.artyrian.frontiers.mixin.block;

import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlockMixin
{
    @Shadow public abstract BlockState getStateWithProperties(BlockState state);

    @Shadow public abstract StateManager<Block, BlockState> getStateManager();

    @Shadow public abstract BlockState getDefaultState();

    @Shadow @Final protected StateManager<Block, BlockState> stateManager;

    @Shadow protected final void setDefaultState(BlockState state) {};

    @Shadow public BlockState getPlacementState(ItemPlacementContext ctx) {return null;}

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendMix(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {

    }
}
