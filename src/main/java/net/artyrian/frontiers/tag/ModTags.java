package net.artyrian.frontiers.tag;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.Block;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

// Tags for tag-gen
public class ModTags
{
    // Block tags.
    public static class Blocks
    {
        public static final TagKey<Block> INCORRECT_FOR_VERDINITE_TOOL = createTag("incorrect_for_verdinite_tool");
        public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = createTag("incorrect_for_cobalt_tool");
        public static final TagKey<Block> NEEDS_VERDINITE_TOOL = createTag("needs_verdinite_tool");
        public static final TagKey<Block> NEEDS_COBALT_TOOL = createTag("needs_cobalt_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag("needs_netherite_tool");
        public static final TagKey<Block> COBALT_ORES = createTag("cobalt_ores");
        public static final TagKey<Block> FROSTITE_ORES = createTag("frostite_ores");
        public static final TagKey<Block> INFINIBURN_CRAGS = createTag("infiniburn_crags");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Frontiers.MOD_ID, name));
        }

        private static TagKey<Block> createTagExt(String namespace, String name)
        {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(namespace, name));
        }
    }

    // Item tags.
    public static class Items
    {
        private static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Frontiers.MOD_ID, name));
        }

        private static TagKey<Item> createTagExt(String namespace, String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, name));
        }
    }
}
