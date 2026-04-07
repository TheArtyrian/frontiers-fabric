package net.artyrian.frontiers.datagen.frontiers.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.content.FRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class FRItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public FRItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    // Mod tags.
    private void modItemTag()
    {
        getOrCreateTagBuilder(FRTags.Items.EVERTREE_BOOSTABLE)
                .add(Items.CARROT)
                .add(Items.POTATO)
                .add(Items.BEETROOT_SEEDS)
                .add(Items.WHEAT_SEEDS)
                .add(Items.PUMPKIN_SEEDS)
                .add(Items.MELON_SEEDS)
                .add(Items.PITCHER_POD)
                .add(Items.TORCHFLOWER_SEEDS)
                .add(Items.SWEET_BERRIES)
                .add(Items.GLOW_BERRIES)
                .add(Items.NETHER_WART)
                .add(Items.NETHER_WART)
                .add(Items.COCOA_BEANS)
                .add(Items.SUGAR_CANE)

                .add(FRItems.ANCIENT_ROSE_SEED.get())
                .add(FRItems.WARPED_WART.get())

                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "rice"))
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "onion"))
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"))
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"))

                .addOptional(Frontiers.id(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(Frontiers.id(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;

        // Balls (manual)
        getOrCreateTagBuilder(FRTags.Items.BALLS)
                .add(FRItems.BALL.get())
                .add(FRItems.BOUNCY_BALL.get())

                // Delicate dyes
                .addOptional(Frontiers.id("coral_ball"))
                .addOptional(Frontiers.id("canary_ball"))
                .addOptional(Frontiers.id("wasabi_ball"))
                .addOptional(Frontiers.id("sacramento_ball"))
                .addOptional(Frontiers.id("sky_ball"))
                .addOptional(Frontiers.id("blurple_ball"))
                .addOptional(Frontiers.id("sangria_ball"))
                .addOptional(Frontiers.id("rose_ball"))
                .addOptional(Frontiers.id("umber_ball"))
                .addOptional(Frontiers.id("lavender_ball"))
        ;

        // Balls (auto)
        for (Supplier<Item> ball : FRItems.COLOR_BALLS.values())
        {
            getOrCreateTagBuilder(FRTags.Items.BALLS).add(ball.get());
        }

        getOrCreateTagBuilder(FRTags.Items.LUMENS)
                .add(FRBlocks.DIAMOND_LUMEN.get().asItem())
                .add(FRBlocks.QUARTZ_LUMEN.get().asItem())
                .add(FRBlocks.REDSTONE_LUMEN.get().asItem())
                .add(FRBlocks.EMERALD_LUMEN.get().asItem())
                .add(FRBlocks.AMETHYST_LUMEN.get().asItem())
                .add(FRBlocks.COBALT_LUMEN.get().asItem())
                .add(FRBlocks.FROSTITE_LUMEN.get().asItem())
                .add(FRBlocks.VERDINITE_LUMEN.get().asItem())
                .add(FRBlocks.VIVULITE_LUMEN.get().asItem())
                .add(FRBlocks.BRIMTAN_LUMEN.get().asItem())
                .add(FRBlocks.ECHO_LUMEN.get().asItem())

                // Compat items
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "feldspar_lumen"))
        ;
        getOrCreateTagBuilder(FRTags.Items.STONE_FENCE_GATES)
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(FRBlocks.NETHER_BRICK_FENCE_GATE.get().asItem())
        ;
        getOrCreateTagBuilder(FRTags.Items.ENTITY_MODELS)
                .add(FRBlocks.CREEPER_MODEL.get().asItem())
                .add(FRBlocks.SKELETON_MODEL.get().asItem())
                .add(FRBlocks.STRAY_MODEL.get().asItem())
                .add(FRBlocks.BOGGED_MODEL.get().asItem())
                .add(FRBlocks.BLAZE_MODEL.get().asItem())
                .add(FRBlocks.WITHER_SKELETON_MODEL.get().asItem())
                .add(FRBlocks.ENDERMAN_MODEL.get().asItem())
                .add(FRBlocks.SLIME_MODEL.get().asItem())
                .add(FRBlocks.MAGMA_CUBE_MODEL.get().asItem())
                .add(FRBlocks.PHANTOM_MODEL.get().asItem())
        ;
        getOrCreateTagBuilder(FRTags.Items.DEFLECTS_BALLS)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.AXES)
                .add(Items.BONE)
                .add(FRItems.ONYX_BONE.get())
                .add(FRItems.FROST_BONE.get())
                .add(Items.BLAZE_ROD)
                .add(Items.BREEZE_ROD)
                .add(Items.STICK)
                .add(Items.BAMBOO)
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "leek"))
        ;
        getOrCreateTagBuilder(FRTags.Items.GLOWING_BRIMTAN_ITEMS)
                .add(FRItems.BRIMTAN_AXE.get())
                .add(FRItems.BRIMTAN_SWORD.get())
                .add(FRItems.BRIMTAN_PICKAXE.get())
                .add(FRItems.BRIMTAN_SHOVEL.get())
                .add(FRItems.BRIMTAN_HOE.get())
                .addOptional(Frontiers.id("brimtan_knife"))

                .add(FRItems.BRIMTAN_HELMET.get())
                .add(FRItems.BRIMTAN_CHESTPLATE.get())
                .add(FRItems.BRIMTAN_LEGGINGS.get())
                .add(FRItems.BRIMTAN_BOOTS.get())
        ;
        getOrCreateTagBuilder(FRTags.Items.OFFHAND_PRIORITY_ITEM)
                .add(Items.SHIELD)
                .add(FRItems.COBALT_SHIELD.get())
        ;
        getOrCreateTagBuilder(FRTags.Items.GOLDEN_CHICKEN_FOOD)
                .add(Items.GOLD_NUGGET)
        ;
        getOrCreateTagBuilder(FRTags.Items.EBONCORK_LOGS)
                .add(FRBlocks.EBONCORK.get().asItem())
        ;
        getOrCreateTagBuilder(FRTags.Items.BLIGHTED_BIRCH_LOGS)
                .add(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get().asItem())
                .add(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get().asItem())
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get().asItem())
        ;
        getOrCreateTagBuilder(FRTags.Items.ITEM_VACUUM_SOUL_FIRE)
                .forceAddTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .add(Items.SOUL_CAMPFIRE)
                .add(Items.SOUL_LANTERN)
                .add(Items.SOUL_TORCH)
                .add(FRItems.SOUL.get())
                .addOptional(Frontiers.id(Frontiers.SUPPLEMENTARIES_ID, "sconce_soul"))
        ;
        getOrCreateTagBuilder(FRTags.Items.ITEM_VACUUM_LIVING_FIRE)
                .add(FRItems.SPAWNER_CHUNK.get())
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_fragment"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_block"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "chiseled_stained_scrap"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_pillar"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_grate"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "cut_stained_scrap"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_door"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_trapdoor"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_scrap_bars"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "stained_lantern"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "living_torch"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "living_lantern"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "living_campfire"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "living_candle"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "dungeon_stove"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "monster_pot"))
                .addOptional(Frontiers.id(Frontiers.DUNGEONS_DELIGHT_ID, "rot_and_steel"))
        ;
        getOrCreateTagBuilder(FRTags.Items.ITEM_VACUUM_HEARTS)
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "appledog_block"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "candied_dogapple"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "applecog"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "applerock"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "appledogllar"))
        ;
        getOrCreateTagBuilder(FRTags.Items.FRUITCAKE_INGREDIENTS)
                .add(Items.APPLE)
                .add(Items.MELON_SLICE)
                .add(Items.SWEET_BERRIES)
                .add(FRItems.POMEGRANATE.get())

                .addOptionalTag(ConventionalItemTags.FRUIT_FOODS)

                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "lemon"))
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "plum"))
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "orange"))
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "walnut"))
                .addOptional(Frontiers.id(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(Frontiers.id(Frontiers.AEU_ID, "dogapple"))
                .addOptional(Frontiers.id(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(Frontiers.id(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;
        getOrCreateTagBuilder(FRTags.Items.WREATHS)
                .add(FRBlocks.OAK_WREATH.get().asItem())
                .add(FRBlocks.DARK_OAK_WREATH.get().asItem())
                .add(FRBlocks.BIRCH_WREATH.get().asItem())
                .add(FRBlocks.SPRUCE_WREATH.get().asItem())
                .add(FRBlocks.JUNGLE_WREATH.get().asItem())
                .add(FRBlocks.ACACIA_WREATH.get().asItem())
                .add(FRBlocks.MANGROVE_WREATH.get().asItem())
                .add(FRBlocks.AZALEA_WREATH.get().asItem())
                .add(FRBlocks.CHERRY_WREATH.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_WREATH.get().asItem())

                .addOptional(Frontiers.id("hoary_wreath"))
                .addOptional(Frontiers.id("walnut_wreath"))
                .addOptional(Frontiers.id("apple_wreath"))
                .addOptional(Frontiers.id("orange_wreath"))
                .addOptional(Frontiers.id("lemon_wreath"))
                .addOptional(Frontiers.id("plum_wreath"))
                .addOptional(Frontiers.id("golden_wreath"))
        ;
        getOrCreateTagBuilder(FRTags.Items.TOMES)
                .add(FRItems.TOME_OF_FANGS.get())
                .add(FRItems.THUNDERVAST_TOME.get())
        ;
    }

    // Vanilla tags.
    private void vanillaItemTag()
    {
        getOrCreateTagBuilder(ItemTags.BEDS)
                .add(FRItems.PHANTOM_STITCH_BED.get())
        ;
        getOrCreateTagBuilder(ItemTags.ANVIL)
                .add(FRBlocks.VIVULITE_ANVIL.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(Frontiers.id("ancient_rose"))
                .add(Frontiers.id("rose"))
                .add(Frontiers.id("violet_rose"))
                .add(Frontiers.id("snow_dahlia"))
                .add(Frontiers.id("fungal_daffodil"))
                .add(Frontiers.id("crimcone"))
                .add(Frontiers.id("experiwinkle"))

        ;
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(Frontiers.id("ancient_rose_bush"))
                .add(Frontiers.id("violet_rose_bush"))
        ;
        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(FRBlocks.COBBLEFROST.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(FRItems.GOLDEN_EGG.get())

                // Compat items
                .addOptional(Frontiers.id("fried_golden_egg"))
        ;
        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(FRItems.BOUNCY_ARROW.get())
                .add(FRItems.SUBZERO_ARROW.get())
                .add(FRItems.WARP_ARROW.get())
                .add(FRItems.DYNAMITE_ARROW.get())
                .add(FRItems.PRISMARINE_ARROW.get())
        ;

        // Trims
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .add(FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .add(FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(FRItems.COBALT_INGOT.get())
                .add(FRItems.VERDINITE_INGOT.get())
                .add(FRItems.VIVULITE_INGOT.get())
                .add(FRItems.FROSTITE_INGOT.get())
                .add(FRItems.MOURNING_GOLD_INGOT.get())
                .add(FRItems.BRIMTAN_INGOT.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(FRItems.NECRO_WEAVE_HELMET.get())
                .add(FRItems.NECRO_WEAVE_CHESTPLATE.get())
                .add(FRItems.NECRO_WEAVE_LEGGINGS.get())
                .add(FRItems.NECRO_WEAVE_BOOTS.get())

                .add(FRItems.MOURNING_GOLD_HELMET.get())
                .add(FRItems.MOURNING_GOLD_CHESTPLATE.get())
                .add(FRItems.MOURNING_GOLD_LEGGINGS.get())
                .add(FRItems.MOURNING_GOLD_BOOTS.get())

                .add(FRItems.VERDINITE_HELMET.get())
                .add(FRItems.VERDINITE_CHESTPLATE.get())
                .add(FRItems.VERDINITE_LEGGINGS.get())
                .add(FRItems.VERDINITE_BOOTS.get())

                .add(FRItems.VIVULITE_HELMET.get())
                .add(FRItems.VIVULITE_CHESTPLATE.get())
                .add(FRItems.VIVULITE_LEGGINGS.get())
                .add(FRItems.VIVULITE_BOOTS.get())

                .add(FRItems.FROSTITE_HELMET.get())
                .add(FRItems.FROSTITE_CHESTPLATE.get())
                .add(FRItems.FROSTITE_LEGGINGS.get())
                .add(FRItems.FROSTITE_BOOTS.get())

                .add(FRItems.COBALT_HELMET.get())
                .add(FRItems.COBALT_CHESTPLATE.get())
                .add(FRItems.COBALT_LEGGINGS.get())
                .add(FRItems.COBALT_BOOTS.get())

                .add(FRItems.BRIMTAN_HELMET.get())
                .add(FRItems.BRIMTAN_CHESTPLATE.get())
                .add(FRItems.BRIMTAN_LEGGINGS.get())
                .add(FRItems.BRIMTAN_BOOTS.get())

                .add(FRItems.PLATE_HELMET.get())
                .add(FRItems.PLATE_CHESTPLATE.get())
                .add(FRItems.PLATE_LEGGINGS.get())
                .add(FRItems.PLATE_BOOTS.get())

                .add(FRItems.SLIME_SHOES.get())
        ;

        // Armors
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(FRItems.NECRO_WEAVE_HELMET.get())
                .add(FRItems.MOURNING_GOLD_HELMET.get())
                .add(FRItems.VIVULITE_HELMET.get())
                .add(FRItems.VERDINITE_HELMET.get())
                .add(FRItems.FROSTITE_HELMET.get())
                .add(FRItems.COBALT_HELMET.get())
                .add(FRItems.BRIMTAN_HELMET.get())
                .add(FRItems.PLATE_HELMET.get())
        ;
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(FRItems.NECRO_WEAVE_CHESTPLATE.get())
                .add(FRItems.MOURNING_GOLD_CHESTPLATE.get())
                .add(FRItems.VIVULITE_CHESTPLATE.get())
                .add(FRItems.VERDINITE_CHESTPLATE.get())
                .add(FRItems.FROSTITE_CHESTPLATE.get())
                .add(FRItems.COBALT_CHESTPLATE.get())
                .add(FRItems.PLATE_CHESTPLATE.get())
                .add(FRItems.BRIMTAN_CHESTPLATE.get())
        ;
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(FRItems.NECRO_WEAVE_LEGGINGS.get())
                .add(FRItems.MOURNING_GOLD_LEGGINGS.get())
                .add(FRItems.VIVULITE_LEGGINGS.get())
                .add(FRItems.VERDINITE_LEGGINGS.get())
                .add(FRItems.FROSTITE_LEGGINGS.get())
                .add(FRItems.COBALT_LEGGINGS.get())
                .add(FRItems.BRIMTAN_LEGGINGS.get())
                .add(FRItems.PLATE_LEGGINGS.get())
        ;
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(FRItems.NECRO_WEAVE_BOOTS.get())
                .add(FRItems.MOURNING_GOLD_BOOTS.get())
                .add(FRItems.VIVULITE_BOOTS.get())
                .add(FRItems.VERDINITE_BOOTS.get())
                .add(FRItems.FROSTITE_BOOTS.get())
                .add(FRItems.COBALT_BOOTS.get())
                .add(FRItems.BRIMTAN_BOOTS.get())
                .add(FRItems.SLIME_SHOES.get())
                .add(FRItems.PLATE_BOOTS.get())
        ;

        // Tools
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(FRItems.COBALT_AXE.get())
                .add(FRItems.VERDINITE_AXE.get())
                .add(FRItems.FROSTITE_AXE.get())
                .add(FRItems.VIVULITE_AXE.get())
                .add(FRItems.BRIMTAN_AXE.get())
                .add(FRItems.MOURNING_GOLD_AXE.get())
                .add(FRItems.OBSIDIAN_AXE.get())
        ;
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(FRItems.COBALT_PICKAXE.get())
                .add(FRItems.VERDINITE_PICKAXE.get())
                .add(FRItems.FROSTITE_PICKAXE.get())
                .add(FRItems.VIVULITE_PICKAXE.get())
                .add(FRItems.BRIMTAN_PICKAXE.get())
                .add(FRItems.MOURNING_GOLD_PICKAXE.get())
                .add(FRItems.OBSIDIAN_PICKAXE.get())
        ;
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(FRItems.COBALT_SWORD.get())
                .add(FRItems.VERDINITE_SWORD.get())
                .add(FRItems.FROSTITE_SWORD.get())
                .add(FRItems.VIVULITE_SWORD.get())
                .add(FRItems.BRIMTAN_SWORD.get())
                .add(FRItems.MOURNING_GOLD_SWORD.get())
                .add(FRItems.OBSIDIAN_SWORD.get())
        ;
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(FRItems.COBALT_SHOVEL.get())
                .add(FRItems.VERDINITE_SHOVEL.get())
                .add(FRItems.FROSTITE_SHOVEL.get())
                .add(FRItems.VIVULITE_SHOVEL.get())
                .add(FRItems.BRIMTAN_SHOVEL.get())
                .add(FRItems.MOURNING_GOLD_SHOVEL.get())
                .add(FRItems.OBSIDIAN_SHOVEL.get())
        ;
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(FRItems.COBALT_HOE.get())
                .add(FRItems.VERDINITE_HOE.get())
                .add(FRItems.FROSTITE_HOE.get())
                .add(FRItems.VIVULITE_HOE.get())
                .add(FRItems.BRIMTAN_HOE.get())
                .add(FRItems.MOURNING_GOLD_HOE.get())
                .add(FRItems.OBSIDIAN_HOE.get())
        ;

        // Enchanting Tags
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(FRItems.COBALT_FISHING_ROD.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIDENT_ENCHANTABLE)
                .add(FRItems.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(FRItems.COPPER_BOW.get())
                .add(FRItems.IRON_BOW.get())
                .add(FRItems.DIAMOND_BOW.get())
                .add(FRItems.NETHERITE_BOW.get())
                .add(FRItems.ECHO_BOW.get())
                .add(FRItems.VERDINITE_BOW.get())
        ;
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(FRItems.COBALT_SHIELD.get())
                .add(FRItems.COPPER_BOW.get())
                .add(FRItems.IRON_BOW.get())
                .add(FRItems.DIAMOND_BOW.get())
                .add(FRItems.NETHERITE_BOW.get())
                .add(FRItems.ECHO_BOW.get())
                .add(FRItems.VERDINITE_BOW.get())
                .add(FRItems.COBALT_FISHING_ROD.get())
                .add(FRItems.PALE_TRIDENT.get())
                .add(FRItems.THUNDERVAST_TOME.get())
        ;
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(FRBlocks.CARVED_MELON.get().asItem())
                .add(FRBlocks.CARVED_GLISTERING_MELON.get().asItem())
                .add(FRBlocks.WHITE_PUMPKIN.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(FRTags.Items.TOMES)
                .add(FRBlocks.CARVED_MELON.get().asItem())
                .add(FRBlocks.CARVED_GLISTERING_MELON.get().asItem())
                .add(FRBlocks.WHITE_PUMPKIN.get().asItem())
        ;

        // Slabs
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(FRBlocks.BLUE_NETHER_BRICK_SLAB.get().asItem())
                .add(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get().asItem())
                .add(FRBlocks.CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.NACRE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.PALE_PRISMARINE_SLAB.get().asItem())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get().asItem())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.TOWER_BRICK_SLAB.get().asItem())
                .add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get().asItem())

                .add(FRBlocks.HIELOSTONE_SLAB.get().asItem())
                .add(FRBlocks.HIELOSTONE_BRICK_SLAB.get().asItem())
                .add(FRBlocks.HIELOSTONE_TILE_SLAB.get().asItem())
                .add(FRBlocks.HIELOSTONE_PLATE_SLAB.get().asItem())
                .add(FRBlocks.COBBLEFROST_SLAB.get().asItem())
                .add(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get().asItem())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get().asItem())

                .add(FRBlocks.GOLDEN_EGG_PALLET.get().asItem())
                .add(FRBlocks.EGG_PALLET.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(FRBlocks.EBONCORK_SLAB.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_SLAB.get().asItem())
        ;
        // Stairs
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.NACRE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.PALE_PRISMARINE_STAIRS.get().asItem())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get().asItem())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.TOWER_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get().asItem())

                .add(FRBlocks.HIELOSTONE_STAIRS.get().asItem())
                .add(FRBlocks.HIELOSTONE_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.HIELOSTONE_TILE_STAIRS.get().asItem())
                .add(FRBlocks.HIELOSTONE_PLATE_STAIRS.get().asItem())
                .add(FRBlocks.COBBLEFROST_STAIRS.get().asItem())
                .add(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get().asItem())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(FRBlocks.EBONCORK_STAIRS.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_STAIRS.get().asItem())
        ;
        // Walls
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(FRBlocks.BLUE_NETHER_BRICK_WALL.get().asItem())
                .add(FRBlocks.PURPLE_NETHER_BRICK_WALL.get().asItem())
                .add(FRBlocks.CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(FRBlocks.NACRE_BRICK_WALL.get().asItem())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_WALL.get().asItem())
                .add(FRBlocks.PALE_PRISMARINE_WALL.get().asItem())
                .add(FRBlocks.TOWER_BRICK_WALL.get().asItem())
                .add(FRBlocks.MOSSY_TOWER_BRICK_WALL.get().asItem())

                .add(FRBlocks.HIELOSTONE_WALL.get().asItem())
                .add(FRBlocks.HIELOSTONE_BRICK_WALL.get().asItem())
                .add(FRBlocks.HIELOSTONE_TILE_WALL.get().asItem())
                .add(FRBlocks.HIELOSTONE_PLATE_WALL.get().asItem())
                .add(FRBlocks.COBBLEFROST_WALL.get().asItem())
                .add(FRBlocks.CRUSTY_SAND_BRICK_WALL.get().asItem())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get().asItem())
        ;
        // Fences
        getOrCreateTagBuilder(ItemTags.FENCES)
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE.get().asItem())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get().asItem())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(FRBlocks.EBONCORK_FENCE.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_FENCE.get().asItem())
        ;
        // Fence Gates
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(FRBlocks.EBONCORK_FENCE_GATE.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get().asItem())
        ;
        // Pressure Plates + Buttons
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(FRBlocks.EBONCORK_PRESSURE_PLATE.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(FRBlocks.EBONCORK_BUTTON.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_BUTTON.get().asItem())
        ;
        // Doors + Trapdoors
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(FRItems.EBONCORK_DOOR.get())
                .add(FRItems.BLIGHTED_BIRCH_DOOR.get())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(FRBlocks.EBONCORK_TRAPDOOR.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get().asItem())
        ;
        // Wood tags
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(FRBlocks.EBONCORK_PLANKS.get().asItem())
                .add(FRBlocks.BLIGHTED_BIRCH_PLANKS.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(FRBlocks.EBONCORK.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(FRTags.Items.BLIGHTED_BIRCH_LOGS)
        ;
        getOrCreateTagBuilder(ItemTags.NON_FLAMMABLE_WOOD)
                .add(FRBlocks.EBONCORK.get().asItem())
                .add(FRBlocks.EBONCORK_PLANKS.get().asItem())
                .add(FRBlocks.EBONCORK_STAIRS.get().asItem())
                .add(FRBlocks.EBONCORK_SLAB.get().asItem())
                .add(FRBlocks.EBONCORK_FENCE.get().asItem())
                .add(FRBlocks.EBONCORK_FENCE_GATE.get().asItem())
                .add(FRBlocks.EBONCORK_TRAPDOOR.get().asItem())
                .add(FRItems.EBONCORK_DOOR.get())
        ;

        getOrCreateTagBuilder(ItemTags.SMELTS_TO_GLASS)
                .add(FRBlocks.QUICKSAND.get().asItem())
                .add(FRBlocks.RED_QUICKSAND.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(FRBlocks.BLIGHTED_BIRCH_SAPLING.get().asItem())
        ;

        //getOrCreateTagBuilder(ItemTags.HOGLIN_FOOD)
        //        .add(ModItem.TRUFFLE.get())
        //;
    }

    // Common tags.
    private void commonItemTag()
    {
        getOrCreateTagBuilder(ConventionalItemTags.STONES)
                .add(FRBlocks.HIELOSTONE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COBBLESTONES)
                .add(FRBlocks.COBBLEFROST.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.OBSIDIANS)
                .add(FRItems.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.SPEAR_TOOLS)
                .add(FRItems.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(FRItems.COPPER_BOW.get())
                .add(FRItems.DIAMOND_BOW.get())
                .add(FRItems.ECHO_BOW.get())
                .add(FRItems.IRON_BOW.get())
                .add(FRItems.NETHERITE_BOW.get())
                .add(FRItems.VERDINITE_BOW.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.SHIELD_TOOLS)
                .add(FRItems.COBALT_SHIELD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FISHING_ROD_TOOLS)
                .add(FRItems.COBALT_FISHING_ROD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
                .add(FRItems.MOURNING_GOLD_SWORD.get())
                .add(FRItems.OBSIDIAN_SWORD.get())
                .add(FRItems.COBALT_SWORD.get())
                .add(FRItems.VERDINITE_SWORD.get())
                .add(FRItems.VIVULITE_SWORD.get())
                .add(FRItems.BRIMTAN_SWORD.get())
                .add(FRItems.FROSTITE_SWORD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RANGED_WEAPON_TOOLS)
                .add(FRItems.COPPER_BOW.get())
                .add(FRItems.DIAMOND_BOW.get())
                .add(FRItems.ECHO_BOW.get())
                .add(FRItems.IRON_BOW.get())
                .add(FRItems.NETHERITE_BOW.get())
                .add(FRItems.VERDINITE_BOW.get())
                .add(FRItems.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
                .add(FRItems.MOURNING_GOLD_PICKAXE.get())
                .add(FRItems.OBSIDIAN_PICKAXE.get())
                .add(FRItems.COBALT_PICKAXE.get())
                .add(FRItems.VERDINITE_PICKAXE.get())
                .add(FRItems.VIVULITE_PICKAXE.get())
                .add(FRItems.BRIMTAN_PICKAXE.get())
                .add(FRItems.FROSTITE_PICKAXE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BRICKS)
                .add(FRItems.NACRE_BRICK.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GEMS)
                .add(FRItems.BLACK_EMERALD.get())
                .add(FRItems.VOID_DIAMOND.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.INGOTS)
                .add(FRItems.MOURNING_GOLD_INGOT.get())
                .add(FRItems.COBALT_INGOT.get())
                .add(FRItems.VERDINITE_INGOT.get())
                .add(FRItems.FROSTITE_INGOT.get())
                .add(FRItems.VIVULITE_INGOT.get())
                .add(FRItems.BRIMTAN_INGOT.get())
                .add(FRItems.PITCH_INGOT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.NUGGETS)
                .add(FRItems.BRIMTAN_NUGGET.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.ORES)
                .add(FRBlocks.COBALT_ORE.get().asItem())
                .add(FRBlocks.DEEPSLATE_COBALT_ORE.get().asItem())
                .add(FRBlocks.VERDINITE_ORE.get().asItem())
                .add(FRBlocks.DEEPSLATE_VERDINITE_ORE.get().asItem())
                .add(FRBlocks.FROSTITE_ORE.get().asItem())
                .add(FRBlocks.DEEPSLATE_VIVULITE_ORE.get().asItem())
                .add(FRBlocks.VIVULITE_ORE.get().asItem())
                .add(FRBlocks.BRIMTAN_ORE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_MATERIALS)
                .add(FRItems.RAW_COBALT.get())
                .add(FRItems.RAW_VERDINITE.get())
                .add(FRItems.RAW_VIVULITE.get())
                .add(FRItems.RAW_FROSTITE.get())
                .add(FRItems.BRIMTAN_CLUSTER.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.EMERALD_GEMS)
                .add(FRItems.BLACK_EMERALD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.ANIMAL_FOODS)
                .addTag(FRTags.Items.GOLDEN_CHICKEN_FOOD)
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FOODS)
                .add(FRItems.MARSHMALLOW.get())
                .add(FRItems.ROASTED_MARSHMALLOW.get())
                .add(FRItems.TRUFFLE.get())
                .add(FRItems.TRUFFLE_POTATO_PUFF.get())
                .add(FRItems.TRUFFLE_OIL.get())
                .add(FRItems.FRUITCAKE_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS)
                .add(FRItems.POMEGRANATE.get())
                .add(FRItems.APPLE_OF_ENLIGHTENMENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BREAD_FOODS)
                .add(FRItems.LEVI_ROLL.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_FISH_FOODS)
                .add(FRItems.GUARDIAN_SLICE.get())
                .add(FRItems.ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_FISHES_FOODS)
                .add(FRItems.GUARDIAN_SLICE.get())
                .add(FRItems.ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISH_FOODS)
                .add(FRItems.COOKED_GUARDIAN_SLICE.get())
                .add(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISHES_FOODS)
                .add(FRItems.COOKED_GUARDIAN_SLICE.get())
                .add(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GOLDEN_FOODS)
                .add(Items.GLISTERING_MELON_SLICE)
        ;
        getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
                .add(FRBlocks.BEEF_WELLINGTON.get().asItem())
                .add(FRBlocks.FRUITCAKE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.CHESTS)
                .add(FRBlocks.PERSONAL_CHEST.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GLASS_BLOCKS)
                .add(FRBlocks.SEA_GLASS.get().asItem())
                .add(FRBlocks.PALE_SEA_GLASS.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GLASS_PANES)
                .add(FRBlocks.SEA_GLASS_PANE.get().asItem())
                .add(FRBlocks.PALE_SEA_GLASS_PANE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BUDS)
                .add(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get().asItem())
                .add(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get().asItem())
                .add(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.CLUSTERS)
                .add(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .add(FRBlocks.BRIMTAN_BLOCK.get().asItem())
                .add(FRBlocks.BLACK_EMERALD_BLOCK.get().asItem())
                .add(FRBlocks.COBALT_BLOCK.get().asItem())
                .add(FRBlocks.FROSTITE_BLOCK.get().asItem())
                .add(FRBlocks.COCOA_BEAN_BLOCK.get().asItem())
                .add(FRBlocks.ROTTEN_FLESH_BLOCK.get().asItem())
                .add(FRBlocks.MOURNING_GOLD_BLOCK.get().asItem())
                .add(FRBlocks.NECRO_WEAVE_BLOCK.get().asItem())
                .add(FRBlocks.RAW_COBALT_BLOCK.get().asItem())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get().asItem())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get().asItem())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get().asItem())
                .add(FRBlocks.SUGAR_CANE_BLOCK.get().asItem())
                .add(FRBlocks.VERDINITE_BLOCK.get().asItem())
                .add(FRBlocks.VIVULITE_BLOCK.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_LOGS)
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_WOODS)
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.NETHER_WART_CROPS)
                .add(FRItems.WARPED_WART.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MUSIC_DISCS)
                .add(FRItems.MUSIC_DISC_DIAPHRAGM.get())
        ;
        // Custom C
        getOrCreateTagBuilder(FRTags.Items.C_EGGS)
                .add(FRItems.GOLDEN_EGG.get())
        ;
        getOrCreateTagBuilder(FRTags.Items.C_RAW_BLOCKS)
                .add(FRBlocks.RAW_COBALT_BLOCK.get().asItem())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get().asItem())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get().asItem())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get().asItem())
        ;
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modItemTag();
        vanillaItemTag();
        commonItemTag();
    }
}
