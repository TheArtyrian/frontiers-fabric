package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.fabric.impl.tag.convention.v2.TagRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class FDTag
{
    // Block tags.
    public static class Blocks
    {
        public static final TagKey<Block> MINEABLE_KNIFE = createTag("mineable/knife");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.create(Registries.BLOCK, ResourceLocation.tryBuild(Frontiers.FARMERS_DELIGHT_ID, name));
        }
    }

    // Item tags.
    public static class Items
    {
        public static final TagKey<Item> KNIVES = createTag("tools/knives");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.create(Registries.ITEM, ResourceLocation.tryBuild(Frontiers.FARMERS_DELIGHT_ID, name));
        }
    }
}
