package net.artyrian.frontiers.mixin.entity.enderman;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public class EndermanMixinFabric
{
    @ModifyExpressionValue(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean playerGazeProtectionREMOVE_IN_1_21_2(boolean original, @Local ItemStack stack)
    {
        return original ||
                stack.is(ModBlocks.CARVED_GLISTERING_MELON.get().asItem()) ||
                stack.is(ModBlocks.CARVED_MELON.get().asItem()) ||
                stack.is(ModBlocks.WHITE_PUMPKIN.get().asItem());
    }
}
