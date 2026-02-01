package net.artyrian.frontiers.definition.util;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MethodToolbox
{
    /** Allows for use of other rarity colors.*/
    public static Style rarityColor(String id)
    {
        // Colors. Uses default
        int c_Common =               0xFFFFFF;
        int c_Uncommon =             0xFFFF55;
        int c_Rare =                 0x55FFFF;
        int c_Epic =                 0xFF55FF;
        int c_Gold =                 0xFFAA00;
        int c_Green =                0x55FF55;
        int c_Blue =                 0x5555FF;
        int c_FAqua =                0x2BE38D;
        int c_FRed =                 0xFA2D6E;
        int c_FStalePurple =         0xA26FBD;
        int c_FPurBrown =            0x574443;

        //
        Style MC_COMMON =            Style.EMPTY.withColor(TextColor.fromRgb(c_Common));
        Style MC_UNCOMMON =          Style.EMPTY.withColor(TextColor.fromRgb(c_Uncommon));
        Style MC_RARE =              Style.EMPTY.withColor(TextColor.fromRgb(c_Rare));
        Style MC_EPIC =              Style.EMPTY.withColor(TextColor.fromRgb(c_Epic));
        Style MC_GOLD =              Style.EMPTY.withColor(TextColor.fromRgb(c_Gold));
        Style MC_GREEN =             Style.EMPTY.withColor(TextColor.fromRgb(c_Green));
        Style MC_BLUE =              Style.EMPTY.withColor(TextColor.fromRgb(c_Blue));
        //
        Style F_AQUA =               Style.EMPTY.withColor(TextColor.fromRgb(c_FAqua));
        Style F_RED =                Style.EMPTY.withColor(TextColor.fromRgb(c_FRed));
        Style F_STALEPURPLE =        Style.EMPTY.withColor(TextColor.fromRgb(c_FStalePurple));
        Style F_PURBROWN =           Style.EMPTY.withColor(TextColor.fromRgb(c_FPurBrown));

        return switch (id)
        {
            case "common" -> MC_COMMON;
            case "uncommon" -> MC_UNCOMMON;
            case "rare" -> MC_RARE;
            case "epic" -> MC_EPIC;
            case "gold" -> MC_GOLD;
            case "green" -> MC_GREEN;
            case "blue" -> MC_BLUE;

            case "frontiers_mythical" -> F_AQUA;
            case "frontiers_legendary" -> F_RED;
            case "frontiers_unreal" -> F_STALEPURPLE;
            case "frontiers_brown" -> F_PURBROWN;

            default -> MC_COMMON;
        };
    }

    /** Gets unique head sounds based on the provided String. */
    public static ResourceLocation getSpecialHeadSound(String name)
    {
        return switch (name)
        {
            case "Steve" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.steve");
            case "_Artyrian" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.artyrian");
            case "xenona" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.xenona");
            case "Yurjezich" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.yurjezich");
            case "KirbyTG" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.kirbytg");
            case "RealMagic_Man" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.magic");
            case "courtjjester" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.courtjjester");
            case "goldalien2016" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.goldalien2016");
            case "Rednalokin" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.rednalokin");
            case "GreyL1me" -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block.skull.greyl1me");
            default -> ResourceLocation.withDefaultNamespace("entity.player.hurt");
        };
    }

    /** Gets an image based on a random int. This is for a totally serious purpose. I swear. */
    public static ResourceLocation funnyImageProvider(RandomSource random)
    {
        int lols = random.nextIntBetweenInclusive(0, 19);
        return switch (lols)
        {
            case 0 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/baldi.png");
            case 1 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/alphys.png");
            case 2 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/brian.png");
            case 3 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/iloveyousteveharvey.png");
            case 4 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/jonesy.png");
            case 5 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/keemstar.png");
            case 6 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/krabs.png");
            case 7 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/noob.png");
            case 8 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/pensive.png");
            case 9 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/trololo.png");
            case 10 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/unfortunate.png");
            case 11 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/augh.png");
            case 12 -> ResourceLocation.withDefaultNamespace("textures/entity/player/wide/steve.png");
            case 13 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/judge.png");
            case 14 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/getajob.png");
            case 15 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/wega.png");
            case 16 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/tooloud.png");
            case 17 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/dude.png");
            case 18 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/thisiswhatdyingfeelslike.png");
            case 19 -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/gui/joke/thosewhoknow.png");
            default -> ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/joke/mojnay.png");
        };
    }


    /** Determines if an entity is on fire for Loot Table usage. */
    public static AnyOfCondition.Builder onfireCheck(HolderLookup.Provider wrapper)
    {
        HolderLookup.RegistryLookup<Enchantment> impl = wrapper.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
                ),
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.entity()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.equipment()
                                                .mainhand(
                                                        ItemPredicate.Builder.item()
                                                                .withSubPredicate(
                                                                        ItemSubPredicates.ENCHANTMENTS,
                                                                        ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY)))
                                                                )
                                                )
                                )
                )
        );
    }
}
