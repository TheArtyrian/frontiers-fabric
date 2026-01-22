package net.artyrian.frontiers.datagen.compat;

import net.artyrian.frontiers.compat.dyemods.DyeModDummyItem;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class DyeModModels
{
    public static void itemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(DyeModDummyItem.CORAL_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.CANARY_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.WASABI_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SACRAMENTO_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SKY_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.BLURPLE_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SANGRIA_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.ROSE_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.UMBER_BALL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.LAVENDER_BALL.get(), ModelTemplates.FLAT_ITEM);
    }
}
