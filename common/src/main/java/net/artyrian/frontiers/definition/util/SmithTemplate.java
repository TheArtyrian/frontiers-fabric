package net.artyrian.frontiers.definition.util;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import java.util.List;
import java.util.Map;

// Has methods and vars necessary to add smithing templates to the game via ModItem.
public class SmithTemplate
{
    // Several formatting statics.
    private static final ChatFormatting TITLE_FORMATTING = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMATTING = ChatFormatting.BLUE;

    // Vanilla texture identifiers - used in determining what outlines appear on a Smithing Tables input slots.
    public static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    public static final ResourceLocation EMPTY_SLOT_HOE_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    public static final ResourceLocation EMPTY_SLOT_AXE_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    public static final ResourceLocation EMPTY_SLOT_SWORD_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    public static final ResourceLocation EMPTY_SLOT_SHOVEL_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    public static final ResourceLocation EMPTY_SLOT_PICKAXE_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    public static final ResourceLocation EMPTY_SLOT_INGOT_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");
    public static final ResourceLocation EMPTY_SLOT_REDSTONE_DUST_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_redstone_dust");
    public static final ResourceLocation EMPTY_SLOT_QUARTZ_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_quartz");
    public static final ResourceLocation EMPTY_SLOT_EMERALD_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_emerald");
    public static final ResourceLocation EMPTY_SLOT_DIAMOND_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_diamond");
    public static final ResourceLocation EMPTY_SLOT_LAPIS_LAZULI_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_lapis_lazuli");
    public static final ResourceLocation EMPTY_SLOT_AMETHYST_SHARD_TEXTURE = ResourceLocation.withDefaultNamespace("item/empty_slot_amethyst_shard");

    // Mod texture identifiers.
    private static final ResourceLocation EMPTY_SLOT_CASING_TEXTURE = Frontiers.id("item/empty_slot_casing");

    // Text identifiers for Obsidian upgrade.
    // ORDER: Upgrade text, applies to text, ingredients text, smithing table base slot text, smithing table additions slot text.
    public static final Component OBSIDIAN_UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("obsidian_upgrade")))
            .withStyle(TITLE_FORMATTING);
    public static final Component OBSIDIAN_UPGRADE_APPLIES_TO_TEXT = Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.obsidian_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMATTING);
    public static final Component OBSIDIAN_UPGRADE_INGREDIENTS_TEXT = Component.translatable(
            Util.makeDescriptionId("item", Frontiers.id("smithing_template.obsidian_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMATTING);
    public static final Component OBSIDIAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(
            Util.makeDescriptionId("item", Frontiers.id("smithing_template.obsidian_upgrade.base_slot_description"))
    );
    public static final Component OBSIDIAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(
            Util.makeDescriptionId("item", Frontiers.id("smithing_template.obsidian_upgrade.additions_slot_description"))
    );

    // Text identifiers for all Brimtan upgrade variants.
    public static final Map<String, Component> BRIMTAN_UPGRADE_TEXT = Map.of(
            "helmet", Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("brimtan_helmet_upgrade"))).withStyle(TITLE_FORMATTING),
            "chestplate", Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("brimtan_chestplate_upgrade"))).withStyle(TITLE_FORMATTING),
            "leggings", Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("brimtan_leggings_upgrade"))).withStyle(TITLE_FORMATTING),
            "boots", Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("brimtan_boots_upgrade"))).withStyle(TITLE_FORMATTING),
            "tool", Component.translatable(Util.makeDescriptionId("upgrade", Frontiers.id("brimtan_tool_upgrade"))).withStyle(TITLE_FORMATTING)
    );
    public static final Map<String, Component> BRIMTAN_UPGRADE_APPLIES_TO_TEXT = Map.of(
            "helmet", Component.translatable(
                            Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_helmet_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING),
            "chestplate", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_chestplate_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING),
            "leggings", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_leggings_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING),
            "boots", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_boots_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING),
            "tool", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_tool_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING)
    );
    public static final Map<String, Component> BRIMTAN_UPGRADE_INGREDIENTS_TEXT = Map.of(
            "helmet", Component.translatable(
                            Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_helmet_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING),
            "chestplate", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_chestplate_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING),
            "leggings", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_leggings_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING),
            "boots", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_boots_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING),
            "tool", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_tool_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING)
    );
    public static final Map<String, Component> BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Map.of(
            "helmet", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_helmet_upgrade.base_slot_description"))),
            "chestplate", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_chestplate_upgrade.base_slot_description"))),
            "leggings", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_leggings_upgrade.base_slot_description"))),
            "boots", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_boots_upgrade.base_slot_description"))),
            "tool", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_tool_upgrade.base_slot_description")))
    );
    public static final Map<String, Component> BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Map.of(
            "helmet", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_helmet_upgrade.additions_slot_description"))),
            "chestplate", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_chestplate_upgrade.additions_slot_description"))),
            "leggings", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_leggings_upgrade.additions_slot_description"))),
            "boots", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_boots_upgrade.additions_slot_description"))),
            "tool", Component.translatable(
                    Util.makeDescriptionId("item", Frontiers.id("smithing_template.brimtan_tool_upgrade.additions_slot_description")))
    );

    // Returns a casing texture.
    public static List<ResourceLocation> casingAdditionsTexture() {
        return List.of(EMPTY_SLOT_CASING_TEXTURE);
    }

    // Returns a list of armor upgrade textures - for upgrade slot textures.
    public static List<ResourceLocation> armorUpgradeSlotTextures() {
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

    // Returns a list of tool upgrade textures - for upgrade slot textures.
    public static List<ResourceLocation> toolUpgradeSlotTextures() {
        return List.of(
                EMPTY_SLOT_SWORD_TEXTURE,
                EMPTY_SLOT_PICKAXE_TEXTURE,
                EMPTY_SLOT_AXE_TEXTURE,
                EMPTY_SLOT_HOE_TEXTURE,
                EMPTY_SLOT_SHOVEL_TEXTURE
        );
    }
}
