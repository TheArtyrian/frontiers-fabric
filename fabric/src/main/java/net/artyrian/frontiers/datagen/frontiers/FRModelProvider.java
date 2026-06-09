package net.artyrian.frontiers.datagen.frontiers;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.datagen.BlockModelHelper;
import net.artyrian.frontiers.datagen.ItemModelHelper;
import net.artyrian.frontiers.datagen.ddye.DDyeModels;
import net.artyrian.frontiers.definition.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.definition.block.custom.ExperiwinkleCropBlock;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Supplier;

// generates block and item models.
public class FRModelProvider extends FabricModelProvider
{
    // Do super
    public FRModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    // Generate block state models.
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator)
    {
        blockStateModelGenerator.createPlant(FRBlocks.ANCIENT_ROSE.get(), FRBlocks.POTTED_ANCIENT_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.ROSE.get(), FRBlocks.POTTED_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.VIOLET_ROSE.get(), FRBlocks.POTTED_VIOLET_ROSE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(FRBlocks.ANCIENT_ROSE_CROP.get(), BlockModelGenerators.TintState.NOT_TINTED, AncientRoseCropBlock.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createDoublePlant(FRBlocks.ANCIENT_ROSE_BUSH.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createDoublePlant(FRBlocks.VIOLET_ROSE_BUSH.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.SNOW_DAHLIA.get(), FRBlocks.POTTED_SNOW_DAHLIA.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.FUNGAL_DAFFODIL.get(), FRBlocks.POTTED_FUNGAL_DAFFODIL.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.CRIMCONE.get(), FRBlocks.POTTED_CRIMCONE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createPlant(FRBlocks.EXPERIWINKLE.get(), FRBlocks.POTTED_EXPERIWINKLE.get(), BlockModelGenerators.TintState.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(FRBlocks.EXPERIWINKLE_CROP.get(), BlockModelGenerators.TintState.NOT_TINTED, ExperiwinkleCropBlock.AGE, 0, 1);
        blockStateModelGenerator.createPlant(FRBlocks.BLIGHTED_BIRCH_SAPLING.get(), FRBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get(), BlockModelGenerators.TintState.NOT_TINTED);

        blockStateModelGenerator.createAmethystCluster(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get());
        blockStateModelGenerator.createAmethystCluster(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get());
        blockStateModelGenerator.createAmethystCluster(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get());
        blockStateModelGenerator.createAmethystCluster(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get());

        // Warped Wart
        blockStateModelGenerator.createCropBlock(FRBlocks.WARPED_WART.get(), BlockStateProperties.AGE_3, 0, 1, 1, 2);

        // Curse Altar
        blockStateModelGenerator.createNonTemplateModelBlock(FRBlocks.CURSE_ALTAR.get());

        // Phantasmic TNT
        blockStateModelGenerator.createTrivialBlock(FRBlocks.PHANTASMIC_TNT.get(), TexturedModel.CUBE_TOP_BOTTOM);

        // Personal Chest
        blockStateModelGenerator.blockEntityModels(
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/personal_chest"), Blocks.COBBLED_DEEPSLATE)
                .createWithoutBlockItem(FRBlocks.PERSONAL_CHEST.get());

        // Melon-relateds
        TextureMapping textureMapGlist = TextureMapping.column(FRBlocks.GLISTERING_MELON.get());
        //blockStateModelGenerator.blockStateCollector.accept(
        //        BlockStateModelGenerator.createSingletonBlockState(ModBlocks.GLISTERING_MELON.get(), ModelIds.getBlockModelId(ModBlocks.GLISTERING_MELON)));
        blockStateModelGenerator.createTrivialBlock(FRBlocks.GLISTERING_MELON.get(), TexturedModel.COLUMN);

        TextureMapping textureMapMelon = TextureMapping.column(Blocks.MELON);
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.CARVED_MELON.get(), textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.CARVED_GLISTERING_MELON.get(), textureMapGlist);
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.JUNE_O_LANTERN.get(), textureMapMelon);
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.GLISTERING_JUNE_O_LANTERN.get(), textureMapGlist);

        // White Pumpkin
        TextureMapping textureWhitePump = TextureMapping.column(FRBlocks.WHITE_PUMPKIN.get());
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.WHITE_PUMPKIN.get(), textureWhitePump);
        blockStateModelGenerator.createPumpkinVariant(FRBlocks.WHITE_JACK_O_LANTERN.get(), textureWhitePump);

        // Crop Blocks
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(FRBlocks.SUGAR_CANE_BLOCK.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createTrivialBlock(FRBlocks.COCOA_BEAN_BLOCK.get(), TexturedModel.COLUMN);

        // Blue Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider blueNetherBrickpool = blockStateModelGenerator.family(FRBlocks.BLUE_NETHER_BRICKS.get());
        blueNetherBrickpool.stairs(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get());
        blueNetherBrickpool.slab(FRBlocks.BLUE_NETHER_BRICK_SLAB.get());
        blueNetherBrickpool.wall(FRBlocks.BLUE_NETHER_BRICK_WALL.get());
        blueNetherBrickpool.fence(FRBlocks.BLUE_NETHER_BRICK_FENCE.get());
        blueNetherBrickpool.fenceGate(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get());
        // Purple Nether Bricks Group
        BlockModelGenerators.BlockFamilyProvider purpNetherBrickpool = blockStateModelGenerator.family(FRBlocks.PURPLE_NETHER_BRICKS.get());
        purpNetherBrickpool.stairs(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get());
        purpNetherBrickpool.slab(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get());
        purpNetherBrickpool.wall(FRBlocks.PURPLE_NETHER_BRICK_WALL.get());
        purpNetherBrickpool.fence(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        purpNetherBrickpool.fenceGate(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get());
        // Red Nether Bricks addon (dummied out since it made unwanted .jsons :P)
        //BlockStateModelGenerator.BlockTexturePool rnbAdd = blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.RED_NETHER_BRICKS);
        //rnbAdd.fence(ModBlocks.RED_NETHER_BRICK_FENCE);
        // Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool = blockStateModelGenerator.family(FRBlocks.CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool.stairs(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool.slab(FRBlocks.CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool.wall(FRBlocks.CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get());
        // Brimmed Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool2 = blockStateModelGenerator.family(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool2.stairs(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool2.slab(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool2.wall(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get());
        // Orange Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool3 = blockStateModelGenerator.family(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool3.stairs(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool3.slab(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool3.wall(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get());
        // Tyrian Cragulstane Bricks Group
        BlockModelGenerators.BlockFamilyProvider cragStoneBrickpool4 = blockStateModelGenerator.family(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get());
        cragStoneBrickpool4.stairs(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get());
        cragStoneBrickpool4.slab(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get());
        cragStoneBrickpool4.wall(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get());
        // Nacre Bricks Group
        BlockModelGenerators.BlockFamilyProvider nacreBlockpool = blockStateModelGenerator.family(FRBlocks.NACRE_BRICKS.get());
        nacreBlockpool.stairs(FRBlocks.NACRE_BRICK_STAIRS.get());
        nacreBlockpool.slab(FRBlocks.NACRE_BRICK_SLAB.get());
        nacreBlockpool.wall(FRBlocks.NACRE_BRICK_WALL.get());
        // Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider palePrisPool = blockStateModelGenerator.family(FRBlocks.PALE_PRISMARINE.get());
        palePrisPool.stairs(FRBlocks.PALE_PRISMARINE_STAIRS.get());
        palePrisPool.slab(FRBlocks.PALE_PRISMARINE_SLAB.get());
        palePrisPool.wall(FRBlocks.PALE_PRISMARINE_WALL.get());
        // Pale Prismarine Brick Group
        BlockModelGenerators.BlockFamilyProvider palePrisBrickPool = blockStateModelGenerator.family(FRBlocks.PALE_PRISMARINE_BRICKS.get());
        palePrisBrickPool.stairs(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get());
        palePrisBrickPool.slab(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get());
        // Deep Pale Prismarine Group
        BlockModelGenerators.BlockFamilyProvider deepPalePool = blockStateModelGenerator.family(FRBlocks.DEEP_PALE_PRISMARINE.get());
        deepPalePool.stairs(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get());
        deepPalePool.slab(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get());
        // Turtle Scute Bricks Group
        BlockModelGenerators.BlockFamilyProvider scutePool = blockStateModelGenerator.family(FRBlocks.TURTLE_SCUTE_BRICKS.get());
        scutePool.stairs(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get());
        scutePool.slab(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get());
        scutePool.wall(FRBlocks.TURTLE_SCUTE_BRICK_WALL.get());

        // Eboncork Group
        BlockModelGenerators.BlockFamilyProvider eboncorkPool = blockStateModelGenerator.family(FRBlocks.EBONCORK_PLANKS.get());
        eboncorkPool.stairs(FRBlocks.EBONCORK_STAIRS.get());
        eboncorkPool.slab(FRBlocks.EBONCORK_SLAB.get());
        eboncorkPool.fence(FRBlocks.EBONCORK_FENCE.get());
        eboncorkPool.fenceGate(FRBlocks.EBONCORK_FENCE_GATE.get());
        eboncorkPool.pressurePlate(FRBlocks.EBONCORK_PRESSURE_PLATE.get());
        eboncorkPool.button(FRBlocks.EBONCORK_BUTTON.get());
        blockStateModelGenerator.createDoor(FRBlocks.EBONCORK_DOOR.get());
        blockStateModelGenerator.createOrientableTrapdoor(FRBlocks.EBONCORK_TRAPDOOR.get());
        // Blighted Birch Group
        BlockModelGenerators.BlockFamilyProvider bBirchPool = blockStateModelGenerator.family(FRBlocks.BLIGHTED_BIRCH_PLANKS.get());
        bBirchPool.stairs(FRBlocks.BLIGHTED_BIRCH_STAIRS.get());
        bBirchPool.slab(FRBlocks.BLIGHTED_BIRCH_SLAB.get());
        bBirchPool.fence(FRBlocks.BLIGHTED_BIRCH_FENCE.get());
        bBirchPool.fenceGate(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get());
        bBirchPool.pressurePlate(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get());
        bBirchPool.button(FRBlocks.BLIGHTED_BIRCH_BUTTON.get());
        blockStateModelGenerator.createDoor(FRBlocks.BLIGHTED_BIRCH_DOOR.get());
        blockStateModelGenerator.createOrientableTrapdoor(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());

        // Hielostone Group
        BlockModelGenerators.BlockFamilyProvider hielostoneG = blockStateModelGenerator.family(FRBlocks.HIELOSTONE.get());
        hielostoneG.stairs(FRBlocks.HIELOSTONE_STAIRS.get());
        hielostoneG.slab(FRBlocks.HIELOSTONE_SLAB.get());
        hielostoneG.wall(FRBlocks.HIELOSTONE_WALL.get());
        // Hielostone Bricks Group
        BlockModelGenerators.BlockFamilyProvider hielostoneBricksG = blockStateModelGenerator.family(FRBlocks.HIELOSTONE_BRICKS.get());
        hielostoneBricksG.stairs(FRBlocks.HIELOSTONE_BRICK_STAIRS.get());
        hielostoneBricksG.slab(FRBlocks.HIELOSTONE_BRICK_SLAB.get());
        hielostoneBricksG.wall(FRBlocks.HIELOSTONE_BRICK_WALL.get());
        // Hielostone Tiles Group
        BlockModelGenerators.BlockFamilyProvider hielostoneTilesG = blockStateModelGenerator.family(FRBlocks.HIELOSTONE_TILES.get());
        hielostoneTilesG.stairs(FRBlocks.HIELOSTONE_TILE_STAIRS.get());
        hielostoneTilesG.slab(FRBlocks.HIELOSTONE_TILE_SLAB.get());
        hielostoneTilesG.wall(FRBlocks.HIELOSTONE_TILE_WALL.get());
        // Hielostone Plates Group
        BlockModelGenerators.BlockFamilyProvider hielostonePlatesG = blockStateModelGenerator.family(FRBlocks.HIELOSTONE_PLATES.get());
        hielostonePlatesG.stairs(FRBlocks.HIELOSTONE_PLATE_STAIRS.get());
        hielostonePlatesG.slab(FRBlocks.HIELOSTONE_PLATE_SLAB.get());
        hielostonePlatesG.wall(FRBlocks.HIELOSTONE_PLATE_WALL.get());
        // Cobblefrost Group
        BlockModelGenerators.BlockFamilyProvider cobblefrostG = blockStateModelGenerator.family(FRBlocks.COBBLEFROST.get());
        cobblefrostG.stairs(FRBlocks.COBBLEFROST_STAIRS.get());
        cobblefrostG.slab(FRBlocks.COBBLEFROST_SLAB.get());
        cobblefrostG.wall(FRBlocks.COBBLEFROST_WALL.get());
        // Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider towerBrickG = blockStateModelGenerator.family(FRBlocks.TOWER_BRICKS.get());
        towerBrickG.stairs(FRBlocks.TOWER_BRICK_STAIRS.get());
        towerBrickG.slab(FRBlocks.TOWER_BRICK_SLAB.get());
        towerBrickG.wall(FRBlocks.TOWER_BRICK_WALL.get());
        // Mossy Tower Bricks Group
        BlockModelGenerators.BlockFamilyProvider mossTowerBrickG = blockStateModelGenerator.family(FRBlocks.MOSSY_TOWER_BRICKS.get());
        mossTowerBrickG.stairs(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get());
        mossTowerBrickG.slab(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get());
        mossTowerBrickG.wall(FRBlocks.MOSSY_TOWER_BRICK_WALL.get());
        // Quicksand + Crusted Quicksand Group
        blockStateModelGenerator.createTrivialCube(FRBlocks.QUICKSAND.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRUSTED_QUICKSAND.get());
        BlockModelGenerators.BlockFamilyProvider crustQuicksandG = blockStateModelGenerator.family(FRBlocks.CRUSTY_SAND_BRICKS.get());
        crustQuicksandG.stairs(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get());
        crustQuicksandG.slab(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get());
        crustQuicksandG.wall(FRBlocks.CRUSTY_SAND_BRICK_WALL.get());
        // Quicksand + Crusted Red Quicksand Group
        blockStateModelGenerator.createTrivialCube(FRBlocks.RED_QUICKSAND.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRUSTED_RED_QUICKSAND.get());
        BlockModelGenerators.BlockFamilyProvider crustRedQuicksandG = blockStateModelGenerator.family(FRBlocks.CRUSTY_RED_SAND_BRICKS.get());
        crustRedQuicksandG.stairs(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get());
        crustRedQuicksandG.slab(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get());
        crustRedQuicksandG.wall(FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get());

        // Lumens
        ItemModelHelper.registerLumen(FRBlocks.AMETHYST_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.COBALT_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.DIAMOND_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.EMERALD_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.FROSTITE_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.QUARTZ_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.REDSTONE_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.VERDINITE_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.VIVULITE_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.BRIMTAN_LUMEN.get(), blockStateModelGenerator);
        ItemModelHelper.registerLumen(FRBlocks.ECHO_LUMEN.get(), blockStateModelGenerator);

        // Blighted Birch
        blockStateModelGenerator.woodProvider(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get()).wood(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.woodProvider(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get()).wood(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.woodProvider(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
                .logWithHorizontal(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get()).wood(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get());
        blockStateModelGenerator.createTrivialBlock(FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), TexturedModel.LEAVES);

        // Monster Bakery
        ItemModelHelper.registerMonsterBakery(FRBlocks.MONSTER_BAKERY.get(), blockStateModelGenerator);

        // Tower Blocks
        ItemModelHelper.registerTowerWatcher(FRBlocks.TOWER_WATCHER.get(), blockStateModelGenerator);
        ItemModelHelper.registerTowerSpawner(FRBlocks.TOWER_SPAWNER.get(), blockStateModelGenerator);
        ItemModelHelper.registerTowerHeart(FRBlocks.TOWER_HEART.get(), blockStateModelGenerator);
        ItemModelHelper.registerTowerVault(FRBlocks.TOWER_TREASURE_VAULT.get(), blockStateModelGenerator);
        ItemModelHelper.registerTowerKeyVault(FRBlocks.TOWER_KEY_VAULT.get(), blockStateModelGenerator);

        // Panes
        blockStateModelGenerator.createGlassBlocks(FRBlocks.SEA_GLASS.get(), FRBlocks.SEA_GLASS_PANE.get());
        blockStateModelGenerator.createGlassBlocks(FRBlocks.PALE_SEA_GLASS.get(), FRBlocks.PALE_SEA_GLASS_PANE.get());

        // Anvils
        BlockModelHelper.registerVivuliteAnvil(FRBlocks.VIVULITE_ANVIL.get(), blockStateModelGenerator);

        // Iron Bars
        BlockModelHelper.registerIronBarLike(FRBlocks.COBALT_GRILLES.get(), blockStateModelGenerator);

        // Carpets
        BlockModelHelper.registerCarpet(FRBlocks.NECRO_RUG.get(), blockStateModelGenerator);

        // Special Slabs
        BlockModelHelper.registerSoloSlab(FRBlocks.EGG_PALLET.get(), blockStateModelGenerator);
        BlockModelHelper.registerSoloSlab(FRBlocks.GOLDEN_EGG_PALLET.get(), blockStateModelGenerator);

        // Mushroom Blocks
        ModelTemplates.SINGLE_FACE.create(
                TextureMapping.getBlockTexture(FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "_inside"),
                TextureMapping.defaultTexture(TextureMapping.getBlockTexture(FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "_inside")),
                blockStateModelGenerator.modelOutput
        );
        ItemModelHelper.registerCustomMushroomBlock(
                FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/fungal_daffodil_block_inside"),
                blockStateModelGenerator
        );

        // Multiface
        blockStateModelGenerator.createMultiface(FRBlocks.SLIME_TRAIL.get());

        // "Cakes" (did in resources too lazy)
        //BlockModels.registerCakeBlock(ModBlocks.BEEF_WELLINGTON.get(), ModBlocks.BEEF_WELLINGTON.asItem().get(), blockStateModelGenerator);

        // Basic blocks
        blockStateModelGenerator.createTrivialCube(FRBlocks.MOURNING_GOLD_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.DEEPSLATE_COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.COBALT_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.RAW_COBALT_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.FROSTITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.RAW_FROSTITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.VERDINITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.DEEPSLATE_VERDINITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.VERDINITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.RAW_VERDINITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.VIVULITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.DEEPSLATE_VIVULITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.VIVULITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.RAW_VIVULITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.BLACK_EMERALD_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.BLACK_EMERALD_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.BRIMTAN_ORE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.BRIMTAN_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.NECRO_WEAVE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CRACKED_RED_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.CHISELED_RED_NETHER_BRICKS.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.EBONCORK.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.AESTHENOSTONE.get());
        blockStateModelGenerator.createTrivialCube(FRBlocks.ROTTEN_FLESH_BLOCK.get());

        // SPAWN EGGS BECAUSE APPARENTLY THIS IS HOW YOU DO IT
        blockStateModelGenerator.delegateItemModel(FRItems.CRAWLER_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(FRItems.JUNGLE_SPIDER_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(FRItems.PUMPKIN_GOLEM_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(FRItems.CROW_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
        blockStateModelGenerator.delegateItemModel(FRItems.GOLDEN_CHICKEN_SPAWN_EGG.get(), ModelLocationUtils.decorateItemModelLocation("template_spawn_egg"));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator)
    {
        for (Item item : BuiltInRegistries.ITEM)
        {
            if (item instanceof ArmorItem armorItem)
            {
                ItemModelHelper.registerArmorWithFrontiersTrims(armorItem, itemModelGenerator);
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

        itemModelGenerator.generateFlatItem(FRItems.CHEST_KEY.get(), ModelTemplates.FLAT_ITEM);

        // Handheld
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VERDINITE_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.VIVULITE_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.FROST_BONE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_KNIFE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        // Generated
        itemModelGenerator.generateFlatItem(FRItems.COBALT_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PITCH_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.RAW_COBALT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.RAW_VERDINITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.RAW_FROSTITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.RAW_VIVULITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TOWER_KEY_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TOWER_KEY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VOID_PEARL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_CLUSTER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.ELDER_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.COOKED_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PALE_PRISMARINE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TOTEM_OF_AVARICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VOID_DIAMOND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BLACK_EMERALD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.WARP_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BOUNCY_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SUBZERO_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.DYNAMITE_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PRISMARINE_ARROW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TRUFFLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TRUFFLE_OIL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TRUFFLE_POTATO_PUFF.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.ELDER_GUARDIAN_SPINE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SNOW_MELT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.MESSAGE_IN_A_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BOTTLED_MESSAGE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SPAWNER_CHUNK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.GOLDEN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SOUL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.POMEGRANATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.MANA_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.WITCH_HAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.FRUITCAKE_SLICE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.HARDENED_SLIME.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.MUSIC_DISC_DIAPHRAGM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BAIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.TABLET_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.CURSED_TABLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.UNFINISHED_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.REACTIVE_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.WITHERED_ESSENCE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.RAVAGER_TOOTH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.ONYX_BONE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.ONYX_MEAL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PURIFIED_END_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.BOUNCY_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SUBZERO_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.WARP_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SPECTRAL_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.DYNAMITE_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PRISMARINE_ARROW_ARROWHEAD.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.SPIRIT_CANDLE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BOUNCY_BALL.get(), ModelTemplates.FLAT_ITEM);

        for (Supplier<Item> ball : FRItems.COLOR_BALLS.values())
        {
            itemModelGenerator.generateFlatItem(ball.get(), ModelTemplates.FLAT_ITEM);
        }

        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_SWORD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_AXE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_HOE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.BRIMTAN_SHELL_BOOTS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.COBALT_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VERDINITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.VIVULITE_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FRItems.TOME_OF_FANGS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FRItems.THUNDERVAST_TOME.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(FDItem.TRUFFLE_PASTA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.FRIED_GOLDEN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(FDItem.BRIMTAN_SHELL_KNIFE.get(), ModelTemplates.FLAT_ITEM);

        // MANUAL DATA GEN
        // Slime Bulb
        ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(FRBlocks.SLIME_BULB.get().asItem()),
                new TextureMapping().put(TextureSlot.LAYER0, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/slime_bulb_stage_3")),
                itemModelGenerator.output
        );

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            DDyeModels.itemModels(itemModelGenerator);
        }
    }
}
