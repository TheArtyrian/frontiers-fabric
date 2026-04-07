package net.artyrian.frontiers.mixin.block.conduit;

import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
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
    @Shadow @Final private static Block[] VALID_BLOCKS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void newActivators(CallbackInfo ci)
    {
        VALID_BLOCKS =
                new Block[]
                {
                        Blocks.PRISMARINE,
                        Blocks.PRISMARINE_BRICKS,
                        Blocks.SEA_LANTERN,
                        Blocks.DARK_PRISMARINE,
                        FRBlocks.PALE_PRISMARINE.get(),
                        FRBlocks.PALE_PRISMARINE_BRICKS.get(),
                        FRBlocks.DEEP_PALE_PRISMARINE.get(),
                };
    }
}
