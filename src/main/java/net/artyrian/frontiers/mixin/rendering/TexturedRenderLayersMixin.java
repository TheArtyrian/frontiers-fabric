package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TexturedRenderLayers.class)
public abstract class TexturedRenderLayersMixin
{
    @Shadow @Final public static Identifier CHEST_ATLAS_TEXTURE;
    @Unique
    private static final SpriteIdentifier PERSONAL =
            new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/chest/personal"));

    @Inject(
            method = "getChestTextureId(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/block/enums/ChestType;Z)Lnet/minecraft/client/util/SpriteIdentifier;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getFrontiersChestData(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<SpriteIdentifier> cir)
    {
        if (blockEntity instanceof PersonalChestBlockEntity)
        {
            cir.setReturnValue(PERSONAL);
        }
    }
}
