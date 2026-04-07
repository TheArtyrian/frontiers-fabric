package net.artyrian.frontiers.mixin.block.beacon;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconRenderer.class)
public class BeaconBlockEntityRendererMixin
{
    @Inject(
            method = "render(Lnet/minecraft/world/level/block/entity/BeaconBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/BeaconBlockEntity;getBeamSections()Ljava/util/List;",
                    shift = At.Shift.AFTER)
    )
    private void frontiers$enlargeBeam(
            BeaconBlockEntity beaconBlockEntity,
            float f,
            PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider,
            int i,
            int j,
            CallbackInfo ci)
    {
        if (
                beaconBlockEntity.levels >= 4 &&
                beaconBlockEntity.getLevel() != null &&
                frontiers$hasBrimtanBlocks(beaconBlockEntity.getLevel(), beaconBlockEntity.getBlockPos().below()))
        {
            matrixStack.scale(1.5F, 1.0F, 1.5F);
            matrixStack.translate(-0.17F, 0.0F, -0.17F);
        }
    }

    @Unique
    private static boolean frontiers$hasBrimtanBlocks(Level world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).is(FRBlocks.BRIMTAN_BLOCK.get()) &&

                world.getBlockState(pos.offset(1, 0, 0)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, 0)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(0, 0, 1)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(0, 0, -1)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&

                world.getBlockState(pos.offset(1, 0, 1)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, 1)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(1, 0, -1)).is(FRBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, -1)).is(FRBlocks.BRIMTAN_BLOCK.get())
        );
    }
}
