package net.artyrian.frontiers.item.data;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

// Materials added by Frontiers.
public enum ModToolMaterial implements ToolMaterial
{
    // Tool mats
    // Add 235 per durability
    COBALT(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL, 2501, 9.0f, 5.0f, 17, () -> Ingredient.ofItems(ModItem.COBALT_INGOT)),
    VERDINITE(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL, 2736, 9.0f, 6.0f, 12, () -> Ingredient.ofItems(ModItem.VERDINITE_INGOT)),
    FROSTITE(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL, 2736, 9.0f, 6.0f, 18, () -> Ingredient.ofItems(ModItem.FROSTITE_INGOT)),
    VIVULITE(ModTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL, 2971, 9.0f, 7.0f, 20, () -> Ingredient.ofItems(ModItem.VIVULITE_INGOT)),
    BRIMTAN(ModTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL, 3206, 10.0f, 8.0f, 12, () -> Ingredient.ofItems(ModItem.BRIMTAN_INGOT)),
    MOURNING_GOLD(BlockTags.INCORRECT_FOR_IRON_TOOL, 875, 7.0f, 2.5f, 15, () -> Ingredient.ofItems(ModItem.MOURNING_GOLD_INGOT)),
    OBSIDIAN(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 125, 12.0f, 3.0f, 22, () -> Ingredient.ofItems(Items.OBSIDIAN)),

    // Unique materials
    TOME(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 256, 0.0f, 0.0f, 12, () -> Ingredient.ofItems(ModItem.INVOKE_SHARD));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient)
    {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability()
    {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier()
    {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag()
    {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }
}