package net.artyrian.frontiers.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export=true)
@Mixin(Blocks.class)
public class BlockRegistryMixin
{
    //@ModifyReturnValue(
        //        method = "<clinit>",
        //        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FletchingTableBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V")
        //)
    //private static Block newFletchingTable(Block original)
    //{
    //    return original;
    //}
}
