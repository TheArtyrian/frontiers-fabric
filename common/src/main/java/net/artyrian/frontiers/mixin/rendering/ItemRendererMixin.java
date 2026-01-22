package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin
{
    @Shadow @Final private ItemModelShaper models;
    @Unique private static final ModelResourceLocation FRNT$PALE_TRIDENT = ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident"));
    @Unique private static final ModelResourceLocation FRNT$PALE_TRIDENT_IN_HAND = ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident_in_hand"));

    @ModifyVariable(method = "renderBakedItemModel", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyLight(int original, @Local(argsOnly = true) ItemStack stack)
    {
        if (!stack.isEmpty() && stack.is(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V"),
            argsOnly = true
    )
    private BakedModel modelOverriderForFrontiers(BakedModel value, @Local(argsOnly = true) ItemDisplayContext renderMode, @Local(argsOnly = true) ItemStack stack)
    {
        boolean renderX = renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.FIXED;
        if (renderX)
        {
            if (stack.is(ModItem.PALE_TRIDENT))
            {
                return this.models.getModelManager().getModel(FRNT$PALE_TRIDENT);
            }
        }
        return value;
    }

    @ModifyVariable(method = "getModel", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/model/BakedModel;getOverrides()Lnet/minecraft/client/render/model/json/ModelOverrideList;",
            shift = At.Shift.BEFORE /*I love brittle*/)
    )
    private BakedModel modelQuadFrontiers(BakedModel og, @Local(argsOnly = true) ItemStack stack)
    {
        if (stack.is(ModItem.PALE_TRIDENT))
        {
            return this.models.getModelManager().getModel(FRNT$PALE_TRIDENT_IN_HAND);
        }
        return og;
    }
}
