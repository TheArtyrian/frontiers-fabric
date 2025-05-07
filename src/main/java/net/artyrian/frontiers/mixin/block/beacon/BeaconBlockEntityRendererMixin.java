package net.artyrian.frontiers.mixin.block.beacon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntityRenderer.class)
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
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            int j,
            CallbackInfo ci)
    {
        if (
                beaconBlockEntity.level >= 4 &&
                beaconBlockEntity.getWorld() != null &&
                hasBrimtanBlocks(beaconBlockEntity.getWorld(), beaconBlockEntity.getPos().down()))
        {
            matrixStack.scale(1.5F, 1.0F, 1.5F);
            matrixStack.translate(-0.17F, 0.0F, -0.17F);
        }
    }

    @Unique
    private static boolean hasBrimtanBlocks(World world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).isOf(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.add(1, 0, 0)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, 0)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(0, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(0, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.add(1, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(1, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK)
        );
    }
}
