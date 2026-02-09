package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LevelRenderer.class)
@Debug(export = true)
public class LevelRendererMixin
{
    @ModifyArg(
            method = "levelEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/particles/ItemParticleOption;<init>(Lnet/minecraft/core/particles/ParticleType;Lnet/minecraft/world/item/ItemStack;)V"
            )
    )
    private ItemStack frontiers$swapEnderEyeWithVoidEye(ItemStack value)
    {
        if (value.is(Items.ENDER_EYE)) return new ItemStack(ModItem.VOID_PEARL.get());
        return value;
    }
}
