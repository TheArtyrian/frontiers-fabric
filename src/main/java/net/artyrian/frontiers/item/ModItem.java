package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.data.components.BottleContentComponent;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.custom.arrow.*;
import net.artyrian.frontiers.item.custom.*;
import net.artyrian.frontiers.item.custom.tomes.EvokerTomeItem;
import net.artyrian.frontiers.item.armor.ModArmorMaterials;
import net.artyrian.frontiers.item.custom.tool.*;
import net.artyrian.frontiers.item.data.ModFoodComponents;
import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.artyrian.frontiers.item.trim.ModTrimPatterns;
import net.artyrian.frontiers.misc.ModRarity;
import net.artyrian.frontiers.misc.SmithTemplate;
import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

// Registers all mod items to Minecraft registries.
public class ModItem
{
    // ITEM LIST.

    // Materials
    public static final Item RAW_COBALT = registerItem("raw_cobalt", new Item(new Item.Settings()));
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new Item.Settings()));
    public static final Item BRIMTAN_INGOT = registerItem("brimtan_ingot", new Item(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_NUGGET = registerItem("brimtan_nugget", new Item(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_CLUSTER = registerItem("brimtan_cluster", new Item(new Item.Settings().fireproof()));
    public static final Item RAW_FROSTITE = registerItem("raw_frostite", new Item(new Item.Settings()));
    public static final Item FROSTITE_INGOT = registerItem("frostite_ingot", new Item(new Item.Settings()));
    public static final Item CURSED_TABLET = registerItem("cursed_tablet", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item OBSIDIAN_CASING = registerItem("obsidian_casing", new Item(new Item.Settings().fireproof()));
    public static final Item HEART_OF_THE_WARDEN = registerItem("heart_of_the_warden", new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(16).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item SHULKER_RESIDUE = registerItem("shulker_residue", new Item(new Item.Settings()));
    public static final Item WITHERED_ESSENCE = registerItem("withered_essence", new Item(new Item.Settings()));
    public static final Item ANCIENT_ROSE_SEED = registerItem("ancient_rose_seed", new AliasedBlockItem(ModBlocks.ANCIENT_ROSE_CROP, new Item.Settings()));
    public static final Item WARPED_WART = registerItem("warped_wart", new AliasedBlockItem(ModBlocks.WARPED_WART, new Item.Settings()));
    public static final Item ONYX_BONE = registerItem("onyx_bone", new Item(new Item.Settings()));
    public static final Item NECRO_WEAVE = registerItem("necro_weave", new Item(new Item.Settings()));
    public static final Item ECTOPLASM = registerItem("ectoplasm", new Item(new Item.Settings()));
    public static final Item MOURNING_GOLD_INGOT = registerItem("mourning_gold_ingot", new Item(new Item.Settings()));
    public static final Item INVOKE_SHARD = registerItem("invoke_shard", new Item(new Item.Settings()));
    public static final Item NACRE_BRICK = registerItem("nacre_brick", new Item(new Item.Settings()));
    public static final Item WHITE_BRICK = registerItem("white_brick", new Item(new Item.Settings()));
    public static final Item RAW_VERDINITE = registerItem("raw_verdinite", new Item(new Item.Settings()));
    public static final Item VERDINITE_INGOT = registerItem("verdinite_ingot", new Item(new Item.Settings()));
    public static final Item HARDENED_SLIME = registerItem("hardened_slime", new Item(new Item.Settings()));
    public static final Item TABLET_FRAGMENT = registerItem("tablet_fragment", new DiscFragmentItem(new Item.Settings()));
    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new Item(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));
    public static final Item END_CRYSTAL_SHARD = registerItem("end_crystal_shard", new EndCrystalShardItem(new Item.Settings()));
    public static final Item RAVAGER_TOOTH = registerItem("ravager_tooth", new Item(new Item.Settings()));
    public static final Item INCENSE = registerItem("incense", new Item(new Item.Settings()));
    public static final Item UNFINISHED_CORE = registerItem("unfinished_core", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item REACTIVE_CORE = registerItem("reactive_core", new Item(new Item.Settings().rarity(Rarity.RARE).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item PITCH_INGOT = registerItem("pitch_ingot", new Item(new Item.Settings()));
    public static final Item TOWER_KEY_FRAGMENT = registerItem("tower_key_fragment", new DiscFragmentItem(new Item.Settings()));
    public static final Item TOWER_KEY = registerItem("tower_key", new Item(new Item.Settings()));
    public static final Item RAW_VIVULITE = registerItem("raw_vivulite", new Item(new Item.Settings()));
    public static final Item VIVULITE_INGOT = registerItem("vivulite_ingot", new Item(new Item.Settings()));
    public static final Item PALE_PRISMARINE_SHARD = registerItem("pale_prismarine_shard", new Item(new Item.Settings()));
    public static final Item VOID_DIAMOND = registerItem("void_diamond", new Item(new Item.Settings()));
    public static final Item ELDER_GUARDIAN_SPINE = registerItem("elder_guardian_spine", new Item(new Item.Settings()));
    public static final Item EXPERIWINKLE_BULB = registerItem("experiwinkle_bulb", new AliasedBlockItem(ModBlocks.EXPERIWINKLE_CROP, new Item.Settings()));
    public static final Item FROST_BONE = registerItem("frost_bone", new Item(new Item.Settings()));
    public static final Item SPAWNER_CHUNK = registerItem("spawner_chunk", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item GOLDEN_EGG = registerItem("golden_egg", new GoldenEggItem(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));
    public static final Item SOUL = registerItem("soul", new Item(new Item.Settings()));

    // Spawn Eggs
    public static final Item JUNGLE_SPIDER_SPAWN_EGG =
            registerItem("jungle_spider_spawn_egg", new SpawnEggItem(ModEntity.JUNGLE_SPIDER, 5324062, 2039583, new Item.Settings()));
    public static final Item CRAWLER_SPAWN_EGG =
            registerItem("crawler_spawn_egg", new SpawnEggItem(ModEntity.CRAWLER, 281859, 790817, new Item.Settings()));
    public static final Item PUMPKIN_GOLEM_SPAWN_EGG =
            registerItem("pumpkin_golem_spawn_egg", new SpawnEggItem(ModEntity.PUMPKIN_GOLEM, 14912029, 16761444, new Item.Settings()));
    public static final Item CROW_SPAWN_EGG =
            registerItem("crow_spawn_egg", new SpawnEggItem(ModEntity.CROW, 3618618, 8355725, new Item.Settings()));
    public static final Item GOLDEN_CHICKEN_SPAWN_EGG =
            registerItem("golden_chicken_spawn_egg", new SpawnEggItem(ModEntity.GOLDEN_CHICKEN, 10592673, 15582019, new Item.Settings()));

    // Misc Tools
    public static final Item PURIFIED_END_CRYSTAL = registerItem("purified_end_crystal", new PurifiedEndCrystalItem(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SNOW_MELT = registerItem("snow_melt", new SnowMeltItem(new Item.Settings()));
    public static final Item ONYX_MEAL = registerItem("onyx_meal", new OnyxMealItem(new Item.Settings()));
    public static final Item VOID_PEARL = registerItem("void_pearl", new VoidPearlItem(new Item.Settings().maxCount(16)));
    public static final Item CHEST_KEY = registerItem("chest_key", new ChestKeyItem(new Item.Settings().maxCount(1)));
    public static final Item TOTEM_OF_AVARICE = registerItem("totem_of_avarice", new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item MESSAGE_IN_A_BOTTLE = registerItem("message_in_a_bottle", new BottleMessageItem(new Item.Settings().maxCount(1)));
    public static final Item MANA_BOTTLE = registerItem("mana_bottle", new ManaBottleItem(new Item.Settings().rarity(Rarity.UNCOMMON).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item BAIT = registerItem("bait", new BaitItem(new Item.Settings()));
    public static final Item BOTTLED_MESSAGE = registerItem("bottled_message", new BottleMessageItem(
            new Item.Settings()
                    .maxCount(1)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .component(ModDataComponents.BOTTLE_CONTENT, BottleContentComponent.DEFAULT)
            )
    );
    public static final Item COBALT_FISHING_ROD = registerItem("cobalt_fishing_rod", new CustomFishingRod(
            BobberType.COBALT,
            22,
            new Item.Settings().maxDamage(256)));
    public static final Item VERDINITE_BOW = registerItem("verdinite_bow", new FrontiersBowItem(
            ModSounds.VERDINITE_BOW_SHOOT,
            new Item.Settings().maxDamage(384))
    );
    public static final Item COBALT_SHIELD = registerItem("cobalt_shield",
            new CustomShieldItem(
                    "cobalt_shield",
                    COBALT_INGOT,
                    new Item.Settings().maxDamage(632).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT))
    );
    public static final Item PALE_TRIDENT = registerItem("pale_trident",
            new CustomTridentItem(new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .maxDamage(480)
                    .attributeModifiers(CustomTridentItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, CustomTridentItem.createToolComponent()
    )));
    public static final Item WITCH_HAT = registerItem("witch_hat",
            new WitchHatItem(new Item.Settings()
                    .maxDamage(380)
                    .maxCount(1)
            )
    );
    public static final Item SLIME_SHOES = registerItem( "slime_shoes",
            new SlimeArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, 3, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(10))
            )
    );
    // Arrows + Arrowheads
    public static final Item SPECTRAL_ARROW_ARROWHEAD = registerItem("spectral_arrow_arrowhead", new ArrowheadItem("spectral", Items.SPECTRAL_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SUBZERO_ARROW = registerItem("subzero_arrow", new SubzeroArrowItem(new Item.Settings()));
    public static final Item SUBZERO_ARROW_ARROWHEAD = registerItem("subzero_arrow_arrowhead", new ArrowheadItem("subzero", SUBZERO_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item WARP_ARROW = registerItem("warp_arrow", new WarpArrowItem(new Item.Settings()));
    public static final Item WARP_ARROW_ARROWHEAD = registerItem("warp_arrow_arrowhead", new ArrowheadItem("warp", WARP_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item BOUNCY_ARROW = registerItem("bouncy_arrow", new BouncyArrowItem(new Item.Settings()));
    public static final Item BOUNCY_ARROW_ARROWHEAD = registerItem("bouncy_arrow_arrowhead", new ArrowheadItem("bouncy", BOUNCY_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DYNAMITE_ARROW = registerItem("dynamite_arrow", new DynamiteArrowItem(new Item.Settings()));
    public static final Item DYNAMITE_ARROW_ARROWHEAD = registerItem("dynamite_arrow_arrowhead", new ArrowheadItem("dynamite", DYNAMITE_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item PRISMARINE_ARROW = registerItem("prismarine_arrow", new PrismarineArrowItem(new Item.Settings()));
    public static final Item PRISMARINE_ARROW_ARROWHEAD = registerItem("prismarine_arrow_arrowhead", new ArrowheadItem("prismarine", PRISMARINE_ARROW, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Balls
    public static final Item BALL = registerItem("ball", new BallItem(Formatting.WHITE, new Item.Settings().maxCount(1)));
    public static final Item BOUNCY_BALL = registerItem("bouncy_ball", new BallItem(Formatting.WHITE, 4, new Item.Settings().maxCount(1)));
    public static final Item WHITE_BALL = registerItem("white_ball", new BallItem(Formatting.WHITE, new Item.Settings().maxCount(1)));
    public static final Item LIGHT_GRAY_BALL = registerItem("light_gray_ball", new BallItem(Formatting.GRAY, new Item.Settings().maxCount(1)));
    public static final Item GRAY_BALL = registerItem("gray_ball", new BallItem(Formatting.GRAY, new Item.Settings().maxCount(1)));
    public static final Item BLACK_BALL = registerItem("black_ball", new BallItem(Formatting.DARK_GRAY, new Item.Settings().maxCount(1)));
    public static final Item BROWN_BALL = registerItem("brown_ball", new BallItem(Formatting.DARK_RED, new Item.Settings().maxCount(1)));
    public static final Item RED_BALL = registerItem("red_ball", new BallItem(Formatting.RED, new Item.Settings().maxCount(1)));
    public static final Item ORANGE_BALL = registerItem("orange_ball", new BallItem(Formatting.GOLD, new Item.Settings().maxCount(1)));
    public static final Item YELLOW_BALL = registerItem("yellow_ball", new BallItem(Formatting.YELLOW, new Item.Settings().maxCount(1)));
    public static final Item LIME_BALL = registerItem("lime_ball", new BallItem(Formatting.GREEN, new Item.Settings().maxCount(1)));
    public static final Item GREEN_BALL = registerItem("green_ball", new BallItem(Formatting.DARK_GREEN, new Item.Settings().maxCount(1)));
    public static final Item CYAN_BALL = registerItem("cyan_ball", new BallItem(Formatting.AQUA, new Item.Settings().maxCount(1)));
    public static final Item LIGHT_BLUE_BALL = registerItem("light_blue_ball", new BallItem(Formatting.BLUE, new Item.Settings().maxCount(1)));
    public static final Item BLUE_BALL = registerItem("blue_ball", new BallItem(Formatting.BLUE, new Item.Settings().maxCount(1)));
    public static final Item PURPLE_BALL = registerItem("purple_ball", new BallItem(Formatting.DARK_PURPLE, new Item.Settings().maxCount(1)));
    public static final Item MAGENTA_BALL = registerItem("magenta_ball", new BallItem(Formatting.LIGHT_PURPLE, new Item.Settings().maxCount(1)));
    public static final Item PINK_BALL = registerItem("pink_ball", new BallItem(Formatting.LIGHT_PURPLE, new Item.Settings().maxCount(1)));

    // Tool Classes
    // Mourning Gold
    public static final Item MOURNING_GOLD_PICKAXE = registerItem("mourning_gold_pickaxe",
            new PickaxeItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, 1.0F, -2.8F)))
    );
    public static final Item MOURNING_GOLD_AXE = registerItem("mourning_gold_axe",
            new AxeItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, 5.0F, -3.0F)))
    );
    public static final Item MOURNING_GOLD_SWORD = registerItem("mourning_gold_sword",
            new SwordItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, 3, -2.4F)))
    );
    public static final Item MOURNING_GOLD_SHOVEL = registerItem("mourning_gold_shovel",
            new ShovelItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, 1.5F, -3.0F)))
    );
    public static final Item MOURNING_GOLD_HOE = registerItem("mourning_gold_hoe",
            new HoeItem(ModToolMaterial.MOURNING_GOLD, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.MOURNING_GOLD, -2.5F, 1.0F)))
    );
    // Obsidian (+ Broken Item)
    public static final Item OBSIDIAN_PICKAXE = registerItem("obsidian_pickaxe",
            new UnbreakablePickaxeItem(
                    Identifier.of(Frontiers.MOD_ID, "obsidian_pickaxe_broken"),
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 1.0F, -2.8F))
            )
    );
    public static final Item OBSIDIAN_PICKAXE_BROKEN = registerItem("obsidian_pickaxe_broken",
            new BrokenToolItem(
                    OBSIDIAN_PICKAXE,
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().maxCount(1))
    );
    public static final Item OBSIDIAN_AXE = registerItem("obsidian_axe",
            new UnbreakableAxeItem(
                    Identifier.of(Frontiers.MOD_ID, "obsidian_axe_broken"),
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 5.0F, -3.0F))
            )
    );
    public static final Item OBSIDIAN_AXE_BROKEN = registerItem("obsidian_axe_broken",
            new BrokenToolItem(
                    OBSIDIAN_AXE,
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().maxCount(1))
    );
    public static final Item OBSIDIAN_SWORD = registerItem("obsidian_sword",
            new UnbreakableSwordItem(
                    Identifier.of(Frontiers.MOD_ID, "obsidian_sword_broken"),
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 3, -2.4F))
            )
    );
    public static final Item OBSIDIAN_SWORD_BROKEN = registerItem("obsidian_sword_broken",
            new BrokenToolItem(
                    OBSIDIAN_SWORD,
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().maxCount(1))
    );
    public static final Item OBSIDIAN_SHOVEL = registerItem("obsidian_shovel",
            new UnbreakableShovelItem(
                    Identifier.of(Frontiers.MOD_ID, "obsidian_shovel_broken"),
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 1.5F, -3.0F))
            )
    );
    public static final Item OBSIDIAN_SHOVEL_BROKEN = registerItem("obsidian_shovel_broken",
            new BrokenToolItem(
                    OBSIDIAN_SHOVEL,
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().maxCount(1))
    );
    public static final Item OBSIDIAN_HOE = registerItem("obsidian_hoe",
            new UnbreakableHoeItem(
                    Identifier.of(Frontiers.MOD_ID, "obsidian_hoe_broken"),
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, -3.0F, 0.0F))
            )
    );
    public static final Item OBSIDIAN_HOE_BROKEN = registerItem("obsidian_hoe_broken",
            new BrokenToolItem(
                    OBSIDIAN_HOE,
                    ModToolMaterial.OBSIDIAN,
                    new Item.Settings().maxCount(1))
    );
    // Cobalt
    public static final Item COBALT_PICKAXE = registerItem("cobalt_pickaxe",
            new PickaxeItem(ModToolMaterial.COBALT, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.COBALT, 1.0F, -2.8F)))
    );
    public static final Item COBALT_AXE = registerItem("cobalt_axe",
            new AxeItem(ModToolMaterial.COBALT, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.COBALT, 5.0F, -3.0F)))
    );
    public static final Item COBALT_SWORD = registerItem("cobalt_sword",
            new SwordItem(ModToolMaterial.COBALT, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.COBALT, 3, -2.4F)))
    );
    public static final Item COBALT_SHOVEL = registerItem("cobalt_shovel",
            new ShovelItem(ModToolMaterial.COBALT, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.COBALT, 1.5F, -3.0F)))
    );
    public static final Item COBALT_HOE = registerItem("cobalt_hoe",
            new HoeItem(ModToolMaterial.COBALT, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.COBALT, -5.0F, 1.0F)))
    );
    // Verdinite
    public static final Item VERDINITE_PICKAXE = registerItem("verdinite_pickaxe",
            new PickaxeItem(ModToolMaterial.VERDINITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.VERDINITE, 1.0F, -2.8F)))
    );
    public static final Item VERDINITE_AXE = registerItem("verdinite_axe",
            new AxeItem(ModToolMaterial.VERDINITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.VERDINITE, 5.0F, -3.0F)))
    );
    public static final Item VERDINITE_SWORD = registerItem("verdinite_sword",
            new SwordItem(ModToolMaterial.VERDINITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.VERDINITE, 3, -2.4F)))
    );
    public static final Item VERDINITE_SHOVEL = registerItem("verdinite_shovel",
            new ShovelItem(ModToolMaterial.VERDINITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.VERDINITE, 1.5F, -3.0F)))
    );
    public static final Item VERDINITE_HOE = registerItem("verdinite_hoe",
            new HoeItem(ModToolMaterial.VERDINITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.VERDINITE, -6.0F, 1.0F)))
    );
    // Frostite
    public static final Item FROSTITE_PICKAXE = registerItem("frostite_pickaxe",
            new PickaxeItem(ModToolMaterial.FROSTITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.FROSTITE, 1.0F, -2.8F)))
    );
    public static final Item FROSTITE_AXE = registerItem("frostite_axe",
            new AxeItem(ModToolMaterial.FROSTITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.FROSTITE, 5.0F, -3.0F)))
    );
    public static final Item FROSTITE_SWORD = registerItem("frostite_sword",
            new SwordItem(ModToolMaterial.FROSTITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.FROSTITE, 3, -2.4F)))
    );
    public static final Item FROSTITE_SHOVEL = registerItem("frostite_shovel",
            new ShovelItem(ModToolMaterial.FROSTITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.FROSTITE, 1.5F, -3.0F)))
    );
    public static final Item FROSTITE_HOE = registerItem("frostite_hoe",
            new HoeItem(ModToolMaterial.FROSTITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.FROSTITE, -6.0F, 1.0F)))
    );
    // Vivulite
    public static final Item VIVULITE_PICKAXE = registerItem("vivulite_pickaxe",
            new PickaxeItem(ModToolMaterial.VIVULITE, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.VIVULITE, 1.0F, -2.8F)))
    );
    public static final Item VIVULITE_AXE = registerItem("vivulite_axe",
            new AxeItem(ModToolMaterial.VIVULITE, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.VIVULITE, 5.0F, -3.0F)))
    );
    public static final Item VIVULITE_SWORD = registerItem("vivulite_sword",
            new SwordItem(ModToolMaterial.VIVULITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.VIVULITE, 3, -2.4F)))
    );
    public static final Item VIVULITE_SHOVEL = registerItem("vivulite_shovel",
            new ShovelItem(ModToolMaterial.VIVULITE, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.VIVULITE, 1.5F, -3.0F)))
    );
    public static final Item VIVULITE_HOE = registerItem("vivulite_hoe",
            new HoeItem(ModToolMaterial.VIVULITE, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.VIVULITE, -7.0F, 1.0F)))
    );
    // Brimtan
    public static final Item BRIMTAN_PICKAXE = registerItem("brimtan_pickaxe",
            new PickaxeItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, 1.0F, -2.8F)))
    );
    public static final Item BRIMTAN_AXE = registerItem("brimtan_axe",
            new AxeItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, 5.0F, -3.0F)))
    );
    public static final Item BRIMTAN_SWORD = registerItem("brimtan_sword",
            new SwordItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, 3, -2.4F)))
    );
    public static final Item BRIMTAN_SHOVEL = registerItem("brimtan_shovel",
            new ShovelItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, 1.5F, -3.0F)))
    );
    public static final Item BRIMTAN_HOE = registerItem("brimtan_hoe",
            new HoeItem(ModToolMaterial.BRIMTAN, new Item.Settings().fireproof().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.BRIMTAN, -8.0F, 1.0F)))
    );

    // Armor Classes
    // Rotcross
    public static final Item NECRO_WEAVE_HELMET = registerItem( "necro_weave_helmet",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(26))
            )
    );
    public static final Item NECRO_WEAVE_CHESTPLATE = registerItem( "necro_weave_chestplate",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(26))
            )
    );
    public static final Item NECRO_WEAVE_LEGGINGS = registerItem( "necro_weave_leggings",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(26))
            )
    );
    public static final Item NECRO_WEAVE_BOOTS = registerItem( "necro_weave_boots",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(26))
            )
    );
    // Mourning Gold
    public static final Item MOURNING_GOLD_HELMET = registerItem( "mourning_gold_helmet",
            new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(20))
            )
    );
    public static final Item MOURNING_GOLD_CHESTPLATE = registerItem( "mourning_gold_chestplate",
            new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(20))
            )
    );
    public static final Item MOURNING_GOLD_LEGGINGS = registerItem( "mourning_gold_leggings",
            new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(20))
            )
    );
    public static final Item MOURNING_GOLD_BOOTS = registerItem( "mourning_gold_boots",
            new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(20))
            )
    );
    // Cobalt
    public static final Item COBALT_HELMET = registerItem( "cobalt_helmet",
            new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(41))
            )
    );
    public static final Item COBALT_CHESTPLATE = registerItem( "cobalt_chestplate",
            new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(41))
            )
    );
    public static final Item COBALT_LEGGINGS = registerItem( "cobalt_leggings",
            new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(41))
            )
    );
    public static final Item COBALT_BOOTS = registerItem( "cobalt_boots",
            new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(41))
            )
    );
    // Verdinite
    public static final Item VERDINITE_HELMET = registerItem( "verdinite_helmet",
            new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45))
            )
    );
    public static final Item VERDINITE_CHESTPLATE = registerItem( "verdinite_chestplate",
            new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45))
            )
    );
    public static final Item VERDINITE_LEGGINGS = registerItem( "verdinite_leggings",
            new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45))
            )
    );
    public static final Item VERDINITE_BOOTS = registerItem( "verdinite_boots",
            new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45))
            )
    );
    // Frostite
    public static final Item FROSTITE_HELMET = registerItem( "frostite_helmet",
            new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45))
            )
    );
    public static final Item FROSTITE_CHESTPLATE = registerItem( "frostite_chestplate",
            new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45))
            )
    );
    public static final Item FROSTITE_LEGGINGS = registerItem( "frostite_leggings",
            new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45))
            )
    );
    public static final Item FROSTITE_BOOTS = registerItem( "frostite_boots",
            new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45))
            )
    );
    // Vivulite
    public static final Item VIVULITE_HELMET = registerItem( "vivulite_helmet",
            new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(50))
            )
    );
    public static final Item VIVULITE_CHESTPLATE = registerItem( "vivulite_chestplate",
            new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(50))
            )
    );
    public static final Item VIVULITE_LEGGINGS = registerItem( "vivulite_leggings",
            new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(50))
            )
    );
    public static final Item VIVULITE_BOOTS = registerItem( "vivulite_boots",
            new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(50))
            )
    );
    // Brimtan
    public static final Item BRIMTAN_HELMET = registerItem( "brimtan_helmet",
            new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(55)).fireproof()
            )
    );
    public static final Item BRIMTAN_CHESTPLATE = registerItem( "brimtan_chestplate",
            new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(55)).fireproof()
            )
    );
    public static final Item BRIMTAN_LEGGINGS = registerItem( "brimtan_leggings",
            new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(55)).fireproof()
            )
    );
    public static final Item BRIMTAN_BOOTS = registerItem( "brimtan_boots",
            new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(55)).fireproof()
            )
    );
    // Plate
    public static final Item PLATE_HELMET = registerItem( "plate_helmet",
            new ArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(60))
            )
    );
    public static final Item PLATE_CHESTPLATE = registerItem( "plate_chestplate",
            new ArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(60))
            )
    );

    // Horse Armors
    public static final Item COBALT_HORSE_ARMOR = registerItem(
            "cobalt_horse_armor", new AnimalArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1))
    );
    public static final Item VERDINITE_HORSE_ARMOR = registerItem(
            "verdinite_horse_armor", new AnimalArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1))
    );
    public static final Item VIVULITE_HORSE_ARMOR = registerItem(
            "vivulite_horse_armor", new AnimalArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1))
    );

    // Food
    public static final Item MARSHMALLOW = registerItem("marshmallow",
            new Item(new Item.Settings().food(ModFoodComponents.MARSHMALLOW))
    );
    public static final Item ROASTED_MARSHMALLOW = registerItem("roasted_marshmallow",
            new Item(new Item.Settings().food(ModFoodComponents.ROASTED_MARSHMALLOW))
    );
    public static final Item LEVI_ROLL = registerItem("levi_roll",
            new Item(new Item.Settings().food(ModFoodComponents.LEVI_ROLL))
    );
    public static final Item POMEGRANATE = registerItem("pomegranate",
            new ChanceVaryFoodItem(0.25F, new Item.Settings().food(ModFoodComponents.POMEGRANATE))
    );
    public static final Item TRUFFLE = registerItem("truffle",
            new TruffleItem(new Item.Settings().food(ModFoodComponents.TRUFFLE).rarity(Rarity.RARE))
    );
    public static final Item TRUFFLE_POTATO_PUFF = registerItem("truffle_potato_puff",
            new Item(new Item.Settings().food(ModFoodComponents.TRUFFLE_POTATO_PUFF))
    );
    public static final Item TRUFFLE_OIL = registerItem("truffle_oil",
            new DrinkItem(new Item.Settings().food(ModFoodComponents.TRUFFLE_OIL)));
    public static final Item GUARDIAN_SLICE = registerItem("guardian_slice",
            new GuardianSliceItem(new Item.Settings().food(ModFoodComponents.GUARDIAN_SLICE))
    );
    public static final Item ELDER_GUARDIAN_SLICE = registerItem("elder_guardian_slice",
            new GuardianSliceItem(new Item.Settings().food(ModFoodComponents.ELDER_GUARDIAN_SLICE))
    );
    public static final Item COOKED_GUARDIAN_SLICE = registerItem("cooked_guardian_slice",
            new GuardianSliceItem(new Item.Settings().food(ModFoodComponents.COOKED_GUARDIAN_SLICE))
    );
    public static final Item COOKED_ELDER_GUARDIAN_SLICE = registerItem("cooked_elder_guardian_slice",
            new GuardianSliceItem(new Item.Settings().food(ModFoodComponents.COOKED_ELDER_GUARDIAN_SLICE))
    );
    public static final Item APPLE_OF_ENLIGHTENMENT = registerItem("apple_of_enlightenment",
            new HealthBuffItem(
                    "UsedAppleBuff",
                    true,
                    new Item.Settings().food(ModFoodComponents.APPLE_OF_ENLIGHTENMENT).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(ModRarity.FRONTIERS_MYTHICAL)
            )
    );
    public static final Item FRUITCAKE_SLICE = registerItem("fruitcake_slice",
            new FruitcakeItem(new Item.Settings().food(ModFoodComponents.FRUITCAKE))
    );

    // Normal Smithing Templates
    public static final Item OBSIDIAN_UPGRADE_SMITHING_TEMPLATE = registerItem("obsidian_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.OBSIDIAN_UPGRADE_APPLIES_TO_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_INGREDIENTS_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.toolUpgradeSlotTextures(),
                    SmithTemplate.casingAdditionsTexture()
            )
    );

    // == BRIMTAN UPGRADE TREE ==
    public static final Item BRIMTAN_SHELL_SWORD = registerItem("brimtan_shell_sword", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_SHOVEL = registerItem("brimtan_shell_shovel", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_HOE = registerItem("brimtan_shell_hoe", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_AXE = registerItem("brimtan_shell_axe", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_PICKAXE = registerItem("brimtan_shell_pickaxe", new DiscFragmentItem(new Item.Settings().fireproof()));

    public static final Item BRIMTAN_SHELL_HELMET = registerItem("brimtan_shell_helmet", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_CHESTPLATE = registerItem("brimtan_shell_chestplate", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_LEGGINGS = registerItem("brimtan_shell_leggings", new DiscFragmentItem(new Item.Settings().fireproof()));
    public static final Item BRIMTAN_SHELL_BOOTS = registerItem("brimtan_shell_boots", new DiscFragmentItem(new Item.Settings().fireproof()));

    public static final Item BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_helmet_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("helmet"),
                    SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("helmet"),
                    SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("helmet"),
                    SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("helmet"),
                    SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("helmet"),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_HELMET_TEXTURE),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_HELMET_TEXTURE)
            )
    );
    public static final Item BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_chestplate_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("chestplate"),
                    SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("chestplate"),
                    SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("chestplate"),
                    SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("chestplate"),
                    SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("chestplate"),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE)
            )
    );
    public static final Item BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_leggings_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("leggings"),
                    SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("leggings"),
                    SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("leggings"),
                    SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("leggings"),
                    SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("leggings"),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE)
            )
    );
    public static final Item BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_boots_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("boots"),
                    SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("boots"),
                    SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("boots"),
                    SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("boots"),
                    SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("boots"),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_BOOTS_TEXTURE),
                    List.of(SmithTemplate.EMPTY_ARMOR_SLOT_BOOTS_TEXTURE)
            )
    );
    public static final Item BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_tool_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("tool"),
                    SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("tool"),
                    SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("tool"),
                    SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("tool"),
                    SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("tool"),
                    SmithTemplate.toolUpgradeSlotTextures(),
                    SmithTemplate.toolUpgradeSlotTextures()
            )
    );

    // Trim Templates
    public static final Item PULSE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem(
            "pulse_armor_trim_smithing_template", SmithingTemplateItem.of(ModTrimPatterns.PULSE)
    );
    public static final Item SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem(
            "sludge_armor_trim_smithing_template", SmithingTemplateItem.of(ModTrimPatterns.SLUDGE)
    );
    public static final Item PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem(
            "photon_armor_trim_smithing_template", SmithingTemplateItem.of(ModTrimPatterns.PHOTON)
    );

    // Unfinished core plates
    public static final Item DEPTHS_CORE_PLATE = registerItem("depths_core_plate", new CorePlateItem(
            CorePlateItem.DEPTH_TYPE_TEXT,
            new Item.Settings().rarity(Rarity.UNCOMMON).fireproof())
    );
    public static final Item FRONTAL_CORE_PLATE = registerItem("frontal_core_plate", new CorePlateItem(
            CorePlateItem.FRONTAL_TYPE_TEXT,
            new Item.Settings().rarity(Rarity.UNCOMMON))
    );

    // Tomes (WIP Set)
    public static final Item TOME_OF_FANGS = registerItem("tome_of_fangs",
            new EvokerTomeItem(new Item.Settings().rarity(Rarity.UNCOMMON))
    );

    // Music Discs
    public static final Item MUSIC_DISC_DIAPHRAGM = registerItem("music_disc_diaphragm",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.DISC_DIAPHRAGM_KEY).maxCount(1).rarity(Rarity.RARE)));

    // Spirit Candle
    public static final Item SPIRIT_CANDLE = registerItem("spirit_candle", new SpiritCandleItem(ModBlocks.SPIRIT_CANDLE, new Item.Settings()));

    // Doors
    public static final Item EBONCORK_DOOR = registerItem("eboncork_door", new TallBlockItem(ModBlocks.EBONCORK_DOOR, new Item.Settings()));
    public static final Item BLIGHTED_BIRCH_DOOR = registerItem("blighted_birch_door", new TallBlockItem(ModBlocks.BLIGHTED_BIRCH_DOOR, new Item.Settings()));

    // Phantom-Stitch Bed
    public static final Item PHANTOM_STITCH_BED = registerItem("phantom_stitch_bed", new BedItem(ModBlocks.PHANTOM_STITCH_BED, new Item.Settings().maxCount(1)));

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. Just sends a log message.
    public static void registerModItems()
    {
        //Frontiers.LOGGER.info("Registering Mod Items for " + Frontiers.MOD_ID);
    }
}
