package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;

import java.util.ArrayList;
import java.util.List;

public class FRItemTabs
{
    public static final ResourceKey<CreativeModeTab> FRONTIERS_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, Frontiers.id("frontiers"));

    private static final List<VectorItemTab> ALL_TABS = new ArrayList<>();

    private static final VectorItemTab FRONTIERS = VectorItemTab.ofNew(FRONTIERS_TAB, Frontiers.CONFIG.doCreativeModeTabs(), ALL_TABS);
    private static final VectorItemTab TOOLS = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.TOOLS, ALL_TABS);
    private static final VectorItemTab COMBAT = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.COMBAT, ALL_TABS);
    private static final VectorItemTab INGREDIENTS = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.INGREDIENTS, ALL_TABS);
    private static final VectorItemTab NATURAL = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.NATURAL, ALL_TABS);
    private static final VectorItemTab BUILDING_BLOCKS = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.BUILDING, ALL_TABS);
    private static final VectorItemTab FUNCTIONAL = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.FUNCTIONAL, ALL_TABS);
    private static final VectorItemTab REDSTONE = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.REDSTONE, ALL_TABS);
    private static final VectorItemTab FOOD_AND_DRINK = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.FOOD, ALL_TABS);
    private static final VectorItemTab SPAWN_EGGS = VectorItemTab.ofExisting(VectorItemTab.VanillaTab.SPAWN_EGGS, ALL_TABS);

    // Vanilla tab - Tools & Utilities.
    public static void tabTools()
    {
        TOOLS.addAfter(Items.GOLDEN_HOE, FRItems.MOURNING_GOLD_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.MOURNING_GOLD_SHOVEL.get(), FRItems.MOURNING_GOLD_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.MOURNING_GOLD_PICKAXE.get(), FRItems.MOURNING_GOLD_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.MOURNING_GOLD_AXE.get(), FRItems.MOURNING_GOLD_HOE.get(), FRONTIERS);

        TOOLS.addAfter(Items.NETHERITE_HOE, FRItems.OBSIDIAN_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.OBSIDIAN_SHOVEL.get(), FRItems.OBSIDIAN_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.OBSIDIAN_PICKAXE.get(), FRItems.OBSIDIAN_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.OBSIDIAN_AXE.get(), FRItems.OBSIDIAN_HOE.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.OBSIDIAN_HOE.get(), FRItems.COBALT_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COBALT_SHOVEL.get(), FRItems.COBALT_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COBALT_PICKAXE.get(), FRItems.COBALT_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COBALT_AXE.get(), FRItems.COBALT_HOE.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.COBALT_HOE.get(), FRItems.VERDINITE_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VERDINITE_SHOVEL.get(), FRItems.VERDINITE_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VERDINITE_PICKAXE.get(), FRItems.VERDINITE_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VERDINITE_AXE.get(), FRItems.VERDINITE_HOE.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.VERDINITE_HOE.get(), FRItems.FROSTITE_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.FROSTITE_SHOVEL.get(), FRItems.FROSTITE_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.FROSTITE_PICKAXE.get(), FRItems.FROSTITE_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.FROSTITE_AXE.get(), FRItems.FROSTITE_HOE.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.FROSTITE_HOE.get(), FRItems.VIVULITE_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VIVULITE_SHOVEL.get(), FRItems.VIVULITE_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VIVULITE_PICKAXE.get(), FRItems.VIVULITE_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.VIVULITE_AXE.get(), FRItems.VIVULITE_HOE.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.VIVULITE_HOE.get(), FRItems.BRIMTAN_SHOVEL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.BRIMTAN_SHOVEL.get(), FRItems.BRIMTAN_PICKAXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.BRIMTAN_PICKAXE.get(), FRItems.BRIMTAN_AXE.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.BRIMTAN_AXE.get(), FRItems.BRIMTAN_HOE.get(), FRONTIERS);

        TOOLS.addAfter(Items.FISHING_ROD, FRItems.COBALT_FISHING_ROD.get(), FRONTIERS);

        TOOLS.addAfter(Items.WRITABLE_BOOK, FRItems.MESSAGE_IN_A_BOTTLE.get(), FRONTIERS);

        TOOLS.addAfter(Items.ENDER_PEARL, FRItems.VOID_PEARL.get(), FRONTIERS);

        TOOLS.addAfter(Items.ELYTRA, FRItems.END_CRYSTAL_SHARD.get(), FRONTIERS);

        TOOLS.addAfter(Items.BONE_MEAL, FRItems.SNOW_MELT.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.SNOW_MELT.get(), FRItems.ONYX_MEAL.get(), FRONTIERS);

        TOOLS.addBefore(Items.COMPASS, FRItems.CHEST_KEY.get(), FRONTIERS);

        TOOLS.addAfter(Items.LEAD, FRItems.WITCH_HAT.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.WITCH_HAT.get(), FRBlocks.WHITE_PUMPKIN.get(), FRONTIERS);

        TOOLS.addAfter(InstrumentItem.create(Items.GOAT_HORN,
                BuiltInRegistries.INSTRUMENT.getHolder(Instruments.DREAM_GOAT_HORN).get()), FRItems.BALL.get(), FRONTIERS);
        TOOLS.addAfter(FRItems.BALL.get(), FRItems.BOUNCY_BALL.get(), FRONTIERS);

        TOOLS.addAfter(FRItems.BOUNCY_BALL.get(), FRItems.COLOR_BALLS.get(DyeColor.WHITE).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.WHITE).get(), FRItems.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), FRItems.COLOR_BALLS.get(DyeColor.GRAY).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.GRAY).get(), FRItems.COLOR_BALLS.get(DyeColor.BLACK).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BLACK).get(), FRItems.COLOR_BALLS.get(DyeColor.BROWN).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BROWN).get(), FRItems.COLOR_BALLS.get(DyeColor.RED).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.RED).get(), FRItems.COLOR_BALLS.get(DyeColor.ORANGE).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.ORANGE).get(), FRItems.COLOR_BALLS.get(DyeColor.YELLOW).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.YELLOW).get(), FRItems.COLOR_BALLS.get(DyeColor.LIME).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIME).get(), FRItems.COLOR_BALLS.get(DyeColor.GREEN).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.GREEN).get(), FRItems.COLOR_BALLS.get(DyeColor.CYAN).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.CYAN).get(), FRItems.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), FRItems.COLOR_BALLS.get(DyeColor.BLUE).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BLUE).get(), FRItems.COLOR_BALLS.get(DyeColor.PURPLE).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.PURPLE).get(), FRItems.COLOR_BALLS.get(DyeColor.MAGENTA).get(), FRONTIERS);
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.MAGENTA).get(), FRItems.COLOR_BALLS.get(DyeColor.PINK).get(), FRONTIERS);

        // Horrible code for integration balls
        Item last = FRItems.COLOR_BALLS.get(DyeColor.PINK).get();
        for (DyeColor color : DyeColor.values())
        {
            if (color.getId() > DyeColor.BLACK.getId())
            {
                TOOLS.addAfter(last, FRItems.COLOR_BALLS.get(color).get(), FRONTIERS);
                last = FRItems.COLOR_BALLS.get(color).get();
            }
        }

        TOOLS.addAfter(Items.MUSIC_DISC_PIGSTEP, FRItems.MUSIC_DISC_DIAPHRAGM.get(), FRONTIERS);
    }

    // Vanilla tab - Combat.
    public static void tabCombat()
    {
        COMBAT.addAfter(Items.GOLDEN_SWORD, FRItems.MOURNING_GOLD_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(Items.NETHERITE_SWORD, FRItems.OBSIDIAN_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.OBSIDIAN_SWORD.get(), FRItems.COBALT_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_SWORD.get(), FRItems.VERDINITE_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_SWORD.get(), FRItems.FROSTITE_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.FROSTITE_SWORD.get(), FRItems.VIVULITE_SWORD.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VIVULITE_SWORD.get(), FRItems.BRIMTAN_SWORD.get(), FRONTIERS);

        COMBAT.addAfter(Items.GOLDEN_AXE, FRItems.MOURNING_GOLD_AXE.get(), FRONTIERS);
        COMBAT.addAfter(Items.NETHERITE_AXE, FRItems.OBSIDIAN_AXE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.OBSIDIAN_AXE.get(), FRItems.COBALT_AXE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_AXE.get(), FRItems.VERDINITE_AXE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_AXE.get(), FRItems.FROSTITE_AXE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.FROSTITE_AXE.get(), FRItems.VIVULITE_AXE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VIVULITE_AXE.get(), FRItems.BRIMTAN_AXE.get(), FRONTIERS);

        COMBAT.addAfter(Items.GOLDEN_BOOTS, FRItems.MOURNING_GOLD_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.MOURNING_GOLD_HELMET.get(), FRItems.MOURNING_GOLD_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.MOURNING_GOLD_CHESTPLATE.get(), FRItems.MOURNING_GOLD_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.MOURNING_GOLD_LEGGINGS.get(), FRItems.MOURNING_GOLD_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(Items.DIAMOND_BOOTS, FRItems.NECRO_WEAVE_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.NECRO_WEAVE_HELMET.get(), FRItems.NECRO_WEAVE_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.NECRO_WEAVE_CHESTPLATE.get(), FRItems.NECRO_WEAVE_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.NECRO_WEAVE_LEGGINGS.get(), FRItems.NECRO_WEAVE_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(Items.NETHERITE_BOOTS, FRItems.COBALT_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_HELMET.get(), FRItems.COBALT_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_CHESTPLATE.get(), FRItems.COBALT_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_LEGGINGS.get(), FRItems.COBALT_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(FRItems.COBALT_BOOTS.get(), FRItems.VERDINITE_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_HELMET.get(), FRItems.VERDINITE_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_CHESTPLATE.get(), FRItems.VERDINITE_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_LEGGINGS.get(), FRItems.VERDINITE_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(FRItems.VERDINITE_BOOTS.get(), FRItems.FROSTITE_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.FROSTITE_HELMET.get(), FRItems.FROSTITE_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.FROSTITE_CHESTPLATE.get(), FRItems.FROSTITE_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.FROSTITE_LEGGINGS.get(), FRItems.FROSTITE_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(FRItems.FROSTITE_BOOTS.get(), FRItems.VIVULITE_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VIVULITE_HELMET.get(), FRItems.VIVULITE_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VIVULITE_CHESTPLATE.get(), FRItems.VIVULITE_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VIVULITE_LEGGINGS.get(), FRItems.VIVULITE_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(FRItems.VIVULITE_BOOTS.get(), FRItems.BRIMTAN_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.BRIMTAN_HELMET.get(), FRItems.BRIMTAN_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.BRIMTAN_CHESTPLATE.get(), FRItems.BRIMTAN_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.BRIMTAN_LEGGINGS.get(), FRItems.BRIMTAN_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(Items.TURTLE_HELMET, FRItems.SLIME_SHOES.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.SLIME_SHOES.get(), FRItems.PLATE_HELMET.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.PLATE_HELMET.get(), FRItems.PLATE_CHESTPLATE.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.PLATE_CHESTPLATE.get(), FRItems.PLATE_LEGGINGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.PLATE_LEGGINGS.get(), FRItems.PLATE_BOOTS.get(), FRONTIERS);

        COMBAT.addAfter(Items.ARROW, FRItems.SUBZERO_ARROW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.SUBZERO_ARROW.get(), FRItems.BOUNCY_ARROW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.BOUNCY_ARROW.get(), FRItems.WARP_ARROW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.WARP_ARROW.get(), FRItems.DYNAMITE_ARROW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.DYNAMITE_ARROW.get(), FRItems.PRISMARINE_ARROW.get(), FRONTIERS);

        COMBAT.addAfter(Items.CROSSBOW, FRItems.COPPER_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COPPER_BOW.get(), FRItems.IRON_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.IRON_BOW.get(), FRItems.DIAMOND_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.DIAMOND_BOW.get(), FRItems.NETHERITE_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.NETHERITE_BOW.get(), FRItems.ECHO_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.ECHO_BOW.get(), FRItems.VERDINITE_BOW.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_BOW.get(), FRItems.TOME_OF_FANGS.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.TOME_OF_FANGS.get(), FRItems.THUNDERVAST_TOME.get(), FRONTIERS);

        COMBAT.addAfter(Items.TOTEM_OF_UNDYING, FRItems.TOTEM_OF_AVARICE.get(), FRONTIERS);

        COMBAT.addAfter(Items.SHIELD, FRItems.COBALT_SHIELD.get(), FRONTIERS);

        COMBAT.addAfter(Items.TNT, FRBlocks.PHANTASMIC_TNT.get(), FRONTIERS);

        COMBAT.addAfter(Items.TRIDENT, FRItems.PALE_TRIDENT.get(), FRONTIERS);

        COMBAT.addAfter(Items.DIAMOND_HORSE_ARMOR, FRItems.COBALT_HORSE_ARMOR.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.COBALT_HORSE_ARMOR.get(), FRItems.VERDINITE_HORSE_ARMOR.get(), FRONTIERS);
        COMBAT.addAfter(FRItems.VERDINITE_HORSE_ARMOR.get(), FRItems.VIVULITE_HORSE_ARMOR.get(), FRONTIERS);
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients()
    {
        INGREDIENTS.addAfter(Items.NETHERITE_INGOT, FRItems.OBSIDIAN_CASING.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.OBSIDIAN_CASING.get(), FRItems.MOURNING_GOLD_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.MOURNING_GOLD_INGOT.get(), FRItems.COBALT_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.COBALT_INGOT.get(), FRItems.VERDINITE_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.VERDINITE_INGOT.get(), FRItems.FROSTITE_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.FROSTITE_INGOT.get(), FRItems.VIVULITE_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.VIVULITE_INGOT.get(), FRItems.BRIMTAN_INGOT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_INGOT.get(), FRItems.PITCH_INGOT.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.RAW_GOLD, FRItems.RAW_COBALT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.RAW_COBALT.get(), FRItems.RAW_VERDINITE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.RAW_VERDINITE.get(), FRItems.RAW_FROSTITE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.RAW_FROSTITE.get(), FRItems.RAW_VIVULITE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.RAW_VIVULITE.get(), FRItems.BRIMTAN_CLUSTER.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.GOLD_NUGGET, FRItems.BRIMTAN_NUGGET.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.AMETHYST_SHARD, FRItems.HARDENED_SLIME.get(), FRONTIERS);

        INGREDIENTS.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, FRItems.TABLET_FRAGMENT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.TABLET_FRAGMENT.get(), FRItems.CURSED_TABLET.get(), FRONTIERS);
        INGREDIENTS.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.HEAVY_CORE, FRItems.SPAWNER_CHUNK.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.EGG, FRItems.GOLDEN_EGG.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.NETHER_STAR, FRItems.WITHERED_ESSENCE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.WITHERED_ESSENCE.get(), FRItems.HEART_OF_THE_WARDEN.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.HEART_OF_THE_WARDEN.get(), FRItems.SOUL.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.SHULKER_SHELL, FRItems.SHULKER_RESIDUE.get(), FRONTIERS);
        INGREDIENTS.addAfter(Items.NETHER_BRICK, FRItems.NACRE_BRICK.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.ENDER_EYE, FRItems.END_CRYSTAL_SHARD.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.NETHER_WART, FRItems.WARPED_WART.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.DIAMOND, FRItems.VOID_DIAMOND.get(), FRONTIERS);
        INGREDIENTS.addAfter(Items.EMERALD, FRItems.BLACK_EMERALD.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.PRISMARINE_SHARD, FRItems.PALE_PRISMARINE_SHARD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.PALE_PRISMARINE_SHARD.get(), FRItems.ELDER_GUARDIAN_SPINE.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.FLINT, FRItems.SPECTRAL_ARROW_ARROWHEAD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.SPECTRAL_ARROW_ARROWHEAD.get(), FRItems.SUBZERO_ARROW_ARROWHEAD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.SUBZERO_ARROW_ARROWHEAD.get(), FRItems.BOUNCY_ARROW_ARROWHEAD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BOUNCY_ARROW_ARROWHEAD.get(), FRItems.WARP_ARROW_ARROWHEAD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.WARP_ARROW_ARROWHEAD.get(), FRItems.DYNAMITE_ARROW_ARROWHEAD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.DYNAMITE_ARROW_ARROWHEAD.get(), FRItems.PRISMARINE_ARROW_ARROWHEAD.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.EXPERIENCE_BOTTLE, FRItems.MANA_BOTTLE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.MANA_BOTTLE.get(), FRItems.BRIMTAN_SHELL_SWORD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_SWORD.get(), FRItems.BRIMTAN_SHELL_SHOVEL.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_SHOVEL.get(), FRItems.BRIMTAN_SHELL_PICKAXE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_PICKAXE.get(), FRItems.BRIMTAN_SHELL_AXE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_AXE.get(), FRItems.BRIMTAN_SHELL_HOE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_HOE.get(), FRItems.BRIMTAN_SHELL_HELMET.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_HELMET.get(), FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), FRItems.BRIMTAN_SHELL_LEGGINGS.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_LEGGINGS.get(), FRItems.BRIMTAN_SHELL_BOOTS.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.BONE, FRItems.FROST_BONE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.FROST_BONE.get(), FRItems.ONYX_BONE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.ONYX_BONE.get(), FRItems.NECRO_WEAVE.get(), FRONTIERS);
        INGREDIENTS.addAfter(Items.BONE_MEAL, FRItems.SNOW_MELT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.SNOW_MELT.get(), FRItems.ONYX_MEAL.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.ENDER_PEARL, FRItems.VOID_PEARL.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.GHAST_TEAR, FRItems.ECTOPLASM.get(), FRONTIERS);
        INGREDIENTS.addBefore(Items.PRISMARINE_SHARD, FRItems.INVOKE_SHARD.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.INVOKE_SHARD.get(), FRItems.INCENSE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.INCENSE.get(), FRItems.RAVAGER_TOOTH.get(), FRONTIERS);

        INGREDIENTS.addBefore(Items.DRAGON_BREATH, FRItems.LIGHTNING_IN_A_BOTTLE.get(), FRONTIERS);

        INGREDIENTS.addAfter(Items.OMINOUS_TRIAL_KEY, FRItems.TOWER_KEY.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.TOWER_KEY.get(), FRItems.TOWER_KEY_FRAGMENT.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.TOWER_KEY_FRAGMENT.get(), FRItems.UNFINISHED_CORE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.UNFINISHED_CORE.get(), FRItems.REACTIVE_CORE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.REACTIVE_CORE.get(), FRItems.DEPTHS_CORE_PLATE.get(), FRONTIERS);
        INGREDIENTS.addAfter(FRItems.DEPTHS_CORE_PLATE.get(), FRItems.FRONTAL_CORE_PLATE.get(), FRONTIERS);
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural()
    {
        NATURAL.addAfter(Blocks.DEEPSLATE_EMERALD_ORE, FRBlocks.BLACK_EMERALD_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.BLACK_EMERALD_ORE.get(), FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, FRBlocks.COBALT_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.COBALT_ORE.get(), FRBlocks.DEEPSLATE_COBALT_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.DEEPSLATE_COBALT_ORE.get(), FRBlocks.VERDINITE_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.VERDINITE_ORE.get(), FRBlocks.DEEPSLATE_VERDINITE_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.DEEPSLATE_VERDINITE_ORE.get(), FRBlocks.VIVULITE_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.VIVULITE_ORE.get(), FRBlocks.DEEPSLATE_VIVULITE_ORE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.DEEPSLATE_VIVULITE_ORE.get(), FRBlocks.FROSTITE_ORE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.RAW_GOLD_BLOCK, FRBlocks.RAW_COBALT_BLOCK.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.RAW_COBALT_BLOCK.get(), FRBlocks.RAW_VERDINITE_BLOCK.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.RAW_VERDINITE_BLOCK.get(), FRBlocks.RAW_VIVULITE_BLOCK.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.RAW_VIVULITE_BLOCK.get(), FRBlocks.RAW_FROSTITE_BLOCK.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.OBSIDIAN, FRBlocks.GLOWING_OBSIDIAN.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.TUFF, FRBlocks.HIELOSTONE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.SMALL_DRIPLEAF, FRBlocks.SLIME_BULB.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.SLIME_BULB.get(), FRBlocks.SLIME_TRAIL.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.POPPY, FRBlocks.ROSE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.ROSE.get(), FRBlocks.ANCIENT_ROSE.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.ANCIENT_ROSE.get(), FRBlocks.VIOLET_ROSE.get(), FRONTIERS);
        NATURAL.addAfter(Blocks.ROSE_BUSH, FRBlocks.ANCIENT_ROSE_BUSH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.ANCIENT_ROSE_BUSH.get(), FRBlocks.VIOLET_ROSE_BUSH.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.BONE_BLOCK, FRBlocks.ONYX_BONE_BLOCK.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.LILY_OF_THE_VALLEY, FRBlocks.SNOW_DAHLIA.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.SNOW_DAHLIA.get(), FRBlocks.FUNGAL_DAFFODIL.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.FUNGAL_DAFFODIL.get(), FRBlocks.CRIMCONE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.WITHER_ROSE, FRBlocks.EXPERIWINKLE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.CHERRY_SAPLING, FRBlocks.BLIGHTED_BIRCH_SAPLING.get(), FRONTIERS);
        NATURAL.addAfter(Blocks.CHERRY_LEAVES, FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), FRONTIERS);
        NATURAL.addAfter(Blocks.CHERRY_LOG, FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.RED_MUSHROOM_BLOCK, FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), FRONTIERS);

        NATURAL.addAfter(Items.PITCHER_POD, FRItems.ANCIENT_ROSE_SEED.get(), FRONTIERS);
        NATURAL.addAfter(Items.NETHER_WART, FRItems.WARPED_WART.get(), FRONTIERS);
        NATURAL.addAfter(FRItems.WARPED_WART.get(), FRItems.EXPERIWINKLE_BULB.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.AMETHYST_CLUSTER, FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get(), FRONTIERS);

        NATURAL.addBefore(Items.OAK_SAPLING, FRBlocks.OAK_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.OAK_WREATH.get(), FRBlocks.SPRUCE_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.SPRUCE_WREATH.get(), FRBlocks.BIRCH_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.BIRCH_WREATH.get(), FRBlocks.JUNGLE_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.JUNGLE_WREATH.get(), FRBlocks.ACACIA_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.ACACIA_WREATH.get(), FRBlocks.DARK_OAK_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.DARK_OAK_WREATH.get(), FRBlocks.MANGROVE_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.MANGROVE_WREATH.get(), FRBlocks.CHERRY_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.CHERRY_WREATH.get(), FRBlocks.BLIGHTED_BIRCH_WREATH.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.BLIGHTED_BIRCH_WREATH.get(), FRBlocks.AZALEA_WREATH.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.SAND, FRBlocks.QUICKSAND.get(), FRONTIERS);
        NATURAL.addAfter(Blocks.RED_SAND, FRBlocks.RED_QUICKSAND.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.WARPED_STEM, FRBlocks.EBONCORK.get(), FRONTIERS);

        NATURAL.addBefore(Blocks.END_STONE, FRBlocks.CRAGULSTANE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.HAY_BLOCK, FRBlocks.SUGAR_CANE_BLOCK.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.SUGAR_CANE_BLOCK.get(), FRBlocks.COCOA_BEAN_BLOCK.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.COCOA_BEAN_BLOCK.get(), FRBlocks.EGG_PALLET.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.EGG_PALLET.get(), FRBlocks.GOLDEN_EGG_PALLET.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.GOLDEN_EGG_PALLET.get(), FRBlocks.ROTTEN_FLESH_BLOCK.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.ANCIENT_DEBRIS, FRBlocks.BRIMTAN_ORE.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.MELON, FRBlocks.CARVED_MELON.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.CARVED_MELON.get(), FRBlocks.JUNE_O_LANTERN.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.JUNE_O_LANTERN.get(), FRBlocks.GLISTERING_MELON.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.GLISTERING_MELON.get(), FRBlocks.CARVED_GLISTERING_MELON.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.CARVED_GLISTERING_MELON.get(), FRBlocks.GLISTERING_JUNE_O_LANTERN.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.JACK_O_LANTERN, FRBlocks.WHITE_PUMPKIN.get(), FRONTIERS);
        NATURAL.addAfter(FRBlocks.WHITE_PUMPKIN.get(), FRBlocks.WHITE_JACK_O_LANTERN.get(), FRONTIERS);

        NATURAL.addAfter(Blocks.BEDROCK, FRBlocks.AESTHENOSTONE.get(), FRONTIERS);
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood()
    {
        FOOD_AND_DRINK.addAfter(Items.BREAD, FRBlocks.BEEF_WELLINGTON.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.CAKE, FRBlocks.FRUITCAKE.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRBlocks.FRUITCAKE.get(), FRItems.FRUITCAKE_SLICE.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.PUMPKIN_PIE, FRItems.LEVI_ROLL.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.LEVI_ROLL.get(), FRItems.MARSHMALLOW.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.MARSHMALLOW.get(), FRItems.ROASTED_MARSHMALLOW.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.DRIED_KELP, FRItems.TRUFFLE.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.TRUFFLE.get(), FRItems.TRUFFLE_OIL.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.TRUFFLE_OIL.get(), FRItems.TRUFFLE_POTATO_PUFF.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.PUFFERFISH, FRItems.GUARDIAN_SLICE.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.GUARDIAN_SLICE.get(), FRItems.COOKED_GUARDIAN_SLICE.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.COOKED_GUARDIAN_SLICE.get(), FRItems.ELDER_GUARDIAN_SLICE.get(), FRONTIERS);
        FOOD_AND_DRINK.addAfter(FRItems.ELDER_GUARDIAN_SLICE.get(), FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.ENCHANTED_GOLDEN_APPLE, FRItems.APPLE_OF_ENLIGHTENMENT.get(), FRONTIERS);

        FOOD_AND_DRINK.addAfter(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE);

        FOOD_AND_DRINK.addAfter(Items.SWEET_BERRIES, FRItems.POMEGRANATE.get(), FRONTIERS);
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding()
    {
        BUILDING_BLOCKS.addAfter(Blocks.EMERALD_BLOCK, FRBlocks.BLACK_EMERALD_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(Blocks.DIAMOND_BLOCK, FRBlocks.MOURNING_GOLD_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.MOURNING_GOLD_BLOCK.get(), FRBlocks.NECRO_WEAVE_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(Blocks.NETHERITE_BLOCK, FRBlocks.COBALT_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.COBALT_BLOCK.get(), FRBlocks.COBALT_GRILLES.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.COBALT_GRILLES.get(), FRBlocks.VERDINITE_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.VERDINITE_BLOCK.get(), FRBlocks.FROSTITE_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.FROSTITE_BLOCK.get(), FRBlocks.VIVULITE_BLOCK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.VIVULITE_BLOCK.get(), FRBlocks.BRIMTAN_BLOCK.get(), FRONTIERS);

        BUILDING_BLOCKS.addBefore(Blocks.PRISMARINE, FRBlocks.SEA_GLASS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.SEA_GLASS.get(), FRBlocks.SEA_GLASS_PANE.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.DARK_PRISMARINE_SLAB, FRBlocks.PALE_SEA_GLASS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_SEA_GLASS.get(), FRBlocks.PALE_SEA_GLASS_PANE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_SEA_GLASS_PANE.get(), FRBlocks.PALE_PRISMARINE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE.get(), FRBlocks.PALE_PRISMARINE_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_STAIRS.get(), FRBlocks.PALE_PRISMARINE_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_SLAB.get(), FRBlocks.PALE_PRISMARINE_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_WALL.get(), FRBlocks.PALE_PRISMARINE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICKS.get(), FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), FRBlocks.DEEP_PALE_PRISMARINE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE.get(), FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.NETHER_BRICK_FENCE, FRBlocks.NETHER_BRICK_FENCE_GATE.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICKS, FRBlocks.CRACKED_RED_NETHER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICK_WALL, FRBlocks.RED_NETHER_BRICK_FENCE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.RED_NETHER_BRICK_FENCE.get(), FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_RED_NETHER_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_RED_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get(), FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), FRBlocks.BLUE_NETHER_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_WALL.get(), FRBlocks.BLUE_NETHER_BRICK_FENCE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_FENCE.get(), FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), FRBlocks.PURPLE_NETHER_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_WALL.get(), FRBlocks.PURPLE_NETHER_BRICK_FENCE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get(), FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.PURPUR_SLAB, FRBlocks.NACRE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICKS.get(), FRBlocks.NACRE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICK_STAIRS.get(), FRBlocks.NACRE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICK_SLAB.get(), FRBlocks.NACRE_BRICK_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), FRBlocks.TOWER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICKS.get(), FRBlocks.TOWER_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_STAIRS.get(), FRBlocks.TOWER_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_SLAB.get(), FRBlocks.TOWER_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_WALL.get(), FRBlocks.MOSSY_TOWER_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), FRBlocks.MOSSY_TOWER_BRICK_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.CHERRY_BUTTON, FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), FRBlocks.BLIGHTED_BIRCH_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_SLAB.get(), FRBlocks.BLIGHTED_BIRCH_FENCE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_FENCE.get(), FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), FRItems.BLIGHTED_BIRCH_DOOR.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRItems.BLIGHTED_BIRCH_DOOR.get(), FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), FRBlocks.BLIGHTED_BIRCH_BUTTON.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.WARPED_BUTTON, FRBlocks.EBONCORK.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK.get(), FRBlocks.EBONCORK_PLANKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_PLANKS.get(), FRBlocks.EBONCORK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_STAIRS.get(), FRBlocks.EBONCORK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_SLAB.get(), FRBlocks.EBONCORK_FENCE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_FENCE.get(), FRBlocks.EBONCORK_FENCE_GATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_FENCE_GATE.get(), FRItems.EBONCORK_DOOR.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRItems.EBONCORK_DOOR.get(), FRBlocks.EBONCORK_TRAPDOOR.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_TRAPDOOR.get(), FRBlocks.EBONCORK_PRESSURE_PLATE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_PRESSURE_PLATE.get(), FRBlocks.EBONCORK_BUTTON.get(), FRONTIERS);

        BUILDING_BLOCKS.addBefore(Blocks.END_STONE, FRBlocks.CRAGULSTANE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE.get(), FRBlocks.CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRAGULSTANE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.CRAGULSTANE_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, FRBlocks.CRUSTED_QUICKSAND.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTED_QUICKSAND.get(), FRBlocks.CRUSTY_SAND_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICKS.get(), FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get(), FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), FRBlocks.CRUSTY_SAND_BRICK_WALL.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_WALL.get(), FRBlocks.CRUSTED_RED_QUICKSAND.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTED_RED_QUICKSAND.get(), FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addBefore(Blocks.SEA_LANTERN, FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), FRBlocks.TURTLE_SCUTE_BRICK_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(Blocks.CHISELED_TUFF_BRICKS, FRBlocks.HIELOSTONE.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_STAIRS.get(), FRBlocks.HIELOSTONE_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_SLAB.get(), FRBlocks.HIELOSTONE_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_WALL.get(), FRBlocks.HIELOSTONE_BRICKS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_BRICK_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_STAIRS.get(), FRBlocks.HIELOSTONE_BRICK_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_SLAB.get(), FRBlocks.HIELOSTONE_BRICK_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_WALL.get(), FRBlocks.HIELOSTONE_TILES.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILES.get(), FRBlocks.HIELOSTONE_TILE_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_STAIRS.get(), FRBlocks.HIELOSTONE_TILE_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_SLAB.get(), FRBlocks.HIELOSTONE_TILE_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_WALL.get(), FRBlocks.HIELOSTONE_PLATES.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATES.get(), FRBlocks.HIELOSTONE_PLATE_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_STAIRS.get(), FRBlocks.HIELOSTONE_PLATE_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_SLAB.get(), FRBlocks.HIELOSTONE_PLATE_WALL.get(), FRONTIERS);

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_WALL.get(), FRBlocks.COBBLEFROST.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST.get(), FRBlocks.COBBLEFROST_STAIRS.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST_STAIRS.get(), FRBlocks.COBBLEFROST_SLAB.get(), FRONTIERS);
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST_SLAB.get(), FRBlocks.COBBLEFROST_WALL.get(), FRONTIERS);
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional()
    {
        FUNCTIONAL.addAfter(Blocks.ENCHANTING_TABLE, FRBlocks.CURSE_ALTAR.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Items.END_CRYSTAL, FRItems.PURIFIED_END_CRYSTAL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(Blocks.BEACON, FRBlocks.ENCHANTING_MAGNET.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.ENCHANTING_MAGNET.get(), FRBlocks.STRANGE_CORE.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Items.REDSTONE_LAMP, FRBlocks.DIAMOND_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.DIAMOND_LUMEN.get(), FRBlocks.REDSTONE_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.REDSTONE_LUMEN.get(), FRBlocks.QUARTZ_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.QUARTZ_LUMEN.get(), FRBlocks.EMERALD_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.EMERALD_LUMEN.get(), FRBlocks.AMETHYST_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.AMETHYST_LUMEN.get(), FRBlocks.ECHO_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.ECHO_LUMEN.get(), FRBlocks.COBALT_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.COBALT_LUMEN.get(), FRBlocks.VERDINITE_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.VERDINITE_LUMEN.get(), FRBlocks.FROSTITE_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.FROSTITE_LUMEN.get(), FRBlocks.VIVULITE_LUMEN.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.VIVULITE_LUMEN.get(), FRBlocks.BRIMTAN_LUMEN.get(), FRONTIERS);

        FUNCTIONAL.addBefore(Items.SKELETON_SKULL, FRBlocks.CREEPER_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.CREEPER_MODEL.get(), FRBlocks.SLIME_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.SLIME_MODEL.get(), FRBlocks.SKELETON_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.SKELETON_MODEL.get(), FRBlocks.STRAY_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.STRAY_MODEL.get(), FRBlocks.BOGGED_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.BOGGED_MODEL.get(), FRBlocks.WITHER_SKELETON_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.WITHER_SKELETON_MODEL.get(), FRBlocks.BLAZE_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.BLAZE_MODEL.get(), FRBlocks.MAGMA_CUBE_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.MAGMA_CUBE_MODEL.get(), FRBlocks.ENDERMAN_MODEL.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.ENDERMAN_MODEL.get(), FRBlocks.PHANTOM_MODEL.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Items.DECORATED_POT, FRBlocks.ITEM_VACUUM.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Items.SCAFFOLDING, FRBlocks.NECRO_RUG.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.DAMAGED_ANVIL, FRBlocks.VIVULITE_ANVIL.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.SOUL_CAMPFIRE, FRBlocks.MONSTER_BAKERY.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.CHEST, FRBlocks.PERSONAL_CHEST.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.PINK_CANDLE, FRItems.SPIRIT_CANDLE.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.PINK_BED, FRItems.PHANTOM_STITCH_BED.get(), FRONTIERS);

        FUNCTIONAL.addAfter(Blocks.VAULT, FRBlocks.TOWER_WATCHER.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.TOWER_WATCHER.get(), FRBlocks.TOWER_HEART.get(), FRONTIERS);
        FUNCTIONAL.addAfter(FRBlocks.TOWER_HEART.get(), FRBlocks.TOWER_TREASURE_VAULT.get(), FRONTIERS);
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone()
    {
        REDSTONE.addAfter(Items.LIGHTNING_ROD, FRBlocks.DIAMOND_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.DIAMOND_LUMEN.get(), FRBlocks.REDSTONE_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.REDSTONE_LUMEN.get(), FRBlocks.QUARTZ_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.QUARTZ_LUMEN.get(), FRBlocks.EMERALD_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.EMERALD_LUMEN.get(), FRBlocks.AMETHYST_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.AMETHYST_LUMEN.get(), FRBlocks.ECHO_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.ECHO_LUMEN.get(), FRBlocks.COBALT_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.COBALT_LUMEN.get(), FRBlocks.VERDINITE_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.VERDINITE_LUMEN.get(), FRBlocks.FROSTITE_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.FROSTITE_LUMEN.get(), FRBlocks.VIVULITE_LUMEN.get(), FRONTIERS);
        REDSTONE.addAfter(FRBlocks.VIVULITE_LUMEN.get(), FRBlocks.BRIMTAN_LUMEN.get(), FRONTIERS);

        REDSTONE.addAfter(Items.HOPPER, FRBlocks.ITEM_VACUUM.get(), FRONTIERS);

        REDSTONE.addAfter(Items.ARMOR_STAND, FRBlocks.BLAZE_MODEL.get(), FRONTIERS);

        REDSTONE.addAfter(Items.TNT, FRBlocks.PHANTASMIC_TNT.get(), FRONTIERS);
    }

    // Vanilla tab - Spawn Eggs.
    public static void tabSpawnEggs()
    {
        SPAWN_EGGS.addAfter(Items.TRIAL_SPAWNER, FRBlocks.TOWER_SPAWNER.get(), FRONTIERS);

        SPAWN_EGGS.addAfter(Items.COW_SPAWN_EGG, FRItems.CRAWLER_SPAWN_EGG.get(), FRONTIERS);

        SPAWN_EGGS.addAfter(Items.CREEPER_SPAWN_EGG, FRItems.CROW_SPAWN_EGG.get(), FRONTIERS);

        SPAWN_EGGS.addAfter(Items.IRON_GOLEM_SPAWN_EGG, FRItems.JUNGLE_SPIDER_SPAWN_EGG.get(), FRONTIERS);

        SPAWN_EGGS.addAfter(Items.PUFFERFISH_SPAWN_EGG, FRItems.PUMPKIN_GOLEM_SPAWN_EGG.get(), FRONTIERS);

        SPAWN_EGGS.addAfter(Items.GOAT_SPAWN_EGG, FRItems.GOLDEN_CHICKEN_SPAWN_EGG.get(), FRONTIERS);
    }

    public static void registerModItemTabs()
    {
        // Set Frontiers tab data
        FRONTIERS.setIcon(FRBlocks.COBALT_ORE.get());
        FRONTIERS.setTitle(Component.translatable("itemGroup.frontiers.main"));

        // Register all tabs
        tabTools();
        tabCombat();
        tabIngredients();
        tabNatural();
        tabBuilding();
        tabFunctional();
        tabRedstone();
        tabFood();
        tabSpawnEggs();

        for (VectorItemTab tab : ALL_TABS)
        {
            tab.build();
        }
    }
}
