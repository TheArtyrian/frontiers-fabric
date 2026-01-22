package net.artyrian.frontiers.compat.dyemods;

import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class DyeModModels
{
    public static void itemModels(ItemModelGenerators itemModelGenerator)
    {
        itemModelGenerator.generateFlatItem(DyeModDummyItem.CORAL_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.CANARY_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.WASABI_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SACRAMENTO_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SKY_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.BLURPLE_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.SANGRIA_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.ROSE_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.UMBER_BALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DyeModDummyItem.LAVENDER_BALL, ModelTemplates.FLAT_ITEM);
    }
}
