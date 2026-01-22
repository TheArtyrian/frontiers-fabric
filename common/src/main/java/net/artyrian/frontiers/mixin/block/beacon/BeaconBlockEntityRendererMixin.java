package net.artyrian.frontiers.mixin.block.beacon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
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
            method = "render(Lnet/minecraft/block/entity/BeaconBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/BeaconBlockEntity;getBeamSegments()Ljava/util/List;",
                    shift = At.Shift.AFTER)
    )
    private void enlargeBeam(
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
                hasBrimtanBlocks(beaconBlockEntity.getLevel(), beaconBlockEntity.getBlockPos().below()))
        {
            matrixStack.scale(1.5F, 1.0F, 1.5F);
            matrixStack.translate(-0.17F, 0.0F, -0.17F);
        }
    }

    @Unique
    private static boolean hasBrimtanBlocks(Level world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).is(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.offset(1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(0, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(0, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.offset(1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK)
        );
    }
}
