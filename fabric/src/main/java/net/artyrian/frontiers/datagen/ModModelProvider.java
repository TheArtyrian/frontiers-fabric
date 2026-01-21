package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.block.custom.ExperiwinkleCropBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFModels;
import net.artyrian.frontiers.compat.dyemods.DyeModModels;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.client.*;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

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
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator)
    {
        blockStateModelGenerator.createPlant(ModBlocks.ANCIENT_ROSE, ModBlocks.POTTED_ANCIENT_ROSE, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.ROSE, ModBlocks.POTTED_ROSE, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.VIOLET_ROSE, ModBlocks.POTTED_VIOLET_ROSE, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(ModBlocks.ANCIENT_ROSE_CROP, BlockModelGenerators.TintState.NOT_TINTED, AncientRoseCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createDoublePlant(ModBlocks.ANCIENT_ROSE_BUSH, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createDoublePlant(ModBlocks.VIOLET_ROSE_BUSH, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.SNOW_DAHLIA, ModBlocks.POTTED_SNOW_DAHLIA, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.FUNGAL_DAFFODIL, ModBlocks.POTTED_FUNGAL_DAFFODIL, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.CRIMCONE, ModBlocks.POTTED_CRIMCONE, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.EXPERIWINKLE, ModBlocks.POTTED_EXPERIWINKLE, BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(ModBlocks.EXPERIWINKLE_CROP, BlockModelGenerators.TintState.NOT_TINTED, ExperiwinkleCropBlock.AGE, 0, 1);
        blockStateModelGenerator.createPlant(ModBlocks.BLIGHTED_BIRCH_SAPLING, ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        blockStateModelGenerator.createAmethystCluster(ModBlocks.CORRUPTED_AMETHYST_CLUSTER);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);

        // Warped Wart
        blockStateModelGenerator.createCropBlock(ModBlocks.WARPED_WART, BlockStateProperties.AGE_3, 0, 1, 1, 2);

        // Curse Altar
        blockStateModelGenerator.createNonTemplateModelBlock(ModBlocks.CURSE_ALTAR);

        // Phantasmic TNT
        blockStateModelGenerator.createTrivialBlock(ModBlocks.PHANTASMIC_TNT, TexturedModel.CUBE_TOP_BOTTOM);

        // Personal Chest
        blockStateModelGenerator.blockEntityModels(
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/personal_chest"), Blocks.COBBLED_DEEPSLATE)
                .createWithoutBlockItem(ModBlocks.PERSONAL_CHEST);

        // Melon-relateds
        TextureMapping textureMapGlist = TextureMapping.column(ModBlocks.GLISTERING_MELON);
        //blockStateModelGenerator.blockStateCollector.accept(
        //        BlockStateModelGenerator.createSingletonBlockState(ModBlocks.GLISTERING_MELON, ModelIds.getBlockModelId(ModBlocks.GLISTERING_MELON)));
        blockStateModelGenerator.createTrivialBlock(ModBlocks.GLISTERING_MELON, TexturedModel.COLUMN);

        TextureMapping textureMapMelon = TextureMapping.column(Blocks.MELON);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.CARVED_MELON, textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.CARVED_GLISTERING_MELON, textureMapGlist);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.JUNE_O_LANTERN, textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.GLISTERING_JUNE_O_LANTERN, textureMapGlist);

        // White Pumpkin
        TextureMapping textureWhitePump = TextureMapping.column(ModBlocks.WHITE_PUMPKIN);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.WHITE_PUMPKIN, textureWhitePump);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.WHITE_JACK_O_LANTERN, textureWhitePump);

        // Crop Blocks
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.SUGAR_CANE_BLOCK, TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.COCOA_BEAN_BLOCK, TexturedModel.COLUMN);

        // Blue Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider blueNetherBrickpool = blockStateModelGenerator.family(ModBlocks.BLUE_NETHER_BRICKS);
        blueNetherBrickpool.stairs(ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        blueNetherBrickpool.slab(ModBlocks.BLUE_NETHER_BRICK_SLAB);
        blueNetherBrickpool.wall(ModBlocks.BLUE_NETHER_BRICK_WALL);
        blueNetherBrickpool.fence(ModBlocks.BLUE_NETHER_BRICK_FENCE);
        blueNetherBrickpool.fenceGate(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_BLUE_NETHER_BRICKS);
        // Purple Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider purpNetherBrickpool = blockStateModelGenerator.family(ModBlocks.PURPLE_NETHER_BRICKS);
        purpNetherBrickpool.stairs(ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        purpNetherBrickpool.slab(ModBlocks.PURPLE_NETHER_BRICK_SLAB);
        purpNetherBrickpool.wall(ModBlocks.PURPLE_NETHER_BRICK_WALL);
        purpNetherBrickpool.fence(ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        purpNetherBrickpool.fenceGate(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);
        // Red Nether Bricks addon (dummied out since it made unwanted .jsons :P)
        //BlockStateModelGenerator.BlockTexturePool rnbAdd = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        //rnbAdd.fence(ModBlocks.RED_NETHER_BRICK_FENCE);
        // Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool = blockStateModelGenerator.family(ModBlocks.CRAGULSTANE_BRICKS);
        cragStoneBrickpool.stairs(ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool.slab(ModBlocks.CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool.wall(ModBlocks.CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_CRAGULSTANE_BRICKS);
        // Brimmed Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool2 = blockStateModelGenerator.family(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS);
        cragStoneBrickpool2.stairs(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool2.slab(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool2.wall(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS);
        // Orange Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool3 = blockStateModelGenerator.family(ModBlocks.ORANGE_CRAGULSTANE_BRICKS);
        cragStoneBrickpool3.stairs(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool3.slab(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool3.wall(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS);
        // Tyrian Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool4 = blockStateModelGenerator.family(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS);
        cragStoneBrickpool4.stairs(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS);
        cragStoneBrickpool4.slab(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB);
        cragStoneBrickpool4.wall(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS);
        // Nacre Bricks Group
        BlockModelGenerators.BlockFamilyProvider nacreBlockpool = blockStateModelGenerator.family(ModBlocks.NACRE_BRICKS);
        nacreBlockpool.stairs(ModBlocks.NACRE_BRICK_STAIRS);
        nacreBlockpool.slab(ModBlocks.NACRE_BRICK_SLAB);
        nacreBlockpool.wall(ModBlocks.NACRE_BRICK_WALL);
        // Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider palePrisPool = blockStateModelGenerator.family(ModBlocks.PALE_PRISMARINE);
        palePrisPool.stairs(ModBlocks.PALE_PRISMARINE_STAIRS);
        palePrisPool.slab(ModBlocks.PALE_PRISMARINE_SLAB);
        palePrisPool.wall(ModBlocks.PALE_PRISMARINE_WALL);
        // Pale Prismarine Brick Group
        BlockModelGenerators.BlockFamilyProvider palePrisBrickPool = blockStateModelGenerator.family(ModBlocks.PALE_PRISMARINE_BRICKS);
        palePrisBrickPool.stairs(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        palePrisBrickPool.slab(ModBlocks.PALE_PRISMARINE_BRICK_SLAB);
        // Deep Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider deepPalePool = blockStateModelGenerator.family(ModBlocks.DEEP_PALE_PRISMARINE);
        deepPalePool.stairs(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        deepPalePool.slab(ModBlocks.DEEP_PALE_PRISMARINE_SLAB);
        // Turtle Scute Bricks Group
        BlockModelGenerators.BlockFamilyProvider scutePool = blockStateModelGenerator.family(ModBlocks.TURTLE_SCUTE_BRICKS);
        scutePool.stairs(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        scutePool.slab(ModBlocks.TURTLE_SCUTE_BRICK_SLAB);
        scutePool.wall(ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        // Eboncork Group
        BlockModelGenerators.BlockFamilyProvider eboncorkPool = blockStateModelGenerator.family(ModBlocks.EBONCORK_PLANKS);
        eboncorkPool.stairs(ModBlocks.EBONCORK_STAIRS);
        eboncorkPool.slab(ModBlocks.EBONCORK_SLAB);
        eboncorkPool.fence(ModBlocks.EBONCORK_FENCE);
        eboncorkPool.fenceGate(ModBlocks.EBONCORK_FENCE_GATE);
        eboncorkPool.pressurePlate(ModBlocks.EBONCORK_PRESSURE_PLATE);
        eboncorkPool.button(ModBlocks.EBONCORK_BUTTON);
        blockStateModelGenerator.createDoor(ModBlocks.EBONCORK_DOOR);
        blockStateModelGenerator.createOrientableTrapdoor(ModBlocks.EBONCORK_TRAPDOOR);
        // Blighted Birch Group
        BlockModelGenerators.BlockFamilyProvider bBirchPool = blockStateModelGenerator.family(ModBlocks.BLIGHTED_BIRCH_PLANKS);
        bBirchPool.stairs(ModBlocks.BLIGHTED_BIRCH_STAIRS);
        bBirchPool.slab(ModBlocks.BLIGHTED_BIRCH_SLAB);
        bBirchPool.fence(ModBlocks.BLIGHTED_BIRCH_FENCE);
        bBirchPool.fenceGate(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE);
        bBirchPool.pressurePlate(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE);
        bBirchPool.button(ModBlocks.BLIGHTED_BIRCH_BUTTON);
        blockStateModelGenerator.createDoor(ModBlocks.BLIGHTED_BIRCH_DOOR);
        blockStateModelGenerator.createOrientableTrapdoor(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR);

        // Hielostone Group
        BlockModelGenerators.BlockFamilyProvider hielostoneG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE);
        hielostoneG.stairs(ModBlocks.HIELOSTONE_STAIRS);
        hielostoneG.slab(ModBlocks.HIELOSTONE_SLAB);
        hielostoneG.wall(ModBlocks.HIELOSTONE_WALL);
        // Hielostone Bricks Group
        BlockModelGenerators.BlockFamilyProvider hielostoneBricksG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_BRICKS);
        hielostoneBricksG.stairs(ModBlocks.HIELOSTONE_BRICK_STAIRS);
        hielostoneBricksG.slab(ModBlocks.HIELOSTONE_BRICK_SLAB);
        hielostoneBricksG.wall(ModBlocks.HIELOSTONE_BRICK_WALL);
        // Hielostone Tiles Group
        BlockModelGenerators.BlockFamilyProvider hielostoneTilesG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_TILES);
        hielostoneTilesG.stairs(ModBlocks.HIELOSTONE_TILE_STAIRS);
        hielostoneTilesG.slab(ModBlocks.HIELOSTONE_TILE_SLAB);
        hielostoneTilesG.wall(ModBlocks.HIELOSTONE_TILE_WALL);
        // Hielostone Plates Group
        BlockModelGenerators.BlockFamilyProvider hielostonePlatesG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_PLATES);
        hielostonePlatesG.stairs(ModBlocks.HIELOSTONE_PLATE_STAIRS);
        hielostonePlatesG.slab(ModBlocks.HIELOSTONE_PLATE_SLAB);
        hielostonePlatesG.wall(ModBlocks.HIELOSTONE_PLATE_WALL);
        // Cobblefrost Group
        BlockModelGenerators.BlockFamilyProvider cobblefrostG = blockStateModelGenerator.family(ModBlocks.COBBLEFROST);
        cobblefrostG.stairs(ModBlocks.COBBLEFROST_STAIRS);
        cobblefrostG.slab(ModBlocks.COBBLEFROST_SLAB);
        cobblefrostG.wall(ModBlocks.COBBLEFROST_WALL);
        // Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider towerBrickG = blockStateModelGenerator.family(ModBlocks.TOWER_BRICKS);
        towerBrickG.stairs(ModBlocks.TOWER_BRICK_STAIRS);
        towerBrickG.slab(ModBlocks.TOWER_BRICK_SLAB);
        towerBrickG.wall(ModBlocks.TOWER_BRICK_WALL);
        // Mossy Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider mossTowerBrickG = blockStateModelGenerator.family(ModBlocks.MOSSY_TOWER_BRICKS);
        mossTowerBrickG.stairs(ModBlocks.MOSSY_TOWER_BRICK_STAIRS);
        mossTowerBrickG.slab(ModBlocks.MOSSY_TOWER_BRICK_SLAB);
        mossTowerBrickG.wall(ModBlocks.MOSSY_TOWER_BRICK_WALL);

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
        blockStateModelGenerator.woodProvider(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG)
                .logWithHorizontal(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG).wood(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG)
                .logWithHorizontal(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG).wood(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG)
                .logWithHorizontal(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG).wood(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.BLIGHTED_BIRCH_LEAVES, TexturedModel.LEAVES);

        // Monster Bakery
        ModelHelper.registerMonsterBakery(ModBlocks.MONSTER_BAKERY, blockStateModelGenerator);

        // Tower Watcher
        ModelHelper.registerTowerWatcher(ModBlocks.TOWER_WATCHER, blockStateModelGenerator);

        // Panes
        blockStateModelGenerator.createGlassBlocks(ModBlocks.SEA_GLASS, ModBlocks.SEA_GLASS_PANE);
        blockStateModelGenerator.createGlassBlocks(ModBlocks.PALE_SEA_GLASS, ModBlocks.PALE_SEA_GLASS_PANE);

        // Anvils
        BlockModels.registerVivuliteAnvil(ModBlocks.VIVULITE_ANVIL, blockStateModelGenerator);

        // Iron Bars
        BlockModels.registerIronBarLike(ModBlocks.COBALT_GRILLES, blockStateModelGenerator);

        // Carpets
        BlockModels.registerCarpet(ModBlocks.NECRO_RUG, blockStateModelGenerator);

        // Mushroom Blocks
        ModelTemplates.SINGLE_FACE.create(
                TextureMapping.getBlockTexture(ModBlocks.FUNGAL_DAFFODIL_BLOCK, "_inside"),
                TextureMapping.defaultTexture(TextureMapping.getBlockTexture(ModBlocks.FUNGAL_DAFFODIL_BLOCK, "_inside")),
                blockStateModelGenerator.modelOutput
        );
        ModelHelper.registerCustomMushroomBlock(
                ModBlocks.FUNGAL_DAFFODIL_BLOCK,
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/fungal_daffodil_block_inside"),
                blockStateModelGenerator
        );


        // "Cakes" (did in resources too lazy)
        //BlockModels.registerCakeBlock(ModBlocks.BEEF_WELLINGTON, ModBlocks.BEEF_WELLINGTON.asItem(), blockStateModelGenerator);

        blockStateModelGenerator.createMultiface(ModBlocks.SLIME_TRAIL);

        // Basic blocks
        blockStateModelGenerator.createTrivialCube(ModBlocks.MOURNING_GOLD_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_COBALT_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.FROSTITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_FROSTITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.VERDINITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_VERDINITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.VERDINITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_VERDINITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.VIVULITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_VIVULITE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.VIVULITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_VIVULITE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_EMERALD_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_EMERALD_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BRIMTAN_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.BRIMTAN_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NECRO_WEAVE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.QUICKSAND);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RED_QUICKSAND);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockStateModelGenerator.createTrivialCube(ModBlocks.EBONCORK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.AESTHENOSTONE);

        // SPAWN EGGS BECAUSE APPARENTLY THIS IS HOW YOU DO IT
        blockStateModelGenerator.delegateItemModel(ModItem.CRAWLER_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.JUNGLE_SPIDER_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.PUMPKIN_GOLEM_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.CROW_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.GOLDEN_CHICKEN_SPAWN_EGG, ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.blockModels(blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator)
    {
        for (Item item : BuiltInRegistries.ITEM)
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

        itemModelGenerator.generateFlatItem(ModItem.CHEST_KEY, ModelTemplates.FLAT_ITEM);

        // Handheld
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VERDINITE_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VIVULITE_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.FROST_BONE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_KNIFE, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Generated
        itemModelGenerator.generateFlatItem(ModItem.PITCH_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOWER_KEY_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOWER_KEY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VOID_PEARL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_CLUSTER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.RAW_VIVULITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.GUARDIAN_SLICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.ELDER_GUARDIAN_SLICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.COOKED_GUARDIAN_SLICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.COOKED_ELDER_GUARDIAN_SLICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PALE_PRISMARINE_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOTEM_OF_AVARICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VOID_DIAMOND, ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.REINFORCED_SHEARS, Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.BLACK_EMERALD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WARP_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SUBZERO_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.DYNAMITE_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PRISMARINE_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TRUFFLE_OIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TRUFFLE_POTATO_PUFF, ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.PALE_TRIDENT, Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.ELDER_GUARDIAN_SPINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SNOW_MELT, ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.EXPERIWINKLE_BULB, Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.MESSAGE_IN_A_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOTTLED_MESSAGE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SPAWNER_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.GOLDEN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SOUL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.POMEGRANATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.MANA_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WITCH_HAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.FRUITCAKE_SLICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.HARDENED_SLIME, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SUBZERO_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WARP_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SPECTRAL_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.DYNAMITE_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PRISMARINE_ARROW_ARROWHEAD, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.SPIRIT_CANDLE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_BALL, ModelTemplates.FLAT_ITEM);

        for (Item ball : ModItem.COLOR_BALLS.values())
        {
            itemModelGenerator.generateFlatItem(ball, ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_SWORD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_SHOVEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_AXE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_PICKAXE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_HOE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.COBALT_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.MUSIC_DISC_DIAPHRAGM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BAIT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.TRUFFLE_PASTA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.FRIED_GOLDEN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_SHELL_KNIFE, ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.WARPED_WART, Models.GENERATED);

        // MANUAL DATA GEN
        // Slime Bulb
        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModBlocks.SLIME_BULB.asItem()),
                new TextureMapping().put(TextureSlot.LAYER0, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/slime_bulb_stage_3")),
                itemModelGenerator.output
        );

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.itemModels(itemModelGenerator);
            DyeModModels.itemModels(itemModelGenerator);
        }
    }
}
