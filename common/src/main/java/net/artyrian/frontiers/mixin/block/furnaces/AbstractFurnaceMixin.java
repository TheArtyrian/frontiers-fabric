package net.artyrian.frontiers.mixin.block.furnaces;

import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceMixin extends BlockEntityMixin
{
    @Inject(method = "serverTick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;",
            ordinal = 0,
            shift = At.Shift.AFTER)
    )
    private static void frnt$doVectorEvent(Level level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci)
    {
        boolean lit = state.getValue(AbstractFurnaceBlock.LIT);
        VectorEventSync.Local.fireEvent(level, pos, FRLevelEvents.Local.FURNACES_LIGHT, (lit) ? 1 : 0);
    }
}
