package net.artyrian.frontiers.mixin.block.conduit;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConduitBlockEntity.class)
public abstract class ConduitBlockEntityMixin
{
    @Mutable
    @Shadow @Final private static Block[] ACTIVATING_BLOCKS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void newActivators(CallbackInfo ci)
    {
        ACTIVATING_BLOCKS =
                new Block[]
                {
                        Blocks.PRISMARINE,
                        Blocks.PRISMARINE_BRICKS,
                        Blocks.SEA_LANTERN,
                        Blocks.DARK_PRISMARINE,
                        ModBlocks.PALE_PRISMARINE,
                        ModBlocks.PALE_PRISMARINE_BRICKS,
                        ModBlocks.DEEP_PALE_PRISMARINE,
                };
    }
}
