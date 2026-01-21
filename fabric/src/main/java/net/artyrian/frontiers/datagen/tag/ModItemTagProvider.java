package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    // Mod tags.
    private void modItemTag()
    {
        tag(ModTags.Items.EVERTREE_BOOSTABLE)
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

                .add(ModItem.ANCIENT_ROSE_SEED)
                .add(ModItem.WARPED_WART)

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "rice"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "onion"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"))

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;

        // Balls (manual)
        tag(ModTags.Items.BALLS)
                .add(ModItem.BALL)
                .add(ModItem.BOUNCY_BALL)

                // Delicate dyes
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "coral_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "canary_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "wasabi_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "sacramento_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "sky_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "blurple_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "sangria_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "rose_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "umber_ball"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "lavender_ball"))
        ;

        // Balls (auto)
        for (Item ball : ModItem.COLOR_BALLS.values())
        {
            tag(ModTags.Items.BALLS).add(ball);
        }

        tag(ModTags.Items.LUMENS)
                .add(ModBlocks.DIAMOND_LUMEN.asItem())
                .add(ModBlocks.QUARTZ_LUMEN.asItem())
                .add(ModBlocks.REDSTONE_LUMEN.asItem())
                .add(ModBlocks.EMERALD_LUMEN.asItem())
                .add(ModBlocks.AMETHYST_LUMEN.asItem())
                .add(ModBlocks.COBALT_LUMEN.asItem())
                .add(ModBlocks.FROSTITE_LUMEN.asItem())
                .add(ModBlocks.VERDINITE_LUMEN.asItem())
                .add(ModBlocks.VIVULITE_LUMEN.asItem())
                .add(ModBlocks.BRIMTAN_LUMEN.asItem())
                .add(ModBlocks.ECHO_LUMEN.asItem())

                // Compat items
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "feldspar_lumen"))
        ;
        tag(ModTags.Items.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.asItem())
        ;
        tag(ModTags.Items.ENTITY_MODELS)
                .add(ModBlocks.CREEPER_MODEL.asItem())
                .add(ModBlocks.SKELETON_MODEL.asItem())
                .add(ModBlocks.STRAY_MODEL.asItem())
                .add(ModBlocks.BOGGED_MODEL.asItem())
                .add(ModBlocks.BLAZE_MODEL.asItem())
                .add(ModBlocks.WITHER_SKELETON_MODEL.asItem())
                .add(ModBlocks.ENDERMAN_MODEL.asItem())
                .add(ModBlocks.SLIME_MODEL.asItem())
                .add(ModBlocks.MAGMA_CUBE_MODEL.asItem())
        ;
        tag(ModTags.Items.DEFLECTS_BALLS)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.AXES)
                .add(Items.BONE)
                .add(ModItem.ONYX_BONE)
                .add(ModItem.FROST_BONE)
                .add(Items.BLAZE_ROD)
                .add(Items.BREEZE_ROD)
                .add(Items.STICK)
                .add(Items.BAMBOO)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "leek"))
        ;
        tag(ModTags.Items.GLOWING_BRIMTAN_ITEMS)
                .add(ModItem.BRIMTAN_AXE)
                .add(ModItem.BRIMTAN_SWORD)
                .add(ModItem.BRIMTAN_PICKAXE)
                .add(ModItem.BRIMTAN_SHOVEL)
                .add(ModItem.BRIMTAN_HOE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_knife"))

                .add(ModItem.BRIMTAN_HELMET)
                .add(ModItem.BRIMTAN_CHESTPLATE)
                .add(ModItem.BRIMTAN_LEGGINGS)
                .add(ModItem.BRIMTAN_BOOTS)
        ;
        tag(ModTags.Items.OFFHAND_PRIORITY_ITEM)
                .add(Items.SHIELD)
                .add(ModItem.COBALT_SHIELD)
        ;
        tag(ModTags.Items.GOLDEN_CHICKEN_FOOD)
                .add(Items.GOLD_NUGGET)
        ;
        tag(ModTags.Items.EBONCORK_LOGS)
                .add(ModBlocks.EBONCORK.asItem())
        ;
        tag(ModTags.Items.BLIGHTED_BIRCH_LOGS)
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.asItem())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.asItem())
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.asItem())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.asItem())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.asItem())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.asItem())
        ;
        tag(ModTags.Items.ITEM_VACUUM_SOUL_FIRE)
                .forceAddTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .add(Items.SOUL_CAMPFIRE)
                .add(Items.SOUL_LANTERN)
                .add(Items.SOUL_TORCH)
                .add(ModItem.SOUL)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.SUPPLEMENTARIES_ID, "sconce_soul"))
        ;
        tag(ModTags.Items.ITEM_VACUUM_HEARTS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "appledog_block"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "candied_dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "applecog"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "applerock"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "appledogllar"))
        ;
        tag(ModTags.Items.FRUITCAKE_INGREDIENTS)
                .add(Items.APPLE)
                .add(Items.MELON_SLICE)
                .add(Items.SWEET_BERRIES)

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "lemon"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "plum"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "orange"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "walnut"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;
        tag(ModTags.Items.WREATHS)
                .add(ModBlocks.OAK_WREATH.asItem())
                .add(ModBlocks.DARK_OAK_WREATH.asItem())
                .add(ModBlocks.BIRCH_WREATH.asItem())
                .add(ModBlocks.SPRUCE_WREATH.asItem())
                .add(ModBlocks.JUNGLE_WREATH.asItem())
                .add(ModBlocks.ACACIA_WREATH.asItem())
                .add(ModBlocks.MANGROVE_WREATH.asItem())
                .add(ModBlocks.AZALEA_WREATH.asItem())
                .add(ModBlocks.CHERRY_WREATH.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_WREATH.asItem())

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hoary_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "walnut_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "apple_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "orange_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "lemon_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "plum_wreath"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "golden_wreath"))
        ;
    }

    // Vanilla tags.
    private void vanillaItemTag()
    {
        tag(ItemTags.BEDS)
                .add(ModItem.PHANTOM_STITCH_BED)
        ;
        tag(ItemTags.ANVIL)
                .add(ModBlocks.VIVULITE_ANVIL.asItem())
        ;
        tag(ItemTags.SMALL_FLOWERS)
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "ancient_rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "violet_rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "snow_dahlia"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fungal_daffodil"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crimcone"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "experiwinkle"))

        ;
        tag(ItemTags.TALL_FLOWERS)
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "ancient_rose_bush"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "violet_rose_bush"))
        ;
        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.COBBLEFROST.asItem())
        ;
        tag(ItemTags.PIGLIN_LOVED)
                .add(ModItem.GOLDEN_EGG)

                // Compat items
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg"))
        ;
        tag(ItemTags.ARROWS)
                .add(ModItem.BOUNCY_ARROW)
                .add(ModItem.SUBZERO_ARROW)
                .add(ModItem.WARP_ARROW)
                .add(ModItem.DYNAMITE_ARROW)
                .add(ModItem.PRISMARINE_ARROW)
        ;

        // Trims
        tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .add(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .add(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE)
        ;
        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItem.COBALT_INGOT)
                .add(ModItem.VERDINITE_INGOT)
                .add(ModItem.VIVULITE_INGOT)
                .add(ModItem.FROSTITE_INGOT)
                .add(ModItem.MOURNING_GOLD_INGOT)
                .add(ModItem.BRIMTAN_INGOT)
        ;
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.NECRO_WEAVE_BOOTS)

                .add(ModItem.MOURNING_GOLD_HELMET)
                .add(ModItem.MOURNING_GOLD_CHESTPLATE)
                .add(ModItem.MOURNING_GOLD_LEGGINGS)
                .add(ModItem.MOURNING_GOLD_BOOTS)

                .add(ModItem.VERDINITE_HELMET)
                .add(ModItem.VERDINITE_CHESTPLATE)
                .add(ModItem.VERDINITE_LEGGINGS)
                .add(ModItem.VERDINITE_BOOTS)

                .add(ModItem.VIVULITE_HELMET)
                .add(ModItem.VIVULITE_CHESTPLATE)
                .add(ModItem.VIVULITE_LEGGINGS)
                .add(ModItem.VIVULITE_BOOTS)

                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.FROSTITE_BOOTS)

                .add(ModItem.COBALT_HELMET)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.COBALT_LEGGINGS)
                .add(ModItem.COBALT_BOOTS)

                .add(ModItem.BRIMTAN_HELMET)
                .add(ModItem.BRIMTAN_CHESTPLATE)
                .add(ModItem.BRIMTAN_LEGGINGS)
                .add(ModItem.BRIMTAN_BOOTS)

                .add(ModItem.PLATE_HELMET)
                .add(ModItem.PLATE_CHESTPLATE)
                .add(ModItem.PLATE_LEGGINGS)
                .add(ModItem.PLATE_BOOTS)

                .add(ModItem.SLIME_SHOES)
        ;

        // Armors
        tag(ItemTags.HEAD_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.MOURNING_GOLD_HELMET)
                .add(ModItem.VIVULITE_HELMET)
                .add(ModItem.VERDINITE_HELMET)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.BRIMTAN_HELMET)
                .add(ModItem.PLATE_HELMET)
        ;
        tag(ItemTags.CHEST_ARMOR)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.MOURNING_GOLD_CHESTPLATE)
                .add(ModItem.VIVULITE_CHESTPLATE)
                .add(ModItem.VERDINITE_CHESTPLATE)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.PLATE_CHESTPLATE)
                .add(ModItem.BRIMTAN_CHESTPLATE)
        ;
        tag(ItemTags.LEG_ARMOR)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.MOURNING_GOLD_LEGGINGS)
                .add(ModItem.VIVULITE_LEGGINGS)
                .add(ModItem.VERDINITE_LEGGINGS)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.COBALT_LEGGINGS)
                .add(ModItem.BRIMTAN_LEGGINGS)
                .add(ModItem.PLATE_LEGGINGS)
        ;
        tag(ItemTags.FOOT_ARMOR)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.MOURNING_GOLD_BOOTS)
                .add(ModItem.VIVULITE_BOOTS)
                .add(ModItem.VERDINITE_BOOTS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_BOOTS)
                .add(ModItem.BRIMTAN_BOOTS)
                .add(ModItem.SLIME_SHOES)
                .add(ModItem.PLATE_BOOTS)
        ;

        // Tools
        tag(ItemTags.AXES)
                .add(ModItem.COBALT_AXE)
                .add(ModItem.VERDINITE_AXE)
                .add(ModItem.FROSTITE_AXE)
                .add(ModItem.VIVULITE_AXE)
                .add(ModItem.BRIMTAN_AXE)
                .add(ModItem.MOURNING_GOLD_AXE)
                .add(ModItem.OBSIDIAN_AXE)
        ;
        tag(ItemTags.PICKAXES)
                .add(ModItem.COBALT_PICKAXE)
                .add(ModItem.VERDINITE_PICKAXE)
                .add(ModItem.FROSTITE_PICKAXE)
                .add(ModItem.VIVULITE_PICKAXE)
                .add(ModItem.BRIMTAN_PICKAXE)
                .add(ModItem.MOURNING_GOLD_PICKAXE)
                .add(ModItem.OBSIDIAN_PICKAXE)
        ;
        tag(ItemTags.SWORDS)
                .add(ModItem.COBALT_SWORD)
                .add(ModItem.VERDINITE_SWORD)
                .add(ModItem.FROSTITE_SWORD)
                .add(ModItem.VIVULITE_SWORD)
                .add(ModItem.BRIMTAN_SWORD)
                .add(ModItem.MOURNING_GOLD_SWORD)
                .add(ModItem.OBSIDIAN_SWORD)
        ;
        tag(ItemTags.SHOVELS)
                .add(ModItem.COBALT_SHOVEL)
                .add(ModItem.VERDINITE_SHOVEL)
                .add(ModItem.FROSTITE_SHOVEL)
                .add(ModItem.VIVULITE_SHOVEL)
                .add(ModItem.BRIMTAN_SHOVEL)
                .add(ModItem.MOURNING_GOLD_SHOVEL)
                .add(ModItem.OBSIDIAN_SHOVEL)
        ;
        tag(ItemTags.HOES)
                .add(ModItem.COBALT_HOE)
                .add(ModItem.VERDINITE_HOE)
                .add(ModItem.FROSTITE_HOE)
                .add(ModItem.VIVULITE_HOE)
                .add(ModItem.BRIMTAN_HOE)
                .add(ModItem.MOURNING_GOLD_HOE)
                .add(ModItem.OBSIDIAN_HOE)
        ;

        // Enchanting Tags
        tag(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItem.COBALT_FISHING_ROD)
        ;
        tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItem.PALE_TRIDENT)
        ;
        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItem.COPPER_BOW)
                .add(ModItem.IRON_BOW)
                .add(ModItem.DIAMOND_BOW)
                .add(ModItem.NETHERITE_BOW)
                .add(ModItem.ECHO_BOW)
                .add(ModItem.VERDINITE_BOW)
        ;
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItem.COBALT_SHIELD)
                .add(ModItem.COPPER_BOW)
                .add(ModItem.IRON_BOW)
                .add(ModItem.DIAMOND_BOW)
                .add(ModItem.NETHERITE_BOW)
                .add(ModItem.ECHO_BOW)
                .add(ModItem.VERDINITE_BOW)
                .add(ModItem.COBALT_FISHING_ROD)
                .add(ModItem.PALE_TRIDENT)
                .add(ModItem.TOME_OF_FANGS)
        ;
        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.asItem())
                .add(ModBlocks.WHITE_PUMPKIN.asItem())
        ;
        tag(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.asItem())
                .add(ModBlocks.WHITE_PUMPKIN.asItem())
        ;

        // Slabs
        tag(ItemTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.asItem())
                .add(ModBlocks.NACRE_BRICK_SLAB.asItem())
                .add(ModBlocks.PALE_PRISMARINE_SLAB.asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.asItem())
                .add(ModBlocks.TOWER_BRICK_SLAB.asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.asItem())

                .add(ModBlocks.HIELOSTONE_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.asItem())
                .add(ModBlocks.COBBLEFROST_SLAB.asItem())
        ;
        tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.EBONCORK_SLAB.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_SLAB.asItem())
        ;
        // Stairs
        tag(ItemTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.asItem())
                .add(ModBlocks.NACRE_BRICK_STAIRS.asItem())
                .add(ModBlocks.PALE_PRISMARINE_STAIRS.asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.asItem())
                .add(ModBlocks.TOWER_BRICK_STAIRS.asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.asItem())

                .add(ModBlocks.HIELOSTONE_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.asItem())
                .add(ModBlocks.COBBLEFROST_STAIRS.asItem())
        ;
        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.EBONCORK_STAIRS.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_STAIRS.asItem())
        ;
        // Walls
        tag(ItemTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.NACRE_BRICK_WALL.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.asItem())
                .add(ModBlocks.PALE_PRISMARINE_WALL.asItem())
                .add(ModBlocks.TOWER_BRICK_WALL.asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.asItem())

                .add(ModBlocks.HIELOSTONE_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.asItem())
                .add(ModBlocks.COBBLEFROST_WALL.asItem())
        ;
        // Fences
        tag(ItemTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE.asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.asItem())
        ;
        tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.EBONCORK_FENCE.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE.asItem())
        ;
        // Fence Gates
        tag(ItemTags.FENCE_GATES)
                .add(ModBlocks.EBONCORK_FENCE_GATE.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.asItem())
        ;
        // Pressure Plates + Buttons
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.EBONCORK_PRESSURE_PLATE.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.asItem())
        ;
        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.EBONCORK_BUTTON.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_BUTTON.asItem())
        ;
        // Doors + Trapdoors
        tag(ItemTags.WOODEN_DOORS)
                .add(ModItem.EBONCORK_DOOR)
                .add(ModItem.BLIGHTED_BIRCH_DOOR)
        ;
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EBONCORK_TRAPDOOR.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.asItem())
        ;
        // Wood tags
        tag(ItemTags.PLANKS)
                .add(ModBlocks.EBONCORK_PLANKS.asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_PLANKS.asItem())
        ;
        tag(ItemTags.LOGS)
                .add(ModBlocks.EBONCORK.asItem())
        ;
        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.BLIGHTED_BIRCH_LOGS)
        ;
        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .add(ModBlocks.EBONCORK.asItem())
                .add(ModBlocks.EBONCORK_PLANKS.asItem())
                .add(ModBlocks.EBONCORK_STAIRS.asItem())
                .add(ModBlocks.EBONCORK_SLAB.asItem())
                .add(ModBlocks.EBONCORK_FENCE.asItem())
                .add(ModBlocks.EBONCORK_FENCE_GATE.asItem())
                .add(ModBlocks.EBONCORK_TRAPDOOR.asItem())
                .add(ModItem.EBONCORK_DOOR)
        ;

        tag(ItemTags.SMELTS_TO_GLASS)
                .add(ModBlocks.QUICKSAND.asItem())
                .add(ModBlocks.RED_QUICKSAND.asItem())
        ;
        tag(ItemTags.SAPLINGS)
                .add(ModBlocks.BLIGHTED_BIRCH_SAPLING.asItem())
        ;

        //getOrCreateTagBuilder(ItemTags.HOGLIN_FOOD)
        //        .add(ModItem.TRUFFLE)
        //;
    }

    // Fabric tags.
    private void fabricItemTag()
    {

    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modItemTag();
        vanillaItemTag();
        fabricItemTag();
    }
}
