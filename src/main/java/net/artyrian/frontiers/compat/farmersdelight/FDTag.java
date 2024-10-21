package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.fabricmc.fabric.impl.tag.convention.v2.TagRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FDTag
{
    // Block tags.
    public static class Blocks
    {
        public static final TagKey<Block> MINEABLE_KNIFE = createTag("mineable/knife");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse(Frontiers.FARMERS_DELIGHT_ID, name));
        }
    }

    // Item tags.
    public static class Items
    {
        public static final TagKey<Item> KNIVES = createTag("tools/knives");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.tryParse(Frontiers.FARMERS_DELIGHT_ID, name));
        }
    }
}
