package net.artyrian.frontiers.reg.content;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;

import java.util.ArrayList;
import java.util.List;

public class FRItemTabs
{
    private static final List<VectorItemTab> ALL_TABS = new ArrayList<>();

    private static final VectorItemTab TOOLS = new VectorItemTab(VectorItemTab.VanillaTab.TOOLS, ALL_TABS);
    private static final VectorItemTab COMBAT = new VectorItemTab(VectorItemTab.VanillaTab.COMBAT, ALL_TABS);
    private static final VectorItemTab INGREDIENTS = new VectorItemTab(VectorItemTab.VanillaTab.INGREDIENTS, ALL_TABS);
    private static final VectorItemTab NATURAL = new VectorItemTab(VectorItemTab.VanillaTab.NATURAL, ALL_TABS);
    private static final VectorItemTab BUILDING_BLOCKS = new VectorItemTab(VectorItemTab.VanillaTab.BUILDING, ALL_TABS);
    private static final VectorItemTab FUNCTIONAL = new VectorItemTab(VectorItemTab.VanillaTab.FUNCTIONAL, ALL_TABS);
    private static final VectorItemTab REDSTONE = new VectorItemTab(VectorItemTab.VanillaTab.REDSTONE, ALL_TABS);
    private static final VectorItemTab FOOD_AND_DRINK = new VectorItemTab(VectorItemTab.VanillaTab.FOOD, ALL_TABS);
    private static final VectorItemTab SPAWN_EGGS = new VectorItemTab(VectorItemTab.VanillaTab.SPAWN_EGGS, ALL_TABS);

    // Vanilla tab - Tools & Utilities.
    public static void tabTools()
    {
        TOOLS.addAfter(Items.GOLDEN_HOE, FRItems.MOURNING_GOLD_SHOVEL.get());
        TOOLS.addAfter(FRItems.MOURNING_GOLD_SHOVEL.get(), FRItems.MOURNING_GOLD_PICKAXE.get());
        TOOLS.addAfter(FRItems.MOURNING_GOLD_PICKAXE.get(), FRItems.MOURNING_GOLD_AXE.get());
        TOOLS.addAfter(FRItems.MOURNING_GOLD_AXE.get(), FRItems.MOURNING_GOLD_HOE.get());

        TOOLS.addAfter(Items.NETHERITE_HOE, FRItems.OBSIDIAN_SHOVEL.get());
        TOOLS.addAfter(FRItems.OBSIDIAN_SHOVEL.get(), FRItems.OBSIDIAN_PICKAXE.get());
        TOOLS.addAfter(FRItems.OBSIDIAN_PICKAXE.get(), FRItems.OBSIDIAN_AXE.get());
        TOOLS.addAfter(FRItems.OBSIDIAN_AXE.get(), FRItems.OBSIDIAN_HOE.get());

        TOOLS.addAfter(FRItems.OBSIDIAN_HOE.get(), FRItems.COBALT_SHOVEL.get());
        TOOLS.addAfter(FRItems.COBALT_SHOVEL.get(), FRItems.COBALT_PICKAXE.get());
        TOOLS.addAfter(FRItems.COBALT_PICKAXE.get(), FRItems.COBALT_AXE.get());
        TOOLS.addAfter(FRItems.COBALT_AXE.get(), FRItems.COBALT_HOE.get());

        TOOLS.addAfter(FRItems.COBALT_HOE.get(), FRItems.VERDINITE_SHOVEL.get());
        TOOLS.addAfter(FRItems.VERDINITE_SHOVEL.get(), FRItems.VERDINITE_PICKAXE.get());
        TOOLS.addAfter(FRItems.VERDINITE_PICKAXE.get(), FRItems.VERDINITE_AXE.get());
        TOOLS.addAfter(FRItems.VERDINITE_AXE.get(), FRItems.VERDINITE_HOE.get());

        TOOLS.addAfter(FRItems.VERDINITE_HOE.get(), FRItems.FROSTITE_SHOVEL.get());
        TOOLS.addAfter(FRItems.FROSTITE_SHOVEL.get(), FRItems.FROSTITE_PICKAXE.get());
        TOOLS.addAfter(FRItems.FROSTITE_PICKAXE.get(), FRItems.FROSTITE_AXE.get());
        TOOLS.addAfter(FRItems.FROSTITE_AXE.get(), FRItems.FROSTITE_HOE.get());

        TOOLS.addAfter(FRItems.FROSTITE_HOE.get(), FRItems.VIVULITE_SHOVEL.get());
        TOOLS.addAfter(FRItems.VIVULITE_SHOVEL.get(), FRItems.VIVULITE_PICKAXE.get());
        TOOLS.addAfter(FRItems.VIVULITE_PICKAXE.get(), FRItems.VIVULITE_AXE.get());
        TOOLS.addAfter(FRItems.VIVULITE_AXE.get(), FRItems.VIVULITE_HOE.get());

        TOOLS.addAfter(FRItems.VIVULITE_HOE.get(), FRItems.BRIMTAN_SHOVEL.get());
        TOOLS.addAfter(FRItems.BRIMTAN_SHOVEL.get(), FRItems.BRIMTAN_PICKAXE.get());
        TOOLS.addAfter(FRItems.BRIMTAN_PICKAXE.get(), FRItems.BRIMTAN_AXE.get());
        TOOLS.addAfter(FRItems.BRIMTAN_AXE.get(), FRItems.BRIMTAN_HOE.get());

        TOOLS.addAfter(Items.FISHING_ROD, FRItems.COBALT_FISHING_ROD.get());

        TOOLS.addAfter(Items.WRITABLE_BOOK, FRItems.MESSAGE_IN_A_BOTTLE.get());

        TOOLS.addAfter(Items.ENDER_PEARL, FRItems.VOID_PEARL.get());

        TOOLS.addAfter(Items.ELYTRA, FRItems.END_CRYSTAL_SHARD.get());

        TOOLS.addAfter(Items.BONE_MEAL, FRItems.SNOW_MELT.get());
        TOOLS.addAfter(FRItems.SNOW_MELT.get(), FRItems.ONYX_MEAL.get());

        TOOLS.addBefore(Items.COMPASS, FRItems.CHEST_KEY.get());

        TOOLS.addAfter(Items.LEAD, FRItems.WITCH_HAT.get());
        TOOLS.addAfter(FRItems.WITCH_HAT.get(), FRBlocks.WHITE_PUMPKIN.get());

        TOOLS.addAfter(InstrumentItem.create(Items.GOAT_HORN,
                BuiltInRegistries.INSTRUMENT.getHolder(Instruments.DREAM_GOAT_HORN).get()), FRItems.BALL.get());
        TOOLS.addAfter(FRItems.BALL.get(), FRItems.BOUNCY_BALL.get());

        TOOLS.addAfter(FRItems.BOUNCY_BALL.get(), FRItems.COLOR_BALLS.get(DyeColor.WHITE).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.WHITE).get(), FRItems.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), FRItems.COLOR_BALLS.get(DyeColor.GRAY).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.GRAY).get(), FRItems.COLOR_BALLS.get(DyeColor.BLACK).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BLACK).get(), FRItems.COLOR_BALLS.get(DyeColor.BROWN).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BROWN).get(), FRItems.COLOR_BALLS.get(DyeColor.RED).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.RED).get(), FRItems.COLOR_BALLS.get(DyeColor.ORANGE).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.ORANGE).get(), FRItems.COLOR_BALLS.get(DyeColor.YELLOW).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.YELLOW).get(), FRItems.COLOR_BALLS.get(DyeColor.LIME).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIME).get(), FRItems.COLOR_BALLS.get(DyeColor.GREEN).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.GREEN).get(), FRItems.COLOR_BALLS.get(DyeColor.CYAN).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.CYAN).get(), FRItems.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), FRItems.COLOR_BALLS.get(DyeColor.BLUE).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.BLUE).get(), FRItems.COLOR_BALLS.get(DyeColor.PURPLE).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.PURPLE).get(), FRItems.COLOR_BALLS.get(DyeColor.MAGENTA).get());
        TOOLS.addAfter(FRItems.COLOR_BALLS.get(DyeColor.MAGENTA).get(), FRItems.COLOR_BALLS.get(DyeColor.PINK).get());

        // Horrible code for integration balls
        Item last = FRItems.COLOR_BALLS.get(DyeColor.PINK).get();
        for (DyeColor color : DyeColor.values())
        {
            if (color.getId() > DyeColor.BLACK.getId())
            {
                TOOLS.addAfter(last, FRItems.COLOR_BALLS.get(color).get());
                last = FRItems.COLOR_BALLS.get(color).get();
            }
        }

        TOOLS.addAfter(Items.MUSIC_DISC_PIGSTEP, FRItems.MUSIC_DISC_DIAPHRAGM.get());
    }

    // Vanilla tab - Combat.
    public static void tabCombat()
    {
        COMBAT.addAfter(Items.GOLDEN_SWORD, FRItems.MOURNING_GOLD_SWORD.get());
        COMBAT.addAfter(Items.NETHERITE_SWORD, FRItems.OBSIDIAN_SWORD.get());
        COMBAT.addAfter(FRItems.OBSIDIAN_SWORD.get(), FRItems.COBALT_SWORD.get());
        COMBAT.addAfter(FRItems.COBALT_SWORD.get(), FRItems.VERDINITE_SWORD.get());
        COMBAT.addAfter(FRItems.VERDINITE_SWORD.get(), FRItems.FROSTITE_SWORD.get());
        COMBAT.addAfter(FRItems.FROSTITE_SWORD.get(), FRItems.VIVULITE_SWORD.get());
        COMBAT.addAfter(FRItems.VIVULITE_SWORD.get(), FRItems.BRIMTAN_SWORD.get());

        COMBAT.addAfter(Items.GOLDEN_AXE, FRItems.MOURNING_GOLD_AXE.get());
        COMBAT.addAfter(Items.NETHERITE_AXE, FRItems.OBSIDIAN_AXE.get());
        COMBAT.addAfter(FRItems.OBSIDIAN_AXE.get(), FRItems.COBALT_AXE.get());
        COMBAT.addAfter(FRItems.COBALT_AXE.get(), FRItems.VERDINITE_AXE.get());
        COMBAT.addAfter(FRItems.VERDINITE_AXE.get(), FRItems.FROSTITE_AXE.get());
        COMBAT.addAfter(FRItems.FROSTITE_AXE.get(), FRItems.VIVULITE_AXE.get());
        COMBAT.addAfter(FRItems.VIVULITE_AXE.get(), FRItems.BRIMTAN_AXE.get());

        COMBAT.addAfter(Items.GOLDEN_BOOTS, FRItems.MOURNING_GOLD_HELMET.get());
        COMBAT.addAfter(FRItems.MOURNING_GOLD_HELMET.get(), FRItems.MOURNING_GOLD_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.MOURNING_GOLD_CHESTPLATE.get(), FRItems.MOURNING_GOLD_LEGGINGS.get());
        COMBAT.addAfter(FRItems.MOURNING_GOLD_LEGGINGS.get(), FRItems.MOURNING_GOLD_BOOTS.get());

        COMBAT.addAfter(Items.DIAMOND_BOOTS, FRItems.NECRO_WEAVE_HELMET.get());
        COMBAT.addAfter(FRItems.NECRO_WEAVE_HELMET.get(), FRItems.NECRO_WEAVE_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.NECRO_WEAVE_CHESTPLATE.get(), FRItems.NECRO_WEAVE_LEGGINGS.get());
        COMBAT.addAfter(FRItems.NECRO_WEAVE_LEGGINGS.get(), FRItems.NECRO_WEAVE_BOOTS.get());

        COMBAT.addAfter(Items.NETHERITE_BOOTS, FRItems.COBALT_HELMET.get());
        COMBAT.addAfter(FRItems.COBALT_HELMET.get(), FRItems.COBALT_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.COBALT_CHESTPLATE.get(), FRItems.COBALT_LEGGINGS.get());
        COMBAT.addAfter(FRItems.COBALT_LEGGINGS.get(), FRItems.COBALT_BOOTS.get());

        COMBAT.addAfter(FRItems.COBALT_BOOTS.get(), FRItems.VERDINITE_HELMET.get());
        COMBAT.addAfter(FRItems.VERDINITE_HELMET.get(), FRItems.VERDINITE_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.VERDINITE_CHESTPLATE.get(), FRItems.VERDINITE_LEGGINGS.get());
        COMBAT.addAfter(FRItems.VERDINITE_LEGGINGS.get(), FRItems.VERDINITE_BOOTS.get());

        COMBAT.addAfter(FRItems.VERDINITE_BOOTS.get(), FRItems.FROSTITE_HELMET.get());
        COMBAT.addAfter(FRItems.FROSTITE_HELMET.get(), FRItems.FROSTITE_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.FROSTITE_CHESTPLATE.get(), FRItems.FROSTITE_LEGGINGS.get());
        COMBAT.addAfter(FRItems.FROSTITE_LEGGINGS.get(), FRItems.FROSTITE_BOOTS.get());

        COMBAT.addAfter(FRItems.FROSTITE_BOOTS.get(), FRItems.VIVULITE_HELMET.get());
        COMBAT.addAfter(FRItems.VIVULITE_HELMET.get(), FRItems.VIVULITE_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.VIVULITE_CHESTPLATE.get(), FRItems.VIVULITE_LEGGINGS.get());
        COMBAT.addAfter(FRItems.VIVULITE_LEGGINGS.get(), FRItems.VIVULITE_BOOTS.get());

        COMBAT.addAfter(FRItems.VIVULITE_BOOTS.get(), FRItems.BRIMTAN_HELMET.get());
        COMBAT.addAfter(FRItems.BRIMTAN_HELMET.get(), FRItems.BRIMTAN_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.BRIMTAN_CHESTPLATE.get(), FRItems.BRIMTAN_LEGGINGS.get());
        COMBAT.addAfter(FRItems.BRIMTAN_LEGGINGS.get(), FRItems.BRIMTAN_BOOTS.get());

        COMBAT.addAfter(Items.TURTLE_HELMET, FRItems.SLIME_SHOES.get());
        COMBAT.addAfter(FRItems.SLIME_SHOES.get(), FRItems.PLATE_HELMET.get());
        COMBAT.addAfter(FRItems.PLATE_HELMET.get(), FRItems.PLATE_CHESTPLATE.get());
        COMBAT.addAfter(FRItems.PLATE_CHESTPLATE.get(), FRItems.PLATE_LEGGINGS.get());
        COMBAT.addAfter(FRItems.PLATE_LEGGINGS.get(), FRItems.PLATE_BOOTS.get());

        COMBAT.addAfter(Items.ARROW, FRItems.SUBZERO_ARROW.get());
        COMBAT.addAfter(FRItems.SUBZERO_ARROW.get(), FRItems.BOUNCY_ARROW.get());
        COMBAT.addAfter(FRItems.BOUNCY_ARROW.get(), FRItems.WARP_ARROW.get());
        COMBAT.addAfter(FRItems.WARP_ARROW.get(), FRItems.DYNAMITE_ARROW.get());
        COMBAT.addAfter(FRItems.DYNAMITE_ARROW.get(), FRItems.PRISMARINE_ARROW.get());

        COMBAT.addAfter(Items.CROSSBOW, FRItems.COPPER_BOW.get());
        COMBAT.addAfter(FRItems.COPPER_BOW.get(), FRItems.IRON_BOW.get());
        COMBAT.addAfter(FRItems.IRON_BOW.get(), FRItems.DIAMOND_BOW.get());
        COMBAT.addAfter(FRItems.DIAMOND_BOW.get(), FRItems.NETHERITE_BOW.get());
        COMBAT.addAfter(FRItems.NETHERITE_BOW.get(), FRItems.ECHO_BOW.get());
        COMBAT.addAfter(FRItems.ECHO_BOW.get(), FRItems.VERDINITE_BOW.get());
        COMBAT.addAfter(FRItems.VERDINITE_BOW.get(), FRItems.TOME_OF_FANGS.get());
        COMBAT.addAfter(FRItems.TOME_OF_FANGS.get(), FRItems.THUNDERVAST_TOME.get());

        COMBAT.addAfter(Items.TOTEM_OF_UNDYING, FRItems.TOTEM_OF_AVARICE.get());

        COMBAT.addAfter(Items.SHIELD, FRItems.COBALT_SHIELD.get());

        COMBAT.addAfter(Items.TNT, FRBlocks.PHANTASMIC_TNT.get());

        COMBAT.addAfter(Items.TRIDENT, FRItems.PALE_TRIDENT.get());

        COMBAT.addAfter(Items.DIAMOND_HORSE_ARMOR, FRItems.COBALT_HORSE_ARMOR.get());
        COMBAT.addAfter(FRItems.COBALT_HORSE_ARMOR.get(), FRItems.VERDINITE_HORSE_ARMOR.get());
        COMBAT.addAfter(FRItems.VERDINITE_HORSE_ARMOR.get(), FRItems.VIVULITE_HORSE_ARMOR.get());
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients()
    {
        INGREDIENTS.addAfter(Items.NETHERITE_INGOT, FRItems.OBSIDIAN_CASING.get());
        INGREDIENTS.addAfter(FRItems.OBSIDIAN_CASING.get(), FRItems.MOURNING_GOLD_INGOT.get());
        INGREDIENTS.addAfter(FRItems.MOURNING_GOLD_INGOT.get(), FRItems.COBALT_INGOT.get());
        INGREDIENTS.addAfter(FRItems.COBALT_INGOT.get(), FRItems.VERDINITE_INGOT.get());
        INGREDIENTS.addAfter(FRItems.VERDINITE_INGOT.get(), FRItems.FROSTITE_INGOT.get());
        INGREDIENTS.addAfter(FRItems.FROSTITE_INGOT.get(), FRItems.VIVULITE_INGOT.get());
        INGREDIENTS.addAfter(FRItems.VIVULITE_INGOT.get(), FRItems.BRIMTAN_INGOT.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_INGOT.get(), FRItems.PITCH_INGOT.get());

        INGREDIENTS.addAfter(Items.RAW_GOLD, FRItems.RAW_COBALT.get());
        INGREDIENTS.addAfter(FRItems.RAW_COBALT.get(), FRItems.RAW_VERDINITE.get());
        INGREDIENTS.addAfter(FRItems.RAW_VERDINITE.get(), FRItems.RAW_FROSTITE.get());
        INGREDIENTS.addAfter(FRItems.RAW_FROSTITE.get(), FRItems.RAW_VIVULITE.get());
        INGREDIENTS.addAfter(FRItems.RAW_VIVULITE.get(), FRItems.BRIMTAN_CLUSTER.get());

        INGREDIENTS.addAfter(Items.GOLD_NUGGET, FRItems.BRIMTAN_NUGGET.get());

        INGREDIENTS.addAfter(Items.AMETHYST_SHARD, FRItems.HARDENED_SLIME.get());

        INGREDIENTS.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, FRItems.TABLET_FRAGMENT.get());
        INGREDIENTS.addAfter(FRItems.TABLET_FRAGMENT.get(), FRItems.CURSED_TABLET.get());
        INGREDIENTS.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get());

        INGREDIENTS.addAfter(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get());

        INGREDIENTS.addAfter(Items.HEAVY_CORE, FRItems.SPAWNER_CHUNK.get());

        INGREDIENTS.addAfter(Items.EGG, FRItems.GOLDEN_EGG.get());

        INGREDIENTS.addAfter(Items.NETHER_STAR, FRItems.WITHERED_ESSENCE.get());
        INGREDIENTS.addAfter(FRItems.WITHERED_ESSENCE.get(), FRItems.HEART_OF_THE_WARDEN.get());
        INGREDIENTS.addAfter(FRItems.HEART_OF_THE_WARDEN.get(), FRItems.SOUL.get());

        INGREDIENTS.addAfter(Items.SHULKER_SHELL, FRItems.SHULKER_RESIDUE.get());
        INGREDIENTS.addAfter(Items.NETHER_BRICK, FRItems.NACRE_BRICK.get());

        INGREDIENTS.addAfter(Items.ENDER_EYE, FRItems.END_CRYSTAL_SHARD.get());

        INGREDIENTS.addAfter(Items.NETHER_WART, FRItems.WARPED_WART.get());

        INGREDIENTS.addAfter(Items.DIAMOND, FRItems.VOID_DIAMOND.get());
        INGREDIENTS.addAfter(Items.EMERALD, FRItems.BLACK_EMERALD.get());

        INGREDIENTS.addAfter(Items.PRISMARINE_SHARD, FRItems.PALE_PRISMARINE_SHARD.get());
        INGREDIENTS.addAfter(FRItems.PALE_PRISMARINE_SHARD.get(), FRItems.ELDER_GUARDIAN_SPINE.get());

        INGREDIENTS.addAfter(Items.FLINT, FRItems.SPECTRAL_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(FRItems.SPECTRAL_ARROW_ARROWHEAD.get(), FRItems.SUBZERO_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(FRItems.SUBZERO_ARROW_ARROWHEAD.get(), FRItems.BOUNCY_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(FRItems.BOUNCY_ARROW_ARROWHEAD.get(), FRItems.WARP_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(FRItems.WARP_ARROW_ARROWHEAD.get(), FRItems.DYNAMITE_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(FRItems.DYNAMITE_ARROW_ARROWHEAD.get(), FRItems.PRISMARINE_ARROW_ARROWHEAD.get());

        INGREDIENTS.addAfter(Items.EXPERIENCE_BOTTLE, FRItems.MANA_BOTTLE.get());
        INGREDIENTS.addAfter(FRItems.MANA_BOTTLE.get(), FRItems.BRIMTAN_SHELL_SWORD.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_SWORD.get(), FRItems.BRIMTAN_SHELL_SHOVEL.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_SHOVEL.get(), FRItems.BRIMTAN_SHELL_PICKAXE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_PICKAXE.get(), FRItems.BRIMTAN_SHELL_AXE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_AXE.get(), FRItems.BRIMTAN_SHELL_HOE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_HOE.get(), FRItems.BRIMTAN_SHELL_HELMET.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_HELMET.get(), FRItems.BRIMTAN_SHELL_CHESTPLATE.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), FRItems.BRIMTAN_SHELL_LEGGINGS.get());
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_LEGGINGS.get(), FRItems.BRIMTAN_SHELL_BOOTS.get());

        INGREDIENTS.addAfter(Items.BONE, FRItems.FROST_BONE.get());
        INGREDIENTS.addAfter(FRItems.FROST_BONE.get(), FRItems.ONYX_BONE.get());
        INGREDIENTS.addAfter(FRItems.ONYX_BONE.get(), FRItems.NECRO_WEAVE.get());
        INGREDIENTS.addAfter(Items.BONE_MEAL, FRItems.SNOW_MELT.get());
        INGREDIENTS.addAfter(FRItems.SNOW_MELT.get(), FRItems.ONYX_MEAL.get());

        INGREDIENTS.addAfter(Items.ENDER_PEARL, FRItems.VOID_PEARL.get());

        INGREDIENTS.addAfter(Items.GHAST_TEAR, FRItems.ECTOPLASM.get());
        INGREDIENTS.addBefore(Items.PRISMARINE_SHARD, FRItems.INVOKE_SHARD.get());
        INGREDIENTS.addAfter(FRItems.INVOKE_SHARD.get(), FRItems.INCENSE.get());
        INGREDIENTS.addAfter(FRItems.INCENSE.get(), FRItems.RAVAGER_TOOTH.get());

        INGREDIENTS.addBefore(Items.DRAGON_BREATH, FRItems.LIGHTNING_IN_A_BOTTLE.get());

        INGREDIENTS.addAfter(Items.OMINOUS_TRIAL_KEY, FRItems.TOWER_KEY.get());
        INGREDIENTS.addAfter(FRItems.TOWER_KEY.get(), FRItems.TOWER_KEY_FRAGMENT.get());
        INGREDIENTS.addAfter(FRItems.TOWER_KEY_FRAGMENT.get(), FRItems.UNFINISHED_CORE.get());
        INGREDIENTS.addAfter(FRItems.UNFINISHED_CORE.get(), FRItems.REACTIVE_CORE.get());
        INGREDIENTS.addAfter(FRItems.REACTIVE_CORE.get(), FRItems.DEPTHS_CORE_PLATE.get());
        INGREDIENTS.addAfter(FRItems.DEPTHS_CORE_PLATE.get(), FRItems.FRONTAL_CORE_PLATE.get());
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural()
    {
        NATURAL.addAfter(Blocks.DEEPSLATE_EMERALD_ORE, FRBlocks.BLACK_EMERALD_ORE.get());
        NATURAL.addAfter(FRBlocks.BLACK_EMERALD_ORE.get(), FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get());

        NATURAL.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, FRBlocks.COBALT_ORE.get());
        NATURAL.addAfter(FRBlocks.COBALT_ORE.get(), FRBlocks.DEEPSLATE_COBALT_ORE.get());
        NATURAL.addAfter(FRBlocks.DEEPSLATE_COBALT_ORE.get(), FRBlocks.VERDINITE_ORE.get());
        NATURAL.addAfter(FRBlocks.VERDINITE_ORE.get(), FRBlocks.DEEPSLATE_VERDINITE_ORE.get());
        NATURAL.addAfter(FRBlocks.DEEPSLATE_VERDINITE_ORE.get(), FRBlocks.VIVULITE_ORE.get());
        NATURAL.addAfter(FRBlocks.VIVULITE_ORE.get(), FRBlocks.DEEPSLATE_VIVULITE_ORE.get());
        NATURAL.addAfter(FRBlocks.DEEPSLATE_VIVULITE_ORE.get(), FRBlocks.FROSTITE_ORE.get());

        NATURAL.addAfter(Blocks.RAW_GOLD_BLOCK, FRBlocks.RAW_COBALT_BLOCK.get());
        NATURAL.addAfter(FRBlocks.RAW_COBALT_BLOCK.get(), FRBlocks.RAW_VERDINITE_BLOCK.get());
        NATURAL.addAfter(FRBlocks.RAW_VERDINITE_BLOCK.get(), FRBlocks.RAW_VIVULITE_BLOCK.get());
        NATURAL.addAfter(FRBlocks.RAW_VIVULITE_BLOCK.get(), FRBlocks.RAW_FROSTITE_BLOCK.get());

        NATURAL.addAfter(Blocks.OBSIDIAN, FRBlocks.GLOWING_OBSIDIAN.get());

        NATURAL.addAfter(Blocks.TUFF, FRBlocks.HIELOSTONE.get());

        NATURAL.addAfter(Blocks.SMALL_DRIPLEAF, FRBlocks.SLIME_BULB.get());
        NATURAL.addAfter(FRBlocks.SLIME_BULB.get(), FRBlocks.SLIME_TRAIL.get());

        NATURAL.addAfter(Blocks.POPPY, FRBlocks.ROSE.get());
        NATURAL.addAfter(FRBlocks.ROSE.get(), FRBlocks.ANCIENT_ROSE.get());
        NATURAL.addAfter(FRBlocks.ANCIENT_ROSE.get(), FRBlocks.VIOLET_ROSE.get());
        NATURAL.addAfter(Blocks.ROSE_BUSH, FRBlocks.ANCIENT_ROSE_BUSH.get());
        NATURAL.addAfter(FRBlocks.ANCIENT_ROSE_BUSH.get(), FRBlocks.VIOLET_ROSE_BUSH.get());

        NATURAL.addAfter(Blocks.BONE_BLOCK, FRBlocks.ONYX_BONE_BLOCK.get());

        NATURAL.addAfter(Blocks.LILY_OF_THE_VALLEY, FRBlocks.SNOW_DAHLIA.get());
        NATURAL.addAfter(FRBlocks.SNOW_DAHLIA.get(), FRBlocks.FUNGAL_DAFFODIL.get());
        NATURAL.addAfter(FRBlocks.FUNGAL_DAFFODIL.get(), FRBlocks.CRIMCONE.get());

        NATURAL.addAfter(Blocks.WITHER_ROSE, FRBlocks.EXPERIWINKLE.get());

        NATURAL.addAfter(Blocks.CHERRY_SAPLING, FRBlocks.BLIGHTED_BIRCH_SAPLING.get());
        NATURAL.addAfter(Blocks.CHERRY_LEAVES, FRBlocks.BLIGHTED_BIRCH_LEAVES.get());
        NATURAL.addAfter(Blocks.CHERRY_LOG, FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        NATURAL.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());

        NATURAL.addAfter(Blocks.RED_MUSHROOM_BLOCK, FRBlocks.FUNGAL_DAFFODIL_BLOCK.get());

        NATURAL.addAfter(Items.PITCHER_POD, FRItems.ANCIENT_ROSE_SEED.get());
        NATURAL.addAfter(Items.NETHER_WART, FRItems.WARPED_WART.get());
        NATURAL.addAfter(FRItems.WARPED_WART.get(), FRItems.EXPERIWINKLE_BULB.get());

        NATURAL.addAfter(Blocks.AMETHYST_CLUSTER, FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get());

        NATURAL.addBefore(Items.OAK_SAPLING, FRBlocks.OAK_WREATH.get());
        NATURAL.addAfter(FRBlocks.OAK_WREATH.get(), FRBlocks.SPRUCE_WREATH.get());
        NATURAL.addAfter(FRBlocks.SPRUCE_WREATH.get(), FRBlocks.BIRCH_WREATH.get());
        NATURAL.addAfter(FRBlocks.BIRCH_WREATH.get(), FRBlocks.JUNGLE_WREATH.get());
        NATURAL.addAfter(FRBlocks.JUNGLE_WREATH.get(), FRBlocks.ACACIA_WREATH.get());
        NATURAL.addAfter(FRBlocks.ACACIA_WREATH.get(), FRBlocks.DARK_OAK_WREATH.get());
        NATURAL.addAfter(FRBlocks.DARK_OAK_WREATH.get(), FRBlocks.MANGROVE_WREATH.get());
        NATURAL.addAfter(FRBlocks.MANGROVE_WREATH.get(), FRBlocks.CHERRY_WREATH.get());
        NATURAL.addAfter(FRBlocks.CHERRY_WREATH.get(), FRBlocks.BLIGHTED_BIRCH_WREATH.get());
        NATURAL.addAfter(FRBlocks.BLIGHTED_BIRCH_WREATH.get(), FRBlocks.AZALEA_WREATH.get());

        NATURAL.addAfter(Blocks.SAND, FRBlocks.QUICKSAND.get());
        NATURAL.addAfter(Blocks.RED_SAND, FRBlocks.RED_QUICKSAND.get());

        NATURAL.addAfter(Blocks.WARPED_STEM, FRBlocks.EBONCORK.get());

        NATURAL.addBefore(Blocks.END_STONE, FRBlocks.CRAGULSTANE.get());

        NATURAL.addAfter(Blocks.HAY_BLOCK, FRBlocks.SUGAR_CANE_BLOCK.get());
        NATURAL.addAfter(FRBlocks.SUGAR_CANE_BLOCK.get(), FRBlocks.COCOA_BEAN_BLOCK.get());
        NATURAL.addAfter(FRBlocks.COCOA_BEAN_BLOCK.get(), FRBlocks.EGG_PALLET.get());
        NATURAL.addAfter(FRBlocks.EGG_PALLET.get(), FRBlocks.GOLDEN_EGG_PALLET.get());
        NATURAL.addAfter(FRBlocks.GOLDEN_EGG_PALLET.get(), FRBlocks.ROTTEN_FLESH_BLOCK.get());

        NATURAL.addAfter(Blocks.ANCIENT_DEBRIS, FRBlocks.BRIMTAN_ORE.get());

        NATURAL.addAfter(Blocks.MELON, FRBlocks.CARVED_MELON.get());
        NATURAL.addAfter(FRBlocks.CARVED_MELON.get(), FRBlocks.JUNE_O_LANTERN.get());
        NATURAL.addAfter(FRBlocks.JUNE_O_LANTERN.get(), FRBlocks.GLISTERING_MELON.get());
        NATURAL.addAfter(FRBlocks.GLISTERING_MELON.get(), FRBlocks.CARVED_GLISTERING_MELON.get());
        NATURAL.addAfter(FRBlocks.CARVED_GLISTERING_MELON.get(), FRBlocks.GLISTERING_JUNE_O_LANTERN.get());

        NATURAL.addAfter(Blocks.JACK_O_LANTERN, FRBlocks.WHITE_PUMPKIN.get());
        NATURAL.addAfter(FRBlocks.WHITE_PUMPKIN.get(), FRBlocks.WHITE_JACK_O_LANTERN.get());

        NATURAL.addAfter(Blocks.BEDROCK, FRBlocks.AESTHENOSTONE.get());
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood()
    {
        FOOD_AND_DRINK.addAfter(Items.BREAD, FRBlocks.BEEF_WELLINGTON.get());

        FOOD_AND_DRINK.addAfter(Items.CAKE, FRBlocks.FRUITCAKE.get());
        FOOD_AND_DRINK.addAfter(FRBlocks.FRUITCAKE.get(), FRItems.FRUITCAKE_SLICE.get());

        FOOD_AND_DRINK.addAfter(Items.PUMPKIN_PIE, FRItems.LEVI_ROLL.get());
        FOOD_AND_DRINK.addAfter(FRItems.LEVI_ROLL.get(), FRItems.MARSHMALLOW.get());
        FOOD_AND_DRINK.addAfter(FRItems.MARSHMALLOW.get(), FRItems.ROASTED_MARSHMALLOW.get());

        FOOD_AND_DRINK.addAfter(Items.DRIED_KELP, FRItems.TRUFFLE.get());
        FOOD_AND_DRINK.addAfter(FRItems.TRUFFLE.get(), FRItems.TRUFFLE_OIL.get());
        FOOD_AND_DRINK.addAfter(FRItems.TRUFFLE_OIL.get(), FRItems.TRUFFLE_POTATO_PUFF.get());

        FOOD_AND_DRINK.addAfter(Items.PUFFERFISH, FRItems.GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(FRItems.GUARDIAN_SLICE.get(), FRItems.COOKED_GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(FRItems.COOKED_GUARDIAN_SLICE.get(), FRItems.ELDER_GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(FRItems.ELDER_GUARDIAN_SLICE.get(), FRItems.COOKED_ELDER_GUARDIAN_SLICE.get());

        FOOD_AND_DRINK.addAfter(Items.ENCHANTED_GOLDEN_APPLE, FRItems.APPLE_OF_ENLIGHTENMENT.get());

        FOOD_AND_DRINK.addAfter(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE);

        FOOD_AND_DRINK.addAfter(Items.SWEET_BERRIES, FRItems.POMEGRANATE.get());
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding()
    {
        BUILDING_BLOCKS.addAfter(Blocks.EMERALD_BLOCK, FRBlocks.BLACK_EMERALD_BLOCK.get());
        BUILDING_BLOCKS.addAfter(Blocks.DIAMOND_BLOCK, FRBlocks.MOURNING_GOLD_BLOCK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.MOURNING_GOLD_BLOCK.get(), FRBlocks.NECRO_WEAVE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(Blocks.NETHERITE_BLOCK, FRBlocks.COBALT_BLOCK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.COBALT_BLOCK.get(), FRBlocks.COBALT_GRILLES.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.COBALT_GRILLES.get(), FRBlocks.VERDINITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.VERDINITE_BLOCK.get(), FRBlocks.FROSTITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.FROSTITE_BLOCK.get(), FRBlocks.VIVULITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.VIVULITE_BLOCK.get(), FRBlocks.BRIMTAN_BLOCK.get());

        BUILDING_BLOCKS.addBefore(Blocks.PRISMARINE, FRBlocks.SEA_GLASS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.SEA_GLASS.get(), FRBlocks.SEA_GLASS_PANE.get());

        BUILDING_BLOCKS.addAfter(Blocks.DARK_PRISMARINE_SLAB, FRBlocks.PALE_SEA_GLASS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_SEA_GLASS.get(), FRBlocks.PALE_SEA_GLASS_PANE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_SEA_GLASS_PANE.get(), FRBlocks.PALE_PRISMARINE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE.get(), FRBlocks.PALE_PRISMARINE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_STAIRS.get(), FRBlocks.PALE_PRISMARINE_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_SLAB.get(), FRBlocks.PALE_PRISMARINE_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_WALL.get(), FRBlocks.PALE_PRISMARINE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICKS.get(), FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), FRBlocks.DEEP_PALE_PRISMARINE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE.get(), FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get());

        BUILDING_BLOCKS.addAfter(Blocks.NETHER_BRICK_FENCE, FRBlocks.NETHER_BRICK_FENCE_GATE.get());

        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICKS, FRBlocks.CRACKED_RED_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICK_WALL, FRBlocks.RED_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.RED_NETHER_BRICK_FENCE.get(), FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_RED_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_RED_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get(), FRBlocks.BLUE_NETHER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), FRBlocks.BLUE_NETHER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_WALL.get(), FRBlocks.BLUE_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_FENCE.get(), FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), FRBlocks.PURPLE_NETHER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), FRBlocks.PURPLE_NETHER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_WALL.get(), FRBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get(), FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(Blocks.PURPUR_SLAB, FRBlocks.NACRE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICKS.get(), FRBlocks.NACRE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICK_STAIRS.get(), FRBlocks.NACRE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.NACRE_BRICK_SLAB.get(), FRBlocks.NACRE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), FRBlocks.TOWER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICKS.get(), FRBlocks.TOWER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_STAIRS.get(), FRBlocks.TOWER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_SLAB.get(), FRBlocks.TOWER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TOWER_BRICK_WALL.get(), FRBlocks.MOSSY_TOWER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), FRBlocks.MOSSY_TOWER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), FRBlocks.MOSSY_TOWER_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(Blocks.CHERRY_BUTTON, FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.BLIGHTED_BIRCH_PLANKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), FRBlocks.BLIGHTED_BIRCH_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), FRBlocks.BLIGHTED_BIRCH_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_SLAB.get(), FRBlocks.BLIGHTED_BIRCH_FENCE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_FENCE.get(), FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), FRItems.BLIGHTED_BIRCH_DOOR.get());
        BUILDING_BLOCKS.addAfter(FRItems.BLIGHTED_BIRCH_DOOR.get(), FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), FRBlocks.BLIGHTED_BIRCH_BUTTON.get());

        BUILDING_BLOCKS.addAfter(Blocks.WARPED_BUTTON, FRBlocks.EBONCORK.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK.get(), FRBlocks.EBONCORK_PLANKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_PLANKS.get(), FRBlocks.EBONCORK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_STAIRS.get(), FRBlocks.EBONCORK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_SLAB.get(), FRBlocks.EBONCORK_FENCE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_FENCE.get(), FRBlocks.EBONCORK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_FENCE_GATE.get(), FRItems.EBONCORK_DOOR.get());
        BUILDING_BLOCKS.addAfter(FRItems.EBONCORK_DOOR.get(), FRBlocks.EBONCORK_TRAPDOOR.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_TRAPDOOR.get(), FRBlocks.EBONCORK_PRESSURE_PLATE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.EBONCORK_PRESSURE_PLATE.get(), FRBlocks.EBONCORK_BUTTON.get());

        BUILDING_BLOCKS.addBefore(Blocks.END_STONE, FRBlocks.CRAGULSTANE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE.get(), FRBlocks.CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(Blocks.CUT_RED_SANDSTONE_SLAB, FRBlocks.CRUSTED_QUICKSAND.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTED_QUICKSAND.get(), FRBlocks.CRUSTY_SAND_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICKS.get(), FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get(), FRBlocks.CRUSTY_SAND_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), FRBlocks.CRUSTY_SAND_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_SAND_BRICK_WALL.get(), FRBlocks.CRUSTED_RED_QUICKSAND.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTED_RED_QUICKSAND.get(), FRBlocks.CRUSTY_RED_SAND_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get());

        BUILDING_BLOCKS.addBefore(Blocks.SEA_LANTERN, FRBlocks.TURTLE_SCUTE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), FRBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(Blocks.CHISELED_TUFF_BRICKS, FRBlocks.HIELOSTONE.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_STAIRS.get(), FRBlocks.HIELOSTONE_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_SLAB.get(), FRBlocks.HIELOSTONE_WALL.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_WALL.get(), FRBlocks.HIELOSTONE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_STAIRS.get(), FRBlocks.HIELOSTONE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_SLAB.get(), FRBlocks.HIELOSTONE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_BRICK_WALL.get(), FRBlocks.HIELOSTONE_TILES.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILES.get(), FRBlocks.HIELOSTONE_TILE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_STAIRS.get(), FRBlocks.HIELOSTONE_TILE_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_SLAB.get(), FRBlocks.HIELOSTONE_TILE_WALL.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_TILE_WALL.get(), FRBlocks.HIELOSTONE_PLATES.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATES.get(), FRBlocks.HIELOSTONE_PLATE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_STAIRS.get(), FRBlocks.HIELOSTONE_PLATE_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_SLAB.get(), FRBlocks.HIELOSTONE_PLATE_WALL.get());

        BUILDING_BLOCKS.addAfter(FRBlocks.HIELOSTONE_PLATE_WALL.get(), FRBlocks.COBBLEFROST.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST.get(), FRBlocks.COBBLEFROST_STAIRS.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST_STAIRS.get(), FRBlocks.COBBLEFROST_SLAB.get());
        BUILDING_BLOCKS.addAfter(FRBlocks.COBBLEFROST_SLAB.get(), FRBlocks.COBBLEFROST_WALL.get());
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional()
    {
        FUNCTIONAL.addAfter(Blocks.ENCHANTING_TABLE, FRBlocks.CURSE_ALTAR.get());

        FUNCTIONAL.addAfter(Items.END_CRYSTAL, FRItems.PURIFIED_END_CRYSTAL.get());
        FUNCTIONAL.addAfter(Blocks.BEACON, FRBlocks.ENCHANTING_MAGNET.get());
        FUNCTIONAL.addAfter(FRBlocks.ENCHANTING_MAGNET.get(), FRBlocks.STRANGE_CORE.get());

        FUNCTIONAL.addAfter(Items.REDSTONE_LAMP, FRBlocks.DIAMOND_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.DIAMOND_LUMEN.get(), FRBlocks.REDSTONE_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.REDSTONE_LUMEN.get(), FRBlocks.QUARTZ_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.QUARTZ_LUMEN.get(), FRBlocks.EMERALD_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.EMERALD_LUMEN.get(), FRBlocks.AMETHYST_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.AMETHYST_LUMEN.get(), FRBlocks.ECHO_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.ECHO_LUMEN.get(), FRBlocks.COBALT_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.COBALT_LUMEN.get(), FRBlocks.VERDINITE_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.VERDINITE_LUMEN.get(), FRBlocks.FROSTITE_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.FROSTITE_LUMEN.get(), FRBlocks.VIVULITE_LUMEN.get());
        FUNCTIONAL.addAfter(FRBlocks.VIVULITE_LUMEN.get(), FRBlocks.BRIMTAN_LUMEN.get());

        FUNCTIONAL.addBefore(Items.SKELETON_SKULL, FRBlocks.CREEPER_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.CREEPER_MODEL.get(), FRBlocks.SLIME_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.SLIME_MODEL.get(), FRBlocks.SKELETON_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.SKELETON_MODEL.get(), FRBlocks.STRAY_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.STRAY_MODEL.get(), FRBlocks.BOGGED_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.BOGGED_MODEL.get(), FRBlocks.WITHER_SKELETON_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.WITHER_SKELETON_MODEL.get(), FRBlocks.BLAZE_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.BLAZE_MODEL.get(), FRBlocks.MAGMA_CUBE_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.MAGMA_CUBE_MODEL.get(), FRBlocks.ENDERMAN_MODEL.get());
        FUNCTIONAL.addAfter(FRBlocks.ENDERMAN_MODEL.get(), FRBlocks.PHANTOM_MODEL.get());

        FUNCTIONAL.addAfter(Items.DECORATED_POT, FRBlocks.ITEM_VACUUM.get());

        FUNCTIONAL.addAfter(Items.SCAFFOLDING, FRBlocks.NECRO_RUG.get());

        FUNCTIONAL.addAfter(Blocks.DAMAGED_ANVIL, FRBlocks.VIVULITE_ANVIL.get());

        FUNCTIONAL.addAfter(Blocks.SOUL_CAMPFIRE, FRBlocks.MONSTER_BAKERY.get());

        FUNCTIONAL.addAfter(Blocks.CHEST, FRBlocks.PERSONAL_CHEST.get());

        FUNCTIONAL.addAfter(Blocks.PINK_CANDLE, FRItems.SPIRIT_CANDLE.get());

        FUNCTIONAL.addAfter(Blocks.PINK_BED, FRItems.PHANTOM_STITCH_BED.get());

        FUNCTIONAL.addAfter(Blocks.VAULT, FRBlocks.TOWER_WATCHER.get());
        FUNCTIONAL.addAfter(FRBlocks.TOWER_WATCHER.get(), FRBlocks.TOWER_HEART.get());
        FUNCTIONAL.addAfter(FRBlocks.TOWER_HEART.get(), FRBlocks.TOWER_TREASURE_VAULT.get());
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone()
    {
        REDSTONE.addAfter(Items.LIGHTNING_ROD, FRBlocks.DIAMOND_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.DIAMOND_LUMEN.get(), FRBlocks.REDSTONE_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.REDSTONE_LUMEN.get(), FRBlocks.QUARTZ_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.QUARTZ_LUMEN.get(), FRBlocks.EMERALD_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.EMERALD_LUMEN.get(), FRBlocks.AMETHYST_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.AMETHYST_LUMEN.get(), FRBlocks.ECHO_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.ECHO_LUMEN.get(), FRBlocks.COBALT_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.COBALT_LUMEN.get(), FRBlocks.VERDINITE_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.VERDINITE_LUMEN.get(), FRBlocks.FROSTITE_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.FROSTITE_LUMEN.get(), FRBlocks.VIVULITE_LUMEN.get());
        REDSTONE.addAfter(FRBlocks.VIVULITE_LUMEN.get(), FRBlocks.BRIMTAN_LUMEN.get());

        REDSTONE.addAfter(Items.HOPPER, FRBlocks.ITEM_VACUUM.get());

        REDSTONE.addAfter(Items.ARMOR_STAND, FRBlocks.BLAZE_MODEL.get());

        REDSTONE.addAfter(Items.TNT, FRBlocks.PHANTASMIC_TNT.get());
    }

    // Vanilla tab - Spawn Eggs.
    public static void tabSpawnEggs()
    {
        SPAWN_EGGS.addAfter(Items.TRIAL_SPAWNER, FRBlocks.TOWER_SPAWNER.get());

        SPAWN_EGGS.addAfter(Items.COW_SPAWN_EGG, FRItems.CRAWLER_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.CREEPER_SPAWN_EGG, FRItems.CROW_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.IRON_GOLEM_SPAWN_EGG, FRItems.JUNGLE_SPIDER_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.PUFFERFISH_SPAWN_EGG, FRItems.PUMPKIN_GOLEM_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.GOAT_SPAWN_EGG, FRItems.GOLDEN_CHICKEN_SPAWN_EGG.get());
    }

    public static void registerModItemTabs()
    {
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
