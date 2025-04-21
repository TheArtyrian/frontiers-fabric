package net.artyrian.frontiers.util;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.text.TextColor;

import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

import java.util.List;

public class MethodToolbox
{
    // Allows for use of other rarity colors.
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

    // Gets unique head sounds.
    public static Identifier getSpecialHeadSound(String name)
    {
        return switch (name)
        {
            case "Steve" -> Identifier.of(Frontiers.MOD_ID, "block.skull.steve");
            case "_Artyrian" -> Identifier.of(Frontiers.MOD_ID, "block.skull.artyrian");
            case "xenona" -> Identifier.of(Frontiers.MOD_ID, "block.skull.xenona");
            case "Yurjezich" -> Identifier.of(Frontiers.MOD_ID, "block.skull.yurjezich");
            case "KirbyTG" -> Identifier.of(Frontiers.MOD_ID, "block.skull.kirbytg");
            case "RealMagic_Man" -> Identifier.of(Frontiers.MOD_ID, "block.skull.magic");
            case "courtjjester" -> Identifier.of(Frontiers.MOD_ID, "block.skull.courtjjester");
            case "goldalien2016" -> Identifier.of(Frontiers.MOD_ID, "block.skull.goldalien2016");
            case "Rednalokin" -> Identifier.of(Frontiers.MOD_ID, "block.skull.rednalokin");
            case "GreyL1me" -> Identifier.of(Frontiers.MOD_ID, "block.skull.greyl1me");
            // Never happening lol      case "ChippyGaming_" -> Identifier.of(Frontiers.MOD_ID, "block.skull.chippygaming");
            default -> Identifier.ofVanilla("entity.player.hurt");
        };
    }

    // Determines if an entity is on fire for Loot Table usage.
    public static AnyOfLootCondition.Builder onfireCheck(RegistryWrapper.WrapperLookup wrapper)
    {
        RegistryWrapper.Impl<Enchantment> impl = wrapper.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))
                ),
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.create()
                                .equipment(
                                        EntityEquipmentPredicate.Builder.create()
                                                .mainhand(
                                                        ItemPredicate.Builder.create()
                                                                .subPredicate(
                                                                        ItemSubPredicateTypes.ENCHANTMENTS,
                                                                        EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))
                                                                )
                                                )
                                )
                )
        );
    }
}
