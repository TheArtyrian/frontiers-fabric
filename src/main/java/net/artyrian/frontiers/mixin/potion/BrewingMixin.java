package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.potion.ModPotion;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin
{
    @Inject(method = "registerDefaults", at = @At("TAIL"))
    private static void registerDefaults(BrewingRecipeRegistry.Builder builder, CallbackInfo ci)
    {
        builder.registerPotionRecipe(Potions.AWKWARD, ModItem.WARPED_WART, ModPotion.DEBONAIR);

        builder.registerPotionRecipe(Potions.AWKWARD, ModItem.SHULKER_RESIDUE, ModPotion.LEVITATION);
        builder.registerPotionRecipe(ModPotion.LEVITATION, Items.REDSTONE, ModPotion.LONG_LEVITATION);
        builder.registerPotionRecipe(ModPotion.LEVITATION, Items.GLOWSTONE_DUST, ModPotion.STRONG_LEVITATION);
    }
}
