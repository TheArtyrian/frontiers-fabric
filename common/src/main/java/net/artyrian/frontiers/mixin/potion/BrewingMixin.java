package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.ModPotion;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Debug(export = true)
@Mixin(PotionBrewing.class)
public class BrewingMixin
{
    @ModifyArg(
            method = "addVanillaMixes",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "classValue=SLIME_BLOCK")
            ),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionBrewing$Builder;addStartMix(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)V", ordinal = 1)
    )
    private static Item frontiers_oozingRemixerSlimer(Item ingredient)
    {
        return ModItem.HARDENED_SLIME.get();
    }

    @Inject(method = "addVanillaMixes", at = @At("TAIL"))
    private static void registerDefaults(PotionBrewing.Builder builder, CallbackInfo ci)
    {
        // Magma Vision
        builder.addMix(Potions.AWKWARD, ModItem.BRIMTAN_NUGGET.get(), ModPotion.MAGMA_VISION);
        builder.addMix(ModPotion.MAGMA_VISION, Items.REDSTONE, ModPotion.LONG_MAGMA_VISION);

        // Levitation
        builder.addMix(Potions.AWKWARD, ModItem.SHULKER_RESIDUE.get(), ModPotion.LEVITATION);
        builder.addMix(ModPotion.LEVITATION, Items.REDSTONE, ModPotion.LONG_LEVITATION);
        builder.addMix(ModPotion.LEVITATION, Items.GLOWSTONE_DUST, ModPotion.STRONG_LEVITATION);

        // Debonair
        builder.addMix(Potions.AWKWARD, ModItem.WARPED_WART.get(), ModPotion.DEBONAIR);
        // Warped -> Thick
        builder.addMix(Potions.WATER, ModItem.WARPED_WART.get(), Potions.THICK);

        // Reverters
        builder.addContainerRecipe(Items.SPLASH_POTION, ModItem.WARPED_WART.get(), Items.POTION);
        builder.addContainerRecipe(Items.LINGERING_POTION, ModItem.WARPED_WART.get(), Items.SPLASH_POTION);

        // ADVANCED POTION MAKING - FAILS
        builder.addMix(ModPotion.DEBONAIR, Items.SPIDER_EYE, Potions.THICK);                                                                        // Failure - Thick
        builder.addMix(ModPotion.DEBONAIR, Items.BEETROOT, Potions.THICK);                                                                          // Failure - Thick
        builder.addMix(ModPotion.DEBONAIR, Items.INK_SAC, Potions.THICK);                                                                           // Failure - Thick
        builder.addMix(ModPotion.DEBONAIR, Items.GLOW_INK_SAC, Potions.THICK);                                                                      // Failure - Thick

        // ADVANCED POTION MAKING - HEALTH
        builder.addMix(ModPotion.DEBONAIR, Items.GLISTERING_MELON_SLICE, ModPotion.INTERESTING_HEALTH);                                             // Base
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.GLISTERING_MELON_SLICE, Potions.THICK);                                                  // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.GOLDEN_CARROT, Potions.THICK);                                                           // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.SPIDER_EYE, Potions.THICK);                                                              // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.FERMENTED_SPIDER_EYE, Potions.THICK);                                                    // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.GLOW_BERRIES, Potions.THICK);                                                            // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.SWEET_BERRIES, Potions.THICK);                                                           // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.BEETROOT, Potions.THICK);                                                                // Failure - Thick
        builder.addMix(ModPotion.INTERESTING_HEALTH, Items.GHAST_TEAR, Potions.THICK);                                                              // Failure - Thick
        frontiers$makeAdvancedRecipe(builder, ModPotion.INTERESTING_HEALTH, ModItem.ECTOPLASM.get(), ModPotion.TURBO_REGENERATION);                 // Pass - Regeneration
        frontiers$makeAdvancedRecipe(builder, ModPotion.INTERESTING_HEALTH, ModItem.APPLE_OF_ENLIGHTENMENT.get(), ModPotion.LIFE_BOOST);            // Pass - Life Boost

        // ADVANCED POTION MAKING - BAD
        builder.addMix(ModPotion.DEBONAIR, Items.GLISTERING_MELON_SLICE, ModPotion.INTERESTING_HEALTH);               // Base

        // TODO: add more adv pots
    }

    @Unique
    private static void frontiers$makeAdvancedRecipe(PotionBrewing.Builder builder, Holder<Potion> input, Item ingredient, Holder<Potion> output)
    {
        // Base
        builder.addMix(input, ingredient, output);
        // Debonair -> Thick
        // Gluttonous forms
        builder.addMix(output, Items.REDSTONE, ModPotion.GLUTTONY);
        builder.addMix(output, Items.GLOWSTONE_DUST, ModPotion.GLUTTONY);
    }
}
