package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.block.custom.ExperiwinkleCropBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFModels;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

// generates block and item models.
public class ModModelProvider extends FabricModelProvider
{
    // Do super
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    // Generate block state models.
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ANCIENT_ROSE, ModBlocks.POTTED_ANCIENT_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ROSE, ModBlocks.POTTED_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.VIOLET_ROSE, ModBlocks.POTTED_VIOLET_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.ANCIENT_ROSE_CROP, BlockStateModelGenerator.TintType.NOT_TINTED, AncientRoseCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.ANCIENT_ROSE_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.VIOLET_ROSE_BUSH, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.SNOW_DAHLIA, ModBlocks.POTTED_SNOW_DAHLIA, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.FUNGAL_DAFFODIL, ModBlocks.POTTED_FUNGAL_DAFFODIL, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.CRIMCONE, ModBlocks.POTTED_CRIMCONE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.EXPERIWINKLE, ModBlocks.POTTED_EXPERIWINKLE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.EXPERIWINKLE_CROP, BlockStateModelGenerator.TintType.NOT_TINTED, ExperiwinkleCropBlock.AGE, 0, 1);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.BLIGHTED_BIRCH_SAPLING, ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerAmethyst(ModBlocks.CORRUPTED_AMETHYST_CLUSTER);
        blockStateModelGenerator.registerAmethyst(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);

        // Warped Wart
        blockStateModelGenerator.registerCrop(ModBlocks.WARPED_WART, Properties.AGE_3, 0, 1, 1, 2);

        // Curse Altar
        blockStateModelGenerator.registerSimpleState(ModBlocks.CURSE_ALTAR);

        // Phantasmic TNT
        blockStateModelGenerator.registerSingleton(ModBlocks.PHANTASMIC_TNT, TexturedModel.CUBE_BOTTOM_TOP);

        // Personal Chest
        blockStateModelGenerator.registerBuiltin(
                Identifier.of(Frontiers.MOD_ID, "block/personal_chest"), Blocks.COBBLED_DEEPSLATE)
                .includeWithoutItem(ModBlocks.PERSONAL_CHEST);

        // Melon-relateds
        TextureMap textureMapGlist = TextureMap.sideEnd(ModBlocks.GLISTERING_MELON);
        //blockStateModelGenerator.blockStateCollector.accept(
        //        BlockStateModelGenerator.createSingletonBlockState(ModBlocks.GLISTERING_MELON, ModelIds.getBlockModelId(ModBlocks.GLISTERING_MELON)));
        blockStateModelGenerator.registerSingleton(ModBlocks.GLISTERING_MELON, TexturedModel.CUBE_COLUMN);

        TextureMap textureMapMelon = TextureMap.sideEnd(Blocks.MELON);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.CARVED_MELON, textureMapMelon);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.CARVED_GLISTERING_MELON, textureMapGlist);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.JUNE_O_LANTERN, textureMapMelon);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.GLISTERING_JUNE_O_LANTERN, textureMapGlist);

        // White Pumpkin
        TextureMap textureWhitePump = TextureMap.sideEnd(ModBlocks.WHITE_PUMPKIN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.WHITE_PUMPKIN, textureWhitePump);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(ModBlocks.WHITE_JACK_O_LANTERN, textureWhitePump);

        // Crop Blocks
        blockStateModelGenerator.registerAxisRotated(ModBlocks.SUGAR_CANE_BLOCK, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);
        blockStateModelGenerator.registerSingleton(ModBlocks.COCOA_BEAN_BLOCK, TexturedModel.CUBE_COLUMN);

        // Blue Nether Bricks Group
        BlockStateModelGenerator.BlockTexturePool blueNetherBrickpool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLUE_NETHER_BRICKS);
        blueNetherBrickpool.stairs(ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        blueNetherBrickpool.slab(ModBlocks.BLUE_NETHER_BRICK_SLAB);
        blueNetherBrickpool.wall(ModBlocks.BLUE_NETHER_BRICK_WALL);
        blueNetherBrickpool.fence(ModBlocks.BLUE_NETHER_BRICK_FENCE);
        blueNetherBrickpool.fenceGate(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        // Purple Nether Bricks Group
        BlockStateModelGenerator.BlockTexturePool purpNetherBrickpool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PURPLE_NETHER_BRICKS);
        purpNetherBrickpool.stairs(ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        purpNetherBrickpool.slab(ModBlocks.PURPLE_NETHER_BRICK_SLAB);
        purpNetherBrickpool.wall(ModBlocks.PURPLE_NETHER_BRICK_WALL);
        purpNetherBrickpool.fence(ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        purpNetherBrickpool.fenceGate(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);
        // Red Nether Bricks addon (dummied out since it made unwanted .jsons :P)
        //BlockStateModelGenerator.BlockTexturePool rnbAdd = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        //rnbAdd.fence(ModBlocks.RED_NETHER_BRICK_FENCE);
        // Cragulstane Bricks Group
        BlockStateModelGenerator.BlockTexturePool cragStoneBrickpool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRAGULSTANE_BRICKS);
        cragStoneBrickpool.stairs(ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool.slab(ModBlocks.CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool.wall(ModBlocks.CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_CRAGULSTANE_BRICKS);
        // Brimmed Cragulstane Bricks Group
        BlockStateModelGenerator.BlockTexturePool cragStoneBrickpool2 = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS);
        cragStoneBrickpool2.stairs(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool2.slab(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool2.wall(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS);
        // Orange Cragulstane Bricks Group
        BlockStateModelGenerator.BlockTexturePool cragStoneBrickpool3 = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ORANGE_CRAGULSTANE_BRICKS);
        cragStoneBrickpool3.stairs(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool3.slab(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool3.wall(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS);
        // Tyrian Cragulstane Bricks Group
        BlockStateModelGenerator.BlockTexturePool cragStoneBrickpool4 = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS);
        cragStoneBrickpool4.stairs(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool4.slab(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool4.wall(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS);
        // Nacre Bricks Group
        BlockStateModelGenerator.BlockTexturePool nacreBlockpool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.NACRE_BRICKS);
        nacreBlockpool.stairs(ModBlocks.NACRE_BRICK_STAIRS);
        nacreBlockpool.slab(ModBlocks.NACRE_BRICK_SLAB);
        nacreBlockpool.wall(ModBlocks.NACRE_BRICK_WALL);
        // Pale Prismarine Group
        BlockStateModelGenerator.BlockTexturePool palePrisPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PALE_PRISMARINE);
        palePrisPool.stairs(ModBlocks.PALE_PRISMARINE_STAIRS);
        palePrisPool.slab(ModBlocks.PALE_PRISMARINE_SLAB);
        palePrisPool.wall(ModBlocks.PALE_PRISMARINE_WALL);
        // Pale Prismarine Brick Group
        BlockStateModelGenerator.BlockTexturePool palePrisBrickPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PALE_PRISMARINE_BRICKS);
        palePrisBrickPool.stairs(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        palePrisBrickPool.slab(ModBlocks.PALE_PRISMARINE_BRICK_SLAB);
        // Deep Pale Prismarine Group
        BlockStateModelGenerator.BlockTexturePool deepPalePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEEP_PALE_PRISMARINE);
        deepPalePool.stairs(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        deepPalePool.slab(ModBlocks.DEEP_PALE_PRISMARINE_SLAB);
        // Turtle Scute Bricks Group
        BlockStateModelGenerator.BlockTexturePool scutePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.TURTLE_SCUTE_BRICKS);
        scutePool.stairs(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        scutePool.slab(ModBlocks.TURTLE_SCUTE_BRICK_SLAB);
        scutePool.wall(ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        // Hielostone Group
        BlockStateModelGenerator.BlockTexturePool hielostoneG = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.HIELOSTONE);
        hielostoneG.stairs(ModBlocks.HIELOSTONE_STAIRS);
        hielostoneG.slab(ModBlocks.HIELOSTONE_SLAB);
        hielostoneG.wall(ModBlocks.HIELOSTONE_WALL);
        // Hielostone Bricks Group
        BlockStateModelGenerator.BlockTexturePool hielostoneBricksG = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.HIELOSTONE_BRICKS);
        hielostoneBricksG.stairs(ModBlocks.HIELOSTONE_BRICK_STAIRS);
        hielostoneBricksG.slab(ModBlocks.HIELOSTONE_BRICK_SLAB);
        hielostoneBricksG.wall(ModBlocks.HIELOSTONE_BRICK_WALL);
        // Hielostone Tiles Group
        BlockStateModelGenerator.BlockTexturePool hielostoneTilesG = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.HIELOSTONE_TILES);
        hielostoneTilesG.stairs(ModBlocks.HIELOSTONE_TILE_STAIRS);
        hielostoneTilesG.slab(ModBlocks.HIELOSTONE_TILE_SLAB);
        hielostoneTilesG.wall(ModBlocks.HIELOSTONE_TILE_WALL);
        // Hielostone Plates Group
        BlockStateModelGenerator.BlockTexturePool hielostonePlatesG = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.HIELOSTONE_PLATES);
        hielostonePlatesG.stairs(ModBlocks.HIELOSTONE_PLATE_STAIRS);
        hielostonePlatesG.slab(ModBlocks.HIELOSTONE_PLATE_SLAB);
        hielostonePlatesG.wall(ModBlocks.HIELOSTONE_PLATE_WALL);
        // Cobblefrost Group
        BlockStateModelGenerator.BlockTexturePool cobblefrostG = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COBBLEFROST);
        cobblefrostG.stairs(ModBlocks.COBBLEFROST_STAIRS);
        cobblefrostG.slab(ModBlocks.COBBLEFROST_SLAB);
        cobblefrostG.wall(ModBlocks.COBBLEFROST_WALL);

        // Lumens
        ModelHelper.registerLumen(ModBlocks.AMETHYST_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.COBALT_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.DIAMOND_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.EMERALD_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.FROSTITE_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.QUARTZ_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.REDSTONE_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.VERDINITE_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.VIVULITE_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.BRIMTAN_LUMEN, blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.ECHO_LUMEN, blockStateModelGenerator);

        // Blighted Birch
        blockStateModelGenerator.registerLog(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG)
                .log(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG).wood(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG)
                .log(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG).wood(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG)
                .log(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG).wood(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.registerSingleton(ModBlocks.BLIGHTED_BIRCH_LEAVES, TexturedModel.LEAVES);

        // Monster Bakery
        ModelHelper.registerMonsterBakery(ModBlocks.MONSTER_BAKERY, blockStateModelGenerator);

        // Panes
        blockStateModelGenerator.registerGlassPane(ModBlocks.SEA_GLASS, ModBlocks.SEA_GLASS_PANE);
        blockStateModelGenerator.registerGlassPane(ModBlocks.PALE_SEA_GLASS, ModBlocks.PALE_SEA_GLASS_PANE);

        // Anvils
        BlockModels.registerVivuliteAnvil(ModBlocks.VIVULITE_ANVIL, blockStateModelGenerator);

        // "Cakes" (did in resources too lazy)
        //BlockModels.registerCakeBlock(ModBlocks.BEEF_WELLINGTON, ModBlocks.BEEF_WELLINGTON.asItem(), blockStateModelGenerator);

        // Basic blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_COBALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FROSTITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_FROSTITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VERDINITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_VERDINITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VERDINITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_VERDINITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VIVULITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_VIVULITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VIVULITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_VIVULITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRIMTAN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRIMTAN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.QUICKSAND);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RED_QUICKSAND);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBONCORK_PLANKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EBONCORK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLIGHTED_BIRCH_PLANKS);

        // SPAWN EGGS BECAUSE APPARENTLY THIS IS HOW YOU DO IT
        blockStateModelGenerator.registerParentedItemModel(ModItem.CRAWLER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItem.JUNGLE_SPIDER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.blockModels(blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        for (Item item : Registries.ITEM)
        {
            if (item instanceof ArmorItem armorItem)
            {
                ModelHelper.registerArmorWithFrontiersTrims(armorItem, itemModelGenerator);
            }
        }

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_CHESTPLATE);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_LEGGINGS);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_BOOTS);

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_CHESTPLATE);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_LEGGINGS);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_BOOTS);

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_CHESTPLATE);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_LEGGINGS);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_BOOTS);

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_CHESTPLATE);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_LEGGINGS);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_BOOTS);

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_CHESTPLATE);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_LEGGINGS);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_BOOTS);

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_HELMET);
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_CHESTPLATE);


        itemModelGenerator.register(ModItem.CHEST_KEY, Models.GENERATED);

        // Handheld
        itemModelGenerator.register(ModItem.VERDINITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VERDINITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VERDINITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VERDINITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VERDINITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(FDItem.VERDINITE_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VIVULITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VIVULITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VIVULITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VIVULITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItem.VIVULITE_HOE, Models.HANDHELD);
        itemModelGenerator.register(FDItem.VIVULITE_KNIFE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.BRIMTAN_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.BRIMTAN_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.BRIMTAN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItem.BRIMTAN_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItem.BRIMTAN_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.FROST_BONE, Models.HANDHELD);
        itemModelGenerator.register(FDItem.BRIMTAN_KNIFE, Models.HANDHELD);

        // Generated
        itemModelGenerator.register(ModItem.PITCH_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOWER_KEY_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOWER_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItem.VOID_PEARL, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_CLUSTER, Models.GENERATED);
        itemModelGenerator.register(ModItem.VIVULITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItem.RAW_VIVULITE, Models.GENERATED);
        itemModelGenerator.register(ModItem.GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.ELDER_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.COOKED_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.COOKED_ELDER_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.PALE_PRISMARINE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOTEM_OF_AVARICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.VOID_DIAMOND, Models.GENERATED);
        itemModelGenerator.register(ModItem.WARP_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItem.BOUNCY_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItem.SUBZERO_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItem.DYNAMITE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItem.PRISMARINE_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItem.TRUFFLE_OIL, Models.GENERATED);
        itemModelGenerator.register(ModItem.TRUFFLE_POTATO_PUFF, Models.GENERATED);
        //itemModelGenerator.register(ModItem.PALE_TRIDENT, Models.GENERATED);
        itemModelGenerator.register(ModItem.ELDER_GUARDIAN_SPINE, Models.GENERATED);
        itemModelGenerator.register(ModItem.SNOW_MELT, Models.GENERATED);
        //itemModelGenerator.register(ModItem.EXPERIWINKLE_BULB, Models.GENERATED);
        itemModelGenerator.register(ModItem.MESSAGE_IN_A_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BOTTLED_MESSAGE, Models.GENERATED);
        itemModelGenerator.register(ModItem.SPAWNER_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItem.GOLDEN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItem.SOUL, Models.GENERATED);
        itemModelGenerator.register(ModItem.POMEGRANATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.MANA_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItem.WITCH_HAT, Models.GENERATED);

        itemModelGenerator.register(ModItem.BOUNCY_ARROW_ARROWHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItem.SUBZERO_ARROW_ARROWHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItem.WARP_ARROW_ARROWHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItem.SPECTRAL_ARROW_ARROWHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItem.DYNAMITE_ARROW_ARROWHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItem.PRISMARINE_ARROW_ARROWHEAD, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.SPIRIT_CANDLE.asItem(), Models.GENERATED);

        itemModelGenerator.register(ModItem.BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.WHITE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.LIGHT_GRAY_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.GRAY_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.BLACK_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.BROWN_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.RED_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.ORANGE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.YELLOW_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.LIME_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.GREEN_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.CYAN_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.LIGHT_BLUE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.BLUE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.PURPLE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.MAGENTA_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItem.PINK_BALL, Models.GENERATED);

        itemModelGenerator.register(ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_SWORD, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_SHOVEL, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_AXE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_PICKAXE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_HOE, Models.GENERATED);

        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItem.BRIMTAN_SHELL_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItem.COBALT_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItem.VERDINITE_HORSE_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ModItem.VIVULITE_HORSE_ARMOR, Models.GENERATED);

        itemModelGenerator.register(ModItem.MUSIC_DISC_DIAPHRAGM, Models.GENERATED);
        itemModelGenerator.register(FDItem.TRUFFLE_PASTA, Models.GENERATED);
        itemModelGenerator.register(FDItem.FRIED_GOLDEN_EGG, Models.GENERATED);
        itemModelGenerator.register(FDItem.BRIMTAN_SHELL_KNIFE, Models.GENERATED);
        //itemModelGenerator.register(ModItem.WARPED_WART, Models.GENERATED);

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.itemModels(itemModelGenerator);
        }
    }
}
