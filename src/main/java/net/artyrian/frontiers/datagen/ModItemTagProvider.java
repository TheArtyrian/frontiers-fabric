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
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
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

                .add(FDItem.RICE)
                .add(FDItem.ONION)
                .add(FDItem.CABBAGE_SEEDS)
                .add(FDItem.TOMATO_SEEDS)
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
                .add(ModBlocks.ECHO_LUMEN.asItem())

                // Compat items
                .add(BFBlock.FELDSPAR_LUMEN.asItem())
        ;
        getOrCreateTagBuilder(ModTags.Items.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.asItem())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.asItem())
        ;
    }

    // Vanilla tags.
    private void vanillaItemTag()
    {
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose"))
                .add(Identifier.of(Frontiers.MOD_ID, "snow_dahlia"))
                .add(Identifier.of(Frontiers.MOD_ID, "fungal_daffodil"))
                .add(Identifier.of(Frontiers.MOD_ID, "crimcone"))

        ;
        getOrCreateTagBuilder(ItemTags.TALL_FLOWERS)
                .add(Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"))
                .add(Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"))
        ;

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.COBALT_LEGGINGS)
                .add(ModItem.COBALT_BOOTS)
                .add(ModItem.PLATE_HELMET)
                .add(ModItem.PLATE_CHESTPLATE)
        ;

        // Armors
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItem.NECRO_WEAVE_HELMET)
                .add(ModItem.FROSTITE_HELMET)
                .add(ModItem.COBALT_HELMET)
                .add(ModItem.PLATE_HELMET)
        ;
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItem.NECRO_WEAVE_CHESTPLATE)
                .add(ModItem.FROSTITE_CHESTPLATE)
                .add(ModItem.COBALT_CHESTPLATE)
                .add(ModItem.PLATE_CHESTPLATE)
        ;
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItem.NECRO_WEAVE_LEGGINGS)
                .add(ModItem.FROSTITE_LEGGINGS)
                .add(ModItem.COBALT_LEGGINGS)
        ;
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItem.NECRO_WEAVE_BOOTS)
                .add(ModItem.FROSTITE_BOOTS)
                .add(ModItem.COBALT_BOOTS)
        ;

        // Tools
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItem.COBALT_AXE)
                .add(ModItem.VERDINITE_AXE)
                .add(ModItem.FROSTITE_AXE)
                .add(ModItem.VIVULITE_AXE)
                .add(ModItem.MOURNING_GOLD_AXE)
                .add(ModItem.OBSIDIAN_AXE)
        ;
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItem.COBALT_PICKAXE)
                .add(ModItem.VERDINITE_PICKAXE)
                .add(ModItem.FROSTITE_PICKAXE)
                .add(ModItem.VIVULITE_PICKAXE)
                .add(ModItem.MOURNING_GOLD_PICKAXE)
                .add(ModItem.OBSIDIAN_PICKAXE)
        ;
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItem.COBALT_SWORD)
                .add(ModItem.VERDINITE_SWORD)
                .add(ModItem.FROSTITE_SWORD)
                .add(ModItem.VIVULITE_SWORD)
                .add(ModItem.MOURNING_GOLD_SWORD)
                .add(ModItem.OBSIDIAN_SWORD)
        ;
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItem.COBALT_SHOVEL)
                .add(ModItem.VERDINITE_SHOVEL)
                .add(ModItem.FROSTITE_SHOVEL)
                .add(ModItem.VIVULITE_SHOVEL)
                .add(ModItem.MOURNING_GOLD_SHOVEL)
                .add(ModItem.OBSIDIAN_SHOVEL)
        ;
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItem.COBALT_HOE)
                .add(ModItem.VERDINITE_HOE)
                .add(ModItem.FROSTITE_HOE)
                .add(ModItem.VIVULITE_HOE)
                .add(ModItem.MOURNING_GOLD_HOE)
                .add(ModItem.OBSIDIAN_HOE)
        ;

        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.asItem())
                .add(ModBlocks.NACRE_BRICK_SLAB.asItem())
                .add(ModBlocks.PALE_PRISMARINE_SLAB.asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.asItem())
                .add(ModBlocks.NACRE_BRICK_STAIRS.asItem())
                .add(ModBlocks.PALE_PRISMARINE_STAIRS.asItem())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.asItem())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.asItem())
        ;
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.asItem())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.asItem())
                .add(ModBlocks.NACRE_BRICK_WALL.asItem())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.asItem())
                .add(ModBlocks.PALE_PRISMARINE_WALL.asItem())
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
