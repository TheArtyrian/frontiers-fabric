package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.datagen.ModelHelper;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

// Referenced in Frontiers model provider.
public class BFModels
{
    public static void blockModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        ModelHelper.registerLumen(BFBlock.FELDSPAR_LUMEN, blockStateModelGenerator);
    }

    public static void itemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(BFItem.GUARDIAN_SOUP, Models.GENERATED);
        itemModelGenerator.register(BFItem.ELDEN_BOWL, Models.GENERATED);
        itemModelGenerator.register(BFItem.BREADED_GUARDIAN, Models.GENERATED);
        itemModelGenerator.register(BFItem.MELON_SPRITZER_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(BFItem.GLISTERING_SPRITZER_BOTTLE, Models.GENERATED);
    }
}
