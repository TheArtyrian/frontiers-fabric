package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFModels;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
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

        blockStateModelGenerator.registerAmethyst(ModBlocks.CORRUPTED_AMETHYST_CLUSTER);
        blockStateModelGenerator.registerAmethyst(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        blockStateModelGenerator.registerAmethyst(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);

        blockStateModelGenerator.registerCrop(ModBlocks.WARPED_WART, Properties.AGE_3, 0, 1, 1, 2);

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
        ModelHelper.registerLumen(ModBlocks.ECHO_LUMEN, blockStateModelGenerator);

        // Panes
        blockStateModelGenerator.registerGlassPane(ModBlocks.SEA_GLASS, ModBlocks.SEA_GLASS_PANE);
        blockStateModelGenerator.registerGlassPane(ModBlocks.PALE_SEA_GLASS, ModBlocks.PALE_SEA_GLASS_PANE);

        // Basic blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FROSTITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VERDINITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_VERDINITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VIVULITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_VIVULITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.QUICKSAND);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RED_QUICKSAND);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRACKED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_RED_NETHER_BRICKS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HIELOSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBBLEFROST);

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.blockModels(blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.NECRO_WEAVE_BOOTS);

        itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.FROSTITE_BOOTS);

        itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.COBALT_BOOTS);

        itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItem.PLATE_CHESTPLATE);

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

        // Generated
        itemModelGenerator.register(ModItem.PITCH_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOWER_KEY_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOWER_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItem.VOID_PEARL, Models.GENERATED);
        itemModelGenerator.register(ModItem.VIVULITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItem.RAW_VIVULITE, Models.GENERATED);
        itemModelGenerator.register(ModItem.GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.ELDER_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.COOKED_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.COOKED_ELDER_GUARDIAN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItem.PALE_PRISMARINE_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItem.TOTEM_OF_AVARICE, Models.GENERATED);

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

        itemModelGenerator.register(ModItem.MUSIC_DISC_DIAPHRAGM, Models.GENERATED);
        //itemModelGenerator.register(ModItem.WARPED_WART, Models.GENERATED);

        // == MOD COMPATS ==
        if (Frontiers.DOING_DATAGEN)
        {
            BFModels.itemModels(itemModelGenerator);
        }
    }
}
