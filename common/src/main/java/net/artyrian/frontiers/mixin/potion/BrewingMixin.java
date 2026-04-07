package net.artyrian.frontiers.mixin.potion;

import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.property.FRPotions;
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
        return FRItems.HARDENED_SLIME.get();
    }

    @Inject(method = "addVanillaMixes", at = @At("TAIL"))
    private static void registerDefaults(PotionBrewing.Builder builder, CallbackInfo ci)
    {
        // Magma Vision
        builder.addMix(Potions.AWKWARD, FRItems.BRIMTAN_NUGGET.get(), FRPotions.MAGMA_VISION);
        builder.addMix(FRPotions.MAGMA_VISION, Items.REDSTONE, FRPotions.LONG_MAGMA_VISION);

        // Levitation
        builder.addMix(Potions.AWKWARD, FRItems.SHULKER_RESIDUE.get(), FRPotions.LEVITATION);
        builder.addMix(FRPotions.LEVITATION, Items.REDSTONE, FRPotions.LONG_LEVITATION);
        builder.addMix(FRPotions.LEVITATION, Items.GLOWSTONE_DUST, FRPotions.STRONG_LEVITATION);

        // Debonair
        builder.addMix(Potions.AWKWARD, FRItems.WARPED_WART.get(), FRPotions.DEBONAIR);
        // Warped -> Thick
        builder.addMix(Potions.WATER, FRItems.WARPED_WART.get(), Potions.THICK);

        // Reverters
        builder.addContainerRecipe(Items.SPLASH_POTION, FRItems.WARPED_WART.get(), Items.POTION);
        builder.addContainerRecipe(Items.LINGERING_POTION, FRItems.WARPED_WART.get(), Items.SPLASH_POTION);

        // ADVANCED POTION MAKING - FAILS
        builder.addMix(FRPotions.DEBONAIR, Items.SPIDER_EYE, Potions.THICK);                                                                        // Failure - Thick
        builder.addMix(FRPotions.DEBONAIR, Items.BEETROOT, Potions.THICK);                                                                          // Failure - Thick
        builder.addMix(FRPotions.DEBONAIR, Items.INK_SAC, Potions.THICK);                                                                           // Failure - Thick
        builder.addMix(FRPotions.DEBONAIR, Items.GLOW_INK_SAC, Potions.THICK);                                                                      // Failure - Thick

        // ADVANCED POTION MAKING - HEALTH
        builder.addMix(FRPotions.DEBONAIR, Items.GLISTERING_MELON_SLICE, FRPotions.INTERESTING_HEALTH);                                             // Base
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.GLISTERING_MELON_SLICE, Potions.THICK);                                                  // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.GOLDEN_CARROT, Potions.THICK);                                                           // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.SPIDER_EYE, Potions.THICK);                                                              // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.FERMENTED_SPIDER_EYE, Potions.THICK);                                                    // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.GLOW_BERRIES, Potions.THICK);                                                            // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.SWEET_BERRIES, Potions.THICK);                                                           // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.BEETROOT, Potions.THICK);                                                                // Failure - Thick
        builder.addMix(FRPotions.INTERESTING_HEALTH, Items.GHAST_TEAR, Potions.THICK);                                                              // Failure - Thick
        frontiersArtyrian$makeAdvancedRecipe(builder, FRPotions.INTERESTING_HEALTH, FRItems.ECTOPLASM.get(), FRPotions.TURBO_REGENERATION);                 // Pass - Regeneration
        frontiersArtyrian$makeAdvancedRecipe(builder, FRPotions.INTERESTING_HEALTH, FRItems.APPLE_OF_ENLIGHTENMENT.get(), FRPotions.LIFE_BOOST);            // Pass - Life Boost

        // ADVANCED POTION MAKING - BAD
        builder.addMix(FRPotions.DEBONAIR, Items.GLISTERING_MELON_SLICE, FRPotions.INTERESTING_HEALTH);               // Base

        // TODO: add more adv pots
    }

    @Unique
    private static void frontiersArtyrian$makeAdvancedRecipe(PotionBrewing.Builder builder, Holder<Potion> input, Item ingredient, Holder<Potion> output)
    {
        // Base
        builder.addMix(input, ingredient, output);
        // Debonair -> Thick
        // Gluttonous forms
        builder.addMix(output, Items.REDSTONE, FRPotions.GLUTTONY);
        builder.addMix(output, Items.GLOWSTONE_DUST, FRPotions.GLUTTONY);
    }
}
