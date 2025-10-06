package net.artyrian.frontiers.mixin.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModBlockset;
import net.minecraft.block.BlockSetType;
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
    @Shadow @Final private static Map<String, BlockSetType> VALUES;

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void frontiersHashSet(CallbackInfo ci)
    {
        VALUES.putAll(ModBlockset.BlockSet.VALUES);
    }
}
