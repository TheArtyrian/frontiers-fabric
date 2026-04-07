package net.artyrian.frontiers.datagen.bfares;

import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.datagen.ItemModelHelper;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

// Referenced in Frontiers model provider.
public class BFModels
{
    public static void blockModels(BlockModelGenerators blockStateModelGenerator)
    {
        ItemModelHelper.registerLumen(BFBlock.FELDSPAR_LUMEN.get(), blockStateModelGenerator);
    }

    public static void itemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(BFItem.GUARDIAN_SOUP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.ELDEN_BOWL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.BREADED_GUARDIAN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.MELON_SPRITZER_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BFItem.GLISTERING_SPRITZER_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
    }
}
