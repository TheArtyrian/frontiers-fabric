package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheets.class)
public abstract class TexturedRenderLayersMixin
{
    @Shadow @Final public static ResourceLocation CHEST_SHEET;
    @Unique
    private static final Material FRONTIERS_PERSONAL =
            new Material(CHEST_SHEET, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "entity/chest/personal"));
    @Unique
    private static final Material FRONTIERS_PERSONAL_DISABLED =
            new Material(CHEST_SHEET, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "entity/chest/personal_disabled"));

    @Inject(
            method = "chooseMaterial(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/level/block/state/properties/ChestType;Z)Lnet/minecraft/client/resources/model/Material;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getFrontiersChestData(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<Material> cir)
    {
        if (blockEntity instanceof PersonalChestBlockEntity chest)
        {
            int time = chest.getCooldown();
            cir.setReturnValue((time > 0) ? FRONTIERS_PERSONAL_DISABLED : FRONTIERS_PERSONAL);
        }
    }
}
