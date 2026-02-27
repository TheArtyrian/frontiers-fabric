package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.datagen.compat.BFModels;
import net.artyrian.frontiers.datagen.compat.DyeModModels;
import net.artyrian.frontiers.definition.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.definition.block.custom.ExperiwinkleCropBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
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

import java.util.function.Supplier;

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
        blockStateModelGenerator.createPlant(ModBlocks.ANCIENT_ROSE.get(), ModBlocks.POTTED_ANCIENT_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.ROSE.get(), ModBlocks.POTTED_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.VIOLET_ROSE.get(), ModBlocks.POTTED_VIOLET_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(ModBlocks.ANCIENT_ROSE_CROP.get(), BlockModelGenerators.TintState.NOT_TINTED, AncientRoseCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createDoublePlant(ModBlocks.ANCIENT_ROSE_BUSH.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createDoublePlant(ModBlocks.VIOLET_ROSE_BUSH.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.SNOW_DAHLIA.get(), ModBlocks.POTTED_SNOW_DAHLIA.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.FUNGAL_DAFFODIL.get(), ModBlocks.POTTED_FUNGAL_DAFFODIL.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.CRIMCONE.get(), ModBlocks.POTTED_CRIMCONE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(ModBlocks.EXPERIWINKLE.get(), ModBlocks.POTTED_EXPERIWINKLE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(ModBlocks.EXPERIWINKLE_CROP.get(), BlockModelGenerators.TintState.NOT_TINTED, ExperiwinkleCropBlock.AGE, 0, 1);
        blockStateModelGenerator.createPlant(ModBlocks.BLIGHTED_BIRCH_SAPLING.get(), ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get(), BlockModelGenerators.TintState.NOT_TINTED);

        blockStateModelGenerator.createAmethystCluster(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get());
        blockStateModelGenerator.createAmethystCluster(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        blockStateModelGenerator.createAmethystCluster(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        blockStateModelGenerator.createAmethystCluster(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());

        // Warped Wart
        blockStateModelGenerator.createCropBlock(ModBlocks.WARPED_WART.get(), BlockStateProperties.AGE_3, 0, 1, 1, 2);

        // Curse Altar
        blockStateModelGenerator.createNonTemplateModelBlock(ModBlocks.CURSE_ALTAR.get());

        // Phantasmic TNT
        blockStateModelGenerator.createTrivialBlock(ModBlocks.PHANTASMIC_TNT.get(), TexturedModel.CUBE_TOP_BOTTOM);

        // Personal Chest
        blockStateModelGenerator.blockEntityModels(
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/personal_chest"), Blocks.COBBLED_DEEPSLATE)
                .createWithoutBlockItem(ModBlocks.PERSONAL_CHEST.get());

        // Melon-relateds
        TextureMapping textureMapGlist = TextureMapping.column(ModBlocks.GLISTERING_MELON.get());
        //blockStateModelGenerator.blockStateCollector.accept(
        //        BlockStateModelGenerator.createSingletonBlockState(ModBlocks.GLISTERING_MELON.get(), ModelIds.getBlockModelId(ModBlocks.GLISTERING_MELON)));
        blockStateModelGenerator.createTrivialBlock(ModBlocks.GLISTERING_MELON.get(), TexturedModel.COLUMN);

        TextureMapping textureMapMelon = TextureMapping.column(Blocks.MELON);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.CARVED_MELON.get(), textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.CARVED_GLISTERING_MELON.get(), textureMapGlist);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.JUNE_O_LANTERN.get(), textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.GLISTERING_JUNE_O_LANTERN.get(), textureMapGlist);

        // White Pumpkin
        TextureMapping textureWhitePump = TextureMapping.column(ModBlocks.WHITE_PUMPKIN.get());
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.WHITE_PUMPKIN.get(), textureWhitePump);
        blockStateModelGenerator.createPumpkinVariant(ModBlocks.WHITE_JACK_O_LANTERN.get(), textureWhitePump);

        // Crop Blocks
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(ModBlocks.SUGAR_CANE_BLOCK.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.COCOA_BEAN_BLOCK.get(), TexturedModel.COLUMN);

        // Blue Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider blueNetherBrickpool = blockStateModelGenerator.family(ModBlocks.BLUE_NETHER_BRICKS.get());
        blueNetherBrickpool.stairs(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        blueNetherBrickpool.slab(ModBlocks.BLUE_NETHER_BRICK_SLAB.get());
        blueNetherBrickpool.wall(ModBlocks.BLUE_NETHER_BRICK_WALL.get());
        blueNetherBrickpool.fence(ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        blueNetherBrickpool.fenceGate(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get());
        // Purple Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider purpNetherBrickpool = blockStateModelGenerator.family(ModBlocks.PURPLE_NETHER_BRICKS.get());
        purpNetherBrickpool.stairs(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        purpNetherBrickpool.slab(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get());
        purpNetherBrickpool.wall(ModBlocks.PURPLE_NETHER_BRICK_WALL.get());
        purpNetherBrickpool.fence(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        purpNetherBrickpool.fenceGate(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());
        // Red Nether Bricks addon (dummied out since it made unwanted .jsons :P)
        //BlockStateModelGenerator.BlockTexturePool rnbAdd = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        //rnbAdd.fence(ModBlocks.RED_NETHER_BRICK_FENCE);
        // Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool = blockStateModelGenerator.family(ModBlocks.CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool.stairs(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool.slab(ModBlocks.CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool.wall(ModBlocks.CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get());
        // Brimmed Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool2 = blockStateModelGenerator.family(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool2.stairs(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool2.slab(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool2.wall(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());
        // Orange Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool3 = blockStateModelGenerator.family(ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool3.stairs(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool3.slab(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool3.wall(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());
        // Tyrian Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool4 = blockStateModelGenerator.family(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool4.stairs(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool4.slab(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool4.wall(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());
        // Nacre Bricks Group
        BlockModelGenerators.BlockFamilyProvider nacreBlockpool = blockStateModelGenerator.family(ModBlocks.NACRE_BRICKS.get());
        nacreBlockpool.stairs(ModBlocks.NACRE_BRICK_STAIRS.get());
        nacreBlockpool.slab(ModBlocks.NACRE_BRICK_SLAB.get());
        nacreBlockpool.wall(ModBlocks.NACRE_BRICK_WALL.get());
        // Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider palePrisPool = blockStateModelGenerator.family(ModBlocks.PALE_PRISMARINE.get());
        palePrisPool.stairs(ModBlocks.PALE_PRISMARINE_STAIRS.get());
        palePrisPool.slab(ModBlocks.PALE_PRISMARINE_SLAB.get());
        palePrisPool.wall(ModBlocks.PALE_PRISMARINE_WALL.get());
        // Pale Prismarine Brick Group
        BlockModelGenerators.BlockFamilyProvider palePrisBrickPool = blockStateModelGenerator.family(ModBlocks.PALE_PRISMARINE_BRICKS.get());
        palePrisBrickPool.stairs(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        palePrisBrickPool.slab(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get());
        // Deep Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider deepPalePool = blockStateModelGenerator.family(ModBlocks.DEEP_PALE_PRISMARINE.get());
        deepPalePool.stairs(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        deepPalePool.slab(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get());
        // Turtle Scute Bricks Group
        BlockModelGenerators.BlockFamilyProvider scutePool = blockStateModelGenerator.family(ModBlocks.TURTLE_SCUTE_BRICKS.get());
        scutePool.stairs(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        scutePool.slab(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get());
        scutePool.wall(ModBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        // Eboncork Group
        BlockModelGenerators.BlockFamilyProvider eboncorkPool = blockStateModelGenerator.family(ModBlocks.EBONCORK_PLANKS.get());
        eboncorkPool.stairs(ModBlocks.EBONCORK_STAIRS.get());
        eboncorkPool.slab(ModBlocks.EBONCORK_SLAB.get());
        eboncorkPool.fence(ModBlocks.EBONCORK_FENCE.get());
        eboncorkPool.fenceGate(ModBlocks.EBONCORK_FENCE_GATE.get());
        eboncorkPool.pressurePlate(ModBlocks.EBONCORK_PRESSURE_PLATE.get());
        eboncorkPool.button(ModBlocks.EBONCORK_BUTTON.get());
        blockStateModelGenerator.createDoor(ModBlocks.EBONCORK_DOOR.get());
        blockStateModelGenerator.createOrientableTrapdoor(ModBlocks.EBONCORK_TRAPDOOR.get());
        // Blighted Birch Group
        BlockModelGenerators.BlockFamilyProvider bBirchPool = blockStateModelGenerator.family(ModBlocks.BLIGHTED_BIRCH_PLANKS.get());
        bBirchPool.stairs(ModBlocks.BLIGHTED_BIRCH_STAIRS.get());
        bBirchPool.slab(ModBlocks.BLIGHTED_BIRCH_SLAB.get());
        bBirchPool.fence(ModBlocks.BLIGHTED_BIRCH_FENCE.get());
        bBirchPool.fenceGate(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        bBirchPool.pressurePlate(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        bBirchPool.button(ModBlocks.BLIGHTED_BIRCH_BUTTON.get());
        blockStateModelGenerator.createDoor(ModBlocks.BLIGHTED_BIRCH_DOOR.get());
        blockStateModelGenerator.createOrientableTrapdoor(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());

        // Hielostone Group
        BlockModelGenerators.BlockFamilyProvider hielostoneG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE.get());
        hielostoneG.stairs(ModBlocks.HIELOSTONE_STAIRS.get());
        hielostoneG.slab(ModBlocks.HIELOSTONE_SLAB.get());
        hielostoneG.wall(ModBlocks.HIELOSTONE_WALL.get());
        // Hielostone Bricks Group
        BlockModelGenerators.BlockFamilyProvider hielostoneBricksG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_BRICKS.get());
        hielostoneBricksG.stairs(ModBlocks.HIELOSTONE_BRICK_STAIRS.get());
        hielostoneBricksG.slab(ModBlocks.HIELOSTONE_BRICK_SLAB.get());
        hielostoneBricksG.wall(ModBlocks.HIELOSTONE_BRICK_WALL.get());
        // Hielostone Tiles Group
        BlockModelGenerators.BlockFamilyProvider hielostoneTilesG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_TILES.get());
        hielostoneTilesG.stairs(ModBlocks.HIELOSTONE_TILE_STAIRS.get());
        hielostoneTilesG.slab(ModBlocks.HIELOSTONE_TILE_SLAB.get());
        hielostoneTilesG.wall(ModBlocks.HIELOSTONE_TILE_WALL.get());
        // Hielostone Plates Group
        BlockModelGenerators.BlockFamilyProvider hielostonePlatesG = blockStateModelGenerator.family(ModBlocks.HIELOSTONE_PLATES.get());
        hielostonePlatesG.stairs(ModBlocks.HIELOSTONE_PLATE_STAIRS.get());
        hielostonePlatesG.slab(ModBlocks.HIELOSTONE_PLATE_SLAB.get());
        hielostonePlatesG.wall(ModBlocks.HIELOSTONE_PLATE_WALL.get());
        // Cobblefrost Group
        BlockModelGenerators.BlockFamilyProvider cobblefrostG = blockStateModelGenerator.family(ModBlocks.COBBLEFROST.get());
        cobblefrostG.stairs(ModBlocks.COBBLEFROST_STAIRS.get());
        cobblefrostG.slab(ModBlocks.COBBLEFROST_SLAB.get());
        cobblefrostG.wall(ModBlocks.COBBLEFROST_WALL.get());
        // Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider towerBrickG = blockStateModelGenerator.family(ModBlocks.TOWER_BRICKS.get());
        towerBrickG.stairs(ModBlocks.TOWER_BRICK_STAIRS.get());
        towerBrickG.slab(ModBlocks.TOWER_BRICK_SLAB.get());
        towerBrickG.wall(ModBlocks.TOWER_BRICK_WALL.get());
        // Mossy Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider mossTowerBrickG = blockStateModelGenerator.family(ModBlocks.MOSSY_TOWER_BRICKS.get());
        mossTowerBrickG.stairs(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        mossTowerBrickG.slab(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get());
        mossTowerBrickG.wall(ModBlocks.MOSSY_TOWER_BRICK_WALL.get());

        // Lumens
        ModelHelper.registerLumen(ModBlocks.AMETHYST_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.COBALT_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.DIAMOND_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.EMERALD_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.FROSTITE_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.QUARTZ_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.REDSTONE_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.VERDINITE_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.VIVULITE_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.BRIMTAN_LUMEN.get(), blockStateModelGenerator);
        ModelHelper.registerLumen(ModBlocks.ECHO_LUMEN.get(), blockStateModelGenerator);

        // Blighted Birch
        blockStateModelGenerator.woodProvider(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get()).wood(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.woodProvider(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get()).wood(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get()).wood(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.createTrivialBlock(ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), TexturedModel.LEAVES);

        // Monster Bakery
        ModelHelper.registerMonsterBakery(ModBlocks.MONSTER_BAKERY.get(), blockStateModelGenerator);

        // Tower Blocks
        ModelHelper.registerTowerWatcher(ModBlocks.TOWER_WATCHER.get(), blockStateModelGenerator);
        ModelHelper.registerTowerSpawner(ModBlocks.TOWER_SPAWNER.get(), blockStateModelGenerator);
        ModelHelper.registerTowerHeart(ModBlocks.TOWER_HEART.get(), blockStateModelGenerator);
        ModelHelper.registerTowerVault(ModBlocks.TOWER_TREASURE_VAULT.get(), blockStateModelGenerator);

        // Panes
        blockStateModelGenerator.createGlassBlocks(ModBlocks.SEA_GLASS.get(), ModBlocks.SEA_GLASS_PANE.get());
        blockStateModelGenerator.createGlassBlocks(ModBlocks.PALE_SEA_GLASS.get(), ModBlocks.PALE_SEA_GLASS_PANE.get());

        // Anvils
        BlockModels.registerVivuliteAnvil(ModBlocks.VIVULITE_ANVIL.get(), blockStateModelGenerator);

        // Iron Bars
        BlockModels.registerIronBarLike(ModBlocks.COBALT_GRILLES.get(), blockStateModelGenerator);

        // Carpets
        BlockModels.registerCarpet(ModBlocks.NECRO_RUG.get(), blockStateModelGenerator);

        // Mushroom Blocks
        ModelTemplates.SINGLE_FACE.create(
                TextureMapping.getBlockTexture(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "_inside"),
                TextureMapping.defaultTexture(TextureMapping.getBlockTexture(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "_inside")),
                blockStateModelGenerator.modelOutput
        );
        ModelHelper.registerCustomMushroomBlock(
                ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/fungal_daffodil_block_inside"),
                blockStateModelGenerator
        );


        // "Cakes" (did in resources too lazy)
        //BlockModels.registerCakeBlock(ModBlocks.BEEF_WELLINGTON.get(), ModBlocks.BEEF_WELLINGTON.asItem().get(), blockStateModelGenerator);

        blockStateModelGenerator.createMultiface(ModBlocks.SLIME_TRAIL.get());

        // Basic blocks
        blockStateModelGenerator.createTrivialCube(ModBlocks.MOURNING_GOLD_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_COBALT_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.FROSTITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_FROSTITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.VERDINITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_VERDINITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.VERDINITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_VERDINITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.VIVULITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_VIVULITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.VIVULITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_VIVULITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_EMERALD_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.BLACK_EMERALD_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.BRIMTAN_ORE.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.BRIMTAN_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.NECRO_WEAVE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.QUICKSAND.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.RED_QUICKSAND.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CRACKED_RED_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.CHISELED_RED_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.EBONCORK.get());
        blockStateModelGenerator.createTrivialCube(ModBlocks.AESTHENOSTONE.get());

        // SPAWN EGGS BECAUSE APPARENTLY THIS IS HOW YOU DO IT
        blockStateModelGenerator.delegateItemModel(ModItem.CRAWLER_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.JUNGLE_SPIDER_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.PUMPKIN_GOLEM_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.CROW_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(ModItem.GOLDEN_CHICKEN_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));

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

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_CHESTPLATE.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_LEGGINGS.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_BOOTS.get());

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_CHESTPLATE.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_LEGGINGS.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VIVULITE_BOOTS.get());

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_CHESTPLATE.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_LEGGINGS.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.VERDINITE_BOOTS.get());

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_CHESTPLATE.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_LEGGINGS.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_BOOTS.get());

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_CHESTPLATE.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_LEGGINGS.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_BOOTS.get());

        //itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_HELMET.get());
        //itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_CHESTPLATE.get());

        itemModelGenerator.generateFlatItem(ModItem.CHEST_KEY.get(), ModelTemplates.FLAT_ITEM);

        // Handheld
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VERDINITE_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VIVULITE_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.FROST_BONE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // Generated
        itemModelGenerator.generateFlatItem(ModItem.PITCH_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOWER_KEY_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOWER_KEY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VOID_PEARL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_CLUSTER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.RAW_VIVULITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.ELDER_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.COOKED_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.COOKED_ELDER_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PALE_PRISMARINE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TOTEM_OF_AVARICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VOID_DIAMOND.get(), ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.REINFORCED_SHEARS.get(), Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.BLACK_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WARP_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SUBZERO_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.DYNAMITE_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PRISMARINE_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TRUFFLE_OIL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.TRUFFLE_POTATO_PUFF.get(), ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.PALE_TRIDENT.get(), Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.ELDER_GUARDIAN_SPINE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SNOW_MELT.get(), ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.EXPERIWINKLE_BULB.get(), Models.GENERATED);
        itemModelGenerator.generateFlatItem(ModItem.MESSAGE_IN_A_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOTTLED_MESSAGE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SPAWNER_CHUNK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.GOLDEN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SOUL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.POMEGRANATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.MANA_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WITCH_HAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.FRUITCAKE_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.HARDENED_SLIME.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SUBZERO_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.WARP_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.SPIRIT_CANDLE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BOUNCY_BALL.get(), ModelTemplates.FLAT_ITEM);

        for (Supplier<Item> ball : ModItem.COLOR_BALLS.values())
        {
            itemModelGenerator.generateFlatItem(ball.get(), ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_SWORD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_AXE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_HOE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BRIMTAN_SHELL_BOOTS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.COBALT_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VERDINITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.VIVULITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItem.MUSIC_DISC_DIAPHRAGM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItem.BAIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.TRUFFLE_PASTA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.FRIED_GOLDEN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_SHELL_KNIFE.get(), ModelTemplates.FLAT_ITEM);
        //itemModelGenerator.register(ModItem.WARPED_WART.get(), Models.GENERATED);

        // MANUAL DATA GEN
        // Slime Bulb
        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(ModBlocks.SLIME_BULB.get().asItem()),
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
