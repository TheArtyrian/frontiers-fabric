package net.artyrian.frontiers.reg.content;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;

import java.util.ArrayList;
import java.util.List;

public class ModItemTabs
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
        TOOLS.addAfter(Items.GOLDEN_HOE, ModItem.MOURNING_GOLD_SHOVEL.get());
        TOOLS.addAfter(ModItem.MOURNING_GOLD_SHOVEL.get(), ModItem.MOURNING_GOLD_PICKAXE.get());
        TOOLS.addAfter(ModItem.MOURNING_GOLD_PICKAXE.get(), ModItem.MOURNING_GOLD_AXE.get());
        TOOLS.addAfter(ModItem.MOURNING_GOLD_AXE.get(), ModItem.MOURNING_GOLD_HOE.get());

        TOOLS.addAfter(Items.NETHERITE_HOE, ModItem.OBSIDIAN_SHOVEL.get());
        TOOLS.addAfter(ModItem.OBSIDIAN_SHOVEL.get(), ModItem.OBSIDIAN_PICKAXE.get());
        TOOLS.addAfter(ModItem.OBSIDIAN_PICKAXE.get(), ModItem.OBSIDIAN_AXE.get());
        TOOLS.addAfter(ModItem.OBSIDIAN_AXE.get(), ModItem.OBSIDIAN_HOE.get());

        TOOLS.addAfter(ModItem.OBSIDIAN_HOE.get(), ModItem.COBALT_SHOVEL.get());
        TOOLS.addAfter(ModItem.COBALT_SHOVEL.get(), ModItem.COBALT_PICKAXE.get());
        TOOLS.addAfter(ModItem.COBALT_PICKAXE.get(), ModItem.COBALT_AXE.get());
        TOOLS.addAfter(ModItem.COBALT_AXE.get(), ModItem.COBALT_HOE.get());

        TOOLS.addAfter(ModItem.COBALT_HOE.get(), ModItem.VERDINITE_SHOVEL.get());
        TOOLS.addAfter(ModItem.VERDINITE_SHOVEL.get(), ModItem.VERDINITE_PICKAXE.get());
        TOOLS.addAfter(ModItem.VERDINITE_PICKAXE.get(), ModItem.VERDINITE_AXE.get());
        TOOLS.addAfter(ModItem.VERDINITE_AXE.get(), ModItem.VERDINITE_HOE.get());

        TOOLS.addAfter(ModItem.VERDINITE_HOE.get(), ModItem.FROSTITE_SHOVEL.get());
        TOOLS.addAfter(ModItem.FROSTITE_SHOVEL.get(), ModItem.FROSTITE_PICKAXE.get());
        TOOLS.addAfter(ModItem.FROSTITE_PICKAXE.get(), ModItem.FROSTITE_AXE.get());
        TOOLS.addAfter(ModItem.FROSTITE_AXE.get(), ModItem.FROSTITE_HOE.get());

        TOOLS.addAfter(ModItem.FROSTITE_HOE.get(), ModItem.VIVULITE_SHOVEL.get());
        TOOLS.addAfter(ModItem.VIVULITE_SHOVEL.get(), ModItem.VIVULITE_PICKAXE.get());
        TOOLS.addAfter(ModItem.VIVULITE_PICKAXE.get(), ModItem.VIVULITE_AXE.get());
        TOOLS.addAfter(ModItem.VIVULITE_AXE.get(), ModItem.VIVULITE_HOE.get());

        TOOLS.addAfter(ModItem.VIVULITE_HOE.get(), ModItem.BRIMTAN_SHOVEL.get());
        TOOLS.addAfter(ModItem.BRIMTAN_SHOVEL.get(), ModItem.BRIMTAN_PICKAXE.get());
        TOOLS.addAfter(ModItem.BRIMTAN_PICKAXE.get(), ModItem.BRIMTAN_AXE.get());
        TOOLS.addAfter(ModItem.BRIMTAN_AXE.get(), ModItem.BRIMTAN_HOE.get());

        TOOLS.addAfter(Items.FISHING_ROD, ModItem.COBALT_FISHING_ROD.get());

        TOOLS.addAfter(Items.WRITABLE_BOOK, ModItem.MESSAGE_IN_A_BOTTLE.get());

        TOOLS.addAfter(Items.ENDER_PEARL, ModItem.VOID_PEARL.get());

        TOOLS.addAfter(Items.ELYTRA, ModItem.END_CRYSTAL_SHARD.get());

        TOOLS.addAfter(Items.BONE_MEAL, ModItem.SNOW_MELT.get());
        TOOLS.addAfter(ModItem.SNOW_MELT.get(), ModItem.ONYX_MEAL.get());

        TOOLS.addBefore(Items.COMPASS, ModItem.CHEST_KEY.get());

        TOOLS.addAfter(Items.LEAD, ModItem.WITCH_HAT.get());
        TOOLS.addAfter(ModItem.WITCH_HAT.get(), ModBlocks.WHITE_PUMPKIN.get());

        TOOLS.addAfter(InstrumentItem.create(Items.GOAT_HORN,
                BuiltInRegistries.INSTRUMENT.getHolder(Instruments.DREAM_GOAT_HORN).get()), ModItem.BALL.get());
        TOOLS.addAfter(ModItem.BALL.get(), ModItem.BOUNCY_BALL.get());

        TOOLS.addAfter(ModItem.BOUNCY_BALL.get(), ModItem.COLOR_BALLS.get(DyeColor.WHITE).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.WHITE).get(), ModItem.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), ModItem.COLOR_BALLS.get(DyeColor.GRAY).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.GRAY).get(), ModItem.COLOR_BALLS.get(DyeColor.BLACK).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.BLACK).get(), ModItem.COLOR_BALLS.get(DyeColor.BROWN).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.BROWN).get(), ModItem.COLOR_BALLS.get(DyeColor.RED).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.RED).get(), ModItem.COLOR_BALLS.get(DyeColor.ORANGE).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.ORANGE).get(), ModItem.COLOR_BALLS.get(DyeColor.YELLOW).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.YELLOW).get(), ModItem.COLOR_BALLS.get(DyeColor.LIME).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.LIME).get(), ModItem.COLOR_BALLS.get(DyeColor.GREEN).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.GREEN).get(), ModItem.COLOR_BALLS.get(DyeColor.CYAN).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.CYAN).get(), ModItem.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), ModItem.COLOR_BALLS.get(DyeColor.BLUE).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.BLUE).get(), ModItem.COLOR_BALLS.get(DyeColor.PURPLE).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.PURPLE).get(), ModItem.COLOR_BALLS.get(DyeColor.MAGENTA).get());
        TOOLS.addAfter(ModItem.COLOR_BALLS.get(DyeColor.MAGENTA).get(), ModItem.COLOR_BALLS.get(DyeColor.PINK).get());

        // Horrible code for integration balls
        Item last = ModItem.COLOR_BALLS.get(DyeColor.PINK).get();
        for (DyeColor color : DyeColor.values())
        {
            if (color.getId() > DyeColor.BLACK.getId())
            {
                TOOLS.addAfter(last, ModItem.COLOR_BALLS.get(color).get());
                last = ModItem.COLOR_BALLS.get(color).get();
            }
        }

        TOOLS.addAfter(Items.MUSIC_DISC_PIGSTEP, ModItem.MUSIC_DISC_DIAPHRAGM.get());
    }

    // Vanilla tab - Combat.
    public static void tabCombat()
    {
        COMBAT.addAfter(Items.GOLDEN_SWORD, ModItem.MOURNING_GOLD_SWORD.get());
        COMBAT.addAfter(Items.NETHERITE_SWORD, ModItem.OBSIDIAN_SWORD.get());
        COMBAT.addAfter(ModItem.OBSIDIAN_SWORD.get(), ModItem.COBALT_SWORD.get());
        COMBAT.addAfter(ModItem.COBALT_SWORD.get(), ModItem.VERDINITE_SWORD.get());
        COMBAT.addAfter(ModItem.VERDINITE_SWORD.get(), ModItem.FROSTITE_SWORD.get());
        COMBAT.addAfter(ModItem.FROSTITE_SWORD.get(), ModItem.VIVULITE_SWORD.get());
        COMBAT.addAfter(ModItem.VIVULITE_SWORD.get(), ModItem.BRIMTAN_SWORD.get());

        COMBAT.addAfter(Items.GOLDEN_AXE, ModItem.MOURNING_GOLD_AXE.get());
        COMBAT.addAfter(Items.NETHERITE_AXE, ModItem.OBSIDIAN_AXE.get());
        COMBAT.addAfter(ModItem.OBSIDIAN_AXE.get(), ModItem.COBALT_AXE.get());
        COMBAT.addAfter(ModItem.COBALT_AXE.get(), ModItem.VERDINITE_AXE.get());
        COMBAT.addAfter(ModItem.VERDINITE_AXE.get(), ModItem.FROSTITE_AXE.get());
        COMBAT.addAfter(ModItem.FROSTITE_AXE.get(), ModItem.VIVULITE_AXE.get());
        COMBAT.addAfter(ModItem.VIVULITE_AXE.get(), ModItem.BRIMTAN_AXE.get());

        COMBAT.addAfter(Items.GOLDEN_BOOTS, ModItem.MOURNING_GOLD_HELMET.get());
        COMBAT.addAfter(ModItem.MOURNING_GOLD_HELMET.get(), ModItem.MOURNING_GOLD_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.MOURNING_GOLD_CHESTPLATE.get(), ModItem.MOURNING_GOLD_LEGGINGS.get());
        COMBAT.addAfter(ModItem.MOURNING_GOLD_LEGGINGS.get(), ModItem.MOURNING_GOLD_BOOTS.get());

        COMBAT.addAfter(Items.DIAMOND_BOOTS, ModItem.NECRO_WEAVE_HELMET.get());
        COMBAT.addAfter(ModItem.NECRO_WEAVE_HELMET.get(), ModItem.NECRO_WEAVE_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.NECRO_WEAVE_CHESTPLATE.get(), ModItem.NECRO_WEAVE_LEGGINGS.get());
        COMBAT.addAfter(ModItem.NECRO_WEAVE_LEGGINGS.get(), ModItem.NECRO_WEAVE_BOOTS.get());

        COMBAT.addAfter(Items.NETHERITE_BOOTS, ModItem.COBALT_HELMET.get());
        COMBAT.addAfter(ModItem.COBALT_HELMET.get(), ModItem.COBALT_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.COBALT_CHESTPLATE.get(), ModItem.COBALT_LEGGINGS.get());
        COMBAT.addAfter(ModItem.COBALT_LEGGINGS.get(), ModItem.COBALT_BOOTS.get());

        COMBAT.addAfter(ModItem.COBALT_BOOTS.get(), ModItem.VERDINITE_HELMET.get());
        COMBAT.addAfter(ModItem.VERDINITE_HELMET.get(), ModItem.VERDINITE_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.VERDINITE_CHESTPLATE.get(), ModItem.VERDINITE_LEGGINGS.get());
        COMBAT.addAfter(ModItem.VERDINITE_LEGGINGS.get(), ModItem.VERDINITE_BOOTS.get());

        COMBAT.addAfter(ModItem.VERDINITE_BOOTS.get(), ModItem.FROSTITE_HELMET.get());
        COMBAT.addAfter(ModItem.FROSTITE_HELMET.get(), ModItem.FROSTITE_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.FROSTITE_CHESTPLATE.get(), ModItem.FROSTITE_LEGGINGS.get());
        COMBAT.addAfter(ModItem.FROSTITE_LEGGINGS.get(), ModItem.FROSTITE_BOOTS.get());

        COMBAT.addAfter(ModItem.FROSTITE_BOOTS.get(), ModItem.VIVULITE_HELMET.get());
        COMBAT.addAfter(ModItem.VIVULITE_HELMET.get(), ModItem.VIVULITE_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.VIVULITE_CHESTPLATE.get(), ModItem.VIVULITE_LEGGINGS.get());
        COMBAT.addAfter(ModItem.VIVULITE_LEGGINGS.get(), ModItem.VIVULITE_BOOTS.get());

        COMBAT.addAfter(ModItem.VIVULITE_BOOTS.get(), ModItem.BRIMTAN_HELMET.get());
        COMBAT.addAfter(ModItem.BRIMTAN_HELMET.get(), ModItem.BRIMTAN_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.BRIMTAN_CHESTPLATE.get(), ModItem.BRIMTAN_LEGGINGS.get());
        COMBAT.addAfter(ModItem.BRIMTAN_LEGGINGS.get(), ModItem.BRIMTAN_BOOTS.get());

        COMBAT.addAfter(Items.TURTLE_HELMET, ModItem.SLIME_SHOES.get());
        COMBAT.addAfter(ModItem.SLIME_SHOES.get(), ModItem.PLATE_HELMET.get());
        COMBAT.addAfter(ModItem.PLATE_HELMET.get(), ModItem.PLATE_CHESTPLATE.get());
        COMBAT.addAfter(ModItem.PLATE_CHESTPLATE.get(), ModItem.PLATE_LEGGINGS.get());
        COMBAT.addAfter(ModItem.PLATE_LEGGINGS.get(), ModItem.PLATE_BOOTS.get());

        COMBAT.addAfter(Items.ARROW, ModItem.SUBZERO_ARROW.get());
        COMBAT.addAfter(ModItem.SUBZERO_ARROW.get(), ModItem.BOUNCY_ARROW.get());
        COMBAT.addAfter(ModItem.BOUNCY_ARROW.get(), ModItem.WARP_ARROW.get());
        COMBAT.addAfter(ModItem.WARP_ARROW.get(), ModItem.DYNAMITE_ARROW.get());
        COMBAT.addAfter(ModItem.DYNAMITE_ARROW.get(), ModItem.PRISMARINE_ARROW.get());

        COMBAT.addAfter(Items.CROSSBOW, ModItem.COPPER_BOW.get());
        COMBAT.addAfter(ModItem.COPPER_BOW.get(), ModItem.IRON_BOW.get());
        COMBAT.addAfter(ModItem.IRON_BOW.get(), ModItem.DIAMOND_BOW.get());
        COMBAT.addAfter(ModItem.DIAMOND_BOW.get(), ModItem.NETHERITE_BOW.get());
        COMBAT.addAfter(ModItem.NETHERITE_BOW.get(), ModItem.ECHO_BOW.get());
        COMBAT.addAfter(ModItem.ECHO_BOW.get(), ModItem.VERDINITE_BOW.get());
        COMBAT.addAfter(ModItem.VERDINITE_BOW.get(), ModItem.TOME_OF_FANGS.get());
        COMBAT.addAfter(ModItem.TOME_OF_FANGS.get(), ModItem.THUNDERVAST_TOME.get());

        COMBAT.addAfter(Items.TOTEM_OF_UNDYING, ModItem.TOTEM_OF_AVARICE.get());

        COMBAT.addAfter(Items.SHIELD, ModItem.COBALT_SHIELD.get());

        COMBAT.addAfter(Items.TNT, ModBlocks.PHANTASMIC_TNT.get());

        COMBAT.addAfter(Items.TRIDENT, ModItem.PALE_TRIDENT.get());

        COMBAT.addAfter(Items.DIAMOND_HORSE_ARMOR, ModItem.COBALT_HORSE_ARMOR.get());
        COMBAT.addAfter(ModItem.COBALT_HORSE_ARMOR.get(), ModItem.VERDINITE_HORSE_ARMOR.get());
        COMBAT.addAfter(ModItem.VERDINITE_HORSE_ARMOR.get(), ModItem.VIVULITE_HORSE_ARMOR.get());
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients()
    {
        INGREDIENTS.addAfter(Items.NETHERITE_INGOT, ModItem.OBSIDIAN_CASING.get());
        INGREDIENTS.addAfter(ModItem.OBSIDIAN_CASING.get(), ModItem.MOURNING_GOLD_INGOT.get());
        INGREDIENTS.addAfter(ModItem.MOURNING_GOLD_INGOT.get(), ModItem.COBALT_INGOT.get());
        INGREDIENTS.addAfter(ModItem.COBALT_INGOT.get(), ModItem.VERDINITE_INGOT.get());
        INGREDIENTS.addAfter(ModItem.VERDINITE_INGOT.get(), ModItem.FROSTITE_INGOT.get());
        INGREDIENTS.addAfter(ModItem.FROSTITE_INGOT.get(), ModItem.VIVULITE_INGOT.get());
        INGREDIENTS.addAfter(ModItem.VIVULITE_INGOT.get(), ModItem.BRIMTAN_INGOT.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_INGOT.get(), ModItem.PITCH_INGOT.get());

        INGREDIENTS.addAfter(Items.RAW_GOLD, ModItem.RAW_COBALT.get());
        INGREDIENTS.addAfter(ModItem.RAW_COBALT.get(), ModItem.RAW_VERDINITE.get());
        INGREDIENTS.addAfter(ModItem.RAW_VERDINITE.get(), ModItem.RAW_FROSTITE.get());
        INGREDIENTS.addAfter(ModItem.RAW_FROSTITE.get(), ModItem.RAW_VIVULITE.get());
        INGREDIENTS.addAfter(ModItem.RAW_VIVULITE.get(), ModItem.BRIMTAN_CLUSTER.get());

        INGREDIENTS.addAfter(Items.GOLD_NUGGET, ModItem.BRIMTAN_NUGGET.get());

        INGREDIENTS.addAfter(Items.AMETHYST_SHARD, ModItem.HARDENED_SLIME.get());

        INGREDIENTS.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.TABLET_FRAGMENT.get());
        INGREDIENTS.addAfter(ModItem.TABLET_FRAGMENT.get(), ModItem.CURSED_TABLET.get());
        INGREDIENTS.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get());

        INGREDIENTS.addAfter(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        INGREDIENTS.addAfter(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get());

        INGREDIENTS.addAfter(Items.HEAVY_CORE, ModItem.SPAWNER_CHUNK.get());

        INGREDIENTS.addAfter(Items.EGG, ModItem.GOLDEN_EGG.get());

        INGREDIENTS.addAfter(Items.NETHER_STAR, ModItem.WITHERED_ESSENCE.get());
        INGREDIENTS.addAfter(ModItem.WITHERED_ESSENCE.get(), ModItem.HEART_OF_THE_WARDEN.get());
        INGREDIENTS.addAfter(ModItem.HEART_OF_THE_WARDEN.get(), ModItem.SOUL.get());

        INGREDIENTS.addAfter(Items.SHULKER_SHELL, ModItem.SHULKER_RESIDUE.get());
        INGREDIENTS.addAfter(Items.NETHER_BRICK, ModItem.NACRE_BRICK.get());

        INGREDIENTS.addAfter(Items.ENDER_EYE, ModItem.END_CRYSTAL_SHARD.get());

        INGREDIENTS.addAfter(Items.NETHER_WART, ModItem.WARPED_WART.get());

        INGREDIENTS.addAfter(Items.DIAMOND, ModItem.VOID_DIAMOND.get());
        INGREDIENTS.addAfter(Items.EMERALD, ModItem.BLACK_EMERALD.get());

        INGREDIENTS.addAfter(Items.PRISMARINE_SHARD, ModItem.PALE_PRISMARINE_SHARD.get());
        INGREDIENTS.addAfter(ModItem.PALE_PRISMARINE_SHARD.get(), ModItem.ELDER_GUARDIAN_SPINE.get());

        INGREDIENTS.addAfter(Items.FLINT, ModItem.SPECTRAL_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), ModItem.SUBZERO_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(ModItem.SUBZERO_ARROW_ARROWHEAD.get(), ModItem.BOUNCY_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(ModItem.BOUNCY_ARROW_ARROWHEAD.get(), ModItem.WARP_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(ModItem.WARP_ARROW_ARROWHEAD.get(), ModItem.DYNAMITE_ARROW_ARROWHEAD.get());
        INGREDIENTS.addAfter(ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), ModItem.PRISMARINE_ARROW_ARROWHEAD.get());

        INGREDIENTS.addAfter(Items.EXPERIENCE_BOTTLE, ModItem.MANA_BOTTLE.get());
        INGREDIENTS.addAfter(ModItem.MANA_BOTTLE.get(), ModItem.BRIMTAN_SHELL_SWORD.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_SWORD.get(), ModItem.BRIMTAN_SHELL_SHOVEL.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_SHOVEL.get(), ModItem.BRIMTAN_SHELL_PICKAXE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_PICKAXE.get(), ModItem.BRIMTAN_SHELL_AXE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_AXE.get(), ModItem.BRIMTAN_SHELL_HOE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_HOE.get(), ModItem.BRIMTAN_SHELL_HELMET.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_HELMET.get(), ModItem.BRIMTAN_SHELL_CHESTPLATE.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_CHESTPLATE.get(), ModItem.BRIMTAN_SHELL_LEGGINGS.get());
        INGREDIENTS.addAfter(ModItem.BRIMTAN_SHELL_LEGGINGS.get(), ModItem.BRIMTAN_SHELL_BOOTS.get());

        INGREDIENTS.addAfter(Items.BONE, ModItem.FROST_BONE.get());
        INGREDIENTS.addAfter(ModItem.FROST_BONE.get(), ModItem.ONYX_BONE.get());
        INGREDIENTS.addAfter(ModItem.ONYX_BONE.get(), ModItem.NECRO_WEAVE.get());
        INGREDIENTS.addAfter(Items.BONE_MEAL, ModItem.SNOW_MELT.get());
        INGREDIENTS.addAfter(ModItem.SNOW_MELT.get(), ModItem.ONYX_MEAL.get());

        INGREDIENTS.addAfter(Items.ENDER_PEARL, ModItem.VOID_PEARL.get());

        INGREDIENTS.addAfter(Items.GHAST_TEAR, ModItem.ECTOPLASM.get());
        INGREDIENTS.addBefore(Items.PRISMARINE_SHARD, ModItem.INVOKE_SHARD.get());
        INGREDIENTS.addAfter(ModItem.INVOKE_SHARD.get(), ModItem.INCENSE.get());
        INGREDIENTS.addAfter(ModItem.INCENSE.get(), ModItem.RAVAGER_TOOTH.get());

        INGREDIENTS.addBefore(Items.DRAGON_BREATH, ModItem.LIGHTNING_IN_A_BOTTLE.get());

        INGREDIENTS.addAfter(Items.OMINOUS_TRIAL_KEY, ModItem.TOWER_KEY.get());
        INGREDIENTS.addAfter(ModItem.TOWER_KEY.get(), ModItem.TOWER_KEY_FRAGMENT.get());
        INGREDIENTS.addAfter(ModItem.TOWER_KEY_FRAGMENT.get(), ModItem.UNFINISHED_CORE.get());
        INGREDIENTS.addAfter(ModItem.UNFINISHED_CORE.get(), ModItem.REACTIVE_CORE.get());
        INGREDIENTS.addAfter(ModItem.REACTIVE_CORE.get(), ModItem.DEPTHS_CORE_PLATE.get());
        INGREDIENTS.addAfter(ModItem.DEPTHS_CORE_PLATE.get(), ModItem.FRONTAL_CORE_PLATE.get());
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural()
    {
        NATURAL.addAfter(Blocks.DEEPSLATE_EMERALD_ORE, ModBlocks.BLACK_EMERALD_ORE.get());
        NATURAL.addAfter(ModBlocks.BLACK_EMERALD_ORE.get(), ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get());

        NATURAL.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, ModBlocks.COBALT_ORE.get());
        NATURAL.addAfter(ModBlocks.COBALT_ORE.get(), ModBlocks.DEEPSLATE_COBALT_ORE.get());
        NATURAL.addAfter(ModBlocks.DEEPSLATE_COBALT_ORE.get(), ModBlocks.VERDINITE_ORE.get());
        NATURAL.addAfter(ModBlocks.VERDINITE_ORE.get(), ModBlocks.DEEPSLATE_VERDINITE_ORE.get());
        NATURAL.addAfter(ModBlocks.DEEPSLATE_VERDINITE_ORE.get(), ModBlocks.VIVULITE_ORE.get());
        NATURAL.addAfter(ModBlocks.VIVULITE_ORE.get(), ModBlocks.DEEPSLATE_VIVULITE_ORE.get());
        NATURAL.addAfter(ModBlocks.DEEPSLATE_VIVULITE_ORE.get(), ModBlocks.FROSTITE_ORE.get());

        NATURAL.addAfter(Blocks.RAW_GOLD_BLOCK, ModBlocks.RAW_COBALT_BLOCK.get());
        NATURAL.addAfter(ModBlocks.RAW_COBALT_BLOCK.get(), ModBlocks.RAW_VERDINITE_BLOCK.get());
        NATURAL.addAfter(ModBlocks.RAW_VERDINITE_BLOCK.get(), ModBlocks.RAW_VIVULITE_BLOCK.get());
        NATURAL.addAfter(ModBlocks.RAW_VIVULITE_BLOCK.get(), ModBlocks.RAW_FROSTITE_BLOCK.get());

        NATURAL.addAfter(Blocks.OBSIDIAN, ModBlocks.GLOWING_OBSIDIAN.get());

        NATURAL.addAfter(Blocks.TUFF, ModBlocks.HIELOSTONE.get());

        NATURAL.addAfter(Blocks.SMALL_DRIPLEAF, ModBlocks.SLIME_BULB.get());
        NATURAL.addAfter(ModBlocks.SLIME_BULB.get(), ModBlocks.SLIME_TRAIL.get());

        NATURAL.addAfter(Blocks.POPPY, ModBlocks.ROSE.get());
        NATURAL.addAfter(ModBlocks.ROSE.get(), ModBlocks.ANCIENT_ROSE.get());
        NATURAL.addAfter(ModBlocks.ANCIENT_ROSE.get(), ModBlocks.VIOLET_ROSE.get());
        NATURAL.addAfter(Blocks.ROSE_BUSH, ModBlocks.ANCIENT_ROSE_BUSH.get());
        NATURAL.addAfter(ModBlocks.ANCIENT_ROSE_BUSH.get(), ModBlocks.VIOLET_ROSE_BUSH.get());

        NATURAL.addAfter(Blocks.BONE_BLOCK, ModBlocks.ONYX_BONE_BLOCK.get());

        NATURAL.addAfter(Blocks.LILY_OF_THE_VALLEY, ModBlocks.SNOW_DAHLIA.get());
        NATURAL.addAfter(ModBlocks.SNOW_DAHLIA.get(), ModBlocks.FUNGAL_DAFFODIL.get());
        NATURAL.addAfter(ModBlocks.FUNGAL_DAFFODIL.get(), ModBlocks.CRIMCONE.get());

        NATURAL.addAfter(Blocks.WITHER_ROSE, ModBlocks.EXPERIWINKLE.get());

        NATURAL.addAfter(Blocks.CHERRY_SAPLING, ModBlocks.BLIGHTED_BIRCH_SAPLING.get());
        NATURAL.addAfter(Blocks.CHERRY_LEAVES, ModBlocks.BLIGHTED_BIRCH_LEAVES.get());
        NATURAL.addAfter(Blocks.CHERRY_LOG, ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        NATURAL.addAfter(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());

        NATURAL.addAfter(Blocks.RED_MUSHROOM_BLOCK, ModBlocks.FUNGAL_DAFFODIL_BLOCK.get());

        NATURAL.addAfter(Items.PITCHER_POD, ModItem.ANCIENT_ROSE_SEED.get());
        NATURAL.addAfter(Items.NETHER_WART, ModItem.WARPED_WART.get());
        NATURAL.addAfter(ModItem.WARPED_WART.get(), ModItem.EXPERIWINKLE_BULB.get());

        NATURAL.addAfter(Blocks.AMETHYST_CLUSTER, ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());
        NATURAL.addAfter(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get());

        NATURAL.addBefore(Items.OAK_SAPLING, ModBlocks.OAK_WREATH.get());
        NATURAL.addAfter(ModBlocks.OAK_WREATH.get(), ModBlocks.SPRUCE_WREATH.get());
        NATURAL.addAfter(ModBlocks.SPRUCE_WREATH.get(), ModBlocks.BIRCH_WREATH.get());
        NATURAL.addAfter(ModBlocks.BIRCH_WREATH.get(), ModBlocks.JUNGLE_WREATH.get());
        NATURAL.addAfter(ModBlocks.JUNGLE_WREATH.get(), ModBlocks.ACACIA_WREATH.get());
        NATURAL.addAfter(ModBlocks.ACACIA_WREATH.get(), ModBlocks.DARK_OAK_WREATH.get());
        NATURAL.addAfter(ModBlocks.DARK_OAK_WREATH.get(), ModBlocks.MANGROVE_WREATH.get());
        NATURAL.addAfter(ModBlocks.MANGROVE_WREATH.get(), ModBlocks.CHERRY_WREATH.get());
        NATURAL.addAfter(ModBlocks.CHERRY_WREATH.get(), ModBlocks.BLIGHTED_BIRCH_WREATH.get());
        NATURAL.addAfter(ModBlocks.BLIGHTED_BIRCH_WREATH.get(), ModBlocks.AZALEA_WREATH.get());

        NATURAL.addAfter(Blocks.SAND, ModBlocks.QUICKSAND.get());
        NATURAL.addAfter(Blocks.RED_SAND, ModBlocks.RED_QUICKSAND.get());

        NATURAL.addAfter(Blocks.WARPED_STEM, ModBlocks.EBONCORK.get());

        NATURAL.addBefore(Blocks.END_STONE, ModBlocks.CRAGULSTANE.get());

        NATURAL.addAfter(Blocks.HAY_BLOCK, ModBlocks.SUGAR_CANE_BLOCK.get());
        NATURAL.addAfter(ModBlocks.SUGAR_CANE_BLOCK.get(), ModBlocks.COCOA_BEAN_BLOCK.get());
        NATURAL.addAfter(ModBlocks.COCOA_BEAN_BLOCK.get(), ModBlocks.EGG_PALLET.get());
        NATURAL.addAfter(ModBlocks.EGG_PALLET.get(), ModBlocks.GOLDEN_EGG_PALLET.get());
        NATURAL.addAfter(ModBlocks.GOLDEN_EGG_PALLET.get(), ModBlocks.ROTTEN_FLESH_BLOCK.get());

        NATURAL.addAfter(Blocks.ANCIENT_DEBRIS, ModBlocks.BRIMTAN_ORE.get());

        NATURAL.addAfter(Blocks.MELON, ModBlocks.CARVED_MELON.get());
        NATURAL.addAfter(ModBlocks.CARVED_MELON.get(), ModBlocks.JUNE_O_LANTERN.get());
        NATURAL.addAfter(ModBlocks.JUNE_O_LANTERN.get(), ModBlocks.GLISTERING_MELON.get());
        NATURAL.addAfter(ModBlocks.GLISTERING_MELON.get(), ModBlocks.CARVED_GLISTERING_MELON.get());
        NATURAL.addAfter(ModBlocks.CARVED_GLISTERING_MELON.get(), ModBlocks.GLISTERING_JUNE_O_LANTERN.get());

        NATURAL.addAfter(Blocks.JACK_O_LANTERN, ModBlocks.WHITE_PUMPKIN.get());
        NATURAL.addAfter(ModBlocks.WHITE_PUMPKIN.get(), ModBlocks.WHITE_JACK_O_LANTERN.get());

        NATURAL.addAfter(Blocks.BEDROCK, ModBlocks.AESTHENOSTONE.get());
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood()
    {
        FOOD_AND_DRINK.addAfter(Items.BREAD, ModBlocks.BEEF_WELLINGTON.get());

        FOOD_AND_DRINK.addAfter(Items.CAKE, ModBlocks.FRUITCAKE.get());
        FOOD_AND_DRINK.addAfter(ModBlocks.FRUITCAKE.get(), ModItem.FRUITCAKE_SLICE.get());

        FOOD_AND_DRINK.addAfter(Items.PUMPKIN_PIE, ModItem.LEVI_ROLL.get());
        FOOD_AND_DRINK.addAfter(ModItem.LEVI_ROLL.get(), ModItem.MARSHMALLOW.get());
        FOOD_AND_DRINK.addAfter(ModItem.MARSHMALLOW.get(), ModItem.ROASTED_MARSHMALLOW.get());

        FOOD_AND_DRINK.addAfter(Items.DRIED_KELP, ModItem.TRUFFLE.get());
        FOOD_AND_DRINK.addAfter(ModItem.TRUFFLE.get(), ModItem.TRUFFLE_OIL.get());
        FOOD_AND_DRINK.addAfter(ModItem.TRUFFLE_OIL.get(), ModItem.TRUFFLE_POTATO_PUFF.get());

        FOOD_AND_DRINK.addAfter(Items.PUFFERFISH, ModItem.GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(ModItem.GUARDIAN_SLICE.get(), ModItem.COOKED_GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(ModItem.COOKED_GUARDIAN_SLICE.get(), ModItem.ELDER_GUARDIAN_SLICE.get());
        FOOD_AND_DRINK.addAfter(ModItem.ELDER_GUARDIAN_SLICE.get(), ModItem.COOKED_ELDER_GUARDIAN_SLICE.get());

        FOOD_AND_DRINK.addAfter(Items.ENCHANTED_GOLDEN_APPLE, ModItem.APPLE_OF_ENLIGHTENMENT.get());

        FOOD_AND_DRINK.addAfter(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE);

        FOOD_AND_DRINK.addAfter(Items.SWEET_BERRIES, ModItem.POMEGRANATE.get());
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding()
    {
        BUILDING_BLOCKS.addAfter(Blocks.EMERALD_BLOCK, ModBlocks.BLACK_EMERALD_BLOCK.get());
        BUILDING_BLOCKS.addAfter(Blocks.DIAMOND_BLOCK, ModBlocks.MOURNING_GOLD_BLOCK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.MOURNING_GOLD_BLOCK.get(), ModBlocks.NECRO_WEAVE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.COBALT_BLOCK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.COBALT_BLOCK.get(), ModBlocks.COBALT_GRILLES.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.COBALT_GRILLES.get(), ModBlocks.VERDINITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.VERDINITE_BLOCK.get(), ModBlocks.FROSTITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.FROSTITE_BLOCK.get(), ModBlocks.VIVULITE_BLOCK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.VIVULITE_BLOCK.get(), ModBlocks.BRIMTAN_BLOCK.get());

        BUILDING_BLOCKS.addBefore(Blocks.PRISMARINE, ModBlocks.SEA_GLASS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.SEA_GLASS.get(), ModBlocks.SEA_GLASS_PANE.get());

        BUILDING_BLOCKS.addAfter(Blocks.DARK_PRISMARINE_SLAB, ModBlocks.PALE_SEA_GLASS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_SEA_GLASS.get(), ModBlocks.PALE_SEA_GLASS_PANE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_SEA_GLASS_PANE.get(), ModBlocks.PALE_PRISMARINE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE.get(), ModBlocks.PALE_PRISMARINE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_STAIRS.get(), ModBlocks.PALE_PRISMARINE_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_SLAB.get(), ModBlocks.PALE_PRISMARINE_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_WALL.get(), ModBlocks.PALE_PRISMARINE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_BRICKS.get(), ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), ModBlocks.DEEP_PALE_PRISMARINE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.DEEP_PALE_PRISMARINE.get(), ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get());

        BUILDING_BLOCKS.addAfter(Blocks.NETHER_BRICK_FENCE, ModBlocks.NETHER_BRICK_FENCE_GATE.get());

        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(Blocks.RED_NETHER_BRICK_WALL, ModBlocks.RED_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.RED_NETHER_BRICK_FENCE.get(), ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), ModBlocks.CHISELED_RED_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_RED_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(), ModBlocks.BLUE_NETHER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), ModBlocks.BLUE_NETHER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICK_WALL.get(), ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE.get(), ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), ModBlocks.PURPLE_NETHER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get(), ModBlocks.PURPLE_NETHER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICK_WALL.get(), ModBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get(), ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());

        BUILDING_BLOCKS.addAfter(Blocks.PURPUR_SLAB, ModBlocks.NACRE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.NACRE_BRICKS.get(), ModBlocks.NACRE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.NACRE_BRICK_STAIRS.get(), ModBlocks.NACRE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.NACRE_BRICK_SLAB.get(), ModBlocks.NACRE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), ModBlocks.TOWER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TOWER_BRICKS.get(), ModBlocks.TOWER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TOWER_BRICK_STAIRS.get(), ModBlocks.TOWER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TOWER_BRICK_SLAB.get(), ModBlocks.TOWER_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TOWER_BRICK_WALL.get(), ModBlocks.MOSSY_TOWER_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.MOSSY_TOWER_BRICKS.get(), ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), ModBlocks.MOSSY_TOWER_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get(), ModBlocks.MOSSY_TOWER_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(Blocks.CHERRY_BUTTON, ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.BLIGHTED_BIRCH_PLANKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_PLANKS.get(), ModBlocks.BLIGHTED_BIRCH_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_STAIRS.get(), ModBlocks.BLIGHTED_BIRCH_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_SLAB.get(), ModBlocks.BLIGHTED_BIRCH_FENCE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_FENCE.get(), ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), ModItem.BLIGHTED_BIRCH_DOOR.get());
        BUILDING_BLOCKS.addAfter(ModItem.BLIGHTED_BIRCH_DOOR.get(), ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), ModBlocks.BLIGHTED_BIRCH_BUTTON.get());

        BUILDING_BLOCKS.addAfter(Blocks.WARPED_BUTTON, ModBlocks.EBONCORK.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK.get(), ModBlocks.EBONCORK_PLANKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_PLANKS.get(), ModBlocks.EBONCORK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_STAIRS.get(), ModBlocks.EBONCORK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_SLAB.get(), ModBlocks.EBONCORK_FENCE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_FENCE.get(), ModBlocks.EBONCORK_FENCE_GATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_FENCE_GATE.get(), ModItem.EBONCORK_DOOR.get());
        BUILDING_BLOCKS.addAfter(ModItem.EBONCORK_DOOR.get(), ModBlocks.EBONCORK_TRAPDOOR.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_TRAPDOOR.get(), ModBlocks.EBONCORK_PRESSURE_PLATE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.EBONCORK_PRESSURE_PLATE.get(), ModBlocks.EBONCORK_BUTTON.get());

        BUILDING_BLOCKS.addBefore(Blocks.END_STONE, ModBlocks.CRAGULSTANE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRAGULSTANE.get(), ModBlocks.CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRAGULSTANE_BRICKS.get(), ModBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get(), ModBlocks.CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRAGULSTANE_BRICK_SLAB.get(), ModBlocks.CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CRAGULSTANE_BRICK_WALL.get(), ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());

        BUILDING_BLOCKS.addBefore(Blocks.SEA_LANTERN, ModBlocks.TURTLE_SCUTE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TURTLE_SCUTE_BRICKS.get(), ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), ModBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(Blocks.CHISELED_TUFF_BRICKS, ModBlocks.HIELOSTONE.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE.get(), ModBlocks.HIELOSTONE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_STAIRS.get(), ModBlocks.HIELOSTONE_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_SLAB.get(), ModBlocks.HIELOSTONE_WALL.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_WALL.get(), ModBlocks.HIELOSTONE_BRICKS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_BRICKS.get(), ModBlocks.HIELOSTONE_BRICK_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_BRICK_STAIRS.get(), ModBlocks.HIELOSTONE_BRICK_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_BRICK_SLAB.get(), ModBlocks.HIELOSTONE_BRICK_WALL.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_BRICK_WALL.get(), ModBlocks.HIELOSTONE_TILES.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_TILES.get(), ModBlocks.HIELOSTONE_TILE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_TILE_STAIRS.get(), ModBlocks.HIELOSTONE_TILE_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_TILE_SLAB.get(), ModBlocks.HIELOSTONE_TILE_WALL.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_TILE_WALL.get(), ModBlocks.HIELOSTONE_PLATES.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_PLATES.get(), ModBlocks.HIELOSTONE_PLATE_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_PLATE_STAIRS.get(), ModBlocks.HIELOSTONE_PLATE_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_PLATE_SLAB.get(), ModBlocks.HIELOSTONE_PLATE_WALL.get());

        BUILDING_BLOCKS.addAfter(ModBlocks.HIELOSTONE_PLATE_WALL.get(), ModBlocks.COBBLEFROST.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.COBBLEFROST.get(), ModBlocks.COBBLEFROST_STAIRS.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.COBBLEFROST_STAIRS.get(), ModBlocks.COBBLEFROST_SLAB.get());
        BUILDING_BLOCKS.addAfter(ModBlocks.COBBLEFROST_SLAB.get(), ModBlocks.COBBLEFROST_WALL.get());
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional()
    {
        FUNCTIONAL.addAfter(Blocks.ENCHANTING_TABLE, ModBlocks.CURSE_ALTAR.get());

        FUNCTIONAL.addAfter(Items.END_CRYSTAL, ModItem.PURIFIED_END_CRYSTAL.get());
        FUNCTIONAL.addAfter(Blocks.BEACON, ModBlocks.ENCHANTING_MAGNET.get());
        FUNCTIONAL.addAfter(ModBlocks.ENCHANTING_MAGNET.get(), ModBlocks.STRANGE_CORE.get());

        FUNCTIONAL.addAfter(Items.REDSTONE_LAMP, ModBlocks.DIAMOND_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.DIAMOND_LUMEN.get(), ModBlocks.REDSTONE_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.REDSTONE_LUMEN.get(), ModBlocks.QUARTZ_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.QUARTZ_LUMEN.get(), ModBlocks.EMERALD_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.EMERALD_LUMEN.get(), ModBlocks.AMETHYST_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.AMETHYST_LUMEN.get(), ModBlocks.ECHO_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.ECHO_LUMEN.get(), ModBlocks.COBALT_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.COBALT_LUMEN.get(), ModBlocks.VERDINITE_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.VERDINITE_LUMEN.get(), ModBlocks.FROSTITE_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.FROSTITE_LUMEN.get(), ModBlocks.VIVULITE_LUMEN.get());
        FUNCTIONAL.addAfter(ModBlocks.VIVULITE_LUMEN.get(), ModBlocks.BRIMTAN_LUMEN.get());

        FUNCTIONAL.addBefore(Items.SKELETON_SKULL, ModBlocks.CREEPER_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.CREEPER_MODEL.get(), ModBlocks.SLIME_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.SLIME_MODEL.get(), ModBlocks.SKELETON_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.SKELETON_MODEL.get(), ModBlocks.STRAY_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.STRAY_MODEL.get(), ModBlocks.BOGGED_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.BOGGED_MODEL.get(), ModBlocks.WITHER_SKELETON_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.WITHER_SKELETON_MODEL.get(), ModBlocks.BLAZE_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.BLAZE_MODEL.get(), ModBlocks.MAGMA_CUBE_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.MAGMA_CUBE_MODEL.get(), ModBlocks.ENDERMAN_MODEL.get());
        FUNCTIONAL.addAfter(ModBlocks.ENDERMAN_MODEL.get(), ModBlocks.PHANTOM_MODEL.get());

        FUNCTIONAL.addAfter(Items.DECORATED_POT, ModBlocks.ITEM_VACUUM.get());

        FUNCTIONAL.addAfter(Items.SCAFFOLDING, ModBlocks.NECRO_RUG.get());

        FUNCTIONAL.addAfter(Blocks.DAMAGED_ANVIL, ModBlocks.VIVULITE_ANVIL.get());

        FUNCTIONAL.addAfter(Blocks.SOUL_CAMPFIRE, ModBlocks.MONSTER_BAKERY.get());

        FUNCTIONAL.addAfter(Blocks.CHEST, ModBlocks.PERSONAL_CHEST.get());

        FUNCTIONAL.addAfter(Blocks.PINK_CANDLE, ModItem.SPIRIT_CANDLE.get());

        FUNCTIONAL.addAfter(Blocks.PINK_BED, ModItem.PHANTOM_STITCH_BED.get());

        FUNCTIONAL.addAfter(Blocks.VAULT, ModBlocks.TOWER_WATCHER.get());
        FUNCTIONAL.addAfter(ModBlocks.TOWER_WATCHER.get(), ModBlocks.TOWER_HEART.get());
        FUNCTIONAL.addAfter(ModBlocks.TOWER_HEART.get(), ModBlocks.TOWER_TREASURE_VAULT.get());
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone()
    {
        REDSTONE.addAfter(Items.LIGHTNING_ROD, ModBlocks.DIAMOND_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.DIAMOND_LUMEN.get(), ModBlocks.REDSTONE_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.REDSTONE_LUMEN.get(), ModBlocks.QUARTZ_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.QUARTZ_LUMEN.get(), ModBlocks.EMERALD_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.EMERALD_LUMEN.get(), ModBlocks.AMETHYST_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.AMETHYST_LUMEN.get(), ModBlocks.ECHO_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.ECHO_LUMEN.get(), ModBlocks.COBALT_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.COBALT_LUMEN.get(), ModBlocks.VERDINITE_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.VERDINITE_LUMEN.get(), ModBlocks.FROSTITE_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.FROSTITE_LUMEN.get(), ModBlocks.VIVULITE_LUMEN.get());
        REDSTONE.addAfter(ModBlocks.VIVULITE_LUMEN.get(), ModBlocks.BRIMTAN_LUMEN.get());

        REDSTONE.addAfter(Items.HOPPER, ModBlocks.ITEM_VACUUM.get());

        REDSTONE.addAfter(Items.ARMOR_STAND, ModBlocks.BLAZE_MODEL.get());

        REDSTONE.addAfter(Items.TNT, ModBlocks.PHANTASMIC_TNT.get());
    }

    // Vanilla tab - Spawn Eggs.
    public static void tabSpawnEggs()
    {
        SPAWN_EGGS.addAfter(Items.TRIAL_SPAWNER, ModBlocks.TOWER_SPAWNER.get());

        SPAWN_EGGS.addAfter(Items.COW_SPAWN_EGG, ModItem.CRAWLER_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.CREEPER_SPAWN_EGG, ModItem.CROW_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.IRON_GOLEM_SPAWN_EGG, ModItem.JUNGLE_SPIDER_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.PUFFERFISH_SPAWN_EGG, ModItem.PUMPKIN_GOLEM_SPAWN_EGG.get());

        SPAWN_EGGS.addAfter(Items.GOAT_SPAWN_EGG, ModItem.GOLDEN_CHICKEN_SPAWN_EGG.get());
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
