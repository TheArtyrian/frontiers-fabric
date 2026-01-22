package net.artyrian.frontiers.mixin.block;

import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;

@Debug(export=true)
@Mixin(Blocks.class)
public class BlockRegistryMixin
{
    //// Fine, must I really do it this way lmao
    //// Call me the NY Jets the way I fail my intercepts (i know nothing about football)
    //@ModifyReturnValue(method = "register(Lnet/minecraft/registry/RegistryKey;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", at = @At("RETURN"))
    //private static Block melonBlockRegistryIntercept(Block original, @Local(argsOnly = true) RegistryKey<Block> key, @Local(argsOnly = true) Block block)
    //{
    //    // Will only intercept the Melon register.
    //    if (key.equals(BlockKeys.MELON))
    //    {
    //        return Registry.register(
    //                Registries.BLOCK,
    //                key,
    //                new MelonBlock(
    //                        AbstractBlock.Settings.create()
    //                                .mapColor(MapColor.LIME)
    //                                .strength(1.0F)
    //                                .sounds(BlockSoundGroup.WOOD)
    //                                .pistonBehavior(PistonBehavior.DESTROY)
    //                )
    //        );
    //    }
    //    return original;
    //}
}
