package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
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

                .add(ModItem.ANCIENT_ROSE_SEED)
                .add(ModItem.WARPED_WART)

                .addOptional(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "rice"))
                .addOptional(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "onion"))
                .addOptional(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "cabbage_seeds"))
                .addOptional(Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "tomato_seeds"))
        ;
        getOrCreateTagBuilder(ModTags.Items.BALLS)
                .add(ModItem.BALL)
                .add(ModItem.WHITE_BALL)
                .add(ModItem.LIGHT_GRAY_BALL)
                .add(ModItem.GRAY_BALL)
                .add(ModItem.BLACK_BALL)
                .add(ModItem.BROWN_BALL)
                .add(ModItem.RED_BALL)
                .add(ModItem.ORANGE_BALL)
                .add(ModItem.YELLOW_BALL)
                .add(ModItem.LIME_BALL)
                .add(ModItem.GREEN_BALL)
                .add(ModItem.CYAN_BALL)
                .add(ModItem.LIGHT_BLUE_BALL)
                .add(ModItem.BLUE_BALL)
                .add(ModItem.PURPLE_BALL)
                .add(ModItem.MAGENTA_BALL)
                .add(ModItem.PINK_BALL)
        ;
        getOrCreateTagBuilder(ModTags.Items.LUMENS)
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
                .addOptional(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "feldspar_lumen"))
        ;
        getOrCreateTagBuilder(ModTags.Items.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.ENTITY_MODELS)
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
        getOrCreateTagBuilder(ModTags.Items.DEFLECTS_BALLS)
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
                .addOptional(Identifier.of(Frontiers.BOUNTIFUL_FARES_ID, "leek"))
        ;
        getOrCreateTagBuilder(ModTags.Items.GLOWING_BRIMTAN_ITEMS)
                .add(ModItem.BRIMTAN_AXE)
                .add(ModItem.BRIMTAN_SWORD)
                .add(ModItem.BRIMTAN_PICKAXE)
                .add(ModItem.BRIMTAN_SHOVEL)
                .add(ModItem.BRIMTAN_HOE)
                .addOptional(Identifier.of(Frontiers.MOD_ID, "brimtan_knife"))

                .add(ModItem.BRIMTAN_HELMET)
                .add(ModItem.BRIMTAN_CHESTPLATE)
                .add(ModItem.BRIMTAN_LEGGINGS)
                .add(ModItem.BRIMTAN_BOOTS)
        ;
        getOrCreateTagBuilder(ModTags.Items.OFFHAND_PRIORITY_ITEM)
                .add(Items.SHIELD)
                .add(ModItem.COBALT_SHIELD)
        ;
    }

    // Vanilla tags.
    private void vanillaItemTag()
    {
        getOrCreateTagBuilder(ItemTags.ANVIL)
                .add(ModBlocks.VIVULITE_ANVIL.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "snow_dahlia"))
                .add(Identifier.of(Frontiers.MOD_ID, "fungal_daffodil"))
                .add(Identifier.of(Frontiers.MOD_ID, "crimcone"))
                .add(Identifier.of(Frontiers.MOD_ID, "experiwinkle"))

        ;
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"))
        ;
        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.COBBLEFROST.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.ARROWS)
                .add(ModItem.BOUNCY_ARROW)
                .add(ModItem.SUBZERO_ARROW)
                .add(ModItem.WARP_ARROW)
                .add(ModItem.DYNAMITE_ARROW)
                .add(ModItem.PRISMARINE_ARROW)
        ;

        // Trims
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItem.COBALT_INGOT)
                .add(ModItem.VERDINITE_INGOT)
                .add(ModItem.VIVULITE_INGOT)
                .add(ModItem.FROSTITE_INGOT)
                .add(ModItem.MOURNING_GOLD_INGOT)
                .add(ModItem.BRIMTAN_INGOT)
        ;
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
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
        ;

        // Armors
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.MOURNING_GOLD_HELMET)
                .add(ModItem.VIVULITE_HELMET)
                .add(ModItem.VERDINITE_HELMET)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.BRIMTAN_HELMET)
                .add(ModItem.PLATE_HELMET)
        ;
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.MOURNING_GOLD_CHESTPLATE)
                .add(ModItem.VIVULITE_CHESTPLATE)
                .add(ModItem.VERDINITE_CHESTPLATE)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.PLATE_CHESTPLATE)
                .add(ModItem.BRIMTAN_CHESTPLATE)
        ;
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.MOURNING_GOLD_LEGGINGS)
                .add(ModItem.VIVULITE_LEGGINGS)
                .add(ModItem.VERDINITE_LEGGINGS)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.COBALT_LEGGINGS)
                .add(ModItem.BRIMTAN_LEGGINGS)
        ;
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.MOURNING_GOLD_BOOTS)
                .add(ModItem.VIVULITE_BOOTS)
                .add(ModItem.VERDINITE_BOOTS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_BOOTS)
                .add(ModItem.BRIMTAN_BOOTS)
        ;

        // Tools
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItem.COBALT_AXE)
                .add(ModItem.VERDINITE_AXE)
                .add(ModItem.FROSTITE_AXE)
                .add(ModItem.VIVULITE_AXE)
                .add(ModItem.BRIMTAN_AXE)
                .add(ModItem.MOURNING_GOLD_AXE)
                .add(ModItem.OBSIDIAN_AXE)
        ;
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItem.COBALT_PICKAXE)
                .add(ModItem.VERDINITE_PICKAXE)
                .add(ModItem.FROSTITE_PICKAXE)
                .add(ModItem.VIVULITE_PICKAXE)
                .add(ModItem.BRIMTAN_PICKAXE)
                .add(ModItem.MOURNING_GOLD_PICKAXE)
                .add(ModItem.OBSIDIAN_PICKAXE)
        ;
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItem.COBALT_SWORD)
                .add(ModItem.VERDINITE_SWORD)
                .add(ModItem.FROSTITE_SWORD)
                .add(ModItem.VIVULITE_SWORD)
                .add(ModItem.BRIMTAN_SWORD)
                .add(ModItem.MOURNING_GOLD_SWORD)
                .add(ModItem.OBSIDIAN_SWORD)
        ;
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItem.COBALT_SHOVEL)
                .add(ModItem.VERDINITE_SHOVEL)
                .add(ModItem.FROSTITE_SHOVEL)
                .add(ModItem.VIVULITE_SHOVEL)
                .add(ModItem.BRIMTAN_SHOVEL)
                .add(ModItem.MOURNING_GOLD_SHOVEL)
                .add(ModItem.OBSIDIAN_SHOVEL)
        ;
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItem.COBALT_HOE)
                .add(ModItem.VERDINITE_HOE)
                .add(ModItem.FROSTITE_HOE)
                .add(ModItem.VIVULITE_HOE)
                .add(ModItem.BRIMTAN_HOE)
                .add(ModItem.MOURNING_GOLD_HOE)
                .add(ModItem.OBSIDIAN_HOE)
        ;

        // Enchanting Tags
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItem.COBALT_FISHING_ROD)
        ;
        getOrCreateTagBuilder(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItem.PALE_TRIDENT)
        ;
        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItem.VERDINITE_BOW)
        ;
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItem.COBALT_SHIELD)
                .add(ModItem.VERDINITE_BOW)
                .add(ModItem.COBALT_FISHING_ROD)
                .add(ModItem.PALE_TRIDENT)
                .add(ModItem.TOME_OF_FANGS)
        ;
        getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.asItem())
                .add(ModBlocks.WHITE_PUMPKIN.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .add(ModBlocks.CARVED_MELON.asItem())
                .add(ModBlocks.CARVED_GLISTERING_MELON.asItem())
                .add(ModBlocks.WHITE_PUMPKIN.asItem())
        ;

        getOrCreateTagBuilder(ItemTags.SLABS)
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

                .add(ModBlocks.HIELOSTONE_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.asItem())
                .add(ModBlocks.COBBLEFROST_SLAB.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.STAIRS)
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

                .add(ModBlocks.HIELOSTONE_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.asItem())
                .add(ModBlocks.COBBLEFROST_STAIRS.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.NACRE_BRICK_WALL.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.asItem())
                .add(ModBlocks.PALE_PRISMARINE_WALL.asItem())

                .add(ModBlocks.HIELOSTONE_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.asItem())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.asItem())
                .add(ModBlocks.COBBLEFROST_WALL.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE.asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.asItem())
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
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modItemTag();
        vanillaItemTag();
        fabricItemTag();
    }
}
