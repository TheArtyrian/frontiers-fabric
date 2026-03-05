package net.vertisoft.vectorlib.mixin.impl.chunksync;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import net.minecraft.world.level.chunk.storage.RegionStorageInfo;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSerializer.class)
public class ChunkSerialMixin
{
    @Inject(method = "read", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/chunk/storage/ChunkSerializer;makeBiomeCodec(Lnet/minecraft/core/Registry;)Lcom/mojang/serialization/Codec;",
            shift = At.Shift.AFTER)
    )
    private static void vectorLib$chunkSyncRead(ServerLevel level, PoiManager poiManager, RegionStorageInfo regionStorageInfo, ChunkPos pos, CompoundTag tag, CallbackInfoReturnable<ProtoChunk> cir)
    {
        VectorLib.LOGGER.info("VECTORLIB CHUNKSYNC READ");
    }

    @WrapOperation(method = "write", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/nbt/NbtUtils;addCurrentDataVersion(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;")
    )
    private static CompoundTag vectorLib$chunkSyncWrite(CompoundTag tag, Operation<CompoundTag> original)
    {
        VectorLib.LOGGER.info("VECTORLIB CHUNKSYNC WRITE");
        return tag;
    }
}
