package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.potion.ModPotion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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

        // ADVANCED POTION MAKING - FAILS
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.SPIDER_EYE, Potions.THICK);                                          // Failure - Thick
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.BEETROOT, Potions.THICK);                                            // Failure - Thick
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.INK_SAC, Potions.THICK);                                             // Failure - Thick
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.GLOW_INK_SAC, Potions.THICK);                                        // Failure - Thick

        // ADVANCED POTION MAKING - HEALTH
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.GLISTERING_MELON_SLICE, ModPotion.INTERESTING_HEALTH);               // Base
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GLISTERING_MELON_SLICE, Potions.THICK);                    // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GOLDEN_CARROT, Potions.THICK);                             // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.SPIDER_EYE, Potions.THICK);                                // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.FERMENTED_SPIDER_EYE, Potions.THICK);                      // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GLOW_BERRIES, Potions.THICK);                              // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.SWEET_BERRIES, Potions.THICK);                             // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.BEETROOT, Potions.THICK);                                  // Failure - Thick
        builder.registerPotionRecipe(ModPotion.INTERESTING_HEALTH, Items.GHAST_TEAR, Potions.THICK);                                // Failure - Thick
        makeAdvancedRecipe(builder, ModPotion.INTERESTING_HEALTH, ModItem.ECTOPLASM, ModPotion.TURBO_REGENERATION);                 // Pass - Regeneration
        makeAdvancedRecipe(builder, ModPotion.INTERESTING_HEALTH, ModItem.APPLE_OF_ENLIGHTENMENT, ModPotion.LIFE_BOOST);            // Pass - Life Boost

        // ADVANCED POTION MAKING - BAD
        builder.registerPotionRecipe(ModPotion.DEBONAIR, Items.GLISTERING_MELON_SLICE, ModPotion.INTERESTING_HEALTH);               // Base

        // TODO: add more adv pots
    }

    @Unique
    private static void makeAdvancedRecipe(BrewingRecipeRegistry.Builder builder, RegistryEntry<Potion> input, Item ingredient, RegistryEntry<Potion> output)
    {
        // Base
        builder.registerPotionRecipe(input, ingredient, output);
        // Debonair -> Thick
        // Gluttonous forms
        builder.registerPotionRecipe(output, Items.REDSTONE, ModPotion.GLUTTONY);
        builder.registerPotionRecipe(output, Items.GLOWSTONE_DUST, ModPotion.GLUTTONY);
    }
}
