package net.artyrian.frontiers.mixin.block.sculk_catalyst;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
    public void init_inject(BlockBehaviour.Properties settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().setValue(WARDENIZED, false));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendWardenStuffs(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(WARDENIZED);
    }
}
