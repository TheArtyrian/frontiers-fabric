package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.PersonalChestBlock;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(BuiltinModelItemRenderer.class)
public abstract class BuiltinModelItemRendererMixin
{
    @Shadow @Final private BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    @Unique
    private final PersonalChestBlockEntity renderChestPersonal =
            new PersonalChestBlockEntity(BlockPos.ORIGIN, ModBlocks.PERSONAL_CHEST.getDefaultState());

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            cancellable = true)
    private void something(
            ItemStack stack,
            ModelTransformationMode mode,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            CallbackInfo ci)
    {
        Item item = stack.getItem();
        if (item instanceof BlockItem block && block.getBlock() == ModBlocks.PERSONAL_CHEST)
        {
            this.blockEntityRenderDispatcher.renderEntity(renderChestPersonal, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
    }
}
