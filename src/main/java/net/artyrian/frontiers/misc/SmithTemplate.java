package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

// Registers smithing templates to the game - contains necessary resources for its proper usage.
public class SmithTemplate
{
    // Several formatting statics.
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;

    // Vanilla texture identifiers - used in determining what outlines appear on a Smithing Tables input slots.
    public static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_helmet");
    public static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_chestplate");
    public static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_leggings");
    public static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = Identifier.ofVanilla("item/empty_armor_slot_boots");
    public static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.ofVanilla("item/empty_slot_hoe");
    public static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_axe");
    public static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.ofVanilla("item/empty_slot_sword");
    public static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.ofVanilla("item/empty_slot_shovel");
    public static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.ofVanilla("item/empty_slot_pickaxe");
    public static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.ofVanilla("item/empty_slot_ingot");
    public static final Identifier EMPTY_SLOT_REDSTONE_DUST_TEXTURE = Identifier.ofVanilla("item/empty_slot_redstone_dust");
    public static final Identifier EMPTY_SLOT_QUARTZ_TEXTURE = Identifier.ofVanilla("item/empty_slot_quartz");
    public static final Identifier EMPTY_SLOT_EMERALD_TEXTURE = Identifier.ofVanilla("item/empty_slot_emerald");
    public static final Identifier EMPTY_SLOT_DIAMOND_TEXTURE = Identifier.ofVanilla("item/empty_slot_diamond");
    public static final Identifier EMPTY_SLOT_LAPIS_LAZULI_TEXTURE = Identifier.ofVanilla("item/empty_slot_lapis_lazuli");
    public static final Identifier EMPTY_SLOT_AMETHYST_SHARD_TEXTURE = Identifier.ofVanilla("item/empty_slot_amethyst_shard");

    // Mod texture identifiers.
    private static final Identifier EMPTY_SLOT_CASING_TEXTURE = Identifier.of(Frontiers.MOD_ID, "item/empty_slot_casing");

    // Text identifiers for Obsidian upgrade.
    // ORDER: Upgrade text, applies to text, ingredients text, smithing table base slot text, smithing table additions slot text.
    public static final Text OBSIDIAN_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", Identifier.of(Frontiers.MOD_ID,"obsidian_upgrade")))
            .formatted(TITLE_FORMATTING);
    public static final Text OBSIDIAN_UPGRADE_APPLIES_TO_TEXT = Text.translatable(
                    Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID, "smithing_template.obsidian_upgrade.applies_to"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    public static final Text OBSIDIAN_UPGRADE_INGREDIENTS_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID, "smithing_template.obsidian_upgrade.ingredients"))
            )
            .formatted(DESCRIPTION_FORMATTING);
    public static final Text OBSIDIAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID,"smithing_template.obsidian_upgrade.base_slot_description"))
    );
    public static final Text OBSIDIAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(
            Util.createTranslationKey("item", Identifier.of(Frontiers.MOD_ID,"smithing_template.obsidian_upgrade.additions_slot_description"))
    );

    // Returns a casing texture.
    public static List<Identifier> casingAdditionsTexture() {
        return List.of(EMPTY_SLOT_CASING_TEXTURE);
    }

    // Returns a list of armor upgrade textures - for upgrade slot textures.
    public static List<Identifier> armorUpgradeSlotTextures() {
        return List.of(
                EMPTY_ARMOR_SLOT_HELMET_TEXTURE,
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_ARMOR_SLOT_BOOTS_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE
        );
    }
}
