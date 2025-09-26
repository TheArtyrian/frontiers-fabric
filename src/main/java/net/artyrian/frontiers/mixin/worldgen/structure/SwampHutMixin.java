package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.SwampHutGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwampHutGenerator.class)
public abstract class SwampHutMixin extends StructurePieceMixin
{
    @Inject(method = "generate", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/structure/SwampHutGenerator;spawnCat(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/BlockBox;)V")
    )
    private void injectOhSoSpookyCheck(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci)
    {
        boolean can_replace = (Frontiers.IS_HALLOWEEN) || (random.nextFloat() <= 0.20);
        if (can_replace) this.addBlock(world, ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.getDefaultState(), 1, 3, 5, chunkBox);
    }
}
