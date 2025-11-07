package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin
{
    @Shadow @Final private ItemModels models;
    @Unique private static final ModelIdentifier FRNT$PALE_TRIDENT = ModelIdentifier.ofInventoryVariant(Identifier.of(Frontiers.MOD_ID, "pale_trident"));
    @Unique private static final ModelIdentifier FRNT$PALE_TRIDENT_IN_HAND = ModelIdentifier.ofInventoryVariant(Identifier.of(Frontiers.MOD_ID, "pale_trident_in_hand"));

    @ModifyVariable(method = "renderBakedItemModel", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyLight(int original, @Local(argsOnly = true) ItemStack stack)
    {
        if (!stack.isEmpty() && stack.isIn(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
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
    private BakedModel modelOverriderForFrontiers(BakedModel value, @Local(argsOnly = true) ModelTransformationMode renderMode, @Local(argsOnly = true) ItemStack stack)
    {
        boolean renderX = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
        if (renderX)
        {
            if (stack.isOf(ModItem.PALE_TRIDENT))
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
        if (stack.isOf(ModItem.PALE_TRIDENT))
        {
            return this.models.getModelManager().getModel(FRNT$PALE_TRIDENT_IN_HAND);
        }
        return og;
    }
}
