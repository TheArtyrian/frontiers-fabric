package net.artyrian.frontiers.mixin.level;

import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin
{
    @Shadow @Final private Minecraft minecraft;

    @Shadow public abstract void addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed);

    @Shadow public abstract ChunkSource getChunkSource();

    @Shadow @Final private ClientChunkCache chunkSource;

    @Inject(method = "doAnimateTick", at = @At("TAIL"))
    private void frontiers$doAnimateTick(int posX, int posY, int posZ, int range, RandomSource random, Block block, BlockPos.MutableBlockPos blockPos, CallbackInfo ci)
    {
        if (this.minecraft.player != null && this.minecraft.player.getMainHandItem().is(ModItem.SNOW_MELT.get()))
        {
            ChunkAccess chunkAt = ((ClientLevel)(Object)this).getChunk(blockPos);
            //addParticle(ModParticle.SNOW_GLINT.get(), above.getX() + 0.5, above.getY() + 0.1, above.getZ() + 0.5, 0.0, 0.0, 0.0);
        }
    }
}
