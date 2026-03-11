package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    // Mod tags.
    private void modItemTag()
    {
        getOrCreateTagBuilder(ModTags.Items.EVERTREE_BOOSTABLE)
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

                .add(ModItem.ANCIENT_ROSE_SEED.get())
                .add(ModItem.WARPED_WART.get())

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "rice"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "onion"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"))

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;

        // Balls (manual)
        getOrCreateTagBuilder(ModTags.Items.BALLS)
                .add(ModItem.BALL.get())
                .add(ModItem.BOUNCY_BALL.get())

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
        for (Supplier<Item> ball : ModItem.COLOR_BALLS.values())
        {
            getOrCreateTagBuilder(ModTags.Items.BALLS).add(ball.get());
        }

        getOrCreateTagBuilder(ModTags.Items.LUMENS)
                .add(ModBlocks.DIAMOND_LUMEN.get().asItem())
                .add(ModBlocks.QUARTZ_LUMEN.get().asItem())
                .add(ModBlocks.REDSTONE_LUMEN.get().asItem())
                .add(ModBlocks.EMERALD_LUMEN.get().asItem())
                .add(ModBlocks.AMETHYST_LUMEN.get().asItem())
                .add(ModBlocks.COBALT_LUMEN.get().asItem())
                .add(ModBlocks.FROSTITE_LUMEN.get().asItem())
                .add(ModBlocks.VERDINITE_LUMEN.get().asItem())
                .add(ModBlocks.VIVULITE_LUMEN.get().asItem())
                .add(ModBlocks.BRIMTAN_LUMEN.get().asItem())
                .add(ModBlocks.ECHO_LUMEN.get().asItem())

                // Compat items
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "feldspar_lumen"))
        ;
        getOrCreateTagBuilder(ModTags.Items.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get().asItem())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.get().asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.ENTITY_MODELS)
                .add(ModBlocks.CREEPER_MODEL.get().asItem())
                .add(ModBlocks.SKELETON_MODEL.get().asItem())
                .add(ModBlocks.STRAY_MODEL.get().asItem())
                .add(ModBlocks.BOGGED_MODEL.get().asItem())
                .add(ModBlocks.BLAZE_MODEL.get().asItem())
                .add(ModBlocks.WITHER_SKELETON_MODEL.get().asItem())
                .add(ModBlocks.ENDERMAN_MODEL.get().asItem())
                .add(ModBlocks.SLIME_MODEL.get().asItem())
                .add(ModBlocks.MAGMA_CUBE_MODEL.get().asItem())
                .add(ModBlocks.PHANTOM_MODEL.get().asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.DEFLECTS_BALLS)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.AXES)
                .add(Items.BONE)
                .add(ModItem.ONYX_BONE.get())
                .add(ModItem.FROST_BONE.get())
                .add(Items.BLAZE_ROD)
                .add(Items.BREEZE_ROD)
                .add(Items.STICK)
                .add(Items.BAMBOO)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "leek"))
        ;
        getOrCreateTagBuilder(ModTags.Items.GLOWING_BRIMTAN_ITEMS)
                .add(ModItem.BRIMTAN_AXE.get())
                .add(ModItem.BRIMTAN_SWORD.get())
                .add(ModItem.BRIMTAN_PICKAXE.get())
                .add(ModItem.BRIMTAN_SHOVEL.get())
                .add(ModItem.BRIMTAN_HOE.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_knife"))

                .add(ModItem.BRIMTAN_HELMET.get())
                .add(ModItem.BRIMTAN_CHESTPLATE.get())
                .add(ModItem.BRIMTAN_LEGGINGS.get())
                .add(ModItem.BRIMTAN_BOOTS.get())
        ;
        getOrCreateTagBuilder(ModTags.Items.OFFHAND_PRIORITY_ITEM)
                .add(Items.SHIELD)
                .add(ModItem.COBALT_SHIELD.get())
        ;
        getOrCreateTagBuilder(ModTags.Items.GOLDEN_CHICKEN_FOOD)
                .add(Items.GOLD_NUGGET)
        ;
        getOrCreateTagBuilder(ModTags.Items.EBONCORK_LOGS)
                .add(ModBlocks.EBONCORK.get().asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.BLIGHTED_BIRCH_LOGS)
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get().asItem())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get().asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.ITEM_VACUUM_SOUL_FIRE)
                .forceAddTag(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .add(Items.SOUL_CAMPFIRE)
                .add(Items.SOUL_LANTERN)
                .add(Items.SOUL_TORCH)
                .add(ModItem.SOUL.get())
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.SUPPLEMENTARIES_ID, "sconce_soul"))
        ;
        getOrCreateTagBuilder(ModTags.Items.ITEM_VACUUM_HEARTS)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "appledog_block"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "candied_dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "applecog"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "applerock"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "appledogllar"))
        ;
        getOrCreateTagBuilder(ModTags.Items.FRUITCAKE_INGREDIENTS)
                .add(Items.APPLE)
                .add(Items.MELON_SLICE)
                .add(Items.SWEET_BERRIES)
                .add(ModItem.POMEGRANATE.get())

                .addOptionalTag(ConventionalItemTags.FRUIT_FOODS)

                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "lemon"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "plum"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "orange"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "walnut"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.APPLEDOG_ID, "dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.AEU_ID, "dogapple"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberries"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.DELICATE_DYES_ID, "blueberrycat"))
        ;
        getOrCreateTagBuilder(ModTags.Items.WREATHS)
                .add(ModBlocks.OAK_WREATH.get().asItem())
                .add(ModBlocks.DARK_OAK_WREATH.get().asItem())
                .add(ModBlocks.BIRCH_WREATH.get().asItem())
                .add(ModBlocks.SPRUCE_WREATH.get().asItem())
                .add(ModBlocks.JUNGLE_WREATH.get().asItem())
                .add(ModBlocks.ACACIA_WREATH.get().asItem())
                .add(ModBlocks.MANGROVE_WREATH.get().asItem())
                .add(ModBlocks.AZALEA_WREATH.get().asItem())
                .add(ModBlocks.CHERRY_WREATH.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_WREATH.get().asItem())

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
        getOrCreateTagBuilder(ItemTags.BEDS)
                .add(ModItem.PHANTOM_STITCH_BED.get())
        ;
        getOrCreateTagBuilder(ItemTags.ANVIL)
                .add(ModBlocks.VIVULITE_ANVIL.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "ancient_rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "violet_rose"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "snow_dahlia"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fungal_daffodil"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crimcone"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "experiwinkle"))

        ;
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "ancient_rose_bush"))
                .add(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "violet_rose_bush"))
        ;
        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.COBBLEFROST.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(ModItem.GOLDEN_EGG.get())

                // Compat items
                .addOptional(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg"))
        ;
        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItem.BOUNCY_ARROW.get())
                .add(ModItem.SUBZERO_ARROW.get())
                .add(ModItem.WARP_ARROW.get())
                .add(ModItem.DYNAMITE_ARROW.get())
                .add(ModItem.PRISMARINE_ARROW.get())
        ;

        // Trims
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .add(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .add(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItem.COBALT_INGOT.get())
                .add(ModItem.VERDINITE_INGOT.get())
                .add(ModItem.VIVULITE_INGOT.get())
                .add(ModItem.FROSTITE_INGOT.get())
                .add(ModItem.MOURNING_GOLD_INGOT.get())
                .add(ModItem.BRIMTAN_INGOT.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET.get())
                .add(ModItem.NECRO_WEAVE_CHESTPLATE.get())
                .add(ModItem.NECRO_WEAVE_LEGGINGS.get())
                .add(ModItem.NECRO_WEAVE_BOOTS.get())

                .add(ModItem.MOURNING_GOLD_HELMET.get())
                .add(ModItem.MOURNING_GOLD_CHESTPLATE.get())
                .add(ModItem.MOURNING_GOLD_LEGGINGS.get())
                .add(ModItem.MOURNING_GOLD_BOOTS.get())

                .add(ModItem.VERDINITE_HELMET.get())
                .add(ModItem.VERDINITE_CHESTPLATE.get())
                .add(ModItem.VERDINITE_LEGGINGS.get())
                .add(ModItem.VERDINITE_BOOTS.get())

                .add(ModItem.VIVULITE_HELMET.get())
                .add(ModItem.VIVULITE_CHESTPLATE.get())
                .add(ModItem.VIVULITE_LEGGINGS.get())
                .add(ModItem.VIVULITE_BOOTS.get())

                .add(ModItem.FROSTITE_HELMET.get())
                .add(ModItem.FROSTITE_CHESTPLATE.get())
                .add(ModItem.FROSTITE_LEGGINGS.get())
                .add(ModItem.FROSTITE_BOOTS.get())

                .add(ModItem.COBALT_HELMET.get())
                .add(ModItem.COBALT_CHESTPLATE.get())
                .add(ModItem.COBALT_LEGGINGS.get())
                .add(ModItem.COBALT_BOOTS.get())

                .add(ModItem.BRIMTAN_HELMET.get())
                .add(ModItem.BRIMTAN_CHESTPLATE.get())
                .add(ModItem.BRIMTAN_LEGGINGS.get())
                .add(ModItem.BRIMTAN_BOOTS.get())

                .add(ModItem.PLATE_HELMET.get())
                .add(ModItem.PLATE_CHESTPLATE.get())
                .add(ModItem.PLATE_LEGGINGS.get())
                .add(ModItem.PLATE_BOOTS.get())

                .add(ModItem.SLIME_SHOES.get())
        ;

        // Armors
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET.get())
                .add(ModItem.MOURNING_GOLD_HELMET.get())
                .add(ModItem.VIVULITE_HELMET.get())
                .add(ModItem.VERDINITE_HELMET.get())
                .add(ModItem.FROSTITE_HELMET.get())
                .add(ModItem.COBALT_HELMET.get())
                .add(ModItem.BRIMTAN_HELMET.get())
                .add(ModItem.PLATE_HELMET.get())
        ;
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE.get())
                .add(ModItem.MOURNING_GOLD_CHESTPLATE.get())
                .add(ModItem.VIVULITE_CHESTPLATE.get())
                .add(ModItem.VERDINITE_CHESTPLATE.get())
                .add(ModItem.FROSTITE_CHESTPLATE.get())
                .add(ModItem.COBALT_CHESTPLATE.get())
                .add(ModItem.PLATE_CHESTPLATE.get())
                .add(ModItem.BRIMTAN_CHESTPLATE.get())
        ;
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItem.NECRO_WEAVE_LEGGINGS.get())
                .add(ModItem.MOURNING_GOLD_LEGGINGS.get())
                .add(ModItem.VIVULITE_LEGGINGS.get())
                .add(ModItem.VERDINITE_LEGGINGS.get())
                .add(ModItem.FROSTITE_LEGGINGS.get())
                .add(ModItem.COBALT_LEGGINGS.get())
                .add(ModItem.BRIMTAN_LEGGINGS.get())
                .add(ModItem.PLATE_LEGGINGS.get())
        ;
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItem.NECRO_WEAVE_BOOTS.get())
                .add(ModItem.MOURNING_GOLD_BOOTS.get())
                .add(ModItem.VIVULITE_BOOTS.get())
                .add(ModItem.VERDINITE_BOOTS.get())
                .add(ModItem.FROSTITE_BOOTS.get())
                .add(ModItem.COBALT_BOOTS.get())
                .add(ModItem.BRIMTAN_BOOTS.get())
                .add(ModItem.SLIME_SHOES.get())
                .add(ModItem.PLATE_BOOTS.get())
        ;

        // Tools
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItem.COBALT_AXE.get())
                .add(ModItem.VERDINITE_AXE.get())
                .add(ModItem.FROSTITE_AXE.get())
                .add(ModItem.VIVULITE_AXE.get())
                .add(ModItem.BRIMTAN_AXE.get())
                .add(ModItem.MOURNING_GOLD_AXE.get())
                .add(ModItem.OBSIDIAN_AXE.get())
        ;
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItem.COBALT_PICKAXE.get())
                .add(ModItem.VERDINITE_PICKAXE.get())
                .add(ModItem.FROSTITE_PICKAXE.get())
                .add(ModItem.VIVULITE_PICKAXE.get())
                .add(ModItem.BRIMTAN_PICKAXE.get())
                .add(ModItem.MOURNING_GOLD_PICKAXE.get())
                .add(ModItem.OBSIDIAN_PICKAXE.get())
        ;
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItem.COBALT_SWORD.get())
                .add(ModItem.VERDINITE_SWORD.get())
                .add(ModItem.FROSTITE_SWORD.get())
                .add(ModItem.VIVULITE_SWORD.get())
                .add(ModItem.BRIMTAN_SWORD.get())
                .add(ModItem.MOURNING_GOLD_SWORD.get())
                .add(ModItem.OBSIDIAN_SWORD.get())
        ;
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItem.COBALT_SHOVEL.get())
                .add(ModItem.VERDINITE_SHOVEL.get())
                .add(ModItem.FROSTITE_SHOVEL.get())
                .add(ModItem.VIVULITE_SHOVEL.get())
                .add(ModItem.BRIMTAN_SHOVEL.get())
                .add(ModItem.MOURNING_GOLD_SHOVEL.get())
                .add(ModItem.OBSIDIAN_SHOVEL.get())
        ;
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItem.COBALT_HOE.get())
                .add(ModItem.VERDINITE_HOE.get())
                .add(ModItem.FROSTITE_HOE.get())
                .add(ModItem.VIVULITE_HOE.get())
                .add(ModItem.BRIMTAN_HOE.get())
                .add(ModItem.MOURNING_GOLD_HOE.get())
                .add(ModItem.OBSIDIAN_HOE.get())
        ;

        // Enchanting Tags
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItem.COBALT_FISHING_ROD.get())
        ;
        getOrCreateTagBuilder(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItem.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItem.COPPER_BOW.get())
                .add(ModItem.IRON_BOW.get())
                .add(ModItem.DIAMOND_BOW.get())
                .add(ModItem.NETHERITE_BOW.get())
                .add(ModItem.ECHO_BOW.get())
                .add(ModItem.VERDINITE_BOW.get())
        ;
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItem.COBALT_SHIELD.get())
                .add(ModItem.COPPER_BOW.get())
                .add(ModItem.IRON_BOW.get())
                .add(ModItem.DIAMOND_BOW.get())
                .add(ModItem.NETHERITE_BOW.get())
                .add(ModItem.ECHO_BOW.get())
                .add(ModItem.VERDINITE_BOW.get())
                .add(ModItem.COBALT_FISHING_ROD.get())
                .add(ModItem.PALE_TRIDENT.get())
                .add(ModItem.TOME_OF_FANGS.get())
        ;
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.get().asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.get().asItem())
                .add(ModBlocks.WHITE_PUMPKIN.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.get().asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.get().asItem())
                .add(ModBlocks.WHITE_PUMPKIN.get().asItem())
        ;

        // Slabs
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get().asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get().asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.NACRE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.PALE_PRISMARINE_SLAB.get().asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get().asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.TOWER_BRICK_SLAB.get().asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get().asItem())

                .add(ModBlocks.HIELOSTONE_SLAB.get().asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.get().asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.get().asItem())
                .add(ModBlocks.COBBLEFROST_SLAB.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.EBONCORK_SLAB.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_SLAB.get().asItem())
        ;
        // Stairs
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.NACRE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.PALE_PRISMARINE_STAIRS.get().asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get().asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.TOWER_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get().asItem())

                .add(ModBlocks.HIELOSTONE_STAIRS.get().asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.get().asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.get().asItem())
                .add(ModBlocks.COBBLEFROST_STAIRS.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.EBONCORK_STAIRS.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_STAIRS.get().asItem())
        ;
        // Walls
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.get().asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.get().asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get().asItem())
                .add(ModBlocks.NACRE_BRICK_WALL.get().asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.get().asItem())
                .add(ModBlocks.PALE_PRISMARINE_WALL.get().asItem())
                .add(ModBlocks.TOWER_BRICK_WALL.get().asItem())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.get().asItem())

                .add(ModBlocks.HIELOSTONE_WALL.get().asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.get().asItem())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.get().asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.get().asItem())
                .add(ModBlocks.COBBLEFROST_WALL.get().asItem())
        ;
        // Fences
        getOrCreateTagBuilder(ItemTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.get().asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get().asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.EBONCORK_FENCE.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE.get().asItem())
        ;
        // Fence Gates
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.EBONCORK_FENCE_GATE.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get().asItem())
        ;
        // Pressure Plates + Buttons
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.EBONCORK_PRESSURE_PLATE.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.EBONCORK_BUTTON.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_BUTTON.get().asItem())
        ;
        // Doors + Trapdoors
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModItem.EBONCORK_DOOR.get())
                .add(ModItem.BLIGHTED_BIRCH_DOOR.get())
        ;
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EBONCORK_TRAPDOOR.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get().asItem())
        ;
        // Wood tags
        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.EBONCORK_PLANKS.get().asItem())
                .add(ModBlocks.BLIGHTED_BIRCH_PLANKS.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.EBONCORK.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.BLIGHTED_BIRCH_LOGS)
        ;
        getOrCreateTagBuilder(ItemTags.NON_FLAMMABLE_WOOD)
                .add(ModBlocks.EBONCORK.get().asItem())
                .add(ModBlocks.EBONCORK_PLANKS.get().asItem())
                .add(ModBlocks.EBONCORK_STAIRS.get().asItem())
                .add(ModBlocks.EBONCORK_SLAB.get().asItem())
                .add(ModBlocks.EBONCORK_FENCE.get().asItem())
                .add(ModBlocks.EBONCORK_FENCE_GATE.get().asItem())
                .add(ModBlocks.EBONCORK_TRAPDOOR.get().asItem())
                .add(ModItem.EBONCORK_DOOR.get())
        ;

        getOrCreateTagBuilder(ItemTags.SMELTS_TO_GLASS)
                .add(ModBlocks.QUICKSAND.get().asItem())
                .add(ModBlocks.RED_QUICKSAND.get().asItem())
        ;
        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlocks.BLIGHTED_BIRCH_SAPLING.get().asItem())
        ;

        //getOrCreateTagBuilder(ItemTags.HOGLIN_FOOD)
        //        .add(ModItem.TRUFFLE.get())
        //;
    }

    // Common tags.
    private void commonItemTag()
    {
        getOrCreateTagBuilder(ConventionalItemTags.STONES)
                .add(ModBlocks.HIELOSTONE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COBBLESTONES)
                .add(ModBlocks.COBBLEFROST.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.OBSIDIANS)
                .add(ModBlocks.GLOWING_OBSIDIAN.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.SPEAR_TOOLS)
                .add(ModItem.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(ModItem.COPPER_BOW.get())
                .add(ModItem.DIAMOND_BOW.get())
                .add(ModItem.ECHO_BOW.get())
                .add(ModItem.IRON_BOW.get())
                .add(ModItem.NETHERITE_BOW.get())
                .add(ModItem.VERDINITE_BOW.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.SHIELD_TOOLS)
                .add(ModItem.COBALT_SHIELD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FISHING_ROD_TOOLS)
                .add(ModItem.COBALT_FISHING_ROD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MELEE_WEAPON_TOOLS)
                .add(ModItem.MOURNING_GOLD_SWORD.get())
                .add(ModItem.OBSIDIAN_SWORD.get())
                .add(ModItem.COBALT_SWORD.get())
                .add(ModItem.VERDINITE_SWORD.get())
                .add(ModItem.VIVULITE_SWORD.get())
                .add(ModItem.BRIMTAN_SWORD.get())
                .add(ModItem.FROSTITE_SWORD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RANGED_WEAPON_TOOLS)
                .add(ModItem.COPPER_BOW.get())
                .add(ModItem.DIAMOND_BOW.get())
                .add(ModItem.ECHO_BOW.get())
                .add(ModItem.IRON_BOW.get())
                .add(ModItem.NETHERITE_BOW.get())
                .add(ModItem.VERDINITE_BOW.get())
                .add(ModItem.PALE_TRIDENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MINING_TOOL_TOOLS)
                .add(ModItem.MOURNING_GOLD_PICKAXE.get())
                .add(ModItem.OBSIDIAN_PICKAXE.get())
                .add(ModItem.COBALT_PICKAXE.get())
                .add(ModItem.VERDINITE_PICKAXE.get())
                .add(ModItem.VIVULITE_PICKAXE.get())
                .add(ModItem.BRIMTAN_PICKAXE.get())
                .add(ModItem.FROSTITE_PICKAXE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BRICKS)
                .add(ModItem.NACRE_BRICK.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GEMS)
                .add(ModItem.BLACK_EMERALD.get())
                .add(ModItem.VOID_DIAMOND.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.INGOTS)
                .add(ModItem.MOURNING_GOLD_INGOT.get())
                .add(ModItem.COBALT_INGOT.get())
                .add(ModItem.VERDINITE_INGOT.get())
                .add(ModItem.FROSTITE_INGOT.get())
                .add(ModItem.VIVULITE_INGOT.get())
                .add(ModItem.BRIMTAN_INGOT.get())
                .add(ModItem.PITCH_INGOT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.NUGGETS)
                .add(ModItem.BRIMTAN_NUGGET.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.ORES)
                .add(ModBlocks.COBALT_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get().asItem())
                .add(ModBlocks.VERDINITE_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_VERDINITE_ORE.get().asItem())
                .add(ModBlocks.FROSTITE_ORE.get().asItem())
                .add(ModBlocks.DEEPSLATE_VIVULITE_ORE.get().asItem())
                .add(ModBlocks.VIVULITE_ORE.get().asItem())
                .add(ModBlocks.BRIMTAN_ORE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_MATERIALS)
                .add(ModItem.RAW_COBALT.get())
                .add(ModItem.RAW_VERDINITE.get())
                .add(ModItem.RAW_VIVULITE.get())
                .add(ModItem.RAW_FROSTITE.get())
                .add(ModItem.BRIMTAN_CLUSTER.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.EMERALD_GEMS)
                .add(ModItem.BLACK_EMERALD.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.ANIMAL_FOODS)
                .addTag(ModTags.Items.GOLDEN_CHICKEN_FOOD)
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FOODS)
                .add(ModItem.MARSHMALLOW.get())
                .add(ModItem.ROASTED_MARSHMALLOW.get())
                .add(ModItem.TRUFFLE.get())
                .add(ModItem.TRUFFLE_POTATO_PUFF.get())
                .add(ModItem.TRUFFLE_OIL.get())
                .add(ModItem.FRUITCAKE_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS)
                .add(ModItem.POMEGRANATE.get())
                .add(ModItem.APPLE_OF_ENLIGHTENMENT.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BREAD_FOODS)
                .add(ModItem.LEVI_ROLL.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_FISH_FOODS)
                .add(ModItem.GUARDIAN_SLICE.get())
                .add(ModItem.ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.RAW_FISHES_FOODS)
                .add(ModItem.GUARDIAN_SLICE.get())
                .add(ModItem.ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISH_FOODS)
                .add(ModItem.COOKED_GUARDIAN_SLICE.get())
                .add(ModItem.COOKED_ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.COOKED_FISHES_FOODS)
                .add(ModItem.COOKED_GUARDIAN_SLICE.get())
                .add(ModItem.COOKED_ELDER_GUARDIAN_SLICE.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GOLDEN_FOODS)
                .add(Items.GLISTERING_MELON_SLICE)
        ;
        getOrCreateTagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
                .add(ModBlocks.BEEF_WELLINGTON.get().asItem())
                .add(ModBlocks.FRUITCAKE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.CHESTS)
                .add(ModBlocks.PERSONAL_CHEST.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GLASS_BLOCKS)
                .add(ModBlocks.SEA_GLASS.get().asItem())
                .add(ModBlocks.PALE_SEA_GLASS.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.GLASS_PANES)
                .add(ModBlocks.SEA_GLASS_PANE.get().asItem())
                .add(ModBlocks.PALE_SEA_GLASS_PANE.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.BUDS)
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get().asItem())
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get().asItem())
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.CLUSTERS)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
                .add(ModBlocks.BRIMTAN_BLOCK.get().asItem())
                .add(ModBlocks.BLACK_EMERALD_BLOCK.get().asItem())
                .add(ModBlocks.COBALT_BLOCK.get().asItem())
                .add(ModBlocks.FROSTITE_BLOCK.get().asItem())
                .add(ModBlocks.COCOA_BEAN_BLOCK.get().asItem())
                .add(ModBlocks.ROTTEN_FLESH_BLOCK.get().asItem())
                .add(ModBlocks.MOURNING_GOLD_BLOCK.get().asItem())
                .add(ModBlocks.NECRO_WEAVE_BLOCK.get().asItem())
                .add(ModBlocks.RAW_COBALT_BLOCK.get().asItem())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get().asItem())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get().asItem())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get().asItem())
                .add(ModBlocks.SUGAR_CANE_BLOCK.get().asItem())
                .add(ModBlocks.VERDINITE_BLOCK.get().asItem())
                .add(ModBlocks.VIVULITE_BLOCK.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_WOODS)
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get().asItem())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.NETHER_WART_CROPS)
                .add(ModItem.WARPED_WART.get())
        ;
        getOrCreateTagBuilder(ConventionalItemTags.MUSIC_DISCS)
                .add(ModItem.MUSIC_DISC_DIAPHRAGM.get())
        ;
        // Custom C
        getOrCreateTagBuilder(ModTags.Items.C_EGGS)
                .add(ModItem.GOLDEN_EGG.get())
        ;
        getOrCreateTagBuilder(ModTags.Items.C_RAW_BLOCKS)
                .add(ModBlocks.RAW_COBALT_BLOCK.get().asItem())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get().asItem())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get().asItem())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get().asItem())
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
