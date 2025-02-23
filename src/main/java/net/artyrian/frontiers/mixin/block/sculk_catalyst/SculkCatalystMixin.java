package net.artyrian.frontiers.mixin.block.sculk_catalyst;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkCatalystBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SculkCatalystBlock.class)
public abstract class SculkCatalystMixin extends BlockMixin
{
    @Unique
    private static final BooleanProperty WARDENIZED = ModBlockProperties.CATALYST_WARDENIZED;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(WARDENIZED, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendWardenStuffs(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(WARDENIZED);
    }
}
