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
        builder.registerPotionRecipe(Potions.WATER, ModItem.WARPED_WART, Potions.THICK);

        builder.registerPotionRecipe(Potions.AWKWARD, ModItem.SHULKER_RESIDUE, ModPotion.LEVITATION);
        builder.registerPotionRecipe(ModPotion.LEVITATION, Items.REDSTONE, ModPotion.LONG_LEVITATION);
        builder.registerPotionRecipe(ModPotion.LEVITATION, Items.GLOWSTONE_DUST, ModPotion.STRONG_LEVITATION);

        // Reverters
        builder.registerItemRecipe(Items.SPLASH_POTION, ModItem.WARPED_WART, Items.POTION);
        builder.registerItemRecipe(Items.LINGERING_POTION, ModItem.WARPED_WART, Items.SPLASH_POTION);

        // ADVANCED POTION MAKING - HEALTH
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.GLISTERING_MELON_SLICE, ModPotion.INTERESTING_HEALTH);               // Base
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GLISTERING_MELON_SLICE, Potions.THICK);                    // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GOLDEN_CARROT, Potions.THICK);                             // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.SPIDER_EYE, Potions.THICK);                                // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.FERMENTED_SPIDER_EYE, Potions.THICK);                      // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GHAST_TEAR, ModPotion.TURBO_REGENERATION);                 // Pass - Regeneration
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, ModItem.APPLE_OF_ENLIGHTENMENT, ModPotion.LIFE_BOOST);           // Pass - Life Boost
        builder.registerPotionRecipe(ModPotion.TURBO_REGENERATION, Items.REDSTONE, Potions.THICK);                                  // Failure - Back to Thick
        builder.registerPotionRecipe(ModPotion.TURBO_REGENERATION, Items.GLOWSTONE_DUST, Potions.THICK);                            // Failure - Back to Thick

        // TODO: add more adv pots
    }
}
