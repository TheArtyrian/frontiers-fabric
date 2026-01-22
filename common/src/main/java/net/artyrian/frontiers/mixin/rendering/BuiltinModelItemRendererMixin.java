package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.block.entity.PhantomBedBlockEntity;
import net.artyrian.frontiers.entity.renderer.projectile.PaleTridentEntityRenderer;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.CustomShieldItem;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Objects;

@Debug(export = true)
@Mixin(BlockEntityWithoutLevelRenderer.class)
public abstract class BuiltinModelItemRendererMixin
{
    @Shadow @Final private BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    @Shadow private ShieldModel modelShield;
    @Shadow private TridentModel modelTrident;

    // Marking these as unique borks them, so to hell with that :shrug:
    private final PhantomBedBlockEntity frontiers$renderPhantomBed = new PhantomBedBlockEntity(BlockPos.ZERO, ModBlocks.PHANTOM_STITCH_BED.defaultBlockState());
    private final PersonalChestBlockEntity frontiers$renderChestPersonal = new PersonalChestBlockEntity(BlockPos.ZERO, ModBlocks.PERSONAL_CHEST.defaultBlockState());

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            cancellable = true)
    private void attemptRenderingMixinICanDoItMyself(
            ItemStack stack,
            ItemDisplayContext mode,
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
            int light,
            int overlay,
            CallbackInfo ci)
    {
        // Lazy coding that'll likely get me fired in a job i'll never get because i'm awful at programming
        Item item = stack.getItem();
        if (item instanceof BlockItem blockItem && blockItem.getBlock() == ModBlocks.PERSONAL_CHEST)
        {
            this.blockEntityRenderDispatcher.renderItem(frontiers$renderChestPersonal, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
        else if (item instanceof BlockItem blockItem && blockItem.getBlock() == ModBlocks.PHANTOM_STITCH_BED)
        {
            this.blockEntityRenderDispatcher.renderItem(frontiers$renderPhantomBed, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
        else if (stack.getItem() instanceof CustomShieldItem shield)
        {
            BannerPatternLayers bannerPatternsComponent = stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
            DyeColor dyeColor2 = stack.get(DataComponents.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.pushPose();
            matrices.scale(1.0F, -1.0F, -1.0F);
            Material spriteIdentifier = new Material(
                    Sheets.SHIELD_SHEET, shield.getTexID(bl)
            );

            VertexConsumer vertexConsumer = spriteIdentifier.sprite()
                    .wrap(
                            ItemRenderer.getFoilBufferDirect(vertexConsumers, this.modelShield.renderType(spriteIdentifier.atlasLocation()), true, stack.hasFoil())
                    );
            this.modelShield.handle().render(matrices, vertexConsumer, light, overlay);
            if (bl)
            {
                BannerRenderer.renderPatterns(
                        matrices,
                        vertexConsumers,
                        light,
                        overlay,
                        this.modelShield.plate(),
                        spriteIdentifier,
                        false,
                        Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE),
                        bannerPatternsComponent,
                        stack.hasFoil()
                );
            }
            else
            {
                this.modelShield.plate().render(matrices, vertexConsumer, light, overlay);
            }

            matrices.popPose();
            ci.cancel();
        }
        else
        {
            if (stack.is(ModItem.PALE_TRIDENT))
            {
                matrices.pushPose();
                matrices.scale(1.0F, -1.0F, -1.0F);
                VertexConsumer vertexConsumer2 = ItemRenderer.getFoilBufferDirect(
                        vertexConsumers, this.modelTrident.renderType(PaleTridentEntityRenderer.TEXTURE), false, stack.hasFoil()
                );
                this.modelTrident.renderToBuffer(matrices, vertexConsumer2, light, overlay);
                matrices.popPose();
                ci.cancel();
            }
        }
    }
}
