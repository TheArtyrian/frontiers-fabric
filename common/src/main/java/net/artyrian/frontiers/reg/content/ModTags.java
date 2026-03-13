package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

// Tags for tag-gen
public class ModTags
{
    private static final String CONV = "c";

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
        public static final TagKey<Block> BLACK_EMERALD_ORES = createTag("black_emerald_ores");
        public static final TagKey<Block> INFINIBURN_CRAGS = createTag("infiniburn_crags");
        public static final TagKey<Block> LUMENS = createTag("lumens");
        public static final TagKey<Block> STONE_FENCE_GATES = createTag("stone_fence_gates");
        public static final TagKey<Block> ENTITY_MODELS = createTag("entity_models");
        public static final TagKey<Block> ONYX_MEAL_DECAYABLE = createTag("onyx_meal_decayable");
        public static final TagKey<Block> WREATHS = createTag("wreaths");

        public static final TagKey<Block> TOWER_WATCHABLES = createTag("tower_watchables");

        public static final TagKey<Block> CROW_CAN_SPAWN_ON = createTag("crow_can_spawn_on");

        public static final TagKey<Block> PUMPKIN_GOLEM_PICKABLE = createTag("pumpkin_golem_pickable");
        public static final TagKey<Block> PUMPKIN_GOLEM_NO_REPLANT = createTag("pumpkin_golem_no_replant");

        public static final TagKey<Block> EBONCORK_LOGS = createTag("eboncork_logs");
        public static final TagKey<Block> BLIGHTED_BIRCH_LOGS = createTag("blighted_birch_logs");

        public static final TagKey<Block> ONLY_DROP_IN_HARDMODE = createTag("only_drop_in_hardmode");
        public static final TagKey<Block> CONDUIT_BASE_BLOCKS = createTag("conduit_base_blocks");
        public static final TagKey<Block> PREVENTS_FLUID_FLOW = createTag("prevents_fluid_flow");

        // Quark
        public static final TagKey<Block> QUARK_SIMPLE_HARVEST_NOGO = createTagExt(Frontiers.QUARK_ID, "simple_harvest_blacklisted");

        // C tags
        public static final TagKey<Block> C_RAW_BLOCKS = createTagExt(CONV, "raw_blocks");

        private static TagKey<Block> createTag(String name)
        {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
        }

        private static TagKey<Block> createTagExt(String namespace, String name)
        {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }

    public static class Enchants
    {
        public static final TagKey<Enchantment> PREVENTS_MAGNET_EXP_DROP = createTag("prevents_magnet_exp_drop");

        private static TagKey<Enchantment> createTag(String name)
        {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
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
        public static final TagKey<Item> GLOWING_BRIMTAN_ITEMS = createTag("glowing_brimtan_items");
        public static final TagKey<Item> OFFHAND_PRIORITY_ITEM = createTag("offhand_priority_item");
        public static final TagKey<Item> GOLDEN_CHICKEN_FOOD = createTag("golden_chicken_food");
        public static final TagKey<Item> EBONCORK_LOGS = createTag("eboncork_logs");
        public static final TagKey<Item> BLIGHTED_BIRCH_LOGS = createTag("blighted_birch_logs");
        public static final TagKey<Item> ITEM_VACUUM_SOUL_FIRE = createTag("item_vacuum_soul_fire");
        public static final TagKey<Item> ITEM_VACUUM_HEARTS = createTag("item_vacuum_hearts");
        public static final TagKey<Item> FRUITCAKE_INGREDIENTS = createTag("fruitcake_ingredients");
        public static final TagKey<Item> WREATHS = createTag("wreaths");

        // C Tags
        public static final TagKey<Item> C_EGGS = createTagExt(CONV, "eggs");
        public static final TagKey<Item> C_RAW_BLOCKS = createTagExt(CONV, "raw_blocks");

        private static TagKey<Item> createTag(String name)
        {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
        }

        private static TagKey<Item> createTagExt(String namespace, String name)
        {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }

    public static class Biomes
    {
        public static final TagKey<Biome> WHITE_TOWER_HAS_STRUCTURE = createTag("has_structure/white_tower");
        public static final TagKey<Biome> BOTTLED_MESSAGE_COMPATIBLE = createTag("bottled_message_compatible");

        public static final TagKey<Biome> GENERATES_QUICKSAND = createTag("generates_quicksand");
        public static final TagKey<Biome> GENERATES_HIELOSTONE = createTag("generates_hielostone");
        public static final TagKey<Biome> GENERATES_BLACK_EMERALD = createTag("generates_black_emerald");
        public static final TagKey<Biome> GENERATES_FROSTITE = createTag("generates_frostite");
        public static final TagKey<Biome> GENERATES_BRIMTAN = createTag("generates_brimtan");

        public static final TagKey<Biome> GENERATES_EBONCORK = createTag("generates_eboncork");
        public static final TagKey<Biome> GENERATES_FUNGAL_DAFFODIL = createTag("generates_fungal_daffodil");
        public static final TagKey<Biome> GENERATES_SNOW_DAHLIA = createTag("generates_snow_dahlia");
        public static final TagKey<Biome> GENERATES_CRIMCONE = createTag("generates_crimcone");
        public static final TagKey<Biome> GENERATES_EXPERIWINKLE = createTag("generates_experiwinkle");

        private static TagKey<Biome> createTag(String name)
        {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
        }

        private static TagKey<Biome> createTagExt(String namespace, String name)
        {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }

    public static class Structures
    {
        public static final TagKey<Structure> ON_WHITE_TOWER_MAPS = createTag("on_white_tower_maps");

        private static TagKey<Structure> createTag(String name)
        {
            return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
        }

        private static TagKey<Structure> createTagExt(String namespace, String name)
        {
            return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }

    public static class EntityTypes
    {
        public static final TagKey<EntityType<?>> QUICKSAND_IMMUNE = createTag("quicksand_immune");
        public static final TagKey<EntityType<?>> IRON_GOLEM_NO_TARGET = createTag("iron_golem_no_target");

        private static TagKey<EntityType<?>> createTag(String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name));
        }

        private static TagKey<EntityType<?>> createTagExt(String namespace, String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(namespace, name));
        }
    }
}
