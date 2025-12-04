package net.artyrian.frontiers.compat.dyemods;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class DyeModModels
{
    public static void itemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(DyeModDummyItem.CORAL_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.CANARY_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.WASABI_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.SACRAMENTO_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.SKY_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.BLURPLE_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.SANGRIA_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.ROSE_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.UMBER_BALL, Models.GENERATED);
        itemModelGenerator.register(DyeModDummyItem.LAVENDER_BALL, Models.GENERATED);
    }
}
