package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.datagen.ModelHelper;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

// Referenced in Frontiers model provider.
public class BFModels
{
    public static void blockModels(BlockModelGenerators blockStateModelGenerator)
    {
        ModelHelper.registerLumen(BFBlock.FELDSPAR_LUMEN, blockStateModelGenerator);
    }

    public static void itemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(BFItem.GUARDIAN_SOUP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.ELDEN_BOWL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.BREADED_GUARDIAN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.MELON_SPRITZER_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.GLISTERING_SPRITZER_BOTTLE, ModelTemplates.FLAT_ITEM);
    }
}
