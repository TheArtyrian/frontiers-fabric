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
        public static final TagKey<Block> INCORRECT_FOR_BRIMTAN_TOOL = createTag("incorrect_for_brimtan_tool");
        public static final TagKey<Block> INCORRECT_FOR_VIVULITE_TOOL = createTag("incorrect_for_vivulite_tool");
        public static final TagKey<Block> INCORRECT_FOR_VERDINITE_TOOL = createTag("incorrect_for_verdinite_tool");
        public static final TagKey<Block> INCORRECT_FOR_COBALT_TOOL = createTag("incorrect_for_cobalt_tool");
        public static final TagKey<Block> NEEDS_BRIMTAN_TOOL = createTag("needs_brimtan_tool");
        public static final TagKey<Block> NEEDS_VIVULITE_TOOL = createTag("needs_vivulite_tool");
        public static final TagKey<Block> NEEDS_VERDINITE_TOOL = createTag("needs_verdinite_tool");
        public static final TagKey<Block> NEEDS_COBALT_TOOL = createTag("needs_cobalt_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag("needs_netherite_tool");
        public static final TagKey<Block> COBALT_ORES = createTag("cobalt_ores");
        public static final TagKey<Block> VERDINITE_ORES = createTag("verdinite_ores");
        public static final TagKey<Block> FROSTITE_ORES = createTag("frostite_ores");
        public static final TagKey<Block> VIVULITE_ORES = createTag("vivulite_ores");
        public static final TagKey<Block> INFINIBURN_CRAGS = createTag("infiniburn_crags");
        public static final TagKey<Block> LUMENS = createTag("lumens");
        public static final TagKey<Block> STONE_FENCE_GATES = createTag("stone_fence_gates");
        public static final TagKey<Block> ENTITY_MODELS = createTag("entity_models");
        public static final TagKey<Block> ONYX_MEAL_DECAYABLE = createTag("onyx_meal_decayable");

        public static final TagKey<Block> ONLY_DROP_IN_HARDMODE = createTag("only_drop_in_hardmode");
        public static final TagKey<Block> CONDUIT_BASE_BLOCKS = createTag("conduit_base_blocks");

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
        public static final TagKey<Item> EVERTREE_BOOSTABLE = createTag("evertree_boostable");
        public static final TagKey<Item> LUMENS = createTag("lumens");
        public static final TagKey<Item> STONE_FENCE_GATES = createTag("stone_fence_gates");
        public static final TagKey<Item> ENTITY_MODELS = createTag("entity_models");
        public static final TagKey<Item> BALLS = createTag("balls");
        public static final TagKey<Item> DEFLECTS_BALLS = createTag("deflects_balls");

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
