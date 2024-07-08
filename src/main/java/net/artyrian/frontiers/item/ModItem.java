package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.SmithTemplate;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
    public static final Item FROSTITE_INGOT = registerItem("frostite_ingot", new Item(new Item.Settings()));
    public static final Item CURSED_TABLET = registerItem("cursed_tablet", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item OBSIDIAN_CASING = registerItem("obsidian_casing", new Item(new Item.Settings().fireproof()));
    public static final Item HEART_OF_THE_WARDEN = registerItem("heart_of_the_warden", new Item(new Item.Settings().rarity(Rarity.EPIC).maxCount(16).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item SHULKER_RESIDUE = registerItem("shulker_residue", new Item(new Item.Settings()));

    // Misc Tools
    public static final Item COBALT_FISHING_ROD = registerItem("cobalt_fishing_rod", new FishingRodItem(
            new Item.Settings().maxDamage(256)));

    // Tool Classes
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

    // Adds an item to the Minecraft registry and returns the value of that operation - used in item list.
    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), item);
    }

    // Registers mod items. Just sends a log message.
    public static void registerModItems()
    {
        Frontiers.LOGGER.info("Registering Mod Items for " + Frontiers.MOD_ID);
    }
}
