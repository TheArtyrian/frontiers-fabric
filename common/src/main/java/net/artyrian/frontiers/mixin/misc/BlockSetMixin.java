package net.artyrian.frontiers.mixin.misc;

import net.artyrian.frontiers.reg.misc.ModBlockset;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(BlockSetType.class)
public class BlockSetMixin
{
    @Shadow @Final private static Map<String, BlockSetType> TYPES;

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void frontiersHashSet(CallbackInfo ci)
    {
        TYPES.putAll(ModBlockset.BlockSet.VALUES);
    }
}
