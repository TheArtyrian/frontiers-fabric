package net.artyrian.frontiers.mixin.block.lightning_rod;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningRodBlock.class)
public abstract class LightningRodMixin extends BlockMixin
{
    @Unique
    private static final BooleanProperty CONNECTED = ModBlockProperties.ROD_CONNECTED;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(CONNECTED, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendWardenStuffs(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(CONNECTED);
    }
}
