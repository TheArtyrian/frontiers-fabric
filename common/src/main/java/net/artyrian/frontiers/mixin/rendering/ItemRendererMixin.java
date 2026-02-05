package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
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
    @Shadow @Final private ItemModelShaper itemModelShaper;
    @Unique private static final ModelResourceLocation FRNT$PALE_TRIDENT = ModelResourceLocation.inventory(Frontiers.id("pale_trident"));
    @Unique private static final ModelResourceLocation FRNT$PALE_TRIDENT_IN_HAND = ModelResourceLocation.inventory(Frontiers.id("pale_trident_in_hand"));

    @ModifyVariable(method = "renderModelLists", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyLight(int original, @Local(argsOnly = true) ItemStack stack)
    {
        if (!stack.isEmpty() && stack.is(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }

    @ModifyVariable(
            method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"),
            argsOnly = true
    )
    private BakedModel modelOverriderForFrontiers(BakedModel value, @Local(argsOnly = true) ItemDisplayContext renderMode, @Local(argsOnly = true) ItemStack stack)
    {
        boolean renderX = renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.FIXED;
        if (renderX)
        {
            if (stack.is(ModItem.PALE_TRIDENT.get()))
            {
                return this.itemModelShaper.getModelManager().getModel(FRNT$PALE_TRIDENT);
            }
        }
        return value;
    }

    @ModifyVariable(method = "getModel", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/resources/model/BakedModel;getOverrides()Lnet/minecraft/client/renderer/block/model/ItemOverrides;",
            shift = At.Shift.BEFORE /*I love brittle*/)
    )
    private BakedModel modelQuadFrontiers(BakedModel og, @Local(argsOnly = true) ItemStack stack)
    {
        if (stack.is(ModItem.PALE_TRIDENT.get()))
        {
            return this.itemModelShaper.getModelManager().getModel(FRNT$PALE_TRIDENT_IN_HAND);
        }
        return og;
    }
}
