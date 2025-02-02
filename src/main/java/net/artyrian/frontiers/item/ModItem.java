package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.custom.*;
import net.artyrian.frontiers.item.data.ModArmorMaterials;
import net.artyrian.frontiers.item.data.ModFoodComponents;
import net.artyrian.frontiers.item.data.ModToolMaterial;
import net.artyrian.frontiers.misc.ModRarity;
import net.artyrian.frontiers.misc.SmithTemplate;
import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.TrailRuinsGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

// Registers all mod items to Minecraft registries.
public class ModItem
{
    // ITEM LIST.

    // Materials
    public static final Item RAW_COBALT = registerItem("raw_cobalt", new Item(new Item.Settings()));
    public static final Item COBALT_INGOT = registerItem("cobalt_ingot", new Item(new Item.Settings()));
    public static final Item BRIMTAN_INGOT = registerItem("brimtan_ingot", new Item(new Item.Settings()));
    public static final Item RAW_FROSTITE = registerItem("raw_frostite", new Item(new Item.Settings()));
    public static final Item FROSTITE_INGOT = registerItem("frostite_ingot", new Item(new Item.Settings()));
    public static final Item CURSED_TABLET = registerItem("cursed_tablet", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item OBSIDIAN_CASING = registerItem("obsidian_casing", new Item(new Item.Settings().fireproof()));
    public static final Item HEART_OF_THE_WARDEN = registerItem("heart_of_the_warden", new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(16).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item SHULKER_RESIDUE = registerItem("shulker_residue", new Item(new Item.Settings()));
    public static final Item WITHERED_ESSENCE = registerItem("withered_essence", new Item(new Item.Settings()));
    public static final Item ANCIENT_ROSE_SEED = registerItem("ancient_rose_seed", new AliasedBlockItem(ModBlocks.ANCIENT_ROSE_CROP, new Item.Settings()));
    public static final Item ONYX_BONE = registerItem("onyx_bone", new Item(new Item.Settings()));
    public static final Item NECRO_WEAVE = registerItem("necro_weave", new Item(new Item.Settings()));
    public static final Item ECTOPLASM = registerItem("ectoplasm", new Item(new Item.Settings()));
    public static final Item MOURNING_GOLD_INGOT = registerItem("mourning_gold_ingot", new Item(new Item.Settings()));
    public static final Item INVOKE_SHARD = registerItem("invoke_shard", new Item(new Item.Settings()));
    public static final Item NACRE_BRICK = registerItem("nacre_brick", new Item(new Item.Settings()));
    public static final Item RAW_VERDINITE = registerItem("raw_verdinite", new Item(new Item.Settings()));
    public static final Item VERDINITE_INGOT = registerItem("verdinite_ingot", new Item(new Item.Settings()));
    public static final Item TABLET_FRAGMENT = registerItem("tablet_fragment", new Item(new Item.Settings()));
    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new Item(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));
    public static final Item END_CRYSTAL_SHARD = registerItem("end_crystal_shard", new Item(new Item.Settings()));
    public static final Item RAVAGER_TOOTH = registerItem("ravager_tooth", new Item(new Item.Settings()));
    public static final Item INCENSE = registerItem("incense", new Item(new Item.Settings()));

    // Misc Tools
    public static final Item PURIFIED_END_CRYSTAL = registerItem("purified_end_crystal", new PurifiedEndCrystalItem(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item COBALT_FISHING_ROD = registerItem("cobalt_fishing_rod", new CustomFishingRod(
            BobberType.COBALT,
            22,
            new Item.Settings().maxDamage(256)));

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
    // Obsidian
    public static final Item OBSIDIAN_PICKAXE = registerItem("obsidian_pickaxe",
            new PickaxeItem(ModToolMaterial.OBSIDIAN, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 1.0F, -2.8F)))
    );
    public static final Item OBSIDIAN_AXE = registerItem("obsidian_axe",
            new AxeItem(ModToolMaterial.OBSIDIAN, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 5.0F, -3.0F)))
    );
    public static final Item OBSIDIAN_SWORD = registerItem("obsidian_sword",
            new SwordItem(ModToolMaterial.OBSIDIAN, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 3, -2.4F)))
    );
    public static final Item OBSIDIAN_SHOVEL = registerItem("obsidian_shovel",
            new ShovelItem(ModToolMaterial.OBSIDIAN, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, 1.5F, -3.0F)))
    );
    public static final Item OBSIDIAN_HOE = registerItem("obsidian_hoe",
            new HoeItem(ModToolMaterial.OBSIDIAN, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterial.OBSIDIAN, -3.0F, 0.0F)))
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

    // Armor Classes
    // Rotcross
    public static final Item NECRO_WEAVE_HELMET = registerItem( "necro_weave_helmet",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(23))
            )
    );
    public static final Item NECRO_WEAVE_CHESTPLATE = registerItem( "necro_weave_chestplate",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(23))
            )
    );
    public static final Item NECRO_WEAVE_LEGGINGS = registerItem( "necro_weave_leggings",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(23))
            )
    );
    public static final Item NECRO_WEAVE_BOOTS = registerItem( "necro_weave_boots",
            new ArmorItem(ModArmorMaterials.NECRO_WEAVE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(23))
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
    public static final Item TRUFFLE = registerItem("truffle",
            new TruffleItem(new Item.Settings().food(ModFoodComponents.TRUFFLE).rarity(Rarity.RARE))
    );
    public static final Item APPLE_OF_ENLIGHTENMENT = registerItem("apple_of_enlightenment",
            new HealthBuffItem(
                    "UsedAppleBuff",
                    true,
                    new Item.Settings().food(ModFoodComponents.APPLE_OF_ENLIGHTENMENT).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true).rarity(ModRarity.FRONTIERS_MYTHICAL)
            )
    );

    // Smithing Templates
    public static final Item OBSIDIAN_UPGRADE_SMITHING_TEMPLATE = registerItem("obsidian_upgrade_smithing_template",
            new SmithingTemplateItem(
                    SmithTemplate.OBSIDIAN_UPGRADE_APPLIES_TO_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_INGREDIENTS_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.OBSIDIAN_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                    SmithTemplate.armorUpgradeSlotTextures(),
                    SmithTemplate.casingAdditionsTexture()
            )
    );

    // Tomes (WIP Set)
    public static final Item TOME_OF_FANGS = registerItem("tome_of_fangs",
            new TomeItem(new Item.Settings().rarity(Rarity.UNCOMMON))
    );

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
