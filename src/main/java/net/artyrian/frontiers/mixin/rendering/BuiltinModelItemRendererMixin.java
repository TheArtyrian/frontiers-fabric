package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.block.entity.PhantomBedBlockEntity;
import net.artyrian.frontiers.entity.renderer.projectile.PaleTridentEntityRenderer;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.CustomShieldItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Debug(export = true)
@Mixin(BuiltinModelItemRenderer.class)
public abstract class BuiltinModelItemRendererMixin
{
    @Shadow @Final private BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    @Shadow private ShieldEntityModel modelShield;
    @Shadow private TridentEntityModel modelTrident;

    // Marking these as unique borks them, so to hell with that :shrug:
    private final PhantomBedBlockEntity frontiers$renderPhantomBed = new PhantomBedBlockEntity(BlockPos.ORIGIN, ModBlocks.PHANTOM_STITCH_BED.getDefaultState());
    private final PersonalChestBlockEntity frontiers$renderChestPersonal = new PersonalChestBlockEntity(BlockPos.ORIGIN, ModBlocks.PERSONAL_CHEST.getDefaultState());

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            cancellable = true)
    private void attemptRenderingMixinICanDoItMyself(
            ItemStack stack,
            ModelTransformationMode mode,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            CallbackInfo ci)
    {
        // Lazy coding that'll likely get me fired in a job i'll never get because i'm awful at programming
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem && blockItem.getBlock() == ModBlocks.PERSONAL_CHEST)
        {
            this.blockEntityRenderDispatcher.renderEntity(frontiers$renderChestPersonal, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
        else if (item instanceof BlockItem blockItem && blockItem.getBlock() == ModBlocks.PHANTOM_STITCH_BED)
        {
            this.blockEntityRenderDispatcher.renderEntity(frontiers$renderPhantomBed, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
        else if (stack.getItem() instanceof CustomShieldItem shield)
        {
            BannerPatternsComponent bannerPatternsComponent = stack.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
            DyeColor dyeColor2 = stack.get(DataComponentTypes.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.push();
            matrices.scale(1.0F, -1.0F, -1.0F);
            SpriteIdentifier spriteIdentifier = new SpriteIdentifier(
                    TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, shield.getTexID(bl)
            );

            VertexConsumer vertexConsumer = spriteIdentifier.getSprite()
                    .getTextureSpecificVertexConsumer(
                            ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.modelShield.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint())
                    );
            this.modelShield.getHandle().render(matrices, vertexConsumer, light, overlay);
            if (bl)
            {
                BannerBlockEntityRenderer.renderCanvas(
                        matrices,
                        vertexConsumers,
                        light,
                        overlay,
                        this.modelShield.getPlate(),
                        spriteIdentifier,
                        false,
                        Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE),
                        bannerPatternsComponent,
                        stack.hasGlint()
                );
            }
            else
            {
                this.modelShield.getPlate().render(matrices, vertexConsumer, light, overlay);
            }

            matrices.pop();
            ci.cancel();
        }
        else
        {
            if (stack.isOf(ModItem.PALE_TRIDENT))
            {
                matrices.push();
                matrices.scale(1.0F, -1.0F, -1.0F);
                VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(
                        vertexConsumers, this.modelTrident.getLayer(PaleTridentEntityRenderer.TEXTURE), false, stack.hasGlint()
                );
                this.modelTrident.render(matrices, vertexConsumer2, light, overlay);
                matrices.pop();
                ci.cancel();
            }
        }
    }
}
