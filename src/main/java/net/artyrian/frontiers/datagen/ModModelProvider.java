package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.property.Properties;

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
    }
}
