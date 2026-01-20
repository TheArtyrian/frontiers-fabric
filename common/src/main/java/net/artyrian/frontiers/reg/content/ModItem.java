package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.item.custom.BallItem;
import net.artyrian.frontiers.reg.misc.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.vertisoft.vectorlib.VectorLib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModItem
{
    // ITEM LIST.

    // Materials
    public static final Supplier<Item> RAW_COBALT = registerItem("raw_cobalt", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> COBALT_INGOT = registerItem("cobalt_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> BRIMTAN_INGOT = registerItem("brimtan_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_NUGGET = registerItem("brimtan_nugget", () -> new Item(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_CLUSTER = registerItem("brimtan_cluster", () -> new Item(new Item.Properties().fireResistant()));
    public static final Supplier<Item> RAW_FROSTITE = registerItem("raw_frostite", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> FROSTITE_INGOT = registerItem("frostite_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CURSED_TABLET = registerItem("cursed_tablet", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> OBSIDIAN_CASING = registerItem("obsidian_casing", () -> new Item(new Item.Properties().fireResistant()));
    public static final Supplier<Item> HEART_OF_THE_WARDEN = registerItem("heart_of_the_warden", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Supplier<Item> SHULKER_RESIDUE = registerItem("shulker_residue", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WITHERED_ESSENCE = registerItem("withered_essence", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANCIENT_ROSE_SEED = registerItem("ancient_rose_seed", () -> new ItemNameBlockItem(ModBlocks.ANCIENT_ROSE_CROP.get(), new Item.Properties()));
    public static final Supplier<Item> WARPED_WART = registerItem("warped_wart", () -> new ItemNameBlockItem(ModBlocks.WARPED_WART.get(), new Item.Properties()));
    public static final Supplier<Item> ONYX_BONE = registerItem("onyx_bone", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> NECRO_WEAVE = registerItem("necro_weave", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ECTOPLASM = registerItem("ectoplasm", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> MOURNING_GOLD_INGOT = registerItem("mourning_gold_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> INVOKE_SHARD = registerItem("invoke_shard", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> NACRE_BRICK = registerItem("nacre_brick", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> RAW_VERDINITE = registerItem("raw_verdinite", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> VERDINITE_INGOT = registerItem("verdinite_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> HARDENED_SLIME = registerItem("hardened_slime", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> TABLET_FRAGMENT = registerItem("tablet_fragment", () -> new DiscFragmentItem(new Item.Properties()));
    public static final Supplier<Item> LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", () -> new Item(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> END_CRYSTAL_SHARD = registerItem("end_crystal_shard", () -> new EndCrystalShardItem(new Item.Properties()));
    public static final Supplier<Item> RAVAGER_TOOTH = registerItem("ravager_tooth", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> INCENSE = registerItem("incense", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> UNFINISHED_CORE = registerItem("unfinished_core", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> REACTIVE_CORE = registerItem("reactive_core", () -> new Item(new Item.Properties().rarity(Rarity.RARE).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Supplier<Item> PITCH_INGOT = registerItem("pitch_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> TOWER_KEY_FRAGMENT = registerItem("tower_key_fragment", () -> new DiscFragmentItem(new Item.Properties()));
    public static final Supplier<Item> TOWER_KEY = registerItem("tower_key", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> RAW_VIVULITE = registerItem("raw_vivulite", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> VIVULITE_INGOT = registerItem("vivulite_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> PALE_PRISMARINE_SHARD = registerItem("pale_prismarine_shard", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> VOID_DIAMOND = registerItem("void_diamond", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> BLACK_EMERALD = registerItem("black_emerald", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final Supplier<Item> ELDER_GUARDIAN_SPINE = registerItem("elder_guardian_spine", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> EXPERIWINKLE_BULB = registerItem("experiwinkle_bulb", () -> new ItemNameBlockItem(ModBlocks.EXPERIWINKLE_CROP.get(), new Item.Properties()));
    public static final Supplier<Item> FROST_BONE = registerItem("frost_bone", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> SPAWNER_CHUNK = registerItem("spawner_chunk", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> GOLDEN_EGG = registerItem("golden_egg", () -> new GoldenEggItem(new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> SOUL = registerItem("soul", () -> new Item(new Item.Properties()));

    // Spawn Eggs
    public static final Supplier<Item> JUNGLE_SPIDER_SPAWN_EGG = registerItem("jungle_spider_spawn_egg", () -> new SpawnEggItem(ModEntity.JUNGLE_SPIDER.get(), 5324062, 2039583, new Item.Properties()));
    public static final Supplier<Item> CRAWLER_SPAWN_EGG = registerItem("crawler_spawn_egg", () -> new SpawnEggItem(ModEntity.CRAWLER.get(), 281859, 790817, new Item.Properties()));
    public static final Supplier<Item> PUMPKIN_GOLEM_SPAWN_EGG = registerItem("pumpkin_golem_spawn_egg", () -> new SpawnEggItem(ModEntity.PUMPKIN_GOLEM.get(), 14912029, 16761444, new Item.Properties()));
    public static final Supplier<Item> CROW_SPAWN_EGG = registerItem("crow_spawn_egg", () -> new SpawnEggItem(ModEntity.CROW.get(), 3618618, 8355725, new Item.Properties()));
    public static final Supplier<Item> GOLDEN_CHICKEN_SPAWN_EGG = registerItem("golden_chicken_spawn_egg", () -> new SpawnEggItem(ModEntity.GOLDEN_CHICKEN.get(), 10592673, 15582019, new Item.Properties()));

    // Misc Tools
    public static final Supplier<Item> PURIFIED_END_CRYSTAL = registerItem("purified_end_crystal", () -> new PurifiedEndCrystalItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final Supplier<Item> SNOW_MELT = registerItem("snow_melt", () -> new SnowMeltItem(new Item.Properties()));
    public static final Supplier<Item> ONYX_MEAL = registerItem("onyx_meal", () -> new OnyxMealItem(new Item.Properties()));
    public static final Supplier<Item> VOID_PEARL = registerItem("void_pearl", () -> new VoidPearlItem(new Item.Properties().stacksTo(16)));
    public static final Supplier<Item> CHEST_KEY = registerItem("chest_key", () -> new ChestKeyItem(new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> TOTEM_OF_AVARICE = registerItem("totem_of_avarice", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> MESSAGE_IN_A_BOTTLE = registerItem("message_in_a_bottle", () -> new BottleMessageItem(new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> MANA_BOTTLE = registerItem("mana_bottle", () -> new ManaBottleItem(new Item.Properties().rarity(Rarity.UNCOMMON).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Supplier<Item> BAIT = registerItem("bait", () -> new BaitItem(new Item.Properties()));
    public static final Supplier<Item> BOTTLED_MESSAGE = registerItem("bottled_message", () -> new BottleMessageItem(new Item.Properties().stacksTo(1).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).component(ModDataComponents.BOTTLE_CONTENT.get(), BottleContentComponent.DEFAULT)));
    public static final Supplier<Item> COBALT_FISHING_ROD = registerItem("cobalt_fishing_rod", () -> new CustomFishingRod(
            BobberType.COBALT,
            22,
            new Item.Properties().durability(256)));
    public static final Supplier<Item> COPPER_BOW = registerItem("copper_bow", () -> new FrontiersBowItem(
            SoundEvents.ARROW_SHOOT,
            1.2F, // <--- might be adjusted (same with all new bows below)
            20.0f,
            new Item.Properties().durability(384)) // I kind of want the bows to increase in durability with higher tiers, but I'll leave that decision up to you - xen
    );
    public static final Supplier<Item> IRON_BOW = registerItem("iron_bow", () -> new FrontiersBowItem(
            SoundEvents.ARROW_SHOOT,
            1.4F,
            20.0f,
            new Item.Properties().durability(384))
    );
    public static final Supplier<Item> DIAMOND_BOW = registerItem("diamond_bow", () -> new FrontiersBowItem(
            SoundEvents.ARROW_SHOOT,
            1.6F,
            20.0f,
            new Item.Properties().durability(384))
    );
    public static final Supplier<Item> NETHERITE_BOW = registerItem("netherite_bow", () -> new FrontiersBowItem(
            SoundEvents.ARROW_SHOOT,
            1.8F,
            20.0f,
            new Item.Properties().durability(384).fireResistant())
    );
    public static final Supplier<Item> ECHO_BOW = registerItem("echo_bow", () -> new FrontiersBowItem(
            ModSounds.ECHO_BOW_SHOOT.get(),
            1.25F,
            20.0f / 1.5f,
            new Item.Properties().durability(256))
    );
    public static final Supplier<Item> VERDINITE_BOW = registerItem("verdinite_bow", () -> new FrontiersBowItem(
            ModSounds.VERDINITE_BOW_SHOOT.get(),
            2.0F,
            20.0f,
            new Item.Properties().durability(384))
    );
    public static final Supplier<Item> COBALT_SHIELD = registerItem("cobalt_shield", () -> new CustomShieldItem(
            "cobalt_shield",
            COBALT_INGOT,
            new Item.Properties().durability(632).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY))
    );
    public static final Supplier<Item> PALE_TRIDENT = registerItem("pale_trident", () -> new CustomTridentItem(new Item.Properties()
            .rarity(Rarity.EPIC)
            .durability(480)
            .attributes(CustomTridentItem.createAttributes())
            .component(DataComponents.TOOL, CustomTridentItem.createToolProperties()))
    );
    public static final Supplier<Item> WITCH_HAT = registerItem("witch_hat", () -> new WitchHatItem(new Item.Properties()
            .durability(380)
            .stacksTo(1))
    );
    public static final Supplier<Item> SLIME_SHOES = registerItem( "slime_shoes", () -> new SlimeArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, 3,
            new Item.Properties()
                .durability(ArmorItem.Type.BOOTS.getDurability(10)))
    );
    // Arrows + Arrowheads
    public static final Supplier<Item> SPECTRAL_ARROW_ARROWHEAD = registerItem("spectral_arrow_arrowhead", () -> new ArrowheadItem("spectral", Items.SPECTRAL_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> SUBZERO_ARROW = registerItem("subzero_arrow", () -> new SubzeroArrowItem(new Item.Properties()));
    public static final Supplier<Item> SUBZERO_ARROW_ARROWHEAD = registerItem("subzero_arrow_arrowhead", () -> new ArrowheadItem("subzero", SUBZERO_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> WARP_ARROW = registerItem("warp_arrow", () -> new WarpArrowItem(new Item.Properties()));
    public static final Supplier<Item> WARP_ARROW_ARROWHEAD = registerItem("warp_arrow_arrowhead", () -> new ArrowheadItem("warp", WARP_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> BOUNCY_ARROW = registerItem("bouncy_arrow", () -> new BouncyArrowItem(new Item.Properties()));
    public static final Supplier<Item> BOUNCY_ARROW_ARROWHEAD = registerItem("bouncy_arrow_arrowhead", () -> new ArrowheadItem("bouncy", BOUNCY_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> DYNAMITE_ARROW = registerItem("dynamite_arrow", () -> new DynamiteArrowItem(new Item.Properties()));
    public static final Supplier<Item> DYNAMITE_ARROW_ARROWHEAD = registerItem("dynamite_arrow_arrowhead", () -> new ArrowheadItem("dynamite", DYNAMITE_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final Supplier<Item> PRISMARINE_ARROW = registerItem("prismarine_arrow", () -> new PrismarineArrowItem(new Item.Properties()));
    public static final Supplier<Item> PRISMARINE_ARROW_ARROWHEAD = registerItem("prismarine_arrow_arrowhead", () -> new ArrowheadItem("prismarine", PRISMARINE_ARROW, new Item.Properties().rarity(Rarity.UNCOMMON)));

    // Balls
    public static final Supplier<Item> BALL = registerItem("ball", () -> new BallItem(ChatFormatting.WHITE, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> BOUNCY_BALL = registerItem("bouncy_ball", () -> new BallItem(ChatFormatting.WHITE, 4, new Item.Properties().stacksTo(1)));
    public static final Map<DyeColor, Supplier<Item>> COLOR_BALLS = new HashMap<>();

    // Tool Classes
    // Mourning Gold
    public static final Supplier<Item> MOURNING_GOLD_PICKAXE = registerItem("mourning_gold_pickaxe", () -> new PickaxeItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.MOURNING_GOLD, 1.0F, -2.8F))));
    public static final Supplier<Item> MOURNING_GOLD_AXE = registerItem("mourning_gold_axe", () -> new AxeItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.MOURNING_GOLD, 5.0F, -3.0F))));
    public static final Supplier<Item> MOURNING_GOLD_SWORD = registerItem("mourning_gold_sword", () -> new SwordItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.MOURNING_GOLD, 3, -2.4F))));
    public static final Supplier<Item> MOURNING_GOLD_SHOVEL = registerItem("mourning_gold_shovel", () -> new ShovelItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.MOURNING_GOLD, 1.5F, -3.0F))));
    public static final Supplier<Item> MOURNING_GOLD_HOE = registerItem("mourning_gold_hoe", () -> new HoeItem(ModToolMaterial.MOURNING_GOLD, new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.MOURNING_GOLD, -2.5F, 1.0F))));
    // Obsidian (+ Broken Item)
    public static final Supplier<Item> OBSIDIAN_PICKAXE = registerItem("obsidian_pickaxe", () -> new UnbreakablePickaxeItem(Frontiers.id("obsidian_pickaxe_broken"), ModToolMaterial.OBSIDIAN,
            new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.OBSIDIAN, 1.0F, -2.8F)))
    );
    public static final Supplier<Item> OBSIDIAN_PICKAXE_BROKEN = registerItem("obsidian_pickaxe_broken", () -> new BrokenToolItem(OBSIDIAN_PICKAXE, ModToolMaterial.OBSIDIAN,
            new Item.Properties().stacksTo(1))
    );
    public static final Supplier<Item> OBSIDIAN_AXE = registerItem("obsidian_axe", () -> new UnbreakableAxeItem(Frontiers.id("obsidian_axe_broken"), ModToolMaterial.OBSIDIAN,
            new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.OBSIDIAN, 5.0F, -3.0F)))
    );
    public static final Supplier<Item> OBSIDIAN_AXE_BROKEN = registerItem("obsidian_axe_broken", () -> new BrokenToolItem(OBSIDIAN_AXE, ModToolMaterial.OBSIDIAN,
            new Item.Properties().stacksTo(1))
    );
    public static final Supplier<Item> OBSIDIAN_SWORD = registerItem("obsidian_sword", () -> new UnbreakableSwordItem(Frontiers.id("obsidian_sword_broken"), ModToolMaterial.OBSIDIAN,
            new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.OBSIDIAN, 3, -2.4F)))
    );
    public static final Supplier<Item> OBSIDIAN_SWORD_BROKEN = registerItem("obsidian_sword_broken", () -> new BrokenToolItem(OBSIDIAN_SWORD, ModToolMaterial.OBSIDIAN,
            new Item.Properties().stacksTo(1))
    );
    public static final Supplier<Item> OBSIDIAN_SHOVEL = registerItem("obsidian_shovel", () -> new UnbreakableShovelItem(Frontiers.id("obsidian_shovel_broken"), ModToolMaterial.OBSIDIAN,
            new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.OBSIDIAN, 1.5F, -3.0F)))
    );
    public static final Supplier<Item> OBSIDIAN_SHOVEL_BROKEN = registerItem("obsidian_shovel_broken", () -> new BrokenToolItem(OBSIDIAN_SHOVEL, ModToolMaterial.OBSIDIAN,
            new Item.Properties().stacksTo(1))
    );
    public static final Supplier<Item> OBSIDIAN_HOE = registerItem("obsidian_hoe", () -> new UnbreakableHoeItem(Frontiers.id("obsidian_hoe_broken"), ModToolMaterial.OBSIDIAN,
            new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.OBSIDIAN, -3.0F, 0.0F)))
    );
    public static final Supplier<Item> OBSIDIAN_HOE_BROKEN = registerItem("obsidian_hoe_broken", () -> new BrokenToolItem(OBSIDIAN_HOE, ModToolMaterial.OBSIDIAN,
            new Item.Properties().stacksTo(1))
    );
    // Cobalt
    public static final Supplier<Item> COBALT_PICKAXE = registerItem("cobalt_pickaxe", () -> new PickaxeItem(ModToolMaterial.COBALT, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.COBALT, 1.0F, -2.8F))));
    public static final Supplier<Item> COBALT_AXE = registerItem("cobalt_axe", () -> new AxeItem(ModToolMaterial.COBALT, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.COBALT, 5.0F, -3.0F))));
    public static final Supplier<Item> COBALT_SWORD = registerItem("cobalt_sword", () -> new SwordItem(ModToolMaterial.COBALT, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.COBALT, 3, -2.4F))));
    public static final Supplier<Item> COBALT_SHOVEL = registerItem("cobalt_shovel", () -> new ShovelItem(ModToolMaterial.COBALT, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.COBALT, 1.5F, -3.0F))));
    public static final Supplier<Item> COBALT_HOE = registerItem("cobalt_hoe", () -> new HoeItem(ModToolMaterial.COBALT, new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.COBALT, -5.0F, 1.0F))));
    // Verdinite
    public static final Supplier<Item> VERDINITE_PICKAXE = registerItem("verdinite_pickaxe", () -> new PickaxeItem(ModToolMaterial.VERDINITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.VERDINITE, 1.0F, -2.8F))));
    public static final Supplier<Item> VERDINITE_AXE = registerItem("verdinite_axe", () -> new AxeItem(ModToolMaterial.VERDINITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.VERDINITE, 5.0F, -3.0F))));
    public static final Supplier<Item> VERDINITE_SWORD = registerItem("verdinite_sword", () -> new SwordItem(ModToolMaterial.VERDINITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.VERDINITE, 3, -2.4F))));
    public static final Supplier<Item> VERDINITE_SHOVEL = registerItem("verdinite_shovel", () -> new ShovelItem(ModToolMaterial.VERDINITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.VERDINITE, 1.5F, -3.0F))));
    public static final Supplier<Item> VERDINITE_HOE = registerItem("verdinite_hoe", () -> new HoeItem(ModToolMaterial.VERDINITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.VERDINITE, -6.0F, 1.0F))));
    // Frostite
    public static final Supplier<Item> FROSTITE_PICKAXE = registerItem("frostite_pickaxe", () -> new PickaxeItem(ModToolMaterial.FROSTITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.FROSTITE, 1.0F, -2.8F))));
    public static final Supplier<Item> FROSTITE_AXE = registerItem("frostite_axe", () -> new AxeItem(ModToolMaterial.FROSTITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.FROSTITE, 5.0F, -3.0F))));
    public static final Supplier<Item> FROSTITE_SWORD = registerItem("frostite_sword", () -> new SwordItem(ModToolMaterial.FROSTITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.FROSTITE, 3, -2.4F))));
    public static final Supplier<Item> FROSTITE_SHOVEL = registerItem("frostite_shovel", () -> new ShovelItem(ModToolMaterial.FROSTITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.FROSTITE, 1.5F, -3.0F))));
    public static final Supplier<Item> FROSTITE_HOE = registerItem("frostite_hoe", () -> new HoeItem(ModToolMaterial.FROSTITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.FROSTITE, -6.0F, 1.0F))));
    // Vivulite
    public static final Supplier<Item> VIVULITE_PICKAXE = registerItem("vivulite_pickaxe", () -> new PickaxeItem(ModToolMaterial.VIVULITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolMaterial.VIVULITE, 1.0F, -2.8F))));
    public static final Supplier<Item> VIVULITE_AXE = registerItem("vivulite_axe", () -> new AxeItem(ModToolMaterial.VIVULITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolMaterial.VIVULITE, 5.0F, -3.0F))));
    public static final Supplier<Item> VIVULITE_SWORD = registerItem("vivulite_sword", () -> new SwordItem(ModToolMaterial.VIVULITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolMaterial.VIVULITE, 3, -2.4F))));
    public static final Supplier<Item> VIVULITE_SHOVEL = registerItem("vivulite_shovel", () -> new ShovelItem(ModToolMaterial.VIVULITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolMaterial.VIVULITE, 1.5F, -3.0F))));
    public static final Supplier<Item> VIVULITE_HOE = registerItem("vivulite_hoe", () -> new HoeItem(ModToolMaterial.VIVULITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolMaterial.VIVULITE, -7.0F, 1.0F))));
    // Brimtan
    public static final Supplier<Item> BRIMTAN_PICKAXE = registerItem("brimtan_pickaxe", () -> new PickaxeItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant().attributes(PickaxeItem.createAttributes(ModToolMaterial.BRIMTAN, 1.0F, -2.8F))));
    public static final Supplier<Item> BRIMTAN_AXE = registerItem("brimtan_axe", () -> new AxeItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant().attributes(AxeItem.createAttributes(ModToolMaterial.BRIMTAN, 5.0F, -3.0F))));
    public static final Supplier<Item> BRIMTAN_SWORD = registerItem("brimtan_sword", () -> new SwordItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(ModToolMaterial.BRIMTAN, 3, -2.4F))));
    public static final Supplier<Item> BRIMTAN_SHOVEL = registerItem("brimtan_shovel", () -> new ShovelItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant().attributes(ShovelItem.createAttributes(ModToolMaterial.BRIMTAN, 1.5F, -3.0F))));
    public static final Supplier<Item> BRIMTAN_HOE = registerItem("brimtan_hoe", () -> new HoeItem(ModToolMaterial.BRIMTAN, new Item.Properties().fireResistant().attributes(HoeItem.createAttributes(ModToolMaterial.BRIMTAN, -8.0F, 1.0F))));

    // Armor Classes
    // Rotcross
    public static final Supplier<Item> NECRO_WEAVE_HELMET = registerItem( "necro_weave_helmet", () -> new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(26))));
    public static final Supplier<Item> NECRO_WEAVE_CHESTPLATE = registerItem( "necro_weave_chestplate", () -> new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(26))));
    public static final Supplier<Item> NECRO_WEAVE_LEGGINGS = registerItem( "necro_weave_leggings", () -> new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(26))));
    public static final Supplier<Item> NECRO_WEAVE_BOOTS = registerItem( "necro_weave_boots", () -> new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(26))));
    // Mourning Gold
    public static final Supplier<Item> MOURNING_GOLD_HELMET = registerItem( "mourning_gold_helmet", () -> new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
    public static final Supplier<Item> MOURNING_GOLD_CHESTPLATE = registerItem( "mourning_gold_chestplate", () -> new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(20))));
    public static final Supplier<Item> MOURNING_GOLD_LEGGINGS = registerItem( "mourning_gold_leggings", () -> new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(20))));
    public static final Supplier<Item> MOURNING_GOLD_BOOTS = registerItem( "mourning_gold_boots", () -> new ArmorItem(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(20))));
    // Cobalt
    public static final Supplier<Item> COBALT_HELMET = registerItem( "cobalt_helmet", () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(41))));
    public static final Supplier<Item> COBALT_CHESTPLATE = registerItem( "cobalt_chestplate", () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(41))));
    public static final Supplier<Item> COBALT_LEGGINGS = registerItem( "cobalt_leggings", () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(41))));
    public static final Supplier<Item> COBALT_BOOTS = registerItem( "cobalt_boots", () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(41))));
    // Verdinite
    public static final Supplier<Item> VERDINITE_HELMET = registerItem( "verdinite_helmet", () -> new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45))));
    public static final Supplier<Item> VERDINITE_CHESTPLATE = registerItem( "verdinite_chestplate", () -> new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final Supplier<Item> VERDINITE_LEGGINGS = registerItem( "verdinite_leggings", () -> new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final Supplier<Item> VERDINITE_BOOTS = registerItem( "verdinite_boots", () -> new ArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    // Frostite
    public static final Supplier<Item> FROSTITE_HELMET = registerItem( "frostite_helmet", () -> new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(45))));
    public static final Supplier<Item> FROSTITE_CHESTPLATE = registerItem( "frostite_chestplate", () -> new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(45))));
    public static final Supplier<Item> FROSTITE_LEGGINGS = registerItem( "frostite_leggings", () -> new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(45))));
    public static final Supplier<Item> FROSTITE_BOOTS = registerItem( "frostite_boots", () -> new ArmorItem(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(45))));
    // Vivulite
    public static final Supplier<Item> VIVULITE_HELMET = registerItem( "vivulite_helmet", () -> new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(50))));
    public static final Supplier<Item> VIVULITE_CHESTPLATE = registerItem( "vivulite_chestplate", () -> new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(50))));
    public static final Supplier<Item> VIVULITE_LEGGINGS = registerItem( "vivulite_leggings", () -> new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(50))));
    public static final Supplier<Item> VIVULITE_BOOTS = registerItem( "vivulite_boots", () -> new ArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(50))));
    // Brimtan
    public static final Supplier<Item> BRIMTAN_HELMET = registerItem( "brimtan_helmet", () -> new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(55)).fireResistant()));
    public static final Supplier<Item> BRIMTAN_CHESTPLATE = registerItem( "brimtan_chestplate", () -> new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(55)).fireResistant()));
    public static final Supplier<Item> BRIMTAN_LEGGINGS = registerItem( "brimtan_leggings", () -> new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(55)).fireResistant()));
    public static final Supplier<Item> BRIMTAN_BOOTS = registerItem( "brimtan_boots", () -> new ArmorItem(ModArmorMaterials.BRIMTAN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(55)).fireResistant()));
    // Plate
    public static final Supplier<Item> PLATE_HELMET = registerItem( "plate_helmet", () -> new PlateArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(31))));
    public static final Supplier<Item> PLATE_CHESTPLATE = registerItem( "plate_chestplate", () -> new PlateArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(31))));
    public static final Supplier<Item> PLATE_LEGGINGS = registerItem( "plate_leggings", () -> new PlateArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(31))));
    public static final Supplier<Item> PLATE_BOOTS = registerItem( "plate_boots", () -> new PlateArmorItem(ModArmorMaterials.PLATE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(31))));

    // Horse Armors
    public static final Supplier<Item> COBALT_HORSE_ARMOR = registerItem("cobalt_horse_armor", () -> new AnimalArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> VERDINITE_HORSE_ARMOR = registerItem("verdinite_horse_armor", () -> new AnimalArmorItem(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));
    public static final Supplier<Item> VIVULITE_HORSE_ARMOR = registerItem("vivulite_horse_armor", () -> new AnimalArmorItem(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));

    // Food
    public static final Supplier<Item> MARSHMALLOW = registerItem("marshmallow", () -> new CustomTimeFoodItem(0.4F, new Item.Properties().food(ModFoodComponents.MARSHMALLOW)));
    public static final Supplier<Item> ROASTED_MARSHMALLOW = registerItem("roasted_marshmallow", () -> new CustomTimeFoodItem(0.4F, new Item.Properties().food(ModFoodComponents.ROASTED_MARSHMALLOW)));
    public static final Supplier<Item> LEVI_ROLL = registerItem("levi_roll", () -> new Item(new Item.Properties().food(ModFoodComponents.LEVI_ROLL)));
    public static final Supplier<Item> POMEGRANATE = registerItem("pomegranate", () -> new ChanceVaryFoodItem(0.25F, new Item.Properties().food(ModFoodComponents.POMEGRANATE)));
    public static final Supplier<Item> TRUFFLE = registerItem("truffle", () -> new TruffleItem(new Item.Properties().food(ModFoodComponents.TRUFFLE).rarity(Rarity.RARE)));
    public static final Supplier<Item> TRUFFLE_POTATO_PUFF = registerItem("truffle_potato_puff", () -> new Item(new Item.Properties().food(ModFoodComponents.TRUFFLE_POTATO_PUFF)));
    public static final Supplier<Item> TRUFFLE_OIL = registerItem("truffle_oil", () -> new DrinkItem(new Item.Properties().food(ModFoodComponents.TRUFFLE_OIL)));
    public static final Supplier<Item> GUARDIAN_SLICE = registerItem("guardian_slice", () -> new GuardianSliceItem(new Item.Properties().food(ModFoodComponents.GUARDIAN_SLICE)));
    public static final Supplier<Item> ELDER_GUARDIAN_SLICE = registerItem("elder_guardian_slice", () -> new GuardianSliceItem(new Item.Properties().food(ModFoodComponents.ELDER_GUARDIAN_SLICE)));
    public static final Supplier<Item> COOKED_GUARDIAN_SLICE = registerItem("cooked_guardian_slice", () -> new GuardianSliceItem(new Item.Properties().food(ModFoodComponents.COOKED_GUARDIAN_SLICE)));
    public static final Supplier<Item> COOKED_ELDER_GUARDIAN_SLICE = registerItem("cooked_elder_guardian_slice", () -> new GuardianSliceItem(new Item.Properties().food(ModFoodComponents.COOKED_ELDER_GUARDIAN_SLICE)));
    public static final Supplier<Item> APPLE_OF_ENLIGHTENMENT = registerItem("apple_of_enlightenment", () -> new HealthBuffItem("UsedAppleBuff", true, new Item.Properties().food(ModFoodComponents.APPLE_OF_ENLIGHTENMENT).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(ModRarity.FRONTIERS_MYTHICAL)));
    public static final Supplier<Item> FRUITCAKE_SLICE = registerItem("fruitcake_slice", () -> new FruitcakeItem(new Item.Properties().food(ModFoodComponents.FRUITCAKE)));

    // Normal Smithing Templates
    public static final Supplier<Item> OBSIDIAN_UPGRADE_SMITHING_TEMPLATE = registerItem("obsidian_upgrade_smithing_template", () -> new SmithingTemplateItem(
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
    public static final Supplier<Item> BRIMTAN_SHELL_SWORD = registerItem("brimtan_shell_sword", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_SHOVEL = registerItem("brimtan_shell_shovel", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_HOE = registerItem("brimtan_shell_hoe", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_AXE = registerItem("brimtan_shell_axe", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_PICKAXE = registerItem("brimtan_shell_pickaxe", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));

    public static final Supplier<Item> BRIMTAN_SHELL_HELMET = registerItem("brimtan_shell_helmet", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_CHESTPLATE = registerItem("brimtan_shell_chestplate", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_LEGGINGS = registerItem("brimtan_shell_leggings", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));
    public static final Supplier<Item> BRIMTAN_SHELL_BOOTS = registerItem("brimtan_shell_boots", () -> new DiscFragmentItem(new Item.Properties().fireResistant()));

    public static final Supplier<Item> BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_helmet_upgrade_smithing_template", () -> new SmithingTemplateItem(
            SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("helmet"),
            SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("helmet"),
            SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("helmet"),
            SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("helmet"),
            SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("helmet"),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_HELMET_TEXTURE),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_HELMET_TEXTURE)
        )
    );
    public static final Supplier<Item> BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_chestplate_upgrade_smithing_template", () -> new SmithingTemplateItem(
            SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("chestplate"),
            SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("chestplate"),
            SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("chestplate"),
            SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("chestplate"),
            SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("chestplate"),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE)
        )
    );
    public static final Supplier<Item> BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_leggings_upgrade_smithing_template", () -> new SmithingTemplateItem(
            SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("leggings"),
            SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("leggings"),
            SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("leggings"),
            SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("leggings"),
            SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("leggings"),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE)
        )
    );
    public static final Supplier<Item> BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_boots_upgrade_smithing_template", () -> new SmithingTemplateItem(
            SmithTemplate.BRIMTAN_UPGRADE_APPLIES_TO_TEXT.get("boots"),
            SmithTemplate.BRIMTAN_UPGRADE_INGREDIENTS_TEXT.get("boots"),
            SmithTemplate.BRIMTAN_UPGRADE_TEXT.get("boots"),
            SmithTemplate.BRIMTAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT.get("boots"),
            SmithTemplate.BRIMTAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT.get("boots"),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_BOOTS_TEXTURE),
            List.of(SmithTemplate.EMPTY_ARMOR_SLOT_BOOTS_TEXTURE)
        )
    );
    public static final Supplier<Item> BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE = registerItem("brimtan_tool_upgrade_smithing_template", () -> new SmithingTemplateItem(
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
    public static final Supplier<Item> PULSE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("pulse_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.PULSE));
    public static final Supplier<Item> SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("sludge_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.SLUDGE));
    public static final Supplier<Item> PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("photon_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.PHOTON));

    // Unfinished core plates
    public static final Supplier<Item> DEPTHS_CORE_PLATE = registerItem("depths_core_plate", () -> new CorePlateItem(CorePlateItem.DEPTH_TYPE_TEXT, new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));
    public static final Supplier<Item> FRONTAL_CORE_PLATE = registerItem("frontal_core_plate", () -> new CorePlateItem(CorePlateItem.FRONTAL_TYPE_TEXT, new Item.Properties().rarity(Rarity.UNCOMMON)));

    // Tomes (WIP Set)
    public static final Supplier<Item> TOME_OF_FANGS = registerItem("tome_of_fangs", () -> new EvokerTomeItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    // Music Discs
    public static final Supplier<Item> MUSIC_DISC_DIAPHRAGM = registerItem("music_disc_diaphragm", () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DISC_DIAPHRAGM_KEY).stacksTo(1).rarity(Rarity.RARE)));

    // Spirit Candle
    public static final Supplier<Item> SPIRIT_CANDLE = registerItem("spirit_candle", () -> new SpiritCandleItem(ModBlocks.SPIRIT_CANDLE.get(), new Item.Properties()));

    // Doors
    public static final Supplier<Item> EBONCORK_DOOR = registerItem("eboncork_door", () -> new DoubleHighBlockItem(ModBlocks.EBONCORK_DOOR.get(), new Item.Properties()));
    public static final Supplier<Item> BLIGHTED_BIRCH_DOOR = registerItem("blighted_birch_door", () -> new DoubleHighBlockItem(ModBlocks.BLIGHTED_BIRCH_DOOR.get(), new Item.Properties()));

    // Phantom-Stitch Bed
    public static final Supplier<Item> PHANTOM_STITCH_BED = registerItem("phantom_stitch_bed", () -> new BedItem(ModBlocks.PHANTOM_STITCH_BED.get(), new Item.Properties().stacksTo(1)));

    private static Supplier<Item> registerItem(String name, Supplier<Item> item)
    {
        return VectorLib.REGISTRY.registerItem(Frontiers.MOD_ID, name, item);
    }

    // Registers mod items. Just sends a log message.
    public static void registerModItems()
    {
        //Frontiers.LOGGER.info("Registering Mod Items for " + Frontiers.MOD_ID);
        registerDyeables();
    }

    /** Registers all dye-able items */
    private static void registerDyeables()
    {
        for (DyeColor color : DyeColor.values())
        {
            COLOR_BALLS.put(color, registerItem(color.getName() + "_ball", () -> new BallItem(BallItem.getTxtColorOrDefault(color), new Item.Properties().stacksTo(1))));
        }
    }
}
